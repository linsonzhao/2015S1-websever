package model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="detected_video")
public class DetectedVideo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="path")
	private String path;
	
	@Column(name="width")
	private int width;
	
	@Column(name="height")
	private int height;
	
	@Column(name="type_id")
	private int type_id;
	
	@Column(name="raw_video_id")
	private int raw_video_id;
	
	@Column(name="time_created")
	private Date time_created;
	
	@Column(name="duration")
	private int duration;
	
	public DetectedVideo(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getRaw_video_id() {
		return raw_video_id;
	}

	public void setRaw_video_id(int raw_video_id) {
		this.raw_video_id = raw_video_id;
	}

	public Date getTime_created() {
		return time_created;
	}

	public void setTime_created(Date time_created) {
		this.time_created = time_created;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
