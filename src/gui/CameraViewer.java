package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.CameraClient;
import client.DelayableImageBuffer;

public class CameraViewer extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ImagePanel panel1;
	private ImagePanel panel2;
	private JButton connectButton1;
	private JButton connectButton2;
	private JButton disconnectButton1;
	private JButton disconnectButton2;
	private JPanel west;
	private JPanel east;
	private JLabel delayLabel1;
	private JLabel delayLabel2;
	private JLabel cam1Status;
	private JLabel cam2Status;
	private CameraClient client;
	private DelayableImageBuffer camBuffer1;
	private DelayableImageBuffer camBuffer2;
	private UpdateClass thread1;
	private UpdateClass thread2;
	private boolean cam1IsAlive;
	private boolean cam2IsAlive;

	public CameraViewer() {

		client = new CameraClient();
		cam1IsAlive = false;
		cam2IsAlive = false;

		setTitle("Axis Camera Security");

		west = new JPanel(new GridBagLayout());
		fillWestPanel();
		east = new JPanel(new GridBagLayout());
		fillEastPanel();

		JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(west, BorderLayout.WEST);
		mainPanel.add(east, BorderLayout.EAST);
		add(mainPanel);

		connectButton1.addActionListener(this);
		connectButton2.addActionListener(this);
		disconnectButton1.addActionListener(this);
		disconnectButton2.addActionListener(this);

		setSize(700, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		thread1 = new UpdateClass(1);
		thread2 = new UpdateClass(2);
		thread1.start();
		thread2.start();

	}

	public ImagePanel getPanel(int i) {
		if (i == 1) {
			return panel1;
		} else {
			return panel2;
		}
	}
	
	public boolean isCamAlive(int i) {
		if (i == 1) {
			return cam1IsAlive;
		} else {
			return cam2IsAlive;
		}
	}
	
	public DelayableImageBuffer getBuffer(int i){
		
		if (i == 1) {
			return camBuffer1;
		} else {
			return camBuffer2;
		}
		
	}

	private void fillWestPanel() {

		panel1 = new ImagePanel();
		panel1.setPreferredSize(new Dimension(320, 240));
		connectButton1 = new JButton("Connect to Camera 1");
		disconnectButton1 = new JButton("Disconnect");
		disconnectButton1.setVisible(false);
		delayLabel1 = new JLabel("Delay: ");
		JLabel camLabel = new JLabel("Camera 1");
		cam1Status = new JLabel("Offline");
		cam1Status.setForeground(Color.RED);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 0, 0);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		west.add(camLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		west.add(cam1Status, c);

		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		west.add(panel1, c);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		west.add(connectButton1, c);

		c.gridx = 1;
		c.gridy = 3;
		west.add(disconnectButton1, c);

		c.gridx = 0;
		c.gridy = 4;
		west.add(delayLabel1, c);

	}

	private void fillEastPanel() {

		panel2 = new ImagePanel();
		panel2.setPreferredSize(new Dimension(320, 240));
		connectButton2 = new JButton("Connect to Camera 2");
		disconnectButton2 = new JButton("Disconnect");
		disconnectButton2.setVisible(false);
		delayLabel2 = new JLabel("Delay: ");
		JLabel camLabel = new JLabel("Camera 2");
		cam2Status = new JLabel("Offline");
		cam2Status.setForeground(Color.RED);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 0, 0, 0);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		east.add(camLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		east.add(cam2Status, c);

		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		east.add(panel2, c);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		east.add(connectButton2, c);

		c.gridx = 0;
		c.gridy = 3;
		east.add(disconnectButton2, c);

		c.gridx = 0;
		c.gridy = 4;
		east.add(delayLabel2, c);
	}

	class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		ImageIcon icon;

		public ImagePanel() {
			super();
			icon = new ImageIcon();
			JLabel label = new JLabel(icon);
			add(label, BorderLayout.CENTER);
			// this.setSize(200, 200);
			setBackground(Color.BLACK);
		}

		public void refresh(byte[] data) {
			Image theImage = getToolkit().createImage(data);
			getToolkit().prepareImage(theImage, -1, -1, null);
			icon.setImage(theImage);
			icon.paintIcon(this, this.getGraphics(), 5, 5);
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		CameraViewer cam = new CameraViewer();
		cam.show();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// System.out.println(split[0] + " och " + split[1]);

		if (e.getSource().equals(connectButton1) || e.getSource().equals(connectButton2)) {

			String input = JOptionPane.showInputDialog("Input: Camera Address");
			String[] split = input.split(":");
			DelayableImageBuffer buffer = client.connect(split[0], Integer.parseInt(split[1]));

			if (e.getSource().equals(connectButton1)) {
				camBuffer1 = buffer;
				cam1IsAlive = true;
				cam1Status.setText("Online");
				cam1Status.setForeground(Color.GREEN);
				connectButton1.setVisible(false);
				disconnectButton1.setVisible(true);
			}

			if (e.getSource().equals(connectButton2)) {
				camBuffer2 = buffer;
				cam2IsAlive = true;
				cam2Status.setText("Online");
				cam2Status.setForeground(Color.GREEN);
				connectButton2.setVisible(false);
				disconnectButton2.setVisible(true);
			}
		}
		if (e.getSource().equals(disconnectButton1)) {

			cam1IsAlive = false;
			connectButton1.setVisible(true);
			cam1Status.setText("Offline");
			cam1Status.setForeground(Color.RED);
			disconnectButton1.setVisible(false);
			panel1.setBackground(Color.BLACK);
		}
		
		if (e.getSource().equals(disconnectButton2)) {

			cam2IsAlive = false;
			cam2Status.setText("Offline");
			cam2Status.setForeground(Color.RED);
			connectButton2.setVisible(true);
			disconnectButton2.setVisible(false);
			panel2.setBackground(Color.BLACK);
		}
	}

	private class UpdateClass extends Thread {

		private int i;

		private UpdateClass(int i) {
			this.i = i;
		}

		public void run() {
			while (true) {
				try {
					sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			//	System.out.println(isCamAlive(i) + " " + i);
				if (isCamAlive(i)) {
					try {
						getPanel(i).refresh(getBuffer(i).getCurrent().getImagedata());
					} catch (Exception e) {

					}
				}
			}
		}

	}
}