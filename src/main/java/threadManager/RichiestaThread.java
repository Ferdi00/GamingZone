package threadManager;

public class RichiestaThread {

    public RichiestaThread(){

    }

    public RichiestaThread(int id_gioco, String utente, String titolo,String tipo, String testo) {
      this.id_gioco =id_gioco;
      this.titolo=titolo;
      this.utente=utente;
      this.tipo=tipo;
      this.testo=testo;
    }

    public int getId_gioco() {
        return id_gioco;
    }

    public void setId_gioco(int id_gioco) {
        this.id_gioco = id_gioco;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getId_richiesta() {
        return id_richiesta;
    }

    public void setId_richiesta(int id_richiesta) {
        this.id_richiesta = id_richiesta;
    }

    private int id_richiesta;
    private int id_gioco;
    private String utente;
    private String titolo;
    private String tipo;
    private String testo;
}
