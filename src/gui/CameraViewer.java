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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CameraViewer extends JFrame implements ActionListener {

private static final long serialVersionUID = 1L;
private ImagePanel panel1;
private ImagePanel panel2;
private JButton connectButton1;
private JButton connectButton2;
private JPanel west;
private JPanel east;
private JLabel delayLabel1;
private JLabel delayLabel2;

public CameraViewer(){

setTitle("Axis Camera Security");

west = new JPanel(new GridBagLayout());
fillWestPanel();
east = new JPanel(new GridBagLayout());
fillEastPanel();

JPanel mainPanel = new JPanel(new BorderLayout());
//mainPanel.setLayout(new GridLayout(1,2));
mainPanel.add(west, BorderLayout.WEST);
mainPanel.add(east, BorderLayout.EAST);
add(mainPanel);

connectButton1.addActionListener(this);
connectButton2.addActionListener(this);

setSize(1200, 800);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setResizable(false);
setVisible(true);

}

private void fillWestPanel(){

panel1 = new ImagePanel();
panel1.setPreferredSize(new Dimension(320, 240));
connectButton1 = new JButton("Connect to Camera 1");
delayLabel1 = new JLabel("Delay: ");

GridBagConstraints c = new GridBagConstraints();
c.fill = GridBagConstraints.HORIZONTAL;
c.insets = new Insets(10, 10, 0, 0);
c.gridwidth = 2;
c.gridx = 0;
c.gridy = 0;
west.add(panel1, c);

c.gridwidth = 1;
c.gridx = 0;
c.gridy = 1;
west.add(connectButton1, c);

c.gridx = 0;
c.gridy = 2;
west.add(delayLabel1, c);

}
private void fillEastPanel(){

panel2 = new ImagePanel();
panel2.setPreferredSize(new Dimension(320, 240));
panel2.setBackground(Color.GREEN);
connectButton2 = new JButton("Connect to Camera 2");
delayLabel2 = new JLabel("Delay: ");

GridBagConstraints c = new GridBagConstraints();
c.fill = GridBagConstraints.HORIZONTAL;
c.insets = new Insets(10, 0, 0, 0);
c.gridwidth = 2;
c.gridx = 0;
c.gridy = 0;
east.add(panel2, c);

c.gridwidth = 1;
c.gridx = 0;
c.gridy = 1;
east.add(connectButton2, c);

c.gridx = 0;
c.gridy = 2;
east.add(delayLabel2, c);

}

// public void refresh(byte[] data, int camera){
//
// if(camera == 0){
// panel1.refresh(data);
// }
// else{
// panel2.refresh(data);
// }
// }
//
class ImagePanel extends JPanel {
private static final long serialVersionUID = 1L;
ImageIcon icon;

public ImagePanel() {
super();
icon = new ImageIcon();
JLabel label = new JLabel(icon);
add(label, BorderLayout.CENTER);
// this.setSize(200, 200);
setBackground(Color.RED);
}

public void refresh(byte[] data) {
Image theImage = getToolkit().createImage(data);
getToolkit().prepareImage(theImage,-1,-1,null);
icon.setImage(theImage);
icon.paintIcon(this, this.getGraphics(), 5, 5);
}
}

@SuppressWarnings("deprecation")
public static void main(String[] args){

CameraViewer cam = new CameraViewer();
cam.show();

}

@Override
public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub

}



}