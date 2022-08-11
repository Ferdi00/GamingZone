<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"%>
<%@ page import="threadManager.RichiestaThreadDAO" %>
<%@ page import="threadManager.RichiestaThread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
	    <link rel="stylesheet" type="text/css" href="css/pagina-richieste.css">
		<title>GamingZone - Pagina richieste thread</title>
		<!-- JQUERY 3.4.1 -->
		<script src="https://code.jquery.com/jquery-3.4.1.js"
			integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
			crossorigin="anonymous"></script>
		
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
	</head>
	<body style="margin:0">
	<!-- Header section start -->
		<div class="header">
			<%@ include file="fragment/header.jsp" %>
		</div> 
	<!-- Header section end -->
	
		<%
		
			if(utenteLoggato==null)
			{
				response.sendRedirect("login-page.jsp");
				return;
			}else if(!(utenteLoggato.getTipo().equals("mod")))
			{
				response.sendRedirect("index.jsp");
				return;
			}
		
		%>
	<div class="text-container">
		<h1> Nuove richieste per l'inserimento dei thread al sito</h1>
	</div>	
	
	<br>
		<div class="container">
			<div>
				<div>
					<%
					RichiestaThreadDAO model=new RichiestaThreadDAO();
					ArrayList<RichiestaThread> array = model.getAllRequests();
					
					if(array.size()!=0)
					{
							
						Iterator<?> it2 = array.iterator();
						while (it2.hasNext()) 
						{
							RichiestaThread bean = (RichiestaThread) it2.next();
					%>
					<div class="col-md-12" id="request">
						<h4>titolo:<%=bean.getTitolo()%><br>
							tipo:<%=bean.getTipo()%><br>
							testo:<%=bean.getTesto()%>
							<br>- richiesto da <%=bean.getUtente()%>. </h4>
						<button id="accept" class="acceptButton" value="<%=bean.getId_richiesta()%>">Accetta Richiesta</button>
						<button id="refuse" class="refuseButton" value="<%=bean.getId_richiesta()%>">Rifiuta Richiesta</button>
					</div>
					
					<% 	}
						} else { %>
							<div class="col-md-12"><h4>Nessuna richiesta al momento.</h4></div>
					<% } %>
				</div>
			</div>
		</div>
	
	</body>
	<script src="script/GestioneRichieste.js"></script>
</html>