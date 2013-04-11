
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/ecmTags.tld" prefix="dev" %>
<div id="page">
	<form:form commandName="userForm" action="${pageContext.request.contextPath}/cpanel/user/store.do" name="userForm">
		<div id="ecmUserAdd">
			<form:hidden path="user.id"/>
			<div class="ecmUsername">
				<span>Käyttäjätunnus</span>
				<form:input path="user.username" />
			</div>
			<div class="ecmPassword">
				<span>Salasana</span>
				<form:password path="user.password" />
			</div>
			<div class="ecmPassword">
				<span>Salasanan varmistus</span>
				<form:password path="password" />
			</div>
			<div class="ecmUserEnabled">
				<span>Aktivoitu käyttäjä</span>
				<form:checkbox path="user.enabled" />
			</div>
			<div class="ecmUserGroups">
				<span>Käyttäjäryhmät</span>
				<form:checkboxes items="${userForm.groups}" path="user.groups" itemLabel="name" itemValue="id" />
			</div>
			<div class="ecmSubmit">
				<input name="submit" type="submit" value="Tallenna" />
				<ecm:back text="Peruuta" cancelControllerURL="/cpanel/user/cancel.do" returnURL="${param['returnURL']}"/>
			</div>
		</div>
	</form:form>
</div>
