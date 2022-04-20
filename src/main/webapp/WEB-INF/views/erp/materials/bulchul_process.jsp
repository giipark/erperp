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
								<li><span>자재 불출처리</span></li>
							</ol>												
						</div>
					</header>
				
				
			<!-- start: page -->
						<div class="row">
				
						
						<div class="col-md-12">
								<section class="panel">
								
									<header class="panel-heading">
										<div class="panel-actions">											
										</div>						
										<h2 class="panel-title">자재 불출처리 </h2>
									</header>
								
									<div class="panel-body">
								<h5>총 : <span id="bulchulListTotalCount"></span>건</h5>
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
								
								<span id="errorMessage">

								</span>
							<form action="" id="bulchulProcessFRM">		
								<table class="table table-bordered table-striped table-condensed mb-none" id="bulchul-table">
										<thead>
											<tr>
												<th width="5%" class="text-center">No</th>
												<th width="10%" class="text-center">신청 날짜</th>															
												<th width="17%" class="text-center">자재코드</th>
												<th width="17%" class="text-center">자재명</th>

												<th width="13%" class="text-center">현 재고</th>
												<th width="11%" class="text-center">신청 수량</th>
												<th width="15%" class="text-center">불출 수량</th>
												<th width="12%" class="text-center">비고</th>
											</tr>
										</thead>
										<tbody id="bulchul_search_tbody">

										</tbody>
										<tfoot>
											<tr>
									<td colspan="8" id="bulchul_search_pagining" class="text-center">
								<div id = "Progress_Loading"><!-- 로딩바 -->
<img src="${HOME}/resources/assets/images/svg-loaders/Spinner-1s-200px.svg" width="100" height="100"/>
									</div>
										</td>
											</tr>
											
										</tfoot>
								</table>
								</form>
								
							</div>									
									</div>
								<footer class="panel-footer text-right">
								<button class="btn btn-primary"  onclick="bulchulProcessSave()" type="button">불출처리</button>
									&nbsp;
								</footer>
								</section>							
							</div>
					
					
					
					
								
						<div class="col-md-12">
						<form action="" id="bulchulPlanSaveFRM">
								<section class="panel">
								
									<header class="panel-heading">
										<div class="panel-actions">											
										</div>						
										<h2 class="panel-title">생산 계획 </h2>
									</header>
								
									<div class="panel-body">
								<h5>총 : <span id="bulchulListTotalCount2"></span>건</h5>
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar my-custom-scrollbar5">
								

									
								<table class="table table-bordered table-striped  table-condensed mb-none" id="bulchul-table-2">
										<thead>
											<tr>
												<th width="5%" class="text-center">No</th>
												<th width="9%" class="text-center">신청 날짜</th>
												<th width="9%" class="text-center">제품코드</th>
												<th width="9%" class="text-center">제품이름</th>
												
												<th width="18%" class="text-center">자재코드</th>
												<th width="18%" class="text-center">자재명</th>
												<th width="10%" class="text-center">현 재고</th>
												<th width="10%" class="text-center">신청 수량</th>
												<th width="15%" class="text-center">비고</th>
											</tr>
										</thead>
										<tbody id="bulchul_search_tbody2" >

										</tbody>
										<tfoot >
											<tr>
												<td colspan="9" id="bulchul_search_pagining2" class="text-center" style="border: none;">
								<div id="Progress_Loading2"><!-- 로딩바 -->
				<img src="${HOME}/resources/assets/images/svg-loaders/Spinner-1s-200px.svg" width="100" height="100"/>
									</div>
												</td>
											</tr>
										</tfoot>
								</table>								
							</div>		
														
	<!-- 							<div id="bulchul_search_more2" class="text-center">								
								</div> --> 
														
									</div>
								<footer class="panel-footer text-right">
								
								<button class="btn btn-primary" onclick="bulchulStatusSave()" type="button">저장</button>
								

								</footer>
								</section>	
								</form>						
							</div>
					
					
					
							
						</div><!-- start: page -->
					
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
		

	<%@ include file="../../include/footer.jsp"  %>
	
	

<script id="template" type="text/x-handlebars-template">
{{#each .}} 	
<tr id="tr-{{bulchulId}}">

 <td class="text-center">{{no}}</td>
 <td class="text-center">{{applicationDateFormat}}</td>

 <td  class="text-center">{{mcode}}</td>
 <td>{{materialName}}</td>


<td  class="text-center" >{{currentInventoryFormat}}</td>
<td  class="text-center" >{{applicationQuantityFormat}}</td>



<td  class="text-center">
<div class="row">
<div class="col-sm-8">
<input type="text" class="form-control" numberOnly="true" name="list[{{counter @index}}].bulchulQuantity" id="bulchulQuantity-{{bulchulId}}" 
onblur="checkBulchulCount(this)" data-id="{{bulchulId}}">

<input type="hidden" name="list[{{counter @index}}].bulchulId"     value="{{bulchulId}}"  >
<input type="hidden" name="list[{{counter @index}}].no"     value="{{no}}"  >


<input type="hidden" id="properInventory-{{bulchulId}}" value="{{properInventory}}"  >
<input type="hidden" id="currentInventory-{{bulchulId}}" value="{{currentInventory}}"  >
<input type="hidden" id="applicationQuantity-{{bulchulId}}" value="{{applicationQuantity}}"  >
</div>

<div class="col-sm-4">
<div class="checkbox-custom checkbox-success">
<input type="checkbox"  name="list[{{counter @index}}].bulchulCheckbox" id="bulchulCheckbox-{{bulchulId}}"  >
<label for="bulchulCheckbox-{{bulchulId}}"></label>
</div>
</div>
</td>



{{#if remarks}}

<td  class="text-center" data-tooltip-text="제품코드:{{pcode}}, 제품 이름 :{{planProductName}}">
<span  class="spanPlan" >{{remarks}}</span>
</td>

   {{^}}

<td  class="text-center" >
<span>{{remarks}}</span>
</td>


{{/if}}






</tr>
{{/each}}	
</script>


<script id="template2" type="text/x-handlebars-template">
{{#each .}} 	
<tr>
 <td class="text-center">{{no}}
<input type="hidden" name="blankConfirm" value="{{no}}">
</td>
 <td class="text-center">{{applicationDateFormat}}</td>


<td  class="text-center">{{pcode}}</td>
<td  class="text-center">{{planProductName}}</td>

 <td  class="text-center">{{mcode}}</td>
 <td>{{materialName}}</td>

<td  class="text-center">{{currentInventoryFormat}}</td>

<td  class="text-center">{{applicationQuantityFormat}}</td>

<td  class="text-center">{{remarks}}</td>

</tr>
{{/each}}	
</script>

<script>
$(function(){
	bulchulDefaultchList(1);
	bulchulDefaultchList2(1);
	
	var param='${param.message}';
	if(param=="success"){
		tSuccess("불출처리 되었습니다."); 	
		history.replaceState({}, null, location.pathname);
	}
	
});
</script>



	</body>
</html>