<%-- 
-- File Name: create
-- Description: This page displays create Page of Code Master
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Other Parameter</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="javascript:void(0)"><asset:image src="add.png" /></a>
							<a href="#" id="custLocId" data-toggle="modal" data-target="#custLocatModal" data-whatever="@fat"><asset:image src="view.png" /></a>
							<a href="#" onclick="onEditClick();"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a> 
							<g:link action="index"><asset:image src="clear.png" /></g:link> 
							<g:link action="dashboard" controller="company"><asset:image src="exit.png" /></g:link>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Other Parameter </h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstCodeInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstCodeInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form url="[resource:mstCodeInstance, action:'save']" >
								<fieldset class="form">
									<g:render template="form"/>
								</fieldset>
								<fieldset class="buttons">
									<g:submitButton name="create" class="active_btn" value="${message(code: 'default.button.create.label', default: 'Create')}" />
								</fieldset>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>