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
								<li><span>계획 등록</span></li>
							</ol>												
						</div>
					</header>
				
				
			<!-- start: page -->
						<div class="row">
							<div class="col-lg-12">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">											
											<button class="btn btn-primary" onclick="planProductAdd()">+</button>
										</div>

										<h2 class="panel-title">계획 등록</h2>										
									</header>

			<form:form method="post" action="write.do" id="planProductWriteFRM">
				<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-striped table-condensed mb-none">
										<thead>
											<tr>
												<th width="5%">No</th>
												<th width="25%">제품코드</th>
												<th width="25%" class="text-right">제품명</th>
												<th width="10%" class="text-right">개수</th>
												<th width="15%" class="text-right">날짜</th>
												<th width="20%" class="text-right">비고</th>												
											</tr>
										</thead>
										<tbody id="plan_wirte_tbody">

											
										</tbody>
									</table>
								</div>
				</div>
			</form:form>				
							
								</section>
						
						
							<footer class="panel-footer text-right bom-write-footer" >																				
								<button class="btn btn-primary" type="button" onclick="planProductWrite()" >등록</button>										
							</footer>												
							</div>
						</div>
					
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
		
	<%@ include file="../../include/footer.jsp"  %>



<script>
var planNo=0;
$(function(){
	planProductAdd();
});
</script>

	
	</body>
</html>