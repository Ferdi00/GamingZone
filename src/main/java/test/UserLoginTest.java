package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import userManager.UserLogin;
import util.ConnectionPool;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserLoginTest extends Mockito {

	private UserLogin servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@BeforeEach
	public void setUp() throws Exception {
		servlet = new UserLogin();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//TC_2.1_1 - Email non trovata nel database
	@Test
	public void testCase_1() throws ServletException, IOException{
		request.addParameter("user_email", "emailfalsa@nonvalida.com");
		request.addParameter("user_password", "nonvalida");

		servlet.doGet(request, response);
		
		assertEquals("incorrectEmail", (String)request.getAttribute("loginResult"));
		assertNull(request.getSession().getAttribute("utenteLoggato"));
	}
	
	
	//TC_2.1_2 - Email trovata nel database, ma password errata
	@Test
	public void testCase_2() throws ServletException, IOException{
		request.addParameter("user_email", "againsborough@yahoo.it");
		request.addParameter("user_password", "nonvalida");

		servlet.doGet(request, response);
		
		assertEquals("incorrectPass", (String)request.getAttribute("loginResult"));
		assertNull(request.getSession().getAttribute("utenteLoggato"));
	}
	
	//TC_2.1_2 - Login corretto
	@Test
	public void testCase_3() throws ServletException, IOException{
		request.addParameter("user_email", "againsborough@yahoo.it");
		request.addParameter("user_password", "midgar03");

		servlet.doGet(request, response);
		
		assertEquals("logged", (String) request.getAttribute("loginResult"));
		assertEquals("AerithGain", (String) request.getSession().getAttribute("utente"));
	}
	
	//Valori null
	@Test
	public void testCase_4() throws ServletException, IOException{

		servlet.doGet(request, response);
		
		assertEquals("wrongLogin", (String) request.getAttribute("loginResult"));
		assertNull(request.getSession().getAttribute("utenteLoggato"));
	}
	
	
}