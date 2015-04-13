package com.webcam;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.xuggle.xuggler.video.ConverterFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class WebcamStream {
	private static WebcamStream webcamStream;
	private Webcam webcam;
	
	public WebcamStream(){
		webcam = Webcam.getDefault();
		Dimension size = WebcamResolution.QVGA.getSize();
		webcam.setViewSize(size);
	}
	
	public static WebcamStream getInstance(){
		if(webcamStream==null){
			webcamStream = new WebcamStream();
		}
		return webcamStream;
	}
	
	public void open(){
		if(webcam!=null&&(!webcam.isOpen())){
			webcam.open();
		}
	}
	
	public void close(){
		webcam.close();
	}
	
	public byte[] getWebcamStream(){
		byte[] imageBytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        this.open();

        System.out.println("webcam: " + webcam.getName());
        System.out.println("webcam: " + webcam.getWebcamListenersCount());

        BufferedImage image = ConverterFactory.convertToType(
                webcam.getImage(), BufferedImage.TYPE_3BYTE_BGR);

        try {
			ImageIO.write(image, "jpg", baos);
	        baos.flush();
	        imageBytes = baos.toByteArray();
	        baos.close();
		} catch (IOException e) {
			System.out.println("WebcamStream error...");
			e.printStackTrace();
		}

        return imageBytes;
	}

    public void openWebcam() throws Throwable {
        
        byte[] imageBytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        System.out.println("webcam: " + webcam.getName());
        System.out.println("webcam: " + webcam.getWebcamListenersCount());
        
        webcam.open(true);

        BufferedImage image = ConverterFactory.convertToType(
                webcam.getImage(), BufferedImage.TYPE_3BYTE_BGR);

        ImageIO.write(image, "jpg", baos);
        baos.flush();
        imageBytes = baos.toByteArray();
        baos.close();
        
        InputStream in = new ByteArrayInputStream(imageBytes);
			BufferedImage bImageFromConvert = ImageIO.read(in);
 
			ImageIO.write(bImageFromConvert, "jpg", new File(
					"/Users/linson/Documents/temp/linson.jpg"));
        System.out.println("size: " + imageBytes.length);

    }
}
