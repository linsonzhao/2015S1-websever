package com.webcam;

/*  
 * Captures the camera stream with OpenCV  
 * Search for the faces  
 * Display a circle around the faces using Java
 */

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.util.List;

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

import com.github.sarxos.webcam.Webcam;

public class FaceDetection {

	private CascadeClassifier face_cascade;
	private BufferedImage image_det;
	private BufferedImage image_raw;
	private boolean play;
	private boolean update;
	private List<Webcam> webcamList;
	private int index;

	public FaceDetection(int index) {

		face_cascade = CamLibrary.getFace_cascade();
		play = true;
		update = false;
		this.index = index;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public List<Webcam> getWebcamList() {
		webcamList = Webcam.getWebcams();
		
		Webcam.shutdown();
		return webcamList;
	}

	private BufferedImage MatToBufferedImage(Mat matBGR) {
		long startTime = System.nanoTime();
		int width = matBGR.width(), height = matBGR.height(), channels = matBGR
				.channels();
		byte[] sourcePixels = new byte[width * height * channels];
		matBGR.get(0, 0, sourcePixels);
		// create new image and get reference to backing data
		image_det = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

		final byte[] targetPixels = ((DataBufferByte) image_det.getRaster()
				.getDataBuffer()).getData();
		System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
		long endTime = System.nanoTime();
		System.out.println(String.format("Elapsed time: %.2f ms",
				(float) (endTime - startTime) / 1000000));

		return image_det;
	}

	private Mat detect(Mat inputframe) {
		Mat mRgba = new Mat();
		Mat mGrey = new Mat();
		MatOfRect faces = new MatOfRect();
		inputframe.copyTo(mRgba);
		inputframe.copyTo(mGrey);
		Imgproc.cvtColor(mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(mGrey, mGrey);

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

	public void updateDetImage() {

		setUpdate(true);
		VideoCapture capture = new VideoCapture(index);
		Mat webcam_image_det = new Mat();

		if (capture.isOpened()) {
			while (play) {
					capture.read(webcam_image_det);
					if (!webcam_image_det.empty()) {
						webcam_image_det = detect(webcam_image_det);
						image_det = MatToBufferedImage(webcam_image_det);
					} else {
						System.out.println(" -- Break!");
						capture.release();
						break;
					}
			}
		}
		System.out.println("Update Image is completed...");
		capture.release();
		
		setUpdate(false);
	}
	
	public void updateRawImage() {

		setUpdate(true);
		VideoCapture capture = new VideoCapture(index);
		Mat webcam_image_det = new Mat();

		if (capture.isOpened()) {
			while (play) {
					capture.read(webcam_image_det);
					if (!webcam_image_det.empty()) {
						image_raw = MatToBufferedImage(webcam_image_det);
					} else {
						System.out.println(" raw image -- Break!");
						capture.release();
						break;
					}
			}
		}
		System.out.println("Update raw Image is completed...");
		capture.release();
		
		setUpdate(false);
	}

	public byte[] getRawImageBytes() {
		byte[] imageBytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		if (!isUpdate()) {
			Thread thread = new Thread() {
				public void run() {
					updateRawImage();
				}
			};
			thread.start();
		}

		while (image_raw == null) {
			// wait for camera image...//
			// System.out.println("Waiting for camera image...");
		}

		try {
			ImageIO.write(image_raw, "jpg", baos);
			baos.flush();
			imageBytes = baos.toByteArray();
			baos.close();
		} catch (Exception e) {
			System.out.println("WebcamStream error...");
			e.printStackTrace();
		}

		return imageBytes;
	}
	
	public byte[] getDetImageBytes() {
		byte[] imageBytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		if (!isUpdate()) {
			Thread thread = new Thread() {
				public void run() {
					updateDetImage();
				}
			};
			thread.start();
		}

		while (image_det == null) {
			// wait for camera image...//
			// System.out.println("Waiting for camera image...");
		}

		try {
			ImageIO.write(image_det, "jpg", baos);
			baos.flush();
			imageBytes = baos.toByteArray();
			baos.close();
		} catch (Exception e) {
			System.out.println("WebcamStream error...");
			e.printStackTrace();
		}

		return imageBytes;
	}

	public static void main(String[] args) {

		CamLibrary.loadLibrary();
		FaceDetection detection = new FaceDetection(0);
		byte[] dd = detection.getDetImageBytes();
		
		FaceDetection detection2 = new FaceDetection(1);
		byte[] dd2 = detection2.getDetImageBytes();
	}
}