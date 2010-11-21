package server;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class TestClient {
	public static Logger log = Logger.getLogger(TestClient.class);
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		try {
			log.debug("Creating socket");
			Socket s = new Socket("localhost", 12345);
			InputStream in = s.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			int m = 0;
			
			while (true) {
				int n = dis.readInt();
				log.debug("Read an int: " + n);
				int read = 0;
				int totRead = 0;
				byte bytes[] = new byte[1024];
				FileOutputStream fos = new FileOutputStream("test" + m + ".jpg");
				int toRead = Math.min(bytes.length, n-totRead);
				while ((read = dis.read(bytes, 0, toRead)) >= 0 && totRead < n) {
					fos.write(bytes, 0, read);
					totRead += read;
					toRead = Math.min(bytes.length, n-totRead);
				}
				log.debug("Total read: " + totRead + " size: " + n);
				fos.close();
				log.debug("Writing to file");
				m++;
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
