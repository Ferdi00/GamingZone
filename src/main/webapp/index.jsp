<%@ page import="gameManager.GiocoDAO" %>
<%@ page import="gameManager.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>GamingZone - Home</title>
    <link rel="stylesheet" type="text/css" href="css/index.css " >
    <script type="text/javascript">
    </script>
</head>
<body style="margin:0">
<header class="index-header">
    <%@ include file="fragment/header.jsp" %>
</header>


<!-- Giochi -->
<section class="catalogo-section">
    <div class="container">
        <p>Giochi popolari</p>
    </div>
    <div>
        <%
            GiocoDAO model_product=new GiocoDAO();
            ArrayList<Gioco> arrayFeatured = null;
            try {
                arrayFeatured = model_product.getTopRated();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int I=0;

            if((arrayFeatured!=null || arrayFeatured.size()!=0))
            {

        %>
        <ul class="games-list">
            <%
                Iterator<?> it2 = arrayFeatured.iterator();
                while (it2.hasNext())
                {
                    Gioco bean = (Gioco) it2.next();
            %>

            <li class="game-object">
                <div class="imageContainer">
                    <a href="GamePage?id=<%=bean.getIdGioco()%>"><img src=<%=bean.getImgpath()+"/img1.png"%> width="150" height="150" class="imgItem"></a>
                </div>
                <div class="nameContainer">
                    <h5><a class="game-name" href="GamePage?id=<%=bean.getIdGioco()%>"><%=bean.getNome()%></a></h5>
                </div>
            </li>


            <% 	}
            } else { %>
            <div class="col-md-12"><h4>Nessun gioco disponibile</h4></div>
            <% } %>
        </ul>
    </div>
</section>

<!-- Classifica Utenti -->
<section class="classifica-section" >
    <section class="titoloclass">
        TOP 10 UTENTI DEL SITO
    </section>

    <div>
        <%
            ArrayList<Utente> arrayTop10 = null;
            try {
                arrayTop10 = userModel.findTopUsers();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int j=0;

            if((arrayTop10!=null || arrayTop10.size()!=0))
            {

        %>
        <ul class="users-list">
            <%
                Iterator<?> it3 = arrayTop10.iterator();
                while (it3.hasNext())
                {
                    j++;
                    Utente user = (Utente) it3.next();
            %>

            <li class="li">
                <h5><%=j%>° <%=user.getUsername()%> (<%=user.getTipo()%>) - <%=user.getPunteggio()%> punti</h5>
            </li>


            <% 	}
            } else { %>
            <div class="col-md-12"><h4>Nessun utente disponibile.</h4></div>
            <% } %>
        </ul>
    </div>

</section>

</body>
</html>