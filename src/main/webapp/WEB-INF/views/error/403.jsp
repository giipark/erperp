<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ include file="../include/contants.jspf" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<title>페이지를 찾을 수 없음</title>
<link href="${HOME}/resources/assets/css/error.css" rel="stylesheet" />
</head>
<body>
<div class="errorWrap">
	<div class="topText">
    	<strong>403</strong>
		403 에러 페이지 입니다.
    </div>
    <div class="conText">
		요청 페이지는 에러 페이지로 시스템 개발자는 오류번호를 확인 바랍니다.
    </div>
    <div class="btnBox">
    	<a href="/"><img src="${HOME}/resources/assets/images/btn_home.gif" alt="홈으로 가기" /></a>
        <a href="/" onclick="history.go(-1); return false;"><img src="${HOME}/resources/assets/images/btn_prev.gif" alt="이전 페이지" /></a>
    </div>
</div>
</body>
</html>
