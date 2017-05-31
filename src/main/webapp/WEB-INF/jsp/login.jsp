<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>欢迎登录</title>

<!-- BOOTSTRAP STYLES-->
<link href="/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="/css/font-awesome.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
	
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
					<form role="form" action="${pageContext.request.contextPath}/user/login" method="post">
						<hr />
						<div class="alert alert-success">欢迎登录同济大学GPS水汽反演服务平台！</div>
						<br />
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-tag"></i></span>
							<input type="text" name="username" class="form-control"
								placeholder="请输入用户名" />
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" name="password" class="form-control"
								placeholder="请输入密码" />
						</div>
						<input type="submit" class="btn btn-primary " value="登录"/>
						<%-- <a href="${pageContext.request.contextPath}/" class="btn btn-primary ">登录</a> --%>
						<a href="${pageContext.request.contextPath}/register"
							class="btn btn-warning">注册</a>
						<hr />
						<c:if test="${!empty msg }">
							<div class="alert alert-danger">${msg }</div>
						</c:if>
					</form>
				</div>

			</div>


		</div>
	</div>

</body>
</html>
