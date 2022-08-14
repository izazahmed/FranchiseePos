<%-- 
-- File Name: createReport
-- Description: This page displays Report Page of Inward Details
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
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-inDt" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="report">Create Report</g:link></li>
		</ul>
	</div>
	<div id="list-inDt" class="content scaffold-list" role="main">
		<h1><g:message code="default.list.label" args="[entityName]" /></h1>
		<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
		<table width="100%" class="table">
			<thead>
				<tr>
					<g:sortableColumn property="inwardId" title="${message(code: 'inDt.inwardId.label', default: 'InWard Id')}" />
					<g:sortableColumn property="inwardDate" title="${message(code: 'inDt.inwardDate.label', default: 'Inward Date')}" />
					<g:sortableColumn property="amount" title="${message(code: 'inDt.amount.label', default: 'Amount')}" />
					<g:sortableColumn property="payMode" title="${message(code: 'inDt.payMode.label', default: 'Pay Mode')}" />
					<g:sortableColumn property="vcCheque" title="${message(code: 'inDt.vcCheque.label', default: 'Chq No./CC No.')}" />
					<g:sortableColumn property="dtCreationDate" title="${message(code: 'inDt.dtCreationDate.label', default: 'Pay Date')}" />
					<g:sortableColumn property="vcCardType" title="${message(code: 'inDt.vcCardType.label', default: 'Credit Card')}" />
					<g:sortableColumn property="drawnOn" title="${message(code: 'inDt.drawnOn.label', default: 'Drawn On')}" />
					<g:sortableColumn property="drawnOnAdd" title="${message(code: 'inDt.drawnOnAdd.label', default: 'Drawn on Address/Appr.no')}" />
					<g:sortableColumn property="formonth" title="${message(code: 'inDt.formonth.label', default: 'For Month')}" />
					<g:sortableColumn property="defaultGoldRate" title="${message(code: 'inDt.defaultGoldRate.label', default: 'Gold Rate')}" />
				</tr>
			</thead>
			<tbody>
				<g:each in="${inDtResult}" status="i" var="inDtInst">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>${inDtInst?.inwardId}</td>
					<td>${inDtInst?.inwardDate}</td>
					<td>${inDtInst?.amount}</td>
					<td>${inDtInst?.payMode}</td>
					<td>${inDtInst?.vcCheque}</td>
					<td>${inDtInst?.dtCreationDate}</td>
					<td>${inDtInst?.vcCardType}</td>
					<td>${inDtInst?.drawnOn}</td>
					<td>${inDtInst?.drawnOnAdd}</td>
					<td>${inDtInst?.formonth}</td>
					<td>${inDtInst?.defaultGoldRate}</td>
				</tr>
				</g:each>
			</tbody>
		</table>			
	</div>
</body>
</html>