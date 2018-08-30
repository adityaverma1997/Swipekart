package controller.admin.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HibernateViewUtil;
import model.to.CompanyInfo;
import model.to.ProductInfo;
import model.to.SubcategoryInfo;

/**
 * Servlet implementation class ViewProductInfo
 */
@WebServlet("/admin/viewproduct.do")
public class ViewProductInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductInfo> products=HibernateViewUtil.getAllProducts();
		if(products!=null && products.size()>0){
			request.setAttribute("products", products);
		}
		List<SubcategoryInfo> subcategory=HibernateViewUtil.getAllSubcategories();
		if(subcategory!=null && subcategory.size()>0){
			request.setAttribute("subcategory", subcategory);
		}
		List<CompanyInfo> company=HibernateViewUtil.getAllCompanies();
		if(company!=null && company.size()>0){
			request.setAttribute("company", company);
		}
		RequestDispatcher rd=request.getRequestDispatcher("viewproduct.jsp");
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
