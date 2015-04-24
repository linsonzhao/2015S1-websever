package com.hibernate.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Video;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HiVideoDao {
	private static HiVideoDao dao;
	private Map<Integer, Video> allVideos;
	private Map<Integer, Video> rawVideos;
	private Map<Integer, Video> trackedVideos;
	
	public HiVideoDao(){
		allVideos = new HashMap<Integer, Video>();
		rawVideos = new HashMap<Integer, Video>();
		trackedVideos = new HashMap<Integer, Video>();
	}
	
	public static HiVideoDao getInstance(){
		if(dao==null){
			dao = new HiVideoDao();
		}
		return dao;
	}

	public Map<Integer, Video> getAllVideos() {
		update();
		return allVideos;
	}

	public Map<Integer, Video> getRawVideos() {
		update();
		return rawVideos;
	}

	public Map<Integer, Video> getTrackedVideos() {
		update();
		return trackedVideos;
	}

	@SuppressWarnings("rawtypes")
	private void update() {

		System.out.println("HiVideoDao updating...");
		
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
			Query query = null;
			List list = null;

			//all videos
			transaction = session.beginTransaction();
			query = session
					.createQuery("FROM Video");
			list = query.list();
			transaction.commit();

			allVideos.clear();
			for (int i = 0; i < list.size(); i++) {
				Video video = (Video) list.get(i);
				System.out.println(video.getVideoFile());
				allVideos.put(video.getVideoId(), video);
			}
			
			//raw videos
			transaction = session.beginTransaction();
			query = session
					.createQuery("FROM Video WHERE tracking=false");
			list = query.list();
			transaction.commit();

			rawVideos.clear();
			for (int i = 0; i < list.size(); i++) {
				Video video = (Video) list.get(i);
				System.out.println(video.getVideoFile());
				rawVideos.put(video.getVideoId(), video);
			}
			
			
			//tracked videos
			transaction = session.beginTransaction();
			query = session
					.createQuery("FROM Video WHERE tracking=true");
			list = query.list();
			transaction.commit();

			trackedVideos.clear();
			for (int i = 0; i < list.size(); i++) {
				Video video = (Video) list.get(i);
				System.out.println(video.getVideoFile());
				trackedVideos.put(video.getVideoId(), video);
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

}
