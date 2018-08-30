package controller.admin.update;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HibernateUtil;
import model.dao.HibernateViewUtil;
import model.to.CategoryInfo;
import model.to.CompanyInfo;
import operations.Validations;

/**
 * Servlet implementation class UpdateCompanyPopup
 */
@WebServlet("/admin/updatecompanypopup.do")
public class UpdateCompanyPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String companyid = request.getParameter("companyid");
		String companyname = request.getParameter("companyname");
		String description = request.getParameter("description");
		String message = "";
		if (companyid != null && companyname != null && description != null) {
			companyid = companyid.trim();
			companyname = companyname.trim();
			description = description.trim();
			if (Validations.isEmpty(companyid) || Validations.isEmpty(companyname)
					|| Validations.isEmpty(description)) {
				message = "Please fill all boxes";
			} else if ((!Validations.onlyCharacter(companyid)) || (!Validations.onlyCharacter(companyname))) {
				message = "Please enter character values in all companyid and companyname boxes";
			} else {
				CompanyInfo record = HibernateViewUtil.getCompany(companyid);
				if (record != null) {
					record.setCompanyid(companyid);
					record.setCompanyname(companyname);
					record.setDescription(description);
					if (HibernateUtil.updateRecord(record)) {
						message = "Company record is updated in system database";
					} else {
						if (HibernateUtil.getErrormessage().contains("NonUniqueObjectException")) {
							message = "Please Enter unique values in companyid and companyname as they are primary key and unique key respectively.";
						} else {
							message = "Error : " + HibernateUtil.getErrormessage();
						}
					}
				} else {
					message = "There is no record for updation.";
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewcompany.do");
		rd.forward(request, response);
	}
}
