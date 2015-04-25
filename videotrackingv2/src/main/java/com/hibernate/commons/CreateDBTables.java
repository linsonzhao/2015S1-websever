package com.hibernate.commons;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class CreateDBTables {

	public static void main(String[] args) {

//		String configFile = "create.mysql.localhost.objectdetection.xml";
//		String configFile = "create.mysql.localhost.videotracking.xml";
		String configFile = "poserver.EA_DailyWork.cfg.xml";
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

			transaction.commit();

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

}
