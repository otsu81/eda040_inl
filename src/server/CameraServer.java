package server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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
	private ServerMode mode;
	private MotionDetector md;
	private ImageCaptureThread ict;
	private CommunicationThread ct;
	private String hostAddress;
	private int hostPort;
	private Logger log = Logger.getLogger(CameraServer.class);

	/**
	 * 
	 */
	public CameraServer() {
		PropertyConfigurator.configure("log4j.conf");
		
		log.info("Starting server...");
		
		imgbfr = new ImageBuffer();
		hostAddress = "";
		hostPort = 0;
		mode = ServerMode.MOVIE;
		md = new MotionDetector();
		
		ict = new ImageCaptureThread(this);
		ct = new CommunicationThread(this);
		
		log.debug("Starting image capture thread");
		ict.start();
		log.debug("Starting image sending thread");
		ct.start();

	}

	/**
	 * 
	 * @param mode
	 */
	public synchronized void setMode(ServerMode mode) {
		this.mode = mode;
	}
	
	public synchronized int getPort() {
		return 12345;
	}

	public synchronized ImageBuffer getImageBuffer() {
		return imgbfr;
	}

	public synchronized ServerMode getMode() {
		return mode;
	}
	
	
	public static void main(String[] args) {
		new CameraServer();
	}
	
}
