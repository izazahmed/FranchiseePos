<%-- 
     -- File Name: createKpPayModeWiseDetailReport
     -- Description: it is used to display Check Clearance Report Data
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>KP PayMode Wise Detail Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>KP PayMode Wise Detail Report</h1>
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
														<td><strong>Inward Id</strong></td>
														<td><strong>Cust Id</strong></td>
														<td><strong>Cust Name</strong></td>
														<td><strong>Registration Id</strong></td>
														<td><strong>Pay Mode</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Cheque Date</strong></td>
														<td><strong>Amount</strong></td>
													</tr>
												</thead>
												<tbody>			
													<g:each in="${kpPayModeWiseDetailList}" status="i" var="kpPayModeWiseDetailValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${kpPayModeWiseDetailValInst?.INWARD_ID}</td>	
															<td>${kpPayModeWiseDetailValInst?.CUST_ID}</td>
															<td>${kpPayModeWiseDetailValInst?.CUSTOMER_NAME}</td>
															<td>${kpPayModeWiseDetailValInst?.REG_NO}</td>
															<td>${kpPayModeWiseDetailValInst?.VC_PAY_MODE}</td>
															<td>${kpPayModeWiseDetailValInst?.VC_CHEQUE}</td>
															<td>${kpPayModeWiseDetailValInst?.PAY_DATE}</td>
															<td>${kpPayModeWiseDetailValInst?.AMT}</td>															
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