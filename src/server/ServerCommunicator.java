package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommunicator {
	
	private PrintWriter out;
    private Socket aSocket;
    private ServerSocket myServer;
    private ExecutorService pool;

    private PropertyDatabaseController propertyDatabase;
    private UserDatabaseController userDatabase;
    
    public ServerCommunicator(int portNumber){
    	try {
    		myServer = new ServerSocket(portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in server communicator");
		}
    	System.out.println(" << server running >> ");
    }
    
    public void communicateClient()throws IOException{
    	try {
			while(true) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in ");
		}
    }
    
    public static void main(String[] args) {
		ServerCommunicator s = new ServerCommunicator(9091);
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
