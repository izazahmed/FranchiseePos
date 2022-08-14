<%-- 
     -- File Name: createNangmelReport
     -- Description: it is used to display NangmelReport Data
     -- Author(s): CTE 
     -- Date: 23/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 23/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE  html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Nangmel Report</title>
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
											<div class="content-bg pull-left">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="col-md-8 page-header">
															<table class="table">
																<tr>
																	<td><strong>Nangmel Date :</strong></td>
																	<td><strong>For Category :</strong> </td>
																	<td><strong>Print Time :</strong></td>
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
																	<g:each in="${nangmel}" status="i"
																		var="nangmelInst">
																		<tr>
																			<td>
																				${nangmelInst?.VOUCHER_DATE}
																			</td>
																			<td>
																				${nangmelInst?.VC_VOUCHER_NO}
																			</td>
																			<td>
																				${nangmelInst?.TRANSACTION_TYPE}
																			</td>
																			<td>
																				${nangmelInst?.PARTY_NAME}
																			</td>
																			<td>
																				${nangmelInst?.PURITY}
																			</td>
																			<td>
																				${nangmelInst?.ISSUE_NANG}
																			</td>
																			<td>
																				${nangmelInst?.ISSUE_PCS}
																			</td>
																			<td>
																				${nangmelInst?.ISSUE_GROSSWT}
																			</td>
																			<td>
																				${nangmelInst?.ISSUE_NETWT}
																			</td>
																			<td>
																				${nangmelInst?.ISSUE_CARAT}
																			</td>
																			<td>
																				${nangmelInst?.RECEIPT_NANG}
																			</td>
																			<td>
																				${nangmelInst?.RECEIPT_PCS}
																			</td>
																			<td>
																				${nangmelInst?.RECEIPT_GROSSWT}
																			</td>
																			<td>
																				${nangmelInst?.RECEIPT_NETWT}
																			</td>
																			<td>
																				${nangmelInst?.RECEIPT_CARATWT}
																			</td>
																			<td>
																				${nangmelInst?.NVL(AMOUNT,0)}
																			</td>																			
																		</tr>
																	</g:each>
																	<g:if test="${nangmel}">
																		<tr>
																			<td colspan="4"><strong>Total :</strong></td>
																			<td>
																				${nangmel?.sum { it.ISSUE_NANG } }
																			</td>
																			<td>
																				${nangmel?.sum { it.ISSUE_PCS } }
																			</td>
																			<td>
																				${nangmel?.sum { it.ISSUE_GROSSWT } }
																			</td>
																			<td>
																				${nangmel?.sum { it.ISSUE_NETWT } }
																			</td>
																			<td>
																				${nangmel?.sum { it.ISSUE_CARAT} }
																			</td>
																			<td>
																				${nangmel?.sum { it.RECEIPT_NANG } }
																			</td>
																			<td>
																				${nangmel?.sum { it.RECEIPT_PCS } }
																			</td>
																			<td>
																				${nangmel?.sum { it.RECEIPT_GROSSWT } }
																			</td>
																			<td>
																				${nangmel?.sum { it.RECEIPT_NETWT } }
																			</td>
																			<td>
																				${nangmel?.sum { it.RECEIPT_CARATWT} }
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