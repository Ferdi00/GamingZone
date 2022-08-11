package gameManager;

import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Questa classe è un oggetto manager che si interfaccia con il database. Gestisce le query riguardanti l’oggetto Voto (valutazione di un certo utente per un certo gioco).
 *
 */
public class VotoDAO {
	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String addVote;
	private static String getVote;
	private static String deleteVote;
	private static String updateVote;
	private static String average;
	
	
	/**
	 * 
	 * @param voto voto da aggiungere
	 * @precondition voto!=null AND database.gioco->includes(select(g|gioco.id=voto.getIdGioco())) AND database.utente->includes (select(u|utente.username=voto.getUsernameUtente())) AND database.voto-> not includes(select(v|voto.id_gioco=voto.getIdGioco() AND voto.utente=voto.getUsernameUtente)
	 * @postcondition database.voto-> includes(select(v|voto.id_gioco=voto.getIdGioco() AND voto.utente=voto.getUsernameUtente)
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean addVote(Voto voto) throws SQLException 
	{
		addVote= "INSERT INTO voto(valutazione,utente,id_gioco) values(?,?,?)";
		boolean flag=false;

		if(voto==null)
			throw new SQLException();
		else {
				try 
				{
					con= ConnectionPool.getConnection();
					statement=con.prepareStatement(addVote);
					statement.setInt(1,voto.getValutazione());
					statement.setString(2,voto.getUsernameUtente());
					statement.setInt(3,voto.getIdGioco());
					flag=statement.executeUpdate()>0;
					con.commit();
				}
				finally
				{
					try
					{
						if(statement!=null)
							statement.close();
					}
					finally
					{
						ConnectionPool.rilasciaConnessione(con);
					}
				}
				return flag;
		}
	}
	
	/**
	 * 
	 * @param idGioco id del gioco per il quale cercare il voto
	 * @param utente username dell'utente che ha votato il gioco
	 * @precondition idGioco>0 AND utente!=null
	 * @postcondition voto-> select(v|voto.id_gioco=idGioco AND voto.utente=utente)
	 * @return results.get(0) primo e unico elemento dalla lista cercata, null se è vuota
	 * @throws SQLException
	 */
	public Voto getVote(int idGioco, String utente) throws SQLException
	{
		getVote="SELECT * FROM voto WHERE id_gioco=? AND utente=?";
		ArrayList<Voto> results=new ArrayList<Voto>();
		
		if(utente==null || idGioco<=0)
			throw new SQLException();
		else {
				try 
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(getVote);
					statement.setInt(1, idGioco);
					statement.setString(2, utente);
					set=statement.executeQuery();
					while(set.next())
					{
						Voto v =new Voto();
						v.setValutazione(set.getInt(1));
						v.setUsernameUtente(set.getString(2));
						v.setIdGioco(set.getInt(3));
						results.add(v);
					}
				}
				finally
				{
					try
					{
						if(statement!=null)
							statement.close();
					}
					finally
					{
						ConnectionPool.rilasciaConnessione(con);
					}
				}
				
				if(results.size()==0) {
					return null;
				}
				return results.get(0);
		}
	}
	
	/**
	 * 
	 * @param gameid id del gioco per il quale eliminare il voto
	 * @param user username dell'utente per il quale eliminare il voto
	 * @precondition gameid>0 AND user!=null AND database.voto -> includes(select(v|voto.id_gioco=gameid AND voto.utente=user)
	 * @postcondition database.voto-> not includes(select(v|voto.id_gioco=gameid AND voto.utente=user)
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean deleteVote(int gameid, String user) throws SQLException
	{
		boolean flag=false;
		if(user==null || gameid<=0)
			throw new SQLException();
		else {
				try
				{
					deleteVote="DELETE FROM voto WHERE id_gioco=? AND utente=?";
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(deleteVote);
					statement.setInt(1,gameid);
					statement.setString(2,user);
					flag=statement.executeUpdate()>0;
					con.commit();
				}
				finally
				{
					try
					{
						if(statement!=null)
							statement.close();
					}
					finally
					{
						ConnectionPool.rilasciaConnessione(con);
					}
				}
				return flag;
		}
	}	
	
	
	/**
	 *
	 * @param user username dell'utente per il quale aggiornare il voto 
	 * @param gameID id del gioco per il quale aggiornare il voto
	 * @param voto nuova valutazione con cui sovrascrivere la precedente
	 * @precondition voto>=0 AND gameid>0 AND user!=null AND database.voto -> includes(select(v|voto.id_gioco=gameid AND voto.utente=user)
	 * @postcondition database.voto-> includes(select(v|voto.id_gioco=gameid AND voto.utente=user and voto.valutazione=voto)
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean updateVote(String user, int gameID, int voto) throws SQLException 
	{
		boolean flag=false;
		updateVote = "UPDATE voto SET valutazione=? WHERE utente=? AND id_gioco=?";
		
		if(user==null || gameID<=0 ||voto<0)
			throw new SQLException();
		else {
				try
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(updateVote);
					statement.setInt(1,voto);
					statement.setString(2,user);
					statement.setInt(3,gameID);
					flag=statement.executeUpdate()>0;
					con.commit();
				}
				finally
				{
					try
					{
						if(statement!=null)
							statement.close();
					}
					finally
					{
						ConnectionPool.rilasciaConnessione(con);
					}
				}
				return flag;
		}
	}
	
	/**
	 *
	 * @param gameid id del gioco per il quale calcolare la media
	 * @postcondition avg>=0
	 * @return avg valore della media dei voti
	 * @throws SQLException
	 */
	public float calculateAverage(int gameid) throws SQLException
	{
		float avg = 0;
		average="SELECT AVG(valutazione) FROM voto WHERE id_gioco=?";
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(average);
			statement.setInt(1, gameid);
			set=statement.executeQuery();
			while(set.next())
			{
				avg = set.getFloat(1);
			}
		}
		finally
		{
			try
			{
				if(statement!=null)
					statement.close();
			}
			finally
			{
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		return avg;
	}
	
	/**
	 *
	 * @postcondition voti->database.voto->select(v) AND voti->size()>=0
	 * @return voti lista completa di tutti i voti salvati nel database
	 * @throws SQLException
	 */
	public ArrayList<Voto> findAllVotes() throws SQLException //per il testing
	{
		String findall="SELECT * FROM voto";
		ArrayList<Voto> voti=new ArrayList<Voto>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(findall);
			set=statement.executeQuery();
			while(set.next())
			{
				Voto v = new Voto();
				v.setValutazione(set.getInt(1));
				v.setUsernameUtente(set.getString(2));
				v.setIdGioco(set.getInt(3));
				voti.add(v);
			}
		}
		finally
		{
			try
			{
				if(statement!=null)
					statement.close();
			}
			finally
			{
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		return voti;
	}
}
