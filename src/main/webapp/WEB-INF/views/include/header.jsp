<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<input type="hidden" value="${HOME}" id="HOME">
				<div class="logo-container">
					<a href="${HOME}/" class="logo">
						<img src="${HOME}/resources/assets/images/logo.png" height="35" width="100" alt="erp admin" />
					</a>
					<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
	<div id="sidebar">
	<a href="#" class="visible-phone"></a>
	
	
	<ul>
    <li class="submenu"><a href="${HOME}/erp/planproduct/list.do" class="${depth1 eq 'planproduct' ?  'selected' : '' }"><span>생산</span></a>
      <ul>
        <li><a href="${HOME}/erp/planproduct/writeForm.do">계획 등록</a></li>
        <li><a href="${HOME}/erp/planproduct/list.do">계획 조회, 수정 , 삭제</a></li>
      </ul>
    </li>
    
    <li class="submenu"> <a href="${HOME}/erp/materials/warehosingMaterialsApplyForm.do" class="${depth1 eq 'materials' ?  'selected' : '' }"> <span>자재</span></a>
      <ul>    
      	<li><a href="${HOME}/erp/materials/warehosingMaterialsApplyForm.do">입고신청</a></li>    
        <li><a href="${HOME}/erp/materials/wmaProcess.do">입고처리</a></li>
        <li><a href="${HOME}/erp/materials/stock.do">재고관리</a></li>
         <li><a href="${HOME}/erp/materials/bulchulApplyPage.do">자재불출신청</a></li>
         <li><a href="${HOME}/erp/materials/bulchulProcess.do">자재불출처리</a></li>
      </ul>
    </li>
    
    <li class="submenu"> <a href="${HOME}/erp/management/listSearch.do" class="${depth1 eq 'management' ?  'selected' : '' }"> <span>자재정보관리</span></a>
      <ul>
      	<li><a href="${HOME}/erp/management/writeForm.do">자재등록</a></li>
        <li><a href="${HOME}/erp/management/listSearch.do">목록조회</a></li>
        <li><a href="${HOME}/erp/management/bomList.do">BOM조회, 삭제</a></li>
        <li><a href="${HOME}/erp/management/bomWriteForm.do">BOM등록</a></li>
        <li><a href="${HOME}/erp/management/bomList.do?msg=update">BOM수정</a></li>
      </ul>
    </li>           
  </ul>
	<div id="userbox" class="userbox">
						<a href="#" data-toggle="dropdown" >							
							<div class="profile-info" data-lock-name="${sessionScope.LOGIN.username }" data-lock-email="${sessionScope.LOGIN.email }">								
								<i class="fa fa-user"></i> ${sessionScope.LOGIN.username }
								<input type="hidden" id="login_userid" value="${sessionScope.LOGIN.userid }">
								<input type="hidden" id="login_username" value="${sessionScope.LOGIN.username }">								
							</div>			
							<i class="fa custom-caret"></i>
						</a>			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li>									
									<a role="menuitem" tabindex="-1" href="${HOME}/member/update" data-lock-screen="true" id="memberUpdate"><i class="fa fa-lock"></i> 정보변경</a>
								</li>
								<li>
									<a role="menuitem" tabindex="-1" href="${HOME}/member/logout.do"><i class="fa fa-power-off"></i> 로그 아웃</a>
								</li>
							</ul>
						</div>
					</div>
  	<div class="gnb_bg"></div>														
  </div>    
  

  