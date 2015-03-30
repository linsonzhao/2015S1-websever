package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import db.ConnectionPool;

public class FileAction extends ActionSupport implements ServletRequestAware {

	private static final String SAVE_DIR = "raw_video";
	public HttpServletRequest request;
	public HttpServletResponse response;
	private String name;
	private String path;
	private String tags;
	private String description;
	private ConnectionPool cp;
	private Connection conn;
	private PreparedStatement pstatement;
	private ResultSet rs;
	private String file;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public FileAction(){
		cp = ConnectionPool.getInstance();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		
		this.response = response;
	}
	
	public String upload() throws IOException, ServletException{
		
		System.out.println("filename: " + file);
		
		return SUCCESS;
	}

}
