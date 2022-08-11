package gameManager;

/**
 * Questa classe rappresenta l'entit� Gioco.
 */
public class Gioco {


	private int idGioco;
	private String nome;
	private String descrizione;
	private String publisher;
	private String anno;
	private String genere;
	private String imgpath;
	private int punteggio;
	private float mediaVoti;
	
	public Gioco() {
		
	}
	
	/**
	 * @param nome titolo del gioco
	 * @param descrizione descrizione del gioco
	 * @param publisher nome dell'editore che ha pubblicato il gioco
	 * @param anno anno di uscita del gioco
	 * @param genere genere del gioco

	 */
	public Gioco(String nome, String descrizione, String publisher, String anno, String genere) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.publisher = publisher;
		this.anno = anno;
		this.genere = genere;

	}
	
	/**
	 * @return idGioco id del gioco
	 */
	public int getIdGioco() {
		return idGioco;
	}
	
	/**
	 * @param idGioco id da assegnare al gioco

	 */
	public void setIdGioco(int idGioco) {
		this.idGioco = idGioco;
	}
	
	/**
	 * @return nome titolo del gioco
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome titolo da assegnare al gioco

	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return descrizione descrizione del gioco
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * @param descrizione descrizione da assegnare al gioco

	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * @return publisher editore del gioco
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * @param publisher editore da assegnare al gioco

	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * @return anno anno di uscita del gioco
	 */
	public String getAnno() {
		return anno;
	}
	
	/**
	 * @param anno anno di uscita da assegnare al gioco

	 */
	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	/**
	 * @return genere genere del gioco
	 */
	public String getGenere() {
		return genere;
	}
	
	/**
	 * @param genere tipologia da assegnare al gioco

	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	/**
	 * @return imgpath path del file dell'immagine del gioco
	 */
	public String getImgpath() {
		return imgpath;
	}
	
	/**
	 * @param imgpath path del file dell'immagine da assegnare al gioco

	 */
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
	/**
	 * @return punteggio punteggio del gioco
	 */
	public int getPunteggio() {
		return punteggio;
	}
	
	/**
	 * @param punteggio punteggio da assegnare al gioco

	 */
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	/**
	 * @return mediaVoti media delle valutazioni per il gioco
	 */
	public float getMediaVoti() {
		return mediaVoti;
	}
	
	/**
	 * @param mediaVoti media dei voti da assegnare al gioco
	 
	 */
	public void setMediaVoti(float mediaVoti) {
		this.mediaVoti = mediaVoti;
	}
	@Override
	public String toString() {
		return "Gioco [idGioco=" + idGioco + ", nome=" + nome + ", descrizione=" + descrizione + ", publisher="
				+ publisher + ", anno=" + anno + ", genere=" + genere + ", imgpath=" + imgpath + ", punteggio="
				+ punteggio + ", mediaVoti=" + mediaVoti + "]";
	}
	
	
	
}
