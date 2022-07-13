package sockets;

import java.io.*;
import java.net.*;

/*
 * This server code can’t accept a request from a client until it has finished with the current client 
 * and started the next iteration of the infinite loop 
 * (where it sits at the accept() call until a request comes in, at which time it
 * makes a Socket with the new client and starts the process over again).
 * */
public class DailyAdviceServer {
	String[] adviceList = { "Take smaller bites", "Go for the tight jeans. No they do NOT make you look fat.",
			"One word:inappropriate", "Just for today, be honest.	Tell your boss what you *really* think",
			"You might	want to rethink that haircut." };

	public void go() {
		try {
			// ServerSocket makes this server application ‘listen’ for client requests on
			// port 4242 on the machine this code is running on.
			ServerSocket serverSock = new ServerSocket(4242);

			// The server goes into a permanent loop, waiting for (and servicing) client
			// request
			while (true) {

				// the accept method blocks (just sits there) until a request comes in, and then
				// the method returns a Socket (on some anonymous port) for communicating
				// with the client
				Socket sock = serverSock.accept();
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				String advice = getAdvice();
				writer.println(advice);
				writer.close();
				System.out.println(advice);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} // close go

	private String getAdvice() {
		int random = (int) (Math.random() * adviceList.length);
		return adviceList[random];
	}

	public static void main(String[] args) {
		DailyAdviceServer server = new DailyAdviceServer();
		server.go();
	}
}