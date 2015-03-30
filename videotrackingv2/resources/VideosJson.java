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

import dao.AppInfo;
import dao.VideoDao;
import model.Video;

@Path("/videos_json")
public class VideosJson {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private AppInfo appInfo;
	
	public VideosJson(){
		appInfo = AppInfo.getInstance();
	}

	// Return the list of videos to the user in the browser
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Video> getVideosBrowser(@QueryParam("apikey") String apikey) {
		if(apikey.equalsIgnoreCase(apikey)){
			List<Video> videos = new ArrayList<Video>();
			videos.addAll(VideoDao.instance.getModel().values());
			return videos;
		}
		else
			return null;
	}
	
	
	// Return one video to the user in the browser
	@Path("{name}")
	public VideoJson getProduct(@PathParam("name") String name, @QueryParam("apikey") String apikey) {
		if(apikey.equalsIgnoreCase(apikey))
			return new VideoJson(uriInfo, request, name);
		else
			return null;
	}
}
