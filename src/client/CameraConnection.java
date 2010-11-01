package client;

public class CameraConnection extends Thread{
	
	private String socket;
	private CameraHandler ch;
	private Image image;
	
	public CameraConnection(CameraHandler ch){
		this.ch = ch;
		image = new Image();
	}
	
	public void run(){
		
	}

}
