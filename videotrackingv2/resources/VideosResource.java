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

@Path("/videos")
public class VideosResource {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private AppInfo appInfo;
	
	public VideosResource(){
		appInfo = AppInfo.getInstance();
	}

	// Return the list of videos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Video> getVideosBrowser(@QueryParam("apikey") String apikey) {
		if(apikey.equalsIgnoreCase(apikey)){
			List<Video> videos = new ArrayList<Video>();
			videos.addAll(VideoDao.instance.getModel().values());
			return videos;
		}
		else
			return null;
	}
	
	// Return the list of videos to the user for application
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Video> getVideosApp(@QueryParam("apikey") String apikey) {
		if(apikey.equalsIgnoreCase(apikey)){
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
	
//	@Path("{name}")
//	public VideoResource getProduct(@PathParam("name") String name) {
//		return new VideoResource(uriInfo, request, name);
//	}
	
	// Return one video to the user in the browser
	@Path("{name}")
	public VideoResource getProduct(@PathParam("name") String name, @QueryParam("apikey") String apikey) {
		if(apikey.equalsIgnoreCase(apikey))
			return new VideoResource(uriInfo, request, name);
		else
			return null;
	}
}
