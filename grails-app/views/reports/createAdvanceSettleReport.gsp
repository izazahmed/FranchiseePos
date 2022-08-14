<%-- 
     -- File Name: createAdvanceSettleReport
     -- Description: it is used to display AdvanceSettleReport Data
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
<title>Advance Settle Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Advance Settle Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Sr No</strong></td>
													<td><strong>Order No</strong></td>														
													<td><strong>Receipt Date</strong></td>													
													<td><strong>Settle Date</strong></td>													
													<td><strong>Name & Address</strong></td>													
													<td><strong>Settled Amount</strong></td>													
													<td><strong>UserID</strong></td>													
													<td><strong>MacID</strong></td>													
													<td><strong>Inv No</strong></td>													
													<td><strong>Type of Payment</strong></td>													
													<td><strong>Nett Wett</strong></td>													
													<td><strong>A/c Ref</strong></td>																											
													<td><strong>Chq No.</strong></td>
													<td><strong>BankInit.</strong></td>							
												</tr>
											</thead>
											<tbody>			
												<g:each in="${advanceSettleList}" status="i" var="advanceSettleListInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>i</td>
														<td>${advanceSettleListInst?.vc_adv_ord_no}</td>	
														<td>${advanceSettleListInst?.dt_adv_ord_date}</td>	
														<td>${advanceSettleListInst?.inv_date}</td>																																								
														<td>${advanceSettleListInst?.vc_customer_name},${advanceSettleListInst?.vc_address}</td>																												
														<td>${advanceSettleListInst?.nu_sett_amt}</td>															
														<td>${advanceSettleListInst?.nu_mc_id}</td>																										
														<td>${advanceSettleListInst?.nu_mc_no}</td>														
														<td>${advanceSettleListInst?.inv_no}</td>														
														<td>${advanceSettleListInst?.pay_mode}</td>														
														<td>${advanceSettleListInst?.nu_nett_wtt}</td>
														<td>${advanceSettleListInst?.vc_settle_chq_no}</td>
														<td>${advanceSettleListInst?.nu_settle_bank_code}</td>																											
													</tr>					
												</g:each>			
												<g:if test="${AccRefNo}">	
													<g:each in="${AccRefNo}" status="i" var="AccRefNoInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>Count ${AccRefNoInst?.CUSTOMER_NUMBER}</td>
														</tr>
													</g:each>	
												</g:if>
												<g:if test="${advanceSettleListInst?.nu_sett_amt}">
													<tr>
														<td colspan="6"><strong>Total :</strong></td> <td>${advanceSettleListInst.sum { it.nu_sett_amt } }</td>																				
													</tr>	
												</g:if>																				
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
