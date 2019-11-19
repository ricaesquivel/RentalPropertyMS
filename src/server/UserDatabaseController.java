package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.controller.LoginEnum;

public class UserDatabaseController {

	Connection myConn;
    String query;
    PreparedStatement preStmt;
    
    public UserDatabaseController(Connection conn){
        try {
            myConn = conn;
        } catch (Exception e) {
            System.err.println("error connecting to user database");
            e.printStackTrace();
        }
    }
	
	public boolean validateUser(String username, String pass, int loginType) {
		
		int userType = loginType;//.getCode(); 
		String type = "";
		if(userType == 1) type = "manager";
		if(userType == 3) type = "regrenter";
		if(userType == 4) type = "landlord";
		
		try {
			query = "SELECT * FROM `users` WHERE `usertype` = ? AND `username` = ? AND `password` = ?";
			preStmt = myConn.prepareStatement(query);
			preStmt.setString(1, type);
			preStmt.setString(2, username);
			preStmt.setString(3, pass);
			
			ResultSet rs = preStmt.executeQuery();
			
			if(!rs.next()){
				return false;
            }
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in validate user");
		}
		
		return false;
	}

	public String listUsers(String type) {
		
		String query = "SELECT * FROM `users` WHERE `usertype` = ?";
		String result = "";
		
		try {
			preStmt = myConn.prepareStatement(query);
			preStmt.setString(1, type);
            ResultSet rs = preStmt.executeQuery();
            
            while(rs.next()){
            	String username = rs.getString("username");
            	String password = rs.getString("password");
            	String email = rs.getString("email");
            	String name = rs.getString("name");
                String userType = rs.getString("usertype");
//                result += toString(username, password, email, name) + "\n";
                result += username +"~"+  password +"~"+  email +"~"+  name +"~"+  userType +"~"+ "é";
            }
            result = result.substring(0, result.length() -1);
            return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in list all landlords");
		}
		return "critical error";
	}
	
	public void addUser(String username,String password, String email, String name, String usertype) {
		query =  "INSERT INTO `users` (`username`,`password`,`email`,`name`,`usertype`)"
	            + "VALUES(?,?,?,?,?)";
		try {
			preStmt = myConn.prepareStatement(query);
	        preStmt.setString(1, username);
	        preStmt.setString(2, password);
	        preStmt.setString(3, email);
	        preStmt.setString(4, name);
	        preStmt.setString(5, usertype);
	        preStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error adding user");
		}
		
	}
	
	public boolean userExists(String username) {

		try {
			query = "SELECT * FROM `users` WHERE `username` = ?";
			preStmt = myConn.prepareStatement(query);
			preStmt.setString(1, username);
			
			ResultSet rs = preStmt.executeQuery();
			
			if(!rs.next()){
				return false;
            }
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in validate user");
		}
		
		return false;
	}

	private String toString(String username, String password, String email, String name) {
		
		return "name: " + name + ", email: " + email +
                ", username: " + username + 
                ", password: " + password;
	}

	public void sendEmail(int to, String from, String emailText) {
		
		query =  "INSERT INTO `emails` (`to`,`from`, `text`)"
	            + "VALUES(?,?,?)";
		
		try {
			
			preStmt = myConn.prepareStatement(query);

            preStmt.setInt(1, to);
            preStmt.setString(2, from);
            preStmt.setString(3, emailText);
            preStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error sending email");
		}
	}

	public int getlandlordID(String username) {
		String ide = "";
		query = "SELECT id " +
				"FROM `landlords` " +
				"WHERE landlordusername = ? ";
		try {
			preStmt = myConn.prepareStatement(query);
			preStmt.setString(1, username);
            ResultSet rs = preStmt.executeQuery();
            
            while(rs.next()){
            	ide = rs.getString("id");
            }
            return Integer.parseInt(ide);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in get landlordID");
		}
		return -1;		//this is critical error
	}

	public String getLandlordEmails(int landlordID) {
		String result = "";
		query = "SELECT * " +
				"FROM `emails` " +
				"WHERE `to` = ?";
		try {
			preStmt = myConn.prepareStatement(query);
			preStmt.setInt(1, landlordID);
            ResultSet rs = preStmt.executeQuery();
		
            while(rs.next()){
            	String from = rs.getString("from");
                String text = rs.getString("text");
                result += from +"~"+  text + "~" + "\n";
            }
            if(result.equals("")) {
            	return "none";
            }
            result = result.substring(0, result.length() -1);
            return result;
            
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in get emails");
		}
            
		return "critical error";
	}

}
