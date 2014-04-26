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
import dao.ForgotPasswordQuestionValidationDAO;

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

			JSONObject resp = new JSONObject();
			ForgotPasswordQuestionValidationDAO dao = new ForgotPasswordQuestionValidationDAO();
			String str = dao.forgotPwdQueValidation(ans, email);

			if(str != null){
				if(str.equalsIgnoreCase("true")){
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
			response.getWriter().write(resp.toString());
		}
		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}


}


