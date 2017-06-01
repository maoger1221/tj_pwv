<!--WIZARD STYLES-->
<link href="/css/wizard/normalize.css" rel="stylesheet" />
<link href="/css/wizard/wizardMain.css" rel="stylesheet" />
<link href="/css/wizard/jquery.steps.css" rel="stylesheet" />
<%@ include file="head&left.jsp"%>
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
    <%@include file="footer.jsp"%>
	<script src="/js/wizard/modernizr-2.6.2.min.js"></script>
	<script src="/js/wizard/jquery.cookie-1.3.1.js"></script>
	<script src="/js/wizard/jquery.steps.js"></script>

</body>
</html>