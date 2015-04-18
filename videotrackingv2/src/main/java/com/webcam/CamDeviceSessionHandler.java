/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

import model.CamDevice;

/**
 *
 * @author linson
 */
@ApplicationScoped
public class CamDeviceSessionHandler {

    private final static Set sessions = new HashSet<>();
    private final static Set devices = new HashSet<>();
    private final static HashMap<Session, Integer> webcamMap = new HashMap<Session, Integer>();
    private static CamDeviceSessionHandler sessionHandler;
    
    public static CamDeviceSessionHandler getInstance(){
    	if(sessionHandler==null){
    		sessionHandler = new CamDeviceSessionHandler();
    	}
    	return sessionHandler;
    }
    
    public void addSession(Session session){
        sessions.add(session);
        Iterator iterator = devices.iterator();
        while(iterator.hasNext()){
            CamDevice device = (CamDevice)iterator.next();
            JsonObject addMessage = createAddMessage(device);
            sendToSession(session, addMessage);
            System.out.println("device:" + device.toString());
        }
    }
    
    public void removeSession(Session session){
        sessions.remove(session);
    }
    
    public List getDevices() {
        return new ArrayList<>(devices);
    }

    public HashMap<Session, Integer> getWebcamMap() {
		return webcamMap;
	}
    
    public void setWebcamMap(Session session, Integer index){
    	if(webcamMap.get(session)!=null){
    		webcamMap.put(session, index);
    	}
    }

	public void addDevice(CamDevice device) {
        devices.add(device);
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(addMessage);
    }

    public CamDevice getDeviceById(int id) {
        Iterator iterator = devices.iterator();
        while(iterator.hasNext()){
            CamDevice device = (CamDevice)iterator.next();
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }
    
    public void clearDevices(){
    	if(devices!=null){
            JsonProvider provider = JsonProvider.provider();
            JsonObject clearMessage = provider.createObjectBuilder()
                    .add("action", "clear")
                    .add("size", devices.size())
                    .build();
            devices.clear();
            sendToAllConnectedSessions(clearMessage);
    	}
    }

    private JsonObject createAddMessage(CamDevice device) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device.getId())
                .add("name", device.getName())
                .add("type", device.getType())
                .add("status", device.getStatus())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        Iterator iterator = sessions.iterator();
        while(iterator.hasNext()){
            Session session = (Session)iterator.next();
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
            
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(CamDeviceSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
