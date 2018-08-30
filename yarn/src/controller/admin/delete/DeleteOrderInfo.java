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
import model.to.OrderInfo;
import operations.Validations;

/**
 * Servlet implementation class DeleteOrderInfo
 */
@WebServlet("/admin/deleteorderinfo.do")
public class DeleteOrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ordids = request.getParameter("ordids");
		String message = "";
		if (ordids != null) {
			ordids = ordids.trim();
			if (Validations.isEmpty(ordids)) {
				message = "Please provide some value for ordids.";
			}else if (ordids.contains(",")) {
				String values[] = ordids.split(",");
				for (String value : values) {
					value=value.trim();
					if (Validations.isNumeric(value)) {
						OrderInfo record = HibernateViewUtil.getOrder(Integer.parseInt(value));
						if (record != null) {
							if (HibernateUtil.deleteRecord(record)) {
								message += "Order Item with Order ID : ( " + value + " ) is removed from the system.<br/>";
							} else {
								message += "Order Item with Order ID : ( " + value + " ) is not removed due to "
										+ HibernateUtil.getErrormessage() + "</br>";
							}
						} else {
							message += "There is no such orderid : " + value + "<br/>";
						}
					} else {
						message += value + "is not a numeric value<br/>";
					}
				}
			} else if (Validations.isNumeric(ordids)) {
				OrderInfo record = HibernateViewUtil.getOrder(Integer.parseInt(ordids));
				if (record != null) {
					if (HibernateUtil.deleteRecord(record)) {
						message += "Order Item with Order ID : ( " + ordids + " ) is removed from the system.";
					} else {
						message += "Order Item with Order ID : ( " + ordids + " ) is not removed due to "
								+ HibernateUtil.getErrormessage();
					}
				} else {
					message += "There is no such orderid : " + ordids;
				}
			} else {
				message = "Please give numeric type orderid.";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("vieworderinfo.do");
		rd.forward(request, response);
	}

}
