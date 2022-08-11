<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"%>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Aggiunta di un nuovo gioco</title>
		<link rel="stylesheet" type="text/css" href="css/nuovo-gioco.css">
		
		
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
			}else if((utenteLoggato.getTipo().equals("mod"))||(utenteLoggato.getTipo().equals("user")))
			{
				response.sendRedirect("nuova-richiesta-gioco.jsp");
				return;
			}
		
		%>
		
		<div class="container">
			<form id="this-form">
				<h1>Nuovo gioco</h1>
				<div id="input-box">
					<input class="input-field" name="game_title" type="text" id="game_title" placeholder=" " required autocomplete="off">
					<label for="game_title">Inserire un titolo*</label>
				</div>
				
				<div id="input-box">
					<input name="publisher" type="text" id="publisher" placeholder=" " required>
					<label for="publisher">Inserire un publisher*</label>
				</div>
				
				<div id="input-box">
					<select id="game_genre" name="game_genre">
	 					<option value="Altro">Altro</option>
	 					<option value="Applicazione">Applicazione</option>
	 					<option value="Azione">Azione</option>
	 					<option value="Avventura">Avventura</option>
	  					<option value="Arcade">Arcade</option>
	  					<option value="Action-Adventure">Action-Adventure</option>
	  					<option value="Action RPG">Action RPG</option>
	  					<option value="Educativo">Educativo</option>
	  					<option value="Gestionale">Gestionale</option>
	  					<option value="JRPG">JRPG</option>
	  					<option value="Musicale">Musicale</option>
	  					<option value="Party">Party</option>
	  					<option value="Platform">Platform</option>
	  					<option value="Picchiaduro">Picchiaduro</option>
	  					<option value="Puzzle">Puzzle</option>
	  					<option value="RPG">RPG</option>
	  					<option value="Roguelike">Roguelike</option>
	  					<option value="Sparatutto">Sparatutto</option>
	  					<option value="Sport">Sport</option>
	  					<option value="Stealth">Stealth</option>
	  					<option value="Strategico">Strategico</option>
					</select>
					<label id="genre-label" for="game_genre">Genere:</label>
				</div>
	
				<div id="input-box">
				 	<input type="number" id="score" name="score" min="50" max="200" value="50" step="10">
				 	<label for="quantity">Punteggio:</label>
				</div>
	
				<div id="input-box">
					<select id="game_year" name="game_year">
						<% for (int anno = 2021; anno>=1958; anno--){ %>
	 					<option value="<%=anno%>"><%=anno%></option>
	 					<%} %>
					</select>
					<label id="game-year-label" for="game_year">Anno di pubblicazione:</label>
				</div>
	
				<div id="input-box">
					<textarea name="game_desc" id="game_desc" placeholder=" " ></textarea>
					<label for="game-desc">Inserire una descrizione</label>
				</div>
	
				<div id="input-box">
					<input type="button" id="buttonaggiungi" class="setButton" value="Aggiungi gioco">
				</div>
			</form>
		</div>
	</body>
	
	<script src="script/NuovoGioco.js"></script>
</html>