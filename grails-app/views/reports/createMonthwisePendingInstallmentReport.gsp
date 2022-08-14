<%-- 
     -- File Name: createMonthwisePendingInstallmentReport
     -- Description: it is used to display Check Clearance Report Data
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Monthwise Pending Installment Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>Monthwise Pending Installment Report</h1>
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
											<table class="table">
												<thead>
													<tr>
														<td><strong>Customer Id</strong></td>
														<td><strong>Customer Name</strong></td>						
														<td><strong>Scheme</strong></td>
														<td><strong>Start From</strong></td>
														<td><strong>Period</strong></td>
														<td><strong>Inst Amt</strong></td>
														<td><strong>Pending Inst.</strong></td>
														<td><strong>Phone No.</strong></td>
														<td><strong>Employee Name</strong></td>
													</tr>
												</thead>
													<tbody>			
													<g:each in="${monthwisePendingInstallmentVal}" status="i" var="monthwisePendingInstallmentValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${monthwisePendingInstallmentValInst?.CUST_ID}</td>
															<td></td>
															<td>${monthwisePendingInstallmentValInst?.SCHEME_NO}</td>
															<td>${monthwisePendingInstallmentValInst?.SCHEME_START_DATE}</td>
															<td>${monthwisePendingInstallmentValInst?.TOT_INSTALL_AMT}</td>
															<td>${monthwisePendingInstallmentValInst?.PENDING_INSTALLMENT}</td>
															<td>${monthwisePendingInstallmentValInst?.MOBILE}</td>
															<td>${monthwisePendingInstallmentValInst?.EMPLOYEE_NAME}</td>
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