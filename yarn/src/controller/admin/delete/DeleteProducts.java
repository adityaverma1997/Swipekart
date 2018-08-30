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
import model.to.ProductInfo;
import model.to.SubcategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class DeleteProducts
 */
@WebServlet("/admin/deleteproducts.do")
public class DeleteProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodids = request.getParameter("prodids");
		String message = "";
		if (prodids != null) {
			prodids = prodids.trim();
			if (Validations.isEmpty(prodids)) {
				message = "Please provide some value for productid.";
			}else if (prodids.contains(",")) {
				String values[] = prodids.split(",");
				for (String value : values) {
					value=value.trim();
					if (Validations.isNumeric(value)) {
						ProductInfo record = HibernateViewUtil.getProduct(Integer.parseInt(value));
						if (record != null) {
							if (HibernateUtil.deleteRecord(record)) {
								message += "Product with productid : ( " + value + " ) is removed from the system.<br/>";
							} else {
								message += "Product with productid : ( " + value + " ) is not removed due to "
										+ HibernateUtil.getErrormessage() + "</br>";
							}
						} else {
							message += "There is no such productid : " + value + "<br/>";
						}
					} else {
						message += value + "is not a numeric value<br/>";
					}
				}
			} else if (Validations.isNumeric(prodids)) {
				ProductInfo record = HibernateViewUtil.getProduct(Integer.parseInt(prodids));
				if (record != null) {
					if (HibernateUtil.deleteRecord(record)) {
						message += "Product with productid : ( " + prodids + " ) is removed from the system.";
					} else {
						message += "Product with productid : ( " + prodids + " ) is not removed due to "
								+ HibernateUtil.getErrormessage();
					}
				} else {
					message += "There is no such productid : " + prodids;
				}
			} else {
				message = "Please give numeric type productid.";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewproduct.do");
		rd.forward(request, response);
	}
}
