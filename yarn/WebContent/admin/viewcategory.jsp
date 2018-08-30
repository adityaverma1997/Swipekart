<%@page import="java.net.URLEncoder"%>
<%@page import="model.to.CategoryInfo"%>
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
<script type="text/javascript">
	function check(frm) {
		var allchecks = document.getElementsByName("categoryids");
		if (allchecks.length > 0) {
			var count = 0;
			var catids = "";
			for (i = 0; i < allchecks.length; i++) {
				if (allchecks[i].checked) {
					catids += allchecks[i].value + ",";
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
					catids = catids.substring(0, catids.length - 2);
					document.getElementById("catids").value = catids;
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
	function setvalues(categoryid,categoryname,description){
		$("#categoryid").val(categoryid);
		$("#categoryname").val(categoryname);
		$("#description").val(description);
	}
</script>
</head>
<body class="right-sidebar">
	<%@include file="header.jsp"%>
	<!-- Main -->
	<div class="wrapper">
		<div class="container" id="main">
			<h1 align="center"
				style="font-weight: bold; font-size: x-large; color: #FD7272;">Category
				Details</h1>
			<%
				if (request.getAttribute("message") != null) {
					out.println("<h1 align=\"center\">" + request.getAttribute("message") + "</h1>");
					out.println("<br/>");
				}
			%>
			<br />
			<table cellpadding="10" cellspacing="10" align="center" border="1"
				rules="all" style="display: inline;">
				<tr style="font-weight: bold;">
					<th align="left" width="120px">Select</th>
					<th align="left" width="150px">Category ID</th>
					<th align="left" width="210px">Category Name</th>
					<th align="left" width="540px">Description</th>
					<th colspan="2" align="left">Operations</th>
				</tr>
				<%
					//String categoryid = request.getParameter("categoryid1") != null ? request.getParameter("categoryid1") : "";
					if (request.getAttribute("category") != null) {
						List<CategoryInfo> category = (List<CategoryInfo>) request.getAttribute("category");
						for (CategoryInfo ci : category) {
							out.println("<tr>");
							/* Required to edit with textfield on table itself
							if (categoryid.equals(ci.getCategoryid())) {
								out.println("<form method=\"post\" action=\"updatecategory.do\">");
								out.println(
										"<td><input type=\"text\" readonly style=\"width: 140px;\" name=\"categoryid\" value=\""
												+ ci.getCategoryid() + "\"></td>");
								out.println("<td><input type=\"text\" style=\"width: 180px;\" name=\"categoryname\" value=\""
										+ ci.getCategoryname() + "\"></td>");
								out.println("<td><input type=\"text\" style=\"width: 520px;\" name=\"description\" value=\""
										+ ci.getDescription() + "\"></td>");
								out.println(
										"<td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"Update this record\"></td>");
								out.println("</form>");
							} else {*/
							out.println("<td>");
							out.println("<input type=\"checkbox\" name=\"categoryids\" value=" + ci.getCategoryid() + "/>");
							out.println("</td>");
							out.println("<td>" + ci.getCategoryid() + "</td>");
							out.println("<td>" + ci.getCategoryname() + "</td>");
							out.println("<td>" + ci.getDescription() + "</td>");
							String cvalue = URLEncoder.encode(ci.getCategoryid());
							/* Delete single record 
							out.println(
									"<td><a onclick=\"return confirm('Are you sure you want to delete this record?')\" href=\"deletecategory.do?categoryid="
										+ cvalue + "\">Delete this record</a></td>");
							*/
							/* Edit with textfield on table row itself 
							out.println(
									"<td style=\"color: #3498db\";><a href=\"viewcategory.do?categoryid1=" + cvalue + "\">Edit this record</a></td>");
							*/
							out.println("<td style=\"color: #3498db\";>");
							String value ="setvalues('" + ci.getCategoryid() + "','" + ci.getCategoryname() + "','" + ci.getDescription() + "')";
							out.println("<a href=\"#\" data-reveal-id=\"myModal\" onclick=\"" + value + "\">Edit this record</a>");
							out.println("</td>");
							//}
							out.println("</tr>");
						}
					} else {
						out.println("<tr>");
						out.println("<td colspan=\"6\" align=\"center\">There is no record</td>");
						out.println("</tr>");
					}
				%>
			</table>
			<br /> <br /> <br />
			<table>
				<%
					if (request.getAttribute("category") != null) {
						out.println("<tr>");
						out.println("<td width=\"1200px\" align=\"center\">");
						out.println("<form method=\"post\" action=\"deletecategories.do\">");
						out.println("<input type=\"hidden\" name=\"catids\" id=\"catids\"/>");
						out.println("<input type=\"button\" value=\"Delete selected records\" onclick=\"check(this.form)\" />");
						out.println("</form>");
						out.println("</td>");
						out.println("</tr>");
					}
				%>
			</table>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<div id="myModal" class="reveal-modal">
		<h1 align="center" style="color: black;font-weight: bold;font-size: xlarge;">Edit Category
			Details</h1>
		<form method="post" action="updatecategorypopup.do">
			<table cellpadding="10" cellspacing="10" align="center">
				<tr>
					<td><label for="categoryid">Enter Category Id : </label></td>
					<td>
					<%--<input type="hidden" id="srno" name="srno"/>--%>
					<input type="text" name="categoryid" id="categoryid"/></td>
				</tr>
				<tr>
					<td><label for="categoryname">Enter Category Name : </label></td>
					<td><input type="text" name="categoryname" id="categoryname"/></td>
				</tr>
				<tr>
					<td><label for="description">Enter Description : </label></td>
					<td><input type="text" name="description" id="description"/></td>
					<%--<td><textarea style="resize:none" name="description" id="description" value="<%= description%>"></textarea></td> --%>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Update category details" /></td>
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