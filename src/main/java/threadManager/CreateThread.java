package threadManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Questa classe e un control che si occupa di passare a ThreadDAO i dati di un thread da pubblicare.
 */
@WebServlet("/CreateThread")
public class CreateThread extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CreateThread() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @precondition request.getParameter(thread_title)!=null AND request.getParameter(thread_type)!=null AND request.getParameter(thread_text)!=null AND request.getParameter(gameId)!=null AND request.getParameter(username)!=null
	 * @postcondition request.getAttribute("inserimentoThread")!=null
	 * @throws ServletException, IOException
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	request.removeAttribute("inserimentoThread");
		String title = null;
		String type = null;
		String text = null;
		String username = null;
		String gameID = null;
		
		title = request.getParameter("thread_title");
		type = request.getParameter("thread_type");
		text = request.getParameter("thread_text");
		gameID = request.getParameter("gameId");
		username = request.getParameter("username");
		//response.getWriter().write(title+" "+type+" "+text+" "+gameID+" "+username);//test
		System.out.println(title+" "+type+" "+text+" "+gameID+" "+username);//test
		//creazione del nuovo thread
		GameThread disc = new GameThread();
		
	
		disc.setTitolo(title);
		disc.setTipoThread(type);
		disc.setTesto(text);
		disc.setIdGioco(Integer.parseInt(gameID));
		disc.setUsernameUtente(username);
		
	
		
		try {
			
				if(ThreadDAO.addThread(disc))
				{
					request.setAttribute("inserimentoThread", "true");
					response.getWriter().write(title+" "+type+" "+text+" "+gameID+" "+username);//test
					RequestDispatcher dispatcher = request.getRequestDispatcher("/GamePage?id="+disc.getIdGioco());
					dispatcher.forward(request, response);
					
				} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("inserimentoThread", "false");
			e.printStackTrace();
		}
		
	}

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		doPost(request, response);
	}

}
