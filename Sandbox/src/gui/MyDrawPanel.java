package gui;

import java.awt.*;
import javax.swing.*;


public class MyDrawPanel extends JPanel {
	
	/* This is Big Important Graphics method
	 * You will NEVER call this yourself. 
	 * The system calls it and says: 
	 * "Here's a nice fresh drawing surface, of type Graphics that you may paint on now".
	 * */
	public void paintComponent (Graphics g) {
		/* Imagine g is the painting machine
		 * You tell it what color to paint with
		 * and what shape to paint 
		 * */
		
		/* Fill the entire panel with black (the default color) */
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Use random start color
		int red 	= (int) (Math.random() * 256);
		int green 	= (int) (Math.random() * 256);
		int blue 	= (int) (Math.random() * 256);
		Color startColor = new Color(red, green, blue);
		
		// Use random end color
		red 	= (int) (Math.random() * 256);
		green 	= (int) (Math.random() * 256);
		blue 	= (int) (Math.random() * 256);
		Color endColor = new Color(red, green, blue);
		
		// Use gradient color available in Graphics2D
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(70, 170, startColor, 170, 250, endColor);
		// set the virtual paint brush to a gradient instead of a solid color
		g2d.setPaint(gradient);
		g2d.fillOval(70, 170, 100, 100);
	}
	
	public static void main(String[] args) {
		// your own customized widget that you add to a JFrame
		MyDrawPanel panel = new MyDrawPanel();
		
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setVisible(true);
	}


}
