package threadManager;

import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ThreadDAO {

	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String viewThread;
	private static String addThread;

	/**
	 * 
	 * @param idThread id del Thread da cercare
	 * @postcondition thread->select(t|thread.id=id)
	 * @return thread il thread cercato per id
	 * @throws SQLException
	 */
	public GameThread viewThreadById(int idThread) throws SQLException
	{
		viewThread="SELECT * FROM thread WHERE id_thread=?";
		ArrayList<GameThread> threads=new ArrayList<GameThread>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewThread);
			statement.setInt(1, idThread);
			set=statement.executeQuery();
			while(set.next())
			{
				GameThread disc=new GameThread();
				disc.setIdThread(set.getInt(1));
				disc.setTipoThread(set.getString(2));
				disc.setTitolo(set.getString(3));
				disc.setTesto(set.getString(4));
				disc.setUsernameUtente(set.getString(5));
				disc.setIdGioco(set.getInt(6));
				threads.add(disc);
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
		if(threads.size()==0) {
			return null;
		}
		return threads.get(0);
	}
	
	
	/**
	 * 
	 * @param idGioco id del gioco su cui sono stati avviati i thread da cercare
	 * @postcondition threads->select(t|thread.id_gioco=idGioco) AND threads->size()>=0
	 * @return threads la lista dei thread avviati per un gioco
	 * @throws SQLException
	 */
	public ArrayList<GameThread> viewThreadByGame(int idGioco) throws SQLException
	{
		viewThread="SELECT * FROM thread WHERE id_gioco=?";
		ArrayList<GameThread> threads=new ArrayList<GameThread>();
		
		viewThread+=" ORDER BY id_thread DESC; ";
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewThread);
			statement.setInt(1, idGioco);
			set=statement.executeQuery();
			while(set.next())
			{
				GameThread disc=new GameThread();
				disc.setIdThread(set.getInt(1));
				disc.setTipoThread(set.getString(2));
				disc.setTitolo(set.getString(3));
				disc.setTesto(set.getString(4));
				disc.setUsernameUtente(set.getString(5));
				disc.setIdGioco(set.getInt(6));
				threads.add(disc);
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
		return threads;
	}
	
	/**
	 * 
	 * @param thread nuovo GameThread da creare su un determinato gioco
	 * @precondition thread!=null AND database.gioco->includes(select(g|gioco.id=thread.getIdGioco())) AND database.utente->includes (select(u|utente.username=thread.getUsernameUtente()))
	 * @postcondition database.thread->includes(thread)
	 * @return flag booleano che conferma l'esito dell'operazione
	 * @throws SQLException
	 */
	public static boolean addThread(GameThread thread) throws SQLException 
	{
		addThread= "INSERT INTO thread(tipo_thread,titolo,testo,utente,id_gioco) values(?,?,?,?,?)";
		boolean flag=false;

		if (thread==null)
			throw new SQLException();
		else {
			try 
			{
				con=ConnectionPool.getConnection();
				statement=con.prepareStatement(addThread);
				statement.setString(1,thread.getTipoThread());
				statement.setString(2,thread.getTitolo());
				statement.setString(3,thread.getTesto());
				statement.setString(4,thread.getUsernameUtente());
				statement.setInt(5,thread.getIdGioco());
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

	

}
