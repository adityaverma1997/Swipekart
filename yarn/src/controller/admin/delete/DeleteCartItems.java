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
import model.to.CartItems;
import model.to.ProductInfo;
import operations.Validations;

/**
 * Servlet implementation class DeleteCartItems
 */
@WebServlet("/admin/deletecartitems.do")
public class DeleteCartItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itmids = request.getParameter("itmids");
		String message = "";
		if (itmids != null) {
			itmids = itmids.trim();
			if (Validations.isEmpty(itmids)) {
				message = "Please provide some value for itemids.";
			}else if (itmids.contains(",")) {
				String values[] = itmids.split(",");
				for (String value : values) {
					value=value.trim();
					if (Validations.isNumeric(value)) {
						CartItems record = HibernateViewUtil.getCartItemInfo(Integer.parseInt(value));
						if (record != null) {
							if (HibernateUtil.deleteRecord(record)) {
								message += "Cart Item with Cart ID : ( " + value + " ) is removed from the system.<br/>";
							} else {
								message += "Cart Item with Cart ID : ( " + value + " ) is not removed due to "
										+ HibernateUtil.getErrormessage() + "</br>";
							}
						} else {
							message += "There is no such cartid : " + value + "<br/>";
						}
					} else {
						message += value + "is not a numeric value<br/>";
					}
				}
			} else if (Validations.isNumeric(itmids)) {
				CartItems record = HibernateViewUtil.getCartItemInfo(Integer.parseInt(itmids));
				if (record != null) {
					if (HibernateUtil.deleteRecord(record)) {
						message += "Cart Item with Cart ID : ( " + itmids + " ) is removed from the system.";
					} else {
						message += "Cart Item with Cart ID : ( " + itmids + " ) is not removed due to "
								+ HibernateUtil.getErrormessage();
					}
				} else {
					message += "There is no such cartid : " + itmids;
				}
			} else {
				message = "Please give numeric type cartid.";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewcartitems.do");
		rd.forward(request, response);
	}

}
