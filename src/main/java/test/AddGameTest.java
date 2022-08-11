package test;

import gameManager.AddGame;
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

public class AddGameTest extends Mockito{
	
	private AddGame servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new AddGame();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//aggiunta corretta del gioco
	@Test
	public void testCase_1() throws ServletException, IOException{
	
		request.addParameter("game_title", "Pikmin");
		request.addParameter("publisher", "Nintendo");
		request.addParameter("game_genre", "Strategico");
		request.addParameter("game_year", "2001");
		request.addParameter("game_desc", "Pikmin è un videogioco di strategia e rompicapo in tempo reale creato da Shigeru Miyamoto e pubblicato da Nintendo.");
		request.addParameter("score", "100");

		servlet.doGet(request, response);
		
		assertEquals("success", request.getAttribute("aggiuntaGioco"));
		
	}
	
	//aggiunta di un gioco esistente
	@Test
	public void testCase_1_1() throws ServletException, IOException{
		
		request.addParameter("game_title", "DOOM");
		request.addParameter("publisher", "Bethesda");
		request.addParameter("game_genre", "Sparatutto");
		request.addParameter("game_year", "2016");
		request.addParameter("game_desc", "Descrizione");
		request.addParameter("score", "100");

		servlet.doGet(request, response);
			
		assertEquals("existing", request.getAttribute("aggiuntaGioco"));
			
	}
	
	//aggiunta di un gioco nuovo, che differisce da uno esistente per il publisher e l'anno
	@Test
	public void testCase_1_2() throws ServletException, IOException{
			
		request.addParameter("game_title", "DOOM");
		request.addParameter("publisher", "id Software");
		request.addParameter("game_genre", "Sparatutto");
		request.addParameter("game_year", "1993");
		request.addParameter("game_desc", "Descrizione");
		request.addParameter("score", "100");

		servlet.doGet(request, response);
				
		assertEquals("success", request.getAttribute("aggiuntaGioco"));
				
	}
	
	//test per lanciare l'eccezione - valori nulli
	@Test
	public void testCase_2() throws ServletException, IOException{
	
	
		request.addParameter("game_genre", "Sparatutto");
		request.addParameter("game_year", "2016");
		request.addParameter("game_desc", "Descrizione");
		request.addParameter("score", "100");
		
		
		servlet.doGet(request, response);
		assertEquals("error", request.getAttribute("aggiuntaGioco"));
		
		
	}
	
	@Test
	public void testCase_2_1() throws ServletException, IOException{
	
		request.addParameter("game_title", "DOOM");
		request.addParameter("publisher", "id Software");
		request.addParameter("game_year", "1993");
		request.addParameter("score", "100");
		
		servlet.doGet(request, response);
		assertEquals("errorSQL", request.getAttribute("aggiuntaGioco"));
		
		
	}
	
	

}

