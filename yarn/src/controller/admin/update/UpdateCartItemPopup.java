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
import model.to.CompanyInfo;
import model.to.ProductInfo;
import model.to.SubcategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class UpdateCartItemPopup
 */
@WebServlet("/admin/updatecartitempopup.do")
public class UpdateCartItemPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemid = request.getParameter("itemid");
		String productid = request.getParameter("productid");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String cartid = request.getParameter("cartid");
		String message = "";
		if (itemid != null && productid != null && quantity != null && price != null && cartid != null) {
			itemid = itemid.trim();
			productid = productid.trim();
			quantity = quantity.trim();
			cartid = cartid.trim();
			price = price.trim();
			if (Validations.isEmpty(itemid) || Validations.isEmpty(productid) || Validations.isEmpty(quantity)
					|| Validations.isEmpty(cartid) || Validations.isEmpty(price)) {
				message = "Please fill all boxes";
			} else if ((!Validations.isNumeric(itemid)) || (!Validations.isNumeric(productid))
					|| (!Validations.isNumeric(quantity)) || (!Validations.isNumeric(cartid))) {
				message = "Please enter numeric value in itemid, productid, quantity and cartid boxes.";
			} else if ((!Validations.isFloat(price))) {
				message = "Please enter numeric value in price box.";
			} else {
				float prices = Float.parseFloat(price);
				if (prices > 0) {
					ProductInfo pi = HibernateViewUtil.getProduct(Integer.parseInt(productid));
					CartInfo ci = HibernateViewUtil.getCartInfo(Integer.parseInt(cartid));
					CartItems record = HibernateViewUtil.getCartItemInfo(Integer.parseInt(itemid));
					if (record != null) {
						record.setItemid(Integer.parseInt(itemid));
						record.setProduct(pi);
						record.setQuantity(Integer.parseInt(quantity));
						record.setPrice(Float.parseFloat(price));
						record.setCart(ci);
						if (HibernateUtil.updateRecord(record)) {
							message = "Cart Item record is updated in system database";
						} else {
							message = "Error : " + HibernateUtil.getErrormessage();
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
		RequestDispatcher rd = request.getRequestDispatcher("viewcartitems.do");
		rd.forward(request, response);
	}

}
