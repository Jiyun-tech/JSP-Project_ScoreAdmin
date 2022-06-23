<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.ac.kopo.ctc.kopo18.dto.Pagination" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.domain.ScoreItem" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemService" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%
	// 객체 생성
	ScoreItem scoreItem = new ScoreItem();
	ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();
	ScoreItemService scoreItemService = ScoreItemServiceImpl.getInstance();
	scoreItemService.setScoreItemDao(scoreItemDao);
	
	// Parameter로 값 받기
	String get_name1 = request.getParameter("name1");
	String get_name = new String(get_name1.getBytes("8859_1"), "utf-8");
	int get_kor = Integer.parseInt(request.getParameter("kor1"));
	int get_eng = Integer.parseInt(request.getParameter("eng1"));
	int get_mat = Integer.parseInt(request.getParameter("mat1"));

	scoreItem.setName(get_name);
	scoreItem.setKor(get_kor);
	scoreItem.setEng(get_eng);
	scoreItem.setMat(get_mat);
	
	ScoreItem retScoreItem = scoreItemService.create(scoreItem);
	ScoreItem finalScoreItem = scoreItemService.selectOne(retScoreItem.getStudentid());
	pageContext.setAttribute("finalScoreItem", finalScoreItem);	// jstl

%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>성적 추가</title>
</head>
<body>
		<!--  ##########출력 시작########## -->
		<div class="title"> 성적 추가 완료 </div>
		<br>
		<br>

		<div class="container">
	
				<table class="content_table">
					<tr>
						<td class="input_category" width="100" align="center">이름</td>
						<td class="input_content" width="300" align="center"><input type="text" readonly name="name1" id="name" maxlength="20" value="${finalScoreItem.name}"></td>
					</tr>
					<tr>
						<td class="input_category" align="center">학번</td>
						<td class="input_content" align="center" name="studentid1"><input type="number" readonly name="kor1" min="209901" id="studentid" value="${finalScoreItem.studentid}"></td>
					</tr>
					<tr>
						<td class="input_category" align="center">국어</td>
						<td class="input_content" align="center"><input type="number" readonly name="kor1" min="0" max="100" id="korean" value="${finalScoreItem.kor}"></td>
					</tr >
					<tr>
						<td class="input_category" align="center">영어</td>
						<td class="input_content" align="center"><input type="number" readonly name="eng1" min="0" max="100" id="english" value="${finalScoreItem.eng}"></td>
					</tr>
					<tr>
						<td class="input_category" align="center">수학</td>
						<td class="input_content" align="center"><input type="number" readonly name="mat1" min="0" max="100" id="math" value="${finalScoreItem.mat}"></td>
					</tr>
				</table>
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
		.input_category {
			width: 100;
			border: 1px solid black;
		}
		.input_content {
			width: 200;
			border: 1px solid black;
		}
		.container {
			item-align: center;
		}
		.button {
			width: 40%;
			margin: auto;
			text-align: center;
		}
		.content_table {
			border-spacing: 0;
			width: 40%;
			border: 1px solid black;
			margin: auto;
			text-align: center;
		}
		.notice_table {
			border-spacing: 0;
			width: 20%;
			border: 0px solid black;
			margin: auto;
		}
	</style>
</body>
</html>