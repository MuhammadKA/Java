package gui;

import javax.swing.*;
// Package containing ActionListener and ActionEvent
import java.awt.event.*;

public class SimpleGui2 implements ActionListener {

	JButton button;
	
	public static void main(String[] args) {
		SimpleGui2 gui = new SimpleGui2();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		button = new JButton("Click Me!");
		
		/* Register me interest with the button
		 * This says to the button "Add me to your list of listeners"
		 * The arguement you pass MUST be an object from a class that implements ActionListener 
		 * */
		button.addActionListener(this);
		
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/* The button calls this method to let you know an event happened
		 * It sends you an ActionEvent object as the argument, but you don't need it
		 * Knowing the event happened is enough info 
		 * */
		button.setText("You click hurts me!");
	}

}
