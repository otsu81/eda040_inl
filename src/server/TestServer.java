package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import se.lth.cs.fakecamera.Axis211A;

public class TestServer {
	public static Logger log = Logger.getLogger(TestServer.class);
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		try {
			log.debug("Creating socket");
			ServerSocket socket = new ServerSocket(12345);

			
			log.debug("Waiting for connection");
			Socket s = socket.accept();
			
			log.debug("Reading bytes from camera");
			Axis211A cam = new Axis211A();
			cam.connect();
			byte[] bytes = new byte[Axis211A.IMAGE_BUFFER_SIZE];
			
			while (true) {
				int read = cam.getJPEG(bytes, 0);
				
				log.debug("Read " + read + " bytes");
				
				OutputStream out = s.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				log.trace("Writing int: " + read);
				dos.writeInt(read);
				log.trace("Writing bytes: " + bytes);
				dos.write(bytes, 0, read);
				dos.flush();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
