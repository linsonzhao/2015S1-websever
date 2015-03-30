package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "videos")
public class Video {

	@Id
	@Column(name = "videoId")
	private int videoId;

	@Column(name = "target")
	private String target;

	@Column(name = "videoFile")
	private String videoFile;

	@Column(name = "size")
	private int size; // how many kB

	@Column(name = "description")
	private String description;

	@Column(name = "tracking")
	private boolean tracking;

	@Column(name = "createdTime")
	private Date createdTime;

	@Column(name = "duration")
	private int duration; // how many seconds

	@Column(name = "codecId")
	private String codecId;

	@Column(name = "width")
	private int width;

	@Column(name = "height")
	private int height;

	public Video() {

	}

	public Video(int videoid, String target, String videoFile, int size,
			String description, boolean tracking) {
		super();
		this.videoId = videoid;
		this.target = target;
		this.videoFile = videoFile;
		this.size = size;
		this.description = description;
		this.tracking = tracking;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isTracking() {
		return tracking;
	}

	public void setTracking(boolean tracking) {
		this.tracking = tracking;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCodecId() {
		return codecId;
	}

	public void setCodecId(String codecId) {
		this.codecId = codecId;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
