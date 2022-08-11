<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="userManager.*"
    import="util.*"
    import="java.util.*"%>
    
      <%	

	UtenteDAO userModel = new UtenteDAO();
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="./css/header.css">
	
	
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
	 <!-- SweetAlert v2.0 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>  
	</head>

	<body>

		<div class="header-grid">

			<div class="logo-item">
				<a href="${pageContext.request.contextPath}/">
					<img src="./img/Website/logo1.png" class="logo-image">
				</a>
			</div>

			<div class="wrap-item">
				<form action="Cerca" method="POST" id="searchbarForm">
					<div class="searchbar-item">
						<input type="text" class="searchTerm" placeholder="Cerca gioco..." name="cerca">
						<button type="submit" class="searchButton">
		     	 			<i class="fa fa-search"></i>
		     	 		</button>
		     	 	</div>
				</form>
			</div>
			
		<!-- UTENTE -->
			<div class="nav-items-left">
				<button class="nav-button"><a href="${pageContext.request.contextPath}/">Home</a></button>
				<button class="nav-button"><a href="FindAll">Catalogo</a></button>
				<button class="nav-button"><a href="#">Aiuto</a></button>
			</div>
			
			<div class="nav-items-right">
						<% 
							Utente utenteLoggato;
							synchronized(session) {
								utenteLoggato = (Utente) request.getSession().getAttribute("utenteLoggato"); 
							}
							if(utenteLoggato!=null) {
								if((utenteLoggato.getTipo()).equals("user")){ %>
									<button class="nav-button"><a href="area-utente.jsp"><span class="glyphicon glyphicon-user men"></span><%=utenteLoggato.getUsername()%> </a></button>
						<%} else if ((utenteLoggato.getTipo()).equals("mod")||(utenteLoggato.getTipo()).equals("dev")){ %>
									<button class="nav-button"><a href="area-utente.jsp"><%=utenteLoggato.getTipo()%>:<%=" "+utenteLoggato.getUsername()%></a></button>
						<%} %>
						<%} else {%>
									<button class="nav-button"><a href="login-page.jsp"><span class="glyphicon glyphicon-user men"></span>Area Utente</a></button>
						<%} 
							if(utenteLoggato!=null) {
							if( utenteLoggato.getTipo().equals("mod")){%>
								<button class="nav-button"><a href="richieste.jsp">Richieste Thread</a></button>
							<%} else if(utenteLoggato.getTipo().equals("manager")|| (utenteLoggato.getTipo().equals("dev"))){%>
							<button class="nav-button"><a href="nuovo-gioco.jsp">Aggiungi Gioco</a></button>
						<%} 
							else{%>
								<button class="nav-button"><a class="nav-button" href="threads.jsp">Visualizza tutti i thread</a></button>
							<%}
							}else{%>
							<button class="nav-button"><a href="threads.jsp">Visualizza tutti i thread</a></button>
						<%}
							if(request.getSession(false) == null || request.getSession(false).getAttribute("utenteLoggato") == null) {
						%>
							<button class="nav-button"><a href="login-page.jsp">LOGIN</a></button>
						<%
						 	  }
							  else {
						%>
							<button class="nav-button"><a href="${pageContext.request.contextPath}/Logout">LOGOUT</a></button>
						<%
							}
						%>
						
			</div>
			
		</div>
		
	</body>

</html>