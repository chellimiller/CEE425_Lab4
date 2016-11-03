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

class TCPClient {
	public static void main (String argv[]) throws Exception {
		
		// Keep connection between client and server open
		int keepOpen = 1;
		
		// Input/output strings
		String userIn;
		String servIn;
		
		// Socket settings
		String clientName = "localhost";
		int clientPortNum = 6789;
		
		try {
			while (keepOpen == 1) {
				
				// Create client socket
				Socket cSocket = new Socket(clientName, clientPortNum);
				
				// Set up input/output streams
				BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
				DataOutputStream toServer = new DataOutputStream(cSocket.getOutputStream());
				BufferedReader fromServer = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
				
				// Prompt user and read input
				System.out.print("\n\nString to capitalize: ");
				userIn = fromUser.readLine();
				
				// Check if user wants to quit
				if ("QUIT".equals(userIn) || "quit".equals(userIn)) {
					
					// Set keepOpen to zero to exit while loop
					keepOpen = 0;
					
					// Close data streams
					fromUser.close();
					toServer.close();
					fromServer.close();
					
					// Close sockets
					cSocket.close();
					
					// Let user know connection is closed
					System.out.println("\nConnection closed\n\n");
					
				} else {
					// Send user input to server
					toServer.writeBytes(userIn + '\n');
					
					// Read server input and print to screen
					servIn = fromServer.readLine();
					System.out.println("From Server: " + servIn);
				}
			}
			
		} catch (Exception error) {
			System.out.println(error.getMessage());
			
		}
	}
}