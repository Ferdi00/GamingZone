package test;

import listManager.AddToListServlet;
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

public class AddToListServletTest extends Mockito {

	private AddToListServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new AddToListServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//inserimento corretto
	@Test
	public void testCase_1_1() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "4");
			request.addParameter("category_value", "Acquistato");
			request.addParameter("score", "120");
			request.addParameter("usrScore", "390");
			
			int newScore = 510;
			String message = "Aggiunto alla lista, nuovo punteggio = "+newScore;

			servlet.doPost(request, response);
			
			assertEquals(message, response.getContentAsString());
		}
	
	@Test
	public void testCase_1_2() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "5");
			request.addParameter("category_value", "In corso");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
			
			int newScore = 500;
			String message = "Aggiunto alla lista, nuovo punteggio = "+newScore;

			servlet.doPost(request, response);
			
			assertEquals(message, response.getContentAsString());
		}
	
	@Test
	public void testCase_1_3() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "5");
			request.addParameter("category_value", "Completato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
			
			int newScore = 520;
			String message = "Aggiunto alla lista, nuovo punteggio = "+newScore;

			servlet.doPost(request, response);
			
			assertEquals(message, response.getContentAsString());
		}
	
	@Test
	public void testCase_1_4() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "5");
			request.addParameter("category_value", "Platinato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
			
			int newScore = 540;
			String message = "Aggiunto alla lista, nuovo punteggio = "+newScore;

			servlet.doPost(request, response);
			
			assertEquals(message, response.getContentAsString());
		}
	
	@Test
	public void testCase_1_5() throws ServletException, IOException{
		
			request.addParameter("username", "AonumaTLOZ");
			request.addParameter("game_id", "2");
			request.addParameter("category_value", "Sviluppato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "0");
			
			int newScore = 200;
			String message = "Aggiunto alla lista, nuovo punteggio = "+newScore;

			servlet.doPost(request, response);
			
			assertEquals(message, response.getContentAsString());
		}
	
	//inserimento errato, per coprire l'eccezione
	@Test
	public void testCase_2() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "1");
			request.addParameter("category_value", "Platinato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");

			servlet.doPost(request, response);
			
			assertEquals("notAdded", (String)request.getAttribute("result"));
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
