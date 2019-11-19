package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import client.view.*;

public class ClientCommunicator {
	
	private Socket palinSocket;
    PrintWriter socketOut;
    BufferedReader socketIn;
    
    public ClientCommunicator(String name, int port) {
		 try {
		     palinSocket = new Socket(name, port);
		     socketIn = new BufferedReader(new InputStreamReader(palinSocket.getInputStream()));
		     socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		 } catch (IOException a) {
		     a.printStackTrace();
		 }
    }
    
    public PrintWriter getOutSocket() {
    	return socketOut;
    }
    public BufferedReader getInSocket() {
    	return socketIn;
    }
}
