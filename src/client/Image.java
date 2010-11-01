package client;

import java.util.Observable;



public class Image extends Observable {
	
	private boolean motion;
	private long timestamp;
	private byte[] imagedata;
	
	
	public boolean getMotion(){
		return false;
	}
	
	public long getTimestamp(){
		return 0;
	}
	
	public byte[] getImagedata(){
		return null;
	}

}
