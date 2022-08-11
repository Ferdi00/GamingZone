<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"
    %>
    
    
    <%
	String action_name = (String) request.getAttribute("action_name");
	String ricerca = (String) session.getAttribute("ricerca");
	ArrayList<?> array = (ArrayList<?>) session.getAttribute("giochi");
	GiocoDAO GameModel=new GiocoDAO();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>GamingZone - Giochi</title>
		<link rel="stylesheet" type="text/css" href="css/catalogo.css">
	</head>
<body style="margin:0">

<!-- Header section start -->
	<div class="header">
		<%@ include file="fragment/header.jsp" %>
	</div> 
<!-- Header section end -->

<section class="catalogo-pagina">
	<div class="container">
		<%  	if(action_name!=null) {%>
		<p><%=action_name%></p>
			<% 	} else {%>
			<p><%=ricerca%></p>
		<% 	}%>
	</div>
		
		
	<nav>
		<ul>
			<li><a href="#">Ricerca per Publisher</a>
	    	<ul>
    			<%try{
					 ArrayList<String> list = GameModel.GetPublishers();
						for(String x:list){ %>
				<li><a href="Publisher?pub=<%=x%>" ><%=x%></a></li>
      			<% }
					} catch (Exception e) {
						System.out.println(e.getMessage());
									}  											    													    
				%>
			</ul>
			</li>
    		 
			<li><a href="#">Ricerca per Genere</a>
	    	<ul>
	    		<%try{
					 ArrayList<String> list = GameModel.GetGenres();
						for(String x:list){ %>
				<li><a href="Genre?gen=<%=x%>" ><%=x%></a></li>
	      		<% }
					} catch (Exception e) {
						System.out.println(e.getMessage());
						}  											    													    
				%>							 			 
    		</ul>
    		</li>
    	</ul>
	</nav> 

		
		
		<div class="catalogo">		
			<%
				if(array!=null && array.size()!=0)
				{
					Iterator<?> it = array.iterator();
					while (it.hasNext()) 
					{
						Gioco bean = (Gioco) it.next();
			%>
					
					<div class="gioco">
						<div class="game-image">
							<a href="GamePage?id=<%=bean.getIdGioco()%>"><img src=<%=bean.getImgpath()+"/img1.png"%> width="150" height="150" class="imgItem"></a>
						</div>						
						<div class="game-name">
							<h5><a href="GamePage?id=<%=bean.getIdGioco()%>"><%=bean.getNome()%></a></h5>
						</div>
					</div>
					<% 	}
						} else { %>
						<div class="row justify-content-center" style="margin-left: 20px"><h4>Nessun gioco disponibile.</h4></div>
							<% if(utenteLoggato!=null) {
								if(utenteLoggato.getTipo().equals("user") || utenteLoggato.getTipo().equals("mod")){%>
						<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="nuova-richiesta-gioco.jsp">Richiedilo subito!</a></h4></div>
					<%		}else{ %>
						<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="#">Aggiungilo subito!</a></h4></div>
					<%		}
					} else{%>
						<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="login-page.jsp">Richiedilo subito!</a></h4></div>
					<% }
					}%>
			</div>
	</section>
	
	</body>
</html>