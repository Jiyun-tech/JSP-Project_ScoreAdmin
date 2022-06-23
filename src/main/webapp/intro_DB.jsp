<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemService" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemServiceImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
	 	// Singleton Pattern 사용 (객체 새로 만들지 않고, 기존 만들어둔 instance 받아 사용)
		ScoreItemService scoreItemService = ScoreItemServiceImpl.getInstance();
 		// scoreItemService 통해서 사용할 DaoImpl 지정 (setter)
		ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();
		scoreItemService.setScoreItemDao(scoreItemDao);
		
		int visitor = scoreItemService.countVisitor();
		pageContext.setAttribute("visitor", visitor);
%>
 <html>
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 </head>
 <body>

 	<div class="title"> KOPO18의 성적 조회 페이지에 오신 것을 환영합니다. </div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="visitor">현재 페이지 총 방문자 수는 [<c:out value="${visitor}"/>]명 입니다.</div>
	<style>
		body {
			background-color: #E5CCFF;
			color: black;
			font-weight: bold;
			margin: auto;
			margin-top: 10%;
		}
		.title {
			text-align: center;
			font-size: 30pt;
		}
		.visitor {
			text-align: center;
			font-size: 15pt;
		}
	</style>
</body>
</html>
