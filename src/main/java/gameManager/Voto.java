package gameManager;

/**
 * Questa classe rappresenta l'entit� Voto (valutazione di un utente a uno specifico gioco).

 */
public class Voto {

	private int valutazione;
	private String usernameUtente;
	private int idGioco;
	
	
	/**
	 * @param valutazione valore numerico del voto
	 * @param utente username dell'utente che ha votato
	 * @param idGioco id del gioco che � stato votato

	 */
	public Voto(int valutazione, String utente, int idGioco) {
		this.valutazione = valutazione;
		this.usernameUtente = utente;
		this.idGioco = idGioco;
	}
	public Voto() {
	
	}

	/**
	 * @return valutazione valore numerico del voto
	 */
	public int getValutazione() {
		return valutazione;
	}

	/**
	 * @param valutazione valore numerico da assegnare al voto

	 */
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	/**
	 * @return usernameUtente username dell'utente che ha votato
	 */
	public String getUsernameUtente() {
		return usernameUtente;
	}

	/**
	 * @param usernameUtente username dell'utente da assegnare al voto

	 */
	public void setUsernameUtente(String usernameUtente) {
		this.usernameUtente = usernameUtente;
	}

	/**
	 * @return idGioco id del gioco che � stato votato
	 */
	public int getIdGioco() {
		return idGioco;
	}

	/**
	 * @param idGioco id del gioco da assegnare al voto

	 */
	public void setIdGioco(int idGioco) {
		this.idGioco = idGioco;
	}
	
}
