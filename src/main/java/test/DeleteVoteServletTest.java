package test;

import gameManager.DeleteVoteServlet;
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

public class DeleteVoteServletTest extends Mockito {

	private DeleteVoteServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new DeleteVoteServlet();
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
			float media = (float) 8;

			servlet.doPost(request, response);
			
			assertEquals("success", request.getAttribute("result"));
			assertEquals(media, request.getAttribute("newAverage"));
		}
	
	//eliminazione corretta dell'ultimo voto per un determinato gioco
	@Test
	public void testCase_1_1() throws ServletException, IOException{
			
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "3");
			float media = (float) 0;

			servlet.doPost(request, response);
				
			assertEquals("success", request.getAttribute("result"));
			assertEquals(media, request.getAttribute("newAverage"));
		}
	
	//errore, eliminazione di un elemento che non esiste
	@Test
	public void testCase_2() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "4");
			
			servlet.doPost(request, response);
			
			assertEquals("fail", request.getAttribute("result"));
	}
	
	//valori nulli
	@Test
	public void testCase_3() throws ServletException, IOException{
	
		servlet.doPost(request, response);
		assertEquals("fail", request.getAttribute("result"));
	
	}
	
}

