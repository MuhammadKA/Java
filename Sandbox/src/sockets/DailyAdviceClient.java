package sockets;

import java.io.*;
import java.net.*;

public class DailyAdviceClient {
	public void go() {
		try {
			Socket s = new Socket("127.0.0.1", 4242);
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);

			/*
			 * this readLine() is EXACTLY the same as if you were using a BufferedReader
			 * chained to a FILE. In other words, by the time you call a BufferedReader
			 * method, the reader doesn�t know or care where the characters came from.
			 */
			String advice = reader.readLine();
			System.out.println("Today you should: " + advice);

			// this closes ALL the streams
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DailyAdviceClient client = new DailyAdviceClient();
		client.go();
	}
}