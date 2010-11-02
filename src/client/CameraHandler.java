package client;

import java.util.ArrayList;

/**
 * A class representing a number of camera connections.
 * 
 * Has the responsibility of synchronizing streams from different cameras.
 * 
 * @author
 * 
 */
public class CameraHandler {

	private ArrayList<CameraConnection> connections;
	private boolean isMovieMode;

	/**
	 * 
	 */
	public CameraHandler() {
		connections = new ArrayList<CameraConnection>();
	}

	/**
	 * 
	 * @param host
	 * @param port
	 */
	public DelayableImageBuffer connect(String host, int port) {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean checkMotion() {
		return true;
	}
	
	/**
	 * 
	 */
	public void synchronizeBuffers() {
		
	}
}
