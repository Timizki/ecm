<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ecm" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<script>
		 function updateForm(action) {
			 var actionInput = document.getElementById('ecmActionInput');
			 actionInput.value = action;
			 document.forms['ecmForm'].submit();
		 }
	</script>
	<c:set var="mode" value="${param['mode']}" />
	<div id="ecmManagerToolbar">
		<div id="ecmToolbarLeftPart">
			<div id="ecmPageActions">
				<ul>
					<c:choose>
						<c:when test="${(mode eq 'edit' or mode eq 'addPage')}">
							<li class="first"><a href="#" onclick="updateForm('');">Tallenna</a></li>
							<li class="first"><a href="?">Peruuta</a></li>
						</c:when>
						<c:otherwise>
							<li class="first"><a href="?mode=edit">Muokkaa sivua</a></li>
						</c:otherwise>
					</c:choose>
					<c:if test="${not (mode eq 'edit' or mode eq 'addPage')}">
						<li><a href="?mode=addPage">Uusi sivu</a></li>
					</c:if>
				</ul>
			</div>
			<div id="ecmPageAttributes">
				<c:if test="${mode eq 'edit' or mode eq 'addPage'}">
					<span>Sivun nimi:</span><form:input path="sitemapItem.name"/>
					<span>Yläsivu:</span>
					<form:select path="parent" onchange="updateForm('parent')">
						<form:option value="">Ei yläsivua</form:option>
						<form:options items="${sitemapItemForm.sitemapItems}" itemLabel="name" itemValue="id"/>
					</form:select>
					<span>Sivupohja:</span>
					<form:select path="sitemapItem.decorationName" onchange="updateForm('decorationName')" 
						items="${sitemapItemForm.templates}" itemLabel="name"
						 itemValue="name"/>
					<span>Järjestys:</span>
					<form:select path="newPagePosition" onchange="updateForm('position')" >
						<c:forEach var="pos" items="${sitemapItemForm.siblings}" varStatus="status">
							<form:option value="${status.index}">${pos.name}</form:option>
						</c:forEach>
					</form:select>
						
					<input type="hidden" value="" id="ecmActionInput" name="action" />
				</c:if>
			</div>
		</div>
		<div class="clear"></div>
		<div id="ecmToolbarRightPart">
			<div id="ecmLoginInfo">
				<span id="ecmLoginUser">${pageContext.request.remoteUser}</span>
				<span><a href="<c:url value="j_spring_security_logout" />" >Kirjaudu ulos</a></span>				
			</div>
			<div id="ecmManagementLink">
				<span><a href="${pageContext.request.contextPath}/cpanel/management.do">Hallinnoi sivustoa</a></span>	
			</div>
		</div>
	</div>
</sec:authorize>