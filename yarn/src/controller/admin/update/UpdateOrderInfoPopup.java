package controller.admin.update;

import java.io.IOException;
import java.sql.Date;

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
import model.to.LoginInfo;
import model.to.OrderInfo;
import model.to.ProductInfo;
import operations.Validations;

/**
 * Servlet implementation class UpdateOrderInfoPopup
 */
@WebServlet("/admin/updateorderinfopopup.do")
public class UpdateOrderInfoPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		String username = request.getParameter("username");
		String orderdate = request.getParameter("orderdate");
		String shippingdate = request.getParameter("shippingdate");
		String cartid = request.getParameter("cartid");
		String orderstatus = request.getParameter("orderstatus");
		String shippingaddress = request.getParameter("shippingaddress");
		String paymentdetails = request.getParameter("paymentdetails");
		String message = "";
		if (orderid != null && username != null && orderdate != null && shippingdate != null && cartid != null
				&& orderstatus != null && shippingaddress != null && paymentdetails != null) {
			orderid = orderid.trim();
			username = username.trim();
			orderdate = orderdate.trim();
			cartid = cartid.trim();
			shippingdate = shippingdate.trim();
			orderstatus = orderstatus.trim();
			paymentdetails = paymentdetails.trim();
			shippingaddress = shippingaddress.trim();
			if (Validations.isEmpty(orderid) || Validations.isEmpty(username) || Validations.isEmpty(orderdate)
					|| Validations.isEmpty(cartid) || Validations.isEmpty(shippingdate)
					|| Validations.isEmpty(orderstatus) || Validations.isEmpty(shippingaddress)
					|| Validations.isEmpty(paymentdetails)) {
				message = "Please fill all boxes";
			} else if ((!Validations.isNumeric(orderid)) || (!Validations.isNumeric(cartid))) {
				message = "Please enter numeric value in orderid and cartid boxes.";
			} else if ((!Validations.validDate(orderdate)) || (!Validations.validDate(shippingdate))) {
				message = "Invalid date for orderdate or shippingdate.";
			} else if ((!Validations.onlyCharacter(username)) || (!Validations.onlyCharacter(orderstatus)) || (!Validations.onlyCharacter(paymentdetails))) {
				message = "Please enter character value in username, orderstatus, shippingaddress and paymentdetails boxes.";
			} else {
				int oid = Integer.parseInt(orderid);
				Date sdate = Date.valueOf(shippingdate);
				Date odate = Date.valueOf(orderdate);
				LoginInfo li = HibernateViewUtil.getUsers(username);
				CartInfo ci = HibernateViewUtil.getCartInfo(Integer.parseInt(cartid));
				OrderInfo record = HibernateViewUtil.getOrder(Integer.parseInt(orderid));
				if (record != null) {
					record.setOrderid(oid);
					record.setLogin(li);
					record.setCart(ci);
					record.setOrderdate(odate);
					record.setShippingdate(sdate);
					record.setOrderstatus(orderstatus);
					record.setShippingaddress(shippingaddress);
					record.setPaymentdetails(paymentdetails);
					if (HibernateUtil.updateRecord(record)) {
						message = "Order information record is updated in system database";
					} else {
						message = "Updation Error due to : " + HibernateUtil.getErrormessage();
					}
				} else {
					message = "There is no record for updation.";
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("vieworderinfo.do");
		rd.forward(request, response);
	}

}
