package servlets;

import java.io.BufferedReader;
import java.io.IOException;
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

/**
 * Servlet implementation class GetLoginDetails
 */
public class GetLoginDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
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

			String email = jsonObject.getString("email");
			System.out.println("email:"+email);
			String password = jsonObject.getString("password");
			System.out.println("password:"+password);
			String securePwd = GetRegistrationDetails.encryptPassword(password);


			Connection con = null;
			ResultSet rs;
			Statement stmt = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			/*String username = "rohini";
			String pwd = "ruh12ruh";
			String dbUrl = "jdbc:mysql://rohinidb.mayfounder.net:3306/eventhub";
			con = DriverManager.getConnection(dbUrl, username, pwd); */

			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventPlanning","root","ruh12ruh");
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
			JSONObject resp = new JSONObject();

			if(rs != null){
				if(rs.next()){
					int userId =  rs.getInt("businessuserid");
					session.setAttribute("userId", userId);
				
					System.out.println("UserId: "+ userId);
					System.out.println("Login Sucessful!");
					resp.put("errorCode",200);
					resp.put("responseText","Success");
				}
				else{
					session.setAttribute("error", "error");
					System.out.println("Login failed");
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
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}



}
