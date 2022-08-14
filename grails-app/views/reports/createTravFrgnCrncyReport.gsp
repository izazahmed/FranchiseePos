<%-- 
     -- File Name: createTravFrgnCrncyReport
     -- Description: it is used to display TravFrgnCrncyReport Data
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
<title>Traveller Foreign Currency Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Traveller Foreign Currency Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<strong>${mainHead.get("coName")}</strong><br>	
								<strong>${traFrgncAddress[0]}</strong>
								<h1 align="center">Foreign Currency/Traveller's Cheque Report for</h1><td><strong>Print Time :</strong>${mainHead.get("today")}</td>
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Inv No</strong></td>
													<td></td>
													<td><strong>Party Name</strong></td>
													<td><strong>Exch. Amt</strong></td>
													<td><strong>Rate</strong></td>
													<td><strong>Rupees</strong></td>
												</tr>
											</thead>
											<tbody>			
												<g:if test="${travelFrgnCrncyMap?.resultListOne}">
													<g:each in="${travelFrgnCrncyMap?.resultListOne}" status="i" var="travelFrgnCrncyMapInstOne">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${travelFrgnCrncyMapInstOne?.cashInvNo}</td>
															<td>Travellers Cheque:${travelFrgnCrncyMapInstOne?.funCashCurrency}</td>
															<td>${travelFrgnCrncyMapInstOne?.cashPartyName}</td>
															<td>${travelFrgnCrncyMapInstOne?.cashExAmt}</td>
															<td>${travelFrgnCrncyMapInstOne?.cashRate}</td>
															<td>${travelFrgnCrncyMapInstOne?.cashRupees}</td>													
														</tr>					
													</g:each>	
												</g:if>
												<g:else>
													<tr>
														<td colspan="6" align="center"><div class="error">No Data Found</div></td>	
													</tr>
												</g:else>																								
											</tbody>
										</table>
										<br/><br/>
										<div class="panel panel-default">
											<table class="table">
												<tbody>							
													<g:if test="${travelFrgnCrncyMap?.resultListTwo}">		
														<g:each in="${travelFrgnCrncyMap?.resultListTwo}" status="i" var="travelFrgnCrncyMapInstTwo">																	
															<tr>
																<td>${travelFrgnCrncyMapInstTwo?.chqInvNo}</td>
																<td>Travellers Cheque:${travelFrgnCrncyMapInstTwo?.funChqCurrency}</td>
																<td>${travelFrgnCrncyMapInstTwo?.chqPartyName}</td>
																<td>${travelFrgnCrncyMapInstTwo?.chqExAmt}</td>
																<td>${travelFrgnCrncyMapInstTwo?.chqRate}</td>
																<td>${travelFrgnCrncyMapInstTwo?.chqRupees}</td>																			
															</tr>																		
														</g:each>	
													</g:if>
													<g:else>
														<td colspan="5" align="center"><div class="error">No Data Found</div></td>	
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
	</div>			
</body>
</html>
