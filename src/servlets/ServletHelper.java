package servlets;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ServletHelper {
	
	Connection con = null;
	ResultSet rs;
	Statement stmt = null;
	
	
	public void CreatePromotion(String promotionName,String promotionDescription,String promotionType,String promotionStartDate,String promotionEndDate,String promotionLocationAddress,double promotionLocationLattitude,double promotionLocationLongitude,double promotionLocationAltitude,ArrayList<DealDetailsPOJO> ALDeals)
	{
		
		System.out.println("Inside create promotion");
		//System.out.print(promotionLocationAddress);
		
		
		
		DatabaseConnection connection = DatabaseConnection.getInstance();
		con = connection.setConnection();
		try {
			stmt = con.createStatement();
			stmt.setEscapeProcessing(true);
			
			if(!con.isClosed()){
				System.out.println("Successfully Connected!");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try
		{	
			System.out.println("inside PromotionDeals Insert");	
						
			String query = "INSERT INTO \"PromotionalDeals\"(promotionName,promotionDescription,promotionType,promotionStartDate,promotionEndDate,promotionLocationAddress,promotionLocationLattitude)"+
					"VALUES ('"+promotionName+"','"+promotionDescription+"','"+promotionType+"','"+promotionStartDate+"','"+promotionEndDate+"','"+promotionLocationAddress+"'," + "POINT(" + promotionLocationLattitude + "," + promotionLocationLongitude + ")) RETURNING \"PromotionalDeals\".\"promotionid\""; 
							//"promotionLocationLattitude + "," + promotionLocationLongitude + ","+ promotionLocationAltitude + ")";
			System.out.println("Query: "+query);
			PreparedStatement prpState = con.prepareStatement(query);
			ResultSet rs = prpState.executeQuery();
			if(rs.next()){
			      System.out.println("returned value");
			      CreateDeals(ALDeals,  rs.getInt(1));
					}}
	catch(Exception sql){
		sql.printStackTrace();}
	}
	
	
	public void CreateDeals(ArrayList<DealDetailsPOJO> ALDeals,int pid){
		
		System.out.print("inside createdeals");
		
		for(int i=0;i<ALDeals.size();i++){
			
			try {
			//convert array of tag [School,Education] into Comma Separated String like {School,Education}
				//System.out.println(ALDeals.get(i).getAltags());	
			String tags = arrayToString(ALDeals.get(i).getAltags());
				String query = "INSERT INTO \"PromotionAdDetails\"(adName,adDescription,adDetail,adTags,adCount,numberOfAttendees,adScheduleDetail,p_Id,subscribeddealcount)"+
						"VALUES ('"+ALDeals.get(i).getDealname() +"','"+ ALDeals.get(i).getDealDesc() +"','"+ALDeals.get(i).getDealDetail()+"','"+tags+"',"+ALDeals.get(i).getDealNumbers()+","+ALDeals.get(i).getDealEventAttendees()+",'" + ALDeals.get(i).getScheduleTime() + "'," + pid + ",0)";	
			
				System.out.print(query);
				int rowCount = stmt.executeUpdate(query);
				if(rowCount>1)
					System.out.print("Ad Inserted Successfully");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
			
	}
	
	public String arrayToString(ArrayList<String> alDeal)
	{
	    if (alDeal.size() == 0) return "";
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < alDeal.size(); ++i)
	    {
	        sb.append(":").append(alDeal.get(i)).append("");
	    }
	    return sb.substring(1);
	}
	
}


