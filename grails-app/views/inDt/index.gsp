<%-- 
-- File Name: index
-- Description: This page displays index Page of Inward Details
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@ page import="com.tbz.franchisee.InDt" %>
<html>
<head>
	<meta name='layout' content='mainerphq'/>
	<title>Inward Details</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<g:link action="create"><asset:image src="add.png" /></g:link>
							<a href="#" id="scheme" data-toggle="modal" data-target="#viewPopup" data-whatever="@fat"><asset:image src="view.png" /></a>
							<a href="#" id="scheme" data-toggle="modal" data-target="#editPopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<a href="${request.getContextPath()}/"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-inDt" class="content scaffold-create">
							<h1>Inward Details</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${inDtInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${inDtInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
									<div class="col-md-12">
										<div class="panel panel-default">
											<table class="table">
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
													<g:each in="${inDtInstanceList}" status="i" var="inDtInstance">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td><g:link action="show" id="${inDtInstance.inwardId}">${fieldValue(bean: inDtInstance, field: "inwardId")}</g:link></td>
															<td>${fieldValue(bean: inDtInstance, field: "inwardDate")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "amount")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "payMode")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "vcCheque")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "dtCreationDate")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "vcCardType")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "drawnOn")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "drawnOnAdd")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "formonth")}</td>
															<td>${fieldValue(bean: inDtInstance, field: "defaultGoldRate")}</td>
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>