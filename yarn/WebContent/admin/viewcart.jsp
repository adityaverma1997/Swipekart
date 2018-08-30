<%@page import="java.util.Calendar"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript">
	function check(frm) {
		var allchecks = document.getElementsByName("cartids");
		if (allchecks.length > 0) {
			var count = 0;
			var crtids = "";
			for (i = 0; i < allchecks.length; i++) {
				if (allchecks[i].checked) {
					crtids += allchecks[i].value + ",";
					count++;
				}
			}
			if (count == 0) {
				alert("Nothing is selected");
			} else {
				if (count == 1) {
					var result = confirm("Are you sure you want to delete this record? ");
				} else {
					var result = confirm("Are you sure you want to delete these records? ");
				}
				if (result) {
					crtids = crtids.substring(0, crtids.length - 1);
					document.getElementById("crtids").value = crtids;
					frm.submit();
				}
			}
		}
	}
</script>
<head>
<title>AASHA by Aditya Verma</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="reveal.css" />
<script type="text/javascript" src="jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="jquery.reveal.js"></script>
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<script type="text/javascript" src="js/calendarDateInput.js"></script>
<script type="text/javascript">
	function setvalues(cartid, username, cartdate, isorder) {
		$("#cartid").val(cartid);
		$("#username").val(username);
		$("#isorder").val(isorder);
		var values = cartdate.split("-");
		var yr = parseInt(values[0]);
		var mt = parseInt(values[1]);
		var dt = parseInt(values[2]);
		$("#startdate_Year_ID").val(yr);
		$("#startdate_Year_ID").keyup();
		$("#startdate_Day_ID").val(dt);
		$("#startdate_Day_ID").change();
		$("#startdate_Month_ID").val(mt - 1);
		$("#startdate_Month_ID").change();
	}
</script>
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<!-- Main -->
	<div class="wrapper">
		<div class="container" id="main">
			<h1 align="center"
				style="font-weight: bold; font-size: x-large; color: #FD7272;">Cart
				Details</h1>
			<br />
			<h1 align="center">${message }</h1>
			<br />
			<table cellpadding="10" cellspacing="10" align="center" border="1"
				rules="all" style="display: inline;">
				<tr style="font-weight: bold;">
					<th align="left" width="150px">Select</th>
					<th align="left" width="180px">Cart ID</th>
					<th align="left" width="230px">User Name</th>
					<th align="left" width="230px">Cart Date</th>
					<th align="left" width="200px">Is Order</th>
					<th align="left">Operations</th>
				</tr>
				<c:choose>
					<c:when test="${cart!=null }">
						<c:forEach var="item" items="${cart }">
							<tr>
								<td><input type="checkbox" name="cartids"
									value=" ${item.cartid } " /></td>
								<td>${item.cartid }</td>
								<td>${item.username }</td>
								<td>${item.cartdate }</td>
								<td>${item.isorder }</td>
								<td style="color: #3498db";><a href="#"
									data-reveal-id="myModal"
									onclick="setvalues('${item.cartid}','${item.username}','${item.cartdate}','${item.isorder}')">Edit
										this record</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6" align="center">There is no record.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<br /> <br /> <br />
			<table>
				<c:if test="${cart!=null }">
					<tr>
						<td width="1200px" align="center">
							<form method="post" action="deletecart.do">
								<input type="hidden" name="crtids" id="crtids" /> <input
									type="button" value="Delete selected records"
									onclick="check(this.form)" />
							</form>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<div id="myModal" class="reveal-modal">
		<h1 align="center"
			style="color: black; font-weight: bold; font-size: xlarge;">Edit
			Cart Details</h1>
		<form method="post" action="updatecartpopup.do">
			<table cellpadding="10" cellspacing="10" align="center">
				<tr>
					<td><label for="username">Enter Username</label> <input
						type="hidden" name="cartid" id="cartid" /></td>
					<td><input type="text" name="username" id="username" /></td>
				</tr>
				<tr>
					<td>Choose Cart date :</td>
					<td><script type="text/javascript">
						DateInput("cartdate", true, "YYYY-MM-DD")
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
						value="Update cart details" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- Scripts -->

	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>
</body>
</html>