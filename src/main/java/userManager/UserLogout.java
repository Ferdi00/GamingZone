package userManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Questa classe è un control che si occupa di gestire l’operazione di logout. 
 * 
 */
@WebServlet("/Logout")
public class UserLogout extends HttpServlet
{
	private static final long serialVersionUID = 1042434985115718411L;

	/**
	 * @precondition Request.getSession(false).getAttribute(“utenteLoggato”)!=null AND Request.getSession(false).getAttribute(“utente”)!=null
	 * @postcondition request.getSession().getAttribute(“utenteLoggato”)==null AND Request.getSession(false).getAttribute(“utente”)==null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
			request.getSession(false).removeAttribute("utenteLoggato");
			request.getSession(false).removeAttribute("utente");
			request.getSession(false).invalidate();
			response.getWriter().write("Logout effettuato");
			response.sendRedirect(request.getContextPath() + "/logout.jsp");
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}