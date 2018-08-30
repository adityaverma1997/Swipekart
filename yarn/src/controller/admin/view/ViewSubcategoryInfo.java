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
import model.to.CategoryInfo;
import model.to.SubcategoryInfo;

/**
 * Servlet implementation class ViewSubcategoryInfo
 */
@WebServlet("/admin/viewsubcategory.do")
public class ViewSubcategoryInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SubcategoryInfo> scategory=HibernateViewUtil.getAllSubcategories();
		if(scategory!=null && scategory.size()>0){
			request.setAttribute("scategory", scategory);
		}
		List<CategoryInfo> category=HibernateViewUtil.getAllCategory();
		if(category!=null && category.size()>0){
			request.setAttribute("category", category);
		}
		RequestDispatcher rd=request.getRequestDispatcher("viewsubcategory.jsp");
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
