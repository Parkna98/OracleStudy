<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	// delete.jsp?hakbun=1
	// 키값을 주고 번호를 받는다
	// 키값은 동일해야 한다 
	String hakbun=request.getParameter("hakbun");
	StudentDAO dao=new StudentDAO();
	dao.stdDelete(Integer.parseInt(hakbun));
	response.sendRedirect("list.jsp");
%>