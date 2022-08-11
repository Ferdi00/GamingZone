<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"
    import="threadManager.*"%>
    
      <%	
	GameThread bean = (GameThread) request.getAttribute("thread");
	ThreadDAO threadModel = new ThreadDAO();
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>GamingZone - <%=bean.getTitolo()%></title>
	<link rel="stylesheet" type="text/css" href="css/pagina-thread.css">
</head>

<body style="margin:0">
	<header class="header">
		<%@ include file="fragment/header.jsp" %>
	</header>
	<section style="padding: 20px">
		<a href="GamePage?id=<%=bean.getIdGioco()%>"><b>--Torna alla pagina del gioco--</b></a>
		<br>


		<h2>- Discussione - <%=bean.getTipoThread()%></h2>

		<section class="th">
			<h1><%=bean.getTitolo()%></h1>
			<h5>Avviata da <%=bean.getUsernameUtente()%></h5>
			<p><%=bean.getTesto()%></p>
		</section>


		<br>

		<div class="container spacerDescrizioneProd">
			<div class="row">
				<div class="col-md-3">
					<h2 id="titlePPS">ALTRE DISCUSSIONI</h2>
				</div>
				<div class="descstyle col-md-9">
					<hr>
				</div>

				<div>
					<div>
						<%
							ArrayList<GameThread> array = threadModel.viewThreadByGame(bean.getIdGioco());


							if(array.size()!=1)
							{


								Iterator<?> it2 = array.iterator();
								while (it2.hasNext())
								{
									GameThread tbean = (GameThread) it2.next();
									if(tbean.getIdThread()!=bean.getIdThread()){
										if (tbean.getTipoThread().equals("Spoiler")){%>

						<div class="row justify-content-center">
							<a href="ViewThread?threadid=<%=tbean.getIdThread()%>"> <b>La discussione è marcata come SPOILER</b>: clicca per visualizzarla! - scritta da <%=tbean.getUsernameUtente()%> </a>
						</div>
					</div>
					<br>

					<%}
					else{
					%>



					<div class="row justify-content-center">
						<a href="ViewThread?threadid=<%=tbean.getIdThread()%>"> <%=tbean.getTipoThread()%> - <b><%=tbean.getTitolo()%></b> - scritta da <%=tbean.getUsernameUtente()%> </a>
					</div>
				</div>
				<br>


				<% 	}

				}
				}
				} else { %>
				<div class="col-md-12"><h4>Nessun'altra Discussione.</h4>
					<% if(utenteLoggato != null)
					{%>
					<a href="nuova-discussione.jsp?gameid=<%=bean.getIdGioco()%>">Creane una!</a></

				<%	}
				else
				{%>
				<a href="login-page.jsp">Creane una!</a></
			<%}%>
		</div>
		<% } %>
		</div>

		</div>
	</section>

</body>
</html>