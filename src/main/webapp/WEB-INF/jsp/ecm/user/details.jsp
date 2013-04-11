<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div id="page">
	<div id="ecmUserDetails">
		<a href="/cpanel/user/edit.do?username=${user.username}?returnURL=/cpanel/user/details.do?username=${user.username}">Muokkaa</a>
		<div class="ecmUsername">
			<label for="ecmUsernameInput"><span>Käyttäjätunnus</span></label> 
			<span id="ecmUsernameInput"><c:out value="${user.username}" /></span>
		</div>
		<div class="ecmUserEnabled">
			<label for="ecmUserEnabled"><span>Tunnus käytössä</span></label>
			<span id="ecmUserEnabled"><c:out value="${user.enabled ? 'Aktiivinen' : 'Passiivinen'}" /></span> 
		</div>
		<div class="ecmUserCreated">
			<label for="ecmUserCreated"><span>Tunnus luotu</span></label>
			<span id="ecmUserCreated"><fmt:formatDate value="${user.created}" pattern="dd.MM.yyyy"/> </span> 
		</div>
		<ecm:back text="Takaisin" type="text" returnURL="/cpanel/users.do" />
	</div>
</div>
