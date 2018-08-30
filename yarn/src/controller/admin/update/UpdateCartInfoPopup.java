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
import operations.Validations;

/**
 * Servlet implementation class UpdateCartInfoPopup
 */
@WebServlet("/admin/updatecartpopup.do")
public class UpdateCartInfoPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cartid = request.getParameter("cartid");
		String username = request.getParameter("username");
		String cartdate = request.getParameter("cartdate");
		String isorder = request.getParameter("isorder");
		String message = "";
		if (cartid != null && username != null && cartdate != null && isorder != null) {
			cartid = cartid.trim();
			username = username.trim();
			cartdate = cartdate.trim();
			isorder = isorder.trim();
			if (Validations.isEmpty(cartid) || Validations.isEmpty(username) || Validations.isEmpty(cartdate)
					|| Validations.isEmpty(isorder)) {
				message = "Please fill all boxes";
			} else if ((!Validations.onlyCharacter(username)) || (!Validations.onlyCharacter(isorder))) {
				message = "Please enter character values in all username and isorder boxes";
			} else if (!Validations.validDate(cartdate)) {
				message = "Invalid cartdate is selected";
			} else if (!Validations.isNumeric(cartid)) {
				message = "Please select numeric value in cartid.";
			} else {
				int cid = Integer.parseInt(cartid);
				Date cdate = Date.valueOf(cartdate);
				CartInfo record = HibernateViewUtil.getCartInfo(cid);
				if (record != null) {
					record.setUsername(username);
					record.setCartdate(cdate);
					record.setIsorder(isorder);
					if (HibernateUtil.updateRecord(record)) {
						message = "Cart record is updated in system database";
					} else {
						message = "Updation Failure due to : " + HibernateUtil.getErrormessage();
					}
				} else {
					message="There is no such record.";
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("viewcart.do");
		rd.forward(request, response);
	}

}
