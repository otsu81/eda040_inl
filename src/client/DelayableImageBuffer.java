package client;

import java.util.LinkedList;

/**
 * A buffer of Image objects capable of delaying which image are to be returned
 * when observed.
 * 
 * @author dt07fl2
 * 
 */
public class DelayableImageBuffer {
	private LinkedList<Image> buffer;

	/**
	 * 
	 * @param im
	 */
	public void offer(Image im) {

	}

	/**
	 * 
	 * @return
	 */
	public Image getCurrent() {
		return null;
	}

	/**
	 * 
	 * @param delay
	 */
	public void setDelay(int delay) {

	}

}
