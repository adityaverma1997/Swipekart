package controller.admin.insert;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HibernateUtil;
import model.to.CategoryInfo;
import operations.Validations;

/**
 * Servlet implementation class InsertCategoryInfo
 */
@WebServlet("/admin/insertcategory.do")
public class InsertCategoryInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryid=request.getParameter("categoryid");
		String categoryname=request.getParameter("categoryname");
		String description=request.getParameter("description");
		String message="";
		if(categoryid!=null && categoryname!=null && description!=null){
			categoryid=categoryid.trim();
			categoryname=categoryname.trim();
			description=description.trim();
			if(Validations.isEmpty(categoryid) || Validations.isEmpty(categoryname) || Validations.isEmpty(description)){
				message="Please fill all boxes";
			}else if((!Validations.onlyCharacter(categoryid)) || (!Validations.onlyCharacter(categoryname))){
				message="Please enter character values in all categoryid and categoryname boxes";
			}
			else{
				CategoryInfo record=new CategoryInfo();
				record.setCategoryid(categoryid);
				record.setCategoryname(categoryname);
				record.setDescription(description);
				if(HibernateUtil.insertRecord(record)){
					message="Category record is added in system database";
				}else{
					if(HibernateUtil.getErrormessage().contains("NonUniqueObjectException")){
						message="Please Enter unique values in categoryid and categoryname as they are primary key and unique key respectively.";
					}else{
						message="Error : " + HibernateUtil.getErrormessage();
					}
				}
			}
		}else{
			message="Not enough parameter supplied";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd=request.getRequestDispatcher("addcategory.jsp");
		rd.forward(request, response);
	}
}
