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
								<li><span>자재등록</span></li>
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
										<h2 class="panel-title">자재등록 </h2>
									</header>
									
									

				<div class="panel-body">
								<div class="table-responsive">
								
					<form:form id="materialRegistrationSaveForm" action="write.do"  method="post"  >					
									<table class="table table-bordered table-striped table-condensed mb-none">
										<thead>
											<tr>
												<th width="5%" class="text-center">No<input type="hidden" id="management_write_no" value="1"></th>
												<th width="40%" class="text-center">코드</th>
												<th width="40%" class="text-center">자재 명</th>
												<th width="10%" class="text-center">적정재고수량</th>
												<!-- <th width="10%" class="text-center"></th> -->
											</tr>
										</thead>
										<tbody id="management_write_tbody">
											<tr class="blank">
												<td colspan="4" class="text-center"> 데이터를 입력해 주세요.</td>																		
											</tr>
										</tbody>
									</table>									
						</form:form>
					
								</div>									
							</div>
								</section>											
							</div>
							
							
						<div class="col-md-12">
								<section class="panel">
									<div class="panel-body">
										<div class="form-group">
											<label class="col-sm-2 control-label">자재 코드:</label>
											<div class="col-sm-10">
												<input type="text" id="input_mcode" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">자재명:</label>
											<div class="col-sm-10">
												<input type="text" id="input_materialName" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">적정 재고 수량:</label>
											<div class="col-sm-10">
												<input type="text" id="input_properInventory" class="form-control" numberonly="true">
											</div>
										</div>
									</div>
									<footer class="panel-footer text-right">
										<button class="btn btn-info" id="materialNewRegistration">신규 등록</button>
										<button type="submit" class="btn btn-primary" id="materialRegistrationSave">저장</button>
									</footer>
								</section>							
							</div>
							
						</div>
					
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
	
	<%@ include file="../../include/footer.jsp"  %>


	</body>
</html>