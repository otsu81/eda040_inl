package client;

import java.util.ArrayList;

import org.apache.log4j.PropertyConfigurator;

/**
 * A class representing a number of camera connections.
 * 
 * Has the responsibility of synchronizing streams from different cameras.
 * 
 * @author
 * 
 */
public class CameraClient {

	private ArrayList<CameraConnection> connections;
	private boolean isMovieMode;

	/**
	 * 
	 */
	public CameraClient() {
		PropertyConfigurator.configure("log4j.conf");
		connections = new ArrayList<CameraConnection>();
	}

	/**
	 * 
	 * @param host
	 * @param port
	 */
	public DelayableImageBuffer connect(String host, int port) {
		DelayableImageBuffer buffer = new DelayableImageBuffer();
		CameraConnection conn = new CameraConnection(buffer, host, port);
		connections.add(conn);
		conn.start();
		return buffer;
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
	
	public static void main(String[] args) {
		CameraClient client = new CameraClient();
		client.connect("localhost", 12345);
		
	}
}
