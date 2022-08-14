<%-- 
     -- File Name: createKpUnSettleReport
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
<title>KP UnSettle Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>KP UnSettle Report</h1>
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
														<td><strong>Cust Id</strong></td>						
														<td><strong>Period</strong></td>
														<td><strong>Scheme Close</strong></td>
														<td><strong>Cust Name</strong></td>
														<td><strong>Resident Phone</strong></td>
														<td><strong>Mobile</strong></td>
														<td><strong>Last month</strong></td>
														<td><strong>Can. Settle</strong></td>
														<td><strong>Cust Ref. Amount</strong></td>
														<td><strong>Sp Amount</strong></td>
														<td><strong>Paid Amount</strong></td>
														<td><strong>Paid Amount</strong></td>
														<td><strong>Paid Installment</strong></td>
														<td><strong>ECS(Yes/No)</strong></td>
														<td><strong>Cheque Uncleared</strong></td>
														<td><strong>Enrollment Start Date</strong></td>
														<td><strong>Enrollemnt End Date</strong></td>
														<td><strong>Enrollemnt End Date</strong></td>
														<td><strong>Cat.</strong></td>
														<td><strong>Scheme No.</strong></td>
														<td><strong>Home Collection</strong></td>
													</tr>
												</thead>
												<tbody>			
													<g:each in="${kpUnSettleList}" status="i" var="KpUnSettleValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td nowrap="nowrap">${KpUnSettleValInst?.CUST_ID}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.NU_PERIOD}</td>	
															<td nowrap="nowrap">${KpUnSettleValInst?.SCHEME_CLOSE}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.CUSTOMER_NAME}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.RESIDENT_PHONE}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.MOBILE}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.LAST_MONTH}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.CAN_SETTLE}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.CUST_REF_AMOUNT}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.SP_AMOUNT}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.PAID_AMOUNT}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.PAID_INSTALLMENTS}</td>
															<td></td>
															<td nowrap="nowrap">${KpUnSettleValInst?.CHQ_UNCLEARED_AMT}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.ENROLLMENT_START_DATE}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.ENROLLMENT_END_DATE}</td>
															<td nowrap="nowrap">${KpUnSettleValInst?.SCHEME_NO}</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
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