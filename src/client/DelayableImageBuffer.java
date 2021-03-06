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
	
	private LinkedList<CameraImage> buffer;
	
	public DelayableImageBuffer() {
		buffer = new LinkedList<CameraImage>();
	}

	/**
	 * 
	 * @param im
	 */
	public synchronized void offer(CameraImage im) {
		buffer.offer(im);
		if (buffer.size() > MAX_SIZE) {
			buffer.pop();
		}
		notifyAll();
	}

	/**
	 * 
	 * @return
	 */
	public synchronized CameraImage getCurrent() {
		while (buffer.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return buffer.pop();
	}

	/**
	 * 
	 * @param delay
	 */
	public synchronized void setDelay(int delay) {

	}

}
