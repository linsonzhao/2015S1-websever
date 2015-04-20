package com.webcam;

public class CamControl {
	private static CamControl camControl;
	private CamMap camMap;
	private FaceDetection faceDetection1;
	private FaceDetection faceDetection2;
	private boolean toggle;
	
	public CamControl(){
		camMap = CamMap.getInstance();
		faceDetection1 = new FaceDetection(0);
		faceDetection2 = new FaceDetection(1);
		updateToggler();
	}
	
	public static CamControl getInstance(){
		if(camControl==null){
			camControl = new CamControl();
		}
		return camControl;
	}

	public FaceDetection getFaceDetection1() {
		return faceDetection1;
	}

	public FaceDetection getFaceDetection2() {
		return faceDetection2;
	}

	public boolean isToggle() {
		return toggle;
	}

	private void toggler(){
		
		while(true){
			toggle = false;

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toggle = true;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void updateToggler(){
		Thread thread = new Thread(){
			public void run(){
				toggler();
			}
		};
		thread.start();
	}
}
