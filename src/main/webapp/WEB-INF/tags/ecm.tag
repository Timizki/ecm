<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ecm" tagdir="/WEB-INF/tags"  %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="body" fragment="true" %>
<sec:authorize var="authorized" ifAnyGranted="ROLE_ADMIN"/>

<c:choose>
	<c:when test="${authorized}">		
		<form:form commandName="sitemapItemForm" id="ecmForm">
			<ecm:ecmManager />
			<jsp:invoke fragment="body" />
		</form:form>
	</c:when>
	<c:otherwise>
		<jsp:invoke fragment="body" />
	</c:otherwise>
</c:choose>