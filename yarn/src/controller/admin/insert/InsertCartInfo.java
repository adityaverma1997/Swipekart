package controller.admin.insert;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HibernateUtil;
import model.to.CartInfo;
import model.to.CategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class InsertCartInfo
 */
@WebServlet("/admin/insertcart.do")
public class InsertCartInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String cartdate = request.getParameter("cartdate");
		String isorder = request.getParameter("isorder");
		String message = "";
		if (username != null && cartdate != null && isorder != null) {
			username = username.trim();
			cartdate = cartdate.trim();
			isorder = isorder.trim();
			if (Validations.isEmpty(username) || Validations.isEmpty(cartdate) || Validations.isEmpty(isorder)) {
				message = "Please fill all boxes";
			} else if ((!Validations.onlyCharacter(username)) || (!Validations.onlyCharacter(isorder))) {
				message = "Please enter character values in all username and isorder boxes";
			} else if (!Validations.validDate(cartdate)) {
				message = "Invalid cartdate is selected";
			} else {
				Date cdate = Date.valueOf(cartdate);
				CartInfo record = new CartInfo();
				record.setUsername(username);
				record.setCartdate(cdate);
				record.setIsorder(isorder);
				if (HibernateUtil.insertRecord(record)) {
					message = "Cart record is added in system database";
				} else {
					message = "Insertion Failure due to : " + HibernateUtil.getErrormessage();
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("addcart.jsp");
		rd.forward(request, response);
	}

}
