package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.Video;
import dao.VideoDao;

public class VideoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	int videoId;
	
	public VideoResource(UriInfo uriInfo, Request request, int videoId) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.videoId = videoId;
	}
	
// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Video getProductHTML() {
		Video video = VideoDao.instance.getModel().get(videoId);
		
		return video;
	}
}
