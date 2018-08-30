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
import model.to.CategoryInfo;
import model.to.SubcategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class DeleteSubCategories
 */
@WebServlet("/admin/deletesubcategories.do")
public class DeleteSubCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scatids = request.getParameter("scatids");
		String message = "";
		if (scatids != null) {
			scatids = scatids.trim();
			if (Validations.isEmpty(scatids)) {
				message = "Please provide some value for subcategoryid.";
			}else if (scatids.contains(",")) {
				String values[] = scatids.split(",");
				for (String value : values) {
					value=value.trim();
					if (Validations.isNumeric(value)) {
						SubcategoryInfo record = HibernateViewUtil.getSubCategory(Integer.parseInt(value));
						if (record != null) {
							if (HibernateUtil.deleteRecord(record)) {
								message += "SubCategory with subcategoryid : ( " + value + " ) is removed from the system.<br/>";
							} else {
								message += "SubCategory with subcategoryid : ( " + value + " ) is not removed due to "
										+ HibernateUtil.getErrormessage() + "</br>";
							}
						} else {
							message += "There is no such subcategoryid : " + value + "<br/>";
						}
					} else {
						message += value + "is not a numeric value<br/>";
					}
				}
			} else if (Validations.isNumeric(scatids)) {
				SubcategoryInfo record = HibernateViewUtil.getSubCategory(Integer.parseInt(scatids));
				if (record != null) {
					if (HibernateUtil.deleteRecord(record)) {
						message += "SubCategory with subcategoryid : ( " + scatids + " ) is removed from the system.";
					} else {
						message += "SubCategory with subcategoryid : ( " + scatids + " ) is not removed due to "
								+ HibernateUtil.getErrormessage();
					}
				} else {
					message += "There is no such subcategoryid : " + scatids;
				}
			} else {
				message = "Please give numeric type subcategoryid.";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewsubcategory.do");
		rd.forward(request, response);
	}
}
