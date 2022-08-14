<%-- 
     -- File Name: createGs11OgReport
     -- Description: it is used to display Gs11OgReport Data
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
<title>GS 11 OG Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list">
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<!-- Table -->
										<div class="col-md-8 page-header">
											<strong> ${mainHead.get("coName")}</strong></br> 
											<strong> ${gs11Og[0]}</strong>
											<h1>
												Franchisee GS 11 OG
											</h1>
											<div class="content-bg pull-left">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="col-md-8 page-header">
															<table class="table">
																<tr>
																	<td><strong>From Date :</strong> ${mainHead.get("fromDate")}</td>
																	<td><strong>To Date :</strong> ${mainHead.get("toDate")}</td>
																	<td><strong>For Category :</strong> ${mainHead.get("cat")}</td>
																	<td><strong>Print Time :</strong> ${mainHead.get("today")}</td>
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
																		<td><strong>Voucher Date</strong></td>
																		<td><strong>Voucher Number</strong></td>
																		<td><strong>Transaction Type</strong></td>
																		<td><strong>Party Name</strong></td>
																		<td><strong>Purity</strong></td>

																		<td><strong>Nang</strong></td>
																		<td><strong>Pcs</strong></td>
																		<td><strong>Gross Wt.</strong></td>
																		<td><strong>Net Wt.</strong></td>
																		<td><strong>Carat Wt</strong></td>

																		<td><strong>Nang</strong></td>
																		<td><strong>Pcs</strong></td>
																		<td><strong>Gross Wt.</strong></td>
																		<td><strong>Net Wt.</strong></td>
																		<td><strong>Carat Wt</strong></td>

																		<td><strong>Amount</strong></td>

																	</tr>
																</thead>
																<tbody>

																	<g:each in="${gs11Og}" status="i" var="gs11OgInst">
																		<tr>
																			<td>
																				${gs11OgInst?.VOUCHER_DATE}
																			</td>
																			<td>
																				${gs11OgInst?.VC_VOUCHER_NO}
																			</td>
																			<td>
																				${gs11OgInst?.TRANSACTION_TYPE}
																			</td>
																			<td>
																				${gs11OgInst?.PARTY_NAME}
																			</td>
																			<td>
																				${gs11OgInst?.PURITY}
																			</td>
																			<td>
																				${gs11OgInst?.ISSUE_NANG}
																			</td>
																			<td>
																				${gs11OgInst?.ISSUE_PCS}
																			</td>
																			<td>
																				${gs11OgInst?.ISSUE_GROSSWT}
																			</td>
																			<td>
																				${gs11OgInst?.ISSUE_NETWT}
																			</td>
																			<td>
																				${gs11OgInst?.ISSUE_CARAT}
																			</td>

																			<td>
																				${gs11OgInst?.RECEIPT_NANG}
																			</td>
																			<td>
																				${gs11OgInst?.RECEIPT_PCS}
																			</td>
																			<td>
																				${gs11OgInst?.RECEIPT_GROSSWT}
																			</td>
																			<td>
																				${gs11OgInst?.RECEIPT_NETWT}
																			</td>
																			<td>
																				${gs11OgInst?.RECEIPT_CARATWT}
																			</td>
																			<td>
																				${gs11OgInst?.NVL(AMOUNT,0)}
																			</td>
																		</tr>
																	</g:each>
																	<g:if test="${gs11Og}">
																		<tr>
																			<td colspan="4"><strong>Total :</strong></td>
																			<td>
																				${gs11Og?.sum { it.ISSUE_NANG } }
																			</td>
																			<td>
																				${gs11Og?.sum { it.ISSUE_PCS } }
																			</td>
																			<td>
																				${gs11Og?.sum { it.ISSUE_GROSSWT } }
																			</td>
																			<td>
																				${gs11Og?.sum { it.ISSUE_NETWT } }
																			</td>
																			<td>
																				${gs11Og?.sum { it.ISSUE_CARAT} }
																			</td>
																			<td>
																				${gs11Og?.sum { it.RECEIPT_NANG } }
																			</td>
																			<td>
																				${gs11Og?.sum { it.RECEIPT_PCS } }
																			</td>
																			<td>
																				${gs11Og?.sum { it.RECEIPT_GROSSWT } }
																			</td>
																			<td>
																				${gs11Og?.sum { it.RECEIPT_NETWT } }
																			</td>
																			<td>
																				${gs11Og?.sum { it.RECEIPT_CARATWT} }
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