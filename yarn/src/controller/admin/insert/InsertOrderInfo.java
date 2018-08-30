package controller.admin.insert;

import java.io.IOException;
import java.sql.Date;
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
import model.to.LoginInfo;
import model.to.OrderInfo;
import model.to.ProductInfo;
import model.to.SubcategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class InsertOrderInfo
 */
@WebServlet("/admin/insertorderinfo.do")
public class InsertOrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String cartid = request.getParameter("cartid");
		String orderdate = request.getParameter("orderdate");
		String shippingdate = request.getParameter("shippingdate");
		String orderstatus = request.getParameter("orderstatus");
		String shippingaddress = request.getParameter("shippingaddress");
		String paymentdetails = request.getParameter("paymentdetails");
		String message = "";
		if (username != null && cartid != null && orderdate != null && shippingdate != null && orderstatus != null
				&& shippingaddress != null && paymentdetails != null) {
			username = username.trim();
			cartid = cartid.trim();
			orderdate = orderdate.trim();
			shippingdate = shippingdate.trim();
			orderstatus = orderstatus.trim();
			shippingaddress = shippingaddress.trim();
			paymentdetails = paymentdetails.trim();
			if (Validations.isEmpty(username) || Validations.isEmpty(cartid) || Validations.isEmpty(orderdate)
					|| Validations.isEmpty(shippingdate) || Validations.isEmpty(orderstatus)
					|| Validations.isEmpty(shippingaddress) || Validations.isEmpty(paymentdetails)) {
				message = "Please fill all boxes";
			} else if ((!Validations.onlyCharacter(username)) || (!Validations.onlyCharacter(orderstatus)) || (!Validations.onlyCharacter(paymentdetails))) {
				message = "Please enter character values in all username, orderstatus, shippingaddress and paymentdetails boxes";
			} else if (Validations.validDate(orderdate) || Validations.validDate(shippingdate)) {
				message = "Invalid orderdate or shippingdate is selected";
			} else if (Validations.isNumeric(cartid)) {
				message = "Please enter numeric value in cartid box.";
			} else {
				Date odate = Date.valueOf(orderdate);
				Date sdate = Date.valueOf(shippingdate);
				int cid = Integer.parseInt(cartid);
				LoginInfo li = HibernateViewUtil.getUsers(username);
				CartInfo ci = HibernateViewUtil.getCartInfo(cid);
				if (li == null || ci == null) {
					message = "There is no record for selected user or cart item.";
				} else {
					OrderInfo record = new OrderInfo();
					record.setLogin(li);
					record.setCart(ci);
					record.setOrderdate(odate);
					record.setShippingdate(sdate);
					record.setOrderstatus(orderstatus);
					record.setShippingaddress(shippingaddress);
					record.setPaymentdetails(paymentdetails);
					List<OrderInfo> results = HibernateViewUtil.getAllOrders(li.getUsername(), ci.getCartid());
					if (results != null && results.size() > 0) {
						message = "This username with selected cartid is already assigned.";
					} else {
						if (HibernateUtil.insertRecord(record)) {
							message = "Order details record is added in system database";
						} else {
							message = "Insertion Failure due to : " + HibernateUtil.getErrormessage();
						}
					}
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("fetchorderinfo.do");
		rd.forward(request, response);
	}

}
