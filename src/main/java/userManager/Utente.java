package userManager;



public class Utente {

	private String tipo;
	private String username;
	private String email;
	private String password;
	private String Bdate;
	private int punteggio;
	
	public Utente () {}


	/**
	 *
	 * @param username
	 * @param email
	 * @param password
	 * @param Bdate
	 */
	public Utente(String username,String email, String password,String Bdate)
	{
		this.username = username;
		this.email = email;
		this.password = password;
		this.Bdate = Bdate;
		
	}

	public String getBdate() {
		return Bdate;
	}

	/**
	 *
	 * @param bdate
	 */

	public void setBdate(String bdate) {
		Bdate = bdate;
	}

	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo tipo da assegnare all'utente
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public String getEmail()
	{
		return email;
	}

	/**
	 *
	 * @param email
	 */

	public void setEmail(String email) 
	{
		this.email = email;
	}

	

	public String getUsername() 
	{
		return username;
	}

	/**
	 *
	 * @param username
	 */

	public void setUsername(String username) 
	{
		this.username = username;
	}


	public String getPassword() 
	{
		return password;
	}

	/**
	 *
	 * @param password
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}


	public int getPunteggio() 
	{
		return punteggio;
	}

	/**
	 *
	 * @param punteggio
	 */
	public void setPunteggio(int punteggio)
	{
		this.punteggio = punteggio;
	}

	@Override
	public String toString() 
	{
		return "email=" + email + ", username=" + username + ", tipo="+ tipo + ", punteggio=" + punteggio ;
	}
	/**
	 *
	 */

}
