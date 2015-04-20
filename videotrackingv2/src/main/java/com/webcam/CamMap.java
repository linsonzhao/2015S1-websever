package com.webcam;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import com.github.sarxos.webcam.Webcam;

public class CamMap {

	private static CamMap camMap;
	private Map<Session, String> sessionWebcamMap;
	private List<Webcam> webcamList;
	private Map<String, Integer> webcamIndexMap;
	private Map<String, BufferedImage> imageMap;

	public CamMap() {
		sessionWebcamMap = new HashMap<Session, String>();
		webcamList = new ArrayList<Webcam>();
		webcamIndexMap = new HashMap<String, Integer>();
		imageMap = new HashMap<String, BufferedImage>();
	}

	public static CamMap getInstance() {
		if (camMap == null) {
			camMap = new CamMap();
		}
		return camMap;
	}

	public List<Webcam> getWebcamList() {
		updateWebcamList();
		return webcamList;
	}
	
	public Map<String, Integer> getWebcamIndexMap() {
		webcamIndexMap.clear();
		for(int i=0;i<webcamList.size();i++){
			webcamIndexMap.put(webcamList.get(i).getName(), i);
		}
		return webcamIndexMap;
	}

	public Map<Session, String> getSessionWebcamMap(){
		return sessionWebcamMap;
	}

	public Map<String, BufferedImage> getImageMap() {
		return imageMap;
	}

	//update the webcam list won't affect running program using webcams.
	public void updateWebcamList() {
		webcamList.clear();
		webcamList = Webcam.getWebcams();
		Webcam.shutdown();
	}
	
	public void updateSessionMapping(Session session, String webcamName) {
		sessionWebcamMap.put(session, webcamName);
	}
}
