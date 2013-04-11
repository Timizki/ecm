<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<form action="<c:url value='j_spring_security_check' />" method="post">
	<div id="ecmLogin">
		<c:if test="${param['errors']}">
			<div id="loginError">
				<span>${errorMessage}</span>
			</div>
		</c:if>
		<div class="ecmUsername">
			<label for="ecmUsernameInput"><span>Käyttäjätunnus</span></label> 
			<input type='text' id="ecmUsernameInput" name='j_username'> 
		</div>
		<div class="ecmPassword">
			<label for="ecmPasswordInput"><span>Salasana</span></label>
			<input type='password' id="ecmPasswordInput" name='j_password' /> 
		</div>
		<div class="ecmSubmitLogin">
			<input name="submit" type="submit"	value="Kirjaudu" />
		</div>
	</div>
</form>
