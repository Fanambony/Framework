<%-- 
    Document   : new
    Created on : 2 mai 2023, 08:50:11
    Author     : Benji
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="etu2023.framework.model.Personne" %>
<% Object[] ob = (Object[])request.getAttribute("personne");%>
<% Personne personne = null; %>
<% for(int i = 0; i < ob.length; i++) { %>
<% personne = (Personne)ob[i]; %>
<% } %>
<%@ page import="java.util.HashMap" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Liste</h1>
        <p> Votre nom est: <label> <%= personne.getNom() %> </label> </p>
        <p> Votre prenom est: <label> <%= personne.getPrenom() %> </label> </p>
        <p> Votre dtn est: <label> <%= personne.getDtn() %> </label> </p>
        
        <p> Vos langues sélectionnées sont: </p>
        <% String[] langues = personne.getLangue(); %>
        <ul>
            <% for (String langue : langues) { %>
            <li><%= langue %></li>
            <% } %>
        </ul>
    </body>
</html>