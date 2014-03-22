package servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.codehaus.jettison.json.JSONObject;

/**
 * Servlet implementation class PromotionHandler
 */
//@WebServlet("/PromotionHandler")
public class PromotionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromotionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("in promotionHandler");
		response.setContentType("application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;
		ServletHelper sh = new ServletHelper();
		
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e)
		{ 
			//report an error
			System.out.println("During initial read error");
			e.printStackTrace();
		}
		
		System.out.println(jb);
		try {
			jsonObject = new JSONObject(jb.toString());
			String promotionName = jsonObject.getString("name");
			String promotionDescription = jsonObject.getString("description");
			String promotionType = jsonObject.getString("type");
			String promotionStartDate = jsonObject.getString("startdate");
			String promotionEndDate = jsonObject.getString("enddate");
			String promotionLocationAddress = jsonObject.getString("address") +","+ jsonObject.getString("city") +","+ jsonObject.getInt("pincode");
			double promotionLocationLattitude = jsonObject.getDouble("lat");
			double promotionLocationLongitude = jsonObject.getDouble("long");
			System.out.println("lattitude" + promotionLocationLattitude);
			//double promotionLocationLattitude=0.0;
			//double promotionLocationLongitude=0.0;
			double promotionLocationAltitude =0.0;
			org.codehaus.jettison.json.JSONArray deals = jsonObject.getJSONArray("deal");
			
			ArrayList<DealDetailsPOJO> ALDeals = new ArrayList<DealDetailsPOJO>();
			for(int i=0;i<deals.length();i++){
			
			DealDetailsPOJO dp= new DealDetailsPOJO();
			JSONObject d = deals.getJSONObject(i);
			System.out.println(d.getString("name"));
			dp.setDealname(d.getString("name"));
			dp.setDealDesc(d.getString("description"));
			dp.setDealNumbers(d.getString("noofDeals"));
			dp.setDealEventAttendees(d.getString("noofAttendees"));
			dp.setScheduleTime(d.getString("scheduletime"));
			
			//iterate through tags
			org.codehaus.jettison.json.JSONArray tags = d.getJSONArray("tags");
			System.out.println(tags);
			
			
			
			ArrayList<String> tagsArray = new ArrayList<String>();
				for(int j=0;j<tags.length();j++){
					String ij = (String) tags.get(j);
					System.out.println("a " + ij);
					tagsArray.add(ij);
				}
			
			dp.setAltags(tagsArray);
			
			ALDeals.add(dp);
				System.out.print(dp.getDealname());
					
			}
			
			System.out.print("d   " + ALDeals.get(0).getDealNumbers());
			
			//make call to database.
		sh.CreatePromotion(promotionName, promotionDescription, promotionType, promotionStartDate, promotionEndDate, promotionLocationAddress, promotionLocationLattitude, promotionLocationLongitude, promotionLocationAltitude,ALDeals);
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}

}
