<%-- 
     -- File Name: createCreditCardReport
     -- Description: it is used to display CreditCard Report Data
     -- Author(s): CTE 
     -- Date: 20/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 20/02/2016	   Abhijit      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.HdCash" %>
<%@ page import="com.tbz.franchisee.DtCashPayment" %>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Credit Card Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Credit Card Report</h1>
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
							<table>
								<tbody>
									<tr>
										<g:each in="${frmDte}" status="k" var="dte">
											<td> From Date:- ${dte}</td>
										</g:each>
									</tr>
								</tbody>
							</table>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<table width="100%" class="table">
											<thead>
											<tr>
												<g:sortableColumn property="vcCategory" title="${message(code: 'HdCash.vcCategory.label', default: 'Inv No')}" />
												<g:sortableColumn property="dtVoucherDate" title="${message(code: 'HdCash.dtVoucherDate.label', default: 'Inv Date')}" />
												<g:sortableColumn property="vcTbzBank" title="${message(code: 'DtCashCard.vcTbzBank.label', default: 'Party Name')}" />
												<g:sortableColumn property="vcCcNo" title="${message(code: 'DtCashCard.vcCcNo.label', default: 'Credit Card No')}" />
												<g:sortableColumn property="vcApprovalNo" title="${message(code: 'DtCashCard.vcApprovalNo.label', default: 'Approval No')}" />
												<g:sortableColumn property="nuSettAmt" title="${message(code: 'DtCashCard.nuSettAmt.label', default: 'Amount')}" />
											</tr>
										</thead>
											<tbody>
											<g:each in="${creditCardReport}" status="i" var="creditCardInst">
												<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
													<td>${creditCardInst?.VC_VOUCHER_NO}</td>
													<td>${creditCardInst?.DT_VOUCHER_DATE}</td>													
													<td>${creditCardInst?.VC_CARD_TYPE}</td>	
													<td>${creditCardInst?.VC_CC_NO}</td>
													<td>${creditCardInst?.VC_APPROVAL_NO}</td>							
													<td>${creditCardInst?.NU_SETT_AMT}</td>					
												</tr>
											</g:each>					
											</tbody>
										</table>
										<table width="100%">
											<g:each in="${creditCardCountReport}" status="j" var="creditCardCountInst">
												<tr>
													<td width="90%"><strong>Total :</strong></td> 
													<td width="10%">${creditCardCountInst?.AMT}</td>	
												</tr>
											</g:each>
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