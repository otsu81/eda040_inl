package client;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import server.ImageBuffer;

public class TestClient extends JFrame {
	
	public TestClient() {
		super("blalasdf");
		CameraClient client = new CameraClient();
		DelayableImageBuffer bfr = client.connect("localhost", 12345);
		JLabel lbl = new JLabel();
		setSize(500,500);
		add(lbl);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		while (true) {
			client.Image im = bfr.getCurrent();
			System.out.println(im.getImagedata());
			ImageIcon icn = new ImageIcon(im.getImagedata());
			lbl.setIcon(icn);
		}
	}

	public static void main(String[] args) {
		new TestClient();
	}
}
