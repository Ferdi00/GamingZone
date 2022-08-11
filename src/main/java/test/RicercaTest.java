package test;

import gameManager.Ricerca;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import util.ConnectionPool;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RicercaTest extends Mockito{

	private Ricerca servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new Ricerca();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//ricerca di un elemento che esiste
	@Test
	public void testCase_1() throws ServletException, IOException{
	
		request.addParameter("cerca", "hades");

		servlet.doGet(request, response);
		
		assertEquals("found", request.getAttribute("result"));
		assertEquals(1, request.getAttribute("resultSize"));
		
	}
	
	//ricerca che porta a più di un elemento
	@Test
	public void testCase_1_2() throws ServletException, IOException{
	
		request.addParameter("cerca", "mario");

		servlet.doGet(request, response);
		
		assertEquals("found", request.getAttribute("result"));
		assertEquals(2, request.getAttribute("resultSize"));
		
	}
	
	//ricerca di un elemento che non esiste
	@Test
	public void testCase_2() throws ServletException, IOException{
		
		request.addParameter("cerca", "xmb");

		servlet.doGet(request, response);
			
		assertEquals("noResults", request.getAttribute("result"));
			
	}
		
	//parametro vuoto
	@Test
	public void testCase_3() throws ServletException, IOException{
		
		servlet.doGet(request, response);
					
		assertEquals("noResults", request.getAttribute("result"));
					
	}
	
}
