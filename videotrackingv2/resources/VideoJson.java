package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import model.Video;
import dao.VideoDao;

public class VideoJson {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String name;
	
	public VideoJson(UriInfo uriInfo, Request request, String name) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.name = name;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Video getProductHTML() {
		Video video = VideoDao.instance.getModel().get(name);
		
		return video;
	}
}
