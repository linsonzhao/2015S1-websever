package com.webcam;

/*  
 * Captures the camera stream with OpenCV  
 * Search for the faces  
 * Display a circle around the faces using Java
 */

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import dao.AppInfo;

public class FaceDetection {
	private static FaceDetection faceDetection;
	private CascadeClassifier face_cascade;
	private BufferedImage image;
	AppInfo appInfo;
	public boolean play;
	Thread thread;

	public FaceDetection(){
		appInfo = AppInfo.getInstance();
		FaceDetection.loadLibrary();
		play = true;
		
		thread = new Thread(){
			public void run(){
				FaceDetection faceDetection = FaceDetection.getInstance();
				faceDetection.updateImage();
			}
		};
		thread.start();
		
	}
	
	public static FaceDetection getInstance(){
		if(faceDetection==null){
			faceDetection = new FaceDetection();
		}
		
		return faceDetection;
	}
	
	synchronized public static void loadLibrary(){
		// Load the native library.
		System.loadLibrary("opencv_java249");
	}
	
	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	private BufferedImage MatToBufferedImage(Mat matBGR) {
		long startTime = System.nanoTime();
		int width = matBGR.width(), height = matBGR.height(), channels = matBGR
				.channels();
		byte[] sourcePixels = new byte[width * height * channels];
		matBGR.get(0, 0, sourcePixels);
		// create new image and get reference to backing data
		image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		final byte[] targetPixels = ((DataBufferByte) image.getRaster()
				.getDataBuffer()).getData();
		System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
		long endTime = System.nanoTime();
		System.out.println(String.format("Elapsed time: %.2f ms",
				(float) (endTime - startTime) / 1000000));
		
		return image;
	}
	
	private Mat detect(Mat inputframe) {
		Mat mRgba = new Mat();
		Mat mGrey = new Mat();
		MatOfRect faces = new MatOfRect();
		inputframe.copyTo(mRgba);
		inputframe.copyTo(mGrey);
		Imgproc.cvtColor(mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(mGrey, mGrey);
		
		///////////////////////////////////////////
		face_cascade = new CascadeClassifier(
				appInfo.getHostPath() + "\\xml\\haarcascade_frontalface_alt.xml");
//		face_cascade = new CascadeClassifier(
//				"C:/temp/haarcascade_frontalface_alt.xml");
		if (face_cascade.empty()) {
			System.out.println("--(!)Error in loading");
		} else {
			System.out.println("Face classifier loaded up");
		}
		/////////////////////////////////////////
		
		face_cascade.detectMultiScale(mGrey, faces);
		System.out.println(String.format("Detected %s faces",
				faces.toArray().length));
		for (Rect rect : faces.toArray()) {
			Point center = new Point(rect.x + rect.width * 0.5, rect.y
					+ rect.height * 0.5);
			Core.ellipse(mRgba, center, new Size(rect.width * 0.5,
					rect.height * 0.5), 0, 0, 360, new Scalar(255, 0, 255), 4,
					8, 0);
		}
		return mRgba;
	}
	
	synchronized public void updateImage() {

		VideoCapture capture = new VideoCapture(0);
		Mat webcam_image = new Mat();
		int count = 1;
		if (capture.isOpened()) {
			while (play) {
				capture.read(webcam_image);
				if (!webcam_image.empty()) {
					webcam_image = detect(webcam_image);
					image = MatToBufferedImage(webcam_image); 
				} else {
					System.out.println(" -- Break!");
				}
				
				System.out.println("count: " + count);
			}
		}
		System.out.println("Update Image is completed...");
		capture.release();

	}
	
	public byte[] getImageBytes(){
		byte[] imageBytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		while(image==null){
			//wait for camera image...//
//			System.out.println("Waiting for camera image...");
		}
		
        try {
			ImageIO.write(image, "jpg", baos);
	        baos.flush();
	        imageBytes = baos.toByteArray();
	        baos.close();
		} catch (Exception e) {
			System.out.println("WebcamStream error...");
			e.printStackTrace();
		}
        
		return imageBytes;
	}
	
	public static void main(String[] args){
//		FaceDetection faceDetection = FaceDetection.getInstance();
//		faceDetection.getImageBytes();
		
//		FaceDetection.loadLibrary();
//		VideoCapture capture = new VideoCapture(0);
//		Mat webcam_image = new Mat();
//		
//		if(capture.isOpened()){
//			while(true){
//				capture.read(webcam_image);
//				System.out.println(webcam_image.width() + ", " + webcam_image.height());
//			}
//		}
		
		FaceDetection.loadLibrary();
		VideoCapture capture = new VideoCapture(0);
		Mat webcam_image = new Mat();
		int count = 1;
		if (capture.isOpened()) {
			while (++count<60) {
				capture.read(webcam_image);
				
				System.out.println("count: " + count);
			}
		}
		System.out.println("Update Image is completed...");
	}
}