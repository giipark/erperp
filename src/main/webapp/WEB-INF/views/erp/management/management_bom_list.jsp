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
						<h2>자재정보관리</h2>					
						<div class="right-wrapper pull-right">
							<ol class="breadcrumbs">
								<li>
									<a href="${HOME}/">
										<i class="fa fa-home"></i>
									</a>
								</li>
								<li><span>자재정보관리</span></li>
								<li><span><i class="fa fa-chevron-right"></i> </span></li>
								<li><span>BOM 조회 ,삭제</span></li>
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
										<h2 class="panel-title">BOM 조회 ,삭제 </h2>
									</header>

				<div class="panel-body">
								<div class="table-responsive">
								
				<form:form id="bomSearchForm" action="bomSearche.do"  method="post" >

					<div class="form-group">
							<label class="col-sm-1 control-label text-right label-custom">제품명 </label>
							<div class="col-sm-3">
								<input type="text" name="productName" id="productName" class="form-control" title="제품명 " placeholder="제품명 " >
							</div>
							<label class="col-sm-2 control-label text-right label-custom">제품 코드</label>
							<div class="col-sm-3 text-center">
								<input type="text" name="pcode" id="pcode" class="form-control" title="제품 코드" placeholder="제품 코드"  >
							</div>
							<div class="col-sm-3 text-center">							
							<button type="button" class="btn btn-primary btn-custom" onclick="bomSearch()">조회</button>
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
								<h5>조회 결과 : <span id="bomSearchTotalCount"></span>건</h5>
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
								
									<div id = "Progress_Loading"><!-- 로딩바 -->
			<img src="${HOME}/resources/assets/images/svg-loaders/Spinner-1s-200px.svg" width="100" height="100"/>
									</div>
									
								<table class="table table-bordered table-striped table-condensed mb-none" style="border-collapse:collapse;">
										<thead>
											<tr>
												<th width="5%" class="text-center">No<input type="hidden" id="" value="1"></th>
												<th width="35%" class="text-center">제품코드</th>
												<th width="35%" class="text-center">제품명</th>
												<th width="10%" class="text-center">제품수량</th>
												<th width="15%" class="text-center"></th>
											</tr>
										</thead>
										<tbody id="bom_search_tbody">

										</tbody>
										<tfoot>
											<tr>
												<td colspan="5" id="bom_search_pagining" class="text-center">
													
												</td>
											</tr>
										</tfoot>
								</table>
							</div>									
									</div>
								<footer class="panel-footer text-right" >
									&nbsp;
								</footer>
								</section>							
							</div>
							
						</div>
			
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
<form action="bomUpdateForm.do" id="bormUpdatFrm" method="post">
	<input type="hidden" name="bomid" id="update_bomid" >
</form>
<input type="hidden" id="msg" value="${msg}">

	
	<%@ include file="../../include/footer.jsp"  %>
	
 
<script id="template" type="text/x-handlebars-template">
{{#each .}} 	
<tr id="tr-{{bomid}}">
 <td class="text-center">{{no}}</td>
 <td class="text-center bom-toggle" onclick="bomToggleMaterialList(this)" 
	data-toggle="collapse" data-target="#bom-toggle-{{bomid}}" data-id={{bomid}} class="accordion-toggle">
{{pcode}}
 </td>

 <td  class="text-center bom-toggle" onclick="bomToggleMaterialList(this)"
data-toggle="collapse" data-target="#bom-toggle-{{bomid}}" data-id={{bomid}} class="accordion-toggle">
{{productName}}
</td>

 <td class="text-center">
{{productQuantity}}
</td>


 <td  class="text-center">
	<button  id="ch-{{bomid}}" onclick="bomUpdateMove('{{bomid}}')" class="btn btn-success btn-custom3">수정</button>
	<button class="btn btn-danger btn-custom3" onclick="bomDelete('{{bomid}}')">삭제</button>
 </td>
</tr>

<tr>
    <td colspan="5" class="hiddenRow">
	<div class="accordian-body collapse" id="bom-toggle-{{bomid}}"> 
				
								<div class="table-responsive">
											<table class="table table-condensed mb-none">
												<thead>
													<tr>
														<th class="text-center">#</th>
														<th class="text-center">자재 명</th>
														<th class="text-center">자재 코드</th>
														<th class="text-center">자재 수량</th>
													</tr>
												</thead>
												<tbody id="bom-toggle-data-{{bomid}}">

													
												</tbody>
											</table>
							</div>
	</div>
	</td>
</tr>

{{/each}}	
</script>

<script id="template-bomToggleMaterialList" type="text/x-handlebars-template">
{{#each .}}
		<tr>
			<td class="text-center">{{no}}</td>
			<td class="text-center">{{mcode}}</td>
			<td class="text-center">{{materialName}}</td>
			<td class="text-center">{{materialQuantity}}</td>
		</tr>
{{/each}}
</script>


<script>
$(function(){	
	bomListAjaxSearchList(1);		
	updateSelectMessage();
	//history.replaceState({}, null, location.pathname);	
});
</script>



	</body>
</html>