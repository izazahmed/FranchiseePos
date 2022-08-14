<%-- 
     -- File Name: createSalesChecklistWithTargetReport
     -- Description: it is used to display SalesChecklistWithTargetReport Data
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
<title>Sales Checklist With Target Report</title>
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
											<strong>
												${mainHead.get("coName")}
											</strong><br> <strong>
												${salesChecklistTarget[0]}
											</strong>
											<h1>
												Sales Checklist With Target Report
											</h1>
											<div class="content-bg pull-left">
												<div class="col-md-12">
													<div class="panel panel-default">
															<table class="table">
																<tr>
																	<td><strong>From Date :</strong>
																		${mainHead.get("fromDate")}</td>
																	<td><strong>To Date :</strong>
																		${mainHead.get("toDate")}</td>
																	<td><strong>For Category :</strong>
																		${mainHead.get("cat")}</td>
																	<td><strong>Print Time :</strong>
																		${mainHead.get("today")}</td>
																</tr>
															</table>
														</div>
													</div>
												</div>
											</div>
											<div class="content-bg pull-left">
												<div class="col-md-12">
													<div class="panel panel-default">
															<table class="table">
																<thead>
																	<tr>
																		<td><strong>Voucher Date</strong></td>
																		<td><strong>Voucher Number</strong></td>
																		<td><strong>Stage</strong></td>
																		<td><strong>Sales Type</strong></td>
																		<td><strong>Sales</strong></td>
																		<td><strong>Bill Amount</strong></td>
																		<td><strong>Label No.</strong></td>
																		<td><strong>Item Code</strong></td>
																		<td><strong>Sub Code</strong></td>
																		<td><strong>Purity</strong></td>
																		<td><strong>Nang</strong></td>
																		<td><strong>Pcs</strong></td>
																		<td><strong>Gross Wt.</strong></td>
																		<td><strong>Net Wt.</strong></td>
																		<td><strong>Rate</strong></td>
																		<td><strong>Vat</strong></td>
																		<td><strong>Customer Name</strong></td>
																		<td><strong>EBS order No.</strong></td>
																	</tr>
																</thead>
																<tbody>

																	<g:each in="${salesChecklistTarget}" status="i"
																		var="salesChecklistTargetInst">
																		<tr>
																			<td>
																				${salesChecklistTargetInst?.DT_VOUCHER_DATE}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.VC_VOUCHER_NO}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.CH_STAGE}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.CH_STAGE}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.NU_BILL_AMOUNT}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.VC_LABEL_NO}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.VC_ITEM_CODE}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.VC_SUB_CODE}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.VC_PURITY1}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.NU_NANG}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.NU_PCS}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.NU_GROSS_WTT}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.NU_NETT_WTT}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.NU_RATE}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.NU_VAT}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.VC_DESIGN_NO}
																			</td>
																			<td>
																				${salesChecklistTargetInst?.VC_EBS_ORDER_NO}
																			</td>
																		</tr>
																	</g:each>
																	<g:if test="${salesChecklistTarget}">
																		<tr>
																			<td colspan="4"><strong>Total :</strong></td>
																			<td>
																				${salesChecklistTargetInst?.sum { it.nang } }
																			</td>
																			<td>
																				${salesChecklistTargetInst?.sum { it.pieces } }
																			</td>
																			<td>
																				${salesChecklistTargetInst?.sum { it.gross_weight } }
																			</td>
																			<td>
																				${salesChecklistTargetInst?.sum { it.net_weight } }
																			</td>
																			<td>
																				${salesChecklistTargetInst?.sum { it.CARAT_WEIGHT } }
																			</td>
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>
																		</tr>
																	</g:if>
																	<g:else>
																		<tr>
																			<td align="center" colspan="18"><div class="error">No Data Found</div></td>
																		</tr>
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
</body>
</html>