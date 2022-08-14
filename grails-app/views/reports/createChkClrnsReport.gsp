<%-- 
     -- File Name: createChkClrnsReport
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
<title>Cheque Clearance Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-empMst" class="content scaffold-list" role="main">
							<h1>Cheque Clearance Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Customer Name</strong></td>						
														<td><strong>Advance/Payment_Id</strong></td>
														<td><strong>Voucher Date</strong></td>
														<td><strong>Chq No</strong></td>
														<td><strong>Chq Date</strong></td>
														<td><strong>Amount</strong></td>
														<td><strong>Tran Type</strong></td>
														<td><strong>Clear Date</strong></td>
														<td><strong>Cust ID</strong></td>
													</tr>
												</thead>
													<tbody>			
														<g:if test="${chkClrnsVal}">
															<g:each in="${chkClrnsVal}" status="i" var="chkClrnsValInst">
																<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
																	<td>${chkClrnsValInst?.CUSTOMER_NAME}</td>
																	<td>${chkClrnsValInst?.VC_CHQ_NO}</td>	
																	<td>${chkClrnsValInst?.DT_VOUCHER_DATE}</td>
																	<td>${chkClrnsValInst?.VC_CHQ_NO}</td>
																	<td>${chkClrnsValInst?.DT_CHQ_DATE}</td>
																	<td>${chkClrnsValInst?.NU_AMOUNT}</td>
																	<td>${chkClrnsValInst?.VC_TRAN_TYPE}</td>
																	<td>${chkClrnsValInst?.DT_CLEAR_DATE}</td>
																	<td>${chkClrnsValInst?.VC_CUST_ID}</td>																
																</tr>					
															</g:each>		
														</g:if>
														<g:else>
															<td colspan="9" align="center"><div class="error">No Data Found</div></td>
														</g:else>																								
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