package controller.admin.fetch;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HibernateViewUtil;
import model.to.CartInfo;
import model.to.CompanyInfo;
import model.to.ProductInfo;
import model.to.SubcategoryInfo;

/**
 * Servlet implementation class FetchCartItems
 */
@WebServlet("/admin/fetchcartitems.do")
public class FetchCartItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductInfo> products=HibernateViewUtil.getAllProducts();
		if(products!=null && products.size()>0){
			request.setAttribute("products", products);
		}
		List<CartInfo> cart=HibernateViewUtil.getAllCartDetails();
		if(cart!=null && cart.size()>0){
			request.setAttribute("cart", cart);
		}
		RequestDispatcher rd=request.getRequestDispatcher("addcartitems.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
