package controller.root;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HibernateUtil;
import model.dao.HibernateViewUtil;
import model.to.LoginInfo;
import operations.RandomString;
import operations.Utility;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/login/forgot.do")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		String username = request.getParameter("username");
		String sq = request.getParameter("sq");
		String sans = request.getParameter("sans");
		if (username != null && sq != null && sans != null) {
			LoginInfo record = HibernateViewUtil.getUsers(username);
			if (record != null) {
				if (record.getSq().equals(sq) && record.getSans().equals(sans)) {
					String np = new String(RandomString.generatePswd(8, 16, 2, 2, 2));
					record.setPassword(np);
					if (HibernateUtil.updateRecord(record)) {
						message = "Your Password for an Aasha account has been reset.";
						message += "Your New Password is : " + np;
						if (Utility.SendEmail(record.getEmailid(), "Password Reset", message)) {
							message = "Your new password has been sent to your registered emailid with us.";
						}
					} else {
						message = "Your Password can't be reset.";
					}
				} else {
					message = "Invalid Username / Security Question / Security Answer.";
				}
			} else {
				message = "Invalid Username / Security Question / Security Answer.";
			}
		} else {
			message = "Provide all values";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("forgotpassword.jsp");
		rd.forward(request, response);
	}

}
