<%-- 
-- File Name: edit
-- Description: This page displays edit Page of Employee
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@ page import="com.tbz.franchisee.EmpMst" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Employee Master</title>
</head>
<body>
	<a href="#edit-empMst" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
	</div>
	<div id="edit-empMst" class="content scaffold-edit" role="main">
		<h1>Employee Master</h1>
		<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
		<g:hasErrors bean="${empMstInstance}">
		<ul class="alert alert-danger" role="alert">
			<g:eachError bean="${empMstInstance}" var="error">
			<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
		</g:hasErrors>
		<g:form url="[resource:empMstInstance, action:'update']" method="PUT" >
			<g:hiddenField name="version" value="${empMstInstance?.version}" />
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