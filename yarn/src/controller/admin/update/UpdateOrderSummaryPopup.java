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
import model.to.CartInfo;
import model.to.CartItems;
import model.to.OrderDetails;
import model.to.OrderInfo;
import model.to.ProductInfo;
import operations.Validations;

/**
 * Servlet implementation class UpdateOrderSummaryPopup
 */
@WebServlet("/admin/updateordersummarypopup.do")
public class UpdateOrderSummaryPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String detailid = request.getParameter("detailid");
		String productid = request.getParameter("productid");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String orderid = request.getParameter("orderid");
		String message = "";
		if (detailid != null && productid != null && quantity != null && price != null && orderid != null) {
			detailid = detailid.trim();
			productid = productid.trim();
			quantity = quantity.trim();
			orderid = orderid.trim();
			price = price.trim();
			if (Validations.isEmpty(detailid) || Validations.isEmpty(productid) || Validations.isEmpty(quantity)
					|| Validations.isEmpty(orderid) || Validations.isEmpty(price)) {
				message = "Please fill all boxes";
			} else if ((!Validations.isNumeric(detailid)) || (!Validations.isNumeric(productid))
					|| (!Validations.isNumeric(quantity)) || (!Validations.isNumeric(orderid))) {
				message = "Please enter numeric value in detailid, productid, quantity and orderid boxes.";
			} else if ((!Validations.isFloat(price))) {
				message = "Please enter numeric value in price box.";
			} else {
				float prices = Float.parseFloat(price);
				if (prices > 0) {
					ProductInfo pi = HibernateViewUtil.getProduct(Integer.parseInt(productid));
					OrderInfo oi = HibernateViewUtil.getOrder(Integer.parseInt(orderid));
					OrderDetails record = HibernateViewUtil.getOrderDetail(Integer.parseInt(detailid));
					if (record != null) {
						record.setDetailid(Integer.parseInt(detailid));
						record.setProduct(pi);
						record.setQuantity(Integer.parseInt(quantity));
						record.setPrice(Float.parseFloat(price));
						record.setOrder(oi);
						if (HibernateUtil.updateRecord(record)) {
							message = "Order details record is updated in system database";
						} else {
							message = "Updation Error due to : " + HibernateUtil.getErrormessage();
						}
					} else {
						message = "There is no record for updation.";
					}
				} else {
					message = "Price should be greater than 0.";
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewordersummary.do");
		rd.forward(request, response);
	}

}
