package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyServletA extends HttpServlet {

	/**
	 * Override the doGet for simple HTTP GET messages. The web server calls this
	 * method, handing you the client’s request (you can get data out of it) and a
	 * ‘response’ object that you’ll use to send back a response (a page).
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This tells the server (and browser) what kind of ‘thing’ is coming back from
		 * the server as a result of this servlet running.
		 */
		response.setContentType("text/html");
		/**
		 * The response object gives us an output stream to ‘write’ information back out
		 * to the server.
		 */
		PrintWriter out = response.getWriter();
		String message = "If you’re reading this, it worked!";
		/**
		 * What we ‘write’ is an HTML page! The page gets delivered through the server
		 * back to the browser, just like any other HTML page, even though this is a
		 * page that never existed until now. In other words, there’s no .html file
		 * somewhere with this stuff in it.
		 */
		out.println("<HTML><BODY>");
		out.println("<H1>" + message + "</H1>");
		out.println("</BODY></HTML>");
		out.close();
	}
}