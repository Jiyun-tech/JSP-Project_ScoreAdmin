<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemService" %>
<%@ page import="kr.ac.kopo.ctc.kopo18.service.ScoreItemServiceImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%			
	// 객체 생성
	// Singleton Pattern 사용 (객체 새로 만들지 않고, 기존 만들어둔 instance 받아 사용)
	ScoreItemService scoreItemService = ScoreItemServiceImpl.getInstance();
	// scoreItemService 통해서 사용할 DaoImpl 지정 (setter)
	ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();
	scoreItemService.setScoreItemDao(scoreItemDao);
	
	String get_name1 = request.getParameter("name1");	
	String get_name = new String(get_name1.getBytes("8859_1"), "utf-8");
	int get_studentid = 0;

	if (get_name != null) {
		get_studentid = Integer.parseInt(request.getParameter("studentid1"));
		scoreItemService.deleteOne(get_studentid);
	}

	response.sendRedirect("tableViewAll.jsp?change=delete");
%>
