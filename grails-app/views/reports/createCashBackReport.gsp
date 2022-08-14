
<%-- 
     -- File Name: createCashBackReport
     -- Description: it is used to display the cashBackReport data
     -- Author(s): CTE 
     -- Date: 10/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 10/02/2016	   Abhijit      	Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.HdCash" %>
<%@ page import="com.tbz.franchisee.DtCashPayment" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Cash Back Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-hdCash" class="content scaffold-list" role="main">
							<h1>Cash Back Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
											<table class="table">
												<thead>
													<tr>
														<g:sortableColumn property="vcVoucherNo" title="${message(code: 'DtCashPayment.vcVoucherNo.label', default: 'Voucher No')}" />
														<g:sortableColumn property="dtVoucherDate" title="${message(code: 'DtCashPayment.dtVoucherDate.label', default: 'Voucher Date')}" />
														<g:sortableColumn property="vcCustFname" title="${message(code: 'HdCash.vcCustFname.label', default: 'Coustomer Name')}" />
														<g:sortableColumn property="vcCategory" title="${message(code: 'HdCash.vcCategory.label', default: 'Category')}" />
														<g:sortableColumn property="vcAddress1" title="${message(code: 'HdCash.vcAddress1.label', default: 'Address')}" />
														<g:sortableColumn property="nuAmount" title="${message(code: 'DtCashPayment.nuAmount.label', default: 'Amount')}" />
													</tr>
												</thead>
												<tbody>
												<g:each in="${cashBackReportList}" status="i" var="cashBackInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${cashBackInst?.VC_VOUCHER_NO}</td>
														<td>${cashBackInst?.DT_VOUCHER_DATE}</td>													
														<td>${cashBackInst?.CUSTOMER_NAME}</td>	
														<td>${cashBackInst?.VC_CATEGORY}</td>
														<td>${cashBackInst?.ADDRESS}</td>							
														<td>${cashBackInst?.AMOUNT}</td>					
													</tr>
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
</body>
</html>