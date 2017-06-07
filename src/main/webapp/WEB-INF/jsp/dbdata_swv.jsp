<%@ include file="head&left.jsp"%>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h1 class="page-head-line">SWV数据查询</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-info">
							<div class="panel-heading">请设置查询条件(若参数值为空，默认查询所有)</div>
							<div class="panel-body">
								<form role="form" id="queryformmwr">
									<div class="form-group">
										<div class="col-md-6">
											<label>日期</label> <input class="form-control" type="text"
												name="date">
											<p class="help-block">格式：年月日时分秒，如2017-06-03 00:00:00~2017-06-05 23:30:00</p>
										</div>
										<div class="col-md-6">
											<label>斜路径水汽含量(swv)</label> <input class="form-control"
												type="text" name="swv">
											<p class="help-block">如：10.00~30.00</p>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-6">
											<label>高度角</label> <input class="form-control" type="text"
												name="elev" id="eee">
											<p class="help-block">如：10~90</p>
										</div>
										<div class="col-md-6">
											<label>方位角</label> <input class="form-control" type="text"
												name="azi">
											<p class="help-block">如：120~240</p>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-6">
											<label>PRN号</label> <input class="form-control" type="text"
																	  name="prn">
											<p class="help-block">如：1~32</p>
										</div>
									</div>
									<input type="hidden" name="type" value="swv"/>
									<div class="col-md-12">
									<button type="button" id="querybuttonmwr"
										class="btn btn-warning">查询</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- 数据表 -->
				<div class="panel panel-default">
					<div class="panel-heading">查询结果</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table id="ttable" class="table table-hover">
								<thead>
									<tr>
										<th>id</th>
										<th>date</th>
										<th>prn</th>
										<th>swv</th>
										<th>elev</th>
										<th>azi</th>
									</tr>
								</thead>
								<tbody id="tbody">
									<!-- 通过ajax获得数据 -->

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- 表格结束  -->
				<ul class="pager">
					<li><button type="button" id="prePage"
							class="btn btn-sm btn-primary">上一页</button></li>
					<li><button type="button" id="nextPage"
							class="btn btn-sm btn-primary">下一页</button></li>
				</ul>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
	<%@include file="footer.jsp"%>
	<script src="/js/getdata.js"></script>
	<script src="/js/jquerySession.js"></script>

</body>
</html>