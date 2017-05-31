	var REGISTER={
		param:{
			//单点登录系统的url
			surl:""
		},
		inputcheck:function(){
				//不能为空检查
				if ($("#username").val() == "") {
					alert("用户名不能为空");
					$("#username").focus();
					return false;
				}
				if ($("#password").val() == "") {
					alert("密码不能为空");
					$("#password").focus();
					return false;
				}
				if ($("#password1").val() == "") {
					alert("确认密码不能为空");
					$("#password1").focus();
					return false;
				}
				if ($("#phone").val() == "") {
					alert("手机号不能为空");
					$("#phone").focus();
					return false;
				}
				if ($("#email").val() == "") {
					alert("email不能为空");
					$("#email").focus();
					return false;
				}
				if ($("#workplace").val() == "") {
					alert("工作单位不能为空");
					$("#workplace").focus();
					return false;
				}
				if ($("#invitation").val() == "") {
					alert("邀请码不能为空");
					$("#invitation").focus();
					return false;
				}
				//密码检查
				if ($("#password").val() != $("#password1").val()) {
					alert("确认密码和密码不一致，请重新输入！");
					$("#password").select();
					$("#password").focus();
					return false;
				}
				return true;
		},
		beforeSubmit:function() {
				//检查用户是否已经被占用
				$.ajax({
	            	url : REGISTER.param.surl + "/user/check/"+escape($("#username").val())+"/1?r=" + Math.random(),
	            	success : function(data) {
	            		if (data.data) {
	            			//检查手机号是否存在
	            			$.ajax({
	            				url : REGISTER.param.surl + "/user/check/"+$("#phone").val()+"/2?r=" + Math.random(),
				            	success : function(data) {
				            		if (data.data) {
					            		REGISTER.doSubmit();
				            		} else {
				            			alert("此手机号已经被注册！");
				            			$("#phone").select();
				            		}
				            	}
	            			});
	            		} else {
	            			alert("此用户名已经被占用，请选择其他用户名");
	            			$("#username").select();
	            		}	
	            	}
				});
	            	
		},
		doSubmit:function() {
			$.post("/user/register",$("#personRegForm").serialize(), function(data){
				if(data.status == 200){
					alert('用户注册成功，请登录！');
					REGISTER.login();
				} else {
					alert("注册失败！");
				}
			});
		},
		login:function() {
			 location.href = "/page/login";
			 return false;
		},
		reg:function() {
			if (this.inputcheck()) {
				this.beforeSubmit();
			}
		}
	};