<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"
    import="threadManager.*"%>
    
    <%	
	GiocoDAO gameModel = new GiocoDAO();
    int idGioco = Integer.parseInt(request.getParameter("gameid"));
    Gioco game = gameModel.findGameById(idGioco);
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Nuova Discussione</title>
		<link rel="stylesheet" type="text/css" href="css/nuova-discussione.css">
		
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
		<div class="header">
					<%@ include file="fragment/header.jsp" %>
		</div>
		
		<%
			if(utenteLoggato==null)
			{
				response.sendRedirect("login-page.jsp");
				return;
			}
		
		%>

		<div class="container">
			<form id="this-form">
				<h1>Nuova discussione su <%=game.getNome()%></h1>
				<h3>Scritta da <%=utenteLoggato.getUsername()%> (<%=utenteLoggato.getTipo()%>)</h3>
		
				<div id="input-box">
					<input name="thread_title" type="text" id="thread_title" placeholder=" " required>
					<label for="thread_title">Inserire un titolo*</label>
				</div>
					
				<div id="input-box">
					<%--@declare id="tipo"--%><select id="thread_type" name="thread_type">
		 				<option value="Opinioni">Opinioni</option>
		  				<option value="Suggerimenti">Suggerimenti</option>
		 				<option value="Spoiler">Spoiler</option>
		  				<option value="Torneo">Torneo</option>
					</select>
					<label id="type-label" for="tipo">Tipo:</label>
				</div>
							
				<div id="input-box">
					<textarea name="thread_text" id="thread_text" placeholder=" " ></textarea>
					<label id="text-label" for="thread_text">Inserire un testo</label>
				</div>
							
				<input type="hidden" id="gameID" name="gameID" value="<%=game.getIdGioco()%>">
				<input type="hidden" id="username" name="username" value="<%=utenteLoggato.getUsername()%>">
				<% if(utenteLoggato.getTipo().equals("mod")|| utenteLoggato.getTipo().equals("dev")){ %>
				<div id="input-box">
					<input type="button" id="buttoncrea" class="setButton"
						value="Crea thread">
				</div>
				<%}else{ %>
				<div id="input-box">
					<input type="button" id="richiestaT" class="setButton" value="Richiedi thread">

				</div>
				<%} %>
			</form>
		</div>
	</body>

	<script src="script/RichiestaThread.js" defer></script>
	<script src="script/CreazioneThread.js" defer></script>
</html>