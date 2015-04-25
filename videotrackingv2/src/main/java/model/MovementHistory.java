package model;

import javax.persistence.*;

@Entity
@Table(name="movement_history")
public class MovementHistory {

	@Column(name="object_id")
	private int object_id;
	
	@Column(name="detected_video_id")
	private int detected_video_id;
	
	@Column(name="location_x")
	private int location_x;
	
	@Column(name="location_y")
	private int location_y;
	
	@Column(name="frame_no_in_video")
	private int frame_no_in_video;
	
	public MovementHistory(){
		
	}

	public int getObject_id() {
		return object_id;
	}

	public void setObject_id(int object_id) {
		this.object_id = object_id;
	}

	public int getDetected_video_id() {
		return detected_video_id;
	}

	public void setDetected_video_id(int detected_video_id) {
		this.detected_video_id = detected_video_id;
	}

	public int getLocation_x() {
		return location_x;
	}

	public void setLocation_x(int location_x) {
		this.location_x = location_x;
	}

	public int getLocation_y() {
		return location_y;
	}

	public void setLocation_y(int location_y) {
		this.location_y = location_y;
	}

	public int getFrame_no_in_video() {
		return frame_no_in_video;
	}

	public void setFrame_no_in_video(int frame_no_in_video) {
		this.frame_no_in_video = frame_no_in_video;
	}
	
}
