package controller.admin.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.property.adapter.ReadOnlyPropertyDescriptor;

import model.dao.HibernateUtil;
import model.dao.HibernateViewUtil;
import model.to.CategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class DeleteCategory
 */
@WebServlet("/admin/deletecategory.do")
public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryid=request.getParameter("categoryid");
		String message="";
		if(categoryid!=null){
			categoryid=categoryid.trim();
			if(Validations.isEmpty(categoryid)){
				message="Please provide some value for categoryid.";
			}else{
				CategoryInfo record=HibernateViewUtil.getCategory(categoryid);
				if(record==null){
					message="There is no record for this categoryid.";
				}else if(HibernateUtil.deleteRecord(record)){
					message="Record is removed from system database.";
				}else{
					message="Failure due to : " + HibernateUtil.getErrormessage();
				}
			}
		}else{
			message="Not enough value supplied.";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd=request.getRequestDispatcher("viewcategory.do");
		rd.forward(request, response);
	}

}
