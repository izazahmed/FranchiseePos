<%-- 
-- File Name: create
-- Description: This page displays create Page of Registration Details
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
	<title>Registration Details</title>
</head>
<body>
<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="#" id="addId"><asset:image src="add.png" /></a>
							<a href="#" id="custLocId" data-toggle="modal" data-target="#custLocatModal" data-whatever="@fat"><asset:image src="view.png" /></a>
							<a href="#" onclick="onEditClick();"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /> </a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /> </a> 
							<a href="javascript:void(0)"><g:link action="index"><asset:image src="clear.png" /></g:link></a> 
							<a href="javascript:void(0)"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Registration Details</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form url="[resource:regDtInstance, action:'save']" >
								<fieldset class="form">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<g:render template="form"/>
								</div>
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