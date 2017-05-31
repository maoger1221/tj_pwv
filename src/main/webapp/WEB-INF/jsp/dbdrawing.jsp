<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>同济大学GPS水汽反演服务平台</title>

<!-- BOOTSTRAP STYLES-->
<link href="/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="/css/font-awesome.css" rel="stylesheet" />
<!--CUSTOM BASIC STYLES-->
<link href="/css/basic.css" rel="stylesheet" />
<!--CUSTOM MAIN STYLES-->
<link href="/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">同济大学<br />GPS水汽反演服务平台
				</a>
			</div>

			<div class="header-right">

				<a href="message-task.html" class="btn btn-info" title="New Message"><b>30
				</b><i class="fa fa-envelope-o fa-2x"></i></a> <a href="message-task.html"
					class="btn btn-primary" title="New Task"><b>40 </b><i
					class="fa fa-bars fa-2x"></i></a> <a href="login.html"
					class="btn btn-danger" title="Logout"><i
					class="fa fa-exclamation-circle fa-2x"></i></a>


			</div>
		</nav>
		<!-- /. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li>
						<div class="user-img-div">
							<img src="/img/user.png" class="img-thumbnail" /> <span
								class="inner-text" style="float: right"> ${user.username }
								<br /> 您好！ <br /> <br /> <a
								href="${pageContext.request.contextPath}/user/quit"><input
									type="button" class="btn btn-sm btn-info" style="float: right"
									value="安全退出" /></a>
							</span>
						</div>

					</li>

					<li><a href="${pageContext.request.contextPath}/"><i
							class="fa fa-dashboard "></i>首页</a></li>
					<li><a href="${pageContext.request.contextPath}/pwvimg"><i
							class="fa fa-dashboard "></i>水汽反演实时图</a></li>
					<li><a href="#"><i class="fa fa-desktop "></i>水汽监测站 <span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="panel-tabs.html"><i class="fa fa-toggle-on"></i>TJCH</a></li>
							<li><a href="notification.html"><i class="fa fa-bell "></i>TJBS</a>
							</li>
							<li><a href="progress.html"><i class="fa fa-circle-o "></i>TJJD</a>
							</li>
						</ul></li>
					<li><a class="active-menu" href="#"><i class="fa fa-yelp "></i>数据库<span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">水汽辐射计<span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="${pageContext.request.contextPath}/dbdata_mwr2d"><i class="fa fa-coffee"></i>水汽辐射计2D</a></li>
									<li><a href="${pageContext.request.contextPath}/dbdata_mwrz"><i class="fa fa-flash "></i>水汽辐射计天顶</a></li>
								</ul></li>
							<li><a href="#">反演PWV<span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="#"><i class="fa fa-plus "></i>TJCH</a></li>
									<li><a href="#"><i class="fa fa-comments-o "></i>TJBS</a></li>
									<li><a href="#"><i class="fa fa-comments-o "></i>TJJD</a></li>
								</ul></li>
							<li><a href="${pageContext.request.contextPath}/dbdrawing"><i class="fa fa-desktop "></i>绘图</a></li>
						</ul></li>

					<li><a href="#"><i class="fa fa-bicycle "></i>数据分析 <span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">

							<li><a href="form.html"><i class="fa fa-desktop "></i>降水预测
							</a></li>
							<li><a href="form-advance.html"><i class="fa fa-code "></i>周期提取</a>
							</li>


						</ul></li>
				</ul>
			</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h1 class="page-head-line">绘图</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-info">
							<div class="panel-heading">请设置查询条件(若参数值为空，默认绘制最近480条数据)</div>
							<div class="panel-body">
								<form role="form" id="drawingform">
									<div class="form-group">
										<div class="col-md-6">
											<label>日期</label> <input class="form-control" type="text"
												name="date">
											<p class="help-block">格式：年月日时分秒，如2015-02-03 00:00:00~2015-02-12 23:30:00</p>
										</div>
										<div class="col-md-6">
											<!-- <label>绘图数据</label> 
											<select class="form-control" name="sdatasource">
                                                <option value="mwrz">水汽辐射计天顶</option>
                                                <option value="pwv">PWV</option>
                                            </select> -->
                                            
                                            
                                            
	                                            <label>绘图数据</label>
	                                            <div class="checkbox">
	                                                <label>
	                                                    <input type="checkbox" id="mwrzbox" name="drawingbox" checked="checked" value="mwrz">水汽辐射计天顶
	                                                </label>
	                                           		&nbsp;&nbsp;&nbsp;&nbsp;  
	                                                <label>
	                                                    <input type="checkbox" id="pwvbox" name="drawingbox" value="pwv">PWV
	                                                </label>
	                                            </div>
                                       		
                                        
                                        
											<p class="help-block">同时选中多个数据源，则将选中的数据源绘制于同一副图上，其中<span style="color:red;">水汽辐射计数据为红色折线</span>，<span style="color:blue;">PWV数据为蓝色折线</span></p>
										</div>
									</div>			
									<button type="button" id="drawingbutton" class="btn btn-warning">绘图</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div align="center">
					<canvas id="myCanvas" style="border: 2px solid #6699cc" hidden="hidden"></canvas>
					<!-- <canvas id="myCanvas" width="1080" height="600" style="border: 2px solid #6699cc"></canvas> -->
					<img id="imgId" src="" class="img-responsive "/>
				</div>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->

	<!-- /. FOOTER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="/js/bootstrap.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script src="/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="/js/custom.js"></script>
	
	<script src="/js/getdata.js"></script>
	<script src="/js/jquerySession.js"></script>
	<script src="/js/dbdrawing.js"></script>
</body>
</html>
