<%-- 
-- File Name: create
-- Description: This page displays create Page of Region Master
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>City Master</title>
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
							<a href="javascript:void(0)" onclick="onEditClick();"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a> 
							<g:link action="index"><asset:image src="clear.png" /></g:link> 
							<a href="javascript:void(0)"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstRegion" class="content scaffold-create">
							<h1>City Master</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstRegionInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstRegionInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form url="[resource:mstRegionInstance, action:'save']" >
								<fieldset class="form">
									<g:render template="form"/>
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