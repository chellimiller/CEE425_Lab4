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

class UDPCLient {
	public static void main (String argv[]) throws Exception {
		
		// Input/output strings
		String userIn;
		String servIn;
		
		// Create byte arrays for sending/receiving data
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		// Socket settings
		String clientName = "localhost";
		int clientPortNum = 9876;
		
		try {
				
			// Create client socket
			DatagramSocket clSocket = new DatagramSocket();
			
			// Get and set IP Address
			InetAddress clientIP = InetAddress.getByName(clientName);
			
			// User input stream and DatagramPackets
			BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
			DatagramPacket sendPack;
			DatagramPacket receivePack;
					
			// Read user input as string, convert to byte array, and send to server
			userIn = fromUser.readLine();
			sendData = userIn.getBytes();
			sendPack = new DatagramPacket(sendData, sendData.length, clientIP, clientPortNum);
			clSocket.send(sendPack);
			
			// Receive DatagramPacket and convert back to string
			receivePack = new DatagramPacket(receiveData, receiveData.length);	
			clSocket.receive(receivePack);
			servIn = new String(receivePack.getData());
			
			// Print out modified string
			System.out.println("From Server: " + servIn);
			
		} catch (Exception error) {
			System.out.println(error);
			
		} finally {
			
			// Close data stream
			fromUser.close();
			
			// Close socket
			clSocket.close();
		}
	}
}
