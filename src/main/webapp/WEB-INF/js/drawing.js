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
//先在canvas中绘图，再转换成img显示（可以自适应显示图片）
$.get("/getPWV", function drawPWV(data) {
	// 清空画布
	clearArea();
	// 绘制格网
	drawGrid(DRAW_WIDTH, DRAW_HEIGHT, GRID_WIDTH, GRID_HEIGHT);
	drawTitle(width, height);
	$.each(data, function(i, pwvData) {
		// 坐标原点平移
		// x坐标缩放2倍，共10天480个点——960，加上最左右的空隙共1080
		draw(i * SCALE_X + MOVE_X, height - pwvData.pw * SCALE_Y - MOVE_Y);
	});
	transImg();
});

function draw(x, y) {
	ctx.beginPath();
	ctx.strokeStyle = "blue";
	ctx.lineWidth = "1";
	ctx.moveTo(lastX, lastY);
	ctx.lineTo(x, y);
	ctx.closePath();
	ctx.stroke();
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
	ctx.fillText("PWV时间序列图（最邻近10天）", parseInt(width / 2), 25);
}

function transImg(){
	var imgSrc = document.getElementById("myCanvas").toDataURL("image/png");  
	document.getElementById("imgId").src = imgSrc;  
}