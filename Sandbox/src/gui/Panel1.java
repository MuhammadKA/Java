package gui;

import javax.swing.*;
import java.awt.*;

public class Panel1 {

	public static void main(String[] args) {
		Panel1 gui = new Panel1();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		panel.setBackground(Color.DARK_GRAY);
		
		/* The BoxLayout constructor needs to know the component its laying out (i.e, the panel)
		 * and which axis to use */
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JButton button = new JButton("shock me!");
		JButton buttonTwo = new JButton("bliss!");
		JButton buttonThree = new JButton("huh?");

		/* Add the button to the panel and add the panel to the frame.
		 * The panel's layout manager (flow) controls the button, and the frame's layout 
		 * manager controls the panel. */
		panel.add(button);
		panel.add(buttonTwo);
		panel.add(buttonThree);
		
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.setSize(250, 200);
		frame.setVisible(true);
	}

}
