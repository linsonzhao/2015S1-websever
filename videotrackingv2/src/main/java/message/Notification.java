package message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Animal;
import model.Video;
import dao.AnimalDao;
import db.ConnectionPool;

/**
 *
 */
public class Notification {
	private String apiKey;
	private String message;
	private String title;
	private String link;
	private List<String> subList;
	private ConnectionPool cp;
	
	public Notification(){
		//Linson's Google GCM API key
		apiKey = "AIzaSyC-QeReBnOy_vOlIagZZ8YG7weg96_7DEM";
		title = "Zoo notification";
		cp = ConnectionPool.getInstance();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<String> getSubList() {
		subList = new ArrayList<String>();
		Connection conn = cp.getVideotrackingConn();
		String query = "select user_id from Subscription";
		try {
			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				subList.add(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subList;
	}
	
	public List<String> getSubList(String animalId) {
		subList = new ArrayList<String>();
		Connection conn = cp.getVideotrackingConn();
		String query = "select user_id from Subscription where animal_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, animalId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				subList.add(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subList;
	}

	public void setSubList(List<String> subList) {
		this.subList = subList;
	}

	public void send(Content content) {
		System.out.println("Sending POST to GCM");

		POST2GCM.post(apiKey, content);
	}

	public void sendMessage() {

		Content c = new Content();
		subList = getSubList();
		
		for(String s: subList){
			c.addRegId(s);
		}
		c.createData(title, message, link);

		POST2GCM.post(apiKey, c);
		
		System.out.println("message: " + message);
		System.out.println("link: " + link);
	}

	public void sendMessage(ArrayList<String> subList) {

			Content c = new Content();
			
			for(String s: subList){
				c.addRegId(s);
				System.out.println("subscription list: " + s);
			}
			c.createData(title, message, link);

			POST2GCM.post(apiKey, c);
			
			System.out.println("message: " + message);
			System.out.println("link: " + link);

	}
}
