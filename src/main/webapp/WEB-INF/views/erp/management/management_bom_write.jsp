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
								<li><span>BOM 등록 </span></li>
							</ol>												
						</div>
					</header>
				
				
			<!-- start: page -->
			<form method="post" action="bomWrite.do" id="bomWriteFrm">
						<div class="row">
							<div class="col-md-12">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">											
										</div>						
										<h2 class="panel-title">BOM 등록</h2>
									</header>

							
								<div class="panel-body">
										<div class="form-group">
											<div class="col-sm-6">
												<label class="col-sm-4 control-label text-right">제품 코드:</label>
												<div class="col-sm-8">
													<input type="text" name="pcode" id="pcode" class="form-control">
												</div>
											</div>
											
											<div class="col-sm-6 ">
											<label class="col-sm-2 control-label text-left">제품 명:</label>
											<div class="col-sm-8 text-left">
												<input type="text" name="productName" id="productName" class="form-control">
											</div>
											</div>
											
										</div>															
								</div>
							
								</section>											
							</div>
							
							
						<div class="col-md-12">
								<section class="panel">
									<div class="panel-body" id="bom_material_input">
								
									<%@ include file="bomForm.jsp" %>
								
									</div>
									
									<footer class="panel-footer text-center">
										 <input type="hidden" value="1" id="addMatterial_no" >
										<button class="btn btn-info" type="button" onclick="addMatterial()">+</button>										
									</footer>
								</section>							
							</div>
							
							
							<div class="col-md-12">
									<footer class="panel-footer text-right bom-write-footer" >
										<button class="btn btn-primary" type="button" onclick="bomWriteSubmit()" >등록</button>										
									</footer>																				
							</div>
							
							
						</div>
					</form>
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		

	
	
	<%@ include file="../../include/footer.jsp"  %>

<script id="successTemplate" type="text/x-handlebars-template">
<div class="form-group">
	 <div class="col-sm-4">
		<label class="col-sm-4 control-label">자재 코드:</label>
		<div class="col-sm-8" id="list_0_div">				
			<input type="text" title="자재 코드" name="list[0].mcode" id="0" class="form-control form-tt" 
			onkeyup="getByMcodeAutoCompleate(this)"   autocomplete="off">		
<div class="tt-menu" id="tt-menu_0" onmouseleave="autoCompleteHide(this)">
	<div class="tt-dataset tt-dataset-states" id="tt-dataset_0" >										
	</div>
</div>																										
				</div>
													
				
			 </div>
			 <div class="col-sm-5">
				<label class="col-sm-3 control-label">자재 명:</label>
				<div class="col-sm-9">
					<input type="text" title="자재 명" name="list[0].materialName" id="list_0_materialName" class="form-control">
				</div>
			 </div>
			<div class="col-sm-3">
				<label class="col-sm-5 control-label">자재 수량:</label>
				<div class="col-sm-7">
					<input type="text" title="자재 수량" name="list[0].materialQuantity" id="list_0_materialQuantity" 
					numberonly="true" style = "text-align:right;"
		class="form-control">
		</div>
	 </div>										
</div>	
</script>

	



	</body>
</html>