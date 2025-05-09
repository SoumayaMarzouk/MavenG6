<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,model.Departement,model.Projet" %> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% List<Departement> dept=(List<Departement>)request.getAttribute("depts");
List<Projet> pr=(List<Projet>)request.getAttribute("projets");%>
<h1>Ajouter une personne</h1>
<form action="PersonneController" method=post>
CIN : <input type=text name=cin><br>
Nom : <input type=text name=nom><br>
Prenom : <input type=text name=prenom><br>
Departement:<select name=dept>

<c:forEach items="${depts}" var="d" varStatus="status">
<option value=${d.id }>${d.nom }</option>	

</c:forEach>
</select>
<br>Projets:<br>

<c:forEach items="${projets}" var="pr" varStatus="status">
<input type=checkbox name=projet value=${pr.id } />${pr.nom}<br>

</c:forEach>


<input type=submit name=create value=Ajouter ><br>
</form>
</body>
</html>