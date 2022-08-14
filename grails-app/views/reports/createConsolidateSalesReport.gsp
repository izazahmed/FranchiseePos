\<%-- 
     -- File Name: createAdvTransferReport
     -- Description: it is used to display ConsolidateSales Report Data
     -- Author(s): CTE 
     -- Date: 19/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 19/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Consolidate Sales Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Consolidate Sales Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<tr>
												<td><strong>From Date :</strong>${mainHead.get("fromDate")}</td>
												<td><strong>To Date :</strong>${mainHead.get("toDate")}</td>
												<td><strong>For Item :</strong>${mainHead.get("item")}</td>
												<td><strong>For Item :</strong>${mainHead.get("item")}</td>
											</tr>
										</table>
										<table class="table">
											<thead>
												<tr>
													<td><strong>Category :</strong></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>	
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><strong>Branch</strong></td>
													<td><strong>Gross Wtt</strong></td>					
													<td><strong>Nett Wtt</strong></td>
													<td><strong>Carrat Wtt</strong></td>
													<td><strong>Amount</strong></td>										
												</tr>
											</thead>
											<tbody>			
												<g:if test="${consolidateSales}">
													<g:each in="${consolidateSales}" status="i" var="consolidateSalesInst">
														<tr>
															<td>${consolidateSalesInst?.Category}</td>
														</tr>	
														<tr>
															<td>${consolidateSalesInst?.Branch_Name}</td>
															<td>${consolidateSalesInst?.Gross_Wtt}</td>	
															<td>${consolidateSalesInst?.Nett_Wtt}</td>
															<td>${consolidateSalesInst?.Carrat_Wtt}</td>						
															<td>${consolidateSalesInst?.Bill_Amount}</td>																													
														</tr>			
														<tr>
															<td colspan="1">Total :</td>
															<td>${consolidateSalesInst?.Gross_Wtt}</td>
															<td>${consolidateSalesInst?.Nett_Wtt}</td>			
															<td>${consolidateSalesInst?.Carrat_Wtt}</td>
															<td>${consolidateSalesInst?.Bill_Amount}</td>
														</tr>													
													</g:each>
													</g:if>
													<g:else>
														<td colspan="5" align="center"><div class="error">No Data Found</div></td>
													</g:else>						
													<tr>
													<g:if test="${consolidateSales}">
														<td colspan="1"><strong>Total :</strong></td>
														<td>${consolidateSales?.grossTotAmt}</td>
														<td>${consolidateSales?.nettTotAmt}</td>	
														<td>${consolidateSales?.carratTotAmt}</td>
														<td>${consolidateSales?.billTotAmt}</td>	
													</g:if>
													<g:else>
														<td colspan="5" align="center"><div class="error">No Data Found</div></td>
													</g:else>																				
												</tr>																
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
