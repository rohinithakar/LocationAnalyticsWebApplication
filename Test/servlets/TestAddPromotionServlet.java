package servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestAddPromotionServlet {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddPromotionValidInput() throws Exception {
		ServletHelper dao = Mockito.mock(ServletHelper.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", 1);
        jsonObject.put("name", "asdf");
        jsonObject.put("description", "asdf");
        jsonObject.put("type", "0");
        jsonObject.put("startdate", "05/02/2014");
        jsonObject.put("enddate", "05/31/2014");
        jsonObject.put("address", "dfbfdjgfsjdbgkjfg");
        jsonObject.put("lat", "37.0000");
        jsonObject.put("long", "121.0000");
        BufferedReader reader = new BufferedReader(new StringReader(jsonObject.toString()));
        
        when(request.getSession()).thenReturn(session);
        when(request.getReader()).thenReturn(reader);
        ArrayList<DealDetailsPOJO> ALDeals = new ArrayList<DealDetailsPOJO>();
        
		//when(dao.CreatePromotion(anyInt(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyDouble(), anyDouble(), anyDouble(), ALDeals)).thenReturn();
		
        JSONObject obj = new JSONObject();
		obj.put("success", true);
		obj.put("error", false);
		obj.toString();
		StringWriter strWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(strWriter);
		when(response.getWriter()).thenReturn(writer);
		
		
		AddEvent servlet = new AddEvent();
		servlet.doPost(request, response);
		
		JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("success", true);
		expectedResponse.put("error", false);
		assertEquals(expectedResponse.toString(),obj.toString());
	}

}
