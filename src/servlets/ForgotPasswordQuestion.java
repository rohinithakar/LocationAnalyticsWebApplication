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

import dao.ForgotPasswordQuestionDAO;

import java.util.logging.Logger;
/**
 * Servlet implementation class ForgotPasswordQuestion
 */
public class ForgotPasswordQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");

		JSONObject resp = new JSONObject();


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


			ForgotPasswordQuestionDAO dao = new ForgotPasswordQuestionDAO();
			String question = dao.forgotPasswordQuestion(email);

			if(question != null){
				resp.put("errorCode",200);
				resp.put("responseText",question);
				System.out.println("Response: "+resp);	
			}
			else{
				resp.put("errorCode",300);
				resp.put("responseText","failure");
				System.out.println("Response: "+resp);			
			}
			response.getWriter().write(resp.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}







	}
}

