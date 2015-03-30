package jni;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.util.concurrent.TimeUnit;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

public class TrackingJNI {
	public native boolean trackingMethod(String s, int i, int j, int k, int l,
			String out);

	public void initExportation(String s, int w, int h) {
		m_outfile = s;
		if (m_outfile != "") {
			m_first = false;
			m_writer = ToolFactory.makeWriter(m_outfile);
			m_width = w;
			m_height = h;
			m_streamIndex = m_writer.addVideoStream(0, 0,
					ICodec.ID.CODEC_ID_H264, w, h);
		}
	}

	public void unInitExportation() {
		if (m_outfile != "") {
			m_writer.close();
			m_writer = null;
		}
	}

	private void callBackMethod(int frameIndex, byte[] imgData, int imgSize) {

		BufferedImage bi = createRGBImage(imgData, m_width, m_height);

		if (m_writer != null && m_outfile != "") {
			if (m_first == false) {
				m_first = true;
				m_startTime = System.nanoTime();
			}

			m_writer.encodeVideo(m_streamIndex, bi, System.nanoTime()
					- m_startTime, TimeUnit.NANOSECONDS);
		}
	}

	private static BufferedImage createRGBImage(byte[] bytes, int width,
			int height) {
		DataBufferByte buffer = new DataBufferByte(bytes, bytes.length);
		ColorModel cm = new ComponentColorModel(
				ColorSpace.getInstance(ColorSpace.CS_sRGB),
				new int[] { 8, 8, 8 }, false, false, Transparency.OPAQUE,
				DataBuffer.TYPE_BYTE);
		return new BufferedImage(cm, Raster.createInterleavedRaster(buffer,
				width, height, width * 3, 3, new int[] { 2, 1, 0 }, null),
				false, null);
	}

	private IMediaWriter m_writer = null;
	private String m_outfile = "";
	private boolean m_first = true;
	private long m_startTime;
	private int m_streamIndex;
	private int m_width;
	private int m_height;
}
