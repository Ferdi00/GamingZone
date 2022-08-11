<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>GamingZone - Registrazione</title>
    <script type="text/javascript">
        function preventBack() { window.history.forward(); }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>
    <link rel="stylesheet" type="text/css" href="css/registration-page.css">
</head>
<body style="margin:0">
<header class="login-header">
    <%@ include file="fragment/header.jsp" %>
</header>
<br>

<div class="registration-area">
    <h2>Registrazione</h2>
    <div id="loginbox" class="loginbox">

        <form action="" method="POST" >
            <fieldset class="input">
                <p id="login-form-username">
                    <input id="username" type="text" name="user_username" class="inputbox" size="18" autocomplete="off" placeholder="Username">
                </p>
                <p id="login-form-email">
                    <input id="email" type="email" name="user_email" class="inputbox" size="18" autocomplete="off" placeholder="E-mail">
                </p>
                <p id="login-form-password">
                    <input id="password" type="password" name="user_password" class="inputbox" size="18" autocomplete="off" placeholder="Password">
                </p>
                <p id="login-form-Bdate">
                    <input id="Bdate" type="date" name="Bdate" class="inputbox" size="18" autocomplete="off" placeholder="data di nascita">
                </p>
                <input type="submit" name="Submit" class="button" value="Registrati">
                <div class="clear"></div>
            </fieldset>
        </form>
    </div>
    <br>
    <div class="tasti-nav">
        <div id="torna-home">
            <a href="index.jsp">Torna alla home</a>
        </div>
    </div>
</div>


</body>
</html>