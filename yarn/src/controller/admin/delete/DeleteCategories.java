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
import operations.Validations;
import sun.net.www.http.Hurryable;

/**
 * Servlet implementation class DeleteCategories
 */
@WebServlet("/admin/deletecategories.do")
public class DeleteCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String catids = request.getParameter("catids");
		String message = "";
		if (catids != null) {
			catids = catids.trim();
			if (Validations.isEmpty(catids)) {
				message = "Please provide some value for categoryid.";
			} else if (catids.contains(",")) {
				String values[] = catids.split(",");
				for (String value : values) {
					if(value.contains("/")){
						value=value.replace("/", "");
					}
					if (Validations.onlyCharacter(value)) {
						CategoryInfo record = HibernateViewUtil.getCategory(value);
						if (record != null) {
							if (HibernateUtil.deleteRecord(record)) {
								message += "Category with categoryid : ( " + value + " ) is removed from the system.<br/>";
							} else {
								message += "Category with categoryid : ( " + value + " ) is not removed due to "
										+ HibernateUtil.getErrormessage() + "</br>";
							}
						} else {
							message += "There is no such categoryid : " + value + "<br/>";
						}
					} else {
						message += value + "is not a character value<br/>";
					}
				}
			} else if (Validations.onlyCharacter(catids)) {
				CategoryInfo record = HibernateViewUtil.getCategory(catids);
				if (record != null) {
					if (HibernateUtil.deleteRecord(record)) {
						message += "Category with categoryid : ( " + catids + " ) is removed from the system.";
					} else {
						message += "Category with categoryid : ( " + catids + " ) is not removed due to "
								+ HibernateUtil.getErrormessage();
					}
				} else {
					message += "There is no such categoryid : " + catids;
				}
			} else {
				message = "Please give character type categoryid.";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewcategory.do");
		rd.forward(request, response);
	}
}
