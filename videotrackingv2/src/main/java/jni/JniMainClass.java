package jni;


public class JniMainClass {
	
	private static JniMainClass jni = null;
	
	public JniMainClass(){
		
	}

	public static JniMainClass getInstance() {
		if (jni == null) {
			jni = new JniMainClass();
			System.loadLibrary("libTracking");
		}
		return jni;
	}
	
	public void run(String sourceFile, int x, int y, int w, int h, String outputFile) {
		//for previous version 
		String[] sub = outputFile.split("\\.");
		String trackingFile = sub[0] + ".mpg";
		//for new version
//		String trackingFile = outputFile;
				
		TrackingJNI trackingJNI = new TrackingJNI();
		System.out.println("\n"	+ trackingJNI.trackingMethod(sourceFile, x, y, w, h, trackingFile));
	}

}