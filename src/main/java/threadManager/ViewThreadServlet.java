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
 * Questa classe è un control che si occupa della visualizzazione di un thread in base alle interazioni di un utente.
 */
@WebServlet("/ViewThread")
public class ViewThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ViewThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    static ThreadDAO threadModel = new ThreadDAO();
    
    /**
  	 * @precondition request.getParamter(“threadid”)!=null
  	 * @postcondition dispatcher!=null
  	 * @throws ServletException, IOException
  	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

				request.removeAttribute("thread");
				String threadID = request.getParameter("threadid");
				RequestDispatcher dispatcher;
				GameThread app=threadModel.viewThreadById(Integer.parseInt(threadID));
				if(app!=null) {
					request.setAttribute("thread", app);
					response.getWriter().write(app.getTitolo());//test
					dispatcher = request.getRequestDispatcher("/pagina-thread.jsp");
					dispatcher.forward(request, response);
				}
				else {
					response.getWriter().write("errore");//test
					dispatcher = request.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
				
						
			} catch (SQLException e) {
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
