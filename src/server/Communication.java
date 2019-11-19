package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Communication implements Runnable {

	private PropertyDatabaseController propertyDatabase;
	private UserDatabaseController userDatabase;
	Socket socketIn;
    PrintWriter out;
    BufferedReader in;
	
	public Communication(PropertyDatabaseController propertyDatabase, UserDatabaseController userDatabase, Socket aSocket) {
		this.propertyDatabase = propertyDatabase;
		this.userDatabase = userDatabase;
		
		socketIn = aSocket;
		
		try{
            in = new BufferedReader(new InputStreamReader(socketIn.getInputStream()));
			out = new PrintWriter((socketIn.getOutputStream()), true);
        }catch(Exception a){
            sendString("error");
            a.printStackTrace();
        }
	}

	void sendString(String toSend){
        out.println(toSend);
        out.flush();
    }
	
	public void menuRunner() throws IOException{
        boolean quit = false;
        while(true){
            switch(menu()){
                case 1:
                    listAllProperty();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                	sendEmail();
                	break;
                case 4:
                	validateUser();
                	break;
                case 5:
                	listLandlords();
                	break;
                case 6:
                	addUser();
                	break;
                default:
                    quit = true;                // this was below
                    sendString("Goodbye\1");    //order of these 2 lines were flipped
            }
            if(quit == true){
                in.close();
                out.close();
                break;
            }
        }
    }
	
	private void addUser() {
		try {
			String userInfo[] = in.readLine().split("~");
			userDatabase.addUser(userInfo[0], userInfo[1],userInfo[2],userInfo[3], userInfo[4]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void listLandlords() {
		try {
			String result = userDatabase.listUsers("landlord");
			sendString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void validateUser() {
		try {
			String user[] = in.readLine().split("é");
			boolean valid =  userDatabase.validateUser(user[0], user[1], Integer.parseInt(user[2]));
			if(valid) {
				sendString("yes");
			} else {
				sendString("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendEmail() {
		try {
			String email[] = in.readLine().split("é");
			userDatabase.sendEmail(Integer.parseInt(email[0]), email[1], email[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void search() {
		try {
			String criteria[] = in.readLine().split("é");
			String result = propertyDatabase.searchProperty(criteria[0], criteria[1], criteria[2], Integer.parseInt(criteria[3]), Integer.parseInt(criteria[4]), criteria[5]);
			sendString(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int menu() throws IOException {
        String temp; int choice = 0;
        temp = in.readLine();
        choice = Integer.parseInt(temp);
        return choice;
    }
	
	private void listAllProperty() {
		sendString(propertyDatabase.listAll("active"));
	}

	@Override
	public void run() {
		 try{
			 	menuRunner();
	        }catch(IOException a){
	            System.err.println(" run caught error ");
	            a.printStackTrace();
	        }catch(Exception b){
	            System.err.println(" run caught error 2 ");
	            b.printStackTrace();
	        }
	}

}
