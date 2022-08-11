package userManager;

import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UtenteDAO {

	private static Connection con;
	private static PreparedStatement statement;
	private static ResultSet set;
	private static String addUtente;
	private static String checkEmail;
	private static String checkPassword;
	private static String showAccount;
	private static String showTopUsers;
	private static String checkLogin;

	static
	{
		addUtente="INSERT INTO utente(username,email,password,ddn) values(?,?,?,?)";
		checkEmail="SELECT username FROM utente where email=?";
		checkPassword="SELECT username FROM utente where email=? AND password=?";
		showAccount="SELECT * FROM utente where email=?";
		showTopUsers="SELECT * FROM utente WHERE tipo <> 'manager' ORDER BY punteggio DESC LIMIT 10";
		checkLogin="SELECT username,email,password,punteggio,tipo FROM utente where email=? AND password=?";
	}
	
	/**
	 * 
	 * @param Email indirizzo email dell'utente
	 * @param password password dell'utente
	 * @precondition Email!=null AND password!=null
	 * @postcondition utenteLoggato -> select(u|utente.email=email and utente.password=password)
	 * @return utenteLoggato->select(u|utente.email=Email and utente.password=password)
	 * @throws SQLException
	 */
	public Utente checkLogin(String Email,String password) throws SQLException {	
		Utente utenteLoggato = null;
		

		if (Email==null || password==null)
			throw new SQLException();
		else {
				try 
				{
					con= ConnectionPool.getConnection();
					statement=con.prepareStatement(checkLogin);
					statement.setString(1,Email);
					statement.setString(2,password);
					set=statement.executeQuery();
				} 		
				finally
				{
					try
					{
						while(set.next()) 
						{
							utenteLoggato = new Utente();
							utenteLoggato.setUsername(set.getString(1));
							utenteLoggato.setEmail(set.getString(2));
							utenteLoggato.setPassword(set.getString(3));
							utenteLoggato.setPunteggio(set.getInt(4));
							utenteLoggato.setTipo(set.getString(5));
		
						}
							
					}
					finally
					{
						ConnectionPool.rilasciaConnessione(con);
					}
				}
				return utenteLoggato;	
			}
	}
	
	
	/**
	 * 
	 * @param Email indirizzo email dell'utente
	 * @precondition Email!=null 
	 * @postcondition true if database.utente->includes (select(u|utente.email=Email)), null altrimenti
	 * @return flag booleano per stabilire l'esito della ricerca
	 * @throws SQLException
	 */
	public boolean checkEmail(String Email) throws SQLException {	
		boolean flag=false;

		if (Email==null)
			throw new SQLException();
		else {
				try 
				{
					con=ConnectionPool.getConnection();
					statement=con.prepareStatement(checkEmail);
					statement.setString(1,Email);
					set=statement.executeQuery();
					flag=set.next();
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
	 * @param email indirizzo email dell'utente
	 * @param Pass password dell'utente
	 * @precondition email!=null AND pass!=null
	 * @postcondition true if database.utente->includes (select(u|utente.email=email and utente.password=pass)), false altrimenti
	 * @return flag booleano per stabilire l'esito della ricerca
	 * @throws SQLException
	 */
	public boolean checkPassword(String email, String Pass) throws SQLException {	//check della password
		boolean flag=false;

		if (email==null || Pass==null)
			throw new SQLException();
		else {
			try 
			{
				con=ConnectionPool.getConnection();
				statement=con.prepareStatement(checkPassword);
				statement.setString(1,email);
				statement.setString(2,Pass);
				set=statement.executeQuery();
				flag=set.next();
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
	 * @param username username dell'utente
	 * @precondition username!=null
	 * @postcondition score = database.utente -> select(u.punteggio|utente.username=username) AND score>=0
	 * @return score punteggio dell'utente cercato
	 * @throws SQLException
	 */
	public int getScore(String username) throws SQLException
	{
		
		String getScore="SELECT punteggio FROM utente WHERE username=?";
		int score= -1;
		
		if (username==null)
			throw new SQLException();
		else {
			try 
			{
				con=ConnectionPool.getConnection();
				statement=con.prepareStatement(getScore);
				statement.setString(1, username);
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
	 * @postcondition utenti->asOrderedSet ->size()<=10 AND utenti-> sortedBy(u|u.punteggio)->reverse()
	 * @return utenti lista ordinata dei 10 utenti con i migliori punteggi nel sito
	 * @throws SQLException
	 */
	public ArrayList<Utente> findTopUsers() throws SQLException
	{
		ArrayList<Utente> utenti=new ArrayList<Utente>();
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(showTopUsers);
			set=statement.executeQuery();
			while(set.next())
			{
				Utente user=new Utente();
				user.setUsername(set.getString(1));
				user.setEmail(set.getString(2));
				user.setPassword(set.getString(3));
				user.setPunteggio(set.getInt(4));
				user.setTipo(set.getString(5));
				utenti.add(user);
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
		return utenti;
	}

	public void AddUser(Utente u) throws SQLException {
		con= ConnectionPool.getConnection();
		statement=con.prepareStatement(addUtente);
		statement.setString(1,u.getUsername());
		statement.setString(2,u.getEmail());
		statement.setString(3,u.getPassword());
		statement.setString(4,u.getBdate());
		set=statement.executeQuery();
	}
}

