<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Logout completato</title>
       <script type="text/javascript">
        function preventBack() { window.history.forward(); }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>

      <style>
       .logout{
           justify-self: center;
           text-align: center;
           background: linear-gradient(90deg, #f8ff00 0%, #3ad59f 100%);
           font-size: 17px;
           border: black solid 2px;
           border-radius: 25px;
       }
      </style>

   </head>
   
   <body style="text-align: center;">
 <div class="logout">
     <h1>Operazione di logout completata.</h1>
     <a href="index.jsp">torna alla home</a>
 </div>

   </body>
</html>