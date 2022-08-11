package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import threadManager.CreateThread;
import util.ConnectionPool;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CreateThreadTest extends Mockito{
	
	private CreateThread servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new CreateThread();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//creazione corretta del thread
	@Test
	public void testCase_1() throws ServletException, IOException{
		String title = "TitoloThread";
		String type = "TipoThread";
		String text = "TestoThread";
		String username = "AerithGain";
		String gameID = "1";
		String message = title+" "+type+" "+text+" "+gameID+" "+username;
	
		request.addParameter("thread_title", title);
		request.addParameter("thread_type", type);
		request.addParameter("thread_text", text);
		request.addParameter("gameId", gameID);
		request.addParameter("username", username);

		servlet.doGet(request, response);
		assertEquals("true", (String)request.getAttribute("inserimentoThread"));
		assertEquals(message, response.getContentAsString());
		
	}
	
	//test per lanciare l'eccezione - utente non esistente
	@Test
	public void testCase_2() throws ServletException, IOException{
	
		request.addParameter("thread_title", "titolo");
		request.addParameter("thread_type", "TipoThread");
		request.addParameter("thread_text", "TestoThread");
		request.addParameter("gameId", "0");
		request.addParameter("username", "UtenteNonEsistente");
		
		//utente non esistente
		servlet.doGet(request, response);
		assertEquals("false", (String)request.getAttribute("inserimentoThread"));
		
		
	}
	
	//gioco inesistente
	@Test
	public void testCase_3() throws ServletException, IOException{
		
		request.addParameter("thread_title", "titolo");
		request.addParameter("thread_type", "TipoThread");
		request.addParameter("thread_text", "TestoThread");
		request.addParameter("gameId", "0");
		request.addParameter("username", "AerithGain");
		
		//utente non esistente
		servlet.doGet(request, response);
		assertEquals("false", (String)request.getAttribute("inserimentoThread"));
		
		
	}
	
	//valori della request nulli
	@Test
	public void testCase_4() throws ServletException, IOException{
			
		try {
			servlet.doGet(request, response);
			fail("null");
		} catch (Exception e) {
				
		}
			
	}

}
