<%-- 
-- File Name: list
-- Description: This page displays list Page of Employee
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
	<a href="#list-empMst" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="add"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
	</div>
	<div id="list-empMst" class="content scaffold-list" role="main">
		<h1>Employee Master</h1>
		<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
		<table>
			<thead>
				<tr>
					<g:sortableColumn property="empId" title="${message(code: 'empMst.empId.label', default: 'Emp Id')}" />
					<g:sortableColumn property="vcSalesId" title="${message(code: 'empMst.vcSalesId.label', default: 'Sales Id')}" />
					<g:sortableColumn property="empName" title="${message(code: 'empMst.empName.label', default: 'Emp Name')}" />
					<g:sortableColumn property="chActive" title="${message(code: 'empMst.chActive.label', default: 'Active')}" />
					<g:sortableColumn property="vcEmpType" title="${message(code: 'empMst.vcEmpType.label', default: 'Emp Type')}" />
					<g:sortableColumn property="vcField1" title="${message(code: 'empMst.vcField1.label', default: 'Category')}" />
					<g:sortableColumn property="empName" title="${message(code: 'empMst.vcDeptDesc.label', default: 'Department Description')}" />
					<g:sortableColumn property="vcDesigCode" title="${message(code: 'empMst.vcDesigCode.label', default: 'Designation Code')}" />
					<g:sortableColumn property="vcDesignation" title="${message(code: 'empMst.vcDesignation.label', default: 'Designation')}" />
				</tr>
			</thead>
				<tbody>
					<g:each in="${empMstInstanceList}" status="i" var="empMstInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><g:link action="show" id="${empMstInstance.empId}">${fieldValue(bean: empMstInstance, field: "empId")}</g:link></td>
							<td>${fieldValue(bean: empMstInstance, field: "vcSalesId")}</td>
							<td>${fieldValue(bean: empMstInstance, field: "empName")}</td>
							<td>${fieldValue(bean: empMstInstance, field: "chActive")}</td>
							<td>${fieldValue(bean: empMstInstance, field: "vcEmpType")}</td>
							<td>${fieldValue(bean: empMstInstance, field: "vcField1")}</td>
							<td>${fieldValue(bean: empMstInstance, field: "vcDeptDesc")}</td>
							<td>${fieldValue(bean: empMstInstance, field: "vcDesigCode")}</td>
							<td>${fieldValue(bean: empMstInstance, field: "vcDesignation")}</td>
						</tr>
					</g:each>
				</tbody>
		</table>
	</div>
</body>
</html>