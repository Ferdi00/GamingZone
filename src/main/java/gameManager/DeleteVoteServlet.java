package gameManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa classe è un control che si occupa di passare a VotoDAO i dati di un voto da rimuovere.
 */
@WebServlet("/DeleteVote")
public class DeleteVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static VotoDAO voteModel = new VotoDAO();
	
    public DeleteVoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
 	 * @precondition request.getParameter(“username”)!=null AND request.getParameter(“game_id”)!=null
 	 * @postcondition request.getAttribute("result")!=null
 	 * @throws ServletException, IOException
 	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameID = request.getParameter("game_id");
		String user = request.getParameter("username");
		System.out.println("ricevuta richiesta eliminazione voto gioco id: "+gameID);
		try {
			if(voteModel.getVote(Integer.parseInt(gameID), user) == null) { //eliminazione di un voto che non esiste
				request.setAttribute("result", "fail");
				response.setStatus(500);
			} else {
				
				if(VotoDAO.deleteVote(Integer.parseInt(gameID), user)) {
					System.out.println("Voto eliminato");
					request.setAttribute("result", "success");
					
				} else {
					response.setStatus(500);
				}
				
			}
			float media = voteModel.calculateAverage(Integer.parseInt(gameID));
			System.out.println("Media =" +media);
			GiocoDAO.updateAverage(Integer.parseInt(gameID), media);
			request.setAttribute("newAverage", media);
			
			
		} catch (NumberFormatException e) {
			request.setAttribute("result", "fail");
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("result", "fail");
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
