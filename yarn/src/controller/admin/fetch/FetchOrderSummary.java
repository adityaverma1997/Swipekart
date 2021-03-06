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
import model.to.LoginInfo;
import model.to.OrderInfo;
import model.to.ProductInfo;

/**
 * Servlet implementation class FetchOrderSummary
 */
@WebServlet("/admin/fetchordersummary.do")
public class FetchOrderSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductInfo> products=HibernateViewUtil.getAllProducts();
		if(products!=null && products.size()>0){
			request.setAttribute("products", products);
		}
		List<OrderInfo> orders=HibernateViewUtil.getAllOrders();
		if(orders!=null && orders.size()>0){
			request.setAttribute("orders", orders);
		}
		RequestDispatcher rd=request.getRequestDispatcher("addordersummary.jsp");
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
