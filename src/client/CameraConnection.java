package client;

import java.net.Socket;

/**
 * This class represents a connection to a camera server.
 * 
 * When started, this thread establishes in- and output streams on the specified
 * socket.
 * 
 * The connection then expects images to be sent from the other end of the
 * socket. The received bytes are offered to a DelayableImageBuffer object and
 * the thread now waits for the next stream of bytes from the server.
 * 
 * @author
 * 
 */
public class CameraConnection extends Thread {

	private Socket socket;
	private CameraHandler ch;
	private DelayableImageBuffer buffer;

	/**
	 * 
	 * @param ch
	 */
	public CameraConnection(CameraHandler handler) {
		ch = handler;
		buffer = new DelayableImageBuffer();
	}
	
	public DelayableImageBuffer getBuffer() {
		return buffer;
	}

	@Override
	public void run() {
		;
	}

}
