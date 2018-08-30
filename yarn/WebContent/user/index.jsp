
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("../login/login.jsp");
	}
	response.setHeader("Cache-Control", "no-cache");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
%>
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
<style type="text/css">
	#myBtn {
    display: none; /* Hidden by default */
    position: fixed; /* Fixed/sticky position */
    bottom: 20px; /* Place the button at the bottom of the page */
    left: 30px; /* Place the button 30px from the right */
    z-index: 99; /* Make sure it does not overlap */
    border: 2px solid #ff7979; /* Remove borders */
    outline: none; /* Remove outline */
    background-color: #ff7979; /* Set a background color */
    color: white; /* Text color */
    cursor: pointer; /* Add a mouse pointer on hover */
    padding: 15px; /* Some padding */
    border-radius: 10px; /* Rounded corners */
    font-size: 18px; /* Increase font size */
}

#myBtn:hover {
    background-color: #fff; /* Add a dark-grey background on hover */
	color: #ff7979;
	border-color:#ff7979;
}
</style>

<script type="text/javascript">
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("myBtn").style.display = "block";
    } else {
        document.getElementById("myBtn").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

</script>
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<!-- Main -->
	<div class="wrapper">
		<div
			style="background-color: #fff; height: 50px; width: 100%; padding-top: 0.5em;">
			<h5 style="font-style: italic; float: left; padding-left: 20px;">Welcome
				User!!!</h5>
			<%
				if (session.getAttribute("lastlogin") != null) {
					out.print("<h5 style=\"float:right;padding-right:30px\">Your Last Visit : "
							+ session.getAttribute("lastlogin") + "</h5><br/>");
				} else {
					out.print(
							"<h5 style=\"float:right;padding-right:30px\">Welcome first time in our web application.</h5><br/>");
				}
			%>
		</div>
		<div class="container" id="main" style="height: 1620px">
			<%@include file="../admin/slideshow.jsp"%>
			<br /> <br /> <br />
			 <button onclick="topFunction()" id="myBtn" title="Go to top">/\</button> 
			<div class="sidebox">
				<a href="#" >
					<img style="padding: 12px 0 12px 12px;" alt="Amazon Echo" src="../Photos_Mainpage/echo1.jpg">
				</a>
				<div class="line"></div>
				<a href="#" >
					<img style="padding: 12px 0 12px 12px;" alt="Amazon Delivery" src="../Photos_Mainpage/del.jpg">
				</a>
				<div class="line"></div>
				<a href="#" >
					<img style="padding: 12px 0 12px 12px;" alt="Amazon Delivery" src="../Photos_Mainpage/jack.jpg">
				</a>
			</div>
			 <div class="row"  style="padding-left: 20px;">
			 	<h4>Today's Deals</h4>
				<div class="column" style="clear: left;padding-left: 5%;">
					<a href="#"> <img src="../Photos_Mainpage/lap.jpg" alt="Laptops" height="250px" width="400px"> </a>
				</div>
				<div class="column" style="float: right;padding-right: 55%;">
					<a href="#"> <img src="../Photos_Mainpage/wp.JPG" alt="Water Purifier" height="250px" width="400px"> </a>
				</div>
			</div> 
			<br/><br/><br/><br/>
			<div class="line" style="width: 80%;"></div>
			<div class="row"  style="padding-left: 20px;">
			 	<h4>Offers For You</h4>
				<div class="column" style="clear: left;padding-left: 5%;padding-right: 24%;">
					<a href="#"> <img src="../productphotos/69.jpg" alt="Mi Band" height="200px" width="300px"> </a>
				</div>
				<div class="column">
					<a href="#"> <img src="../productphotos/70.jpg" alt="IFB Oven" height="200px" width="300px"> </a>
				</div>
				<div class="column" style="padding-left: 8%;">
					<a href="#"> <img src="../productphotos/71.jpeg" alt="Apple Watch" height="200px" width="200px"> </a>
				</div>
			</div>
			<br/><br/><br/><br/><br/>
			<div class="line" style="width: 80%;"></div>
			<div class="row"  style="padding-left: 20px;">
			 	<h4>Trending Offers</h4>
				<div class="column" style="clear: left;padding-left: 5%;padding-right: 20%;">
					<a href="#"> <img src="../productphotos/73.jpeg" alt="Mi Band" height="200px" width="200px"> </a>
				</div>
				<div class="column">
					<a href="#"> <img src="../productphotos/74.jpeg" alt="Water Purifier" height="200px" width="280px"> </a>
				</div>
				<div class="column" style="padding-left: 8%;">
					<a href="#"> <img src="../productphotos/72.jpeg" alt="Water Purifier" height="200px" width="200px"> </a>
				</div>
			</div>
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