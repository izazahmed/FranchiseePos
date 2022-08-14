<%-- 
     -- File Name: createEmployeePerformanceReport
     -- Description: it is used to display Check Clearance Report Data
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Sachin      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Employee Performance Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>Employee Performance Report</h1>
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
														<td><strong>Registration No.</strong></td>
														<td><strong>Registration Date</strong></td>
														<td><strong>Cust Id</strong></td>						
														<td><strong>Cust Name</strong></td>														
													</tr>
												</thead>
												<tbody>			
													<g:each in="${employeePerformanaceList}" status="i" var="employeePerformanaceValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${employeePerformanaceValInst?.REG_NO}</td>
															<td>${employeePerformanaceValInst?.REG_DATE}</td>
															<td>${employeePerformanaceValInst?.CUST_ID}</td>
															<td>${employeePerformanaceValInst?.NU_PERIOD}</td>
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