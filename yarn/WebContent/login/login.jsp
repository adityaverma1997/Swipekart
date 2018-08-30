
<%
	if (session.getAttribute("user") != null) {
		response.sendRedirect("../user/index.jsp");
	} else if (session.getAttribute("admin") != null) {
		response.sendRedirect("../admin/index.jsp");
	}
	response.setHeader("Cache-Control", "no-cache");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up/Sign In</title>
</head>
<body bgcolor="#f08c89">

</body>
</html> -->
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Login form</title>



<link rel="stylesheet" href="css/style.css">
<!-- <script type="text/javascript">
	document.onreadystatechange=function(){
		var state=document.readyState
		if(state == 'interactive'){
			//document.getElementById('contents').style.opacity="0.5"
			document.getElementById('contents').style.visibility="hidden";
			document.getElementById('loading').style.visibility="visible";
		}else if(state == "complete"){
			setTimeout(function(){
				document.getElementById('interactive');
				document.getElementById('loading').style.visibility="hidden";
				document.getElementById('contents').style.opacity="1.0"
				document.getElementById('contents').style.visibility="visible";
			}, 1000)
		}
	}
</script> -->

</head>

<body>

	<form method="post" action="checklogin.do">
		<div class="panel-lite" id="contents">
		<!-- <div id="loading"></div> -->
			<div class="thumbur">
				<div class="icon-lock"></div>
			</div>
			<h4>Login</h4>
			<div class="form-group">
				<input class="form-control" required="required" id="username"
					name="username" /> <label class="form-label" for="username">Username
				</label>
			</div>
			<div class="form-group">
				<input class="form-control" type="password" required="required"
					id="password" name="password" /> <label class="form-label"
					for="password">Password</label>
			</div><br />
			<button class="floating-btn">
				<i class="icon-arrow"></i>
			</button>
			<a href="forgotpassword.jsp" style="display: block;text-align: center;">Forgot Password ? </a>
			<br />
			<div class="line"></div><br/><br/>
			<div class="form-group">
				<label style="display: block; text-align: center;">Don't
					have an Aasha account?</label> <a href="register.jsp" style="display: block;text-align: center;">REGISTER </a>
			</div>
		</div>
	</form>
	<br />
	<br />
	<h2 align="center" style="color: white">${message }</h2>


</body>

</html>
