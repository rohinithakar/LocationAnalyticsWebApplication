package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import servlets.DatabaseConnection;

public class DeletePromotionDAO {
	
	public String deletePromotion(String promotionid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection con = null;
		ResultSet rs;
		Statement stmt = null;

		
		DatabaseConnection connection = DatabaseConnection.getInstance();
		con = connection.setConnection();
		stmt = con.createStatement();
		stmt.setEscapeProcessing(true);
		if(!con.isClosed()){
			System.out.println("Successfully Connected!");
		}

		String query = "UPDATE \"PromotionalDeals\" SET  dealstatus = false WHERE promotionid ="+promotionid;
		System.out.println("Query: "+query);
		int rowCount = stmt.executeUpdate(query);
		System.out.println("count: "+rowCount);

		if(rowCount>0){
			if(rowCount>0){
				System.out.println("Promotion Delete Successful");
				return "true";
			}
			else{
				System.out.println("Promotion Delete failed");
				return "false";
				
			}

		}
		return "false";
	}

}
