/*
 * File Name:     UDPServer.java
 * Created By:    Michelle Miller
 * Creation Date: 2016.10.27
 *
 * UW Stout - CEE 425: Computer Networking
 *            Lab 4:   Simple Socket Program
 */

import java.io.*;
import java.net.*;

class UDPServer {
	public static void main (String argv[]) throws Exception {
		
		// Input/output strings
		String clientIn;
		String serverOut;
		
		// Create byte array for sending data
		byte[] sendData = new byte[1024];
		
		// Socket settings
		int servPortNum = 9876;
		
		try {
			// Create connection socket
			DatagramSocket servSocket = new DatagramSocket(servPortNum);
			
			while(true) {
				
				// Create byte array for receiving data
				byte[] receiveData = new byte[1024];
				
				// Receive packet from client and convert data to String
				DatagramPacket receivePacket = 
					new DatagramPacket(receiveData, receiveData.length);
				servSocket.receive(receivePacket);
				clientIn = new String(receivePacket.getData());
				
				// If clientIn is null
				if (clientIn == null) {
					// Let user know connection's closed
					System.out.println("\n\nClient closed connection\n\n");
				} else {
					// Print client input
					clientIn = clientIn.replaceAll("\u0000.*", "");
					System.out.println("\n\nReceived:  " + clientIn);
					
					// Get Client IP Address and Port
					InetAddress clientIP = receivePacket.getAddress();
					int cPort = receivePacket.getPort();
					
					// Modify string received from client and convert to bytes
					serverOut = clientIn.toUpperCase();
					sendData = serverOut.getBytes();
					
					// Send new string back to client
					DatagramPacket sendPacket = 
						new DatagramPacket(sendData, sendData.length, clientIP, cPort);
					servSocket.send(sendPacket);
				}
			}
			
		} catch (Exception error) {
			System.out.println(error);
			
		}
	}
}
