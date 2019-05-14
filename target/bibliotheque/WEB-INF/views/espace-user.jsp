<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach var="livre" items="${livres}">
			<tr><td><c:out value="${livre.id }"></c:out></td>
			<td><c:out value="${livre.auteur }"></c:out></td>
			<td><c:out value="${livre.annee }"></c:out></td>
			<td><c:out value="${livre.genre }"></c:out></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>