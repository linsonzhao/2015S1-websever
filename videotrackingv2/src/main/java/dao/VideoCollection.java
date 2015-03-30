package dao;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.Video;

import com.OSValidator;

public class VideoCollection {
	private Map<String, Video> videoMap = new HashMap<String, Video>();

	public Map<String, Video> getModel() {
		return videoMap;
	}

	public void updateVideoMap(String foldername) {

		OSValidator os = new OSValidator();
		File folder;
		AppInfo appInfo = AppInfo.getInstance();

		if (os.isWindows()) {
			folder = new File(appInfo.getHostPath() + "\\" + foldername);
		} else {
			folder = new File(appInfo.getHostPath() + "/" + foldername);
		}
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				System.out.println("File size is " + fileSize(listOfFiles[i]));
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
	}

	public long fileSize(File file) {
		long length = 0;
			if (file.isFile())
				length += file.length();
			
		return length;
	}
}
