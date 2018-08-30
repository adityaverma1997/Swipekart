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
		var allchecks = document.getElementsByName("productids");
		if (allchecks.length > 0) {
			var count = 0;
			var prodids = "";
			for (i = 0; i < allchecks.length; i++) {
				if (allchecks[i].checked) {
					prodids += allchecks[i].value + ",";
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
					prodids = prodids.substring(0, prodids.length - 1);
					document.getElementById("prodids").value = prodids;
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
	function setvalues(productid, productname, subcategoryid, companyid, price, description, discount) {
		$("#productid").val(productid);
		$("#productname").val(productname);
		$("#subcategoryid").val(subcategoryid);
		$("#companyid").val(companyid);
		$("#price").val(price);
		$("#description").val(description);
		$("#discount").val(discount);
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
					<th align="left" width="100px">Select</th>
					<th align="left" width="100px">Product ID</th>
					<th align="left" width="160px">Product Name</th>
					<th align="left" width="120px">SubCat. ID</th>
					<th align="left" width="130px">Company ID</th>
					<th align="left" width="100px">Price</th>
					<th align="left" width="550px">Description</th>
					<th align="left" width="100px">Discount</th>
					<th align="left">Operations</th>
				</tr>
				<c:choose>
					<c:when test="${products!=null }">
						<c:forEach var="item" items="${products }">
							<tr>
								<td><input type="checkbox" name="productids"
									value=" ${item.productid } " /></td>
								<td>${item.productid }</td>
								<td>${item.productname }</td>
								<td>${item.subcategory.subcategoryid }[${item.subcategory.subcategoryname}]</td>
								<td>${item.company.companyid }[${item.company.companyname}]</td>
								<td>${item.price }</td>
								<td>${item.description }</td>
								<td>${item.discount }</td>
								<td style="color: #3498db";><a href="#"
									data-reveal-id="myModal"
									onclick="setvalues('${item.productid}','${item.productname}','${item.subcategory.subcategoryid}','${item.company.companyid}','${item.price}','${item.description}','${item.discount }')">Edit
										this record</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8" align="center">There is no record.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<br /> <br /> <br />
			<table>
			<c:if test="${products!=null }">
				<tr>
					<td width="1200px" align="center">
						<form method="post" action="deleteproducts.do">
							<input type="hidden" name="prodids" id="prodids" /> <input
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
			Product Details</h1>
		<form method="post" action="updateproductpopup.do">
			<table cellpadding="10" cellspacing="10" align="center">
				<tr>
					<td><label for="productname">Enter Product Name : </label> <input
						type="hidden" name="productid" id="productid" /></td>
					<td><input type="text" name="productname"
						id="productname" /></td>
				</tr>
				<tr>
					<td><label for="subcategoryid">Choose Sub Category ID
							: </label></td>
					<td><select name="subcategoryid" id="subcategoryid">
							<c:forEach var="item" items="${subcategory }">
								<option value="${item.subcategoryid }">${item.subcategoryid }
									[ ${item.subcategoryname} ]</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label for="companyid">Choose Company ID : </label></td>
					<td><select name="companyid" id="companyid">
							<c:forEach var="item" items="${company }">
								<option value="${item.companyid }">${item.companyid }[
									${item.companyname} ]</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label for="price">Enter Product Price : </label></td>
					<td><input type="text" name="price" id="price"/></td>
				</tr>
				<tr>
					<td><label for="description">Enter Description : </label></td>
					<td><input type="text" name="description" id="description"/></td>
					<%--<td><textarea style="resize:none" name="description" id="description" value="<%= description%>"></textarea></td> --%>
				</tr>
				<tr>
					<td><label for="discount">Enter Product Discount : </label></td>
					<td><input type="text" name="discount" id="discount"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Update product details" /></td>
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