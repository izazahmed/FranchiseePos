<%-- 
     -- File Name: createChequeToBeDepositReport
     -- Description: it is used to display ChequeToBeDepositReport Data
     -- Author(s): CTE 
     -- Date: 04/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 04/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Cheque tobe Deposit Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Cheque tobe Deposit Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Customer Name</strong></td>
													<td><strong>Voucher No</strong></td>
													<td><strong>Voucher Date</strong></td>
													<td><strong>Chq No</strong></td>
													<td><strong>Chq Date</strong></td>
													<td><strong>Amount</strong></td>
													<td><strong>Tran Type</strong></td>
													<td><strong>KP ID</strong></td>
													<td><strong>Customer Bank</strong></td>							
												</tr>
											</thead>
											<tbody>			
											<g:if test="${chequeToBeDepositList}">	
												<g:each in="${chequeToBeDepositList}" status="i" var="chequeToBeDepositListInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${chequeToBeDepositListInst?.customer_name}</td>
														<td>${chequeToBeDepositListInst?.vc_voucher_no}</td>	
														<td>${chequeToBeDepositListInst?.dt_voucher_date}</td>
														<td>${chequeToBeDepositListInst?.vc_chq_no}</td>	
														<td>${chequeToBeDepositListInst?.dt_chq_date}</td>
														<td>${chequeToBeDepositListInst?.nu_amount}</td>	
														<td>${chequeToBeDepositListInst?.vc_tran_type}</td>
														<td>${chequeToBeDepositListInst?.vc_cust_id}</td>	
														<td>${chequeToBeDepositListInst?.vc_card_type}</td>													
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
