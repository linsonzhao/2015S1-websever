package com.webcam;

import org.opencv.objdetect.CascadeClassifier;

import dao.AppInfo;

public class CamLibrary {
	private static CamLibrary camLibrary;

	public CamLibrary() {

	}
	
	public static CamLibrary getInstance(){
		if(camLibrary==null){
			camLibrary = new CamLibrary();
		}
		return camLibrary;
	}

	synchronized public static void loadLibrary() {
		// Load the native library.
		System.loadLibrary("opencv_java249");
	}

	public static CascadeClassifier getFace_cascade() {
		AppInfo appInfo = AppInfo.getInstance();
		
		// /////////////////////////////////////////
//		CascadeClassifier face_cascade = new CascadeClassifier(appInfo.getHostPath()
//				+ "\\xml\\haarcascade_frontalface_alt.xml");
		CascadeClassifier face_cascade = new CascadeClassifier(
		 "C:/temp/haarcascade_frontalface_alt.xml");
		if (face_cascade.empty()) {
			System.out.println("--(!)Error in loading");
		} else {
			System.out.println("Face classifier loaded up");
		}
		// ///////////////////////////////////////
		return face_cascade;
	}
}
