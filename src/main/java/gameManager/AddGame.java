package gameManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Questa classe è un control che si occupa di passare a GiocoDAO i dati di un gioco da aggiungere.
 */
@WebServlet("/AddGame")
public class AddGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AddGame() {
        super();
        // TODO Auto-generated constructor stub
    }

    static GiocoDAO gameModel = new GiocoDAO();
    
    /**
 	 * @precondition request.getParameter(“game_title”)!=null AND request.getParameter(“publisher”)!=null AND request.getParameter(“game_genre”)!=null AND request.getParameter(game_year)!=null AND request.getParameter(“game_desc”)!=null AND request.getParameter(“score”)!null
 	 * @postcondition request.getAttribute("aggiuntaGioco")!=null
 	 * @throws ServletException, IOException
 	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	request.removeAttribute("aggiuntaGioco");
		String name;
		String publisher;
		String genere ;
		String anno ;
		String descrizione ;
		String punteggio ;
		
		name = request.getParameter("game_title");
		publisher = request.getParameter("publisher");
		genere = request.getParameter("game_genre");
		anno = request.getParameter("game_year");
		descrizione = request.getParameter("game_desc");
		punteggio = request.getParameter("score");

		
		try {
			
			if(gameModel.checkExistingGame(name, publisher, anno)==false) {
				
				//creazione del nuovo oggetto gioco
				
				Gioco gioco = new Gioco();
				gioco.setNome(name);
				gioco.setPublisher(publisher);
				gioco.setGenere(genere);
				gioco.setAnno(anno);
				gioco.setDescrizione(descrizione);
				gioco.setPunteggio(Integer.parseInt(punteggio));
				gioco.setMediaVoti(0);
				gioco.setImgpath("img/Games/placeholder");
				
				try {
					
						GiocoDAO.addGame(gioco);
						request.setAttribute("aggiuntaGioco", "success");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/FindAll");
						dispatcher.forward(request, response);
							
					
				} catch (SQLException e) {
					request.setAttribute("aggiuntaGioco", "errorSQL");
					response.setStatus(500);
					e.printStackTrace();
				}
			} else {
				request.setAttribute("aggiuntaGioco", "existing");
				response.setStatus(500);
			}
	} catch (SQLException e1) {
			request.setAttribute("aggiuntaGioco", "error");
			response.setStatus(500);
			e1.printStackTrace();
}
		
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		doPost(request, response);
	}


}
