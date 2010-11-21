package client;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		long prev = 0;
		while (true) {
			client.CameraImage im = bfr.getCurrent();
			System.out.println(System.currentTimeMillis() - prev);
			ImageIcon icn = new ImageIcon(im.getImagedata());
			lbl.setIcon(icn);
			prev = System.currentTimeMillis();
		}
	}

	public static void main(String[] args) {
		new TestClient();
	}
}
