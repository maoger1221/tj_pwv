<%@ include file="head&left.jsp"%>
		<!-- /. NAV SIDE  -->

		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h1 class="page-head-line">数据分析</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-info">
							<div class="panel-heading">请设置查询条件(若参数值为空，默认绘制最近480条数据)</div>
							<div class="panel-body">
								<form role="form" id="drawingform">
									<div class="form-group">
										<div class="">
										<div class="col-md-6">
											<label>日期</label> <input class="form-control" type="text"
												name="date">
											<p class="help-block">格式：年月日时分秒，如2015-02-03 00:00:00~2015-02-12 23:30:00</p>
										</div>
										<div class="col-md-6">
											<label>绘图数据</label>
											<div class="checkbox">
												<label>
													<input type="checkbox" id="beforebox" name="drawingbox" checked="checked" value="before">原始PWV
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<label>
													<input type="checkbox" id="afterbox" name="drawingbox" value="after">分析后PWV
												</label>
											</div>
											<p class="help-block">同时选中多个数据源，则将选中数据源绘制于同一副图上，其中<span style="color:red;">原始PWV数据为红色折线</span>，<span style="color:blue;">分析后PWV数据为蓝色折线</span></p>
										</div>
										</div>

										<div class="col-md-6">
											<label>选择数据分析方法</label>
											<select class="form-control" name="selectbox">
												<option value="1">滤波/奇异谱分析</option>
												<option value="2">趋势项判定/奇异谱分析</option>
												<option value="3">周期提取/奇异谱分析</option>
												<option value="4">预测/ARIMA</option>
											</select>
											<p class="help-block"></p>
										</div>
										<div class="col-md-1"></div>
										<div class="panel panel-back col-md-3" >
											<span id="progressbar1" class="label">
												准备处理数据。。。
											</span>

												<div class="progress">
													<span id="progressbar2" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
													</span>
												</div>

										</div>

									</div>
										<div class="col-md-12 ">
											<button type="button" id="analysisbutton" class="btn btn-warning">绘图</button>
										</div>
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
	<%@include file="footer.jsp"%>
	<script src="/js/getdata.js"></script>
	<script src="/js/jquerySession.js"></script>
	<script src="/js/dbdrawing.js"></script>

</body>
</html>