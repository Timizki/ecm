<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm" %>
<%@ taglib uri="/WEB-INF/ecmTags.tld" prefix="dev" %>

<html>

	<head>	
		<dev:head>
			<title>
				<ecm:title />
			</title>
		</dev:head>
	
		<sec:authorize ifAllGranted="ROLE_ADMIN">
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ecm.css">
			<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
		</sec:authorize>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/screen.css">
	</head>
	<body>
		<ecm:ecm>
			<jsp:attribute name="body">
				<div id="pageContainer">
					<div class="page"></div>
					<div id="headerStripe">					
						<div id="page">		
							<div id="header">
								<div class="headerContent">
									<tiles:insertAttribute name="header" ignore="true"/>
								</div>
								<div id="menu">
									<tiles:insertAttribute name="menu" />
								</div>
							</div>							
							<div id="body">
								<tiles:insertAttribute name="body" ignore="true"/>
							</div>
							<div id="footer">
								<tiles:insertAttribute name="footer" ignore="true"/>
							</div>		
						</div>
					</div>
				</div>
			</jsp:attribute>
		</ecm:ecm>
	</body>

</html>