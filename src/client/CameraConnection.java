package client;

/**
 * 
 * @author
 * 
 */
public class CameraConnection extends Thread {

	private String socket;
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
