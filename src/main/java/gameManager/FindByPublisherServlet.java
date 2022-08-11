package gameManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Questa classe è un control che si occupa di interfacciarsi con GiocoDAO per ottenere i giochi pubblicati da un editore selezionato dall’utente.
 */
@WebServlet("/Publisher")
public class FindByPublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindByPublisherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    static GiocoDAO gameModel = new GiocoDAO();
    
    /**
 	 * @precondition request.getParameter("pub")!=null
 	 * @postcondition request.getAttribute("giochi")!=null AND dispatcher!=null
 	 * @throws ServletException, IOException
 	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
			request.removeAttribute("action_name");
			request.getSession().removeAttribute("giochi");	
			String publisher = request.getParameter("pub");
			ArrayList<Gioco> app=gameModel.findByPublisher(publisher);
			request.setAttribute("action_name", "Ricerca per publisher: "+publisher);
			request.getSession().setAttribute("giochi", app);		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	
	
	RequestDispatcher dispatcher;
	dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
	
	dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
