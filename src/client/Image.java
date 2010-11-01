package client;

import java.util.Observable;

/**
 * 
 * @author
 * 
 */
public class Image extends Observable {

	private boolean motion;
	private long timestamp;
	private byte[] imagedata;

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
		return null;
	}

}
