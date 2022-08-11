package threadManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/CreateThreadReq")
public class CreateThreadReq extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateThreadReq() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.removeAttribute("richiestathread");

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


        RichiestaThread req = new RichiestaThread();


        req.setId_gioco(Integer.parseInt(gameID));
        req.setTitolo(title);
        req.setTipo(type);
        req.setTesto(text);
        req.setUtente(username);


        try {
            RichiestaThreadDAO rtd= new RichiestaThreadDAO();
            if(rtd.addThreadRequest(req))
            {
                request.setAttribute("richiestathread", "true");


            }
            else
            {
                request.setAttribute("richiestathread", "false");
                RequestDispatcher dispatcher = request.getRequestDispatcher("nuova-discussione.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException e) {
            request.setAttribute("richiestathread", "errorSQL");
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