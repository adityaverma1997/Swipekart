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
import model.to.ProductInfo;
import model.to.SubcategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class UpdateProductPopup
 */
@WebServlet("/admin/updateproductpopup.do")
public class UpdateProductPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productid = request.getParameter("productid");
		String productname = request.getParameter("productname");
		String subcategoryid = request.getParameter("subcategoryid");
		String companyid = request.getParameter("companyid");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String discount = request.getParameter("discount");
		String message = "";
		if (productid != null && productname != null && subcategoryid != null && companyid != null && price != null
				&& description != null && discount != null) {
			productid = productid.trim();
			productname = productname.trim();
			subcategoryid = subcategoryid.trim();
			companyid = companyid.trim();
			price = price.trim();
			description = description.trim();
			discount = discount.trim();
			if (Validations.isEmpty(productid) || Validations.isEmpty(productname) || Validations.isEmpty(subcategoryid)
					|| Validations.isEmpty(companyid) || Validations.isEmpty(description)
					|| Validations.isEmpty(discount) || Validations.isEmpty(price)) {
				message = "Please fill all boxes";
			} else if ((!Validations.onlyCharacter(productname)) || (!Validations.onlyCharacter(companyid))) {
				message = "Please enter character values in productname and companyid boxes";
			} else if ((!Validations.isNumeric(productid)) || (!Validations.isNumeric(subcategoryid))) {
				message = "Please enter numeric value in productid box and subcategory box.";
			} else if ((!Validations.isFloat(price)) || (!Validations.isFloat(discount))) {
				message = "Please enter numeric value in price box and discount box.";
			} else {
				float prices = Float.parseFloat(price);
				float discounts = Float.parseFloat(discount);
				if (prices > 0) {
					if (discounts < 100) {
						CompanyInfo ci = HibernateViewUtil.getCompany(companyid);
						SubcategoryInfo si = HibernateViewUtil.getSubCategory(Integer.parseInt(subcategoryid));
						ProductInfo record = HibernateViewUtil.getProduct(Integer.parseInt(productid));
						if (record != null) {
							record.setProductid(Integer.parseInt(productid));
							record.setProductname(productname);
							record.setSubcategory(si);
							record.setCompany(ci);
							record.setPrice(Float.parseFloat(price));
							record.setDescription(description);
							record.setDiscount(Float.parseFloat(discount));
							if (HibernateUtil.updateRecord(record)) {
								message = "Product record is updated in system database";
							} else {
								if (HibernateUtil.getErrormessage().contains("NonUniqueObjectException")) {
									message = "Please Enter unique values in productid as it is a primary key.";
								} else {
									message = "Error : " + HibernateUtil.getErrormessage();
								}
							}
						} else {
							message = "There is no record for updation.";
						}
					} else {
						message = "Discount should be less than 100.";
					}
				} else {
					message = "Price should be greater than 0.";
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewproduct.do");
		rd.forward(request, response);
	}
}
