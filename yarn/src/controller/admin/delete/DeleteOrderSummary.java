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
import model.to.OrderDetails;
import operations.Validations;

/**
 * Servlet implementation class DeleteOrderSummary
 */
@WebServlet("/admin/deleteordersummary.do")
public class DeleteOrderSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dtids = request.getParameter("dtids");
		String message = "";
		if (dtids != null) {
			dtids = dtids.trim();
			if (Validations.isEmpty(dtids)) {
				message = "Please provide some value for detailid.";
			}else if (dtids.contains(",")) {
				String values[] = dtids.split(",");
				for (String value : values) {
					value=value.trim();
					if (Validations.isNumeric(value)) {
						OrderDetails record = HibernateViewUtil.getOrderDetail(Integer.parseInt(value));
						if (record != null) {
							if (HibernateUtil.deleteRecord(record)) {
								message += "Order detail with Detail ID : ( " + value + " ) is removed from the system.<br/>";
							} else {
								message += "Order detail with Detail ID : ( " + value + " ) is not removed due to "
										+ HibernateUtil.getErrormessage() + "</br>";
							}
						} else {
							message += "There is no such detailid : " + value + "<br/>";
						}
					} else {
						message += value + "is not a numeric value<br/>";
					}
				}
			} else if (Validations.isNumeric(dtids)) {
				OrderDetails record = HibernateViewUtil.getOrderDetail(Integer.parseInt(dtids));
				if (record != null) {
					if (HibernateUtil.deleteRecord(record)) {
						message += "Order detail with Detail ID : ( " + dtids + " ) is removed from the system.";
					} else {
						message += "Order detail with Detail ID : ( " + dtids + " ) is not removed due to "
								+ HibernateUtil.getErrormessage();
					}
				} else {
					message += "There is no such detailid : " + dtids;
				}
			} else {
				message = "Please give numeric type detailid.";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewordersummary.do");
		rd.forward(request, response);
	}

}
