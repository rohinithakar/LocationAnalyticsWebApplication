package servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
import org.openqa.jetty.http.HttpRequest;

import dao.LoginDAO;

public class TestLoginServlet {

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
	public void testLoginValidInput() throws Exception {
		LoginDAO dao = Mockito.mock(LoginDAO.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "abc");
        jsonObject.put("password", "asdf");
        BufferedReader reader = new BufferedReader(new StringReader(jsonObject.toString()));
        
        when(request.getSession()).thenReturn(session);
        when(request.getReader()).thenReturn(reader);
        
		when(dao.getLogin(anyString(), anyString())).thenReturn(23);
		
		StringWriter strWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(strWriter);
		when(response.getWriter()).thenReturn(writer);
		
		
		GetLoginDetails servlet = new GetLoginDetails(dao);
		servlet.doPost(request, response);
		
		verify(dao).getLogin(eq("abc"), anyString());
		JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("errorCode", 200);
		expectedResponse.put("responseText", "Success");
		assertEquals(expectedResponse.toString(),strWriter.toString());
	}
}
	
