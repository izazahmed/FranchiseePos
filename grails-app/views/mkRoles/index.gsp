<%-- 
-- File Name: index
-- Description: This page displays index Page of role
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
	<title>Role Management</title>
	<script type="text/javascript">
		function returnToMainPage(){
			 if(confirm("Sure You Want To Exit?")){
			 	$("#spinner").show();
			 	document.getElementById("roleIndexForm").action ="${request.getContextPath()}/company/mainModule";
		    	document.getElementById("roleIndexForm").submit();
			 }
		 }
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<g:link action="create"><asset:image src="add.png" /></g:link>
							<a href="#" data-toggle="modal" data-target="#roleViewModal" onclick="viewPopUp('view');"><asset:image src="view.png" /></a>
							<a href="#" data-toggle="modal" data-target="#roleViewModal" onclick="viewPopUp('edit');"><asset:image src="edit.png" /></a>
						    <a href="javascript:void(0)" class="cursordefault"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="delete.png" /></a>
							<a href="/FranchiseePos" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
							<select id="compDD" name="compDD">
								<g:each in="${complist}" status="i" var="complistData">
									<option value="${complistData.key}">${complistData.value}</option>							
								</g:each>
							</select>
						</div>
						<div id="create-roles" class="content scaffold-create">
						<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
							<h1><g:message code="Roles" args="Roles" /></h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mkRoleInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mkRoleInstance}" var="error">
										<li
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
												error="${error}" />
										</li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="roleIndexForm" id="roleIndexForm">
							<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
							<g:hiddenField name="viewRoleId" id="viewRoleId" value="${viewRoleId}"/>
							<g:hiddenField name="viewModId" id="viewModId" />
							<g:hiddenField name="viewRoleName" id="viewRoleName" value="${viewRoleName}"/>
							<g:hiddenField name="viewBrId" id="viewBrId" value="${viewBrId}"/>
							<g:hiddenField name="viewModIdFrmIndex" id="viewModIdFrmIndex" value="${moduleId}"/>
							<g:hiddenField name="viewFirLvlMenuCode" id="viewFirLvlMenuCode"/>
							<g:hiddenField name="typeValue" id="typeValue"/>
								<fieldset class="form">
									<g:render template="form" />
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