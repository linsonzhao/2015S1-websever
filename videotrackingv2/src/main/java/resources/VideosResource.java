package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.TrackingFile;

import controller.MainAction;
import jni.JniMain;
import jni.TrackingJNI;
import dao.AppInfo;
import dao.VideoDao;
import model.Video;

@Path("/videos")
public class VideosResource {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private AppInfo appInfo;
	//get log4j
	private static final Logger logger = Logger.getLogger(MainAction.class);
	
	public VideosResource(){
		appInfo = AppInfo.getInstance();
	}

	// Return the list of videos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Video> getVideosBrowser(@QueryParam("apikey") String apikey) {
		if(apikey.equalsIgnoreCase(AppInfo.getInstance().getApikey())){
			List<Video> videos = new ArrayList<Video>();
			videos.addAll(VideoDao.instance.getModel().values());
			return videos;
		}
		else
			return null;
	}
	
	@GET
	@Path("/query")
	public Response getUsers(
		@QueryParam("param1") int param1,
		@QueryParam("param2") int param2,
		@QueryParam("param3") List<String> param3) {
 
		return Response
		   .status(200)
		   .entity("param1 : " + param1 + ", param2 : " + param2
			+ ", param3 " + param3.toString()).build();
	}
	
	//get coordinates (x, y, w, h);
	@Path("/coordinates")
	public VideoResource getCoordinates(
		@QueryParam("apikey") String apikey,
		@QueryParam("videoid") int videoid,
		@QueryParam("sourcefile") String sourcefile,  //not in used..
		@QueryParam("x") int x,
		@QueryParam("y") int y,
		@QueryParam("w") int w,
		@QueryParam("h") int h) {
		
		Video video = VideoDao.instance.getVideo(videoid);
		TrackingFile trackingFile = new TrackingFile(video.getVideoFile());
		String sourceFile = trackingFile.getSourceFile();
		String outputFile = trackingFile.getOutputFile();
		
		logger.debug("Generating tracking video.");
		logger.debug("Source file name: " + AppInfo.getInstance().getHostPath()+sourceFile);
		
		String filePath = "c:\\videotracking";
		String source = filePath + sourceFile;
		String output = AppInfo.getInstance().getHostPath() + outputFile;
		logger.debug("coordinates: " + x + "," + y + "," + w + "," + h );

		if(apikey.equalsIgnoreCase(AppInfo.getInstance().getApikey())){
			appInfo.setTrackingDone(false);
			
			JniMain jni = new JniMain();
			appInfo.setTrackingDone( jni.run(output, video.getWidth(), video.getHeight(), source, x, y, w, h));
			
//			synchronized(VideosResource.class){
//				System.loadLibrary("libTracking");
//				TrackingJNI trackingJNI = new TrackingJNI();
//				trackingJNI.initExportation(output, video.getWidth(), video.getHeight());
//				appInfo.setTrackingDone( trackingJNI.trackingMethod(source, x, y, w, h, ""));
//				trackingJNI.unInitExportation();
//			}
			
			while(!appInfo.isTrackingDone()){
				
			}

			logger.debug("Tracking video generated.");
			logger.debug("Output file name: " + AppInfo.getInstance().getHostPath()+outputFile);
			
			String appFile = outputFile.replace("\\", "/");
			video.setVideoFile(appFile);
			video.setTracking(true);
			VideoDao.instance.addVideo(video);
			int trackingVideoId = VideoDao.instance.getVideoId(appFile);
			
			return new VideoResource(uriInfo, request, trackingVideoId);
		}
		else
			return null;
	}
	
	// Return one video to the user in the browser
	@Path("{videoId}")
	public VideoResource getProduct(@PathParam("videoId") int videoId, @QueryParam("apikey") String apikey) {
		if(apikey.equalsIgnoreCase(AppInfo.getInstance().getApikey()))
			return new VideoResource(uriInfo, request, videoId);
		else
			return null;
	}
}
