/**
 * 
 */
package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.OSValidator;
import com.Subscription;
import com.VideoInfo;

import db.ConnectionPool;
import message.Notification;
import model.Video;

/**
 * @author linson
 *
 */
public enum VideoDao {
	instance;
	
	private Map<Integer, Video> videoMap = new HashMap<Integer, Video>();
	
	private ConnectionPool cp;
	
	private VideoDao(){
		this.cp = ConnectionPool.getInstance();
	}
	
	public Map<Integer, Video> getModel(){
		updateVideoMap();
		return videoMap;
	}
	
	public void updateVideoMap(){
		AppInfo appInfo = AppInfo.getInstance();
		Connection conn = cp.getVideotrackingConn();
		String selectVideos ="select * from videos";
		ResultSet rs;
		
		try {
			PreparedStatement pstatement = conn.prepareStatement(selectVideos, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstatement.executeQuery();
			videoMap.clear();
			while(rs.next()){
				Video video = new Video();
//				System.out.println(rs.getInt("videoId"));
				video.setVideoId(rs.getInt("videoId"));
				video.setTarget(rs.getString("target"));
				video.setSize(rs.getInt("size"));
				video.setVideoFile(rs.getString("videoFile"));
				video.setDescription(rs.getString("description"));
				video.setTracking(rs.getBoolean("tracking"));
				video.setCodecId(rs.getString("codecId"));
				video.setDuration(rs.getInt("duration"));
				video.setWidth(rs.getInt("width"));
				video.setHeight(rs.getInt("height"));
				videoMap.put(video.getVideoId(), video);
			}
			
			pstatement.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addVideo(Video video){
		
		if(!video.isTracking()){
			//check and set video info
			VideoInfo videoInfo = new VideoInfo(AppInfo.getInstance().getHostPath() + video.getVideoFile().replace("/", "\\"));
			video.setCodecId(videoInfo.getCodecId());
			video.setDuration(videoInfo.getDuration());
			video.setHeight(videoInfo.getHeight());
			video.setWidth(videoInfo.getWidth());
			video.setSize(videoInfo.getFileSize());
		}
		
		Connection conn = cp.getVideotrackingConn();
		String insertVideo ="INSERT INTO videos (target, videoFile, size, description, tracking, codecId, duration, height, width) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		
		String updateVideo = "update videos"
				+ " set target=?, size=?, description=?, tracking=? , codecId=?, duration=?, height=?, width=?"
				+ " where videoFile=?";
		
		try {
			if(VideoDao.instance.getVideoId(video.getVideoFile())>0){
				//update video
				PreparedStatement ps = conn.prepareStatement(updateVideo, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setString(1, video.getTarget());
				ps.setDouble(2, video.getSize());
				ps.setString(3, video.getDescription());
				ps.setBoolean(4, video.isTracking());
				ps.setString(5, video.getCodecId());
				ps.setInt(6, video.getDuration());
				ps.setInt(7, video.getHeight());
				ps.setInt(8, video.getWidth());
				ps.setString(9, video.getVideoFile());
				
				ps.executeUpdate();
				updateVideoMap();
				
				Subscription ss = new Subscription();
				if(!ss.getSubList(video.getTarget()).isEmpty()){
					Notification notification = new Notification();
					notification.setMessage("Updated Video");
					notification.setLink(video.getVideoFile());
					notification.sendMessage(ss.getSubList(video.getTarget()));
				}
			}
			else{
				//insert video
				PreparedStatement ps = conn.prepareStatement(insertVideo, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setString(1, video.getTarget());
				ps.setString(2, video.getVideoFile());
				ps.setDouble(3, video.getSize());
				ps.setString(4, video.getDescription());
				ps.setBoolean(5, video.isTracking());
				ps.setString(6, video.getCodecId());
				ps.setInt(7, video.getDuration());
				ps.setInt(8, video.getHeight());
				ps.setInt(9, video.getWidth());
				
				ps.executeUpdate();
				updateVideoMap();
				
				Subscription ss = new Subscription();
				if(!ss.getSubList(video.getTarget()).isEmpty()){
					Notification notification = new Notification();
					notification.setMessage("New Video");
					notification.setLink(video.getVideoFile());
					notification.sendMessage(ss.getSubList(video.getTarget()));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Video getVideo(int videoId){
		Video video = videoMap.get(videoId);
		
//		Connection conn = cp.getVideotrackingConn();
//		String query = "select * from videos where videoId = ?";
//		try {
//			PreparedStatement pstatement = conn.prepareStatement(query,
//					ResultSet.TYPE_SCROLL_INSENSITIVE,
//					ResultSet.CONCUR_READ_ONLY);
//			pstatement.setInt(1, videoId);
//			ResultSet rs = pstatement.executeQuery();
//
//			while (rs.next()) {
//				video.setVideoFile(rs.getString("videoFile"));
//				video.setDescription(rs.getString("description"));
//				video.setTarget(rs.getString("target"));
//				video.setSize(rs.getDouble("size"));
//				video.setTracking(rs.getBoolean("tracking"));
//			}
//
//			pstatement.close();
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		return video;
	}
	
	public int getVideoId(String videoFile){
		int videoId = 0;
		Connection conn = cp.getVideotrackingConn();
		String query = "select videoId from videos where videoFile = ?";
		try {
			PreparedStatement pstatement = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstatement.setString(1, videoFile);
			ResultSet rs = pstatement.executeQuery();

			while (rs.next()) {
				videoId = rs.getInt("videoId");
			}

			pstatement.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return videoId;
	}
	
	public void deleteVideo(int videoId){
		Connection conn = cp.getVideotrackingConn();
		String query = "delete from videos where videoId=?";
		try{
		videoMap.remove(videoId);
		PreparedStatement pstatement = conn.prepareStatement(query);
		pstatement.setInt(1, videoId);
		pstatement.executeUpdate();
		
		pstatement.close();
		conn.close();
		}
		catch(Exception e){
			
		}
	}
	
	public void deleteAllVideo(){
		Connection conn = cp.getVideotrackingConn();
		String query = "truncate videos";
		try{
		videoMap.clear();
		PreparedStatement pstatement = conn.prepareStatement(query);
		pstatement.executeUpdate();
		
		pstatement.close();
		conn.close();
		}
		catch(Exception e){
			
		}
	}
	
//	public void updateVideoMap(String path){
//		AppInfo appInfo = AppInfo.getInstance();
//		
//		OSValidator os = new OSValidator();
//		File folder;
//		String separator;
//		if (os.isWindows()) {
//			separator = "\\";
//		} else {
//			separator = "/";
//		}
//		folder = new File(appInfo.getHostPath() + separator + path);
//		File[] listOfFiles = folder.listFiles();
//
//		for (int i = 0; i < listOfFiles.length; i++) {
//			if (listOfFiles[i].isFile()) {
//				System.out.println("FileName is: " + listOfFiles[i].getName());
//				long length = 0;
//				double dLength;
//				if (listOfFiles[i].isFile()) {
//					length += listOfFiles[i].length();
//					length /= (1000 * 10);
//				}
//
//				if (length != 0)
//					dLength = length / 100.0;
//				else
//					dLength = 0.0;
//				System.out.println("FileSize is: " + dLength + "MB");
//				System.out.println("FileLocation is: " + "/raw_video/" + listOfFiles[i].getName());
//			} else if (listOfFiles[i].isDirectory()) {
//				System.out.println("Directory " + listOfFiles[i].getName());
//			}
//		}
//	}
}
