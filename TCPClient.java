/*
 * File Name:     TCPClient.java
 * Created By:    Michelle Miller
 * Creation Date: 2016.10.27
 *
 * UW Stout - CEE 425: Computer Networking
 *            Lab 4:   Simple Socket Program
 */

import java.io.*;
import java.net.*;

class TCPCLient {
	public static void main (String argv[]) throws Exception {
		
		// Input/output strings
		String userIn;
		String servIn;
		
		// Socket settings
		String clientName;
		int clientPortNum;
		
		try {
				
			// Create client socket
			Socket clSlocket = new Socket("clientName", clientPortNum);
			
			// Input/output streams
			BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream toServer = new DataOutputStream(clSlocket.getOutputStream());
			DataInputStream fromServer = new DataInputStream(clSlocket.getInputStream());
			
			// Read user input and send to server
			userIn = fromUser.readLine();
			toServer.writeBytes(userIn + '\n');
			
			// Buffer for data input stream
			int bufferLen = fromServer.available();
			byte[] buffer = new byte[bufferLen];
			
			// Read new input from server		
			fromServer.readFully(buffer);
			
			// Convert buffer to String
			for (byte b:buffer) {
				// Cast byte to char and add to servIn
				char c = (char)b;
				servIn = servIn + b;
			}
			
			// Print out modified string
			System.out.println("From Server: " + servIn);
			
		} catch (Exception error) {
			System.out.println(error);
			
		} finally {
			
			// Close data streams
			fromUser.close();
			toServer.close();
			fromServer.close();
			
			// Close sockets
			clSlocket.close();
		}
	}
}
