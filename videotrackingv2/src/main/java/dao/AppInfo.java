package dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.OSValidator;

public class AppInfo {
	
	private static AppInfo appInfo;
	private String hostPath;
	private String hostUrl;
	private Properties props;
	private String apikey;
	private String raw_video_path;
	private String tracking_video_path;
	private boolean trackingDone;
	
	public AppInfo(){
		props = new Properties();
	}
	
	public static AppInfo getInstance(){
		if(appInfo==null){
			appInfo = new AppInfo();
		}
		return appInfo;
	}

	public String getHostPath() {
		return hostPath;
	}

	public void setHostPath(String hostPath) {
		this.hostPath = hostPath;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getRaw_video_path() {
		return raw_video_path;
	}

	public void setRaw_video_path(String raw_video_path) {
		this.raw_video_path = raw_video_path;
	}

	public String getTracking_video_path() {
		return tracking_video_path;
	}

	public void setTracking_video_path(String tracking_video_path) {
		this.tracking_video_path = tracking_video_path;
	}

	public boolean isTrackingDone() {
		return trackingDone;
	}

	public void setTrackingDone(boolean trackingDone) {
		this.trackingDone = trackingDone;
	}

	public void loadConfig(){
		
		String propsFile;
		OSValidator os = new OSValidator();
		if(os.isWindows()){
			//Windows version
			propsFile = hostPath + "\\config\\config.properties";
		}
		else{
			//unix or linux or mac version
			propsFile = hostPath + "/config/config.properties";
		}
		InputStream input = null;
		
		try{
			input = new FileInputStream(propsFile);
			props.load(input);
			apikey = props.getProperty("apikey");

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
