package userManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Questa classe è un control che si occupa di gestire l’operazione di login. 
 * Passerà a UtenteDAO le credenziali immesse in input per verificarne la correttezza ed effettuare il login di conseguenza.
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UtenteDAO model_utente=new UtenteDAO();
    
    public UserLogin() {
        super();
    }

    /**
	 * @precondition Request.getParamter(“user_email”)!=null AND Request.getParameter(“user_password”)!=null 
	 * @postcondition request.getAttribute(“loginResult”)!=null
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.removeAttribute("login");
		request.removeAttribute("loginResult");
		
		String user_email = request.getParameter("user_email");
		String user_password = request.getParameter("user_password");
		
		String redirectedPage = "";
		
		HttpSession session = request.getSession(true);
		synchronized (session) {
		
			try 
			{
				Utente utenteLoggato = model_utente.checkLogin(user_email, user_password);
				boolean correctEmail = model_utente.checkEmail(user_email);
				boolean correctPass = model_utente.checkPassword(user_email, user_password);
				
				if(correctEmail && correctPass) {
					session.setAttribute("utenteLoggato", utenteLoggato);
					session.setAttribute("utente", utenteLoggato.getUsername());
					request.setAttribute("loginResult", "logged");
					redirectedPage = "/index.jsp";
				}
				else if (!correctEmail)
				{
					request.setAttribute("correctEmail", false);
					request.setAttribute("loginResult", "incorrectEmail");
					redirectedPage = "/login-page.jsp";
				}
				else if (!correctPass)
				{
					request.setAttribute("correctPassword", false);
					request.setAttribute("loginResult", "incorrectPass");
					redirectedPage = "/login-page.jsp";
				}
				/*else
				{
					request.setAttribute("correctEmail", false);
					request.setAttribute("correctPassword", false);
					request.setAttribute("result", "wrongLogin");
					redirectedPage = "/login-page.jsp";
				}*/
			}
				 catch (Exception e) {
					 System.out.println("Servlet error");
					 request.setAttribute("loginResult", "wrongLogin");
				e.printStackTrace();
				}
			}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


}
