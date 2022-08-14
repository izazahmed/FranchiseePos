<%-- 
     -- File Name: createGiftVoucherActiveReport
     -- Description: it is used to display GiftVoucherActive Report Data
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
<title>Gift Voucher Active Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Gift Voucher Active Report</h1>
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
														<td><strong>Loc</strong></td>
														<td><strong>Gift Voucher No.</strong></td>
														<td><strong>Date</strong></td>
														<td><strong>Name</strong></td>
														<td><strong>Address</strong></td>
														<td><strong>Issued To</strong></td>
														<td><strong>Payment Mode</strong></td>
														<td><strong>Amount</strong></td>
														<td><strong>KBS</strong></td>
														<td><strong>Order No.</strong></td>
													</tr>
												</thead>
												<tbody>
													<g:each in="${giftVoucherActiveReportVal}" status="i" var="giftVoucherActiveReportValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${giftVoucherActiveReportValInst?.Q1_COMP}
															</td>
															<td>
																${giftVoucherActiveReportValInst?.Q1_CATG}
															</td>
															<td>
																${giftVoucherActiveReportValInst?.Q1_IDATE}
															</td>
															<td>
																${giftVoucherActiveReportValInst?.Q1_NAME}
															</td>
															<td>
																${giftVoucherActiveReportValInst?.Q1_ADDR}
															</td>
															<td>
																${giftVoucherActiveReportValInst?.Q1_ISS_TO}
															</td>
															<td>
																${giftVoucherActiveReportValInst?.CH_PAY_MODE}
															</td>
															<td>
																${giftVoucherActiveReportValInst?.Q1_AMT}
															</td>
														</tr>
														<g:set var="nuAmount" value="${giftVoucherActiveReportVal.sum { it.Q1_AMT} }"></g:set>
													</g:each>
													<tr>
														<td colspan="6"></td>
														<td><strong>Total :</strong></td>
														<td>
															${nuAmount}
														</td>
														<td colspan="4"></td>
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