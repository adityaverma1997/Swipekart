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
import model.to.CompanyInfo;
import model.to.ProductInfo;
import model.to.SubcategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class InsertProductInfo
 */
@WebServlet("/admin/insertproduct.do")
public class InsertProductInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productname = request.getParameter("productname");
		String subcategoryid = request.getParameter("subcategoryid");
		String companyid = request.getParameter("companyid");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String discount = request.getParameter("discount");
		String message = "";
		if (productname != null && description != null && subcategoryid != null && companyid != null && price != null
				&& discount != null) {
			productname = productname.trim();
			subcategoryid = subcategoryid.trim();
			companyid = companyid.trim();
			price = price.trim();
			description = description.trim();
			discount = discount.trim();
			if (Validations.isEmpty(subcategoryid) || Validations.isEmpty(productname)
					|| Validations.isEmpty(description) || Validations.isEmpty(companyid) || Validations.isEmpty(price)
					|| Validations.isEmpty(description)) {
				message = "Please fill all boxes";
			} else if ((!Validations.onlyCharacter(companyid))) {
				message = "Please select character values in companyid boxes.";
			} else if ((!Validations.isFloat(price)) || (!Validations.isFloat(discount))) {
				message = "Please select decimal values in price and discount boxes.";
			} else if (!Validations.isNumeric(subcategoryid)) {
				message = "Please select numeric values in subcategoryid box.";
			} else {
				int scategoryid = Integer.parseInt(subcategoryid);
				float prices = Float.parseFloat(price);
				float discounts = Float.parseFloat(discount);
				if (prices > 0) {
					if (discounts < 100) {
						SubcategoryInfo si = HibernateViewUtil.getSubCategory(scategoryid);
						CompanyInfo ci = HibernateViewUtil.getCompany(companyid);
						if (ci == null || si == null) {
							message = "There is no record for selected subcategory or company.";
						} else {
							ProductInfo record = new ProductInfo();
							record.setProductname(productname);
							record.setSubcategory(si);
							record.setCompany(ci);
							record.setPrice(prices);
							record.setDescription(description);
							record.setDiscount(discounts);
							List<ProductInfo> results = HibernateViewUtil.getAllProducts(productname, si.getSubcategoryid(), ci.getCompanyid());
							if (results != null && results.size() > 0) {
								message = "This product is already assigned to selected categoryid with respect to your selected subcategoryid.";
							} else {
								if (HibernateUtil.insertRecord(record)) {
									message = "Product record is added in system database";
								} else {
									message = "Insertion failure due to : " + HibernateUtil.getErrormessage();
								}
							}
						}
					} else {
						message = "Discount must be less than 100.";
					}
				} else {
					message = "Price must be greater than 0.";
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher("fetchassignproduct.do");
		rd.forward(request, response);
	}
}
