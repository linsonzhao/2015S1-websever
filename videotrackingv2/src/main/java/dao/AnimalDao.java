/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db.ConnectionPool;
import model.Animal;

/**
 * @author linson
 *
 */
public enum AnimalDao {
	instance;
	
	private Map<Integer, Animal> animalMap = new HashMap<Integer, Animal>();
	
	private ConnectionPool cp;
	
	private AnimalDao(){
		this.cp = ConnectionPool.getInstance();
	}
	
	public Map<Integer, Animal> getModel(){
		updateAnimalMap();
		return animalMap;
	}
	
	public void updateAnimalMap(){
		AppInfo appInfo = AppInfo.getInstance();
		Connection conn = cp.getVideotrackingConn();
		String selectAnimal ="select * from animal order by name";
		ResultSet rs;
		
		try {
			PreparedStatement pstatement = conn.prepareStatement(selectAnimal);
			rs = pstatement.executeQuery();
			animalMap.clear();
			while(rs.next()){
				Animal animal = new Animal();
				animal.setDescription(rs.getString("description"));
				animal.setId(rs.getInt("id"));
				animal.setImageFile(rs.getString("imageFile"));
				animal.setName(rs.getString("name"));
				animal.setRating(rs.getInt("rating"));
				animalMap.put(animal.getId(), animal);
			}
			
			pstatement.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}