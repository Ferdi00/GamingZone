package test;

import gameManager.Gioco;
import gameManager.GiocoDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionPool;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GiocoDAOTest {

	private static GiocoDAO giocoDAO = new GiocoDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
    }
	
	@Test
	public void TestFindAllGames() throws SQLException{
		ArrayList<Gioco> games  = giocoDAO.findAllGames();
		assertEquals(games.size(), 19);
	}
	
	@Test
	public void TestGetTopRated() throws SQLException{
		boolean flag = false;
		ArrayList<Gioco> games  = giocoDAO.getTopRated();
		assertEquals(games.size(), 11);
		
		for(int i=0; i<games.size()-1;i++){ 
			if(games.get(i).getMediaVoti()<games.get(i+1).getMediaVoti()) 
			flag=true;
		}
		assertEquals(flag, false);
	}
	
	@Test
	public void TestFindGameById() throws SQLException{
		//il gioco esiste
		int id = 1;
		Gioco g = new Gioco();
		g= giocoDAO.findGameById(id);
		assertNotNull(g);
		
		//il gioco non esiste
		id = 69;
		g= giocoDAO.findGameById(id);
		assertNull(g);
		
		//il valore id non è valido
		try {
			g=giocoDAO.findGameById(0);
			fail("Valore non valido");
		} catch(SQLException e) {
			//success
		}
		
	}
	
	@Test
	public void TestFindByPublisher() throws SQLException{
		
		ArrayList<Gioco> games  = giocoDAO.findByPublisher("Nintendo");
		assertEquals(games.size(), 5);
		
		games  = giocoDAO.findByPublisher("Sony");
		assertEquals(games.size(), 1);
		
		games  = giocoDAO.findByPublisher("Chucklefish");
		assertEquals(games.size(), 0);
		
		games  = giocoDAO.findByPublisher(null);
		assertEquals(games.size(), 0);
		
	}
	
	@Test
	public void TestFindByGenre() throws SQLException{
		ArrayList<Gioco> games  = giocoDAO.findByGenre("Roguelike");
		assertEquals(games.size(), 2);
		
		games  = giocoDAO.findByGenre("Arcade");
		assertEquals(games.size(), 1);
		
		games  = giocoDAO.findByGenre("Survival Horror");
		assertEquals(games.size(), 0);
		
		games  = giocoDAO.findByGenre(null);
		assertEquals(games.size(), 0);
		
	}
	
	@Test
	public void TestCheckExistingGame() throws SQLException{
		boolean flag = false;
		flag = giocoDAO.checkExistingGame("DOOM", "Bethesda", "2016");
		assertEquals(flag, true);
		
		flag = giocoDAO.checkExistingGame("DOOM", "id Software", "1993");
		assertEquals(flag, false);
		
		try {
			flag = giocoDAO.checkExistingGame(null, "id Software", "1993");
		}catch(SQLException e) {
			//success
		}
		try {
			flag = giocoDAO.checkExistingGame("DOOM", null, "1993");
		}catch(SQLException e) {
			//success
		}
		try {
			flag = giocoDAO.checkExistingGame("DOOM", "id Software", null);
		}catch(SQLException e) {
			//success
		}
	}
	
	@Test
	public void TestGetPublishers() throws SQLException{
		
		ArrayList<String> publishers = giocoDAO.GetPublishers();
		assertEquals(publishers.size(), 12);
	}
	
	@Test
	public void TestGetGenres() throws SQLException{
		
		ArrayList<String> genres = giocoDAO.GetGenres();
		assertEquals(genres.size(), 9);
	}
	
	@Test
	public void TestAddGame() throws SQLException{
		
		ArrayList<Gioco> prev = giocoDAO.findAllGames();
		Gioco g = new Gioco("nomeGioco", "Descrizione", "Publisher", "2000", "Genere"); //gli altri valori sono di default
		GiocoDAO.addGame(g);
		ArrayList<Gioco> now = giocoDAO.findAllGames();
		assertEquals(now.size(), prev.size()+1);
	}

	@Test
	public void TestAddGameException(){
		
		Gioco g = new Gioco(); 
		try {
			GiocoDAO.addGame(g);
			fail("Parametri non specificati");
		} catch (SQLException e) {
			//success
		}
	}
	
	@Test
	public void TestUpdateAverage() throws SQLException{
		//aggiornamento media per un gioco esistente
		float mediaPrec = giocoDAO.findGameById(1).getMediaVoti();
		GiocoDAO.updateAverage(1, 10);
		float mediaAtt = giocoDAO.findGameById(1).getMediaVoti();
		assertNotEquals(mediaPrec, mediaAtt);
		
		//aggiornamento media per un gioco inesistente
		assertEquals(GiocoDAO.updateAverage(0, 10), false);
	}
}
