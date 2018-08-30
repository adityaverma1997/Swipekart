<%@page import="model.dao.HibernateViewUtil"%>
<%@page import="model.to.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String username=request.getParameter("username");
	if(username!=null){
		username=username.trim();
		LoginInfo record=HibernateViewUtil.getUsers(username);
		if(record!=null){
			out.println("Username is available");
		}else{
			out.println("Username is not available");
		}
	}
%>