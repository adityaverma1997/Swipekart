package controller.admin.fetch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HibernateViewUtil;
import model.to.ProductPhoto;
import operations.Validations;

/**
 * Servlet implementation class DownloadProductPhoto
 */
@WebServlet("/admin/downloadphoto.do")
public class DownloadProductPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadProductPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean jump = true;
		if (request.getParameter("photoid") != null) {
			String photoid = request.getParameter("photoid").trim();
			if (!Validations.isEmpty(photoid) && Validations.isNumeric(photoid)) {
				int pid = Integer.parseInt(photoid);
				ProductPhoto record = HibernateViewUtil.getProductPhoto(pid);
				if (record != null) {
					String path = "/productphotos/" + record.getPhotoid() + "." + record.getExtname();
					path = getServletContext().getRealPath(path);
					File file = new File(path);
					if (file.exists()) {
						response.reset();
						response.setContentType(record.getPhototype());
						response.setContentLength(record.getPhotosize());
						response.addHeader("content-disposition", "attachment;filename=" + record.getPhotoname());
						FileInputStream fis = new FileInputStream(file);
						byte[] filedata = new byte[fis.available()];
						fis.read(filedata);
						fis.close();
						response.getOutputStream().write(filedata);
					}
				}
			}
		}
		if (jump) {
			response.sendRedirect("viewproduct.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
