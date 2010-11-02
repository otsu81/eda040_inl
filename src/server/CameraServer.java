package server;

import java.net.Socket;

import se.lth.cs.fakecamera.MotionDetector;

/**
 * A class that sends images from a camera through a socket to a CameraConnection object.
 * It contains one thread, ImageCaptureThread, that every 4 ms get a image from the camera.
 * It contains another thread that depending on mode, Idle or Movie, sends a image every
 * 5 s or 4 ms.
 * The mode is determined depending on information from a MotionDetector class.
 * 
 * 
 * @author
 * 
 */



public class CameraServer {

	private ImageBuffer imgbfr;
	private boolean mode;
	private MotionDetector md;
	private Socket socket;
	private ImageCaptureThread ict;
	private CommunicationThread ct;
	private String hostAddress;
	private int hostPort;

	/**
	 * 
	 */
	public CameraServer() {
		ict = new ImageCaptureThread(imgbfr, hostAddress, hostPort);
		ct = new CommunicationThread(socket);

	}

	/**
	 * 
	 */
	public void setImageBuffer() {

	}

	/**
	 * 
	 */
	public void getImageBuffer() {

	}

	/**
	 * 
	 * @param mode
	 */
	public void setMode(boolean mode) {

	}

}
