<%@page import="java.net.URLEncoder"%>
<%@page import="model.to.CategoryInfo"%>
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
		var allchecks = document.getElementsByName("scategoryids");
		if (allchecks.length > 0) {
			var count = 0;
			var scatids = "";
			for (i = 0; i < allchecks.length; i++) {
				if (allchecks[i].checked) {
					scatids += allchecks[i].value + ",";
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
					scatids = scatids.substring(0, scatids.length - 1);
					document.getElementById("scatids").value = scatids;
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
	function setvalues(subcategoryid, subcategoryname, description, categoryid) {
		$("#subcategoryid").val(subcategoryid);
		$("#subcategoryname").val(subcategoryname);
		$("#description").val(description);
		$("#categoryid").val(categoryid);
	}
</script>
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<!-- Main -->
	<div class="wrapper">
		<div class="container" id="main">
			<h1 align="center"
				style="font-weight: bold; font-size: x-large; color: #FD7272;">Sub
				Category Details</h1>
			<br />
			<h1 align="center">${message }</h1>
			<br />
			<table cellpadding="10" cellspacing="10" align="center" border="1"
				rules="all" style="display: inline;">
				<tr style="font-weight: bold;">
					<th align="left" width="100px">Select</th>
					<th align="left" width="140px">SubCat. ID</th>
					<th align="left" width="160px">SubCat. Name</th>
					<th align="left" width="160px">Category ID</th>
					<th align="left" width="450px">Description</th>
					<th align="left">Operations</th>
				</tr>
				<c:choose>
					<c:when test="${scategory!=null }">
						<c:forEach var="item" items="${scategory }">
							<tr>
								<td><input type="checkbox" name="scategoryids"
									value=" ${item.subcategoryid } " /></td>
								<td>${item.subcategoryid }</td>
								<td>${item.subcategoryname }</td>
								<td>${item.category.categoryid }[${item.category.categoryname }]</td>
								<td>${item.description }</td>
								<td style="color: #3498db";><a href="#" data-reveal-id="myModal" onclick="setvalues('${item.subcategoryid}','${item.subcategoryname}','${item.description}','${item.category.categoryid}')">Edit this record</a></td>
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
			<c:if test="${scategory!=null }">
				<tr>
					<td width="1200px" align="center">
						<form method="post" action="deletesubcategories.do">
							<input type="hidden" name="scatids" id="scatids" /> <input
								type="button" value="Delete selected records"
								onclick="check(this.form)"/>
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
			Sub Category Details</h1>
		<form method="post" action="updatesubcategorypopup.do">
			<table cellpadding="10" cellspacing="10" align="center">
				<tr>
					<td><label for="subcategoryname">Enter SubCategory Name : </label>
					<input type="hidden" name="subcategoryid" id="subcategoryid"/>
					</td>
					<td><input type="text" name="subcategoryname" id="subcategoryname" /></td>
				</tr>
				<tr>
					<td><label for="categoryid">Choose Category ID : </label></td>
					<td>
					<select name="categoryid" id="categoryid">
						<c:forEach var="item" items="${category }">
							<option value="${item.categoryid }">${item.categoryid }
								[ ${item.categoryname} ]</option>
						</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td><label for="description">Enter Description : </label></td>
					<td><input type="text" name="description" id="description" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Update Subcategory details" /></td>
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