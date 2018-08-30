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
			<form method="post" action="insertorderinfo.do">
				<table cellpadding="10" cellspacing="10" align="center">
					<tr>
						<td><label for="username">Choose User Name : </label></td>
						<td><select name="username" id="username">
								<option value="">Choose any username</option>
								<c:forEach var="item" items="${users }">
									<c:choose>
										<c:when test="${param.username==item.username }">
											<option value="${item.username }" selected>${item.username }
												[ ${item.rolename} ]</option>
										</c:when>
										<c:otherwise>
											<option value="${item.username }">${item.username }
												[ ${item.rolename} ]</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
					<tr>
						<td><label for="cartid">Choose Cart ID : </label></td>
						<td><select name="cartid" id="cartid">
								<option value="">Choose any cartid</option>
								<c:forEach var="item" items="${cart }">
									<c:choose>
										<c:when test="${param.cartid==item.cartid }">
											<option value="${item.cartid }" selected>${item.cartid }
												[ ${item.username} ]</option>
										</c:when>
										<c:otherwise>
											<option value="${item.cartid }">${item.cartid }[
												${item.username} ]</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
					<tr>
						<td>Choose Order date :</td>
						<td><script type="text/javascript">
							DateInput("orderdate", true, "YYYY-MM-DD",
									'${orderdate}')
						</script></td>
					</tr>
					<tr>
						<td>Choose Shipping date :</td>
						<td><script type="text/javascript">
							DateInput("shippingdate", true, "YYYY-MM-DD",
									'${shippingdate}')
						</script></td>
					</tr>
					<tr>
						<td><label for="orderstatus">Enter Order Status : </label></td>
						<td><input type="text" name="orderstatus" id="orderstatus"
							value="${param.orderstatus }" /></td>
					</tr>
					<tr>
						<td><label for="shippingaddress">Enter Shipping
								Address : </label></td>
						<td><input type="text" name="shippingaddress"
							id="shippingaddress" value="${param.shippingaddress }" /></td>
					</tr>
					<tr>
						<td><label for="paymentdetails">Enter Payment Details
								: </label></td>
						<td><input type="text" name="paymentdetails"
							id="paymentdetails" value="${param.paymentdetails }" /></td>
						<%--<td><textarea style="resize:none" name="description" id="description" value="<%= description%>"></textarea></td> --%>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Add Order details" /></td>
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