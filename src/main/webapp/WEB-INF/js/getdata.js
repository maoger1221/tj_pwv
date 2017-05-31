/*$("#prePage").click(function() {
	$.get("/getdbdata", {
		pageNum : "1"
	}, function(data) {
		var records = JSON.parse(data);
		$.each(records, function(i, record) {
			alert(record.id);
		});

	});
});*/
//上一页ajax请求
$("#prePage").click(
		function() {
			checkfirst();
			// 判断是否为首页
			if ($.session.get("pageNum") != "1") {
				$.ajax({
					type : "POST",
					url : "/getdbdata",
					data : {
						pageNum : parseInt($.session.get("pageNum"))
								- parseInt(1)
					},
					dataType : "json",
					success : function(data) {
						append(data)
					}
				});

				$.session.set("pageNum", parseInt($.session.get("pageNum"))
						- parseInt(1));
			} else {
				alert("当前已是第一页");
			}
		});


// 下一页ajax请求
$("#nextPage").click(
		function() {
			checkfirst();
			// 判断是否为最后一页
			var totalpages;
			$.get("/getTotalpages", function(data) {
				totalpages = data;			
				if (parseInt($.session.get("pageNum")) != parseInt(totalpages)) {
					$.ajax({
						type : "POST",
						url : "/getdbdata",
						data : {
							pageNum : parseInt($.session.get("pageNum"))
									+ parseInt(1)
						},
						dataType : "json",
						success : function(data) {
							append(data)
						}
					});
					$.session.set("pageNum", parseInt($.session.get("pageNum"))
							+ parseInt(1));
				} else {
					alert("当前已是最后一页");
				}
			});
		});

// 检查pageNum是否有值
function checkfirst() {
	if ($.session.get("pageNum") == null || $.session.get("pageNum") == "") {
		$.session.set("pageNum", 1);
	}
}

// 向表格中添加数据
function append(data) {
	$("#tbody").children().remove();
	$.each(data, function(i, record) {
		var obj = "<tr>" + "<td>" + record.id + "</td>" + "<td>" + record.date
				+ "</td>" + "<td>" + record.rainflag + "</td>" + "<td>"
				+ record.iwv + "</td>" + "<td>" + record.elev + "</td>"
				+ "<td>" + record.azi + "</td>" + "</tr>";
		$("#tbody").append(obj);
	});
	$("#tbody").trigger("create");
}

//多条件查询
$("#querybuttonmwr").click(
		function() {
			//清空session防止数据互扰
			$.get("/clearSession",function(data) {});
			$.ajax({
				type : "POST",
				url : "/getdbdata",
				data : $('#queryformmwr').serialize(),
//				dataType : "json",
				success : function(data) {
					$("#tbody").children().remove();
					$.each(data, function(i, record) {
						var obj = "<tr>" + "<td>" + record.id + "</td>" + "<td>" + record.date
								+ "</td>" + "<td>" + record.rainflag + "</td>" + "<td>"
								+ record.iwv + "</td>" + "<td>" + record.elev + "</td>"
								+ "<td>" + record.azi + "</td>" + "</tr>";
						$("#tbody").append(obj);
					});
					$("#tbody").trigger("create");
				}
			});
		});