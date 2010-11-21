package server;


/**
 * A class to represent the data and the length of the data
 *  
 * @author
 * 
*/

public class ServerImage {

	private byte[] data;
	private int size;
	
	public ServerImage(byte[] data, int size){
		
		this.data = data;
		this.size = size;
	}
	
	/**
	 * The byte[] data have a timestamp of the JPEG image is part of the image header (byte offset 25-29)
	 * @return
	 */
	public byte[] getData(){
		return data;
	}

	/**
	 * 
	 * @return size of image
	 */
	public int getSize() {
		return size;
	}
	
}
