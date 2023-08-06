package server;

//testing

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                case 8:
                	getMaxPropertyID();
                	break;
                case 9:
					addProperty();
					break;
                case 7:
                	newLandlordID();
                	break;
                case 11:
                	getSubscribes();
                	break;
                case 12:
                	deleteSubscribe();
                	break;
                case 10:
                	deleteEmail();
                	break;
                case 13:
                	changeState();
                	break;
                case 14:
                	getLandlordProperty();
                	break;
                case 15:
                	getlandlordEmail();
                	break;
                case 16:
                	getlandlordID();
                	break;
                case 17:
                	addSubscriber();
                	break;
                case 18:
                	getLandlordAndProperty();
                	break;
                case 19:
                	getRegRenters();
                	break;
                case 21:
                	checkExists();
					break;	
                case 20:
                	periodicSummary();
                	break;
                case 22:
                	changeFee();
                	break;
                case 23:
                	getFee();
                	break;
                case 24:
                	checkForMatches();
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
	
	private void checkForMatches() {
		try {
			String propertyData[] = in.readLine().split("~");
			propertyDatabase.checkProperty(Integer.parseInt(propertyData[0]), propertyData[1], propertyData[2], propertyData[3], propertyData[4], propertyData[5], Integer.parseInt(propertyData[6]), propertyData[7]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getFee() {
		try {
			sendString(userDatabase.getFee());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void changeFee() {
		try {
			String args[] = in.readLine().split("é");
			userDatabase.changeFee(args[0], args[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void checkExists() {
		try {
			String arg = in.readLine();
			boolean b = userDatabase.userExists(arg);
			sendString(Boolean.toString(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getRegRenters() {
		try {
			sendString(userDatabase.listUsers("regrenter"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void getLandlordAndProperty() {
		try {
			sendString(propertyDatabase.listPropertiesAndLandlords());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addSubscriber() {
		try {
			String args[] = in.readLine().split("é");
			userDatabase.addSubscribes(args[0], args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void getlandlordID() {
		try {
			String landlordId = in.readLine();
			int id = userDatabase.getlandlordID(landlordId);
			sendString(Integer.toString(id));
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Error in get landlord id");
		}
		
	}

	private void getlandlordEmail() {
		try {
			String landlordId = in.readLine();
			sendString(userDatabase.getLandlordEmails(Integer.parseInt(landlordId)));
			}catch(Exception e) {
				e.printStackTrace();
				System.err.println("Error in get landlord property");
			}
		
	}

	private void getLandlordProperty() {
		try {
		String landlordId = in.readLine();
		sendString(propertyDatabase.getLandlordProperties(Integer.parseInt(landlordId)));
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Error in get landlord property");
		}
	}

	private void deleteEmail() {
		try {
		String emailData[] = in.readLine().split("é");
		userDatabase.removeEmail(Integer.parseInt(emailData[0]), emailData[1], emailData[2]);
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Error in delete email");
		}
	}

	private void deleteSubscribe() {
		try {
			String args[] = in.readLine().split("é");
			userDatabase.deleteSubscribe(args[0], args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getSubscribes() {
		try {
			String user = in.readLine();
			String res = userDatabase.getSubscribes(user);
			sendString(res);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error getting subscribes");
		}
	}

	private void changeState() {
		try {
			String state[] = in.readLine().split("~");
			int id = Integer.parseInt(state[1]);
			propertyDatabase.setNewStatus(state[0], id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void addProperty() {
		try {	
		String propertyData[] = in.readLine().split("é");
		propertyDatabase.addProperty(Integer.parseInt(propertyData[0]), propertyData[1], propertyData[2], propertyData[3], propertyData[4], propertyData[5], Integer.parseInt(propertyData[6]), propertyData[7]);
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Error in add property");
		}
		
	}

	private void newLandlordID() {
		try {
			String s = userDatabase.landlordSignUpID()+"";
			sendString(s);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addUser() {
		try {
			String userInfo[] = in.readLine().split("~");
			userDatabase.addUser(userInfo[0], userInfo[1],userInfo[2],userInfo[3], userInfo[4]);
			
			if(userInfo[4].contentEquals("Landlord")) {
				System.out.println("adding landlord");
				int id = Integer.parseInt(userInfo[5]);
				userDatabase.addLandlord(id,userInfo[3],userInfo[2],userInfo[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void periodicSummary() {
		
		String result = "Total houses: ";
		result+= propertyDatabase.getPropertyCount("");
		result+= "      Total rented: ";
		result+=propertyDatabase.getPropertyCount("rented");
		result+= "      Total active: ";
		result+=(propertyDatabase.getPropertyCount("active"));
		sendString(result);
		
		result ="";
		result+= (propertyDatabase.listAll("rented"));
		sendString(result);
	}
	
	private void listLandlords() {
		try {
			String result =  userDatabase.listUsers("landlord");
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
	
	private void getMaxPropertyID() {
		int max = propertyDatabase.getMaxPropertyID();
		sendString(max + "");
	}

	@Override
	public void run() {
		 try{
			 	menuRunner();
	        }catch(IOException a){
	            if(!a.getMessage().equals("Connection reset")) {
	            	System.err.println(" run caught error ");
	            	a.printStackTrace();
	            } else {
	            	System.out.println("  << a Client disconnected voluntarily");
	            }
	        }catch(Exception b){
	            System.err.println(" run caught error 2 ");
	            b.printStackTrace();
	        }
	}

}
