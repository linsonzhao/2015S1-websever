package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import dao.VideoDao;
import model.Video;

@SuppressWarnings("serial")
@WebServlet("/uploadfile")
@MultipartConfig(fileSizeThreshold=1024*1024*2,	// 2MB 
				 maxRequestSize=1024*1024*50)	// 10MB
public class UploadFile extends HttpServlet {

	public static long FILE_MAX_UPLOAD_LIMIT = 5; // 5MB  setup the max upload size here !!!!!!!!!!
	/**
	 * Name of the directory where uploaded files will be saved, relative to
	 * the web application directory.
	 */
	private static final String SAVE_DIR = "raw_video";
	private String fileName;
	private File file;
	
	public String getFile() {
		return fileName;
	}

	public void setFile(String file) {
		this.fileName = file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("struts/uploadFile");
	}
	
	/**
	 * handles file upload
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response){
		// gets absolute path of the web application
		String appPath = request.getServletContext().getRealPath("");
		String appPath2 = "c:\\videotracking";
		// constructs path of the directory to save uploaded file
		String savePath = appPath + File.separator + SAVE_DIR;
		String savePath2 = appPath2 + File.separator + SAVE_DIR;
		Video video = new Video();
		
		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		
		System.out.println(request.getParameter("target"));
		System.out.println(request.getParameter("description"));
		System.out.println(request.getParameter("file"));
		
		try {
			String message = null;
			Part part = request.getPart("file");
			long size = part.getSize();
			if(size > FILE_MAX_UPLOAD_LIMIT * 1024 * 1024){
				message = "File exceeds max upload limit " + FILE_MAX_UPLOAD_LIMIT + " MB";
				request.setAttribute("message", message);
				response.sendRedirect("struts/error?message="+message);
			}else{
				fileName = extractFileName(part);
				System.out.println("fileName from part: " + fileName);
				String[] sub = fileName.split("\\\\");
				for(String s: sub){
					fileName = s;
					System.out.println("s is: " + s);
				}
				part.write(savePath + File.separator + fileName);
				
				File source = new File(savePath + File.separator + fileName);
				File dest = new File(savePath2 + File.separator + fileName);
				FileUtils.copyFile(source, dest);
				
				System.out.println("target: " + request.getParameter("target"));
				video.setTarget(request.getParameter("target"));
				video.setDescription(request.getParameter("description"));
				video.setVideoFile("/" + SAVE_DIR + "/" + fileName);
				VideoDao.instance.addVideo(video);
				
				response.sendRedirect("struts/upload");
			}
		}		
		catch (Exception e) {
			try {
				response.sendRedirect("struts/upload");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}
}