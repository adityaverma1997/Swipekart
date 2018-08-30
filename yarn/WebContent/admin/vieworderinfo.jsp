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
		var allchecks = document.getElementsByName("orderids");
		if (allchecks.length > 0) {
			var count = 0;
			var ordids = "";
			for (i = 0; i < allchecks.length; i++) {
				if (allchecks[i].checked) {
					ordids += allchecks[i].value + ",";
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
					ordids = ordids.substring(0, ordids.length - 1);
					document.getElementById("ordids").value = ordids;
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
	function setvalues(orderid, username, cartid, orderdate, shippingdate,
			orderstatus, shippingaddress, paymentdetails) {
		$("#orderid").val(orderid);
		$("#username").val(username);
		$("#cartid").val(cartid);
		$("#orderstatus").val(orderstatus);
		$("#shippingaddress").val(shippingaddress);
		$("#paymentdetails").val(paymentdetails);
		var values = orderdate.split("-");
		var yr = parseInt(values[0]);
		var mt = parseInt(values[1]);
		var dt = parseInt(values[2]);
		$("#orderdate_Year_ID").val(yr);
		$("#orderdate_Year_ID").keyup();
		$("#orderdate_Day_ID").val(dt);
		$("#orderdate_Day_ID").change();
		$("#orderdate_Month_ID").val(mt - 1);
		$("#orderdate_Month_ID").change();
		if (shippingdate != "") {
			values = shippingdate.split("-");
			yr = parseInt(values[0]);
			mt = parseInt(values[1]);
			dt = parseInt(values[2]);
			$("#shippingdate_Year_ID").val(yr);
			$("#shippingdate_Year_ID").keyup();
			$("#shippingdate_Day_ID").val(dt);
			$("#shippingdate_Day_ID").change();
			$("#shippingdate_Month_ID").val(mt - 1);
			$("#shippingdate_Month_ID").change();
		}
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
					<th align="left" width="130px">Order ID</th>
					<th align="left" width="180px">User Name</th>
					<th align="left" width="140px">Cart ID</th>
					<th align="left" width="160px">Order Date</th>
					<th align="left" width="160px">Shipping Date</th>
					<th align="left" width="180px">Order Status</th>
					<th align="left" width="200px">Shipping Address</th>
					<th align="left" width="200px">Payment Details</th>
					<th align="left">Operations</th>
				</tr>
				<c:choose>
					<c:when test="${orders!=null }">
						<c:forEach var="item" items="${orders }">
							<tr>
								<td><input type="checkbox" name="orderids"
									value=" ${item.orderid } " /></td>
								<td>${item.orderid }</td>
								<td>${item.users.username }[${item.users.rolename}]</td>
								<td>${item.cart.cartid }[${item.cart.username}]</td>
								<td>${item.orderdate }</td>
								<td>${item.shippingdate }</td>
								<td>${item.orderstatus }</td>
								<td>${item.shippingaddress }</td>
								<td>${item.paymentdetails }</td>
								<td style="color: #3498db";><a href="#"
									data-reveal-id="myModal"
									onclick="setvalues('${item.orderid}','${item.users.username}','${item.cart.cartid}','${item.orderdate}','${item.shippingdate}','${item.orderstatus}','${item.shippingaddress}','${item.paymentdetails}')">Edit
										this record</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="9" align="center">There is no record.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<br /> <br /> <br />
			<table>
			<c:if test="${orders!=null }">
				<tr>
					<td width="1200px" align="center">
						<form method="post" action="deleteorderinfo.do">
							<input type="hidden" name="ordids" id="ordids" /> <input
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
			Order Item Details</h1>
		<form method="post" action="updateorderinfopopup.do">
			<table cellpadding="10" cellspacing="10" align="center">
				<tr>
					<td><label for="username">Choose User Name : </label></td>
					<td><input type="hidden" name="orderid" id="orderid" /></td>
					<td><select name="username" id="username">
							<option value="">Choose any username</option>
							<c:forEach var="item" items="${users }">
								<option value="${item.username }">${item.username }[
									${item.rolename} ]</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label for="cartid">Choose Cart ID : </label></td>
					<td><select name="cartid" id="cartid">
							<option value="">Choose any cartid</option>
							<c:forEach var="item" items="${cart }">
								<option value="${item.cartid }">${item.cartid }[
									${item.username} ]</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Choose Order date :</td>
					<td><script type="text/javascript">
						DateInput("orderdate", true, "YYYY-MM-DD")
					</script></td>
				</tr>
				<tr>
					<td>Choose Shipping date :</td>
					<td><script type="text/javascript">
						DateInput("shippingdate", true, "YYYY-MM-DD")
					</script></td>
				</tr>
				<tr>
					<td><label for="orderstatus">Enter Order Status : </label></td>
					<td><input type="text" name="orderstatus" id="orderstatus" /></td>
				</tr>
				<tr>
					<td><label for="shippingaddress">Enter Shipping
							Address : </label></td>
					<td><input type="text" name="shippingaddress"
						id="shippingaddress" /></td>
				</tr>
				<tr>
					<td><label for="paymentdetails">Enter Payment Details
							: </label></td>
					<td><input type="text" name="paymentdetails"
						id="paymentdetails" /></td>
					<%--<td><textarea style="resize:none" name="description" id="description" value="<%= description%>"></textarea></td> --%>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Update Order details" /></td>
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