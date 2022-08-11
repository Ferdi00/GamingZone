package listManager;

import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Questa classe è un oggetto manager che si interfaccia con il database. Gestisce le query riguardanti l’oggetto ElementoLista (elemento che fa riferimento a uno specifico gioco per uno specifico utente, con una determinata categoria).
 *
 */
public class ElementoListaDAO {
	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String addToList;
	private static String getList;
	private static String deleteFromList;
	private static String updateCategory;
	private static String GetGameName;
	private static String GetGameScore;
	private static String updateScore;
	private static String GetStats;
	
	/**
	 * 
	 * @param elem elemento della lista da inserire
	 * @precondition elem!=null AND database.gioco->includes(select(g|gioco.id=elem.getIdGioco())) AND database.utente->includes (select(u|utente.username=elem.getUsernameUtente())) AND database.elemento_lista->not includes(select(e|elemento_lista.utente=elem.getUsernameUtente() AND elemento_lista.id_gioco=elem.getIdGioco()))
	 * @postcondition database.elemento_lista-> includes(select(e|elemento_lista.utente=elem.getUsernameUtente() and elemento_lista.id_gioco=elem.getIdGioco())
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean addListElement(ElementoLista elem) throws SQLException 
	{
		addToList= "INSERT INTO elemento_lista (utente,id_gioco,categoria) values(?,?,?)";
		boolean flag=false;
		if (elem==null)
			throw new SQLException();
		else {
				try 
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(addToList);
					statement.setString(1,elem.getUsernameUtente());
					statement.setInt(2,elem.getIdGioco());
					statement.setString(3,elem.getCategoria());
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
	 * @param username username dell'utente di cui cercare la lista
	 * @postcondition results->size()>=0
	 * @return results lista dell'utente cercato
	 * @throws SQLException
	 */
	public ArrayList <ElementoLista> getUserList(String username) throws SQLException
	{
		getList="SELECT * FROM elemento_lista WHERE utente=? ORDER BY categoria";
		ArrayList<ElementoLista> results=new ArrayList<ElementoLista>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getList);
			statement.setString(1, username);
			set=statement.executeQuery();
			while(set.next())
			{
				ElementoLista el =new ElementoLista();
				el.setUsernameUtente(set.getString(1));
				el.setIdGioco(set.getInt(2));
				el.setCategoria(set.getString(3));
				results.add(el);
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
		return results;
	}
	
	/**
	 * 
	 * @param username username dell'utente da cercare
	 * @param gameid id del gioco aggiunto dall'utente
	 * @postcondition elemento_lista->select(e|elemento_lista.utente=username and elemento_lista.id_gioco=gameid) if database.elemento_lista->includes(e|elemento_lista.utente=username and elemento_lista.id_gioco=gameid), null altrimenti
	 * @return results.get(0) primo e unico elemento dalla lista cercata, null se è vuota
	 * @throws SQLException
	 */
	public ElementoLista getListElement(String username, int gameid) throws SQLException
	{
		String getListElement="SELECT * FROM elemento_lista WHERE utente=? AND id_gioco=?";
		ArrayList<ElementoLista> results=new ArrayList<ElementoLista>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getListElement);
			statement.setString(1, username);
			statement.setInt(2, gameid);
			set=statement.executeQuery();
			while(set.next())
			{
				ElementoLista el =new ElementoLista();
				el.setUsernameUtente(set.getString(1));
				el.setIdGioco(set.getInt(2));
				el.setCategoria(set.getString(3));
				results.add(el);
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
		
		if(results.size()!=0) {
		return results.get(0);
		} else {
		return null;
		}
	}
	
	
	/**
	 * 
	 * @param user username dell'utente da cercare
	 * @param gameid id del gioco aggiunto dall'utente
	 * @precondition user!=null AND gameid>0 
	 * @postcondition database.elemento_lista->not includes(select(e|elemento_lista.utente=user and elemento_lista.id_gioco=gameid))
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean deleteListElement(int gameid, String user) throws SQLException
	{
		
		boolean flag=false;
		
		if(gameid<=0||user==null)
			throw new SQLException();
		else {
			try
			{
				deleteFromList="DELETE FROM elemento_lista WHERE id_gioco=? AND utente=?";
				con=ConnectionPool.getConnection();
				statement=con.prepareStatement(deleteFromList);
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
	 * @param user username dell'utente da cercare
	 * @param gameID id del gioco aggiunto dall'utente
	 * @param categoria nuova categoria da assegnare all'elemento
	 * @precondition user!=null AND gameid>0 AND categoria!=null AND database.elemento_lista->includes(select(e|elemento_lista.utente=user and elemento_lista.id_gioco=gameid)
	 * @postcondition database.elemento_lista-> includes(select(e|elemento_lista.utente=user and elemento_lista.id_gioco=gameid and elemento_lista.categoria=categoria))
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean updateCategory(String user, int gameID, String categoria) throws SQLException 
	{
		boolean flag=false;
		updateCategory = "UPDATE elemento_lista SET categoria=? WHERE utente=? AND id_gioco=?";
		if(gameID<=0||user==null||categoria==null)
			throw new SQLException();
		else {
			try
			{
				con=ConnectionPool.getConnection();
				statement=con.prepareStatement(updateCategory);
				statement.setString(1,categoria);
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
	 * @param gameID id del gioco di cui cercare il nome
	 * @precondition gameID>0
	 * @postcondition name=database.gioco->select(g.nome|gioco.id=gameid) if database.gioco->includes(g|gioco.id=gameid), null altrimenti
	 * @return name nome del gioco
	 * @throws SQLException
	 */
	public String getGameName(int gameID) throws SQLException
	{
		GetGameName="SELECT DISTINCT nome FROM elemento_lista JOIN gioco ON gioco.id=elemento_lista.id_gioco WHERE id_gioco=?";
		String name=null;
		
		if(gameID<=0)
			throw new SQLException();
		else {
				try 
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(GetGameName);
					statement.setInt(1, gameID);
					set=statement.executeQuery();
					while(set.next())
					{
						name = set.getString(1);
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
				return name;
		}
	}
	

	/**
	 * 
	 * @param gameID id del gioco di cui cercare il nome
	 * @precondition gameID>0
	 * @postcondition score=database.gioco->select(g.punteggio|gioco.id=gameid) if database.gioco->includes(g|gioco.id=gameid), 0 altrimenti
	 * @return score punteggio del gioco
	 * @throws SQLException
	 */
	public int getGameScore(int gameID) throws SQLException
	{
		GetGameScore="SELECT DISTINCT punteggio FROM elemento_lista JOIN gioco ON gioco.id=elemento_lista.id_gioco WHERE id_gioco=?";
		int score=0;
		
		if(gameID<=0)
			throw new SQLException();
		else {
				try 
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(GetGameScore);
					statement.setInt(1, gameID);
					set=statement.executeQuery();
					while(set.next())
					{
						score = set.getInt(1);
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
				return score;
		}
	}
	
	/**
	 * 
	 * @param user username dell'utente a cui aggiornare il punteggio
	 * @param punteggio nuovo punteggio da assegnare all'utente
	 * @precondition punteggio>=0 AND user!=null AND database.utente->includes(select(u|utente.username=username)
	 * @postcondition database.utente-> includes(select(u|u.username=user and u.punteggio=punteggio))
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean updateUserScore(String user, int punteggio) throws SQLException 
	{
		boolean flag=false;
		updateScore = "update elemento_lista join utente on utente.username=elemento_lista.utente set punteggio=? where utente=?";
		
		if(user==null || punteggio<0)
			throw new SQLException();
		else {
				try
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(updateScore);
					statement.setInt(1,punteggio);
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
	 * @param gameID id del gioco da cercare
	 * @param categoria categoria da cercare per il gioco
	 * @postcondition count>=0
	 * @return count quantità di volte in ui un gioco è stato aggiunto a quella categoria
	 * @throws SQLException
	 */
	public int getCategoryStats(int gameID, String categoria) throws SQLException
	{
		GetStats="SELECT count(*) FROM elemento_lista WHERE id_gioco=? and categoria=?";
		int count=-1;
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(GetStats);
			statement.setInt(1, gameID);
			statement.setString(2, categoria);
			set=statement.executeQuery();
			while(set.next())
			{
				count = set.getInt(1);
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
		return count;
	}
}
