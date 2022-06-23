<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
 <head>
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	 <!-- 인코딩 방식 선언 ==> UTF-8 ==> 한글 깨지지 않도록 함 -->
 </head>
 <body>
	<style>
		body{
			background-color: black;
			<!--background-size: 100% 100%;-->
			<!-- background-repeat: no-repeat;-->
		}
		table {
			align-items: center;
			margin: auto;
			margin-top: 2%;
		}
		td{
			width:20%;
			text-align: center;
			font-size: 20pt;
			font-family: "Gill Sans", sans-serif;
			font-family: "Gill Sans", sans-serif;
		}
		.menu {
			border: 2px solid #E5CCFF;
		}
		a:link {
			color: white;
			text-decoration-line: none;
		}
		a:visited {
			color: white;
			text-decoration-line: none;
		}	

	</style>
	<table>
		<!-- 4개 메뉴 만들고, anchor 태그로 각각의 파일 연결. -->
		<!-- 이 때, 연결해서 보여줄 페이지 범위를 지정 ==> "main" (하단 frame)-->
		<tr>
			<td class="menu"><a href="./intro_DB.jsp" target="main">　　🏡　　</a></td>
			<td class="menu"><a href="./tableViewAll.jsp" target="main">* 성적 전체 조회</a></td>
			<td class="menu"><a href="./inputForm1_insert.html" target="main">* 추가</a></td>
			<td class="menu"><a href="./inputForm2_select.html" target="main">* 정정/삭제</a></td>
			<td class="menu"><a href="./dataDeleteAll.jsp" target="main">* 성적 전체 삭제</a></td>
		</tr>
	</table>

	<table>
 </body>
 <html>