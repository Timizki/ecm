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
				<th>Tiedostotyyppi</th>
				<th></th>
				<th></th>
				<th>Toiminnot</th>
			</tr>
			<c:forEach var="file" items="${files}">
				<tr>
					<td>${file.fileName}.${file.fileSuffix}</td>
					<td>${file.type}/${file.subType}</td>
					<td></td>
					<td></td>
					<td>
						<a href="${pageContext.request.contextPath}/cpanel/files/details.do?fileName=${file.fileName}&fileSuffix=${file.fileSuffix}&returnURL=/cpanel/files/list.do">Tiedot</a>
						<a href="${pageContext.request.contextPath}/cpanel/files/edit.do?fileName=${file.fileName}&fileSuffix=${file.fileSuffix}&returnURL=/cpanel/files/list.do">Muokkaa</a>
						<a href="${pageContext.request.contextPath}/cpanel/files/delete.do?fileName=${file.fileName}&fileSuffix=${file.fileSuffix}&returnURL=/cpanel/files/list.do">Poista</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<ecm:back text="Takaisin" type="text" returnURL="/cpanel/management.do" />
</div>
