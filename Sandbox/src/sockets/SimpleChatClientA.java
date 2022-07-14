/*
 * This version just send a message over a socket and doesn't receive any thing.
 * */

package sockets;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClientA {
	JTextField outgoing;
	PrintWriter writer;
	Socket sock;

	public void go() {
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		setUpNetworking();
		frame.setSize(400, 500);
		frame.setVisible(true);
	} // close go

	private void setUpNetworking() {
		try {
			// Use localhost so you can test the client and server on one machine
			sock = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} // close setUpNetworking

	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				// Remember, the writer is chained to the output stream from the Socket,
				// so whenever we do a println(), it goes over the network to the server!
				writer.println(outgoing.getText());
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	} // close SendButtonListener inner class

	public static void main(String[] args) {
		new SimpleChatClientA().go();
	}
} // close outer class