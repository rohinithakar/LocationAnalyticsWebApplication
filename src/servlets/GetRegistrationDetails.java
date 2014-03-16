package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
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
/**
 * Servlet implementation class GetRegistrationDetails
 */
public class GetRegistrationDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static  String encryptPassword(String pwd){
		String securePwd = null;
		byte[] bytesOfMessage;
		try {
			bytesOfMessage = pwd.getBytes("UTF-8");

			MessageDigest md;

			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			securePwd = (new BigInteger(1,thedigest)).toString(16);
		} 
		catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return securePwd;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;
		JSONObject resp = new JSONObject();
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e)
		{ 
			//report an error 

		}

		try {
			jsonObject = new JSONObject(jb.toString());

			System.out.println("jsonObject:"+jsonObject.toString());

			String firstName = jsonObject.getString("firstName");
			System.out.println("firstName:"+firstName);
			String lastName = jsonObject.getString("lastName");
			System.out.println("lastName:"+lastName);
			String email = jsonObject.getString("email");
			System.out.println("email:"+email);
			String question = jsonObject.getString("question");
			System.out.println("question:"+question);
			String answer = jsonObject.getString("answer");
			System.out.println("answer:"+answer);

			String password = jsonObject.getString("password");
			System.out.println("password:"+password);
			String securePwd = encryptPassword(password);


			Connection con = null;
			ResultSet rs;
			Statement stmt = null;


			/*
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//This is old code for Heroku Connection
			

			URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

			String username = dbUri.getUserInfo().split(":")[0];
			String pwd = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath(); 

			String username = "rohini";
			String pwd = "ruh12ruh";
			String dbUrl = "jdbc:mysql://rohinidb.mayfounder.net:3306/eventhub"; */
			
			//	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventPlanning","b48da195f9eae4","457ffc28");
			//con = DriverManager.getConnection(dbUrl, username, pwd);
			
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
			int rowCount = stmt.executeUpdate(query);


			if(rowCount>0){
				String result = "true";
				System.out.println("Insert Successful");

				resp.put("errorCode",200);
				resp.put("responseText","Success");
				response.getWriter().write(resp.toString());
				System.out.println("Response: "+resp);

			}

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			if (e1.getMessage().contains("Duplicate entry")) {

				System.out.println("message issss " + e1.getMessage());

				try {
					resp.put("errorCode", 500);

					resp.put("responseText", "DuplicateId");

					response.getWriter().write(resp.toString());

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			e1.printStackTrace();
		}
	}

}


