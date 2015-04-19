package com.webcam;

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

@ServerEndpoint("/livevideo")
public class LiveStream {

	private static final Set<Session> sessions = Collections
			.synchronizedSet(new HashSet<Session>());
	private static FaceDetection faceDetection;
	private static FaceDetection faceDetection2;
	private CamDeviceSessionHandler sessionHandler;
	private boolean toggle;

	public LiveStream() {
		faceDetection = new FaceDetection(0);
		faceDetection2 = new FaceDetection(1);
		sessionHandler = CamDeviceSessionHandler.getInstance();
		toggle = false;
	}

	@OnOpen
	public void onOpen(Session session) throws IOException, EncodeException {
		session.setMaxBinaryMessageBufferSize(1024 * 512);
		sessions.add(session);
		faceDetection.setPlay(true);
		faceDetection2.setPlay(true);
		
		System.out.println("session open");

	}

	@OnMessage
	public void processVideo(String message, Session session) {
		System.out.println("INsite process Video");
		System.out.println("message: " + message);

		try {
			byte[] imageInByte;
			if(toggle){
				imageInByte = faceDetection2.getImageBytes();
				toggle = false;
			}
			else{
				imageInByte = faceDetection.getImageBytes();
				toggle = true;
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
