<%-- 
-- File Name: report
-- Description: This page displays report Page of Inward Details
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@ page import="com.tbz.franchisee.InDt" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Inward Details</title>
</head>
<body>
	<a href="#list-inDt" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="report">Generate Report</g:link></li>
		</ul>
	</div>
	<div id="list-inDt" class="content scaffold-list" role="main">
		<h1>Inward Details</h1>
		<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
		<g:form action="createReport">
			<table width="100%">
				<thead>
					<tr>
						<td>From Date</td>
						<td><g:datePicker name="fromDate" precision="day"  value="${inDtInstance?.fromDate}" default="none" noSelection="['': '']" /></td>
					</tr>
					<tr>
						<td>End Date</td>
						<td><g:datePicker name="toDate" precision="day"  value="${inDtInstance?.toDate}" default="none" noSelection="['': '']" /></td>
					</tr>
					<tr>
						<td>Scheme No.</td>
						<g:set var="scheme" value="MstApp"></g:set>
						<td>
							<g:field name="schemeNo" type="number" value="${inDtInstance?.schemeNo}"/>
						</td>
					</tr>
					<tr>
						<td>Payment Mode</td>
						<td><g:field name="payMode" value="${inDtInstance?.payMode}"/></td>
					</tr>
				</thead>				
			</table>
			<g:submitButton name="Submit" value="Submit"/>
		</g:form>
	</div>
</body>
</html>