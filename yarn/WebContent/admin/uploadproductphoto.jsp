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
			<form method="post" action="uploadproductphoto.do"
				enctype="multipart/form-data">
				<table cellpadding="10" cellspacing="10" align="center">
					<tr>
						<td><label for="productid">Choose Product ID : </label></td>
						<td><select name="productid" id="productid">
							<option value="">Choose any productid </option>
								<c:forEach var="item" items="${products }">
									<c:choose>
										<c:when test="${param.productid==item.productid }">
											<option value="${item.productid }" selected>${item.productid }
												[ ${item.productname} ]</option>
										</c:when>
										<c:otherwise>
											<option value="${item.productid }">${item.productid }
												[ ${item.productname} ]</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label for="photo">Choose any photo : </label></td>
						<td><input type="file" name="photo" id="photo" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Upload product photo" /></td>
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