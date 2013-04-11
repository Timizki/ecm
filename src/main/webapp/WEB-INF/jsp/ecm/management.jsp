
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div id="page">
	<div id="ecmUsersList">
		<table>
			<tr>
				<th>K‰ytt‰j‰t</th>
				<th>K‰ytt‰j‰ryhm‰t</th>
				<th>Sivut</th>
				<th>Lomakkeet</th>
				<th>Tiedostot</th>
			</tr>
			<tr>
				<td>
					<ul id="ecmUsers">
						<li>	
							<span><a href="${pageContext.request.contextPath}/cpanel/users.do">N‰yt‰ kaikki k‰ytt‰j‰t</a></span>
						</li>
						<li>	
							<span><a href="${pageContext.request.contextPath}/cpanel/user/add.do">Lis‰‰ k‰ytt‰j‰</a></span>
						</li>
						<li>	
							<span><a href="${pageContext.request.contextPath}/cpanel/user/edit.do?username=${pageContext.request.remoteUser}">Muokkaa nykyist‰ k‰ytt‰j‰‰</a></span>
						</li>
					</ul>
				</td>				
				<td>
					<ul id="emcGroups">
						<li>
							<span><a href="#">N‰yt‰ kaikki k‰ytt‰j‰ryhm‰t</a></span>
						</li>
						<li>
							<span><a href="#">Lis‰‰ k‰ytt‰j‰ryhm‰</a></span>
						</li>
					</ul>
				</td>
				<td>
					<ul id="emcPages">
						<li>
							<span><a href="#">N‰yt‰ sivukartta</a></span>
						</li>
						<li>
							<span><a href="#">Muokkaa sivuston tyylitiedostoa</a></span>
						</li>
						<li>
							<span><a href="#">Lis‰‰ uusi sivupohja</a></span>
						</li>
						<li>
							<span><a href="#">Lis‰‰ uusi sivu</a></span>
						</li>
					</ul>
				</td>
				<td>
					<ul id="emcForms">
						<li>
							<span><a href="#">N‰yt‰ kaikki lomakkeet</a></span>
						</li>
						<li>
							<span><a href="#">Lis‰‰ uusi lomake</a></span>
						</li>
					</ul>
				</td>
				<td>
					<ul id="emcFiles">
						<li>
							<span><a href="${pageContext.request.contextPath}/cpanel/files/list.do">Kaikki tiedostot</a></span>
						</li>
						<li>
							<span><a href="${pageContext.request.contextPath}/cpanel/files/add.do">Luo uusi tiedosto</a></span>
						</li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</div>
