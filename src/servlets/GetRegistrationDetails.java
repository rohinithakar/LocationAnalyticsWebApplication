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

import dao.RegistrationDAO;

import java.util.logging.Logger;
/**
 * Servlet implementation class GetRegistrationDetails
 */
public class GetRegistrationDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RegistrationDAO dao = new RegistrationDAO();

	public GetRegistrationDetails() {
		this(new RegistrationDAO());
	}
	
	public GetRegistrationDetails(RegistrationDAO dao) {
		this.dao = dao;
	}

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

			String outputStr = 	dao.getRegistration(firstName, lastName, email, securePwd, question, answer);
			if(outputStr.equalsIgnoreCase("false")){
				resp.put("errorCode",300);
				resp.put("responseText","Failure");
			}
			else if(outputStr.equalsIgnoreCase("duplicate")){
				resp.put("errorCode",500);
				resp.put("responseText","Duplicate");
			}
			else if (outputStr.equalsIgnoreCase("true")){
				resp.put("errorCode",200);
				resp.put("responseText","Success");		
			}

			response.getWriter().write(resp.toString());
			System.out.println("Response: "+resp);
		}
		catch(Exception e){
			e.getMessage();
		}
	}

}


