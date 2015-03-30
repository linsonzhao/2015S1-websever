package controller;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jni.JniMain;
import jni.JniMainClass;
import jni.TrackingJNI;
import message.Notification;
import model.Animal;
import model.Video;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.TrackingFile;
import com.opensymphony.xwork2.ActionSupport;

import dao.AnimalDao;
import dao.AppInfo;
import dao.VideoDao;
import db.ConnectionPool;
import db.DatabaseTable;
import db.ResourceReader;

public class MainAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	//get log4j
	private static final Logger logger = Logger.getLogger(MainAction.class);
	
	public HttpServletRequest request;
	private String query;
	private String path;
	private String[] header;
	private String[][] rows;
	private int rowcount;
	private int colcount;
	private ConnectionPool cp;
	private Connection conn;
	private PreparedStatement pstatement;
	private ResultSet rs;
	private String param;
	private String message;
	//videoList is for raw videos
	private List<Video> videoList;
	//trackingVideoList is for tracking videos.
	private List<Video> trackingVideoList;
	private List<Animal> animals;
	private int videoId;
	private String videoFile;

	public MainAction() {
		cp = ConnectionPool.getInstance();
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String[][] getRows() {
		return rows;
	}

	public void setRows(String[][] rows) {
		this.rows = rows;
	}

	public int getRowcount() {
		return rowcount;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	public int getColcount() {
		return colcount;
	}

	public void setColcount(int colcount) {
		this.colcount = colcount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public int getRawVideoId() {
		return videoId;
	}

	public void setRawVideoId(int rawVideoId) {
		this.videoId = rawVideoId;
	}

	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}

	public List<Video> getTrackingVideoList() {
		return trackingVideoList;
	}

	public void setTrackingVideoList(List<Video> trackingVideoList) {
		this.trackingVideoList = trackingVideoList;
	}

	@Override
	public String execute() {
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String startup() {
		String relativeWebPath = "/sql";
		path = request.getServletContext().getRealPath(relativeWebPath)
				+ "/selectAnimals.sql";

		ResourceReader rr = new ResourceReader();
		query = rr.getFileString(path);
		conn = cp.getVideotrackingConn();

		return tableResult(conn, query);
	}

	public String sendMessage() {
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.sendMessage();
		return SUCCESS;
	}

	public String koalaAwake() {
		Notification notification = new Notification();
		notification.setMessage("koala is awake;/raw_video/koala.mp4");
		notification.sendMessage();
		return SUCCESS;
	}

	public String kangarooAwake() {
		Notification notification = new Notification();
		notification
				.setMessage("kangaroo is awake;/raw_video/kangaroo.mp4");
		notification.sendMessage();
		return SUCCESS;
	}

	public String koalaVideo() {
		Notification notification = new Notification();
		notification.setMessage("new koala video;/raw_video/koala.mp4");
		notification.sendMessage();
		return SUCCESS;
	}

	public String kangarooVideo() {
		Notification notification = new Notification();
		notification
				.setMessage("new kangaroo video;/raw_video/kangaroo.mp4");
		notification.sendMessage();
		return SUCCESS;
	}

	public String error() {
		message = (String) request.getAttribute("message");
		return SUCCESS;
	}

	public String uploadFile() {

//		message = (String) request.getAttribute("message");
		animals = new ArrayList<Animal>();
		for(Animal a: AnimalDao.instance.getModel().values()){
			animals.add(a);
		}

		return SUCCESS;
	}

	public String tableResult(Connection conn, String query, String param) {
		try {
			// important!!!!!!
			// ResultSet default setting is TYPE_FORWARD_ONLY, so need to define
			// ResultSet.TYPE_SCROLL_INSENSITIVE and ResultSet.CONCUR_READ_ONLY
			pstatement = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstatement.setString(1, param);
			rs = pstatement.executeQuery();
			DatabaseTable dt = new DatabaseTable(rs);
			rows = dt.getRows();
			header = dt.getHeader();
			rowcount = dt.getRowCount();
			colcount = dt.getHeader().length;
			pstatement.close();
			rs.close();
			conn.close();
			return SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("tableResult(Connection conn, String query, String param)", e);
			return INPUT;
		}
	}

	public String tableResult(Connection conn, String query, String param1,
			String param2) {
		try {
			// important!!!!!!
			// ResultSet default setting is TYPE_FORWARD_ONLY, so need to define
			// ResultSet.TYPE_SCROLL_INSENSITIVE and ResultSet.CONCUR_READ_ONLY
			pstatement = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstatement.setString(1, param1);
			pstatement.setString(2, param2);
			rs = pstatement.executeQuery();
			DatabaseTable dt = new DatabaseTable(rs);
			rows = dt.getRows();
			header = dt.getHeader();
			rowcount = dt.getRowCount();
			colcount = dt.getHeader().length;
			pstatement.close();
			rs.close();
			conn.close();
			return SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			return INPUT;
		}
	}

	public String tableResult(Connection conn, String query) {
		try {
			// important!!!!!!
			// ResultSet default setting is TYPE_FORWARD_ONLY, so need to define
			// ResultSet.TYPE_SCROLL_INSENSITIVE and ResultSet.CONCUR_READ_ONLY
			pstatement = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstatement.executeQuery();
			DatabaseTable dt = new DatabaseTable(rs);
			rows = dt.getRows();
			header = dt.getHeader();
			rowcount = dt.getRowCount();
			colcount = dt.getHeader().length;
			pstatement.close();
			rs.close();
			conn.close();
			return SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("tableResult(Connection conn, String query): ", e);
			return INPUT;
		}
	}

	public String trackingJNI() {

		Thread thread = new Thread() {
			public void run() {
				// System.loadLibrary("libTracking");
				// TrackingJNI trackingJNI = new TrackingJNI();
				//
				// System.out.println("\n"
				// + trackingJNI.trackingMethod("c:/temp/test/cup.avi",
				// 242, 289, 107, 68,
				// "c:/temp/test/cup_tracking2.mpg"));
				JniMainClass.getInstance().run("c:/temp/test/cup.avi", 242,
						289, 107, 68, "c:/temp/test/cup_tracking2.mpg");
			}
		};

		thread.start();

		return SUCCESS;
	}

	public String trackingJNI(String sourceFile, String outputFile) {

		Thread thread = new Thread() {
			public void run() {
				System.loadLibrary("libTracking");
				// System.loadLibrary("tracking_cpp");
				TrackingJNI trackingJNI = new TrackingJNI();

				System.out.println("\n"
						+ trackingJNI.trackingMethod("c:/temp/test/cup.avi",
								242, 289, 107, 68,
								"c:/temp/test/cup_tracking2.avi"));
				// System.out.println("\n"
				// + trackingJNI.trackingMethod("c:/temp/test/cat.avi", 242,
				// 289,
				// 107, 68, "c:/temp/test/cat_tracking.avi"));
			}
		};

		thread.start();

		message = "TRACKING VIDEO CREATED";

		return SUCCESS;
	}

	public String getVideos() {
		
		conn = cp.getVideotrackingConn();
		query = "select * from videos where tracking=false";

		videoList = new ArrayList<Video>();
		// important!!!!!!
		// ResultSet default setting is TYPE_FORWARD_ONLY, so need to define
		// ResultSet.TYPE_SCROLL_INSENSITIVE and ResultSet.CONCUR_READ_ONLY
		try {
			pstatement = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = pstatement.executeQuery();
			while (rs.next()) {
				Video video = new Video();
				video.setVideoId(rs.getInt("videoId"));
				video.setTarget(rs.getString("target").toUpperCase());
				video.setSize(rs.getInt("size"));
				video.setVideoFile(rs.getString("videoFile"));
				video.setDescription(rs.getString("description"));
				video.setTracking(rs.getBoolean("tracking"));

				videoList.add(video);
			}

			pstatement.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("getVideos error: " , e);
		}

		return SUCCESS;
	}

	public String getTrackingVideos() {
		conn = cp.getVideotrackingConn();
		query = "select * from videos where tracking=true";

		trackingVideoList = new ArrayList<Video>();
		// important!!!!!!
		// ResultSet default setting is TYPE_FORWARD_ONLY, so need to define
		// ResultSet.TYPE_SCROLL_INSENSITIVE and ResultSet.CONCUR_READ_ONLY
		try {
			pstatement = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = pstatement.executeQuery();
			while (rs.next()) {
				Video video = new Video();
				video.setVideoId(rs.getInt("videoId"));
				video.setTarget(rs.getString("target").toUpperCase());
				video.setSize(rs.getInt("size"));
				video.setVideoFile(rs.getString("videoFile"));
				video.setDescription(rs.getString("description"));
				video.setTracking(rs.getBoolean("tracking"));

				trackingVideoList.add(video);
			}

			pstatement.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("getTrackingVideos():", e);
		}

		return SUCCESS;
	}
	
	public String subscription() {
		conn = cp.getVideotrackingConn();
		query = "select imageFile, description, rating from videos";

		animals = new ArrayList<Animal>();
		// important!!!!!!
		// ResultSet default setting is TYPE_FORWARD_ONLY, so need to define
		// ResultSet.TYPE_SCROLL_INSENSITIVE and ResultSet.CONCUR_READ_ONLY
		try {
			pstatement = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = pstatement.executeQuery();
			while (rs.next()) {
				Animal animal = new Animal();
				animal.setImageFile(rs.getString("imageFile"));
				animal.setDescription(rs.getString("description"));

				animals.add(animal);
			}

			pstatement.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		message = "Subscribed successfully..";

		return SUCCESS;
	}

	public String trackingVideo() {
		videoId = Integer.parseInt(request.getParameter("videoId"));

		videoFile = getVideoFile(videoId);
		request.setAttribute("videoFile", videoFile);

		return SUCCESS;
	}

	public String playTracking() {
		int x = Integer.parseInt(request.getParameter("x").split("\\.")[0]);
		int y = Integer.parseInt(request.getParameter("y").split("\\.")[0]);
		int w = Integer.parseInt(request.getParameter("w").split("\\.")[0]);
		int h = Integer.parseInt(request.getParameter("h").split("\\.")[0]);
		int videoWidth = Integer.parseInt(request.getParameter("videoWidth").split("\\.")[0]);
		int videoHeight = Integer.parseInt(request.getParameter("videoHeight").split("\\.")[0]);
		
		System.out.println(x + "," + y + "," + w + "," + h);
		logger.debug("Coordinates: " + x + "," + y + "," + w + "," + h);
		logger.debug("Video size (width, height): " + videoWidth + "," + videoHeight);

		videoId = Integer.parseInt(request.getParameter("videoId"));
//		videoFile = getVideoFile(videoId);
		Video video = VideoDao.instance.getVideo(videoId);
		
		String filePath = "c:\\videotracking";

		TrackingFile trackingFile = new TrackingFile(video.getVideoFile());
		String sourceFile = trackingFile.getSourceFile();
		String outputFile = trackingFile.getOutputFile();
		
		logger.debug("Generating tracking video.");
		logger.debug("Source file name: " + AppInfo.getInstance().getHostPath()+sourceFile);
		
		//please use c:\videotracking folder
		String source = filePath + sourceFile;
		
		//********************************************************
		//do not use web folder
//		String source = AppInfo.getInstance().getHostPath() + sourceFile;
		//********************************************************
		
		String output = AppInfo.getInstance().getHostPath() + outputFile;
//		request.setAttribute("outputFile", output);
		//change output to videoFile
		request.setAttribute("outputFile", outputFile.replace("\\", "/"));
		AppInfo appInfo = AppInfo.getInstance();
		
		appInfo.setTrackingDone(false);
		
		JniMain jni = new JniMain();
		appInfo.setTrackingDone( jni.run(output, video.getWidth(), video.getHeight(), source, x, y, w, h));
		
//		synchronized(MainAction.class){			
//			System.loadLibrary("libTracking");
//			TrackingJNI trackingJNI = new TrackingJNI();
//			trackingJNI.initExportation(output, video.getWidth(), video.getHeight());
//			appInfo.setTrackingDone( trackingJNI.trackingMethod(source, x, y, w, h, ""));
//			trackingJNI.unInitExportation();
//		}
		
		while(!appInfo.isTrackingDone()){
			
		}
		
		logger.debug("Tracking video generated.");
		logger.debug("Output file name: " + AppInfo.getInstance().getHostPath()+outputFile);
		
		String appFile = outputFile.replace("\\", "/");
		video.setVideoFile(appFile);
		video.setTracking(true);
		VideoDao.instance.addVideo(video);
		
		System.out.println("tracking done.." + appInfo.isTrackingDone());
		logger.debug("tracking done.." + appInfo.isTrackingDone());
		
		return SUCCESS;
	}

	String getVideoFile(int videoId) {
		String videoFile = "";
		conn = cp.getVideotrackingConn();
		String query = "select videoFile from videos where videoId = ?";
		try {
			pstatement = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstatement.setInt(1, videoId);
			rs = pstatement.executeQuery();

			while (rs.next()) {
				videoFile = rs.getString("videoFile");
			}

			request.setAttribute("videoFile", videoFile);
			pstatement.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("getVideoFile(int videoId): " , e);
		}

		return videoFile;
	}
	
	public String admin(){
		videoList = new ArrayList<Video>();
		for(Video video: VideoDao.instance.getModel().values()){
			videoList.add(video);
		}
		
		return SUCCESS;
	}
	
	public String deleteVideo(){
		videoId = Integer.parseInt(request.getParameter("videoId"));
		VideoDao.instance.deleteVideo(videoId);
		videoList = new ArrayList<Video>();
		for(Video video: VideoDao.instance.getModel().values()){
			videoList.add(video);
		}
		
		return SUCCESS;
	}
	
	public String deleteAllVideos(){
		VideoDao.instance.deleteAllVideo();
		return SUCCESS;
	}
}
