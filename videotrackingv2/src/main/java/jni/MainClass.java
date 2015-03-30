package jni;

public class MainClass {

	public static void main(String[] args) {
		// System.loadLibrary("tracking_cpp");
		System.loadLibrary("libTracking");
		TrackingJNI trackingJNI = new TrackingJNI();

		System.out.println("\n"
				+ trackingJNI.trackingMethod("c:/temp/test/cup.mp4",
						274, 17, 92, 104,
						"c:/temp/test/cup_tracking.mpg"));
	}

}