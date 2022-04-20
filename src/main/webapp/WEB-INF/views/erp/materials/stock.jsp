<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/contants.jspf" %>
<!DOCTYPE html>
<html class="${sidebarCookie}" lang="ko">
<head>
<%@ include file="../../include/head.jsp" %>
</head>
<body>

		<section class="body">	
			<header  class="page-header header">
				<%@ include file="../../include/header.jsp" %>
			</header>
		
			<div class="inner-wrapper">			
				<%@ include file="../../include/side_menu.jsp" %>
				<section role="main" class="content-body">
				<header class="page-header">
						<h2>자재</h2>					
						<div class="right-wrapper pull-right">
							<ol class="breadcrumbs">
								<li>
									<a href="${HOME}/">
										<i class="fa fa-home"></i>
									</a>
								</li>
								<li><span>자재</span></li>
								<li><span><i class="fa fa-chevron-right"></i> </span></li>
								<li><span>재고관리</span></li>
							</ol>												
						</div>
					</header>
								
			<!-- start: page -->
						<div class="row">
							<div class="col-lg-12">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">											
										</div>						
										<h2 class="panel-title">재고관리 </h2>
									</header>

				<div class="panel-body">
								<div class="table-responsive">
								
				<form:form id="stockSearchForm" action="stockSearche.do"  method="post" >
					<div class="form-group">
							<label class="col-sm-1 control-label text-right label-custom">자재 명</label>
							<div class="col-sm-3">
								<input type="text" name="materialName" id="materialName" class="form-control" title="자재 명" placeholder="자재 명" >
							</div>
							<label class="col-sm-2 control-label text-right label-custom">자재 코드</label>
							<div class="col-sm-3 text-center">
								<input type="text" name="mcode" id="mcode" class="form-control" title="자재 코드" placeholder="자재 코드"  >
							</div>
							<div class="col-sm-3 text-center">							
							<button type="button" class="btn btn-primary btn-custom" onclick="stockSearch()">조회</button>
							</div>
					</div>																													
				</form:form>
									
								</div>									
							</div>
								</section>											
							</div>							
							
						<div class="col-md-12">
								<section class="panel">
									<div class="panel-body">
								<h5>조회 결과 : <span id="stockTotalCount"></span>건</h5>
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
								
									<div id="Progress_Loading"><!-- 로딩바 -->
<img src="${HOME}/resources/assets/images/svg-loaders/Spinner-1s-200px.svg" width="100" height="100"/>
									</div>
									
								<table class="table table-bordered table-striped table-condensed mb-none">
										<thead>
											<tr>
												<th width="5%" class="text-center">No</th>
												<th width="25%" class="text-center">자재 코드</th>
												<th width="25%" class="text-center">자재 명</th>
												<th width="15%" class="text-center">적정 재고수량</th>
												<th width="15%" class="text-center">현재고</th>
												<th width="15%" class="text-center"></th>
											</tr>
										</thead>
										<tbody id="stock_search_tbody">

										</tbody>
										<tfoot>
											<tr>
												<td colspan="6" id="stock_pagining" class="text-center">
													
												</td>
											</tr>
										</tfoot>
								</table>
							</div>									
									</div>
								<footer class="panel-footer text-right">
									&nbsp;
								</footer>
								</section>							
							</div>
							
						</div>
					
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
	
	<%@ include file="../../include/footer.jsp"  %>



<script id="stockTemplate" type="text/x-handlebars-stockTemplate">
{{#each .}} 	
<tr>
 <td class="text-center">{{no}}</td>
 <td class="text-center"><input type="text"  id="mcode-{{maid}}"   value="{{mcode}}"  class='material-input2' readonly>
 </td>
 <td  class="text-center"><input type="text" id="materialName-{{maid}}"  value="{{materialName}}"  class='material-input2' readonly></td>
 <td  class="text-right"><input type="text" id="properInventory-{{maid}}"  value="{{properInventoryFormat}}"  class='material-input2' readonly   numberonly='true'></td>
 <td  class="text-right"><input type="text" id="currentInventory-{{maid}}"  value="{{currentInventoryFormat}}"  class='material-input2 input-{{maid}}' readonly   numberonly='true'></td>

 <td  class="text-center">
	<button  id="ch-{{maid}}" onclick="stockChangeButton('{{maid}}')" class="btn btn-success btn-custom3">수정</button>
	<button  id="up-{{maid}}" onclick="stockUpdate('{{mcode}}', '{{maid}}')" class="btn btn-warning btn-custom3" style="display:none">수정</button>	
 </td>
</tr>
{{/each}}	
</script>


<script>
$(function(){	
	stockAjaxSearchList(1);			
});
</script>

		
	</body>
</html>