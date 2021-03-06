package com.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.webcam.CamControl;
import com.webcam.FaceDetection;
import com.webcam.CamDeviceSessionHandler;

@ServerEndpoint("/detection1")
public class DetectionWSS1 {

	private static final Set<Session> sessions = Collections
			.synchronizedSet(new HashSet<Session>());
	private static FaceDetection faceDetection;

	public DetectionWSS1() {
		faceDetection = CamControl.getInstance().getFaceDetection1();
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

		try {
			byte[] imageInByte;

			imageInByte = faceDetection.getDetImageBytes();

			ByteBuffer buf = ByteBuffer.wrap(imageInByte);

			for (Session session2 : sessions) {
				session2.getBasicRemote().sendBinary(buf);
			}

		} catch (Exception ioe) {
			System.out.println("Error sending message - camera1:" + ioe.getMessage());
		}
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Camera1: Goodbye !");
		sessions.remove(session);
		
		if (sessions.isEmpty()) {
			faceDetection.setPlay(false);
		}
	}

}
