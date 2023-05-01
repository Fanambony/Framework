<%-- 
    Document   : index
    Created on : 30 mars 2023, 16:28:37
    Author     : Benji
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Object[] ob = (Object[])request.getAttribute("value");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <% for(Object o : ob){%>
        <label> <%= o.toString() %> </label>
        <% } %>
        
        <form action="saveAll" method="post">
            Entrer votre prenom:
            <input type="text" name="prenom">
            <input type="submit" value="Valider">
        </form> 
           
    </body>
</html>
