<%@ include file="head&left.jsp"%>
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
                                       		
                                        
                                        
											<p class="help-block">同时选中多个数据源，则将选中数据源绘制于同一副图上，其中<span style="color:red;">水汽辐射计数据为红色折线</span>，<span style="color:blue;">PWV数据为蓝色折线</span></p>
										</div>
										<div class="col-md-12 ">
											<button type="button" id="drawingbutton" class="btn btn-warning">绘图</button>
										</div>
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