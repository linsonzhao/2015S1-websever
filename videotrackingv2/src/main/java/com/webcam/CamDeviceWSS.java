/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcam;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.github.sarxos.webcam.Webcam;

import model.CamDevice;
import model.Device;

/**
 *
 * @author linson
 */
@ApplicationScoped
@ServerEndpoint("/camdevices")
public class CamDeviceWSS {

    @Inject
    private static CamDeviceSessionHandler sessionHandler;
    private FaceDetection faceDetection;
    
    public CamDeviceWSS(){
    	faceDetection = new FaceDetection(0);
    }

    @OnOpen
    public void onOpen(Session session) {

    	sessionHandler = CamDeviceSessionHandler.getInstance();
    	sessionHandler.addSession(session);
        System.out.println("onOpen...");
        System.out.println("session id: " + session.getId());
    }

    @OnClose
    public void close(Session session){
        sessionHandler.removeSession(session);
        System.out.println("Goodbye session: " + session.getId());
        
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(CamDeviceWSS.class.getName()).log(Level.WARNING, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {

    	System.out.println(message);    	
    	
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("update".equals(jsonMessage.getString("action"))) {
            	sessionHandler.clearDevices();
            	
            	List<Webcam> webcamList = faceDetection.getWebcamList();
            	for(int i=0;i<webcamList.size();i++){
            		CamDevice device = new CamDevice();
            		device.setId(i);
            		device.setName(webcamList.get(i).getName());
            		device.setType("webcam");
            		device.setStatus("");
            		sessionHandler.addDevice(device);
            	}
            	Webcam.shutdown();
            }
            if ("changewebcam".equals(jsonMessage.getString("action"))) {

            	String webcamName = jsonMessage.getString("device");
            	
            	List<Webcam> webcamList = faceDetection.getWebcamList();

            	for(int i=0;i<webcamList.size();i++){
            		if(webcamName.equalsIgnoreCase(webcamList.get(i).getName())){
            			sessionHandler.setWebcamMap(session, i);
            			break;
            		}
            	}
            }
        }
    }
}
