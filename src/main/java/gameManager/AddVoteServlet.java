package gameManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa classe  un control che si occupa di passare a VotoDAO i dati di un voto da registrare.
 */
@WebServlet("/AddVote")
public class AddVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	static GiocoDAO gameModel = new GiocoDAO();
	static VotoDAO voteModel = new VotoDAO();
	
    public AddVoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
 	 * @precondition request.getParameter(username)!=null AND request.getParameter(game_id)!=null AND request.getParameter(vote_value)!=null
 	 * @postcondition request.getAttribute("result")!=null
 	 * @throws ServletException, IOException
 	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null; 
		String gameID = null; 
		String vote_value = null; 
		float media = 0;
		username = request.getParameter("username");
		gameID = request.getParameter("game_id");
		vote_value = request.getParameter("vote_value");
		System.out.println(username + " ha votato " +gameID+" con "+vote_value);//test
		try {
			Voto app = voteModel.getVote(Integer.parseInt(gameID), username);
			if(app!=null) {
				VotoDAO.updateVote(username, Integer.parseInt(gameID), Integer.parseInt(vote_value));
						System.out.println("Valutazione aggiornata");
				
			}
			else {
				System.out.println("Non c'e ancora questo voto."); //test
				Voto v  = new Voto();
				v.setIdGioco(Integer.parseInt(gameID));
				v.setUsernameUtente(username);
				v.setValutazione(Integer.parseInt(vote_value));
				VotoDAO.addVote(v);
					System.out.println("Valutazione aggiunta");
				}
			
			media = voteModel.calculateAverage(Integer.parseInt(gameID));
			System.out.println("Media =" +media);
			GiocoDAO.updateAverage(Integer.parseInt(gameID), media);
			request.setAttribute("newAverage", media);
			request.setAttribute("result", "success");
			
		} catch (SQLException e) {
			request.setAttribute("result", "fail");
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doPost(request, response);
	}

}
