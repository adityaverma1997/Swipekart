<%@page import="model.dao.HibernateViewUtil"%>
<%@page import="model.to.ProductPhoto"%>
<%@page import="java.util.List"%>
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
<link rel="stylesheet" href="reveal.css" />
<script type="text/javascript" src="jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="jquery.reveal.js"></script>
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<!-- Main -->
	<div class="wrapper">
		<div class="container" id="main">
			<h1 align="center"
				style="font-weight: bold; font-size: x-large; color: #FD7272;">Product
				Photo Details</h1>
			<%
				if (request.getAttribute("message") != null) {
					out.println("<h1 align=\"center\">" + request.getAttribute("message") + "</h1>");
					out.println("<br/>");
				}
			%>
			<br />
			<table cellpadding="10" cellspacing="10" align="center" border="1"
				rules="all">
				<%
					List<ProductPhoto> photos = HibernateViewUtil.getAllProductPhotos();
					if (photos != null && photos.size() > 0) {
						int i = 0;
						for (ProductPhoto photo : photos) {
							if (i == 0) {
								out.println("<tr>");
							}
							out.println("<td align=\"center\">");
							String path = "../productphotos/" + photo.getPhotoid() + "." + photo.getExtname();
							out.println("<img src=\"" + path + "\" width=\"250px\" height=\"200px\"/><br/>");
							out.println(photo.getProduct().getProductname() + "'s Photo<br/>");
							out.println(
									"<a onclick=\"return confirm('Are you sure you want to remove this photo?')\" href=\"deletephoto.jsp?photoid="
											+ photo.getPhotoid() + "\">[Delete this photo]</a><br/>");
							out.println("<a href=\"downloadphoto.do?photoid=" + photo.getPhotoid()
									+ "\">[Download this photo]</a><br/><br/><br/>");
							out.println("</td>");
							i++;
							if (i == 3) {
								out.println("</tr>");
								i = 0;
							}
						}
						if (i != 0) {
							out.println("</tr>");
						}
					} else {
						out.println("<tr><td colspan=\"3\" align=\"center\">There is no photo.</td></tr>");
					}
				%>
			</table>
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