<%@page isErrorPage="false" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Formation Servlet / JSP : <sitemesh:write property='title'/></title>
		<!-- base sert à définir une référence qui préfixera l'ensemble des liens -->
		<base href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"%>" />
		<sitemesh:write property='head'/>
	</head>
<body>

	<%-- inclusion à l'exécution (on peut passer des paramètres) --%>
	<jsp:include page="/WEB-INF/views/entete.jsp">
		<jsp:param value="${sessionScope.isConnected}" name="isConnected" />
	</jsp:include>
	
	<!--  le body des autres pages sera inséré ici -->
	<sitemesh:write property='body'/>
	
	<%-- inclusion à la compilation --%>
	<%@include file="/WEB-INF/views/piedpage.jsp" %>
	
</body>
</html>
