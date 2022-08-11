<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="userManager.*"
    import="util.*"
    import="java.util.*"
    import="listManager.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<h1>I miei giochi</h1>

							
		
		<div class="giochi">
		<%
				ElementoListaDAO model=new ElementoListaDAO();
				ArrayList<ElementoLista> array = model.getUserList(utenteLoggato.getUsername());		
				if(array.size()!=0)
				{										
					Iterator<?> it2 = array.iterator();
					while (it2.hasNext()){
					ElementoLista bean = (ElementoLista) it2.next();%>
						<b> - </b><a href="GamePage?id=<%=bean.getIdGioco()%>"><%=model.getGameName(bean.getIdGioco())%></a><b> - <%=bean.getCategoria()%></b>
						<br>
						<br>
					<% }
				}
				else { %>
						<h4>La tua lista è vuota.</h4>
							<% } %>	
		</div>		
</body>
</html>