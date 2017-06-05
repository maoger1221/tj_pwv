var lastX, lastY;
var ctx;

ctx = document.getElementById('myCanvas').getContext("2d");
var GRID_WIDTH = 20 ;
var GRID_HEIGHT = 10 ;
var SCALE_X=2 ;
var SCALE_Y=10 ;
//修改坐标轴高度，调整一下四个属性
var DRAW_WIDTH=960;
var DRAW_HEIGHT=500;
//960+60+60
$("#myCanvas").attr("width", 1080);  
//500+50+50
$("#myCanvas").attr("height", 600);  
// 坐标原点平移
var MOVE_X = 60;
var MOVE_Y = 50;

var width = ctx.canvas.width;
var height = ctx.canvas.height;
//返回的vo
var myvo;

//在绘图页面的请求
$("#drawingbutton").click(
		function() {

			//清空session防止数据互扰getPWV getDbDrawing
			$.get("/clearSession",function(data) {});
			$.ajax({
				type : "POST",
				url : "/getDbDrawing",
				data : $('#drawingform').serialize(),
				success : function(data) {
					// 清空画布
					clearArea();
					// 绘制格网
					drawGrid(DRAW_WIDTH, DRAW_HEIGHT, GRID_WIDTH, GRID_HEIGHT);
					drawTitle(width, height);

                    ctx.beginPath();
					ctx.lineWidth = "1";
                    // 坐标原点平移
					// x坐标缩放2倍，共10天480个点——960，加上最左右的空隙共1080
					if(typeof(data.objs.iwvdata) != "undefined") {
						$.each(data.objs.iwvdata, function (i, data) {
							draw(i * SCALE_X + MOVE_X, height - data.iwv * SCALE_Y - MOVE_Y, i, "red");
						});
					}
					if(typeof(data.objs.pwvdata) != "undefined") {
						$.each(data.objs.pwvdata, function (i, data) {
							draw(i * SCALE_X + MOVE_X, height - data.pw * SCALE_Y - MOVE_Y, i, "blue");
						});
					}
                    ctx.closePath();
					ctx.stroke();
					transImg();
				}
			});
			
		});

//在数据分析页面的请求
$("#analysisbutton").click(
    function() {
        $("#progressbar1").text("数据正在处理，请稍后。。。")
        $("#progressbar2").attr("style","width: 50%")
        $("#myTable").hide()
        //清空session防止数据互扰getPWV getDbDrawing
        $.get("/clearSession",function(data) {});
        $.ajax({
            type : "POST",
            url : "/getAnalysis",
            data : $('#drawingform').serialize(),
            success : function(data) {
                myvo = data;
            	//向表格中添加数据
                appendTable(data);

                // 清空画布
                clearArea();
                // 绘制格网
                drawGrid(DRAW_WIDTH, DRAW_HEIGHT, GRID_WIDTH, GRID_HEIGHT);
                drawTitle(width, height);
                ctx.beginPath();
                ctx.lineWidth = "1";
                // 坐标原点平移
                // x坐标缩放2倍，共10天480个点——960，加上最左右的空隙共1080
                if(typeof(data.objs.pwv_before) != "undefined") {
                    $.each(data.objs.pwv_before, function (i, data) {
                        draw(i * SCALE_X + MOVE_X, height - data.pw * SCALE_Y - MOVE_Y, i, "red");
                    });
                }
                if(typeof(data.objs.pwv_after) != "undefined") {
                    $.each(data.objs.pwv_after, function (i, data) {
                        draw(i * SCALE_X + MOVE_X, height - data * SCALE_Y - MOVE_Y, i, "blue");
                    });
                }

                if(typeof(data.objs.predict) != "undefined") {
                    $.each(data.objs.predict, function (i, data) {
                    	//预测5个，去掉前5个，后5个变红（原来有480个，预测5个变成485个）
                    	if(i > 4 && i < 485 - 5){
                        	draw((i-5) * SCALE_X + MOVE_X, height - data * SCALE_Y - MOVE_Y, (i-5), "blue");
						}else if(i >= 485 - 5){
                            draw((i-5) * SCALE_X + MOVE_X, height - data * SCALE_Y - MOVE_Y, (i-5), "red");
						}
                    });
                }
                ctx.closePath();
                ctx.stroke();
                transImg();

            },
            complete: function () {
                $("#progressbar1").text("数据处理完成。。。")
                $("#progressbar2").attr("style","width: 100%")
                $("#myTable").show()
            }
        });

    });

// 向表格中添加数据
function append(data,name) {

	var obj = "<tr>" + "<td align='center'>" + name + "</td>" + "<td align='center'>" + data + "</td>" + "</tr>";
	$("#tbody").append(obj);
    $("#tbody").trigger("create");
}
function append1(data,name,id) {

    var obj = "<tr>" + "<td align='center'>" + name + "</td>" + "<td align='center'>" +
        "<button id='" + id + "' type='button' class='btn btn-xs btn-info'>"+ "<i class='fa fa-bolt fa-fw'></i>" +data +"</button>" + "</td>" + "</tr>";
    $("#tbody").append(obj);
    $("#"+id).bind("click",function() {
        download(id);
    });
    $("#tbody").trigger("create");
}

function appendTable(data) {
    $("#tbody").children().remove();
    if(typeof(data.objs.pwv_before) != "undefined") {
        append1("点击下载","处理前信号","pwv_before")
    }
    if(typeof(data.objs.pwv_after) != "undefined") {
        append1("点击下载","处理后信号","pwv_after")
    }
    if(typeof(data.objs.p_filter) != "undefined") {
        append(data.objs.p_filter,"滤波截断值","p_filter")
    }
    if(typeof(data.objs.r) != "undefined") {
        append1("点击下载","残差","r")
    }
    if(typeof(data.objs.sigma) != "undefined") {
        append1("点击下载","奇异值","sigma")
    }
    if(typeof(data.objs.trends) != "undefined") {
        append(data.objs.trends,"趋势项序号")
    }
    if(typeof(data.objs.per) != "undefined") {
        append(data.objs.per,"贡献率")
    }
    /*if(typeof(data.objs.y) != "undefined") {
        append1("点击下载","各个重构信号rc",y)
    }*/
    if(typeof(data.objs.p) != "undefined") {
        append(data.objs.p,"周期项序号(每个周期项由两项构成，p和p+1)")
    }
    if(typeof(data.objs.ffk) != "undefined") {
        append(data.objs.ffk,"频率1")
    }
    if(typeof(data.objs.ffk1) != "undefined") {
        append(data.objs.ffk1,"频率2")
    }
    if(typeof(data.objs.predict) != "undefined") {
        append1("点击下载","预测信号","predict")
    }
}

//点击数据下载按钮
function download(id) {
    if( id == "pwv_before"){
        window.location.href="/download/"+myvo.objs.pwv_before_name;
    }
    if( id == "pwv_after"){
        window.location.href="/download/"+myvo.objs.pwv_after_name;
    }
    if( id == "r"){
        window.location.href="/download/"+myvo.objs.r_name;
    }
    if( id == "sigma"){
        window.location.href="/download/"+myvo.objs.sigma_name;
    }
    if( id == "predict"){
        window.location.href="/download/"+myvo.objs.predict_name;
    }
}

function draw(x, y, i,color) {
	if(i != 0){
		ctx.beginPath();
		ctx.strokeStyle = color;
		ctx.lineWidth = "1";
		ctx.moveTo(lastX, lastY);
		ctx.lineTo(x, y);
		ctx.closePath();
		ctx.stroke();
	}
	lastX = x;
	lastY = y;
}
// 清空绘图区
function clearArea() {
	ctx.setTransform(1, 0, 0, 1, 0, 0);
	ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
}

// 绘制网格
function drawGrid(CANVAS_WIDTH, CANVAS_HEIGHT, GRID_WIDTH, GRID_HEIGHT) {
	var rows = parseInt(CANVAS_WIDTH / GRID_WIDTH/SCALE_X);
	var cols = parseInt(CANVAS_HEIGHT / GRID_HEIGHT/SCALE_Y);
	for (var i = 0; i <= rows; i++) {
		for (var j = 0; j <= cols; j++) {
			// 最后少画一个
			if (i != rows && j != cols ) {
				drawRect(i, j, GRID_WIDTH, GRID_HEIGHT);
			}
			if ((i == 0 || j == 0)) {
				drawText(i, j, GRID_WIDTH, GRID_HEIGHT); // 增加坐标
			}
		}
	}
}

// 绘制横坐标与纵坐标
function drawText(i, j, GRID_WIDTH, GRID_HEIGHT) {
	ctx.font = "bold 16px Arial";
	ctx.textAlign = "center";
	//时间间隔为30min，所以横坐标要除以2
	var x_axis = Math.round(i * GRID_WIDTH/2) + "";
	var y_axis = Math.round(j * GRID_HEIGHT) + "";

	// 绘制横坐标(文字，x，y)
	// 坐标原点平移
	ctx.fillText(x_axis, i * GRID_WIDTH * SCALE_X + MOVE_X, height - MOVE_Y + 20);
	// 绘制纵坐标
	// 只画一个原点
	if (y_axis != "0") {
		ctx.fillText(y_axis, MOVE_X - 15, height - j * GRID_HEIGHT * SCALE_Y- MOVE_Y + 5);
	}
}

// 绘制矩形
function drawRect(i, j, GRID_WIDTH, GRID_HEIGHT) {
	ctx.lineWidth = 1;
	ctx.strokeStyle = "lightgrey";
	// 坐标原点平移
	ctx.strokeRect(i * GRID_WIDTH * SCALE_X + MOVE_X, j * GRID_HEIGHT * SCALE_Y + MOVE_Y,
			GRID_WIDTH * SCALE_X, GRID_HEIGHT * SCALE_Y);
}
// 画坐标轴单位及标题
function drawTitle(width, height) {
	ctx.font = "bold 16px Arial";
	ctx.textAlign = "center";
	ctx.fillText("时间/h", parseInt(width / 2), height - 5);

	ctx.save();
	ctx.rotate(-90 * Math.PI / 180);
	ctx.fillText("PWV/mm", -parseInt(height / 2), 20);
	ctx.restore();

	ctx.font = "bold 20px Arial";
	ctx.textAlign = "center";
	ctx.fillText("PWV时间序列图", parseInt(width / 2), 25);
}

function transImg(){
	var imgSrc = document.getElementById("myCanvas").toDataURL("image/png");  
	document.getElementById("imgId").src = imgSrc;  
}


//ajax上传数据
$("#owndatabutton").click(
    function() {
        // var formData = new FormData($("#uploadForm")[0]);
        var formData = new FormData();
        formData.append('file', $('#file')[0].files[0]);
        $.ajax({
            url : "/fileUpload",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success : function(data) {
                alert(data.objs.msg);
                $("#owndataHidden").val(data.objs.filename);
            }
        });

    });