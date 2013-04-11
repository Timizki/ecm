<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="item" type="net.vksn.sitemap.model.SitemapItem" required="true" %>
<%@ attribute name="styleClass" %>
<c:set var="mode" value="${param['mode']}" />

<sec:authorize var="authorized" ifAllGranted="ROLE_ADMIN"/>
<li class="${styleClass}">
	<c:choose>
		<c:when test="${((mode eq 'edit' or mode eq 'addPage') and authorized) and (item.id eq sitemapItemForm.sitemapItem.id )}">
			<form:input path="sitemapItem.properties['title']"/>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/${item.pathAsString}.html" class="${styleClass}"><span class="${styleClass}"><c:out value="${empty item.properties['title'] ? item.name : item.properties['title']}" /></span></a>
		</c:otherwise>
	</c:choose>							
</li>