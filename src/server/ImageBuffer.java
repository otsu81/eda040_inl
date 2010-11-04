package server;

import java.util.LinkedList;

/**
 * A class to store the images before the CommunicationThread sends them to the client package
 * @author
 * 
 */

public class ImageBuffer {

	private LinkedList<ServerImage> images;
	
	public ImageBuffer(){
		images = new LinkedList<ServerImage>();
	}
	
	public void addImage(ServerImage image){
		
		images.addLast(image);
		
	}
	
	public ServerImage getImage(){
		return images.poll();
	}
	
	public boolean isEmpty(){
		
		return images.isEmpty();
	}
	
}
