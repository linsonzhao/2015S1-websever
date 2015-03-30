package com.hibernate.commons;

import java.util.ArrayList;
import java.util.List;

import model.Video;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class AppRun {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		String configFile = "mysql.cfg.xml";
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			Configuration configuration = new Configuration()
					.configure(configFile);
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("FROM Video");
			// query.setMaxResults(2);
			List empList = query.list();
			transaction.commit();

			ArrayList<Video> videos = new ArrayList<Video>();
			for (int i = 0; i < empList.size(); i++) {
				Video video = (Video) empList.get(i);
				System.out.println(video.getVideoFile());
			}

		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
		} finally {
			if (session.isOpen()) {
				System.out.println("Closing session");
				session.close();
			}
			if (!sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}
	
	public void test(){

		int i = 10;
		float f = 10.0f;
	}

}
