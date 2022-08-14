<%-- 
-- File Name: index
-- Description: This page displays index Page of Registration Details
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
	<title>Registration Details</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<g:link action="createDiscount"><asset:image src="add.png" /></g:link>
							<a href="#" id="scheme" data-toggle="modal" data-target="#viewPopup" data-whatever="@fat"><asset:image src="view.png" /></a>
							<a href="#" id="scheme" data-toggle="modal" data-target="#editPopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<a href="${request.getContextPath()}/"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-empMst" class="content scaffold-create">
							<h1>Registration Details</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<!-- Table -->
										<div class="col-md-8 page-header">
											<table class="table">
												<thead>
													<tr>
														<g:sortableColumn property="regNo" title="${message(code: 'regDt.regNo.label', default: 'Reg No')}" />
														<g:sortableColumn property="custId" title="${message(code: 'regDt.custId.label', default: 'Cust Id')}" />
														<g:sortableColumn property="custRefno" title="${message(code: 'regDt.custRefno.label', default: 'Cust Refno')}" />
														<g:sortableColumn property="schemeNo" title="${message(code: 'regDt.schemeNo.label', default: 'Scheme No')}" />
														<g:sortableColumn property="enrollmentStartDate" title="${message(code: 'regDt.enrollmentStartDate.label', default: 'Enrollment Start Date')}" />
														<g:sortableColumn property="enrollmentEndDate" title="${message(code: 'regDt.enrollmentEndDate.label', default: 'Enrollment End Date')}" />
													</tr>
												</thead>
												<tbody>
													<g:each in="${regDtInstanceList}" status="i" var="regDtInstance">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td><g:link action="show" id="${regDtInstance.regNo}">${fieldValue(bean: regDtInstance, field: "regNo")}</g:link></td>
															<td>${fieldValue(bean: regDtInstance, field: "custId")}</td>
															<td>${fieldValue(bean: regDtInstance, field: "custRefno")}</td>
															<td>${fieldValue(bean: regDtInstance, field: "schemeNo")}</td>
															<td>${fieldValue(bean: regDtInstance, field: "enrollmentStartDate")}</td>
															<td>${fieldValue(bean: regDtInstance, field: "enrollmentEndDate")}</td>
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
	</div>
</body>
</html>