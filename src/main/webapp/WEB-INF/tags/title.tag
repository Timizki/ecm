<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="mode" value="${param['mode']}" />
<c:choose>
	<c:when test="${(mode eq 'edit' or mode eq 'addPage') and authorized}">
		<c:out
			value="${empty sitemapItemFrom.sitemapItem.properties['title'] ? sitemapItemFrom.sitemapItem.name : sitemapItemFrom.sitemapItem.properties['title']}"
			escapeXml="true" />
	</c:when>
	<c:otherwise>
		<c:out
			value="${empty sitemapItem.properties['title'] ? sitemapItem.name : sitemapItem.properties['title']}"
			escapeXml="true" />
	</c:otherwise>
</c:choose>