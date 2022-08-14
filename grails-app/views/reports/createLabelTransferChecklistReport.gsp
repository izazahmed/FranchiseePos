<%-- 
     -- File Name: createLabelTransferChecklistReport
     -- Description: it is used to display LabelTransferChecklistReport Data
     -- Author(s): CTE 
     -- Date: 23/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 23/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
	<head>
		<meta name='layout' content='mainerphq' />
<title>Label Transfer Checklist Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<!-- Table -->
									<div class="col-md-8 page-header">
									<strong>${mainHead.get("coName")}</strong><br>
									<strong>${lblTrChkAddress[0]}</strong>
									<h1><center>Franchisee Label Transfer</center></h1>
										<div class="content-bg pull-left">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="col-md-8 page-header">
														<table class="table">	
															<tr>
																<td><strong>From Date :</strong>${mainHead.get("fromDate")}</td>
																<td><strong>To Date :</strong>${mainHead.get("toDate")}</td>
																<td><strong>For Category :</strong>${mainHead.get("cat")}</td>
																<td><strong>Print Time :</strong>${mainHead.get("today")}</td>
															</tr>
														</table>
													</div>
												</div>
											</div>
										</div>
										<div class="content-bg pull-left">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="col-md-8 page-header">
														<table class="table">
															<thead>
																<tr>
																	<td><strong>Bill Date</strong></td>
																	<td><strong>Bill Number</strong></td>					
																	<td><strong>From Locator</strong></td>
																	<td><strong>To Locator</strong></td>
																	<td><strong>Lot Number</strong></td>														
																	<td><strong>Item Code</strong></td>
																	<td><strong>Purity</strong></td>					
																	<td><strong>Nang</strong></td>
																	<td><strong>Pcs</strong></td>
																	<td><strong>Gross Wt</strong></td>													
																	<td><strong>Net Wt</strong></td>
																	<td><strong>Carrat Wt</strong></td>									
																</tr>
															</thead>
															<tbody>			
															
																<g:each in="${labelTrChLst}" status="i" var="labelTrChLstInst">
																	<tr>
																		<td>${labelTrChLstInst?.voucher_date}</td>
																		<td>${labelTrChLstInst?.voucher_number}</td>	
																		<td>${labelTrChLstInst?.from_locator_name}</td>
																		<td>${labelTrChLstInst?.to_locator_name}</td>
																		<td>${labelTrChLstInst?.lot_number}</td>						
																		<td>${labelTrChLstInst?.item_category}</td>	
																		<td>${labelTrChLstInst?.Purity}</td>
																		<td>${labelTrChLstInst?.nang}</td>	
																		<td>${labelTrChLstInst?.pieces}</td>
																		<td>${labelTrChLstInst?.gross_weight}</td>																								
																		<td>${labelTrChLstInst?.net_weight}</td>
																		<td>${labelTrChLstInst?.CARAT_WEIGHT}</td>																														
																	</tr>															
																</g:each>				
																<g:if test="${labelTrChLst}">		
																	<tr>
																		<td colspan="4"><strong>Total :</strong></td>
																		<td>${labelTrChLst?.sum { it.nang } }</td>
																		<td>${labelTrChLst?.sum { it.pieces } }</td>	
																		<td>${labelTrChLst?.sum { it.gross_weight } }</td>
																		<td>${labelTrChLst?.sum { it.net_weight } }</td>
																		<td>${labelTrChLst?.sum { it.CARAT_WEIGHT } }</td>	
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>																					
																	</tr>					
																</g:if>
																<g:else>
																	<div style="font-size: 16px; color: red">No Data Found</div>
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
		</div>
	</div>
</div>
</div>
</body>
</html>
