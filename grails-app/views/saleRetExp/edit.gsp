<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>SR Days Extend</title>
</head>
<body>
	<a href="#edit-saleRetExp" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
	</div>
	<div id="edit-saleRetExp" class="content scaffold-edit" role="main">
		<h1>SR Days Extend</h1>
		<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
		<g:hasErrors bean="${saleRetExpInstance}">
		<ul class="alert alert-danger" role="alert">
			<g:eachError bean="${saleRetExpInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
		</g:hasErrors>
		<g:form url="[resource:saleRetExpInstance, action:'update']" method="PUT" >
			<g:hiddenField name="version" value="${saleRetExpInstance?.version}" />
			<fieldset class="form">
				<g:render template="form"/>
			</fieldset>
			<fieldset class="buttons">
				<g:actionSubmit class="active_btn" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>