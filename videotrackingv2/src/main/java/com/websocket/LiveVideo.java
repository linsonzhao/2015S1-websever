package com.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.webcam.CamControl;
import com.webcam.FaceDetection;
import com.webcam.CamDeviceSessionHandler;

@ServerEndpoint("/livevideo")
public class LiveVideo {

	private static final Set<Session> sessions = Collections
			.synchronizedSet(new HashSet<Session>());
	private FaceDetection faceDetection;
	private FaceDetection faceDetection2;

	public LiveVideo() {
		faceDetection = CamControl.getInstance().getFaceDetection1();
		faceDetection2 = CamControl.getInstance().getFaceDetection2();
	}

	@OnOpen
	public void onOpen(Session session) throws IOException, EncodeException {
		session.setMaxBinaryMessageBufferSize(1024 * 512);
		sessions.add(session);
		
		System.out.println("session open");
	}

	@OnMessage
	public void processVideo(String message, Session session) {
		System.out.println("Session id: " + session.getId());
		faceDetection.setPlay(true);
		faceDetection2.setPlay(true);

		boolean toggle = CamControl.getInstance().isToggle();
		
		try {
			byte[] imageInByte;
			
			if(toggle){
				imageInByte = faceDetection2.getDetImageBytes();
			}
			else{
				imageInByte = faceDetection.getDetImageBytes();
			}

			ByteBuffer buf = ByteBuffer.wrap(imageInByte);

			for (Session session2 : sessions) {
				session2.getBasicRemote().sendBinary(buf);
			}

		} catch (Exception ioe) {
			System.out.println("Error sending message " + ioe.getMessage());
		}
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Goodbye !");
		sessions.remove(session);

		if (sessions.isEmpty()) {
			faceDetection.setPlay(false);
			faceDetection2.setPlay(false);
		}
	}

}
