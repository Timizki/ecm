<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm" %>
<tiles:setCurrentContainer containerKey="adminContainer" />

<html>
	<head>	
		<title>
			<tiles:insertAttribute name="title"/>
		</title>
		<sec:authorize ifAllGranted="ROLE_ADMIN">
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ecm.css">
		</sec:authorize>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/screen.css">
	</head>
	<body>
		<ecm:ecmManager />
		<div id="pageContainer">
		<div class="page"></div>
			<div id="headerStripe">					
				<div id="page" class="page">		
					<div id="header">
						<div class="headerContent">
							<tiles:insertAttribute name="header" ignore="true"/>
						</div>
						<div id="menu">
							<tiles:insertAttribute name="menu" ignore="true"/>
						</div>
					</div>							
					<div id="body">
						<tiles:insertAttribute name="body" ignore="true"/>
					</div>
					<div id="footer">
					</div>		
				</div>
			</div>
		</div>
	</body>
</html>