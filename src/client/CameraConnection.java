package client;

import java.net.Socket;

/**
 * 
 * @author
 * 
 */
public class CameraConnection extends Thread {

	private Socket socket;
	private CameraHandler ch;
	private Image image;

	/**
	 * 
	 * @param ch
	 */
	public CameraConnection(CameraHandler ch) {
		this.ch = ch;
		image = new Image();
	}

	public void run() {
		;
	}

}
