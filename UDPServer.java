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
		
		// Create byte arrays for sending/receiving data
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		// Socket settings
		int servPortNum = 9876;
		
		try {
			// Create handshake socket
			DatagramSocket servSocket = new ServerSocket(servPortNum);
			
			// Create DatagramPackets
			DatagramPacket getPack;
			DatagramPacket sendPack;
			
			while(true) {
				// Receive packet from client and convert data to String
				getPack = new DatagramPacket(receiveData, receiveData.length);
				servSocket.receiveData(getPack);
				clientIn = new String(getPack.getData());
				
				// Break loop if clientIn is equal to "QUIT"
				if (clientIn == "QUIT") {
					break;
				}
				
				// Get Client IP Address and Port
				InetAddress clientIP = getPack.getAddress();
				int cPort = getPack.getPort();
				
				// Modify string received from client and convert to bytes
				serverOut = clientIn.toUpperCase();
				sendData = serverOut.getBytes();
				
				// Send new string back to client
				sendPack = 
					new DatagramPacket(sendData, sendData.length, clientIP, cPort);
				servSocket.send(sendPack);
			}
			
			// Close socket
			servSocket.close();
			
		} catch (Exception error) {
			System.out.println(error);
			
		}
	}
}
