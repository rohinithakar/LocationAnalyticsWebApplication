package utils;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class UtilsClient {
	
	 public static boolean validateLogin( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		String user = getUserSession(request);
		System.out.println("user validate login: "+user);
		if( user == null ) {
			response.sendRedirect("index.jsp");
			return false;
		}
		return true;
	}
	
	
	public static String getUserSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("session: "+session);
		System.out.println("Session value:"+session.getAttribute("userId"));
		String userId = (String) session.getAttribute("userId");
		return userId;
	}

}
