package controller.root;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.dao.HibernateUtil;
import model.dao.HibernateViewUtil;
import model.to.LoginInfo;
import operations.Validations;

/**
 * Servlet implementation class Register
 */
@WebServlet("/login/register.do")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rolename = request.getParameter("rolename");
		String emailid = request.getParameter("emailid");
		String sq = request.getParameter("sq");
		String sans = request.getParameter("sans");
		String message = "";
		boolean invalid = true;
		if (username != null && password != null && rolename != null && emailid != null && sq != null && sans != null) {
			username = username.trim();
			password = password.trim();
			rolename = rolename.trim();
			emailid = emailid.trim();
			sq = sq.trim();
			sans = sans.trim();
			if (Validations.isEmpty(username) || Validations.isEmpty(password) && Validations.isEmpty(rolename)
					|| Validations.isEmpty(emailid) && Validations.isEmpty(sq) || Validations.isEmpty(sans)) {
				message = "Please Fill All Boxes";
			} else if (!Validations.onlyCharacter(username)) {
				message = "Please select character values in username box.";
			} else {
				LoginInfo record = new LoginInfo();
				record.setUsername(username);
				record.setPassword(password);
				record.setRolename(rolename);
				record.setEmailid(emailid);
				record.setSq(sq);
				record.setSans(sans);
				LoginInfo results = HibernateViewUtil.getUsers(username);
				if (results != null) {
					message = "Username you have entered is not available.";
				} else {
					if (HibernateUtil.insertRecord(record)) {
						HttpSession session = request.getSession();
						session.setAttribute("user", username);
						invalid = false;
						//session.setAttribute("lastlogin", results.getLastlogin());
						//results.setLastlogin(new Timestamp(new java.util.Date().getTime()));
						//HibernateUtil.updateRecord(results);
						response.sendRedirect("../user/index.jsp");
					} else {
						message = "Registration failure due to : " + HibernateUtil.getErrormessage();
					}
				}
			}
		} else {
			message = "Not Enough Values are Given";
		}
		if (invalid) {
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
		}
	}

}
