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
import model.to.CartInfo;
import model.to.CartItems;
import model.to.OrderDetails;
import model.to.OrderInfo;
import model.to.ProductInfo;
import operations.Validations;

/**
 * Servlet implementation class InsertOrderSummary
 */
@WebServlet("/admin/insertordersummary.do")
public class InsertOrderSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productid = request.getParameter("productid");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String orderid = request.getParameter("orderid");
		String message = "";
		if (productid != null && quantity != null && price != null && orderid != null) {
			productid = productid.trim();
			quantity = quantity.trim();
			price = price.trim();
			orderid = orderid.trim();
			if (Validations.isEmpty(productid) || Validations.isEmpty(quantity) || Validations.isEmpty(price)
					|| Validations.isEmpty(orderid)) {
				message = "Please fill all boxes";
			} else if (!Validations.isFloat(price)) {
				message = "Please select decimal values in price box.";
			} else if ((!Validations.isNumeric(productid)) || (!Validations.isNumeric(quantity))
					|| (!Validations.isNumeric(orderid))) {
				message = "Please select numeric values in productid, quantity and orderid boxes.";
			} else {
				int prodid = Integer.parseInt(productid);
				int quant = Integer.parseInt(quantity);
				float prices = Float.parseFloat(price);
				int cid = Integer.parseInt(orderid);
				if (prices > 0) {
					ProductInfo pi = HibernateViewUtil.getProduct(prodid);
					OrderInfo oi = HibernateViewUtil.getOrder(Integer.parseInt(orderid));
					if (pi == null || oi == null) {
						message = "There is no record for selected product or order item.";
					} else {
						OrderDetails record = new OrderDetails();
						record.setProduct(pi);
						record.setQuantity(Integer.parseInt(quantity));
						record.setPrice(prices);
						record.setOrder(oi);
						List<OrderDetails> results = HibernateViewUtil.getAllOrderDetails(pi.getProductid(),
								Integer.parseInt(quantity), oi.getOrderid());
						if (results != null && results.size() > 0) {
							message = "This productid is already assigned having selected orderid and quantity.";
						} else {
							if (HibernateUtil.insertRecord(record)) {
								message = "Order details is added in system database";
							} else {
								message = "Insertion failure due to : " + HibernateUtil.getErrormessage();
							}
						}
					}
				} else {
					message = "Price must be greater than 0.";
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("fetchordersummary.do");
		rd.forward(request, response);
	}

}
