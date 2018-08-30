package controller.admin.insert;

import java.io.IOException;
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
import model.to.CartItems;
import model.to.CompanyInfo;
import model.to.ProductInfo;
import model.to.SubcategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class InsertCartItemsInfo
 */
@WebServlet("/admin/insertcartitems.do")
public class InsertCartItemsInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productid = request.getParameter("productid");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String cartid = request.getParameter("cartid");
		String message = "";
		if (productid != null && quantity != null && price != null && cartid != null) {
			productid = productid.trim();
			quantity = quantity.trim();
			price = price.trim();
			cartid = cartid.trim();
			if (Validations.isEmpty(productid) || Validations.isEmpty(quantity) || Validations.isEmpty(price)
					|| Validations.isEmpty(cartid)) {
				message = "Please fill all boxes";
			} else if (!Validations.isFloat(price)) {
				message = "Please select decimal values in price box.";
			} else if ((!Validations.isNumeric(productid)) || (!Validations.isNumeric(quantity))
					|| (!Validations.isNumeric(cartid))) {
				message = "Please select numeric values in productid, quantity and cartid boxes.";
			} else {
				int prodid = Integer.parseInt(productid);
				int quant = Integer.parseInt(quantity);
				float prices = Float.parseFloat(price);
				int cid = Integer.parseInt(cartid);
				if (prices > 0) {
					ProductInfo pi = HibernateViewUtil.getProduct(prodid);
					CartInfo ci = HibernateViewUtil.getCartInfo(cid);
					if (pi == null || ci == null) {
						message = "There is no record for selected product or cart item.";
					} else {
						CartItems record = new CartItems();
						record.setProduct(pi);
						record.setQuantity(Integer.parseInt(quantity));
						record.setPrice(prices);
						record.setCart(ci);
						List<CartItems> results = HibernateViewUtil.getAllCartItemDetails(pi.getProductid(), Integer.parseInt(quantity), ci.getCartid());
						if (results != null && results.size() > 0) {
							message = "This cart item is already added to cart having selected cartid and quantity.";
						} else {
							if (HibernateUtil.insertRecord(record)) {
								message = "Cart Item record is added in system database";
							} else {
								message = "Insertion failure due to : " + HibernateUtil.getErrormessage();
							}
						}
					}
				} else {
					message = "Price must be greater than 0.";
				}
			}
		} else {
			message = "Not enough parameter supplied";
		}
		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher("fetchcartitems.do");
		rd.forward(request, response);
	}
}
