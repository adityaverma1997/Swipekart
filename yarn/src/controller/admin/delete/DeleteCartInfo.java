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
import model.to.CartInfo;
import operations.Validations;

/**
 * Servlet implementation class DeleteCartInfo
 */
@WebServlet("/admin/deletecart.do")
public class DeleteCartInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String crtids = request.getParameter("crtids");
		String message = "";
		if (crtids != null) {
			crtids = crtids.trim();
			if (Validations.isEmpty(crtids)) {
				message = "Please provide some value for cartid.";
			} else if (crtids.contains(",")) {
				String values[] = crtids.split(",");
				for (String value : values) {
					value=value.trim();
					if (Validations.isNumeric(value)) {
						CartInfo record = HibernateViewUtil.getCartInfo(Integer.parseInt(value));
						if (record != null) {
							if (HibernateUtil.deleteRecord(record)) {
								message += "Cart details with cartid : ( " + value + " ) is removed from the system.<br/>";
							} else {
								message += "Cart details with cartid : ( " + value + " ) is not removed due to "
										+ HibernateUtil.getErrormessage() + "</br>";
							}
						} else {
							message += "There is no such cartid : " + value + "<br/>";
						}
					} else {
						message += value + "is not a numeric value<br/>";
					}
				}
			} else if (Validations.isNumeric(crtids)) {
				CartInfo record = HibernateViewUtil.getCartInfo(Integer.parseInt(crtids));
				if (record != null) {
					if (HibernateUtil.deleteRecord(record)) {
						message += "Cart details with cartid : ( " + crtids + " ) is removed from the system.";
					} else {
						message += "Cart details with cartid : ( " + crtids + " ) is not removed due to "
								+ HibernateUtil.getErrormessage();
					}
				} else {
					message += "There is no such cartid : " + crtids;
				}
			} else {
				message = "Please give character type cartid.";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher rd=request.getRequestDispatcher("viewcart.do");
		rd.forward(request, response);
	}

}
