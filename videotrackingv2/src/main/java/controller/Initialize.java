package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.Scheduler;

import dao.AppInfo;
import dao.VideoDao;

/**
 * Servlet implementation class StartUp
 */
@WebServlet(name = "initialize", urlPatterns = { "/initialize" }, loadOnStartup = 1)
public class Initialize extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//get log4j
	private static final Logger logger = Logger.getLogger(Initialize.class);
	
	private String hostUrl;
	private Scheduler scheduler;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Initialize() {
		super();
		// TODO Auto-generated constructor stub
		hostUrl = "http://localhost:8080/videotracking";
		scheduler = new Scheduler();
		scheduler.scheduleControl();
		
		System.out.println("Starting...");
		logger.debug("Starting...");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		AppInfo appInfo = AppInfo.getInstance();
		appInfo.setHostPath(config.getServletContext().getRealPath(""));
		appInfo.setHostUrl(hostUrl);
		appInfo.loadConfig();
		
//		OSValidator os = new OSValidator();
//		File folder;
//		if (os.isWindows()) {
//			folder = new File(appInfo.getHostPath() + "\raw_video");
//		} else {
//			folder = new File(appInfo.getHostPath() + "/raw_video");
//		}
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
		
//		VideoDao.instance.updateVideoMap("raw_video");
//		VideoDao.instance.updateVideoMap("tracking_video");

		System.out.println(appInfo.getApikey());
		System.out.println(appInfo.getHostPath());
		System.out.println(appInfo.getHostUrl());
		logger.debug("Host path: " + appInfo.getHostPath());
		
		//important...
		VideoDao.instance.updateVideoMap();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see Servlet#destroy()
	 * Need to cancel and purge timer in destroy method, 
	 * otherwise timer instance will keep running after app restarted.
	 */
	public void destroy() {
		scheduler.stopTimer();
	}

}
