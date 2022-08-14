<!DOCTYPE html>
<html>
<head>
	<title>Scheme Master</title>
</head>
<body>
	<div id="page-body" role="main">
		<div id="controller-list" role="navigation">
			<ul>
				<li><a href="${request.contextPath}/mstApprovalAuth" >Scheme Master</a></li> 
				<li><a href="${request.contextPath}/empMst" >Employee Master</a></li>
				<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
					<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
				</g:each>
			</ul>
		</div>
	</div>
</body>
</html>