package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userManager.Utente;
import userManager.UtenteDAO;
import util.ConnectionPool;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UtenteDAOTest {

	private static UtenteDAO utenteDAO = new UtenteDAO();
	
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
    }
	
	@Test
	void TestCheckLogin() throws SQLException {
		//login corretto
		Utente u = new Utente();
		u = utenteDAO.checkLogin("againsborough@yahoo.it", "midgar03");
		assertNotNull(u);
		
		//email errata
		u = utenteDAO.checkLogin("emailerrata", "midgar03");
		assertNull(u);
		
		//password errata
		u = utenteDAO.checkLogin("againsborough@yahoo.it", "midgar04");
		assertNull(u);
		
		//password == null
		try {
			u = utenteDAO.checkLogin("againsborough@yahoo.it", null);
			fail("password null");
		}catch(SQLException e) {
			//success
		}
		
		//email == null
		try {
			u = utenteDAO.checkLogin(null, "pass");
			fail("email null");
		}catch(SQLException e) {
			//success
		}
	}
	

	@Test
	void TestCheckEmail() throws SQLException {
		boolean flag = false;
		
		//email corretta
		flag = utenteDAO.checkEmail("againsborough@yahoo.it");
		assertEquals(flag, true);
		
		//email errata
		flag = utenteDAO.checkEmail("questaeunemail@errata.it");
		assertEquals(flag, false);
		
		//email == null
		try {
			flag = utenteDAO.checkEmail(null);
			fail("email null");
		}catch(SQLException e) {
			//success
		}
	}
	
	@Test
	void TestCheckPassword() throws SQLException {
		boolean flag = false;
		
		//password corretta
		flag = utenteDAO.checkPassword("againsborough@yahoo.it", "midgar03");
		assertEquals(flag, true);
		
		//password errata
		flag = utenteDAO.checkPassword("againsborough@yahoo.it", "pass");
		assertEquals(flag, false);
		
		//password errata, ma di un altro utente
		flag = utenteDAO.checkPassword("againsborough@yahoo.it", "midgar01");
		assertEquals(flag, false);
		
		//password == null
		try {
			flag = utenteDAO.checkPassword("againsborough@yahoo.it", null);
			fail("password null");
		}catch(SQLException e) {
			//success
		}
		
		//email == null
		try {
			flag = utenteDAO.checkPassword(null, "pass");
			fail("email null");
		}catch(SQLException e) {
			//success
		}
		
		//email == null && password == null
		try {
			flag = utenteDAO.checkPassword(null, null);
			fail("valori null");
		}catch(SQLException e) {
			//success
		}
	}
	
	@Test
	void TestGetScore() throws SQLException{
		int score;
		//utente esistente
		score=utenteDAO.getScore("AerithGain");
		assertEquals(score, 390);
		
		//utente inesistente
		score=utenteDAO.getScore("NonEsisto");
		assertEquals(score, -1);
		
		//utente null
		try {
			score=utenteDAO.getScore(null);
			fail("Valore null");
		} catch (SQLException e) {
			//success
		}
	}
	
	@Test
	void TestFindTopUsers() throws SQLException{
		boolean flag = false;
		ArrayList<Utente> users = new ArrayList<Utente>();
		users = utenteDAO.findTopUsers();
		assertEquals(users.size(), 10); //verifico che l'array contiene la top 10
		
		for(int i=0; i<users.size()-1;i++){ //scorro l'array per vedere se i punteggi sono ordinati
			if(users.get(i).getPunteggio()<users.get(i+1).getPunteggio()) 
			flag=true; //se non lo sono cambio il flag
		}
		assertEquals(flag, false);
	}

}
