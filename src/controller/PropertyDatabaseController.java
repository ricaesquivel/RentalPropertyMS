package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropertyDatabaseController {

	Connection myConn;
    String query;
    PreparedStatement preStmt;
    
    public PropertyDatabaseController(Connection conn){
        try {
            myConn = conn;
        } catch (Exception e) {
            System.err.println("error connecting to database");
            e.printStackTrace();
        }
    }
	
	public String searchProperty(String houseTypeChoice, String furnishChoice, String quadChoice, int beds, int baths) {
		
		String result = ""; byte furnishedByte = 0; String bedString = ""; String bathString = "";
		
		try {
			query = "SELECT * FROM `properties` WHERE";

			//-----------------------------------------------------------------------for house type
			if(!houseTypeChoice.equals("") && !houseTypeChoice.contains("choose")) {
				query += " `type` = ?";
			} else {
				query += " `type` LIKE ?";
				houseTypeChoice = "%";
			}
			//-----------------------------------------------------------------------for quadrant
			if(!quadChoice.equals("") && !quadChoice.contains("choose")) {
				query += " AND `quadrant` = ?";
			} else {
				query += " AND `quadrant` LIKE ?";
				quadChoice = "%";
			}
			//-----------------------------------------------------------------------for furnished
			if(!furnishChoice.equals("") && !furnishChoice.contains("choose")) {
				query += " AND `furnished` = ?";
				if(furnishChoice.equals("Furnished"))
					furnishChoice = "1";
				else
					furnishChoice = "0";
			} else {
				query += " AND `furnished` LIKE ?";
				furnishChoice = "%";
			}
			//-----------------------------------------------------------------------for bedrooms
			if(beds != Integer.MAX_VALUE) {
				query += " AND `bedrooms` = ?";
				bedString = Integer.toString(beds);
			} else {
				query += " AND `bedrooms` LIKE ?";
				bedString = "%";
			}
			//-----------------------------------------------------------------------for bath
			if(baths != Integer.MAX_VALUE) {
				query += " AND `bathrooms` = ?";
				bathString = Integer.toString(baths);
			} else {
				query += " AND `bathrooms` LIKE ?";
				bathString = "%";
			}
			
			preStmt = myConn.prepareStatement(query);
			preStmt.setString(1, houseTypeChoice);
			preStmt.setString(2, quadChoice);
			preStmt.setString(3, furnishChoice);
			preStmt.setString(4, bedString);
			preStmt.setString(5, bathString);
			
			ResultSet rs = preStmt.executeQuery();
			
			while(rs.next()){
                int id = rs.getInt("id");
                String type = rs.getString("type");
                int bedsResult = rs.getInt("bedrooms");
                int bathsResult = rs.getInt("bathrooms");
                String quadResult= rs.getString("quadrant");
                boolean furnishedResult= rs.getBoolean("furnished");
                int landlordId = rs.getInt("landlordID");
                result += toString(id, type, bedsResult, bathsResult, quadResult, furnishedResult) + "\n";
            }
//			result = result.substring(0, result.length() -1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in search");
		}
		
		return "error";
	}
	
	public String listAll() {
		String query = "SELECT * FROM `properties`";
		
		try{
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String list = "";
            while(rs.next()){
            	int id = rs.getInt("id");
                String type = rs.getString("type");
                int bedsResult = rs.getInt("bedrooms");
                int bathsResult = rs.getInt("bathrooms");
                String quadResult= rs.getString("quadrant");
                boolean furnishedResult= rs.getBoolean("furnished");
                int landlordId = rs.getInt("landlordID");
                list += toString(id, type, bedsResult, bathsResult, quadResult, furnishedResult) + "\n";
            }
            list = list.substring(0, list.length() -1);
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "something went wrong";
	}
	
	private String toString(int id, String type, int bedsResult, int bathsResult, String quadResult, boolean furnished) {
		String fur;
		if(furnished)
			fur = "yes";
		else 
			fur = "no";
		
		return "id: " + id + ", property type: " + type +
	                ", bedrooms: " + bedsResult + 
	                ", bathrooms: " + bathsResult +
	                ", city quadrant: " + quadResult +
	                ", furnished: " + fur;
	}

}
