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
								<li><span>입고처리</span></li>
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
										<h2 class="panel-title">입고처리</h2>
									</header>

							
								<div class="panel-body">
										<div class="form-group">
											<div class="col-sm-5 ">
											<label class="col-sm-3 control-label text-left label-custom-process">자재 코드:</label>
											<div class="col-sm-9 text-left">
												<select class="form-control" onchange="ttWmaAutoInfo(this)" id="mcode" >
													<option value=""></option>
												 	<c:forEach items="${wmaAllList}" var="row">
												 		<c:if test="${row.processStatus ne  '1'.charAt(0)}">
<option value="${row.applicationCode }" ${row.applicationCode eq wmaVO.applicationCode ? 'selected	' :''}  id="${row.applicationCode }" >${row.mcode}</option>
												 		</c:if>
												 	</c:forEach>
												</select>
											</div>
											</div>	
											
											
											<div class="col-sm-5">
												<label class="col-sm-3 control-label text-left label-custom-process">자재 명:&nbsp;&nbsp;</label>
												<div class="col-sm-9">
													<input type="text" name="warehosingMaterialName" id="warehosingMaterialName" 
													 value="${wmaVO.warehosingMaterialName }" class="form-control" readonly>				
												</div>
											</div>
																										
										</div>		
										
									<div class="form-group">
											<div class="col-sm-5">
												<label class="col-sm-3 control-label text-left label-custom-process">입고수량:</label>
												<div class="col-sm-9">
													<input type="text" name="applicationQuantity" readonly value="${wmaVO.applicationQuantity }" id="applicationQuantity" class="form-control">
												</div>
											</div>
											
											<div class="col-sm-5 ">
											<label class="col-sm-3 control-label text-left label-custom-process">비고:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
											<div class="col-sm-9 text-left">
												<input type="text" name="remarks" id="remarks" readonly value="${wmaVO.remarks }" class="form-control">
											</div>
											</div>	
											<div class="col-sm-2 ">
											
											<input type="hidden" id="processStatus" value="${wmaVO.processStatus }">
											<input type="hidden" id="properInventory" value="${wmaVO.properInventory }">
											<input type="hidden" id="currentInventory" value="${wmaVO.currentInventory }">
											<input type="hidden" id="applicationCode" value="${wmaVO.applicationCode }">
											<input type="hidden" id="maid" value="${wmaVO.maid }">
											<button class="btn btn-info" type="button" onclick="wmaProcessAdd()">+</button>
											</div>										
										</div>																																	
								</div>
							
								</section>											
							</div>
	
	
			
							<div class="col-lg-12">
								<section class="panel">

				<form:form method="post" action="" id="wrmProcesssfrm">
				<div class="panel-body">
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar my-custom-scrollbar5">
									<table class="table table-bordered table-striped table-condensed mb-none">
										<thead>
											<tr>
												<th width="5%" class="text-center">No</th>
												<th width="30%" class="text-center">자재 코드</th>
												<th width="30%" class="text-center">자재 명</th>
												<th width="15%" class="text-center">입고 수량</th>	
												<th width="20%" class="text-center">비고</th>											
											</tr>
										</thead>
										<tbody id="tbody-wmaProcess">
											<tr class="blank">
												<td colspan="5" class="text-center">	
													 데이터를 입력해 주세요.
												</td>
											</tr>
										</tbody>
										<tfoot >
											<tr>
												<td colspan="5" class="text-right">																
												</td>
											</tr> 
										</tfoot>
									</table>
								</div>
							</div>
						</form:form>				
							 <footer class="panel-footer text-right" >
										<button class="btn btn-primary" type="button" onclick="wmaProcessSave()" >저장</button>
								</footer>
								</section>																	
							</div>

							
						</div><!-- start: page -->
					
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
		
		
		<datalist>
			<option onclick="wmaMcodedatalistSelect()" ondrag="wmaMcodedatalistSelect()" onfocus="wmaMcodedatalistSelect()"></option>
		</datalist>
		
		
	<%@ include file="../../include/footer.jsp"  %>





	</body>
</html>