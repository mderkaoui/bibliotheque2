<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Formation Servlet / JSP</h2>
<a href="/">Accueil</a>
<hr />
<c:if test="${param.isConnected}">
	<p>Bonjour ${sessionScope.username}</p>
</c:if>

