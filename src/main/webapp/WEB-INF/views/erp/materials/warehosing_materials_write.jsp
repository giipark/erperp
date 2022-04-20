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
								<li><span>입고신청</span></li>
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
										<h2 class="panel-title">입고신청 </h2>
									</header>

				<div class="panel-body">
								
							<div class="table-responsive">
									<table class="table" id="applyPlus">
										<tbody>
											<tr>
												<th>자재 코드</th>
												<th>자재 명</th>
												<th>신청 수량</th>
												<th></th>									
											</tr>																					
									  <tr>
										<td>
				<input type="text" name="mcode" id="mcode" 	autocomplete="off" list="mcode-list"  onblur="ttWMAmcodeAutocompleteTextClick(this)"
				onchange="ttWMAmcodeAutocompleteTextClick(this)" data-name-id="warehosingMaterialName"   data-quantity-id="applicationQuantity"
				class="form-control" title="자재 코드 " placeholder="자재 코드" onkeydown="getByWMAmcodeAutoCompleate(this)" >
				<datalist id="mcode-list">					
				</datalist>				
										</td>									  
										<td>
				<input type="text" name="warehosingMaterialName" id="warehosingMaterialName"  list="warehosingMaterialName-list"
				autocomplete="off" class="form-control" title="자재 명 " placeholder="자재 명 "  >
				<datalist id="warehosingMaterialName-list">
					<option value="1">
				</datalist>
										</td>

										<td>
				<input type="text" name="applicationQuantity" id="applicationQuantity" class="form-control" 
				data-toggle="tooltip" data-placement="top" min="0" onkeyup="maxCheck(this)"
				 numberonly='true' placeholder="신청 수량" >
										</td>
										 <td  class="text-center">
												<button class="btn btn-primary" onclick="wMaterialApplyPlus()">+</button>
										 </td>	
										</tr>
										</tbody>																												
									</table>
								</div>																																					
							</div>
								</section>											
							</div>
						
						
							<div class="col-lg-12">
								<section class="panel">

			<form:form method="post" action="write.do" id="warehosingMaterialApplyFRM">
				<div class="panel-body">
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar my-custom-scrollbar5">
									<table class="table table-bordered table-striped table-condensed mb-none">
										<thead>
											<tr>
												<th width="5%" class="text-center">No</th>
												<th width="30%" class="text-center">자재 코드</th>
												<th width="30%" class="text-center">자재 명</th>
												<th width="15%" class="text-center">신청 수량</th>	
												<th width="20%" class="text-center">비고</th>											
											</tr>
										</thead>
										<tbody id="tbody-wMaterialApplyFRM">
											<tr class="blank">
												<td colspan="5" class="text-center">	
													 데이터를 입력해 주세요.
												</td>
											</tr>
										</tbody>
										<tfoot >
										<tr>
												<td colspan="5" class="text-right">											
					<!-- 											
					<button class="btn btn-primary" type="button" onclick="warehosingMaterialApplySave()" >저장</button> 
					-->	
												</td>
											</tr> 
										</tfoot>
									</table>
								</div>
							</div>
						</form:form>				
							 <footer class="panel-footer text-right" >
										<button class="btn btn-primary" type="button" onclick="warehosingMaterialApplySave()" >저장</button>
								</footer>
								</section>																	
							</div>

											
						<div class="col-md-12">
								<section class="panel">
									<div class="panel-body">
								<h5>입고 신청:  <span id="wmaSearchTotalCount"></span>건
								<div class="text-right">
								<span><i class="fa fa-stop" style="color:#4a4a4a"></i>입고처리 대기</span>  
								<span><i class="fa fa-stop" style="color:#47a447"></i>입고처리 완료</span> 
								<span><i class="fa fa-stop"  style="color:#ed9c28"></i>적정 재고수량 초과신청</span>
								<span><i class="fa fa-stop" style="color:#d2322d"></i>불출 부족분 신청</span>
								<span><i class="fa fa-square-o" style="color:#ffb61c;"></i>중복 입고신청 자재코드</span>
								</div>
								</h5>
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
								
									<div id="Progress_Loading"><!-- 로딩바 -->
			<img src="${HOME}/resources/assets/images/svg-loaders/Spinner-1s-200px.svg" width="100" height="100"/>
									</div>
									
								<table class="table table-bordered table-hover table-striped table-condensed mb-none" id="wmaDataTable">
										<thead>
											<tr>
												<th width="5%" class="text-center">No</th>
												<th width="20%" class="text-center">자재코드</th>
												<th width="20%" class="text-center">자재명</th>												
												<th width="10%" class="text-center">적정재고수량</th>	
												<th width="10%" class="text-center">현재재고수량</th>	
												<th width="10%" class="text-center">신청수량</th>										
												<th width="12%" class="text-center">비고</th>
												<th width="13%" class="text-center"></th>
											</tr>
										</thead>
										<tbody id="wma_search_tbody">

										</tbody>
										<tfoot>
											<tr>
												<td colspan="8" id="wma_search_pagining" class="text-center">
													
												</td>
											</tr>
										</tfoot>
								</table>
							</div>									
									</div>
								<footer class="panel-footer text-right" >
									<button class="btn btn-success" type="button" onclick="wmaSuccessDelete()">입고처리 완료건만 삭제</button>
									<button class="btn btn-danger" type="button" onclick="wmaAllDelete()">입고신청건 전체삭제</button>
								</footer>
								</section>							
							</div>
				
				
							
							
						</div><!-- start: page -->
					
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
		

	<%@ include file="../../include/footer.jsp"  %>

<script id="wmaTemplate" type="text/x-handlebars-template">
{{#each .}} 	
<tr id="tr-{{applicationCode}}"  >
 <td class="text-center process-status-{{processStatus}}">{{no}}</td>
 
<td class="text-center td-{{applicationCode}}" >
<span id="port-span-{{applicationCode}}" class="port span-{{applicationCode}} text-process-status-{{processStatus}}" >{{mcode}}</span>

<input type="text" id="mcode-{{applicationCode}}" name="mcode" value="{{mcode}}" autocomplete="off" list="list-{{applicationCode}}" 
onblur="ttWMAmcodeAutocompleteTextClick(this)"  data-applicationCode='{{applicationCode}}'
onchange="ttWMAmcodeAutocompleteTextClick(this)" data-name-id="warehosingMaterialName-{{applicationCode}}"  style="display:none"
 data-quantity-id="applicationQuantity-{{applicationCode}}" class="form-control" onkeydown="getByWMAmcodeAutoCompleate(this)">

<datalist id="list-{{applicationCode}}">
</datalist>

</td>

 <td class="text-center td-{{applicationCode}}" onclick="moveWmaProcess('{{applicationCode}}' , '{{processStatus}}')">
<span class="span-{{applicationCode}} text-process-status-{{processStatus}}">{{warehosingMaterialName}}</span>
<input type="text" id="warehosingMaterialName-{{applicationCode}}" name="warehosingMaterialName" value="{{warehosingMaterialName}}"
 class="form-control" style="display:none">
</td>

<td class="text-right text-process-status-{{processStatus}}" id="properInventory-{{applicationCode}}" onclick="moveWmaProcess('{{applicationCode}}' , '{{processStatus}}')" >{{properInventoryFormat}}</td>
<td class="text-right text-process-status-{{processStatus}}" id="currentInventory-{{applicationCode}}" onclick="moveWmaProcess('{{applicationCode}}' , '{{processStatus}}')">{{currentInventoryFormat}}</td>

 <td class="text-right td-{{applicationCode}}" onclick="moveWmaProcess('{{applicationCode}}' , '{{processStatus}}')">
	<span class="span-{{applicationCode}} text-process-status-{{processStatus}}" >{{applicationQuantityFormat}}</span>

 <input type="text" id="applicationQuantity-{{applicationCode}}"
  name="applicationQuantity" value="{{applicationQuantity}}" class="form-control" numberonly="true" min="0" onkeyup="maxCheck(this)"
	   style="display:none" >
 </td>


 <td class="text-center td-{{applicationCode}}" onclick="moveWmaProcess('{{applicationCode}}' , '{{processStatus}}')">
<span class="span-{{applicationCode}} text-process-status-{{processStatus}}" >{{remarks}}</span>
 <input type="text" id="remarks-{{applicationCode}}" name="remarks" value="{{remarks}}" class="form-control"  style="display:none">
 </td>


<td class="text-center">
	<button class="btn btn-success btn-custom3" onclick="wmaChangeUpdate({{applicationCode}})" id="wmaChangeUpdate-{{applicationCode}}">수정</button>
	<button class="btn btn-warning btn-custom3" onclick="wmaUpdate({{applicationCode}})" id="wmaUpdate-{{applicationCode}}" style="display:none" >수정</button>
	<button class="btn btn-danger btn-custom3" onclick="wmaDelete({{applicationCode}})" >삭제</button>
</td>

</tr>
	
{{/each}}	
</script>

<script>
$(function(){	
	wmaListAjaxSearchList(1);	
});
</script>


	</body>
</html>