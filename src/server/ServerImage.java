package server;


/**
 * A class to represent the data and the length of the data
 *  
 * @author
 * 
*/

public class ServerImage {

	private byte[] data;
	private int readBytes;
	
	public ServerImage(byte[] data, int readBytes){
		
		this.data = data;
		this.readBytes = readBytes;
	}
	
	/**
	 * The byte[] data have a timestamp of the JPEG image is part of the image header (byte offset 25-29)
	 * @return
	 */
	public byte[] getData(){
		return data;
	}

	public int getReadBytes() {
		return readBytes;
	}
	
}
