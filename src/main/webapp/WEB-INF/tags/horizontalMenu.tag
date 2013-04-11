<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm"%>
<%@ taglib uri="/WEB-INF/ecmTags.tld" prefix="dev"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<sec:authorize var="authorized" ifAnyGranted="ROLE_ADMIN" />
<dev:head>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resource_files/style.css">
</dev:head>
<div id="navigation">
	<c:set var="mode" value="${param['mode']}" />
	<c:choose>
		<c:when test="${(mode eq 'edit' or mode eq 'addPage') and authorized}">
			<dev:navigation var="levelRootItem" depth="1"
				sitemap="${sitemapItemForm.sitemap}">
				vittu toimi!
				<ul>
					<dev:navigationLevel var="navigationItem"
						sitemapItem="${levelRootItem}"
						activeSitemapItem="${sitemapItemForm.sitemapItem}">
						<ecm:NavigationItem item="${navigationItem}"
							styleClass="${styleClass}" />
					</dev:navigationLevel>

					<c:if test="${isLastItem and empty sitemapItemForm.sitemapItem.id}">
						<form:input path="sitemapItem.properties['title']" />
					</c:if>
				</ul>
			</dev:navigation>
		</c:when>
		<c:otherwise>
			<dev:navigation var="levelRootItem" depth="3"
				sitemapId="${currentSitemapId}">
				<ul>
					<dev:navigationLevel var="navigationItem"
						sitemapItem="${levelRootItem}" activeSitemapItem="${sitemapItem}">
						<ecm:NavigationItem item="${navigationItem}"
							styleClass="${styleClass}" />
					</dev:navigationLevel>
				</ul>
			</dev:navigation>
		</c:otherwise>
	</c:choose>
	<span class="navEnd"> </span>
</div>