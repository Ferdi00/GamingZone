package test;

import listManager.DeleteFromListServlet;
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

public class DeleteFromListServletTest extends Mockito {

	private DeleteFromListServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new DeleteFromListServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//eliminazione corretta
	@Test
	public void testCase_1() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "1");
			request.addParameter("user_category", "Platinato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
			
			int newScore = 240;
			String message = "Eliminato, nuovo punteggio = "+newScore;

			servlet.doPost(request, response);
			
			assertEquals(message, response.getContentAsString());
		}
	
	//errore, eliminazione di un elemento che non esiste
	@Test
	public void testCase_2() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "4");
			request.addParameter("user_category", "Platinato");
			request.addParameter("score", "120");
			request.addParameter("usrScore", "390");
			

			servlet.doPost(request, response);
			
			assertEquals("Errore", response.getContentAsString());
			assertEquals(500, response.getStatus());
		}
	

	//Valori null
		@Test
		public void testCase_3() throws ServletException, IOException{
				
			try {
					servlet.doPost(request, response);
					fail("Valori null");
				}catch(Exception e) {
					//success
				}
		}
	
}

