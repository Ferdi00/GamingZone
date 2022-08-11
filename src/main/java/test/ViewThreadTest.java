package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import threadManager.ViewThreadServlet;
import util.ConnectionPool;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ViewThreadTest extends Mockito {

	private ViewThreadServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new ViewThreadServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//visualizzazione corretta del thread
	@Test
	public void testCase_1() throws ServletException, IOException{
	
		request.addParameter("threadid", "100");

		servlet.doPost(request, response);
		assertEquals("Party di Final Fantasy VII", response.getContentAsString());
		
	}
	
	//visualizzazione errata del thread
	@Test
	public void testCase_2() throws ServletException, IOException{
		
		request.addParameter("threadid", "0");

		servlet.doPost(request, response);
		assertEquals("errore", response.getContentAsString());
			
	}
	
	//valore null
	@Test
	public void testCase_3() throws ServletException, IOException{
		
		try {
		servlet.doPost(request, response);
		fail("Valore null");
		}catch(Exception e) {
			//success
		}
			
	}
}
