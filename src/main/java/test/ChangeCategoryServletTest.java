package test;

import listManager.ChangeCategoryServlet;
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

public class ChangeCategoryServletTest extends Mockito {

	private ChangeCategoryServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new ChangeCategoryServlet();
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
			request.addParameter("game_id", "1");
			request.addParameter("category_value", "Acquistato");
			request.addParameter("user_category", "Platinato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
			
			int newScore = 340;
			String message = "Categoria aggiornata, nuovo punteggio = "+newScore;

			servlet.doPost(request, response);
			
			assertEquals(message, response.getContentAsString());
		}
	
	//inserimento errato, cambio di categoria per un elemento che non esiste
	@Test
	public void testCase_2() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "4");
			request.addParameter("category_value", "Acquistato");
			request.addParameter("user_category", "Platinato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
		

			servlet.doPost(request, response);
			
			assertEquals("Errore", response.getContentAsString());
			assertEquals(500, response.getStatus());
		}
	
	//eccezione SQL, un parametro della chiave non è specificato
	@Test
	public void testCase_2_1() throws ServletException, IOException{
		
			
			request.addParameter("game_id", "4");
			request.addParameter("category_value", "Acquistato");
			request.addParameter("user_category", "Platinato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
		

			servlet.doPost(request, response);
			
			assertEquals("Errore SQL", response.getContentAsString());
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
