<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.ac.kopo.ctc.kopo18.dto.Pagination" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.domain.ScoreItem" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemService" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!-- JSTL 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%			
	int countPerPage = 10;
	int pageSize = 15;
	
	// 객체 생성
	List <ScoreItem> scoreItemList = new ArrayList<ScoreItem>();
	Pagination p = new Pagination();
	
	// Singleton Pattern 사용 (객체 새로 만들지 않고, 기존 만들어둔 instance 받아 사용)
	ScoreItemService scoreItemService = ScoreItemServiceImpl.getInstance();
	// scoreItemService 통해서 사용할 DaoImpl 지정 (setter)
	ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();
	scoreItemService.setScoreItemDao(scoreItemDao);
	
	// Parameter로 값 받아오기
	String from = request.getParameter("from");
	// 수정된 데이터 studentid
	String target = request.getParameter("target");
	pageContext.setAttribute("target", target);
	// 삭제, 수정 여부
	String change = request.getParameter("change");
	pageContext.setAttribute("change", change);
	
	// 현재 페이지 계산
	int currentPage = 1;
	if (from == null || from.equals("")) {
		currentPage = 1;
	} else {
		currentPage = Integer.parseInt(from);
	}
	
	// Pagenation 메서드 실행하여 return값 객체에 연결.
	int totalCount = scoreItemService.countAll();	// 총 데이터 수
	p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
	pageContext.setAttribute("p", p);	

	// 출력 데이터
	int LineCnt = (currentPage-1)*countPerPage;	// 첫 번째 데이터 번호-1
	pageContext.setAttribute("LineCnt", LineCnt);
	
	scoreItemList = scoreItemService.selectAll(currentPage, countPerPage);
	pageContext.setAttribute("scoreItemList", scoreItemList);

%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>성적 전체 조회</title>
</head>
<body>
		<!--  ##########출력 시작########## -->
		<c:choose>
			<c:when test="${change eq 'update'}">
			<div class="title"> 성적 정정 완료 </div>
			</c:when>
			<c:when test="${change eq 'delete'}">
			<div class="title"> 성적 삭제 완료 </div>
			</c:when>
			<c:otherwise>
			<div class="title"> 전체 성적 조회 </div>
			</c:otherwise>
		</c:choose>
		<br>
		<!-- ##1. 페이지 번호 -->
		<div class=small_title> 현재 페이지 : <c:out value="${ p.cPage }"/> </div>
		<br>

		<div class="container">
		
		<table class="content_table">
		<!-- 테이블 항목 출력 -> 내용 가운데 정렬 -->
		<tr>
		<td class="category_score"><p align=center>NO</p></td>
		<td class="category_name"><p align=center>이름</p></td>
		<td class="category_id"><p align=center>학번</p></td>
		<td class="category_score"><p align=center>국어</p></td>
		<td class="category_score"><p align=center>영어</p></td>
		<td class="category_score"><p align=center>수학</p></td>
		<td class="category_score"><p align=center>총점</p></td>
		<td class="category_score"><p align=center>평균</p></td>
		<td class="category_score"><p align=center>등수</p></td>
		</tr>
		
		<c:forEach var="scoreItemList" items="${scoreItemList}" varStatus="status">
			<c:choose>
			<c:when test="${target eq scoreItemList.studentid}">
				<tr class="updatedOne">
					<td class="category_score"><p align=center>${status.count + LineCnt}</p></td>
					<td class="category_name"><p align=center>${scoreItemList.name}</p></td>
					<td class="category_id"><p align=center style="text-decoration: underline"><a href="./tableViewOne.jsp?id=${scoreItemList.studentid}"> ${scoreItemList.studentid} </a></p></td>
					<td class="category_score"><p align=center>${scoreItemList.kor}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.eng}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.mat}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.sum}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.average}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.rank}</p></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="category_score"><p align=center>${status.count + LineCnt}</p></td>
					<td class="category_name"><p align=center>${scoreItemList.name}</p></td>
					<td class="category_id"><p align=center style="text-decoration: underline"><a href="./tableViewOne.jsp?id=${scoreItemList.studentid}"> ${scoreItemList.studentid} </a></p></td>
					<td class="category_score"><p align=center>${scoreItemList.kor}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.eng}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.mat}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.sum}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.average}</p></td>
					<td class="category_score"><p align=center>${scoreItemList.rank}</p></td>
				</tr>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</table>
		<br>
		
		<!-- Pagination --> 
		<table class="pager_table">
		<tr>
		
		<!-- 4-1. << 화살표, < 화살표 --> 
		<td width="50"><p align="center"><a class="alink" href="./tableViewAll.jsp?from=<c:out value="${p.ppPage}"/>&count=<c:out value="${countPerPage}"/>&target=<c:out value="${target}"/>"> << </a></p></td>
		<td width="50"><p align="center"><a class="alink" href="./tableViewAll.jsp?from=<c:out value="${p.pPage}"/>&count=<c:out value="${countPerPage}"/>&target=<c:out value="${target}"/>"> < </a></p></td>

		<c:forEach var="i" begin="${p.listFirst}" end="${p.listLast}" step="1">
			<c:if test="${i <= p.nnPage }">
				<c:choose>
					<c:when test="${i eq p.cPage}">
					<td width=50 class="selectedPage"><p align=center><a class=alink href="./tableViewAll.jsp?from=${i}&count=<c:out value="${countPerPage}"/>&target=<c:out value="${target}"/>"> ${i} </a></p></td>
					</c:when>
					<c:otherwise>
					<td width=50><p align=center><a class=alink href="./tableViewAll.jsp?from=${i}&count=<c:out value="${countPerPage}"/>&target=<c:out value="${target}"/>"> ${i} </a></p></td>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
		
<%-- 		<c:forEach items="${pageList}" var="pageList"> --%>
<%-- 			<c:if test="${pageList <= p.nnPage }"> --%>
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${pageList == currentPage}"> --%>
<%-- 					<td width=50 class="selectedPage"><p align=center><a class=alink href="./tableViewAll.jsp?from=${pageList}&count=<c:out value="${countPerPage}"/>&target=<c:out value="${target}"/>"> ${pageList} </a></p></td> --%>
<%-- 					</c:when> --%>
<%-- 					<c:otherwise> --%>
<%-- 					<td width=50><p align=center><a class=alink href="./tableViewAll.jsp?from=${pageList}&count=<c:out value="${countPerPage}"/>&target=<c:out value="${target}"/>"> ${pageList} </a></p></td> --%>
<%-- 					</c:otherwise> --%>
<%-- 				</c:choose> --%>
<%-- 			</c:if> --%>
<%-- 		</c:forEach> --%>
				
		<!-- 4-3. > 화살표, >> 화살표  --> 
		<!-- 페이지 이동 화살표 클릭 시의 Parameter 결정 (데이터 출력 시작점) --> 
		<td width=50><p align=center><a class=alink href="./tableViewAll.jsp?from=<c:out value="${p.nPage}"/>&count=<c:out value="${countPerPage}"/>&target=<c:out value="${target}"/>"> > </a></p></td>
		<td width=50><p align=center><a class=alink href="./tableViewAll.jsp?from=<c:out value="${p.nnPage}"/>&count=<c:out value="${countPerPage}"/>&target=<c:out value="${target}"/>"> >> </a></p></td>
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
			font-size: 15pt;
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
		
		.pager_table {
			border-spacing: 0;
			width: 50%;
			border-width: 0px;
			margin: auto;
		}
		
		.selectedPage {
			font-size: 25px;
			text-decoration-line: underline;
		}
		.updatedOne {
			background-color: #B266FF;
		}
		
		a:link {
			color: black;
			text-decoration-line: none;
		}
		a:visited {
			color: black;
			text-decoration-line: none;
		}
	</style>
</body>
</html>