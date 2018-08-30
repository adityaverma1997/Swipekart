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
		var allchecks = document.getElementsByName("companyids");
		if (allchecks.length > 0) {
			var count = 0;
			var comids = "";
			for (i = 0; i < allchecks.length; i++) {
				if (allchecks[i].checked) {
					comids += allchecks[i].value + ",";
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
					comids = comids.substring(0, comids.length - 2);
					document.getElementById("comids").value = comids;
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
<script type="text/javascript">
	function setvalues(companyid, companyname, description) {
		$("#companyid").val(companyid);
		$("#companyname").val(companyname);
		$("#description").val(description);
	}
</script>
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<!-- Main -->
	<div class="wrapper">
		<div class="container" id="main">
			<h1 align="center"
				style="font-weight: bold; font-size: x-large; color: #FD7272;">Company
				Details</h1>
			<br />
			<h1 align="center">${message }</h1>
			<br />
			<table cellpadding="10" cellspacing="10" align="center" border="1"
				rules="all" style="display: inline;">
				<tr style="font-weight: bold;">
					<th align="left" width="120px">Select</th>
					<th align="left" width="150px">Company ID</th>
					<th align="left" width="200px">Company Name</th>
					<th align="left" width="550px">Description</th>
					<th align="left">Operations</th>
				</tr>
				<c:choose>
					<c:when test="${company!=null }">
						<c:forEach var="item" items="${company }">
							<tr>
								<td><input type="checkbox" name="companyids"
									value=" ${item.companyid } " /></td>
								<td>${item.companyid }</td>
								<td>${item.companyname }</td>
								<td>${item.description }</td>
								<td style="color: #3498db";><a href="#"
									data-reveal-id="myModal"
									onclick="setvalues('${item.companyid}','${item.companyname}','${item.description}')">Edit
										this record</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" align="center">There is no record.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<br /> <br /> <br />
			<table>
			<c:if test="${company!=null }">
				<tr>
					<td width="1200px" align="center">
						<form method="post" action="deletecompanies.do">
							<input type="hidden" name="comids" id="comids" /> <input
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
			Company Details</h1>
		<form method="post" action="updatecompanypopup.do">
			<table cellpadding="10" cellspacing="10" align="center">
				<tr>
					<td><label for="companyid">Enter Company ID</label> 
					<td><input type="text" name="companyid"
						id="companyid" /></td>
				</tr>
				<tr>
					<td><label for="companyname">Enter Company Name</label> 
					<td><input type="text" name="companyname"
						id="companyname" /></td>
				</tr>
				<tr>
					<td><label for="description">Enter Description : </label></td>
					<td><input type="text" name="description" id="description" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Update Company details" /></td>
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