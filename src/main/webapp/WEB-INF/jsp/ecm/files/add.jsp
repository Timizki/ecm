
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/ecmTags.tld" prefix="dev" %>

<script src="${pageContext.request.contextPath}/resources/codemirror/lib/codemirror.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/codemirror/lib/codemirror.css">
<script src="${pageContext.request.contextPath}/resources/codemirror/mode/javascript/javascript.js"></script>
<div id="page">
	<form:form commandName="fileForm" action="${pageContext.request.contextPath}/cpanel/files/store.do" name="userForm">
		<script src="${pageContext.request.contextPath}/resources/codemirror/mode/css/css.js"></script>
		<form:hidden path="file.id"/>
		<div id="ecmFileAdd">
			<div class="ecmUsername">
				<span>Tiedoston nimi ja pääte</span>
				<form:input path="file.fileName" id="ecmFileName" />.<form:input path="file.fileSuffix" id="ecmFileSuffix"/>
			</div>
			<div class="ecmFileType">
				<span>Tiedoston tyyppi</span>
				<form:input path="file.type" id="ecmFileType"/>/<form:input path="file.subType" id="ecmFileSubType"/>
			</div>
			<div class="ecmFileContent">
				<span>Sisältö</span>
				<form:textarea path="fileContent" id="ecmFileContent"/>
			</div>			
			<div class="ecmSubmit">
				<input name="submit" type="submit" value="Tallenna" />
			</div>
		</div>
	</form:form>
</div>