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
import dao.DeletePromotionDAO;

/**
 * Servlet implementation class GetLoginDetails
 */
public class DeletePromotion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
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
			JSONObject jsonObject = new JSONObject(jb.toString());
			System.out.println("jsonObject:"+jsonObject.toString());

			String promotionid = jsonObject.getString("promotionid");
			System.out.println("promotionid:"+promotionid);


			Connection con = null;
			ResultSet rs;
			Statement stmt = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			DeletePromotionDAO dao = new DeletePromotionDAO();

			String ans = dao.deletePromotion(promotionid);
			if(ans.equalsIgnoreCase("true")){
				System.out.println("Promotion Delete Successful");
				resp.put("errorCode",200);
				resp.put("responseText","Success");
			}
			else{
				System.out.println("Promotion Delete failed");
				resp.put("errorCode",300);
				resp.put("responseText","Failure");
			}
		response.getWriter().write(resp.toString());

	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  catch (InstantiationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalAccessException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	} catch (ClassNotFoundException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
