package server;

import java.util.ArrayList;

/**
 * 
 * @author
 * 
 */

/** A class to store the images before the CommunicationThread sends them to the client package*/

public class ImageBuffer {

	private ArrayList<ServerImage> images;
	
	public ImageBuffer(){
		images = new ArrayList<ServerImage>();
	}
	
	public void addImage(ServerImage image){
		
		images.add(image);
		
	}
	
}
