package model.dao;

import java.util.List;

import org.hibernate.Query;

import model.to.CartInfo;
import model.to.CartItems;
import model.to.CategoryInfo;
import model.to.CompanyInfo;
import model.to.LoginInfo;
import model.to.OrderDetails;
import model.to.OrderInfo;
import model.to.ProductInfo;
import model.to.ProductPhoto;
import model.to.SubcategoryInfo;

public class HibernateViewUtil {
	public static List<CategoryInfo> getAllCategory() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from CategoryInfo");
			List<CategoryInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static CategoryInfo getCategory(String categoryid) {
		try {
			CategoryInfo record= (CategoryInfo) HibernateUtil.getHibernateSession().get(CategoryInfo.class, categoryid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<SubcategoryInfo> getAllSubcategories(String subcategoryname,String categoryid) {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from SubcategoryInfo where subcategoryname= :subcatname and categoryid= :catid");
			query.setParameter("subcatname", subcategoryname);
			query.setParameter("catid", categoryid);
			List<SubcategoryInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<SubcategoryInfo> getAllSubcategories() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from SubcategoryInfo");
			List<SubcategoryInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static SubcategoryInfo getSubCategory(int subcategoryid) {
		try {
			SubcategoryInfo record= (SubcategoryInfo) HibernateUtil.getHibernateSession().get(SubcategoryInfo.class, subcategoryid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<CompanyInfo> getAllCompanies() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from CompanyInfo");
			List<CompanyInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static CompanyInfo getCompany(String companyid) {
		try {
			CompanyInfo record= (CompanyInfo) HibernateUtil.getHibernateSession().get(CompanyInfo.class, companyid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<ProductInfo> getAllProducts(String productname,int subcategoryid,String companyid) {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from ProductInfo where productname= :prodname, subcategoryid= :scatid and companyid= :comid");
			query.setParameter("prodname", productname);
			query.setParameter("scatid", subcategoryid);
			query.setParameter("comid", companyid);
			List<ProductInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<ProductInfo> getAllProducts() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from ProductInfo");
			List<ProductInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static ProductInfo getProduct(int productid) {
		try {
			ProductInfo record= (ProductInfo) HibernateUtil.getHibernateSession().get(ProductInfo.class, productid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<ProductPhoto> getAllProductPhotos() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from ProductPhoto");
			List<ProductPhoto> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<ProductPhoto> getAllProductPhotos(String photoname,int productid) {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from ProductPhoto where photoname= :phtname and productid= :prodid");
			query.setParameter("phtname", photoname);
			query.setParameter("prodid", productid);
			List<ProductPhoto> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static ProductPhoto getProductPhoto(int photoid) {
		try {
			ProductPhoto record= (ProductPhoto) HibernateUtil.getHibernateSession().get(ProductPhoto.class, photoid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<CartInfo> getAllCartDetails() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from CartInfo");
			List<CartInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static CartInfo getCartInfo(int cartid) {
		try {
			CartInfo record= (CartInfo) HibernateUtil.getHibernateSession().get(CartInfo.class, cartid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<CartItems> getAllCartItemDetails() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from CartItems");
			List<CartItems> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<CartItems> getAllCartItemDetails(int productid,int quantity,int cartid) {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from CartItems where productid= :prodid, quantity= :quant and cartid= :crtid");
			query.setParameter("prodid", productid);
			query.setParameter("quant", quantity);
			query.setParameter("crtid", cartid);
			List<CartItems> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static CartItems getCartItemInfo(int itemid) {
		try {
			CartItems record= (CartItems) HibernateUtil.getHibernateSession().get(CartItems.class, itemid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<LoginInfo> getAllUsers() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from LoginInfo");
			List<LoginInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static LoginInfo getUsers(String username) {
		try {
			LoginInfo record= (LoginInfo) HibernateUtil.getHibernateSession().get(LoginInfo.class, username);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<OrderInfo> getAllOrders() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from OrderInfo");
			List<OrderInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<OrderInfo> getAllOrders(String username,int cartid) {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from OrderInfo where username= :usrname and cartid= :crtid");
			query.setParameter("usrname", username);
			query.setParameter("crtid", cartid);
			List<OrderInfo> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static OrderInfo getOrder(int orderid) {
		try {
			OrderInfo record= (OrderInfo) HibernateUtil.getHibernateSession().get(OrderInfo.class, orderid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<OrderDetails> getAllOrderDetails() {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from OrderDetails");
			List<OrderDetails> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static List<OrderDetails> getAllOrderDetails(int productid,int quantity,int orderid) {
		try {
			Query query = HibernateUtil.getHibernateSession().createQuery("from CartItems where productid= :prodid, quantity= :quant and orderid= :odrid");
			query.setParameter("prodid", productid);
			query.setParameter("quant", quantity);
			query.setParameter("odrid", orderid);
			List<OrderDetails> records = query.list();
			return records;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	public static OrderDetails getOrderDetail(int detailid) {
		try {
			OrderDetails record= (OrderDetails) HibernateUtil.getHibernateSession().get(OrderDetails.class, detailid);
			return record;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

}
