<%-- 
     -- File Name: createadvanceActiveReport
     -- Description: it is used to display advanceActive Report Data
     -- Author(s): CTE 
     -- Date: 07/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 07/03/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Advance Active Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Advance Active Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${custMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${custMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Advance Type</strong></td>
														<td><strong>Order No.</strong></td>
														<td><strong>Stage</strong></td>
														<td><strong>Date</strong></td>
														<td><strong>Name & Address</strong></td>
														<td><strong>Amount Received</strong></td>
														<td><strong>Type Of Pay</strong></td>
														<td><strong>category</strong></td>
														<td><strong>EBS Ord No.</strong></td>
														<td><strong>Wtt/CRT</strong></td>
														<td><strong>Rate</strong></td>
													</tr>
												</thead>
												<tbody>
													<g:each in="${advanceActiveList}" status="i"
														var="advanceActiveReportValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${advanceActiveReportValInst?.VC_VOUCHER_NO}
															</td>
															<td>
																${advanceActiveReportValInst?.DT_VOUCHER_DATE}
															</td>
															<td>
																${advanceActiveReportValInst?.NU_AMOUNT}
															</td>
															<td>
																${advanceActiveReportValInst?.VC_NARRATION}
															</td>
															<td>
																${advanceActiveReportValInst?.VC_CATEGORY}
															</td>
															<td>
																${advanceActiveReportValInst?.PAY_TYPE}
															</td>
															<td>
																${advanceActiveReportValInst?.VOUCHER_NO}
															</td>
															<td></td>
														</tr>
														<g:set var="nuAmount" value="${advanceActiveReportVal.sum { it.NU_AMOUNT} }"></g:set>
													</g:each>
													<tr>
														<td colspan="2"><strong>Total :</strong></td>
														<td>${nuAmount}</td>
														<td colspan="8"></td>
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