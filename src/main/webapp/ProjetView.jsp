<%@ page import="java.util.List,model.Personne,model.Projet" %> 
<head> <meta charset="utf-8"> <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> </head> 
<h1> Liste des projets</h1>
<div class="container mt-4">


<table class="table table-hover">
<tr class="table-primary"><th>Projet</th><th>Nb personne</th></tr>
<%
List<Object[]> results = (List<Object[]> )request.getAttribute("projets");

for(Object[] p:results){ 
	String nom=(String)p[0];
	long l=(long)p[1];
	
%>
	<tr><td> <%= nom%> </td><td> <%= l%>
	      </tr>
<% }  %> </table></div>

