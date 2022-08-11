package gameManager;

import threadManager.GameThread;
import threadManager.RichiestaThread;
import threadManager.RichiestaThreadDAO;
import threadManager.ThreadDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa classe è un control che si occupa di gestire l’accettazione di una richiesta per un nuovo thread, passa l’id della richiesta a RichiestaThreadDAO, che ne ricava i dati, passandoli a ThreadDAO per aggiungere la discussione, e successivamente elimina la richiesta.
 */
@WebServlet("/AcceptReq")
public class AcceptRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AcceptRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    static RichiestaThreadDAO model = new RichiestaThreadDAO();

    /**
 	 * @pre request.getParameter(“reqid”)!=null
 	 * @post request.getAttribute("result")!=null
 	 * @throws ServletException, IOException
 	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.removeAttribute("result");
		
		try {
		String reqID = request.getParameter("reqid");
		if(model.viewRequestById(Integer.parseInt(reqID))==null) {
			request.setAttribute("result", "fail");
			response.getWriter().write("Richiesta inesistente");
		} else {
			System.out.println("ricevuta richiesta aggiunta id: "+reqID);
			RichiestaThread app;
			app = model.viewRequestById(Integer.parseInt(reqID));
			GameThread t= new GameThread();
			t.setIdGioco(app.getId_gioco());
			t.setTitolo(app.getTitolo());
			t.setTesto(app.getTesto());
			t.setTipoThread(app.getTipo());
			t.setUsernameUtente(app.getUtente());

				if(ThreadDAO.addThread(t)) {
					model.deleteThreadRequest(app.getId_richiesta());
					request.setAttribute("result", "success");
				}
		}
		
			} catch (SQLException e) {
			// TODO Auto-generated catch block
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
