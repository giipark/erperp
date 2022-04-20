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
								<li><span>BOM 수정 </span></li>
							</ol>												
						</div>
					</header>
				
				
			<!-- start: page -->
			<form method="post" action="bomUpdate.do" id="bomUpdateFrm">
						<div class="row">
							<div class="col-md-12">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">											
										</div>						
										<h2 class="panel-title">BOM 수정</h2>
									</header>

							
								<div class="panel-body">
										<div class="form-group">
											<div class="col-sm-6">
												<label class="col-sm-4 control-label text-right">제품 코드:</label>
												<div class="col-sm-8">
													<input type="text" name="pcode" id="pcode" class="form-control" value="${bomVO.pcode}">
												</div>
											</div>
											
											<div class="col-sm-6 ">
											<label class="col-sm-2 control-label text-left">제품 명:</label>
											<div class="col-sm-8 text-left">
												<input type="text" name="productName" id="productName" class="form-control" value="${bomVO.productName}">
											</div>
											</div>
											
										</div>															
								</div>
							
								</section>											
							</div>
							
							
						<div class="col-md-12">
								<section class="panel">
									<div class="panel-body" id="bom_material_input">
								
								<c:forEach items="${bomMaterialList}" var="row" varStatus="status">
										<div class="form-group">
											 <div class="col-sm-4">
												<label class="col-sm-4 control-label">자재 코드:</label>
												<div class="col-sm-8" id="list_${status.index}_div">				
													<input type="text" title="자재 코드" name="list[${status.index}].mcode" id="${status.index}" class="form-control form-tt" 
													onkeyup="getByMcodeAutoCompleate(this)"   autocomplete="off"  value="${row.mcode }">		
								<div class="tt-menu" id="tt-menu_${status.index}" onmouseleave="autoCompleteHide(this)">
									<div class="tt-dataset tt-dataset-states" id="tt-dataset_${status.index}" >										
									</div>
								</div>																										
												</div>
																					
												
											 </div>
											 <div class="col-sm-5">
												<label class="col-sm-3 control-label">자재 명:</label>
												<div class="col-sm-9">
													<input type="text" title="자재 명" name="list[${status.index}].materialName" id="list_${status.index}_materialName"
													value="${row.materialName }"
													 class="form-control">
												</div>
											 </div>
											<div class="col-sm-3">
												<label class="col-sm-5 control-label">자재 수량:</label>
												<div class="col-sm-7">
													<input type="text" title="자재 수량" name="list[${status.index}].materialQuantity" 
													value="${row.materialQuantity }"
													id="list_${status.index}_materialQuantity" 
													numberonly="true" style = "text-align:right;"
													class="form-control">
												</div>
											 </div>										
										</div>
								</c:forEach>
								<c:if test="${bomMaterialList.size() ==0}">
								<%@ include file="bomForm.jsp" %>
								</c:if>		
										
									</div>
									
									<footer class="panel-footer text-center">
										 <input type="hidden" value="${bomMaterCount}" id="addMatterial_no" >
										<button class="btn btn-info" type="button" onclick="addMatterial()">+</button>										
									</footer>
								</section>							
							</div>
							
							
							<div class="col-md-12">
									<footer class="panel-footer text-right bom-write-footer" >
										<input type="hidden" name="bomid" value="${bomVO.bomid}">
										<button class="btn btn-default" type="button" onclick="bomCancel()" >취소</button>
										<button class="btn btn-primary" type="button" onclick="bomUpdateSubmit()" >수정</button>										
									</footer>																				
							</div>
							
							
						</div>
					</form>
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		

	
	
	<%@ include file="../../include/footer.jsp"  %>



	</body>
</html>