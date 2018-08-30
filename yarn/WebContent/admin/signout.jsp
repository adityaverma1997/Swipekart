<%
	session.invalidate();
	response.sendRedirect("../login/login.jsp");
%>