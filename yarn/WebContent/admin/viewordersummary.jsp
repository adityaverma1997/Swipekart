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
		var allchecks = document.getElementsByName("detailids");
		if (allchecks.length > 0) {
			var count = 0;
			var dtids = "";
			for (i = 0; i < allchecks.length; i++) {
				if (allchecks[i].checked) {
					dtids += allchecks[i].value + ",";
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
					dtids = dtids.substring(0, dtids.length - 1);
					document.getElementById("dtids").value = dtids;
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
	function setvalues(detailid, productid, quantity, price, orderid) {
		$("#detailid").val(detailid);
		$("#productid").val(productid);
		$("#quantity").val(quantity);
		$("#price").val(price);
		$("#orderid").val(orderid);
	}
</script>
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<!-- Main -->
	<div class="wrapper">
		<div class="container" id="main">
			<h1 align="center"
				style="font-weight: bold; font-size: x-large; color: #FD7272;">Product
				Details</h1>
			<br />
			<h1 align="center">${message }</h1>
			<br />
			<table cellpadding="10" cellspacing="10" align="center" border="1"
				rules="all" style="display: inline;">
				<tr style="font-weight: bold;">
					<th align="left" width="120px">Select</th>
					<th align="left" width="200px">Detail ID</th>
					<th align="left" width="240px">Product ID</th>
					<th align="left" width="190px">Quantity</th>
					<th align="left" width="180px">Price</th>
					<th align="left" width="200px">Order ID</th>
					<th align="left">Operations</th>
				</tr>
				<c:choose>
					<c:when test="${details!=null }">
						<c:forEach var="item" items="${details }">
							<tr>
								<td><input type="checkbox" name="detailids"
									value=" ${item.detailid } " /></td>
								<td>${item.detailid }</td>
								<td>${item.products.productid }[${item.products.productname}]</td>
								<td>${item.quantity }</td>
								<td>${item.price }</td>
								<td>${item.order.orderid }[${item.order.username}]</td>
								<td style="color: #3498db";><a href="#"
									data-reveal-id="myModal"
									onclick="setvalues('${item.detailid}','${item.products.productid}','${item.quantity}','${item.price}','${item.order.orderid}')">Edit
										this record</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="7" align="center">There is no record.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<br /> <br /> <br />
			<table>
			<c:if test="${details!=null }">
				<tr>
					<td width="1200px" align="center">
						<form method="post" action="deleteordersummary.do">
							<input type="hidden" name="dtids" id="dtids" /> <input
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
			Cart Item Details</h1>
		<form method="post" action="updateordersummarypopup.do">
			<table cellpadding="10" cellspacing="10" align="center">
				<tr>
					<td><label for="productid">Choose Product ID : </label></td>
					<td><input type="hidden" name="detailid" id="detailid" /></td>
					<td><select name="productid" id="productid">
							<option value="">Choose any productid</option>
							<c:forEach var="item" items="${products }">
								<option value="${item.productid }">${item.productid }[
									${item.productname} ]</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label for="quantity">Enter Quantity : </label></td>
					<td><input type="text" name="quantity" id="quantity" /></td>
				</tr>
				<tr>
					<td><label for="price">Enter Price : </label></td>
					<td><input type="text" name="price" id="price" /></td>
					<%--<td><textarea style="resize:none" name="description" id="description" value="<%= description%>"></textarea></td> --%>
				</tr>
				<tr>
					<td><label for="orderid">Choose Order ID : </label></td>
					<td><select name="orderid" id="orderid">
							<option value="">Choose any orderid</option>
							<c:forEach var="item" items="${orders }">
								<option value="${item.orderid }">${item.orderid }[
									${item.username} ]</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Update Cart Item details" /></td>
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