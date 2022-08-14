<%-- 
     -- File Name: createAdvanceBalanceReport
     -- Description: it is used to display Mis Report Data
     -- Author(s): CTE 
     -- Date: 07/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 11/05/2016	   Rohil      		Created File
     --            
--%>

<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Advance Balance Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Advance Balance Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${advanceBalanceInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${advanceBalanceInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<!-- Table -->
										<div class="col-md-8 page-header">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Adv No. </strong></td>
														<td><strong>Adv Date. </strong></td>
														<td><strong>Customer Name </strong></td>
											            <td><strong>Remark </strong></td>
											            <td><strong>Label No. </strong></td>
											            <td><strong>G.wtt </strong></td>
											            <td><strong>Cts. </strong></td>
											            <td><strong>Rate. </strong></td>
											            <td><strong>Cat. </strong></td>
											            <td><strong>Adv Amt. </strong></td>
											            <td><strong>Balance Amt. </strong></td>
											    	</tr>
												</thead>
												<tbody>
													<g:each in="${advanceBalanceList}" status="i" var="advanceBalanceInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${advanceBalanceInst?.VC_ADV_ORD_NO}
															</td>
															<td>
																${advanceBalanceInst?.DT_ADV_ORD_DATE}
															</td>
															<td>
																${advanceBalanceInst?.VC_CUSTOMER_NAME}
															</td>
															<td>
																${advanceBalanceInst?.VC_NARRATION}
															</td>
															<td>
																${advanceBalanceInst?.VC_LABEL_NO}
															</td>
															<td>
																${advanceBalanceInst?.NU_GROSS_WTT}
															</td>
															<td>
																${advanceBalanceInst?.NU_CARRAT_WTT}
															</td>
															<td>
																${advanceBalanceInst?.NU_RATE}
															</td>
															<td>
																${advanceBalanceInst?.VC_CATEGORY}
															</td>
															<td>
																${advanceBalanceInst?.NU_TOT_ADV_AMT}
															</td>
															<td>
																${advanceBalanceInst?.BALANCE_AMOUNT}
															</td>
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
	</div>
</body>
</html>