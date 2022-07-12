package gui;

import javax.swing.*;

public class SimpleGui1 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		JButton button = new JButton("Click Me!");
		
		/* This line makes the program quit as soon as you close the window 
		 * If you leave this out it will just sit there on the screen forever
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		// add the button to the frame's content pane
		frame.getContentPane().add(button);
		
		frame.setSize(300, 300);
		
		// If you forget this step, you won't see anything when you run this code
		frame.setVisible(true);
		
	}

}
