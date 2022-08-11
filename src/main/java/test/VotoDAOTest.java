package test;

import gameManager.Voto;
import gameManager.VotoDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionPool;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VotoDAOTest {

	private static VotoDAO votoDAO = new VotoDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
    }
	
	
	@Test
	void TestAddVote() throws SQLException{
		//inserimento corretto
		Voto voto = new Voto(8, "Zagreus94", 12);
		
		ArrayList <Voto> prev = votoDAO.findAllVotes();
		VotoDAO.addVote(voto);
		ArrayList <Voto> next = votoDAO.findAllVotes();
		assertEquals(prev.size()+1, next.size());
	}
	
	@Test
	void TestAddVoteException(){
		//il gioco non esiste
		Voto voto = new Voto(8, "Zagreus94", 0);
		try {
				VotoDAO.addVote(voto);
				fail("Gioco inesistente");
			} catch (SQLException e) {
			// success
			}
		//l'utente non esiste
		voto = new Voto(8, null, 0);
		try {
				VotoDAO.addVote(voto);
				fail("Utente inesistente");
			} catch (SQLException e) {
				// success
			}
		//chiave primaria gia presente
		voto = new Voto(8, "AerithGain", 1);
		try {
				VotoDAO.addVote(voto);
				fail("Chiave primaria dev'essere unica");
			} catch (SQLException e) {
				// success
			}
		//voto null
		try {
			VotoDAO.addVote(null);
			fail("Il voto non puo essere null");
		} catch (SQLException e) {
			// success
		}
	}
	
	@Test
	void TestGetVote() throws SQLException{
		//voto esistente
		Voto voto = votoDAO.getVote(1, "AerithGain");
		assertNotNull(voto);
		//voto non esistente
		voto = votoDAO.getVote(1, "Zagreus94");
		assertNull(voto);
		//id<=0
		try {
		voto = votoDAO.getVote(0, "AerithGain");
		fail("ID non valido");
		} catch (SQLException e) {
			// success
		}
		//username==null
		try {
			voto = votoDAO.getVote(1, null);
		fail("username null");
		} catch (SQLException e) {
			// success
		}
	}

	@Test
	void TestDeleteVote() throws SQLException {
		//eliminazione elemento inesistente, il numero dei voti rimane lo stesso
		ArrayList <Voto> prev = votoDAO.findAllVotes();
		VotoDAO.deleteVote(8, "AerithGain");
		ArrayList <Voto> next = votoDAO.findAllVotes();
		assertEquals(prev.size(), next.size());
		
		//eliminazione corretta
		VotoDAO.deleteVote(1, "AerithGain");
		next = votoDAO.findAllVotes();
		assertEquals(prev.size()-1, next.size());
		
		//id<=0
		try {
		VotoDAO.deleteVote(0, "AerithGain");
		fail("ID non valido");
		} catch (SQLException e) {
			// success
		}
		//username==null
		try {
		VotoDAO.deleteVote(1, null);
		fail("username null");
		} catch (SQLException e) {
			// success
		}
	}
	
	@Test
	void TestUpdateVote() throws SQLException {
		ArrayList <Voto> prev = votoDAO.findAllVotes();
		//modifica corretta
		Voto voto = votoDAO.getVote(1, "AerithGain");
		int previous = voto.getValutazione();
		VotoDAO.updateVote("AerithGain", 1, 8);
		voto = votoDAO.getVote(1, "AerithGain");
		int current = voto.getValutazione();
		assertNotEquals(previous, current);
		ArrayList <Voto> next = votoDAO.findAllVotes();
		assertEquals(prev.size(), next.size());
		
		//elemento inesistente
		VotoDAO.updateVote("AerithGain", 4, 8);
		next = votoDAO.findAllVotes();
		assertEquals(prev.size(), next.size());
		
		//id<=0
		try {
		VotoDAO.updateVote("AerithGain", 0, 7);
		fail("ID non valido");
		} catch (SQLException e) {
			// success
		}
		//username==null
		try {
		VotoDAO.updateVote(null, 1, 7);
		fail("username null");
		} catch (SQLException e) {
			// success
		}
		//valutazione non valida
		try {
		VotoDAO.updateVote("AerithGain", 1, -1);
		fail("username null");
		} catch (SQLException e) {
			// success
		}
		
	}
	
	@Test
	void TestUpdateSameVote() throws SQLException {
		//modifica con lo stesso voto
		ArrayList <Voto> prev = votoDAO.findAllVotes();
		Voto voto = votoDAO.getVote(1, "AerithGain");
		int previous = voto.getValutazione();
		VotoDAO.updateVote("AerithGain", 1, 9);
		voto = votoDAO.getVote(1, "AerithGain");
		int current = voto.getValutazione();
		assertEquals(previous, current);
		ArrayList <Voto> next = votoDAO.findAllVotes();
		assertEquals(prev.size(), next.size());
		
	}
	
	@Test
	void TestCalculateAverage() throws SQLException {
		float avg = 0;
		//gioco con piu di un voto
		avg = votoDAO.calculateAverage(1);
		assertEquals(avg, 8.5);
		//gioco con un solo voto
		avg = votoDAO.calculateAverage(3);
		assertEquals(avg, 10);
		//gioco non presente
		avg = votoDAO.calculateAverage(0);
		assertEquals(avg, 0);
		
	}
	
}
