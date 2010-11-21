package client;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 * This class represents a connection to a camera server.
 * 
 * When started, this thread establishes in- and output streams on the specified
 * socket.
 * 
 * The connection then expects images to be sent from the other end of the
 * socket. The received bytes are offered to a DelayableImageBuffer object and
 * the thread now waits for the next stream of bytes from the server.
 * 
 * @author
 * 
 */
public class CameraConnection extends Thread {

	private String host;
	private int port;
	private DelayableImageBuffer buffer;
	private Logger log = Logger.getLogger(CameraConnection.class);

	/**
	 * 
	 * @param ch
	 */
	public CameraConnection(DelayableImageBuffer buffer, String host, int port) {
		this.host = host;
		this.port = port;
		this.buffer = buffer;
	}
	
	public DelayableImageBuffer getBuffer() {
		return buffer;
	}

	@Override
	public void run() {
		try {
			log.debug("Creating socket");
			Socket s = new Socket(host, port);
			InputStream in = s.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			while (true) {
				int n = dis.readInt();
				//log.debug("Read an int: " + n);
				int read = 0;
				int totRead = 0;
				byte bytes[] = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream(n);
				int toRead = Math.min(bytes.length, n-totRead);
				while ((read = dis.read(bytes, 0, toRead)) >= 0 && totRead < n) {
					baos.write(bytes, 0, read);
					totRead += read;
					toRead = Math.min(bytes.length, n-totRead);
				}
				//log.debug("Total read: " + totRead + " size: " + n);
				baos.close();
				buffer.offer(new CameraImage(baos.toByteArray()));
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
