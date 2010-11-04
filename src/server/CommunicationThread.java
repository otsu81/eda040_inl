package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A class that sends images from the ImageBuffer to a CameraConnection in
 * client package. Depending on mode, Idle or Movie, it will sends a image every
 * 5 s or 4 ms.
 * 
 * @author
 * 
 */

public class CommunicationThread extends Thread {

	private boolean mode;
	private Socket s;
	private OutputStream out;
	private DataOutputStream dataOut;
	private ImageBuffer imgbfr;

	public CommunicationThread(ServerSocket socket, ImageBuffer imgbfr) {

		this.imgbfr = imgbfr;

		try {
			s = socket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		
		try {
		if (!imgbfr.isEmpty()) {

			out = s.getOutputStream();
			ServerImage image = imgbfr.getImage();
			dataOut.writeInt(image.getReadBytes());
			dataOut.write(image.getData(), 0 , image.getReadBytes());
			dataOut.flush();
			
		}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
