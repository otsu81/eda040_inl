package server;

import java.net.Socket;

import se.lth.cs.fakecamera.MotionDetector;

public class CameraServer {
	
	private ImageBuffer imgbfr;
	private boolean mode;
	private MotionDetector md;
	private Socket socket;
	private ImageCaptureThread ict;
	private CommunicationThread ct;
	
	public CameraServer(){
		ict = new ImageCaptureThread(imgbfr);
		ct = new CommunicationThread();
		
	}
	
	
	public void setImageBuffer(){
		
	}

	public void getImageBuffer(){
		
	}
	
	public void setMode(boolean mode){
		
	}
	
}
