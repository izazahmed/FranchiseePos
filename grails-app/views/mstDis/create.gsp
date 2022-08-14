<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Discount Master</title>
</head>
<body>
	<a href="#create-mstDis" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
		</ul>
	</div>
	<div id="create-mstDis" class="content scaffold-create" role="main">
		<h1>Discount Master</h1>
		<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
		<g:hasErrors bean="${mstDisInstance}">
		<ul class="alert alert-danger" role="alert">
			<g:eachError bean="${mstDisInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
		</g:hasErrors>
		<g:form url="[resource:mstDisInstance, action:'save']" >
			<fieldset class="form">
				<g:render template="form"/>
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="create" class="active_btn" value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>