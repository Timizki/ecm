<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="type" %>
<%@ attribute name="text" %>
<%@ attribute name="cancelControllerURL" %>
<%@ attribute name="returnURL" %>

<c:if test="${not empty param['returnURL'] and empty returnURL}">
	<c:set var="returnURL" value="${param['returnURL']}" />
</c:if>

<c:if test="${empty text}">
	<c:set var="text" value="Takaisin" />
</c:if>

<c:if test="${empty type}">
	<c:set var="type" value="button" />
</c:if>
<c:choose>
	<c:when test="${empty cancelControllerURL}">
		<c:url var="rURL" value="${returnURL}" />
	</c:when>
	<c:otherwise>
		<c:url var="rURL" value="${pageContext.request.contextPath}${cancelControllerURL}?returnURL=${returnURL}" />
	</c:otherwise>
</c:choose>
	<a href='${rURL}'>
		<c:choose>
			<c:when test="${type eq 'button'}">
					<input type="button" value='<c:out value="${text}" escapeXml="true" />' />	
			</c:when>
			<c:otherwise>
				<c:out	value="${text}" escapeXml="true" />
			</c:otherwise>
		</c:choose>
	</a>	
