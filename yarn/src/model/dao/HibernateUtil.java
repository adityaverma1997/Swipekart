package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Session session = null;

	private HibernateUtil() {

	}

	public static Session getHibernateSession() {
		try {
			if (session == null) {
				Configuration config = new Configuration();
				config.configure();
				SessionFactory factory = config.buildSessionFactory();
				session = factory.openSession();
			}
			return session;
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			return null;
		}
	}

	public static void closeSession() {
		try {
			if (session != null) {
				session.close();
			}
			session = null;
		} catch (Exception ex) {

		}
	}

	private static String errormessage;

	public static String getErrormessage() {
		return errormessage;
	}

	public static boolean insertRecord(Object record) {
		try {
			getHibernateSession().beginTransaction();
			getHibernateSession().save(record);
			getHibernateSession().getTransaction().commit();
			return true;
		} catch (Exception ex) {
			errormessage = ex.toString();
			return false;
		}
	}

	public static boolean updateRecord(Object record) {
		try {
			getHibernateSession().beginTransaction();
			getHibernateSession().update(record);
			getHibernateSession().getTransaction().commit();
			return true;
		} catch (Exception ex) {
			errormessage = ex.toString();
			return false;
		}
	}

	public static boolean deleteRecord(Object record) {
		try {
			getHibernateSession().beginTransaction();
			getHibernateSession().delete(record);
			getHibernateSession().getTransaction().commit();
			return true;
		} catch (Exception ex) {
			errormessage = ex.toString();
			return false;
		}
	}
}
