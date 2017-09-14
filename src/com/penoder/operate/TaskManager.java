package com.penoder.operate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TaskManager implements ServletContextListener  {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		GetFunData gfd = new GetFunData();
		gfd.operate(gfd);
	}

}
