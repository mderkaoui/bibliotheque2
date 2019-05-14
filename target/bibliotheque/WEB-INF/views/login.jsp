<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Login</title>
</head>
<body>
	<form action="login" method="post">
		<div>
			<label for="login">Login : </label>
			<input type="text" name="login" placeholder="Saisir login" id="login" />
			<label for="password">Mot de passe : </label>
			<input type="password" name="password" placeholder="Saisir mot de passe" id="password" />
			<input type="submit" value="Se connecter" >	
		</div>
	</form>
	<p><c:out value="${msg}"/><p>
</body>
</html>