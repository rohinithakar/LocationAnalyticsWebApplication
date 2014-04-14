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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.logging.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgotPasswordQuestionValidation
 */
public class ForgotPasswordQuestionValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside ForgotPasswordQuestionValidation");
		
		response.setContentType("application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e)
		{ 
			//report an error 

		}

		try {
			JSONObject jsonObject = new JSONObject(jb.toString());
			System.out.println("jsonObject:"+jsonObject.toString());

			String ans = jsonObject.getString("value");
			String email = jsonObject.getString("email");
			

			Connection con = null;
			ResultSet rs;
			Statement stmt = null;

			/*
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventPlanning","root","ruh12ruh");
			stmt = con.createStatement();
			stmt.setEscapeProcessing(true);
			if(!con.isClosed()){
				System.out.println("Successfully Connected!");
			}
*/
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
					resp.put("errorCode",200);
					resp.put("responseText","Success");
					}
					else{
						System.out.println("validation failed");
						resp.put("errorCode",202); //wrong info provided
						resp.put("responseText","Failure");
						
					}
				}
				else{
					
					System.out.println("server error");
					resp.put("errorCode",300);
					resp.put("responseText","Failure");
				}

			}
			response.getWriter().write(resp.toString());

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}


	}


