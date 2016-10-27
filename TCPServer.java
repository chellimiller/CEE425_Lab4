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

public class TCPServer {
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
				// Create connection socket
				Socket cnSocket = hnSocket.accept();
				
				// Set up input/output streams
				fromClient = new DataInputStream(cnSocket.getInputStream());
				toClient = new DataOutputStream(cnSocket.getOutputStream());
				
				// Create buffer and read input stream
				int bufferLen = fromClient.available();
				byte[] buffer = new byte[bufferLen];	
				fromClient.readFully(buffer);
				
				// Convert buffer to String, check if "QUIT", and print if not
				clientIn = new String(buffer.getData());
				if (clientIn == "QUIT") {
					break;
				} else {
					System.out.println("Received: " + clientIn);
				}
				
				// Convert client input to uppercase
				serverOut = clientIn.toUpperCase() + '\n';
				System.out.println("Modified: " + serverOut);
				
				// Send new string back to client
				toClient.writeBytes(serverOut);
			}
			// Close data streams
			fromClient.close();
			toClient.close();
			
			// Close sockets
			cnSocket.close();
			hnSocket.close();
			
		} catch (IOException error) {
			System.out.println("Exception!!!");
			System.out.println(error.getMessage());
			
		}
	}
}
