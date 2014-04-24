package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import servlets.DatabaseConnection;

public class LoginDAO {

	public int getLogin(String email,String securePwd) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		int userId = -1;
		Connection con = null;
		ResultSet rs;
		Statement stmt = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();


		DatabaseConnection connection = DatabaseConnection.getInstance();
		con = connection.setConnection();
		stmt = con.createStatement();
		stmt.setEscapeProcessing(true);
		if(!con.isClosed()){
			System.out.println("Successfully Connected!");
		}

		String query = "select userName,firstName, password, businessuserid from \"BusinessUser\" where userName= '"+email+"' and password = '"
				+securePwd+"';";
		System.out.println("Query: "+query);
		rs = stmt.executeQuery(query);
		System.out.println("rs: "+rs);


		if(rs != null){
			if(rs.next()){
				userId =  rs.getInt("businessuserid");
				System.out.println("UserId: "+ userId);
				System.out.println("Login Sucessful!");
			}
			else{
				System.out.println("Login failed");
			}
		}
		return userId;
	}

}
