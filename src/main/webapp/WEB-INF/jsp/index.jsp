<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>同济大学GPS水汽反演服务平台</title>

<!--WIZARD STYLES-->
<link href="/css/wizard/normalize.css" rel="stylesheet" />
<link href="/css/wizard/wizardMain.css" rel="stylesheet" />
<link href="/css/wizard/jquery.steps.css" rel="stylesheet" />
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
					<li><a class="active-menu"
						href="${pageContext.request.contextPath}/"><i
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
					<li><a href=""><i class="fa fa-yelp "></i>数据库<span
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
						<h1 class="page-head-line">首页</h1>
					</div>
				</div>

				<!-- /. ROW  -->

                <div class="row">
                    <div class="col-md-8">
                        <!-- /. ROW  -->
                        <div class="panel panel-default">

                            <div id="carousel-example" class="carousel slide" data-ride="carousel" style="border: 5px solid #000;">

                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img src="img/1.JPG" alt="" />

                                    </div>
                                    <div class="item">
                                        <img src="img/2.JPG" alt="" />

                                    </div>
                                    <div class="item">
                                        <img src="img/3.JPG" alt="" />

                                    </div>
                                </div>
                                <!--INDICATORS-->
                                <ol class="carousel-indicators">
                                    <li data-target="#carousel-example" data-slide-to="0" class="active"></li>
                                    <li data-target="#carousel-example" data-slide-to="1"></li>
                                    <li data-target="#carousel-example" data-slide-to="2"></li>
                                </ol>
                                <!--PREVIUS-NEXT BUTTONS-->
                                <a class="left carousel-control" href="#carousel-example" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-example" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right"></span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- /.REVIEWS &  SLIDESHOW  -->
                    <div class="col-md-4">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Recent Chat History
                            </div>
                            <div class="panel-body" style="padding: 0px;">
                                <div class="chat-widget-main">


                                    <div class="chat-widget-left">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                    </div>
                                    <div class="chat-widget-name-left">
                                        <h4>Amanna Seiar</h4>
                                    </div>
                                    <div class="chat-widget-right">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                    </div>
                                    <div class="chat-widget-name-right">
                                        <h4>Donim Cruseia </h4>
                                    </div>
                                    <div class="chat-widget-left">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                    </div>
                                    <div class="chat-widget-name-left">
                                        <h4>Amanna Seiar</h4>
                                    </div>
                                    <div class="chat-widget-right">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                    </div>
                                    <div class="chat-widget-name-right">
                                        <h4>Donim Cruseia </h4>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Enter Message" />
                                    <span class="input-group-btn">
                                        <button class="btn btn-success" type="button">SEND</button>
                                    </span>
                                </div>
                            </div>
                        </div>


                    </div>
                    <!--/.Chat Panel End-->
                </div>

				
				
				
				
				<!-- /. ROW  -->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">平台简介</div>
							<div class="panel-body">
								<div id="wizard">
									<h2>First Step</h2>
									<section>
										<p>平台简介1平台简介1平台简介1平台简介1平台简介1平台简介1平台简介1</p>
									</section>

									<h2>Second Step</h2>
									<section>
										<p>平台简介2平台简介2平台简介2平台简介2平台简介2平台简介2平台简介2</p>
									</section>

									<h2>Third Step</h2>
									<section>
										<p>平台简介3平台简介3平台简介3平台简介3平台简介3平台简介3平台简介3</p>
									</section>

									<h2>Forth Step</h2>
									<section>
										<p>平台简介4平台简介4平台简介4平台简介4平台简介4平台简介4平台简介4</p>
									</section>
								</div>

							</div>
						</div>
					</div>
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
	<!-- WIZARD SCRIPTS -->
	<script src="/js/wizard/modernizr-2.6.2.min.js"></script>
	<script src="/js/wizard/jquery.cookie-1.3.1.js"></script>
	<script src="/js/wizard/jquery.steps.js"></script>

</body>
</html>
