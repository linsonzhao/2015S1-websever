package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Animal;
import dao.AnimalDao;
import db.ConnectionPool;

public class Subscription {

	private ArrayList<String> subList;
	private ConnectionPool cp;
	
	public Subscription(){
		cp = ConnectionPool.getInstance();
	}
	
	public ArrayList<String> getSubList(String animalName) {
		
		Animal animal = null;
		for(Animal a: AnimalDao.instance.getModel().values()){
			if(a.getName().equalsIgnoreCase(animalName)){
				animal = a;
				System.out.println(a.getId() + "," + a.getName());
			}
		}
		
		subList = new ArrayList<String>();
		Connection conn = cp.getVideotrackingConn();
		String query = "select user_id from Subscription where animal_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, String.valueOf(animal.getId()));
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				subList.add(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subList;
	}
	
}
