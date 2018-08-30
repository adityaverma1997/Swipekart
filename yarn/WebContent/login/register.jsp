<%@page import="operations.QuestionList"%>
<%@page import="java.util.List"%>
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
<title>Register form</title>
<%--<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#username").blur(function() {
			var address = "checkuser.jsp?username=" + $(this).val();
			$.ajax({
				url : address,
				success : function(result) {
					$("#message").html(result);
				}
			});
		});
	});
</script> --%>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<form method="post" action="register.do">
		<div class="panel-lite">
			<div class="thumbur">
				<div class="icon-lock"></div>
			</div>
			<h4>Create Aasha Account</h4>
			<br />
			<div class="form-group">
				<input class="form-control" required="required" id="username"
					name="username" /> <label class="form-label" for="username">Username
				</label> <%--<span style="color: red" id="message"></span> --%>
			</div>
			<div class="form-group">
				<input class="form-control" type="password" required="required"
					id="password" name="password" /> <label class="form-label"
					for="password">Password</label>
			</div>
			<div class="form-group">
				<input class="form-control" required="required" id="emailid"
					name="emailid" /> <label class="form-label" for="emailid">Email
					Address </label> <input type="hidden" name="rolename" id="rolename"
					value="user" />
			</div>
			<div class="form-group">
				<!-- <input class="form-control" required="required" id="username"
					name="username" /> <label class="form-label" for="username">Username
				</label> -->
				<select class="form-control"
					style="color: #666; font-size: 15px; font-family: 'Roboto', sans-serif;"
					id="sq" name="sq">
					<!-- <option value="">Choose Security Question</option> -->
					<%
						List<String> questions = QuestionList.getQuestions();
						for (String question : questions) {
							out.println("<option value=\"" + question + "\">" + question + "</option>");
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<input class="form-control" type="password" required="required"
					id="sans" name="sans" /> <label class="form-label" for="sans">Security
					Answer</label>
			</div>
			<br /> <br />
			<button class="floating-btn">
				<i class="icon-arrow"></i>
			</button>
			<br /> <br /> <br /> <br />
			<div class="line"></div>
			<br />
			<div class="form-group">
				<label style="display: block; text-align: center;">Already
					have an Aasha account?</label> <a href="login.jsp"
					style="display: block; text-align: center;">Signin </a>
			</div>
		</div>
	</form>
	<br />
	<br />
	<h2 align="center" style="color: white">${message }</h2>


</body>

</html>
