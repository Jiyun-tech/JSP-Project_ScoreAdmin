<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemService" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemServiceImpl" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%
	// 고정값
	int countPerPage = 20;
	//int pageSize = 15;
	
	// Singleton Pattern 사용 (객체 새로 만들지 않고, 기존 만들어둔 instance 받아 사용)
	ScoreItemService scoreItemService = ScoreItemServiceImpl.getInstance();
	// scoreItemService 통해서 사용할 DaoImpl 지정 (setter)
	ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();
	scoreItemService.setScoreItemDao(scoreItemDao);
	
	// Parameter로 값 받아오기
	String get_name1 = request.getParameter("name1");
	String get_name = new String(get_name1.getBytes("8859_1"), "utf-8");
	
	int get_studentid = 0;
	int get_kor = 0;
	int get_eng = 0;
	int get_mat = 0;
	if (get_name != null) {
		get_studentid = Integer.parseInt(request.getParameter("studentid1"));
		get_kor = Integer.parseInt(request.getParameter("kor1"));
		get_eng = Integer.parseInt(request.getParameter("eng1"));
		get_mat = Integer.parseInt(request.getParameter("mat1"));
		
		scoreItemService.updateOne(get_name, get_studentid, get_kor, get_eng, get_mat);
	}

	// 목록 번호 시작점 출력
	String from = request.getParameter("from");
	int findFrom = scoreItemService.findPage(get_studentid, countPerPage);
	
	// 현재 페이지 계산
	int currentPage = 1;
	if (from == null || from.equals("")) {
		currentPage = findFrom;
	} else {
		currentPage = Integer.parseInt(from);
	}
	
	response.sendRedirect("tableViewAll.jsp?from="+findFrom+"&cnt="+countPerPage+"&target="+get_studentid+"&change=update");
%>