package servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dao.DeletePromotionDAO;
import dao.LoginDAO;

public class TestDeletePromotionServlet {

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
	public void testDeletePromotion() throws Exception {
		DeletePromotionDAO dao = Mockito.mock(DeletePromotionDAO.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("promotionid", "24");
		BufferedReader reader = new BufferedReader(new StringReader(jsonObject.toString()));

		when(request.getSession()).thenReturn(session);
		when(request.getReader()).thenReturn(reader);
		
		when(dao.deletePromotion(anyString())).thenReturn("true");
		StringWriter strWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(strWriter);
		when(response.getWriter()).thenReturn(writer);
		
		DeletePromotion servlet  = new DeletePromotion();
		servlet.doPost(request, response);

		JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("errorCode", 200);
		expectedResponse.put("responseText", "Success");
		assertEquals(expectedResponse.toString(),strWriter.toString());

	}
}
