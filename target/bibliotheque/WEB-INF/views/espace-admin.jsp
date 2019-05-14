<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Liste des livres</title>
</head>
<body>
<!--  CRUD = Create Read Update Delete -->
	<h1>Gestion des livres</h1>
	
	<h3>Ajout / Modif</h3>
	<form method="post" action="admin/livre">
		<label for="titre">Titre : </label>
			<input type="text" name="titre" id="titre" value="${livTrouve.titre}" />
			<br />
			<label for="auteur">Auteur : </label>
			<input type="text" name="auteur" id="auteur" value="${livTrouve.auteur}"  />
			<br />
			<label for="annee">Année : </label>
			<input type="text" name="annee" id="annee" value='<fmt:formatDate value="${livTrouve.annee}" pattern="dd/MM/yyyy" />' />
			<br />
			<label for="genre">Genre : </label>
			<select name="genre" id="genre">
				<c:forEach var="g" items="${genres}"> <!--  TODO if sur livTrouve.genre pour ajouter selected -->
					<option value="${g.ordinal()}">${g}</option>
				</c:forEach>
			</select>
			<br />
			<input type="hidden" name="id" value="${livTrouve.id}" />
			<input type="submit" value="Sauvegarder" />
	</form>
	
	<h3>Liste des livres</h3>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Titre</th>
			<th>Auteur</th>
			<th>Année</th>
			<th>Genre</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="livre" items="${livres}">
			<tr>
				<td><c:out value="${livre.id}"></c:out></td>
				<td><c:out value="${livre.titre}"></c:out></td>
				<td><c:out value="${livre.auteur}"></c:out></td>
				<td><fmt:formatDate value="${livre.annee}" pattern="dd/MM/yyyy" /> </td>
				<td><c:out value="${livre.genre}"></c:out></td>
				<td>
					<a href="admin/livre?action=modifier&id=${livre.id}">Modifier</a>
					<span> | </span>
					<a href="admin/livre?action=supprimer&id=${livre.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${page>1}">
		<a href="admin/livre?page=${page-1}&max=${max}">Précédent</a>
	</c:if>
	 <span>${page}</span> 
	 
	<c:if test="${suivExist}">
		<a href="admin/livre?page=${page+1}&max=${max}">Suivant</a>
	</c:if>
	
	<h3>Export CSV</h3>
	<a href="admin/livre?action=exportcsv">Download CSV</a>
	
	
	
	<div>${msg}</div>
</body>
</html>