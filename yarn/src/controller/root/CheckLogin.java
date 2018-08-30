package controller.root;

import java.io.IOException;
import java.sql.Timestamp;

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
 * Servlet implementation class CheckLogin
 */
@WebServlet("/login/checklogin.do")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String message = "";
		boolean invalid = true;
		if (username != null && password != null) {
			if (Validations.isEmpty(username) || Validations.isEmpty(password)) {
				message = "Please Fill All Boxes";
			} else {
				LoginInfo login = HibernateViewUtil.getUsers(username);
				if (login != null) {
					if (login.getPassword().equals(password)) {
						if (login.getRolename() != null) {
							if (login.getRolename().equalsIgnoreCase("admin")) {
								HttpSession session = request.getSession();
								session.setAttribute("admin", username);
								invalid = false;
								session.setAttribute("lastlogin", login.getLastlogin());
								login.setLastlogin(new Timestamp(new java.util.Date().getTime()));
								HibernateUtil.updateRecord(login);
								response.sendRedirect("../admin/index.jsp");
							} else if (login.getRolename().equalsIgnoreCase("user")) {
								HttpSession session = request.getSession();
								session.setAttribute("user", username);
								invalid = false;
								session.setAttribute("lastlogin", login.getLastlogin());
								login.setLastlogin(new Timestamp(new java.util.Date().getTime()));
								HibernateUtil.updateRecord(login);
								response.sendRedirect("../user/index.jsp");
							} else {
								message = "Insufficient Privileges to this user contact administrator";
							}
						} else {
							message = "Role Name is Not Defined";
						}
					} else {
						message = "Invalid User Name and Password";
					}
				} else {
					message = "Invalid User Name and Password";
				}
			}
		} else {
			message = "Not Enough Values are Given";
		}
		if (invalid) {
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

}
