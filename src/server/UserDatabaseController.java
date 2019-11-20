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
	
	public void addLandlord(int id, String name, String email, String userName) {
		query =  "INSERT INTO `landlords` (`id`,`name`,`email`,`landlordusername`)"
	            + "VALUES(?,?,?,?)";
		
		try {
			preStmt = myConn.prepareStatement(query);
	        preStmt.setInt(1,id );
	        preStmt.setString(2, name);
	        preStmt.setString(3, email);
	        preStmt.setString(4, userName);
	        preStmt.execute();
		}catch(Exception e){
			e.printStackTrace();
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
	public void removeEmail(int id, String from, String text){
		try {
		query = "DELETE FROM `emails` WHERE `to` = ?"
				+ " AND `from` = ? AND `text` = ?";
		preStmt = myConn.prepareStatement(query);
		preStmt.setInt(1, id);
		preStmt.setString(2, from);
		preStmt.setString(3, text);
		preStmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addSubscribes(String username, String houseTypeChoice, String furnishChoice, int beds, int baths, String quadChoice) {
		
		String result = ""; byte furnishedByte = 0; String bedString = ""; String bathString = "";
		
		query =  "INSERT INTO `subscribes` (`subusername`,`housetype`,`furnished`,`beds`,`baths`,`quadrant`)"
	            + "VALUES(?,?,?,?,?,?)";
		
		if(houseTypeChoice.equals("") || houseTypeChoice.contains("choose")) {
			houseTypeChoice = "any";
		}
		if(furnishChoice.equals("") || furnishChoice.contains("choose")) {
			furnishChoice = "any";
		}
		if(quadChoice.equals("") || quadChoice.contains("choose")) {
			quadChoice = "any";
		}
		if(beds == Integer.MAX_VALUE) {
			beds = -1;
		}
		if(baths == Integer.MAX_VALUE) {
			baths = -1;
		}
			
		try {
				preStmt = myConn.prepareStatement(query);
		        preStmt.setString(1, username);
		        preStmt.setString(2, houseTypeChoice);
		        preStmt.setString(3, furnishChoice);
		        preStmt.setInt(4, beds);
		        preStmt.setInt(5, baths);
		        preStmt.setString(6, quadChoice);
		        preStmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in subscribe");
		}
	}
	
	public int landlordSignUpID() {
		System.out.println("landlordsignupid");
		query = "SELECT MAX(id) FROM `landlords`";
		try {
			preStmt = myConn.prepareStatement(query);
			ResultSet rs = preStmt.executeQuery();
			int id = 0;
			while(rs.next()) {
				id = rs.getInt("MAX(id)");
			}
			
			return id;
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("error in get max property id");
		}
		return -1; //critical error
	}

	public String getSubscribes(String user) {
		String result = "";
		query = "SELECT * " +
				"FROM `subscribes` " +
				"WHERE `subusername` = ?";
		try {
			preStmt = myConn.prepareStatement(query);
			preStmt.setString(1, user);
            ResultSet rs = preStmt.executeQuery();
		
            while(rs.next()){
            	String housetype = rs.getString("housetype");
            	String furnish = rs.getString("furnished");
                int beds = rs.getInt("beds");
                int baths = rs.getInt("baths");
                String quadrant = rs.getString("quadrant");
                result += housetype +"~"+  furnish +"~" + beds +"~" + baths +"~" + quadrant +"~" + "é";
            }
            if(result.equals("")) {
            	return "none";
            }
            result = result.substring(0, result.length() -1);
            return result;
            
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in get subscribes");
		}
		return "critical error";
	}

	public void deleteSubscribe(String user, String type, String furnish, int beds, int baths, String quad) {
		
		query =  "DELETE FROM `subscribes` WHERE subusername = ? AND housetype = ? AND furnished = ? AND beds = ? AND baths = ? AND quadrant = ?";
		
		try {
			
			preStmt = myConn.prepareStatement(query);

            preStmt.setString(1, user);
            preStmt.setString(2, type);
            preStmt.setString(3, furnish);
            preStmt.setInt(4, beds);
            preStmt.setInt(5, baths);
            preStmt.setString(6, quad);
            preStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error delete subs");
		}
	}
}

