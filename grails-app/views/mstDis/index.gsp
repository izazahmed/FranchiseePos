<%-- 
-- File Name: index
-- Description: This page displays list og Discount present in DB
-- Author(s): CTE. 
-- Date: 05/06/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
--  05/06/2016		Rohil				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Discount Master</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-ecsDateEntry" class="content scaffold-create">
						<div class="row">
							<h1>Discount Master</h1>
							<input type="button" class="active_btn" value="Refresh" onClick="window.location.reload()">
						</div>
						<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<g:sortableColumn property="vcEmpCode" title="${message(code: 'mstDis.vcEmpCode.label', default: 'Employee Code')}" />
													<g:sortableColumn property="vcDisCode" title="${message(code: 'mstDis.vcDisCode.label', default: 'Discount Id')}" />
													<g:sortableColumn property="vcEmpName" title="${message(code: 'mstDis.vcEmpName.label', default: 'Employee Name')}" />
												</tr>
											</thead>
											<tbody>
											<g:each in="${mstDisInstanceList}" status="i" var="mstDisInstance">
												<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
													<td><g:link action="show" id="${mstDisInstance.id}">${fieldValue(bean: mstDisInstance, field: "vcEmpCode")}</g:link></td>
													<td>${fieldValue(bean: mstDisInstance, field: "vcDisCode")}</td>
													<td>${fieldValue(bean: mstDisInstance, field: "vcEmpName")}</td>
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