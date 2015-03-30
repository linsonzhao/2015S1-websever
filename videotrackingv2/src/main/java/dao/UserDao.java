package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.User;
import db.ConnectionPool;

public enum UserDao {
	instance;
	
	private Map<Integer, User> userMap = new HashMap<Integer, User>();
	private ArrayList<User> userList = new ArrayList<User>();
	
	private ConnectionPool cp;
	
	private UserDao(){
		this.cp = ConnectionPool.getInstance();
	}
	
	public Map<Integer, User> getModel(){

		return userMap;
	}
	
	public ArrayList<User> getUserList(){
		for(User user: userMap.values()){
			userList.add(user);
		}
		
		return userList;
	}
	
}
