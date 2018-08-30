package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.dao.HibernateUtil;

public class ProjectContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		HibernateUtil.closeSession();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
