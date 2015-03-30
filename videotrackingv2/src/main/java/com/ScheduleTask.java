package com;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import controller.MainAction;
import dao.AppInfo;
import db.ConnectionPool;

public class ScheduleTask extends TimerTask {

	ConnectionPool cp;
	AppInfo appInfo;
	//get log4j
	private static final Logger logger = Logger.getLogger(ScheduleTask.class);
	
	public ScheduleTask(){
		cp = ConnectionPool.getInstance();
		appInfo = AppInfo.getInstance();
	}
	
	@Override
	public void run() {

		System.out.println("Schedule task running..");
		ArrayList<String> fileList = new ArrayList<String>();
		Connection conn = cp.getVideotrackingConn();
		String query = "select * from videos where createdTime < ?";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		String dateString = date_format.format(new Date());
//		System.out.println(dateString);
//		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)-1);
		
		//delete the old videos based on time setting
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)-20);
		String dateString = date_format.format(calendar.getTime());
		System.out.println(dateString);
		
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, dateString);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				fileList.add(rs.getString("videoFile"));
			}
			
			File file;
			String path = "c:\\videotracking";
			
			for(String s: fileList){
				file = new File(appInfo.getHostPath() + s.replace("/", "\\"));
				System.out.println(file.getAbsolutePath());
				if(file.exists()){
					file.delete();
					System.out.println("delete file: " + file.getAbsolutePath());
				}
				
				file = new File(path + s.replace("/", "\\"));
				if(file.exists()){
					file.delete();
					System.out.println("delete file: " + file.getAbsolutePath());
				}
				
				System.out.println("file for delete: " + path + s.replace("/", "\\"));
			}
			
			query = "delete from videos where createdTime < ? limit 1000";
			ps = conn.prepareStatement(query);
			ps.setString(1, dateString);
			ps.executeUpdate();
			
			ps.close();
			rs.close();
			conn.close();
		}
		catch(SQLException e){
			
		}
	}

}
