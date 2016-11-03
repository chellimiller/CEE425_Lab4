/*
 * File Name:     UDPClient.java
 * Created By:    Michelle Miller
 * Creation Date: 2016.10.27
 *
 * UW Stout - CEE 425: Computer Networking
 *            Lab 4:   Simple Socket Program
 */

import java.io.*;
import java.net.*;

class UDPClient {
	public static void main (String argv[]) throws Exception {
		
		// Input/output strings
		String userIn;
		String servIn;
		
		// Create byte arrays for sending data
		byte[] sendData = new byte[1024];
		
		// Socket settings
		String clientName = "localhost";
		int clientPortNum = 9876;
		
		try {
			while (true) {
				
				// Create byte arrays for receiving data
				byte[] receiveData = new byte[1024];
				
				// Create client socket
				DatagramSocket cSocket = new DatagramSocket();

				// Get and set IP Address
				InetAddress clientIP = InetAddress.getByName(clientName);

				// User input stream and DatagramPackets
				BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));

				// Read user input as string, convert to byte array, and send to server
				System.out.print("\n\nString to capitalize:  ");
				userIn = fromUser.readLine();
				sendData = userIn.getBytes();
				DatagramPacket sendPack = 
					new DatagramPacket(sendData, sendData.length, clientIP, clientPortNum);
				cSocket.send(sendPack);
				
				// Check if connection should be closed down
				if ("quit".equals(userIn) || "QUIT".equals(userIn) || "q".equals(userIn)) {
					
					// Close data streams
					fromUser.close();
					
					// Close socket
					cSocket.close();
					
					// Let user know connection's closed
					System.out.println("\n\nClosed connection to server\n\n");
				}
				
				// Receive DatagramPacket
				DatagramPacket receivePack = 
					new DatagramPacket(receiveData, receiveData.length);	
				cSocket.receive(receivePack);
				
				// Prepare to convert to string without extra white space
				servIn = new String(receivePack.getData());
				servIn = servIn.replaceAll("\u0000.*", "");
				
				// Print out modified string
				System.out.println("\nFrom Server: " + servIn);

			}
			
		} catch (Exception error) {
			//System.out.println(error);
		}
	}
}
