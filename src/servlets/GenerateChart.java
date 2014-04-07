package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import servlets.DealDetailsPOJO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Servlet implementation class GetLoginDetails
 */
public class GenerateChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static ArrayList<DealDetailsPOJO> promotionList  = new ArrayList<DealDetailsPOJO>();
	static ArrayList<Integer> promotionCount = new ArrayList<Integer>();
	static ArrayList<String> promotionName = new ArrayList<String>();
	static String userId;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		HttpSession session = request.getSession();
		userId = session.getAttribute("userId").toString();
		JSONObject resp = new JSONObject();
		getPromotions();
		try {
			getDealCount();
			
			resp.put("errorCode",200);
			resp.put("nameArray", promotionName);
			resp.put("countArray", promotionCount);
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		response.getWriter().write(resp.toString());
	}
	
	public static void getPromotions(){

		try {	
			Connection con = null;
			ResultSet rs;;
			Statement stmt = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			DatabaseConnection connection = DatabaseConnection.getInstance();
			con = connection.setConnection();
			stmt = con.createStatement();
			stmt.setEscapeProcessing(true);

			if(!con.isClosed()){
				System.out.println("Successfully Connected!");
			}

			
			int uid = Integer.parseInt(userId);

			String query = "select promotionname,promotionid from \"PromotionalDeals\" where userid = "+uid;

			System.out.println("Query: "+query);
			rs = stmt.executeQuery(query);
			System.out.println("List of promotions by user: ");

			
			if(rs != null){
				while (rs.next()){
					DealDetailsPOJO promotion = new DealDetailsPOJO();
					int id = rs.getInt("promotionid");
					String name = rs.getString("promotionname");
					promotion.setDealid(id);
					promotion.setDealname(name);
					promotionList.add(promotion);
					promotionName.add(name);
				}	
			}
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
		
		public static void getDealCount() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			
			Connection con = null;
			ResultSet rs;;
			Statement stmt = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			DatabaseConnection connection = DatabaseConnection.getInstance();
			con = connection.setConnection();
			stmt = con.createStatement();
			stmt.setEscapeProcessing(true);

			if(!con.isClosed()){
				System.out.println("Successfully Connected!");
			}

			
			for (int i=0; i < promotionList.size(); i++)
			{
				//String query1 = "select sum(subscribeddealcount) as count from \"PromotionAdDetails\" where p_id = "+promotionList.get(i).getDealid()+" group by p_id;";
				String query1 = "select sum(subscribeddealcount) from \"PromotionAdDetails\" where p_id = "+promotionList.get(i).getDealid();
				//String query1 = "select * from \"PromotionAdDetails\" where p_id = "+promotionList.get(i).getDealid();
				System.out.println("Query: "+query1);
				rs = stmt.executeQuery(query1);
				if(rs!= null)
					if(rs.next()){
					int count = rs.getInt("sum");
					promotionList.get(i).setSubscribedDealCount(count);
					promotionCount.add(count);
					
				}
			}
		
	}
}
