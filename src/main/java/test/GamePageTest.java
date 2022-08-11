package test;

import gameManager.GamePageServlet;
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

public class GamePageTest extends Mockito{

	private GamePageServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new GamePageServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//visualizzazione corretta del gioco
	@Test
	public void testCase_1() throws ServletException, IOException{
	
		request.addParameter("id", "1");

		servlet.doPost(request, response);
		assertEquals("Final Fantasy VII", response.getContentAsString());
		
	}
	
	//visualizzazione errata di un gioco che non esiste
		@Test
		public void testCase_2() throws ServletException, IOException{
		
			request.addParameter("id", "69");

			servlet.doPost(request, response);
			assertEquals("errore", response.getContentAsString());
			
		}
	
	//parametro id non valido
		@Test
		public void testCase_3() throws ServletException, IOException{
				
			request.addParameter("id", "0");

			servlet.doPost(request, response);
			assertEquals("errore SQL", response.getContentAsString());
					
		}
		
	//parametro id null
		@Test
		public void testCase_4() throws ServletException, IOException{
					
		try {
				servlet.doPost(request, response);
				fail("Valore null");
			}catch(Exception e) {
					//success
			}
							
		}
	
}
