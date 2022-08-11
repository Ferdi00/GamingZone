package gameManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Questa classe e un control che si occupa di prelevare l’intero catalogo da GiocoDAO.
 */
@WebServlet("/FindAll")
public class FindAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FindAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    static GiocoDAO gameModel = new GiocoDAO();
    
    /**
 	 *
 	 * @postcondition request.getAttribute("giochi")!=null
 	 * @throws ServletException, IOException
 	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
			
				request.getSession().removeAttribute("giochi");	
				request.removeAttribute("action_name");
				ArrayList<Gioco> app=gameModel.findAllGames();
				request.setAttribute("action_name", "Tutti i giochi");
				request.getSession().setAttribute("giochi", app);	
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	
	
	request.getRequestDispatcher("catalogo.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
