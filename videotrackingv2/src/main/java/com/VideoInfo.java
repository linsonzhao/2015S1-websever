package com;

import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

public class VideoInfo {

	private String filename;
	private int duration;
	private int fileSize;
	private String codecId;
	private int width;
	private int height; 
	
	public VideoInfo(String filename){
		this.filename = filename;
		// first we create a Xuggler container object
		IContainer container = IContainer.make();
		// we attempt to open up the container
		int result = container.open(filename, IContainer.Type.READ, null);
		// check if the operation was successful
		if (result < 0)
			throw new RuntimeException("Failed to open media file");
		// query how many streams (for example: stream 1 is video, stream 2 is audio) the call to open found
		int numStreams = container.getNumStreams();
		// query for the total duration
//		duration = (int)container.getDuration()/(1000);
		// query for the file size
		fileSize = (int)container.getFileSize()/1000;
		// iterate through the streams to print their meta data

		for (int i = 0; i < numStreams; i++) {
			// find the stream object
			IStream stream = container.getStream(i);
			// get the pre-configured decoder that can decode this stream;
			IStreamCoder coder = stream.getStreamCoder();

			if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
				codecId = coder.getCodecID().toString();
				duration = (int) stream.getDuration()/1000;  //how many seconds
				width = coder.getWidth();
				height = coder.getHeight();
			}
		}
	}

	public String getFilename() {
		return filename;
	}

	public int getDuration() {
		return duration;
	}

	public int getFileSize() {
		return fileSize;
	}

	public String getCodecId() {
		return codecId;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}