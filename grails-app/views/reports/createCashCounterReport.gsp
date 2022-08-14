<%-- 
     -- File Name: createCashCounterReport
     -- Description: it is used to display CashCounterReport Data
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
<title>Cash Counter Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Cash Counter Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Sales Bill</strong></td>
													<td></td>
													<td></td>
													<td></td>
													<td><strong>Sales return</strong></td>	
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>	
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><strong>Category</strong></td>
													<td><strong>Gr.Wt.</strong></td>														
													<td><strong>Nt.Wt.</strong></td>													
													<td><strong>Amount</strong></td>	
													<td><strong>Gr.Wt.</strong></td>														
													<td><strong>Nt.Wt.</strong></td>													
													<td><strong>Amount</strong></td>	
													<td><strong>Tot GWt.</strong></td>
													<td><strong>Tot NWt.</strong></td>		
													<td><strong>Total</strong></td>	
												</tr>
											</thead>
											<tbody>			
												<g:each in="${cashCounterReportList}" status="i" var="cashCounterReportListInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${cashCounterReportListInst?.VC_CATEGORY}</td>	
														<td>${cashCounterReportListInst?.NU_GROSS_WTT}</td>	
														<td>${cashCounterReportListInst?.NU_NETT_WTT}</td>																																								
														<td>${cashCounterReportListInst?.NU_AMOUNT}</td>	
														<td>${cashCounterReportListInst?.GWT}</td>	
														<td>${cashCounterReportListInst?.NWT}</td>																																								
														<td>${cashCounterReportListInst?.AMT}</td>		
														<td>${cashCounterReportListInst?.totGrWtLst}</td>	
														<td>${cashCounterReportListInst?.totNtLst}</td>																																								
														<td>${cashCounterReportListInst?.totAmtLst}</td>																											
													</tr>					
												</g:each>	
												<g:if test="${cashCounterReportList}">	
												<tr>												
													<td colspan="1"><strong>Total :</strong></td>
													<td>${cashCounterReportList.sum { it.NU_GROSS_WTT } }</td> 
													<td>${cashCounterReportList.sum { it.NU_NETT_WTT } }</td>	
													<td>${cashCounterReportList.sum { it.NU_AMOUNT } }</td> 
													<td>${cashCounterReportList.sum { it.GWT } }</td>
													<td>${cashCounterReportList.sum { it.NWT } }</td> 
													<td>${cashCounterReportList.sum { it.AMT } }</td>
													<td>${cashCounterReportList.sum { it.KPSTG } }</td> 
													<td>${cashCounterReportList.sum { it.KPUNSTG } }</td>	
													<td>${cashCounterReportList.sum { it.cpchqclr } }</td>																			
												</tr>	
												</g:if>
												<g:else>
													<td colspan="11" align="center"><div class="error">No Data Found</div></td>
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
