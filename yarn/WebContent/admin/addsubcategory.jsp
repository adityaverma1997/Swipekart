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
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<!-- Main -->
	<div class="wrapper">
		<div class="container" id="main">
			<form method="post" action="insertsubcategory.do">
				<table cellpadding="10" cellspacing="10" align="center">
					<tr>
						<td><label for="subcategoryname">Enter Sub Category
								Name : </label></td>
						<td><input type="text" name="subcategoryname"
							id="subcategoryname" value="${param.subcategoryname }" /></td>
					</tr>
					<tr>
						<td><label for="description">Enter Description : </label></td>
						<td><input type="text" name="description" id="description"
							value="${param.description }" /></td>
						<%--<td><textarea style="resize:none" name="description" id="description" value="<%= description%>"></textarea></td> --%>
					</tr>
					<tr>
						<td><label for="categoryid">Choose Category ID : </label></td>
						<td><select name="categoryid" id="categoryid">
								<option value="">Choose any categoryid </option>
								<c:forEach var="item" items="${category }">
									<c:choose>
										<c:when test="${param.categoryid==item.categoryid }">
											<option value="${item.categoryid }" selected>${item.categoryid }
												[ ${item.categoryname} ]</option>
										</c:when>
										<c:otherwise>
											<option value="${item.categoryid }">${item.categoryid }
												[ ${item.categoryname} ]</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Add subcategory details" /></td>
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