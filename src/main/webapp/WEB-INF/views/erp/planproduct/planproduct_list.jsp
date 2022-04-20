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
						<h2>생산</h2>					
						<div class="right-wrapper pull-right">
							<ol class="breadcrumbs">
								<li>
									<a href="${HOME}/">
										<i class="fa fa-home"></i>
									</a>
								</li>
								<li><span>생산</span></li>
								<li><span><i class="fa fa-chevron-right"></i> </span></li>
								<li><span>계획 조회, 수정 , 삭제</span></li>
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
										<h2 class="panel-title">계획 조회, 수정 , 삭제 </h2>
									</header>

				<div class="panel-body">
								<div class="table-responsive">
								
				<form:form id="planSearchForm" action="planSearche.do"  method="post" >
					<div class="form-group">
							<label class="col-sm-2 control-label text-center label-custom">제품명</label>
							<div class="col-sm-4">
								<input type="text" name="planProductName" id="planProductName" class="form-control" title="제품명 " placeholder="제품명 " >
							</div>							
						
							<label class="col-sm-2 control-label text-center label-custom">제품 코드</label>
							<div class="col-sm-4 text-center">
								<input type="text" name="pcode" id="pcode" class="form-control" title="제품 코드" placeholder="제품 코드"  >
							</div>
					</div>		
	
					<div class="form-group">									
							
							<label class="col-sm-2 control-label text-center label-custom">제품날짜</label>
							<div class="col-sm-4">							
								<div class="input-daterange input-group" data-plugin-datepicker="">
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span>
										<input type="text" class="form-control" name="startDate" id="startDate" autocomplete="off">
										<span class="input-group-addon">to</span>
										<input type="text" class="form-control" name="endDate" id="endDate" autocomplete="off">													
								</div>
							</div>
														
							<div class="col-sm-6 text-center">							
							<button type="button" class="btn btn-primary btn-custom" onclick="planSearch()">조회</button>
							<button type="button" class="btn btn-default btn-custom" onclick="planSearchReset()">검색초기화</button>
							<button type="button" class="btn btn-info btn-custom" onclick="planRegister()">등록</button>
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
								<h5>조회 결과 : <span id="planSearchTotalCount"></span>건</h5>
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
								
									<div id = "Progress_Loading"><!-- 로딩바 -->
			<img src="${HOME}/resources/assets/images/svg-loaders/Spinner-1s-200px.svg" width="100" height="100"/>
									</div>
									
								<table class="table table-bordered table-striped table-condensed mb-none" style="border-collapse:collapse;">
										<thead>
											<tr>
												<th width="5%" class="text-center">No<input type="hidden" id="" value="1"></th>
												<th width="24%" class="text-center">제품코드</th>
												<th width="24%" class="text-center">제품명</th>
												<th width="8%" class="text-center">생산수</th>
												<th width="10%" class="text-center">기한</th>
												<th width="19%" class="text-center">비고</th>
												<th width="10%" class="text-center"></th>
											</tr>
										</thead>
										<tbody id="plan_search_tbody">
										</tbody>
										<tfoot>
											<tr>
												<td colspan="7" id="plan_search_pagining" class="text-center">
													
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

	
	<%@ include file="../../include/footer.jsp"  %>

<script id="planTemplate" type="text/x-handlebars-template">
{{#each .}} 	
	<tr class="text-center" id="tr-{{planId}}">

			<td onclick="planToggleMaterialList('{{planId}}' , '{{bomid}}' , '{{planProductQuantity}}')"
data-toggle="collapse" data-target="#plan-toggle-{{planId}}" data-id={{planId}} class="accordion-toggle">{{no}}</td>

			<td onclick="planToggleMaterialList('{{planId}}' , '{{bomid}}' , '{{planProductQuantity}}')"
data-toggle="collapse" data-target="#plan-toggle-{{planId}}" data-id={{planId}} class="accordion-toggle">{{pcode}}</td>





			<td onclick="planToggleMaterialList('{{planId}}' , '{{bomid}}' , '{{planProductQuantity}}')"
data-toggle="collapse" data-target="#plan-toggle-{{planId}}" data-id={{planId}} class="accordion-toggle class-{{planId}}">
<span class="span-{{planId}}" >{{planProductName}}</span>

<input type="text" id="planProductName-{{planId}}" name="planProductName" value="{{planProductName}}" class="form-control"  style="display:none">
</td>


			<td onclick="planToggleMaterialList('{{planId}}' , '{{bomid}}' , '{{planProductQuantity}}')"
data-toggle="collapse" data-target="#plan-toggle-{{planId}}" data-id={{planId}} class="accordion-toggle class-{{planId}}"  >
<span class="span-{{planId}}" >{{planProductQuantity}}</span>

<input type="text" id="planProductQuantity-{{planId}}" 
numberonly="true"
name="planProductQuantity" value="{{planProductQuantity}}" class="form-control"  style="display:none">
</td>



			<td onclick="planToggleMaterialList('{{planId}}' , '{{bomid}}' , '{{planProductQuantity}}')"
data-toggle="collapse" data-target="#plan-toggle-{{planId}}" data-id={{planId}} class="accordion-toggle class-{{planId}}">
<span class="span-{{planId}}" >{{deadline}}</span>

<input type="text" id="deadline-{{planId}}" name="deadline" value="{{deadline}}" class="form-control"  
onclick="palnDataPicker(this)" onmouseover="palnDataPicker(this)" readonly style="display:none">
</td>


			<td onclick="planToggleMaterialList('{{planId}}' , '{{bomid}}' , '{{planProductQuantity}}')"
data-toggle="collapse" data-target="#plan-toggle-{{planId}}" data-id={{planId}} class="accordion-toggle class-{{planId}}">
<span class="span-{{planId}}" >{{remarks}}</span>

<input type="text" id="remarks-{{planId}}" name="remarks" value="{{remarks}}" class="form-control"  style="display:none">
</td>




			<td >
<button class="btn btn-success btn-custom3" onclick="planChangeUpdate({{planId}})" id="planChangeUpdate-{{planId}}">수정</button>
<button class="btn btn-warning btn-custom3" onclick="planUpdate('{{planId}}','{{bomid}}')" id="planUpdate-{{planId}}" style="display:none" >수정</button>

			<button class="btn btn-danger btn-custom3" onclick="planDelete({{planId}})">삭제</button>
			</td>
		</tr>



<tr>
    <td colspan="7" class="hiddenRow">
	<div class="accordian-body collapse" id="plan-toggle-{{planId}}"> 
				
								<div class="table-responsive">
											<table class="table table-condensed mb-none">
												<thead>
													<tr>
														<th class="text-center">#</th>
														<th class="text-center">자재 명</th>
														<th class="text-center">자재 코드</th>
														<th class="text-center">자재 수량</th>
														<th class="text-center">신청 수량</th>
													</tr>
												</thead>
												<tbody id="plan-toggle-data-{{planId}}">

													
												</tbody>
											</table>
							</div>
	</div>
	</td>
</tr>
	
{{/each}}	
</script>


<script id="template-planToggleMaterialList" type="text/x-handlebars-template">
{{#each .}}
		<tr>
			<td class="text-center">{{no}}</td>
			<td class="text-center">{{mcode}}</td>
			<td class="text-center">{{materialName}}</td>
			<td class="text-center">{{materialQuantityFormat}}</td>
			<td class="text-center">{{materialQuantityFormat}} × {{planProductQuantity}} = {{sumQuantityFormat}}</td>
		</tr>
{{/each}}
</script>



<script>
$(function(){	
	planListAjaxSearchList(1);		
	var msg='${msg}';
	if(msg=="success"){
		tSuccess("등록 되었습니다.");
	}
});
</script>




	</body>
</html>