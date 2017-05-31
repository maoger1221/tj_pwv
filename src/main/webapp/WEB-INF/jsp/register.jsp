<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>欢迎注册</title>

<!-- BOOTSTRAP STYLES-->
<link href="/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="/css/font-awesome.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<script type="text/javascript" src="js/register.js"></script>
</head>
<body style="background-color: #E2E2E2;">
	<div class="container">
		<div class="row text-center " style="padding-top: 100px;">
			<div class="col-md-12">
				<img src="/img/tj_logo.jpg" />
			</div>
		</div>
		
		
		<div class="row ">

			<div
				class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

				<div class="panel-body">
					<form role="form" action="${pageContext.request.contextPath}/user/register" method="post">
						<hr />
						<div class="alert alert-success">欢迎注册同济大学GPS水汽反演服务平台！</div>
						<br />
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-tag"></i></span>
							<input type="text" name="username" class="form-control"
								placeholder="用户名" />
						</div>
						<c:if test="${!empty unmsg }">
						<span style="color:red;font-weight: bold;font-size: small;">${unmsg }</span><br/>
						</c:if>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" name="password" class="form-control"
								placeholder="密码" />
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" name="password1" class="form-control"
								placeholder="确认密码" />
						</div>
						<c:if test="${!empty psmsg }">
						<span style="color:red;font-weight: bold;font-size: small;">${psmsg }</span><br/>
						</c:if>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-tag"></i></span>
							<input type="text" name="phone" class="form-control"
								placeholder="手机号" />
						</div>
						<c:if test="${!empty phmsg }">
						<span style="color:red;font-weight: bold;font-size: small;">${phmsg }</span><br/>
						</c:if>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-tag"></i></span>
							<input type="text" name="email" class="form-control"
								placeholder="邮箱" />
						</div>
						<c:if test="${!empty emmsg }">
						<span style="color:red;font-weight: bold;font-size: small;">${emmsg }</span><br/>
						</c:if>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-tag"></i></span>
							<input type="text" name="workplace" class="form-control"
								placeholder="工作单位" />
						</div>
						<c:if test="${!empty wpmsg }">
						<span style="color:red;font-weight: bold;font-size: small;">${wpmsg }</span><br/>
						</c:if>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-tag"></i></span>
							<input type="text" name="invitation" class="form-control"
								placeholder="邀请码" />
						</div>
						<c:if test="${!empty ivmsg }">
						<span style="color:red;font-weight: bold;font-size: small;">${ivmsg }</span><br/>
						</c:if>
						<input type="submit" class="btn btn-primary " value="注册" />
						<a href="${pageContext.request.contextPath}/login"
							class="btn btn-warning">已注册，去登录</a>
						<hr />
					</form>
				</div>

			</div>


		</div>
	</div>

</body>
</html>
