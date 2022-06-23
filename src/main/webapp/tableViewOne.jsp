<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.ac.kopo.ctc.kopo18.domain.ScoreItem" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemService" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%				
	int id = Integer.parseInt(request.getParameter("id"));
	
	// 객체 생성
	ScoreItem scoreItem = new ScoreItem();
	
	// Singleton Pattern 사용 (객체 새로 만들지 않고, 기존 만들어둔 instance 받아 사용)
	ScoreItemService scoreItemService = ScoreItemServiceImpl.getInstance();
	// scoreItemService 통해서 사용할 DaoImpl 지정 (setter)
	ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();
	scoreItemService.setScoreItemDao(scoreItemDao);
	
	// 출력 데이터
	scoreItem = scoreItemService.selectOne(id);
	pageContext.setAttribute("scoreItem", scoreItem);	// jstl

%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>성적 전체 조회</title>
</head>
<body>	
		<!--  ##########출력 시작########## -->
		<div class="title"> 성적 선택 조회 </div>
		<br>
		<br>

		<div class="container">
		
		<table class="content_table">
		<!-- 테이블 항목 출력 -> 내용 가운데 정렬 -->
		<tr>
		<td class="category_name"><p align=center>이름</p></td>
		<td class="category_id"><p align=center>학번</p></td>
		<td class="category_score"><p align=center>국어</p></td>
		<td class="category_score"><p align=center>영어</p></td>
		<td class="category_score"><p align=center>수학</p></td>
		<td class="category_score"><p align=center>총점</p></td>
		<td class="category_score"><p align=center>평균</p></td>
		<td class="category_score"><p align=center>등수</p></td>
		</tr>
		
		<tr>
		<td class="category_name"><p align=center><c:out value="${scoreItem.name}"/></p></td>
		<td class="category_id"><p align=center><c:out value="${scoreItem.studentid}"/></p></td>
		<td class="category_score"><p align=center><c:out value="${scoreItem.kor}"/></p></td>
		<td class="category_score"><p align=center><c:out value="${scoreItem.eng}"/></p></td>
		<td class="category_score"><p align=center><c:out value="${scoreItem.mat}"/></p></td>
		<td class="category_score"><p align=center><c:out value="${scoreItem.sum}"/></p></td>
		<td class="category_score"><p align=center><c:out value="${scoreItem.average}"/></p></td>
		<td class="category_score"><p align=center><c:out value="${scoreItem.rank}"/></p></td>
		</tr>
		
		</table>
		<br>

		</div>

	<style>
		body {
			background-color: #E5CCFF;
			color: black;
			font-weight: bold;
			margin: auto;
			padding-top: 2%;
			padding-bottom: 3%;
		}
		.title {
			font-size: 30pt;
			text-align: center;
		}
		.small_title {
			font-size: 20pt;
			text-align: center;
		}
		.category_name {
			width: 100;
			border: 1px solid black;
		}
		.category_id {
			width: 100;
			border: 1px solid black;
		}
		.category_score {
			width: 50;
			border: 1px solid black;
		}
		.container {
			item-align: center;
			text-align: center;
		}
		.content_table {
			border-spacing: 0;
			border-collapse : collapse;
			width: 50%;
			border: 1px solid black;
			margin: auto;
		}
		
		.alink {
			color: black;
		}
	</style>
</body>
</html>