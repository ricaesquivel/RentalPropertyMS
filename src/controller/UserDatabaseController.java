package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public boolean validateUser(String username, String pass, LoginEnum loginType) {
		
		int userType = loginType.getCode(); String type = "";
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
                result += username +"~"+  password +"~"+  email +"~"+  name +"~"+  userType +"~"+ "\n";
            }
            result = result.substring(0, result.length() -1);
            return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in list all landlords");
		}
		return "critical error";
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
			System.err.println("in email sender");
            preStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error sending email");
		}
	}

}
