package server;


/**
 * 
 * @author
 * 
 */

/** A class to store the images before the CommunicationThread sends them to the client package*/

public class ImageBuffer {

	/*private LinkedList<ServerImage> images;
	
	public ImageBuffer(){
		images = new LinkedList<ServerImage>();
	}
	
	public void addImage(ServerImage image){
		
		images.add(image);
		
	}
	
	public ServerImage getImage() {
		return images.pop();
	}*/
	
	ServerImage image;
	
	public synchronized void setImage(ServerImage image){
		this.image = image;
		notifyAll();
	}
	
	public synchronized ServerImage getNextImage() {
		while (image == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServerImage tmpImage = image;
		image = null;
		return tmpImage;
	}	
}
