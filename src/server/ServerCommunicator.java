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
	
	private static ServerCommunicator onlyInstance;
	
	private PrintWriter out;
    private Socket aSocket;
    private ServerSocket myServer;
    private ExecutorService pool;
    private Connection myConn;

    private PropertyDatabaseController propertyDatabase;
    private UserDatabaseController userDatabase;
    
    private ServerCommunicator(int portNumber){
    	try {
    		myServer = new ServerSocket(portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in server communicator");
		}
    	System.out.println(" << server running >> ");
    }
    
    public static ServerCommunicator getOnlyInstance(int port) {
    	if(onlyInstance == null) {
    		onlyInstance = new ServerCommunicator(9091);
    	}
    	return onlyInstance;
    }
    
    public void communicateClient()throws IOException{
    	try {
			while(true) {
				System.out.println(" Accepting Clients ");
                pool = Executors.newCachedThreadPool();
                aSocket = myServer.accept();
                
                Communication communicate = new Communication(propertyDatabase, userDatabase, aSocket);
                
                System.out.println("<< RentalMS server started >>");
                pool.execute(communicate);
                pool.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("--error in Server loop--");
			pool.shutdown();
		} finally {
			out.close();
		}
    }
    
    public void createDatabase() {
		try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rental?user=root","root", "8002");
            propertyDatabase = PropertyDatabaseController.getOnlyInstance(myConn); 
    		userDatabase = UserDatabaseController.getOnlyInstance(myConn);
        } catch(SQLException a){
        	a.printStackTrace();
            System.err.println("Error connecting to database");
        }
        System.out.println("<< database Server is Running >>");
	}
    
    public static void main(String[] args) {
    	
		ServerCommunicator s = ServerCommunicator.getOnlyInstance(9091);
		s.createDatabase();
		try {
			s.communicateClient();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Server issue main");
		}
	}
}
