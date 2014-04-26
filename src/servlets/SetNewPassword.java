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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import dao.SetNewPasswordDAO;

/**
 * Servlet implementation class SetNewPassword
 */
public class SetNewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside SetNewPassword");
		
		response.setContentType("application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e){  
			e.printStackTrace();
		}

		
			
			try {
				JSONObject jsonObject = new JSONObject(jb.toString());
				System.out.println("jsonObject:"+jsonObject.toString());

				String newPwd = jsonObject.getString("value");
				String email = jsonObject.getString("email");
				String securePwd = GetRegistrationDetails.encryptPassword(newPwd);
				
				JSONObject resp = new JSONObject();
				SetNewPasswordDAO dao = new SetNewPasswordDAO();
				String ans = dao.setNewPwd(securePwd, email);
				
				
				if(ans.equalsIgnoreCase("success")){
					System.out.println("success");
						resp.put("errorCode",200);
						resp.put("responseText","Success");
					}
					else{
						
						System.out.println("error");
						resp.put("errorCode",300);
						resp.put("responseText","Failure");
					}

				
				response.getWriter().write(resp.toString());
				

			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	}
	
	}

