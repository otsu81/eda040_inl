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
	private static final int MAX_SIZE = 100;
	
	private LinkedList<Image> buffer;

	/**
	 * 
	 * @param im
	 */
	public synchronized void offer(Image im) {
		buffer.offer(im);
		if (buffer.size() > MAX_SIZE) {
			buffer.pop();
		}
	}

	/**
	 * 
	 * @return
	 */
	public synchronized Image getCurrent() {
		return null;
	}

	/**
	 * 
	 * @param delay
	 */
	public synchronized void setDelay(int delay) {

	}

}
