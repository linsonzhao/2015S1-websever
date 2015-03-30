package jni;

public class JniMain {

	public boolean run(String output, int videoWidth, int videoHeight,
			String source, int x, int y, int w, int h) {
		boolean flag = false;

		synchronized (JniMain.class) {
			System.loadLibrary("libTracking");
			TrackingJNI trackingJNI = new TrackingJNI();
			trackingJNI.initExportation(output, videoWidth, videoHeight);
			flag = trackingJNI.trackingMethod(source, x, y, w, h, "");
			trackingJNI.unInitExportation();
		}
		
		return flag;
	}

}
