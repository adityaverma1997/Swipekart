<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<div
			style="background-color: #fff; height: 50px; width: 100%; padding-top: 2em;">
			<h6 style="font-style: italic; float: left; padding-left: 20px;">Welcome
				Admin!!!</h6>
			<%
				if (session.getAttribute("lastlogin") != null) {
					out.println("<h6 style=\"float:right;padding-right:30px\">Your Last Visit : "
							+ session.getAttribute("lastlogin") + "</h6><br/>");
				} else {
					out.println(
							"<h6 style=\"float:right;padding-right:30px\">Welcome first time in our web application.</h6><br/>");
				}
			%>
		</div>
		<div class="container" id="main">
			<div class="slideshow"><%@include file="slideshow.jsp"%></div>
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