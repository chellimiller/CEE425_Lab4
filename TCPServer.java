/*
 * File Name:     TCPServer.java
 * Created By:    Michelle Miller
 * Creation Date: 2016.10.27
 *
 * UW Stout - CEE 425: Computer Networking
 *            Lab 4:   Simple Socket Program
 */

import java.io.*;
import java.net.*;

class TCPServer {
	public static void main (String argv[]) throws Exception {
		
		// Input/output strings
		String clientIn;
		String serverOut;
		
		// Socket settings
		int servPortNum;
		
		try {
			
			// Create handshake socket
			ServerSocket hnSocket = new ServerSocket(servPortNum);
			
			// Create input/output streams
			DataInputStream fromClient;
			DataOutputStream toClient;
			
			
			while(true) {
				Socket cnSocket = hnSocket.accept();
				
				// Set up input/output streams
				fromClient = new DataInputStream(cnSocket.getInputStream());
				toClient = new DataOutputStream(cnSocket.getOutputStream());
				
				// Buffer for data input stream
				int bufferLen = fromClient.available();
				byte[] buffer = new byte[bufferLen];
				
				// Read new input from server		
				fromClient.readFully(buffer);
				
				// Convert buffer to String
				for (byte b:buffer) {
					// Cast byte to char and add to clientIn
					char c = (char)b;
					clientIn = clientIn + b;
				}
				
				// Print out client input
				System.out.println("Received: " + clientIn);
				
				// Convert client input to uppercase
				serverOut = clientIn.toUpperCase() + '\n';
				System.out.println("Modified: " + serverOut);
				
				// Send new string back to client
				toClient.writeBytes(serverOut);
			}
			
		} catch (Exception error) {
			System.out.println(error);
			
		} finally {
			
			// Close data streams
			fromClient.close();
			toClient.close();
			
			// Close sockets
			cnSocket.close();
			hnSocket.close();
		}
	}
}
 
 class TCPServer {
	public static void main (String argv[]) throws Exception {
		
		// Input/output strings
		String input;
		String output;
		
		// Settings
		int serverPortNum;
		
		// Create socket for handshake
		ServerSocket hnSocket = new ServerSocket(serverPortNum);
		
		while(1) {
			// Create connection socket and accept handshake
			Socket coSocket = hnSocket.accept();
			
			
		}
		
	}
}
