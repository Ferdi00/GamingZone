package gameManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Questa classe è un control che si occupa di gestire la ricerca per parola chiave di un gioco.
 */
@WebServlet("/Cerca")
public class Ricerca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static GiocoDAO model_product = new GiocoDAO();
	
    public Ricerca() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
 	 * @postcondition request.getAttribute("result")!=null
 	 * @throws ServletException, IOException
 	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    	request.removeAttribute("result");
			String cerca = request.getParameter("cerca");
			String redirectedPage = "";
			
			try {
				
				ArrayList<Gioco> giochi = new ArrayList<Gioco>();
				ArrayList<Gioco> risultato = new ArrayList<Gioco>();
				
				request.removeAttribute("giochi");
				
				giochi = model_product.findAllGames(); //mi prendo tutti i giochi
				int I = 0;					
				String parola="";
				int indiceScorrimentoParolaFine = 1;
				boolean trovato = false;
				
				while (I<giochi.size())
				{
					for(int indiceScorrimentoParolaInizio = 0  ;indiceScorrimentoParolaInizio <= giochi.get(I).getNome().length()-1 && !trovato ; indiceScorrimentoParolaInizio++)
					{
						for( indiceScorrimentoParolaFine = indiceScorrimentoParolaInizio +1  ;indiceScorrimentoParolaFine <= giochi.get(I).getNome().length() && !trovato; indiceScorrimentoParolaFine++)
						{
							parola= giochi.get(I).getNome().substring(indiceScorrimentoParolaInizio, indiceScorrimentoParolaFine);
								if(parola.equalsIgnoreCase(cerca))
								{
									risultato.add(giochi.get(I));
									trovato=true;
								}
						}
						
					}				
					I++;
					trovato=false;
				}
				
				String ricerca = ("Hai cercato: "+cerca);

				if(risultato.size()==0) {
					request.setAttribute("result", "noResults");
				} else {
					request.setAttribute("result", "found");
					request.setAttribute("resultSize", risultato.size());
				}
				
				request.getSession().setAttribute("ricerca", ricerca);
				request.getSession().setAttribute("giochi", risultato);	
				redirectedPage = "/catalogo.jsp";
				
				} catch (Exception e) {
					request.setAttribute("result", "error");
					redirectedPage = "/catalogo.jsp";
					e.printStackTrace();
				}
			
		response.sendRedirect(request.getContextPath() + redirectedPage);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
