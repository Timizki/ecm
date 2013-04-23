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
			<dev:navigation var="levelItems" depth="1"
				sitemap="${sitemapItemForm.sitemap}"
				sitemapItem="${sitemapItemForm.sitemapItem}">
				<ul>
					<dev:navigationLevel var="navigationItem"
						levelItems="${levelItems}"
						activeSitemapItem="${sitemapItemForm.sitemapItem}">
						<ecm:NavigationItem item="${navigationItem}" styleClass="${styleClass}" />
					</dev:navigationLevel>
				</ul>
			</dev:navigation>
		</c:when>
		<c:otherwise>
			<dev:navigation var="levelItems" depth="1"
				sitemapId="${currentSitemapId}"
				sitemapItem="${sitemapItem}">
				<ul>
					<dev:navigationLevel var="navigationItem"
 						levelItems="${levelItems}" 
 						activeSitemapItem="${sitemapItem}">
						<ecm:NavigationItem item="${navigationItem}" styleClass="${styleClass}" />
					</dev:navigationLevel>
				</ul>
			</dev:navigation>
		</c:otherwise>
	</c:choose>
	<span class="navEnd"> </span>
</div>