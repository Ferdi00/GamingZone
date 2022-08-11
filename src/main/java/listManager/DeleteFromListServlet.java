package listManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa classe e un control che si occupa di passare a ElementoListaDAO i dati di un Elemento Lista da rimuovere.
 */
@WebServlet("/DeleteFromList")
public class DeleteFromListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ElementoListaDAO model = new ElementoListaDAO();
    
    public DeleteFromListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
   	 * @precondition request.getParameter(username)!=null AND request.getParameter(game_id)!=null AND request.getParameter(category_value)!=null AND request.getParameter(user_category)!=null AND request.getParameter(score)!=null AND request.getParameter(usrScore)!=null
   	 * @postcondition ElementoListaDAO:deleteListELement(gameid, user)==true ElementoListaDAO:updateUserScore(user, punteggio)==true
   	 * @throws ServletException, IOException
   	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null; 
		String gameID = null; 
		String user_category= null;
		int score = 0;
		int usrScore = 0;
		username = request.getParameter("username");
		gameID = request.getParameter("game_id");
		user_category=request.getParameter("user_category");
		score=Integer.parseInt(request.getParameter("score"));
		usrScore=Integer.parseInt(request.getParameter("usrScore"));
		System.out.println(username + " vuole togliere " +gameID+" punteggio "+score+" partendo da un punteggio di "+usrScore+" e dalla categoria "+user_category);//test
		
		int oldScore = ScoreHelper.calcolaPunteggio(score, user_category);
		//System.out.println("il vecchio punteggio : "+ score);
		usrScore-=oldScore;
		//System.out.println("l'utente ora avr: "+ usrScore);
		//score =calcolaPunteggio(score, category_value);
		//System.out.println("il nuovo punteggio  "+ score);
		
		usrScore+=0;
		System.out.println("l'utente ora avr "+ usrScore);
		
		try {
			if(model.getListElement(username, Integer.parseInt(gameID))!=null){
				if(ElementoListaDAO.updateUserScore(username, usrScore)) {
					if(ElementoListaDAO.deleteListElement(Integer.parseInt(gameID), username))
						response.getWriter().write("Eliminato, nuovo punteggio = "+usrScore);
					}
				}
			else {
				response.getWriter().write("Errore");
				response.setStatus(500);
			}
		} catch (SQLException e) {
			response.getWriter().write("Errore SQL");
			e.printStackTrace();
		}
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
