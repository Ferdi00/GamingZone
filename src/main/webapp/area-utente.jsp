<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="util.*"
    import="userManager.*"
    %>
  
	
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>GamingZone - Area Utente</title>
 	<link rel="stylesheet" type="text/css" href="css/area-utente.css">  
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
			}else if((utenteLoggato.getTipo().equals("manager")))
			{
				response.sendRedirect("index.jsp");
				return;
			}
		
		%>
		
	<section class="utente-section">
		<div id="info-section">
			<div class="username">
				<h1><%=utenteLoggato.getUsername()%></h1>
			</div>
			<!-- Info utente -->
			<div class="infobox">
				<ul>
					<li>
						<img id="imgXD" src="./img/Website/default-propic.jpg" alt="Img" style="width:100%; max-width:280px; height: 280px;" class="imgItem2">
					</li>
					<li class="userinfo">
						<p>Username: <%=utenteLoggato.getUsername()%></p>
					</li>
					<li class="userinfo">
						<p>E-Mail: <%=utenteLoggato.getEmail()%></p>
					</li>
					<li class="userinfo">
						<p>Tipo: <%=utenteLoggato.getTipo()%></p>
					</li>
					<li class="userinfo" id="last">
						<p>Punteggio abilità: <%=userModel.getScore(utenteLoggato.getUsername())%></p>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="list">
			<%@ include file="list-view.jsp" %>
		</div>
	</section>
</body>
</html>