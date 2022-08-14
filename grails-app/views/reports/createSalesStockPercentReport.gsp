<%-- 
     -- File Name: createSalesStockPercentReport
     -- Description: it is used to display SalesStockPercentReport Data
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
<title>Sales Stock/Percent Report</title>
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
											<strong>
												
											</strong><br> <strong>
												
											</strong>
											<h1>
												Sales Stock/Percent Report
											</h1>
											<div class="content-bg pull-left">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="col-md-8 page-header">
															<table class="table">
																<tr>
																	<td><strong>From Date :</strong>
																		</td>
																	<td><strong>To Date :</strong>
																		</td>
																	<td><strong>For Category :</strong>
																		</td>
																	<td><strong>Print Time :</strong>
																		</td>
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
																		<td><strong>Item</strong></td>
																		<td><strong>Pur</strong></td>

																		<td><strong>PCS</strong></td>
																		<td><strong>Net</strong></td>
																		<td><strong>Crt</strong></td>

																		<td><strong>PCS</strong></td>
																		<td><strong>Net</strong></td>
																		<td><strong>Crt</strong></td>

																		<td><strong>PCS</strong></td>
																		<td><strong>Net</strong></td>
																		<td><strong>Crt</strong></td>

																	</tr>
																</thead>
																<tbody>

																	<g:each in="${salesStockPercent}" status="i"
																		var="salesStockPercentInst">
																		<tr>
																			<td>
																				${salesStockPercentInst?.CATEGORY}
																			</td>
																			<td>
																				${salesStockPercentInst?.PURITY}
																			</td>
																			<td>
																				${salesStockPercentInst?.PCS}
																			</td>
																			<td>
																				${salesStockPercentInst?.NET}
																			</td>
																			<td>
																				${salesStockPercentInst?.CRT}
																			</td>
																			<td>
																				${salesStockPercentInst?.PCS}
																			</td>
																			<td>
																				${salesStockPercentInst?.NET}
																			</td>
																			<td>
																				${salesStockPercentInst?.CRT}
																			</td>
																			<td>
																				${salesStockPercentInst?.PCS}
																			</td>
																			<td>
																				${salesStockPercentInst?.NET}
																			</td>
																			<td>
																				${salesStockPercentInst?.CRT}
																			</td>																		
																		</tr>
																	</g:each>
																	<g:if test="${salesStockPercent}">
																		<tr>
																			<td colspan="4"><strong>Total :</strong></td>
																			<td>
																				${salesStockPercent?.sum { it.ISSUE_NANG } }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.ISSUE_PCS } }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.ISSUE_GROSSWT } }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.ISSUE_NETWT } }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.ISSUE_CARAT} }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.RECEIPT_NANG } }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.RECEIPT_PCS } }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.RECEIPT_GROSSWT } }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.RECEIPT_NETWT } }
																			</td>
																			<td>
																				${salesStockPercent?.sum { it.RECEIPT_CARATWT} }
																			</td>
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