<%-- 
-- File Name: create
-- Description: This page displays create Page of Jan Se Jama
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
	<title>Jan Se Jama</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="javascript:void(0)"><asset:image src="add.png" /></a>
							<a href="#" id="scheme" data-toggle="modal" data-target="#schemePopup" data-whatever="@fat"><asset:image src="view.png" /></a>
							<a href="#" id="scheme" data-toggle="modal" data-target="#schemePopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="#" onclick="saveDiscountForm()"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="#" onclick="clearDiscountForm()"><asset:image src="clear.png" /></a> 
							<a href="${request.getContextPath()}/"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-xxposCashReceipts" class="content scaffold-create">
							<h1>Jan Se Jama</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${xxposCashReceiptsInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${xxposCashReceiptsInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form url="[resource:xxposCashReceiptsInstance, action:'save']">
								<fieldset class="form">
									<g:render template="form" />
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