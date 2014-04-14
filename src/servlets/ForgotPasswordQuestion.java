package servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.logging.Logger;
/**
 * Servlet implementation class ForgotPasswordQuestion
 */
public class ForgotPasswordQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		
		JSONObject resp = new JSONObject();
	
			Connection con = null;
			ResultSet rs;
			Statement stmt = null;
			
			/*
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventPlanning","root","ruh12ruh");
				stmt = con.createStatement();
				stmt.setEscapeProcessing(true);
				if(!con.isClosed()){
					System.out.println("Successfully Connected!");
				}
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/	DatabaseConnection connection = DatabaseConnection.getInstance();
con = connection.setConnection();
try {
	stmt = con.createStatement();
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
				
			response.setContentType("application/json");
			StringBuffer jb = new StringBuffer();
			String line = null;
			try {
				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null)
					jb.append(line);
			
				JSONObject jsonObject = new JSONObject(jb.toString());
				System.out.println("jsonObject:"+jsonObject.toString());

				String email = jsonObject.getString("email");
				System.out.println("email:"+email);
					
					
					String query = "Select securityquestion from \"BusinessUser\" where username = '"+email+"'";
					System.out.println("Query: "+query);
					
					rs = stmt.executeQuery(query);
					
					if(rs.next()){
						
						String question = rs.getString("securityQuestion");
						System.out.println("Question:"+question);
						resp.put("errorCode",200);
						resp.put("responseText",question);
						response.getWriter().write(resp.toString());
						System.out.println("Response: "+resp);	
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			
	
	
		
		
	}
}

