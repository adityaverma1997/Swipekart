<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Telephasic by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>AASHA by Aditya Verma</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]>
<script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<script type="text/javascript" src="js/calendarDateInput.js"></script>
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<%
		Calendar cal = Calendar.getInstance();
		String datevalue = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DATE);
		String cartdate = request.getParameter("cartdate") != null ? request.getParameter("cartdate") : datevalue;
		pageContext.setAttribute("cartdate", cartdate);
	%>
	<!-- Main -->
	<div class="wrapper">
		<div class="container" id="main">
			<form method="post" action="insertcart.do">
				<table cellpadding="10" cellspacing="10" align="center">
					<tr>
						<td><label for="username">Enter User Name : </label></td>
						<td><input type="text" name="username" id="username"
							value="${param.username }" /></td>
					</tr>
					<tr>
						<td>Choose Cart date :</td>
						<td><script type="text/javascript">
							DateInput("cartdate", true, "YYYY-MM-DD",
									'${cartdate}')
						</script></td>
					</tr>
					<tr>
						<td><label for="isorder">Choose whether previously
								ordered or not : </label></td>
						<td><select name="isorder" id="isorder">
								<option value="">Choose any option</option>
								<option value="Yes">Yes</option>
								<option value="No">No</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Add cart details" /></td>
					</tr>
				</table>
			</form>
			<h1 align="center">${message }</h1>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<!-- Scripts -->

	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>
</body>
</html>