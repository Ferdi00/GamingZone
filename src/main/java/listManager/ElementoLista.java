package listManager;

/**
 * Questa classe rappresenta l'entita Elemento_Lista di un particolare utente per uno specifico gioco.
 */
public class ElementoLista {

	private String usernameUtente;
	private int idGioco;
	private String categoria;
	
	public ElementoLista() {
		
	}
	
	/**
	 * @param user username dell'utente di riferimento
	 * @param idGame id del gioco di riferimento
	 * @param category categoria per quella specifica aggiunta nella lista

	 */
	public ElementoLista(String user, int idGame, String category) {
		this.usernameUtente = user;
		this.idGioco = idGame;
		this.categoria = category;
	}

	/**
	 * @return usernameUtente username dell'utente che ha aggiunto l'elemento alla sua lista
	 */
	public String getUsernameUtente() {
		return usernameUtente;
	}

	/**
	 * @param usernameUtente username dell'utente da assegnare all'elemento

	 */
	public void setUsernameUtente(String usernameUtente) {
		this.usernameUtente = usernameUtente;
	}

	/**
	 * @return idGioco id del gioco aggiunto
	 */
	public int getIdGioco() {
		return idGioco;
	}

	/**
	 * @param idGioco id del gioco da assegnare all'elemento

	 */
	public void setIdGioco(int idGioco) {
		this.idGioco = idGioco;
	}

	/**
	 * @return categoria categoria a cui e stato assegnato l'elemento
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria categoria da assegnare all'elemento

	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
