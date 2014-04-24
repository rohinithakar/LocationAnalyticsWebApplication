package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONException;

import servlets.DatabaseConnection;

public class RegistrationDAO {

	public String getRegistration(String firstName,String lastName,String email,String securePwd,String question,String answer) throws SQLException{
		Connection con = null;
		ResultSet rs;
		Statement stmt = null;
		String result = null;

		DatabaseConnection connection = DatabaseConnection.getInstance();
		con = connection.setConnection();
		stmt = con.createStatement();
		stmt.setEscapeProcessing(true);
		if(!con.isClosed()){
			System.out.println("Successfully Connected!");
		}

		//locationAnalyticsManager.
		String query = "INSERT INTO \"BusinessUser\"(password,firstName,lastName,userName,securityQuestion,answer)"+
				"VALUES ('"+securePwd+"','"+firstName+"','"+lastName+"','"+email+"','"+question+"','"+answer+"')";
		System.out.println("Query: "+query);
		int rowCount;
		try {
			rowCount = stmt.executeUpdate(query);
		} catch (SQLException e) {
			result = "duplicate";
			e.printStackTrace();
			return result;
			
		}
		if(rowCount>0){
			result = "true";
			System.out.println("Insert Successful");
		}
		else{
			result = "false";
			System.out.println("Insert Failed");
		}
		return result;

	}
}

