package com.webcam;

public class CamControl {
	private static CamControl camControl;
	private CamMap camMap;
	
	public CamControl(){
		camMap = CamMap.getInstance();
	}
	
	public static CamControl getInstance(){
		if(camControl==null){
			camControl = new CamControl();
		}
		return camControl;
	}

	
}
