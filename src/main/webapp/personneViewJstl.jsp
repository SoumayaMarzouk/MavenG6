<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head> <meta charset="utf-8"> <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> </head> 
<h1> Liste des personnes</h1>
<div class="container mt-4">
<c:if test="${message!=''}" >
<div class="alert alert-dismissible alert-secondary">
<strong> ${message} </strong>.
</div>
</c:if>
<a href='PersonneController?formCreate=ok' class="btn btn-success">Create</a>
<form action="PersonneController" method=post>
Search by Project: <input type=text name=id><br>
<input type=submit name=search value=Search ><br>
</form>
<form action="personneViewJstl.jsp" method=post>
Search by Department: <input type=text name=idDept><br>
<input type=submit name=deptSearch value=Search ><br>
</form>
<table class="table table-hover">
<tr class="table-primary"><th>Id</th><th>Cin</th><th>Nom</th><th>Prenom</th> <th>Departement</th><th>Projets</th><th>Nb Projets</th><th>Actions</th></tr>
<c:set var="listPersonne" value="${listPersonne}" scope="session"/>
<c:choose>
<c:when test="${empty listPersonne}">
<tr><td>Pas de personne dans la bd</td></tr>
</c:when>
<c:otherwise>
<c:forEach items="${listPersonne}" var="p" varStatus="status">


<c:if test="${empty param.deptSearch or param.idDept==p.department.id }">
	<tr><td> ${p.id} </td><td> ${p.cin}
	      </td> <td>${p.nom}</td> <td>${p.prenom}</td>  <td>${p.department.nom}</td> 
	      <td>
	<c:forEach items="${p.projets}" var="pr" varStatus="status">
		${status.index+1} - ${pr.nom}<br>
	</c:forEach>
		</td><td>${fn:length(p.projets) }</td>
	       <td><a href='PersonneController?idUpdate=${p.id}' class="btn btn-success">Editer</a> 
	              <a href='PersonneController?id=${p.id}' class="btn btn-danger">supprimer</a></td>
	</tr>
	</c:if>
	
	
</c:forEach>
</c:otherwise>
</c:choose>
</table></div>

