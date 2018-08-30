package controller.admin.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HibernateUtil;
import model.dao.HibernateViewUtil;
import model.to.CompanyInfo;
import operations.Validations;

/**
 * Servlet implementation class DeleteCompanies
 */
@WebServlet("/admin/deletecompanies.do")
public class DeleteCompanies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comids = request.getParameter("comids");
		String message = "";
		if (comids != null) {
			comids = comids.trim();
			if (Validations.isEmpty(comids)) {
				message = "Please provide some value for companyid.";
			} else if (comids.contains(",")) {
				String values[] = comids.split(",");
				for (String value : values) {
					/*if(value.contains("/")){
						value=value.replace("/", "");
					}*/
					value=value.trim();
					if (Validations.onlyCharacter(value)) {
						CompanyInfo record = HibernateViewUtil.getCompany(value);
						if (record != null) {
							if (HibernateUtil.deleteRecord(record)) {
								message += "Company with companyid : ( " + value + " ) is removed from the system.<br/>";
							} else {
								message += "Company with companyid : ( " + value + " ) is not removed due to "
										+ HibernateUtil.getErrormessage() + "</br>";
							}
						} else {
							message += "There is no such companyid : " + value + "<br/>";
						}
					} else {
						message += value + "is not a character value<br/>";
					}
				}
			} else if (Validations.onlyCharacter(comids)) {
				CompanyInfo record = HibernateViewUtil.getCompany(comids);
				if (record != null) {
					if (HibernateUtil.deleteRecord(record)) {
						message += "Company with companyid : ( " + comids + " ) is removed from the system.";
					} else {
						message += "Company with companyid : ( " + comids + " ) is not removed due to "
								+ HibernateUtil.getErrormessage();
					}
				} else {
					message += "There is no such companyid : " + comids;
				}
			} else {
				message = "Please give character type companyid.";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewcompany.do");
		rd.forward(request, response);
	}
}
