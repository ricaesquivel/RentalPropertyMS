package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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
    
    
    
    
    
    
	public void setNewStatus(String s, int id) {
		System.out.println("in property");
		String query = "UPDATE `properties` SET `state` = ? WHERE `id` = ?";
		try {
			preStmt = myConn.prepareStatement(query);
		      preStmt.setString  (1, s);
		      preStmt.setInt(2, id);
		      
		      preStmt.execute();
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	
	public String searchProperty(String houseTypeChoice, String furnishChoice, String quadChoice, int beds, int baths, String state) {
		
		String result = ""; byte furnishedByte = 0; String bedString = ""; String bathString = "";
		
		try {
			query = "SELECT * FROM `properties` WHERE `state` = ? AND";

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
			preStmt.setString(1, state);
			preStmt.setString(2, houseTypeChoice);
			preStmt.setString(3, quadChoice);
			preStmt.setString(4, furnishChoice);
			preStmt.setString(5, bedString);
			preStmt.setString(6, bathString);
			
			ResultSet rs = preStmt.executeQuery();
			
			while(rs.next()){
                int id = rs.getInt("id");
                String type = rs.getString("type");
                int bedsResult = rs.getInt("bedrooms");
                int bathsResult = rs.getInt("bathrooms");
                String quadResult= rs.getString("quadrant");
                boolean furnishedResult= rs.getBoolean("furnished");
                int landlordId = rs.getInt("landlordID");
//                result += toString(id, type, bedsResult, bathsResult, quadResult, furnishedResult) + "\n";
                result += id +"~"+  type +"~"+  bedsResult+"~"+  bathsResult+"~"+  quadResult+"~"+ furnishedResult + "~"+ landlordId + "~"+ "é";
            }
			if(!result.equals(""))
				result = result.substring(0, result.length() -1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in search");
		}
		
		return "error";
	}
	
	
	
    
    public String getPropertyCount(String s) {
    	int count = -1;
    	if(s.equals("")) {
    		
    		try {
    	    	String query = "SELECT count(properties.id) FROM `properties`"; 
    	    	preStmt = myConn.prepareStatement(query);
    	  
    			ResultSet rs = preStmt.executeQuery();
                String list = "";
                
                while (rs.next()) {
                	count = rs.getInt("count(properties.id)");
                }
                //list = list.substring(0, list.length() -1);
                return list +=count;
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
        	return count+"";
    		
    	}else {
    	
	    	String query = "SELECT count(properties.id) FROM `properties` WHERE `state` = ?";    	
	    	
	    	try {
		    	preStmt = myConn.prepareStatement(query);
		    	preStmt.setString(1, s);
		    	
				ResultSet rs = preStmt.executeQuery();
	            String list = "";
	            
	            while (rs.next()) {
	            	count = rs.getInt("count(properties.id)");
	            }
	            //list = list.substring(0, list.length() -1);
	            return list +=count;
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return count+"";
    	}
    }
    
    
	public String listAll(String state) {
		String query = "SELECT * FROM `properties` WHERE `state` = ? ORDER BY id ASC";
		
		try{
			preStmt = myConn.prepareStatement(query);
			preStmt.setString(1, state);
			ResultSet rs = preStmt.executeQuery();
            String list = "";
            
            while(rs.next()){
            	int id = rs.getInt("id");
                String type = rs.getString("type");
                int bedsResult = rs.getInt("bedrooms");
                int bathsResult = rs.getInt("bathrooms");
                String quadResult= rs.getString("quadrant");
                boolean furnishedResult= rs.getBoolean("furnished");
                int landlordId = rs.getInt("landlordID");
//                list += toString(id, type, bedsResult, bathsResult, quadResult, furnishedResult) + "\n";
                list += id +"~"+  type +"~"+  bedsResult+"~"+  bathsResult+"~"+  quadResult+"~"+ furnishedResult + "~"+ landlordId + "~"+"é";
            }
            if(list.length()==0) {
            	return "";
            }
            list = list.substring(0, list.length() -1);
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "something went wrong";
	}
	
	public String listPropertiesAndLandlords() {
		
		String query = "SELECT *, name, email " +
						"FROM `properties`, `landlords`" +
						"WHERE landlordID = landlords.id" +
						" ORDER BY properties.id ASC";
		String result = "";
		
		try {
			preStmt = myConn.prepareStatement(query);
            ResultSet rs = preStmt.executeQuery();
            
            while(rs.next()){
            	int id = rs.getInt("id");
                String type = rs.getString("type");
                int bedsResult = rs.getInt("bedrooms");
                int bathsResult = rs.getInt("bathrooms");
                String quadResult= rs.getString("quadrant");
                boolean furnishedResult= rs.getBoolean("furnished");
                int landlordId = rs.getInt("landlordID");
                String state = rs.getString("state");
                String name = rs.getString("name");
                String email = rs.getString("email");
                result += id +"~"+  type +"~"+  bedsResult +"~"+  bathsResult +"~"+  quadResult +"~"+  furnishedResult +"~"+ state + "~" + name +"~"+ email +"~"+ "\n";
            }
            result = result.substring(0, result.length() -1);
            return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in list all landlords");
		}
		return "critical error";
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


	public String getLandlordProperties(int landlordID) {
		
		String query = "SELECT * FROM `properties` WHERE `landlordID` = ? ORDER BY id ASC";
		
		try {
			preStmt = myConn.prepareStatement(query);
			preStmt.setInt(1, landlordID);
			ResultSet rs = preStmt.executeQuery();
            String list = "";
            
            while(rs.next()){
            	int id = rs.getInt("id");
                String type = rs.getString("type");
                int bedsResult = rs.getInt("bedrooms");
                int bathsResult = rs.getInt("bathrooms");
                String quadResult= rs.getString("quadrant");
                boolean furnishedResult= rs.getBoolean("furnished");
                String state = rs.getString("state");
                list += id +"~"+  type +"~"+  bedsResult+"~"+  bathsResult+"~"+  quadResult+"~"+ furnishedResult + "~"+ state + "~"+"\n";
            }
            if(list.length() == 0) {
            	return "no properties yet";
            }
            list = list.substring(0, list.length() -1);
            return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in get landlord's properties");
		}
		
		return "critical error";
	}

	public void addProperty(int id, String houseTypeChoice, String bedroom, String bathroom, String quadChoice,
			String furnishChoice, int landlordID, String state) {
		
		query =  "INSERT INTO `properties` (`id`,`type`,`bedrooms`,`bathrooms`,`quadrant`,`furnished`,`landlordID`, `state`)"
	            + "VALUES(?,?,?,?,?,?,?,?)";

		if(furnishChoice.equals("Furnished")) {
			furnishChoice = "1";
		}
		else {
			furnishChoice = "0";
		}
		
		try {
			preStmt = myConn.prepareStatement(query);
	        preStmt.setInt(1, id);
	        preStmt.setString(2, houseTypeChoice);
	        preStmt.setString(3, bedroom);
	        preStmt.setString(4, bathroom);
	        preStmt.setString(5, quadChoice);
	        preStmt.setString(6, furnishChoice);
	        preStmt.setInt(7, landlordID);
	        preStmt.setString(8, state);
	        preStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error adding user");
		}
	}

	public int getMaxPropertyID() {
		query = "SELECT MAX(id) FROM `properties`";
		try {
			preStmt = myConn.prepareStatement(query);
			ResultSet rs = preStmt.executeQuery();
			int res = 0;
			while(rs.next()) {
				res = rs.getInt("MAX(id)");
			}
			
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("error in get max property id");
		}
		return -1; //critical error
	}

}
