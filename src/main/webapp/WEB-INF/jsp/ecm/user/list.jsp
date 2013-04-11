<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/ecmTags.tld" prefix="dev" %>
<div id="page">
	<div id="ecmUsersList">
		<table>
			<tr>
				<th>Nimi</th>
				<th>Sähköposti</th>
				<th>Aktiivinen</th>
				<th>Roolit</th>
				<th>Toiminnot</th>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.username}</td>
					<td></td>
					<td>${user.enabled}</td>
					<td>
						<ul>
							<c:forEach var="group" items="${user.groups}">
								<li><c:out value="${group.name}"/></li>
							</c:forEach>
						</ul>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/cpanel/user/details.do?username=${user.username}&returnURL=/cpanel/user/list.do"></a>
						<a href="${pageContext.request.contextPath}/cpanel/user/edit.do?username=${user.username}&returnURL=/cpanel/user/list.do">Muokkaa</a>
						<a href="${pageContext.request.contextPath}/cpanel/user/disable.do?username=${user.username}&returnURL=/cpanel/user/list.do">Passivoi</a>
						<a href="${pageContext.request.contextPath}/cpanel/user/delete.do?userId=${user.id}&returnURL=/cpanel/user/list.do">Poista</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<ecm:back text="Takaisin" type="text" returnURL="/cpanel/management.do" />
</div>
