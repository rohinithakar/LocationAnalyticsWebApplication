package dao;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import servlets.DatabaseConnection;

public class ForgotPasswordQuestionDAO {
	
	public String forgotPasswordQuestion(String userName) throws SQLException{
		Connection con = null;
		ResultSet rs;
		Statement stmt = null;
		String question = null;

		DatabaseConnection connection = DatabaseConnection.getInstance();
		con = connection.setConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

			String query = "Select securityquestion from \"BusinessUser\" where username = '"+userName+"'";
			System.out.println("Query: "+query);

			rs = stmt.executeQuery(query);

			if(rs.next()){
				 question = rs.getString("securityQuestion");
				System.out.println("Question:"+question);
			}
			return question;
	}

}
