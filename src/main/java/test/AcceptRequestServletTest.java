package test;

import gameManager.AcceptRequestServlet;
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
import static org.junit.Assert.fail;


public class AcceptRequestServletTest extends Mockito{
	
	private AcceptRequestServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new AcceptRequestServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//rifiuto di una richiesta esistente
	@Test
	public void testCase_1() throws ServletException, IOException{
	
		request.addParameter("reqid", "1");

		servlet.doPost(request, response);
		
		assertEquals("success", request.getAttribute("result"));
		
	}
	
	//rifiuto di una richiesta esistente
		@Test
		public void testCase_1_2() throws ServletException, IOException{
		
			request.addParameter("reqid", "0");

			servlet.doPost(request, response);
			
			assertEquals("fail", request.getAttribute("result"));
			
		}
	
	
	//ecezione con valore nullo
	@Test
	public void testCase_2() throws ServletException, IOException{
	
		try {
			servlet.doPost(request, response);
			fail("Valore nullo");
		} catch (Exception e) {

		}
		
	}
	
}
