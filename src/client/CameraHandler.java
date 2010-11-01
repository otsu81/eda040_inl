package client;

import java.util.ArrayList;

public class CameraHandler {
	
	private ArrayList<CameraConnection> connections;
	private boolean isMovieMode;
	
	public CameraHandler(){
		connections = new ArrayList<CameraConnection>();
	}
	
	
	public void connect(String host, int port){
	}
	
	
	public boolean checkMotion(){
		return true;
	}

}
