<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"
         import="java.util.*"
         import="gameManager.*"
%>
<%@ page import="threadManager.ThreadDAO" %>
<%@ page import="threadManager.GameThread" %>
<%@ page import="java.sql.SQLException" %>


<%


    ArrayList<?> array = (ArrayList<?>) session.getAttribute("giochi");
    GiocoDAO GameModel=new GiocoDAO();
    ThreadDAO threadModel= new ThreadDAO();
    ArrayList<GameThread> list = new ArrayList<>();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>GamingZone - Threads</title>
    <link rel="stylesheet" type="text/css" href="css/threads.css">
</head>
<body style="margin:0">

<!-- Header section start -->
<div class="header">
    <%@ include file="fragment/header.jsp" %>
</div>
<!-- Header section end -->

<section class="catalogo-pagina">

    <div class="container">
        <p>Tutti i thread</p>
    </div>

    <%
        for (Gioco g:GameModel.findAllGames()) {%>
    <div class="thread">
        <img class="imagethread" src="<%=g.getImgpath()+"/img1.png"%>" width="90px" height="90px" >  <h2><%=g.getNome()%></h2><%
        list=threadModel.viewThreadByGame(g.getIdGioco());
        if(list.isEmpty()){   %>  <p>non esistono thread per questo gioco </p>         <%}
        else{
         %>
        <ul>
            <%
                for (GameThread t:list) {%>

            <li>
                <a href="ViewThread?threadid=<%=t.getIdThread()%>"><%=t.getTitolo()%></a>  SCRITTO DA: <%=t.getUsernameUtente()%>
            </li>
              <%  } %>
        </ul>
        <% }%>
    </div>
    <%  }%>
</section>


</body>
</html>