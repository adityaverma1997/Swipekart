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
import model.to.SubcategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class UpdateSubcategoryPopup
 */
@WebServlet("/admin/updatesubcategorypopup.do")
public class UpdateSubcategoryPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subcategoryid = request.getParameter("subcategoryid");
		String subcategoryname = request.getParameter("subcategoryname");
		String description = request.getParameter("description");
		String categoryid = request.getParameter("categoryid");
		String message = "";
		if (subcategoryid != null && subcategoryname != null && description != null && categoryid != null) {
			subcategoryid = subcategoryid.trim();
			subcategoryname = subcategoryname.trim();
			description = description.trim();
			categoryid = categoryid.trim();
			if (Validations.isEmpty(subcategoryid) || Validations.isEmpty(subcategoryname)
					|| Validations.isEmpty(description) || Validations.isEmpty(categoryid)) {
				message = "Please fill all boxes";
			} else if ((!Validations.onlyCharacter(categoryid)) || (!Validations.onlyCharacter(subcategoryname))) {
				message = "Please enter character values in categoryid and categoryname boxes";
			} else if (!Validations.isNumeric(subcategoryid)) {
				message = "Please enter numeric value in subcategoryid box.";
			} else {
				CategoryInfo ci=HibernateViewUtil.getCategory(categoryid);
				SubcategoryInfo record = HibernateViewUtil.getSubCategory(Integer.parseInt(subcategoryid));
				if (record != null) {
					record.setSubcategoryid(Integer.parseInt(subcategoryid));
					record.setSubcategoryname(subcategoryname);
					record.setDescription(description);
					record.setCategory(ci);
					if (HibernateUtil.updateRecord(record)) {
						message = "SubCategory record is updated in system database";
					} else {
						if (HibernateUtil.getErrormessage().contains("NonUniqueObjectException")) {
							message = "Please Enter unique values in subcategoryid as it is a primary key.";
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
		RequestDispatcher rd = request.getRequestDispatcher("viewsubcategory.do");
		rd.forward(request, response);

	}

}
