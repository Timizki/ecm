<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
	<div id="ecmLogin">
		<c:if test="${param['errors']}">
			<div id="loginError">
				<span>${errorMessage}</span>
			</div>
		</c:if>
		<form action="<c:url value='j_spring_security_check' />" method="post">
			<div class="ecmUsername">
				<label for="ecmUsernameInput"><span>K�ytt�j�tunnus</span></label> 
				<input type='text' id="ecmUsernameInput" name='j_username'> 
			</div>
			<div class="ecmPassword">
				<label for="ecmPasswordInput"><span>Salasana</span></label>
				<input type='password' id="ecmPasswordInput" name='j_password' /> 
			</div>
			<div class="ecmSubmitLogin">
				<input name="submit" type="submit"	value="Kirjaudu" />
			</div>
		</form>
	</div>
