<%-- 
-- File Name: index
-- Description: Shows index Page of Location Master
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 18/04/2016		Sachin				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Location Master</title>
	<script type="text/javascript">
		$(document).ready(function(){	
			$("#clearImg").attr("disabled" , 'disabled');
			$("#deleteImg").attr("disabled" , 'disabled');
			$("#exitImg").attr("disabled" , 'disabled');
			$("#saveImg").attr("disabled" , 'disabled');
		});
	</script>	
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-812page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<g:link action="create"><asset:image src="add.png" /></g:link> 
							<a href="javascript:void(0)"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a> 
							<a href="#" onclick="clearMtlItemLocations()"><asset:image src="clear.png" /></a>
							<g:link action="mainModule" controller="company"><asset:image src="exit.png" /></g:link> 
						</div>
						<div id="create-mtlItemLocations" class="content scaffold-create">
							<h1>Location Master</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mtlItemLocationsInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mtlItemLocationsInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message	error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="mtlItemLocationsDetailsForm" id="mtlItemLocationsDetailsForm">
								<div class="content-bg pull-left">
									<div class="col-md-12">
										<fieldset class="form">
											<g:render template="form" />
										</fieldset>
									</div>
								</div>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>