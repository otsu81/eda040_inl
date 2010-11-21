package server;


import org.apache.log4j.Logger;

import se.lth.cs.fakecamera.*;

/**
 * A class that handle the capture of images from a camera periodically.
 * @author
 * 
 */

public class ImageCaptureThread extends Thread {

	private ImageBuffer imageBuffer;
	private Axis211A axis;
	private byte[] data;
	private int readBytes;
	
	private Logger log = Logger.getLogger(ImageCaptureThread.class);
	

	/**
	 * 
	 * @param imageBuffer
	 */
	public ImageCaptureThread(CameraServer server) {
		this.imageBuffer = server.getImageBuffer();
		this.axis = new Axis211A();
//		this.axis = new Axis211A(address, port);
		data = new byte[Axis211A.IMAGE_BUFFER_SIZE];
		axis.connect();
	}
	
	public void run() {
		log.debug("Starting image capture");
		
		long t, diff;
		t = System.currentTimeMillis();
		while(true) {
			t += 40;
 			readBytes = axis.getJPEG(data, 0);
			imageBuffer.setImage(new ServerImage(data, readBytes));
			diff = t - System.currentTimeMillis();
			try {
				Thread.sleep(diff);
			} catch (InterruptedException e) {
				log.warn("Interrupted!");
				// TODO: handle exception
			}
		}
		
	}

}
