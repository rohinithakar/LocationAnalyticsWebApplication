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

import dao.LoginDAO;

/**
 * Servlet implementation class GetLoginDetails
 */
public class GetLoginDetails extends HttpServlet {
	
	LoginDAO dao = new LoginDAO();
	
	GetLoginDetails(LoginDAO dao) {
		this.dao = dao;
	}
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		JSONObject resp = new JSONObject();

		
		response.setContentType("application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			//
		}

		try {
			JSONObject jsonObject = new JSONObject(jb.toString());
			System.out.println("jsonObject:"+jsonObject.toString());

			String email = jsonObject.getString("email");
			System.out.println("email:"+email);
			String password = jsonObject.getString("password");
			System.out.println("password:"+password);
			String securePwd = GetRegistrationDetails.encryptPassword(password);

			int userId = dao.getLogin(email, securePwd);
			
			
			if(userId == -1){
				resp.put("errorCode",300);
				resp.put("responseText","Failure");
			}
			else{
				session.setAttribute("userId", userId);
				resp.put("errorCode",200);
				resp.put("responseText","Success");	
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
