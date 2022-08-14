<%-- 
     -- File Name: createMisReport
     -- Description: it is used to display Mis Report Data
     -- Author(s): CTE 
     -- Date: 07/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 07/03/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>MIS Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>MIS Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${custMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${custMstInstance}" var="error">
										<li
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
												error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Vou No</strong></td>
														<td><strong>Voucher Date</strong></td>
														<td><strong>Amount</strong></td>
														<td><strong>Narration</strong></td>
														<td><strong>Cat</strong></td>
														<td><strong>Payment</strong></td>
														<td><strong>Voucher Agst.Receipt</strong></td>
													</tr>
												</thead>
												<tbody>
												<g:if test="${misReportVal}">
													<g:each in="${misReportVal}" status="i"
														var="misReportValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${misReportValInst?.VC_VOUCHER_NO}</td>
															<td>${misReportValInst?.DT_VOUCHER_DATE}</td>
															<td>${misReportValInst?.NU_AMOUNT}</td>
															<td>${misReportValInst?.VC_NARRATION}</td>
															<td>${misReportValInst?.VC_CATEGORY}</td>
															<td>${misReportValInst?.PAY_TYPE}</td>
															<td>${misReportValInst?.VOUCHER_NO}</td>
														</tr>
														<g:set var="nuAmount" value="${misReportVal.sum { it.NU_AMOUNT} }"></g:set>
													</g:each>
													<tr>
														<td colspan="2"><strong>Total :</strong></td>
														<td>
															${nuAmount}
														</td>
														<td colspan="4"></td>
													</tr>
													</g:if>
												<g:else>
													<td colspan="7" align="center"><div class="error">No Data Found</div></td>
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