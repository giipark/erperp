<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="HOME" value="${pageContext.request.contextPath}"/>
<html>
<head>
<!-- head 영역 -->
<c:import url="../../include/head.jsp"/>
<link rel="stylesheet" href="${HOME}/resources/assets/css/login.css" />
</head>
<body>

			<!-- start: page -->
		<section class="body-sign">
			<div class="center-sign">
				<a href="#" class="logo pull-left" onclick="return false;">
					ERP 재고관리 
				</a>
				<div class="panel panel-sign">					
					<div class="panel-title-sign mt-xl text-right">
						<h2 class="title text-uppercase text-bold m-none"><i class="fa fa-user mr-xs"></i> 로그인</h2>
					</div>
					<div class="panel-body">
						<h5 class="error text-center">${loginError}${param.loginError}</h5>
					
				<form:form  method="post"  commandName="memberVO" action="login.do" id="login_post" >	
							<div class="form-group mb-lg">
								<label>아이디</label>
								<div class="input-group input-group-icon">
									<form:input path="userid"  cssClass="form-control input-lg" />
									<form:errors  path="userid" cssClass="error" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-user"></i>
										</span>
									</span>
								</div>
							</div>

							<div class="form-group mb-lg">
								<div class="clearfix">
									<label class="pull-left">비밀번호</label>
								
								</div>
								<div class="input-group input-group-icon">
									<form:password path="userpw"  cssClass="form-control input-lg" />
									<form:errors  path="userpw" cssClass="error" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-lock"></i>
										</span>
									</span>
								</div>
							</div>
					
 
							<div class="mb-xs text-center">
								<button type="button" class="btn btn-google mb-md ml-xs mr-xs"  id="login_submit"> 								
										<span class="icon icon-lg">
											<i class="fa fa-user"></i>
										</span>&nbsp;로그인</button>
							</div>
						</form:form>
						
						
					</div>
				</div>

			</div>
		</section>
		<!-- end: page -->

<!-- footer 영역 -->
<c:import url="../../include/footer.jsp"/>
</body>
</html>