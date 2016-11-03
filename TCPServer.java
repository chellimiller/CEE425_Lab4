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
		int servPortNum = 6789;
		
		try {
			// Create handshake socket
			ServerSocket hnSocket = new ServerSocket(servPortNum);
			
			while(true) {
				// Create connection socket
				Socket cSocket = hnSocket.accept();
				
				// Set up input/output streams
				BufferedReader fromClient = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
				DataOutputStream toClient = new DataOutputStream(cSocket.getOutputStream());
				
				// Read input from client
				clientIn = fromClient.readLine();
				
				// If client input is NULL, close connection, otherwise capitalize string and send back
				if (clientIn == null) {
					System.out.println("\nConnection Closed\n\n");
					cSocket.close();
				} else {
					
					// Print out string from client
					System.out.println("Received: " + clientIn);		
					
					// Convert client input to uppercase and send back
					serverOut = clientIn.toUpperCase() + '\n';
					toClient.writeBytes(serverOut);
				}
			}
			
		} catch (IOException error) {
			System.out.println("Exception!!!");
			System.out.println(error.getMessage());
			
		}
	}
}