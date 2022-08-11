package gameManager;

import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Questa classe è un oggetto manager che si interfaccia con il database. Gestisce le query riguardanti l’oggetto Gioco.
 *
 */
public class GiocoDAO {
	
	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String viewGame;
	private static String addGame;
	private static String getPublishers;
	private static String getGenres;
	private static String updateAverage;

	

	/**
	 *
	 * @postcondition giochi->database.gioco->select(g)->asSet() AND giochi->size()>=0
	 * @return giochi lista completa di tutti i giochi salvati nel database
	 * @throws SQLException
	 */
	public ArrayList<Gioco> findAllGames() throws SQLException
	{
		viewGame="SELECT * FROM gioco";
		ArrayList<Gioco> giochi=new ArrayList<Gioco>();
		
		try 
		{
			con= ConnectionPool.getConnection();
			statement=con.prepareStatement(viewGame);
			set=statement.executeQuery();
			while(set.next())
			{
				Gioco g=new Gioco();
				g.setIdGioco(set.getInt(1));
				g.setNome(set.getString(2));
				g.setDescrizione(set.getString(3));
				g.setPublisher(set.getString(4));
				g.setAnno(set.getString(5));
				g.setGenere(set.getString(6));
				g.setImgpath(set.getString(7));
				g.setPunteggio(set.getInt(8));
				g.setMediaVoti(set.getFloat(9));
				giochi.add(g);
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
		return giochi;
	}
	
	/**
	 *
	 * @postcondition giochi->asOrderedSet()->size()>=0 AND giochi->size()<=11 AND giochi->sortedBy(g|g.media_voti)->reverse()
	 * @return giochi classifica degli 11 giochi con la media più alta
	 * @throws SQLException
	 */
	public ArrayList<Gioco> getTopRated() throws SQLException
	{
		viewGame="SELECT * FROM gioco ORDER BY media_voti DESC LIMIT 11";
		ArrayList<Gioco> giochi=new ArrayList<Gioco>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewGame);
			set=statement.executeQuery();
			while(set.next())
			{
				Gioco vidya=new Gioco();
				vidya.setIdGioco(set.getInt(1));
				vidya.setNome(set.getString(2));
				vidya.setDescrizione(set.getString(3));
				vidya.setPublisher(set.getString(4));
				vidya.setAnno(set.getString(5));
				vidya.setGenere(set.getString(6));
				vidya.setImgpath(set.getString(7));
				vidya.setPunteggio(set.getInt(8));
				vidya.setMediaVoti(set.getFloat(9));
				giochi.add(vidya);
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
		return giochi;
	}
	
	/**
	 *
	 * @param id id del gioco da trovare
	 * @precondition id>0
	 * @postcondition gioco->select(g|g.id=id)
	 * @return giochi.get(0) primo e unico valore dalla lista restituita, null se essa è vuota
	 * @throws SQLException
	 */
	public Gioco findGameById(int id) throws SQLException
	{
		viewGame="SELECT * FROM gioco WHERE id=?";
		ArrayList<Gioco> giochi=new ArrayList<Gioco>();
		
		if(id<=0)
			throw new SQLException();
		else {
				try 
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(viewGame); 
					statement.setInt(1, id);
					set=statement.executeQuery();
					while(set.next())
					{
						Gioco g=new Gioco();
						g.setIdGioco(set.getInt(1));
						g.setNome(set.getString(2));
						g.setDescrizione(set.getString(3));
						g.setPublisher(set.getString(4));
						g.setAnno(set.getString(5));
						g.setGenere(set.getString(6));
						g.setImgpath(set.getString(7));
						g.setPunteggio(set.getInt(8));
						g.setMediaVoti(set.getFloat(9));
						giochi.add(g);
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
				if(giochi.size()==0) {
					return null;
				}
				return giochi.get(0);
		}
	}
	
	/**
	 *
	 * @param publisher nome dell'editore che ha pubblicato il gioco
	 * @postcondition giochi->select(g|g.publisher=publisher) AND giochi->size>=0
	 * @return giochi lista dei giochi restituiti dalla query
	 * @throws SQLException
	 */
	public ArrayList<Gioco> findByPublisher(String publisher) throws SQLException
	{
		viewGame="SELECT * FROM gioco WHERE publisher=?";
		ArrayList<Gioco> giochi=new ArrayList<Gioco>();
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewGame); 
			statement.setString(1, publisher);
			set=statement.executeQuery();
			while(set.next())
			{
				Gioco g=new Gioco();
				g.setIdGioco(set.getInt(1));
				g.setNome(set.getString(2));
				g.setDescrizione(set.getString(3));
				g.setPublisher(set.getString(4));
				g.setAnno(set.getString(5));
				g.setGenere(set.getString(6));
				g.setImgpath(set.getString(7));
				g.setPunteggio(set.getInt(8));
				g.setMediaVoti(set.getFloat(9));
				giochi.add(g);
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
		return giochi;
	}
	
	
	/**
	 *
	 * @param genre genere del gioco
	 * @postcondition giochi->select(g|g.genere=genre) AND giochi->size>=0
	 * @return giochi lista dei giochi restituiti dalla query
	 * @throws SQLException
	 */
	public ArrayList<Gioco> findByGenre(String genre) throws SQLException
	{
		viewGame="SELECT * FROM gioco WHERE genere=?";
		ArrayList<Gioco> giochi=new ArrayList<Gioco>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewGame); 
			statement.setString(1, genre);
			set=statement.executeQuery();
			while(set.next())
			{
				Gioco vidya=new Gioco();
				vidya.setIdGioco(set.getInt(1));
				vidya.setNome(set.getString(2));
				vidya.setDescrizione(set.getString(3));
				vidya.setPublisher(set.getString(4));
				vidya.setAnno(set.getString(5));
				vidya.setGenere(set.getString(6));
				vidya.setImgpath(set.getString(7));
				vidya.setPunteggio(set.getInt(8));
				vidya.setMediaVoti(set.getFloat(9));
				giochi.add(vidya);
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
		return giochi;
	}
	
	/**
	 *
	 * @param nome nome del gioco
	 * @param publisher editore che ha pubblicato il gioco
	 * @param anno anno di pubblicazione del gioco
	 * @precondition nome!=null, publisher!=null, anno!=null
	 * @postcondition true if database.gioco->includes(select(g|gioco.nome=nome and gioco.publisher=publisher and gioco.anno=anno), false altrimenti
	 * @return boolean che mostra l'esito della ricerca
	 * @throws SQLException
	 */
	public boolean checkExistingGame(String nome, String publisher, String anno) throws SQLException
	{
		viewGame="SELECT * FROM gioco WHERE nome=? AND publisher=? AND anno=?";
		ArrayList<Gioco> giochi=new ArrayList<Gioco>();
		if(nome==null||publisher==null||anno==null)
			throw new SQLException();
		else {
				try 
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(viewGame); 
					statement.setString(1, nome);
					statement.setString(2, publisher);
					statement.setString(3, anno);
					set=statement.executeQuery();
					while(set.next())
					{
						Gioco vidya=new Gioco();
						vidya.setIdGioco(set.getInt(1));
						vidya.setNome(set.getString(2));
						vidya.setDescrizione(set.getString(3));
						vidya.setPublisher(set.getString(4));
						vidya.setAnno(set.getString(5));
						vidya.setGenere(set.getString(6));
						vidya.setImgpath(set.getString(7));
						vidya.setPunteggio(set.getInt(8));
						vidya.setMediaVoti(set.getFloat(9));
						giochi.add(vidya);
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
				if(giochi.size()==0) {
					return false;
				}
				return true;
		}
	}
	
	/**
	 *
	 * @postcondition results->size()>=0
	 * @return results lista dei publisher restituiti dalla query
	 * @throws SQLException
	 */
	public ArrayList<String> GetPublishers() throws SQLException {
		getPublishers = "SELECT DISTINCT publisher FROM gioco ORDER BY publisher;";
		ArrayList <String> results = new ArrayList<String>();
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getPublishers);
			set=statement.executeQuery();
			while(set.next())
			{
				String pub =set.getString(1);
				results.add(pub);
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
	 * @postcondition results->size()>=0
	 * @return results lista dei generi restituiti dalla query
	 * @throws SQLException
	 */
	public ArrayList<String> GetGenres() throws SQLException {
		getGenres = "SELECT DISTINCT genere FROM gioco ORDER BY genere;";
		ArrayList <String> results = new ArrayList<String>();
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getGenres);
			set=statement.executeQuery();
			while(set.next())
			{
				String pub =set.getString(1);
				results.add(pub);
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
	 * @param game gioco da aggiungere
	 * @precondition game!=null
	 * @postcondition database.gioco->includes(select(g|gioco.nome=game.getNome() and gioco.publisher=fame.getPublisher() and gioco.anno=game.getAnno())
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean addGame(Gioco game) throws SQLException 
	{
		addGame= "INSERT INTO gioco(nome,descrizione,publisher,anno,genere,imgpath, punteggio) values(?,?,?,?,?,?,?)";
		boolean flag=false;
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(addGame);
			statement.setString(1,game.getNome());
			statement.setString(2,game.getDescrizione());
			statement.setString(3,game.getPublisher());
			statement.setString(4,game.getAnno());
			statement.setString(5,game.getGenere());
			statement.setString(6,game.getImgpath());
			statement.setInt(7,game.getPunteggio());
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

	/**
	 * 
	 * @param gameID id del gioco a cui aggiornare la media
	 * @param media nuovo valore da aggiornare alla media
	 * @precondition database.gioco->includes(select(g|gioco.id=gameID) AND id>0 media>=0
	 * @postcondition true if database.gioco->includes(select(g|gioco.id=gameID and gioco.media_voti=media), false altrimenti
	 * @return flag booleano per stabilire l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean updateAverage(int gameID, float media) throws SQLException 
	{
		boolean flag=false;
		updateAverage = "UPDATE gioco SET media_voti=? WHERE id=?";
		try
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(updateAverage);
			statement.setFloat(1,media);
			statement.setInt(2,gameID);
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
