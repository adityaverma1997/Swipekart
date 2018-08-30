<%@page import="java.io.File"%>
<%@page import="model.dao.HibernateUtil"%>
<%@page import="model.dao.HibernateViewUtil"%>
<%@page import="model.to.ProductPhoto"%>
<%@page import="operations.Validations"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String message = "";
	if (request.getParameter("photoid") != null) {
		String photoid = request.getParameter("photoid").trim();
		if (Validations.isEmpty(photoid)) {
			message = "Please provide any Photo Id.";
		} else if (Validations.isNumeric(photoid)) {
			int pid = Integer.parseInt(photoid);
			ProductPhoto record = HibernateViewUtil.getProductPhoto(pid);
			if (record != null) {
				String path = "/productphotos/" + record.getPhotoid() + "." + record.getExtname();
				if (HibernateUtil.deleteRecord(record)) {
					message = "Product Photo is removed from the database.";
					path = application.getRealPath(path);
					File file = new File(path);
					if (file.exists()) {
						file.delete();
					}
				} else {
					message = "Product Photo does not removed due to :" + HibernateUtil.getErrormessage();
				}
			} else {
				message = "There is no photo for this id.";
			}
		} else {
			message = "Photo Id must be a number.";
		}
	} else {
		message = "Not enough values passed.";
	}
	request.setAttribute("message", message);
	RequestDispatcher rd = request.getRequestDispatcher("viewproductphoto.jsp");
	rd.forward(request, response);
%>
