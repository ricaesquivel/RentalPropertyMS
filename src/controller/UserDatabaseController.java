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
	
}
