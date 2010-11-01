package client;

import java.util.ArrayList;

/**
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
	public void connect(String host, int port) {
		;
	}

	/**
	 * 
	 * @return
	 */
	public boolean checkMotion() {
		return true;
	}

}
