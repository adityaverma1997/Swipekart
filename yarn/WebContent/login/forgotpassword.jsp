<%@page import="java.util.List"%>
<%@page import="operations.QuestionList"%>
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
<title>Forgot Password</title>



<link rel="stylesheet" href="css/style.css">


</head>

<body>

	<form method="post" action="forgot.do">
		<div class="panel-lite">
			<div class="thumbur">
				<div class="icon-lock"></div>
			</div>
			<h4>Forgot Password</h4>
			<div class="form-group">
				<input class="form-control" required="required" id="username"
					name="username" /> <label class="form-label" for="username">Username
				</label>
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
			</div><br/>
			<a href="login.jsp">Back To Login</a>
			<button class="floating-btn" style="bottom: 30px">
				<i class="icon-arrow"></i>
			</button>
		</div>
	</form>
	<h2 align="center" style="color: white">${message }</h2>


</body>

</html>
