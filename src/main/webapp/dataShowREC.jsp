<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.ac.kopo.ctc.kopo18.dto.Pagination" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.domain.ScoreItem" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemService" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// 객체 생성
	// Singleton Pattern 사용 (객체 새로 만들지 않고, 기존 만들어둔 instance 받아 사용)
	ScoreItemService scoreItemService = ScoreItemServiceImpl.getInstance();
	// scoreItemService 통해서 사용할 DaoImpl 지정 (setter)
	ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();
	scoreItemService.setScoreItemDao(scoreItemDao);

	int get_studentid = Integer.parseInt(request.getParameter("selectedID"));
	ScoreItem scoreItem = scoreItemService.selectOne(get_studentid);
	pageContext.setAttribute("scoreItem", scoreItem);	// jstl
	
	String get_name = scoreItem.getName();
	pageContext.setAttribute("get_name", get_name);		// jstl	
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>성적 추가</title>
</head>
<body>
	
		<!--  ##########출력 시작########## -->
		<div class="title"> 성적 조회 후 정정/삭제</div>
		<br>
		<br>

		<div class="container">
		
			<form id="select" method="post" action="./dataShowREC.jsp">
				<table class="button">
					<tr>
						<td width="100" align="center">조회할 학번</td>
						<td width="300" align="center">
						<!--max="2147483647"-->
						<input type="number" id="input_studentid" name="selectedID" min="209901" onchange="idCheck(this)" onkeyup="idCheck(this)" onkeydown="idCheck(this)">
						<input type="submit" value="조회">
						</td>
					</tr>
				</table>
			</form>
			
			<form id="change" method="post" action="./dataUpdate.jsp">
			<c:choose>
				<c:when test="${get_name eq null or get_name eq ''}">
					<table class="content_table">
						<tr>
							<td class="input_category" width="100" align="center">이름</td>
							<td class="input_content" width="300" align="center"><input type="text" readonly id="name" maxlength="20" value="해당 학번 없음"></td>
						</tr>
						<tr>
							<td class="input_category" align="center">학번</td>
							<td class="input_content" align="center"><input type="number" readonly min="209901" id="studentid"></td>
						</tr>
						<tr>
							<td class="input_category" align="center">국어</td>
							<td class="input_content" align="center"><input type="number" readonly min="0" max="100" id="korean"></td>
						</tr >
						<tr>
							<td class="input_category" align="center">영어</td>
							<td class="input_content" align="center"><input type="number" readonly min="0" max="100" id="english"></td>
						</tr>
						<tr>
							<td class="input_category" align="center">수학</td>
							<td class="input_content" align="center"><input type="number" readonly min="0" max="100" id="math"></td>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
					<table class="content_table">
						<tr>
							<td class="input_category" width="100" align="center">이름</td>
							<td class="input_content" width="300" align="center"><input type="text" name="name1" id="name" maxlength="20" value="${scoreItem.name}" onsubmit="characterCheck(this)" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)"></td>
						</tr>
						<tr>
							<td class="input_category" align="center">학번</td>
							<td class="input_content" align="center"><input type="number" readonly name="studentid1" min="209901" id="studentid" value="${scoreItem.studentid}"></td>
						</tr>
						<tr>
							<td class="input_category" align="center">국어</td>
							<td class="input_content" align="center"><input type="number" name="kor1" min="0" max="100" id="korean" value="${scoreItem.kor}"></td>
						</tr >
						<tr>
							<td class="input_category" align="center">영어</td>
							<td class="input_content" align="center"><input type="number" name="eng1" min="0" max="100" id="english" value="${scoreItem.eng}"></td>
						</tr>
						<tr>
							<td class="input_category" align="center">수학</td>
							<td class="input_content" align="center"><input type="number" name="mat1" min="0" max="100" id="math" value="${scoreItem.mat}"></td>
						</tr>
					</table>
					<br>
					
					<table class="button">
						<tr>
							<td width="100"></td>
							<td width="300" align="center">
								<input type="submit" value="정정">
								<input type="submit" formaction="./dataDeleteOne.jsp" value="삭제">
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>

				<br>
				<table class="notice_table">
				<tr>
					<td>* 이름 : 최대 20자</td>
				</tr>
				<tr>
					<td>* 점수 : 0점~100점 (숫자만 입력)</td>
				</tr>
				</table>
			</form>
		</div>
	<script>
	    function idCheck(obj){
			let reg = /[\{\}\[\]\/?.,;:|\-_+<>@\#$%&\'\"\\\()\=`~!^*\s]/gi;
			if (reg.test(obj.value)){
				alert("특수 문자나 띄어쓰기는 입력하실 수 없습니다.");
				obj.value = obj.value.substring(0, 0);
			}
		}
	
	    function characterCheck(obj){
			<!-- var reg = /[\{\}\[\]\/?.,;:|\-_+<>@\#$%&\'\"\\\()\=`~!^*123456789\s^ㄱ-ㅎㅏ-ㅣ\x20]/gi; -->
			let reg = /[\{\}\[\]\/?.,;:|\-_+<>@\#$%&\'\"\\\()\=`~!^*\s]/gi;
			if (reg.test(obj.value)){
				alert("특수 문자나 숫자, 띄어쓰기는 입력하실 수 없습니다.");
				obj.value = obj.value.substring(0, 0);
			}
		}
		
		window.onload = function() {
			document.getElementById('change').addEventListener('submit', function(e){
			if(document.getElementById('name').value == ''){
				e.preventDefault()//이름 미입력을 방지
				alert('이름을 입력하세요 (최대 20자)')
			} else if(document.getElementById('korean').value == '' || document.getElementById('korean').value < 0 || document.getElementById('korean').value > 100){
				e.preventDefault()//국어점수 미입력을 방지
				alert('국어 점수를 정확히 입력하세요 (0~100)')
			} else if(document.getElementById('english').value == '' || document.getElementById('english').value < 0 || document.getElementById('english').value > 100){
				e.preventDefault()//영어점수 미입력을 방지
				alert('영어 점수를 정확히 입력하세요 (0~100)')
			} else if(document.getElementById('math').value == '' || document.getElementById('math').value < 0 || document.getElementById('math').value > 100){
				e.preventDefault()//국어점수 미입력을 방지
				alert('수학 점수를 정확히 입력하세요 (0~100)')} 
			  });
		}
	</script>
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