package server;

import java.net.Socket;

/**
 * A class that sends images from the ImageBuffer to a CameraConnection in client package. Depending on mode, 
 * Idle or Movie, it will sends a image every 5 s or 4 ms.
 * @author
 * 
 */

public class CommunicationThread extends Thread {

	private boolean mode;
	private Socket socket;
	
	public CommunicationThread(Socket socket){
		
	}
	
	public void run() {

	}

}
