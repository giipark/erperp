<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./include/contants.jspf" %>
<!DOCTYPE html>
<html class="fixed" lang="ko">
<head>
<%@ include file="include/head.jsp" %>
</head>
<body>
		<section class="body">	
			<header  class="page-header header">
				<%@ include file="include/header.jsp" %>
			</header>
		
			<div class="inner-wrapper">			

				<%@ include file="include/side_menu.jsp" %>

				<section role="main" class="content-body">
						<div class="row">
						
						</div>
				</section>
				
			</div><!-- //inner-wrapper -->
		</section>
		
		
	<%@ include file="include/footer.jsp"  %>


<script>
//Scroll totop
//-----------------------------------------------
$(window).scroll(function() {
	if($(this).scrollTop() != 0) {
		$(".scrollToTop").addClass("fadeToTop");
		$(".scrollToTop").removeClass("fadeToBottom");
	} else {
		$(".scrollToTop").removeClass("fadeToTop");
		$(".scrollToTop").addClass("fadeToBottom");
	}	
	
});

$(function(){
	$(".scrollToTop").click(function() {
		$("body,html").animate({scrollTop:0},800);
	});	
	

	
});


</script>
		
	</body>
</html>