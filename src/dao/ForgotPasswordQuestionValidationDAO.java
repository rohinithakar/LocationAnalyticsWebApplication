package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import servlets.DatabaseConnection;

public class ForgotPasswordQuestionValidationDAO {

	public String forgotPwdQueValidation(String ans,String email) throws SQLException{
		Connection con = null;
		ResultSet rs;
		Statement stmt = null;


		DatabaseConnection connection = DatabaseConnection.getInstance();
		con = connection.setConnection();
		stmt = con.createStatement();
		String query = "select answer from \"BusinessUser\" where username = '"+email+"'";
		System.out.println("Query: "+query);
		rs = stmt.executeQuery(query);
		System.out.println("rs: "+rs);
		JSONObject resp = new JSONObject();

		if(rs != null){
			if(rs.next()){
				String dbAnswer =  rs.getString("answer");
				System.out.println("db ans:"+dbAnswer);

				if(dbAnswer.equalsIgnoreCase(ans)){
					System.out.println("validated");
					return "true";
				}
				else{
					System.out.println("validation failed");
					return "false";

				}
			}
			else{

				System.out.println("server error");
				return "error";
			}
		}
		return null;

	}
}
