package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import threadManager.GameThread;
import threadManager.ThreadDAO;
import util.ConnectionPool;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ThreadDAOTest {

	private static ThreadDAO threadDAO = new ThreadDAO();
	
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
    }
	
	@Test
	public void TestAddThread() throws SQLException{
		//inserimento corretto di un thread
		GameThread t= new GameThread();
		t.setIdGioco(1);
		t.setTitolo("Titolo");
		t.setTesto("Testo Thread");
		t.setTipoThread("Suggerimenti");
		t.setUsernameUtente("AerithGain");
		ArrayList <GameThread> threads = threadDAO.viewThreadByGame(1);
		ThreadDAO.addThread(t);
		ArrayList <GameThread> newThreads = threadDAO.viewThreadByGame(1);
		assertEquals(threads.size()+1, newThreads.size());
	}
	
	@Test
	public void TestAddThreadException(){
		//inserimento di un thread per un gioco inesistente
		GameThread t= new GameThread();
		t.setIdGioco(0);
		t.setTitolo("Titolo");
		t.setTesto("Testo Thread");
		t.setTipoThread("Suggerimenti");
		t.setUsernameUtente("AerithGain");
		try {
			ThreadDAO.addThread(t);
			fail("Gioco inesistente");
		} catch (SQLException e) {
		
		}
		
		//inserimento di un thread da un utente inesistente
		t.setIdGioco(1);
		t.setTitolo("Titolo");
		t.setTesto("Testo Thread");
		t.setTipoThread("Suggerimenti");
		t.setUsernameUtente("UtenteCheNonEsiste");
		try {
			ThreadDAO.addThread(t);
			fail("Utente inesistente");
		} catch (SQLException e) {
		
		}
		
		//inserimento di un oggetto null
		try {
			ThreadDAO.addThread(null);
			fail("null");
		} catch (SQLException e) {
		
		}
	}
	
	
	@Test
	public void TestViewThreadById() throws SQLException{
		//il thread esiste
		int id = 100;
		GameThread t = new GameThread();
		t= threadDAO.viewThreadById(id);
		assertNotNull(t);
		
		//il thread non esiste
		id = 0;
		t= threadDAO.viewThreadById(id);
		assertNull(t);
		
	}
	
	@Test
	public void TestViewThreadByGame() throws SQLException{
		//il gioco ha più di un thread (nel database di test il gioco 1 ha 2 thread)
		int idGioco = 1;
		ArrayList<GameThread> array = new ArrayList<GameThread>();
		array= threadDAO.viewThreadByGame(idGioco);
		assertEquals(array.size(), 2);
		
		//il gioco ha un thread
		idGioco = 6;
		array= threadDAO.viewThreadByGame(idGioco);
		assertEquals(array.size(), 1);
		
		//il gioco non ha thread
		idGioco = 5;
		array= threadDAO.viewThreadByGame(idGioco);
		assertEquals(array.size(), 0);
		
		//il gioco non esiste
		idGioco = 0;
		array= threadDAO.viewThreadByGame(idGioco);
		assertEquals(array.size(), 0);
	}
	

}
