package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Information {

	public Connection connect() {
		Connection con = null;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_ims","root","1234");
			
			//for testing
			System.out.println("Successfully Connected");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not Connected");
		}
		
		return con;
	}
	
	//readInformation
	public String readInformation() { 
		String output = ""; 
		
		try
		 { 
		 Connection con = connect(); 
		 
		 if (con == null) {
			 return "Error while connecting to the database for reading."; 
		 } 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr>" +
		 "<th>Information ID</th>" +
		 "<th>Category</th>" +
		 "<th>Information Name</th>" + 
		 "<th>Summary</th>" +
		 "<th>Status</th>" +
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from information";
		 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next()) { 
			 String informationID = Integer.toString(rs.getInt("informationID")); 
			 String category = rs.getString("category"); 
			 String name = rs.getString("name"); 
			 String summary = rs.getString("summary"); 
			 String status = rs.getString("status"); 
			 
			 // Add into the html table
			 output += "<tr><td>" + informationID + "</td>"; 
			 output += "<td>" + category + "</td>"; 
			 output += "<td>" + name + "</td>"; 
			 output += "<td>" + summary + "</td>"; 
			 output += "<td>" + status + "</td>"; 
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='information.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='informationID' type='hidden' value='" + informationID 
			 + "'>" + "</form></td></tr>"; 
		 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } catch (Exception e) { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		 } 
		
		 return output; 
	}
	
	//insertInformation
	public String insertInformation(int id, String category, String name, String summary, String status) { 
		String output = ""; 
		
		try
		{ 
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for inserting."; 
			} 
			
			// create a prepared statement
			String query = " insert into information (`informationID`,`category`,`name`,`summary`,`status`)"+ " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, id); 
			preparedStmt.setString(2, category); 
			preparedStmt.setString(3, name); 
			preparedStmt.setString(4, summary);
			preparedStmt.setString(5, status); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			output = "Inserted successfully"; 
		} 
		catch (Exception e) { 
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
		} 
		
		return output; 
	}
	
	//updateInformation
	public String updateInformation(String ID, String category, String name, String summary, String status) {
		String output = ""; 
		
		 try { 
			 Connection con = connect(); 
			 
			 if (con == null) {
				 return "Error while connecting to the database for updating."; 
			} 
			 
			 // create a prepared statement
			 String query = "UPDATE information SET category=?,name=?,summary=?,status=? WHERE informationID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, category); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, summary); 
			 preparedStmt.setString(4, status); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Updated successfully"; 
			 
		} catch (Exception e) { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		} 
		 
 		return output; 	
	}

	//deleteInformation
	public String deleteInformation(String id) { 
		String output = "";
		
		try { 
			Connection con = connect();
			 
			if (con == null) {
				 return "Error while connecting to the database for deleting."; 
			} 
			
			// create a prepared statement
			String query = "delete from information where informationID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			output = "Deleted successfully"; 
			
		 } catch (Exception e) { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		
		 return output; 
	} 
}
