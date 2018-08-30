package controller.admin.insert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.dao.HibernateUtil;
import model.dao.HibernateViewUtil;
import model.to.ProductInfo;
import model.to.ProductPhoto;
import model.to.SubcategoryInfo;

/**
 * Servlet implementation class UploadProductPhoto
 */
@WebServlet("/admin/uploadproductphoto.do")
@MultipartConfig
public class UploadProductPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		Part photopart = request.getPart("photo");
		Part productidpart = request.getPart("productid");
		if (photopart != null && productidpart != null) {
			InputStream photostream = photopart.getInputStream();
			InputStream productidstream = productidpart.getInputStream();
			Scanner sc = new Scanner(productidstream);
			if (sc.hasNextInt() && photostream instanceof FileInputStream) {
				int productid = sc.nextInt();
				/*
				 * Collection<String> headers = photopart.getHeaderNames(); if
				 * (headers != null) { for (String headername : headers) {
				 * message += headername + ":" + photopart.getHeader(headername)
				 * + "<br/>"; } }
				 */
				String photoname = photopart.getHeader("content-disposition");
				photoname = photoname.substring(photoname.lastIndexOf("\\") + 1);
				photoname = photoname.replace("\"", "");
				String extname = photoname.substring(photoname.lastIndexOf('.') + 1);
				String extensions = "jpg,jpeg,png,gif,bmp";
				if (extensions.contains(extname.toLowerCase())) {
					ProductInfo pi = HibernateViewUtil.getProduct(productid);
					if (pi != null) {
						ProductPhoto record = new ProductPhoto();
						record.setPhotoname(photoname);
						record.setExtname(extname);
						record.setPhototype(photopart.getContentType());
						record.setPhotosize(photostream.available());
						record.setProduct(pi);
						List<ProductPhoto> results = HibernateViewUtil.getAllProductPhotos(photoname, pi.getProductid());
						if (results != null && results.size() > 0) {
							message = "This photo is already assigned to selected productid.";
						} else {
							if (HibernateUtil.insertRecord(record)) {
								message = "Product photo is uploaded.";
								int photoid = record.getPhotoid();
								String path = "/productphotos/" + photoid + "." + extname;
								path = getServletContext().getRealPath(path);
								FileOutputStream fout = new FileOutputStream(path);
								int data = photostream.read();
								while (data != -1) {
									fout.write(data);
									data = photostream.read();
								}
								fout.close();
							} else {
								message = "Upload failure due to : " + HibernateUtil.getErrormessage();
							}
						}
					} else {
						message = "This productid doesn't exists.";
					}
				} else {
					message = "This file format is not supported.";
				}
			} else {
				message = "Please select appropriate values.";
			}
		} else {
			message = "Not enough values";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("fetchproductforphoto.do");
		rd.forward(request, response);
	}

}
