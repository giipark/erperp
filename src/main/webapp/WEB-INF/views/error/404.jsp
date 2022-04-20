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
    	<strong>404</strong>
		요청하신 페이지를 찾을수 없습니다.
    </div>
    <div class="conText">
		방문하시려는 페이지의 주소가 잘못 입력되었거나,<br />
		페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.<br />
		입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.
    </div>
    <div class="btnBox">
    	<a href="/"><img src="${HOME}/resources/assets/images/btn_home.gif" alt="홈으로 가기" /></a>
        <a href="/" onclick="history.go(-1); return false;"><img src="${HOME}/resources/assets/images/btn_prev.gif" alt="이전 페이지" /></a>
    </div>
</div>
</body>
</html>
