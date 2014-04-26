package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import servlets.DatabaseConnection;

public class SetNewPasswordDAO {

	public String setNewPwd(String securePwd,String email) throws SQLException{
		Connection con = null;
		ResultSet rs;
		Statement stmt = null;
		int rowCount = 0;
		String resp = null;

		DatabaseConnection connection = DatabaseConnection.getInstance();
		con = connection.setConnection();
		stmt = con.createStatement();


		String query = "update \"BusinessUser\" set password = '"+securePwd+"' where username = '"+email+"'";
		System.out.println("Query: "+query);
		rowCount = stmt.executeUpdate(query);

		if(rowCount>0){
			System.out.println("success");
			resp = "success";
		}
		else{
			System.out.println("error");
			resp = "failure";
		}
		return resp;

	}

}
