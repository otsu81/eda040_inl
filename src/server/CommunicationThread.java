package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * A class that sends images from the ImageBuffer to a CameraConnection in client package. Depending on mode, 
 * Idle or Movie, it will sends a image every 5 s or 4 ms.
 * @author
 * 
 */

public class CommunicationThread extends Thread {

	private CameraServer server;
	private Logger log = Logger.getLogger(CommunicationThread.class);
	
	public CommunicationThread(CameraServer server) {
		this.server = server;
	}
	
	public void run() {
		log.debug("Creating socket");
		ServerSocket serverSocket = null;
		try {
			 serverSocket = new ServerSocket(server.getPort());			
		} catch (IOException e) {
			log.error("Error binding socket! Terminating.");
			log.error(e);
			System.exit(1);
		}

		while (true) {
			long prevImage = 0;
			ImageBuffer buffer = server.getImageBuffer();
			try {
				log.debug("Waiting for connection");
				Socket s = serverSocket.accept();
				
				log.debug("Creating streams");
				OutputStream out = s.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
	
				
				while (true) {
					ServerImage image = buffer.getNextImage();
					
					long diff = System.currentTimeMillis() - prevImage;
					if (server.getMode() == ServerMode.MOVIE || diff > 5000) {
						int size = image.getSize();
						dos.writeInt(size);
						dos.write(image.getData(), 0, size);
						dos.flush();
						prevImage = System.currentTimeMillis();
					}
				}
			} catch (IOException e) {
				log.info("Exception caught, restarting socket");
				log.debug(e);
			}
		}
	}

}
