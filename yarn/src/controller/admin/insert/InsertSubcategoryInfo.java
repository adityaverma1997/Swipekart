package controller.admin.insert;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class InsertSubcategoryInfo
 */
@WebServlet("/admin/insertsubcategory.do")
public class InsertSubcategoryInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String subcategoryname = request.getParameter("subcategoryname");
		String description = request.getParameter("description");
		String categoryid = request.getParameter("categoryid");
		String message = "";
		if (subcategoryname != null && description != null && categoryid != null) {
			subcategoryname = subcategoryname.trim();
			description = description.trim();
			categoryid = categoryid.trim();
			if (Validations.isEmpty(categoryid) || Validations.isEmpty(subcategoryname)
					|| Validations.isEmpty(description)) {
				message = "Please fill all boxes";
			} else if ((!Validations.onlyCharacter(categoryid)) || (!Validations.onlyCharacter(subcategoryname))) {
				message = "Please enter character values in subcategoryname box";
			} else {
				CategoryInfo ci = HibernateViewUtil.getCategory(categoryid);
				if (ci == null) {
					message = "There is no record for selected category.";
				} else {
					SubcategoryInfo record = new SubcategoryInfo();
					record.setCategory(ci);
					record.setSubcategoryname(subcategoryname);
					record.setDescription(description);
					List<SubcategoryInfo> results = HibernateViewUtil.getAllSubcategories(subcategoryname,
							ci.getCategoryid());
					if (results != null && results.size() > 0) {
						message="This subcategory is already assigned to selected categoryid.";
					} else {
						if (HibernateUtil.insertRecord(record)) {
							message = "Sub Category record is added in system database";
						} else {
							message = "Insertion failure due to : " + HibernateUtil.getErrormessage();
						}
					}
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher("fetchassigncategory.do");
		rd.forward(request, response);
	}

}
