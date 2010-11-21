package client;


/**
 * Represents an image (frame) from a camera. Apart from JPEG bytes, this class
 * contains additional metadata such as timestamp and motion-detection data.
 * 
 * 
 * @author
 * 
 */
public class CameraImage {

	private boolean motion;
	private long timestamp;
	private byte[] imagedata;
	
	public CameraImage(byte[] data) {
		imagedata = data;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getMotion() {
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public long getTimestamp() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] getImagedata() {
		return imagedata;
	}

}
