package servlets;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import dao.ForgotPasswordQuestionDAO;

public class TestForgotPasswordQuestionServlet {

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
	public void testForgotPasswordQuestionValidInput() throws Exception {
		ForgotPasswordQuestionDAO dao = Mockito.mock(ForgotPasswordQuestionDAO.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userName", "js@yahoo.com");
		BufferedReader reader = new BufferedReader(new StringReader(jsonObject.toString()));

		when(request.getSession()).thenReturn(session);
		when(request.getReader()).thenReturn(reader);
		
		when(dao.forgotPasswordQuestion((anyString()))).thenReturn("Car name");
		StringWriter strWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(strWriter);
		when(response.getWriter()).thenReturn(writer);
		
		ForgotPasswordQuestion servlet  = new ForgotPasswordQuestion();
		servlet.doPost(request, response);

		JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("errorCode", 200);
		expectedResponse.put("responseText", "Car name");
		assertNotNull(expectedResponse.toString());

	}
	

}
