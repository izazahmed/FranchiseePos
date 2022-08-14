<%-- 
     -- File Name: createMiscellaneousRecieptsReport
     -- Description: it is used to display MiscellaneousRecieptsReport Data
     -- Author(s): CTE 
     -- Date: 05/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 05/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
<title>Miscellaneous Reciepts Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Miscellaneous Reciepts Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Vou No</strong></td>
													<td><strong>Voucher Date</strong></td>														
													<td><strong>Amount</strong></td>													
													<td><strong>Narration</strong></td>													
													<td><strong>Cat</strong></td>													
													<td><strong>Payment</strong></td>													
													<td><strong>Voucher Agst.Receipt</strong></td>								
												</tr>
											</thead>
											<tbody>			
												<g:each in	="${miscellaneousRecieptsReportList}" status="i" var="miscellaneousRecieptsReportListInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${miscellaneousRecieptsReportListInst?.vc_voucher_no}</td>	
														<td>${miscellaneousRecieptsReportListInst?.dt_voucher_date}</td>	
														<td>${miscellaneousRecieptsReportListInst?.nu_amount}</td>																																								
														<td>${miscellaneousRecieptsReportListInst?.vc_narration}</td>																												
														<td>${miscellaneousRecieptsReportListInst?.vc_category}</td>															
														<td>${miscellaneousRecieptsReportListInst?.Pay_type}</td>																										
														<td>${miscellaneousRecieptsReportListInst?.voucher_no}</td>																											
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
