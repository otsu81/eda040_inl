package server;


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
	

	/**
	 * 
	 * @param imageBuffer
	 */
	public ImageCaptureThread(ImageBuffer imageBuffer, String hostAddress, int port) {
		this.imageBuffer = imageBuffer;
		this.axis = new Axis211A();
//		this.axis = new Axis211A(address, port);
		data = new byte[Axis211A.IMAGE_BUFFER_SIZE];
		axis.connect();
		run();
	}
	
	public void run() {

		while(true){
			
			readBytes = axis.getJPEG(data, 0);
			imageBuffer.addImage(new ServerImage(data, readBytes));
			
		}
		
	}

}
