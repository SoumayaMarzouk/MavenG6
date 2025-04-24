<%@ page import="java.util.List,model.Personne" %> 
<head> <meta charset="utf-8"> <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> </head> 
<h1> Liste des personnes</h1>
<div class="container mt-4">
<% String m=(String)request.getAttribute("message");
if (m!=null && !m.equals("")) { %>
<div class="alert alert-dismissible alert-secondary">
<strong> <%= m %> </strong>.
</div> <% } %> 
<table class="table table-hover">
<tr class="table-primary"><th>Id</th><th>Cin</th><th>Nom</th><th>Prenom</th> <th>Actions</th></tr>
<%
List<Personne> results = (List<Personne> )request.getAttribute("listPersonne");
for(Personne p:results){ %>
	<tr><td> <%= p.getId()%> </td><td> <%= p.getCin()%>
	      </td> <td> <%= p. getNom()%> </td> <td> <%= p. getPrenom()%> </td> 
	       <td><a href='PersonneController?idUpdate=<%=p.getId()%>' class="btn btn-success">Editer</a> 
	              <a href='PersonneController?id=<%=p.getId()%>' class="btn btn-danger">supprimer</a></td>
	</tr>
<% }  %> </table></div>

