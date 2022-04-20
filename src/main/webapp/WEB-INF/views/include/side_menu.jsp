<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
				<!-- PC - start: 생산  -->
				<aside id="sidebar-left" class="sidebar-left" style="${depth1 eq 'planproduct' ?  'display: block;' : 'display: none;' }">				
					<div class="nano">
						<div class="nano-content">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-main">								
									<li class="nav-parent nav-expanded">
										<a data-toggle-class="sidebar-left-collapsed" data-target="html" 
										data-fire-event="sidebar-left-toggle" onclick="sidebarCollapsed(this)">
											<i class="fa fa-life-ring" aria-hidden="true"></i>
											<span>생산</span>											
						<span class="sidebar-toggle hidden-xs" 						
						data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
							<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
						</span>
										</a>
										<ul class="nav nav-children ${depth1 eq 'planproduct' ?  'nav-active' : '' }">
											<li class="${depth2 eq 'planWrite' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/planproduct/writeForm.do">
													 계획 등록  
												</a>
											</li>
											<li class="${depth2 eq 'planList' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/planproduct/list.do">
													계획 조회, 수정 , 삭제
												</a>
											</li>
										</ul>
									</li>								
								</ul>
							</nav>						
						</div>				
					</div>				
				</aside>
				<!-- end: 생산 -->
				
				
						<!-- PC - start: 자재  -->
				<aside id="sidebar-left" class="sidebar-left" style="${depth1 eq 'materials' ?  'display: block;' : 'display: none;' }">				
					<div class="nano">
						<div class="nano-content">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-main">								
									<li class="nav-parent nav-expanded">
										<a data-toggle-class="sidebar-left-collapsed" data-target="html"
										 data-fire-event="sidebar-left-toggle" onclick="sidebarCollapsed(this)">
											<i class="fa fa-life-ring" aria-hidden="true"></i>
											<span>자재</span>											
						<span class="sidebar-toggle hidden-xs" 						
						data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
							<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
						</span>
										</a>
										<ul class="nav nav-children ${depth1 eq 'materials' ?  'nav-active' : '' }">
											<li class="${depth2 eq 'warehosingMaterialsApply' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/warehosingMaterialsApplyForm.do">
													입고신청  
												</a>
											</li>
											<li class="${depth2 eq 'wmaProcess' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/wmaProcess.do">
													입고처리 
												</a>
											</li>
											<li class="${depth2 eq 'stock' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/stock.do">
													재고관리 
												</a>
											</li>	
											<li class="${depth2 eq 'bulchulApply' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/bulchulApplyPage.do">
													자재불출신청 
												</a>
											</li>
											<li class="${depth2 eq 'bulchulProcess' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/bulchulProcess.do">
													자재불출처리 
												</a>
											</li>																																	
										</ul>
									</li>								
								</ul>
							</nav>						
						</div>				
					</div>				
				</aside>
				<!-- end: 자재 -->		
				
								
								
								<!-- PC - start: 자재정보관리  -->
				<aside id="sidebar-left" class="sidebar-left" style="${depth1 eq 'management' ?  'display: block;' : 'display: none;' }">				
					<div class="nano">
						<div class="nano-content">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-main">								
									<li class="nav-parent nav-expanded">
										<a data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle" onclick="sidebarCollapsed(this)">
											<i class="fa fa-life-ring" aria-hidden="true"></i>
											<span>자재정보관리</span>											
						<span class="sidebar-toggle hidden-xs" 						
						data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
							<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
						</span>
										</a>
										<ul class="nav nav-children ${depth1 eq 'management' ?  'nav-active' : '' }">
											
											<li class="${depth2 eq 'managementWrite' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/writeForm.do">
													자재등록  
												</a>
											</li>
											<li class="${depth2 eq 'managementList' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/listSearch.do">
													목록조회 
												</a>
											</li>
											<li class="${depth2 eq 'bomList' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/bomList.do">
													BOM조회 , 삭제
												</a>
											</li>	
											<li class="${depth2 eq 'bomWrite' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/bomWriteForm.do">
													BOM등록
												</a>
											</li>
											<li class="${depth2 eq 'bomUpdateForm' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/bomList.do?msg=update">
													BOM수정
												</a>
											</li>																																	
										</ul>
									</li>								
								</ul>
							</nav>						
						</div>				
					</div>				
				</aside>
				<!-- end: 자재정보관리 -->			
				
				
				<!-- Mobile - start: sidebar  -->
				<aside id="sidebar-left-Mobile" class="sidebar-left" style="display: none;">				
					
					
					<div class="nano">
						<div class="nano-content">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-main">								
									
								 <li class="nav-parent" >
										<a onclick="navExpandedToggle(this)">
											<i class="fa fa-life-ring" aria-hidden="true"></i>
											<span>회원정보</span>													
										</a>
										<ul class="nav nav-children">
										<li>									
											<a role="menuitem" tabindex="-1" href="${HOME}/member/update" data-lock-screen="true" id="memberUpdate"><i class="fa fa-lock"></i> 정보변경</a>
										</li>
										<li>
											<a role="menuitem" tabindex="-1" href="${HOME}/member/logout.do"><i class="fa fa-power-off"></i> 로그 아웃</a>
										</li>
										</ul>
									</li>
									
									
									
									<li class="nav-parent" >
										<a onclick="navExpandedToggle(this)">
											<i class="fa fa-life-ring" aria-hidden="true"></i>
											<span>생산</span>													
										</a>
										<ul class="nav nav-children ${depth1 eq 'planproduct' ?  'nav-active' : '' }">
											<li class="${depth2 eq 'planWrite' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/planproduct/writeForm.do">
													 계획 등록  
												</a>
											</li>
											<li class="${depth2 eq 'planList' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/planproduct/list.do">
													계획 조회, 수정 , 삭제
												</a>
											</li>
										</ul>
									</li>
						
						
						
							<li class="nav-parent">
										<a  onclick="navExpandedToggle(this)">
											<i class="fa fa-life-ring" aria-hidden="true"></i>
											<span>자재</span>											
										</a>
										<ul class="nav nav-children ${depth1 eq 'materials' ?  'nav-active' : '' }">
											<li class="${depth2 eq 'warehosingMaterialsApply' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/warehosingMaterialsApplyForm.do">
													입고신청  
												</a>
											</li>
											<li class="${depth2 eq 'wmaProcess' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/wmaProcess.do">
													입고처리 
												</a>
											</li>
											<li class="${depth2 eq 'stock' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/stock.do">
													재고관리 
												</a>
											</li>	
											<li class="${depth2 eq 'bulchulApply' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/bulchulApplyPage.do">
													자재불출신청 
												</a>
											</li>
											<li class="${depth2 eq 'bulchulProcess' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/materials/bulchulProcess.do">
													자재불출처리 
												</a>
											</li>																																	
										</ul>
									</li>
									
									
							
				
									
									<li class="nav-parent" >
										<a onclick="navExpandedToggle(this)"><i class="fa fa-life-ring" aria-hidden="true"></i>
											<span>자재정보관리</span>											
										</a>
										<ul class="nav nav-children ${depth1 eq 'management' ?  'nav-active' : '' }">
											
											<li class="${depth2 eq 'managementWrite' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/writeForm.do">
													자재등록  
												</a>
											</li>
											<li class="${depth2 eq 'managementList' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/listSearch.do">
													목록조회 
												</a>
											</li>
											<li class="${depth2 eq 'bomList' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/bomList.do">
													BOM조회 , 삭제
												</a>
											</li>	
											<li class="${depth2 eq 'bomWrite' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/bomWriteForm.do">
													BOM등록
												</a>
											</li>
											<li class="${depth2 eq 'bomUpdateForm' ?  'nav-active' : '' }">
												<a href="${HOME}/erp/management/bomList.do?msg=update">
													BOM수정
												</a>
											</li>																																	
										</ul>
									</li>									
									
									
											
								</ul>
							</nav>						
						</div>				
					</div>				
				
				
				
				</aside>
				<!-- end:  Mobile sidebar -->

		