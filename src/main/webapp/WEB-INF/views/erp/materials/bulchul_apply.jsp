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
								<li><span>자재불출신청</span></li>
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
										<h2 class="panel-title">자재불출신청 </h2>
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
				<input type="text" name="mcode" id="mcode" 	autocomplete="off" list="mcode-list"  
				class="form-control" title="자재 코드 " placeholder="자재 코드" onkeyup="getByMcodeAutoCompleateBulchul(this)"
					onblur="ttAutocompleteBulchulApply(this)"  onchange="ttAutocompleteBulchulApply"
				>
				<datalist id="mcode-list">					
				</datalist>	
								
								
										</td>									  
										<td>
				<input type="text" name="materialName" id="materialName" 
				autocomplete="off" class="form-control" title="자재 명 " placeholder="자재 명 "  >

										</td>

										<td>
				<input type="hidden" name="maid" id="maid">
				<input type="hidden" name="currentInventory" id="currentInventory">
				<input type="hidden" name="properInventory" id="properInventory">
						
				<input type="text" name="applicationQuantity" id="applicationQuantity" class="form-control" 
				data-toggle="tooltip" data-placement="top" min="0" onkeyup="maxCheck(this)"
				 numberonly='true' placeholder="신청 수량" >
										</td>
										 <td  class="text-center">
												<button class="btn btn-primary" onclick="addBulchulApply()">+</button>
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

			<form:form method="post" action="bulchulApplyWrite.do" id="bulchulApplyFRM">
				<div class="panel-body">
								<div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar my-custom-scrollbar5">
									<table class="table table-bordered table-striped table-condensed mb-none">
										<thead>
											<tr>
												<th width="5%" class="text-center">No</th>
												<th width="25%" class="text-center">자재 코드</th>
												<th width="25%" class="text-center">자재 명</th>
												<th width="15%" class="text-center">재고수량</th>
												<th width="15%" class="text-center">신청수량</th>	
												<th width="15%" class="text-center">비고</th>											
											</tr>
										</thead>
										<tbody id="tbody-bulchul-apply">
											<tr class="blank">
												<td colspan="6" class="text-center">	
													 데이터를 입력해 주세요.
												</td>
											</tr>	
											
										</tbody>
										<tfoot >
										<tr>
												<td colspan="6" class="text-right">											
												
												</td>
											</tr> 
										</tfoot>
									</table>
								</div>
							</div>
						</form:form>				
							 <footer class="panel-footer text-right" >
							 			<input type="hidden" id="no" value="0">
										<button class="btn btn-primary" type="button" onclick="bulchulApplySave()" >저장</button>
								</footer>
								</section>																	
							</div>
					
							
						</div><!-- start: page -->
					
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
		

	<%@ include file="../../include/footer.jsp"  %>






	</body>
</html>