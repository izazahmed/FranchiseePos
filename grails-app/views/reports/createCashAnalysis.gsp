<%-- 
     -- File Name: createCashAnalysis
     -- Description: it is used to display CashAnalysis Report Data
     -- Author(s): CTE 
     -- Date: 11/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 11/05/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.TbzPosItemcodeLabelV" %>
<%@ page import="com.tbz.franchisee.XxtbzComponentDetailVNew" %>

<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Cash Analysis Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Cash Analysis Report</h1>
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
										<div class="col-md-8 page-header">
											<table class="table">
												<thead>
												<g:set value="${kpCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>KP   KALPAVRUKSHA</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${kpCashAnalysisList}" status="i" var="kpCashAnalysisInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${kpCashAnalysisInst?.INWARD_ID}</td>
															<td>${kpCashAnalysisInst?.BILL_AMOUNT}</td>
															<td>${kpCashAnalysisInst?.CASH}</td>
															<td>${kpCashAnalysisInst?.CASHBACK}</td>
															<td>${kpCashAnalysisInst?.BALANCE}</td>
															<td>${kpCashAnalysisInst?.CHEQUE}</td>
															<td>${kpCashAnalysisInst?.CREDITCARD}</td>
															<td>${kpCashAnalysisInst?.FOREIGNCURR}</td>
															<td>${kpCashAnalysisInst?.ADVANCE}</td>
															<td>${kpCashAnalysisInst?.KP_SETTLE}</td>
															<td>${kpCashAnalysisInst?.GIFTV}</td>
															<td>${kpCashAnalysisInst?.LOAN}</td>
															<td>${kpCashAnalysisInst?.TRAVEL}</td>
															<td>${kpCashAnalysisInst?.DISCOUNT}</td>
															<td>${kpCashAnalysisInst?.TR_ADVANCE}</td>
															<td>${kpCashAnalysisInst?.WB_ADVANCE}</td>
															<td>${kpCashAnalysisInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${kpCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${kpCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${kpCashAnalysisList.sum { it.CASH } }</td>
															<td> ${kpCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${kpCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${kpCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${kpCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${kpCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${kpCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${kpCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${kpCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${kpCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${kpCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${kpCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${kpCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${kpCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${kpCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For KP</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${adCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>AD   ADVANCE</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${adCashAnalysisList}" status="i" var="adCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${adCashAnalysisListInst?.INWARD_ID}</td>
															<td>${adCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${adCashAnalysisListInst?.CASH}</td>
															<td>${adCashAnalysisListInst?.CASHBACK}</td>
															<td>${adCashAnalysisListInst?.BALANCE}</td>
															<td>${adCashAnalysisListInst?.CHEQUE}</td>
															<td>${adCashAnalysisListInst?.CREDITCARD}</td>
															<td>${adCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${adCashAnalysisListInst?.ADVANCE}</td>
															<td>${adCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${adCashAnalysisListInst?.GIFTV}</td>
															<td>${adCashAnalysisListInst?.LOAN}</td>
															<td>${adCashAnalysisListInst?.TRAVEL}</td>
															<td>${adCashAnalysisListInst?.DISCOUNT}</td>
															<td>${adCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${adCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${adCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${adCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${adCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${adCashAnalysisList.sum { it.CASH } }</td>
															<td> ${adCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${adCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${adCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${adCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${adCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${adCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${adCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${adCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${adCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${adCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${adCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${adCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${adCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${adCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For AD</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${mrCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>MR MISCELLANEOUS RECEIPT</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${mrCashAnalysisList}" status="i" var="mrCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${mrCashAnalysisListInst?.INWARD_ID}</td>
															<td>${mrCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${mrCashAnalysisListInst?.CASH}</td>
															<td>${mrCashAnalysisListInst?.CASHBACK}</td>
															<td>${mrCashAnalysisListInst?.BALANCE}</td>
															<td>${mrCashAnalysisListInst?.CHEQUE}</td>
															<td>${mrCashAnalysisListInst?.CREDITCARD}</td>
															<td>${mrCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${mrCashAnalysisListInst?.ADVANCE}</td>
															<td>${mrCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${mrCashAnalysisListInst?.GIFTV}</td>
															<td>${mrCashAnalysisListInst?.LOAN}</td>
															<td>${mrCashAnalysisListInst?.TRAVEL}</td>
															<td>${mrCashAnalysisListInst?.DISCOUNT}</td>
															<td>${mrCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${mrCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${mrCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${mrCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${mrCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${mrCashAnalysisList.sum { it.CASH } }</td>
															<td> ${mrCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${mrCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${mrCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${mrCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${mrCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${mrCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${mrCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${mrCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${mrCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${mrCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${mrCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${mrCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${mrCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${mrCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For MR</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${saCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>SA SALES</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${saCashAnalysisList}" status="i" var="saCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${saCashAnalysisListInst?.INWARD_ID}</td>
															<td>${saCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${saCashAnalysisListInst?.CASH}</td>
															<td>${saCashAnalysisListInst?.CASHBACK}</td>
															<td>${saCashAnalysisListInst?.BALANCE}</td>
															<td>${saCashAnalysisListInst?.CHEQUE}</td>
															<td>${saCashAnalysisListInst?.CREDITCARD}</td>
															<td>${saCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${saCashAnalysisListInst?.ADVANCE}</td>
															<td>${saCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${saCashAnalysisListInst?.GIFTV}</td>
															<td>${saCashAnalysisListInst?.LOAN}</td>
															<td>${saCashAnalysisListInst?.TRAVEL}</td>
															<td>${saCashAnalysisListInst?.DISCOUNT}</td>
															<td>${saCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${saCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${saCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${saCashAnalysisList}">
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${saCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${saCashAnalysisList.sum { it.CASH } }</td>
															<td> ${saCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${saCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${saCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${saCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${saCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${saCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${saCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${saCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${saCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${saCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${saCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${saCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${saCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${saCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For SA</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${ssCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>SS STD SALES</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${ssCashAnalysisList}" status="i" var="ssCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${ssCashAnalysisListInst?.INWARD_ID}</td>
															<td>${ssCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${ssCashAnalysisListInst?.CASH}</td>
															<td>${ssCashAnalysisListInst?.CASHBACK}</td>
															<td>${ssCashAnalysisListInst?.BALANCE}</td>
															<td>${ssCashAnalysisListInst?.CHEQUE}</td>
															<td>${ssCashAnalysisListInst?.CREDITCARD}</td>
															<td>${ssCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${ssCashAnalysisListInst?.ADVANCE}</td>
															<td>${ssCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${ssCashAnalysisListInst?.GIFTV}</td>
															<td>${ssCashAnalysisListInst?.LOAN}</td>
															<td>${ssCashAnalysisListInst?.TRAVEL}</td>
															<td>${ssCashAnalysisListInst?.DISCOUNT}</td>
															<td>${ssCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${ssCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${ssCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${ssCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${ssCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${ssCashAnalysisList.sum { it.CASH } }</td>
															<td> ${ssCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${ssCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${ssCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${ssCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${ssCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${ssCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${ssCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${ssCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${ssCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${ssCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${ssCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${ssCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${ssCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${ssCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For SS</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${pbCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>PB</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${pbCashAnalysisList}" status="i" var="pbCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${pbCashAnalysisListInst?.INWARD_ID}</td>
															<td>${pbCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${pbCashAnalysisListInst?.CASH}</td>
															<td>${pbCashAnalysisListInst?.CASHBACK}</td>
															<td>${pbCashAnalysisListInst?.BALANCE}</td>
															<td>${pbCashAnalysisListInst?.CHEQUE}</td>
															<td>${pbCashAnalysisListInst?.CREDITCARD}</td>
															<td>${pbCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${pbCashAnalysisListInst?.ADVANCE}</td>
															<td>${pbCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${pbCashAnalysisListInst?.GIFTV}</td>
															<td>${pbCashAnalysisListInst?.LOAN}</td>
															<td>${pbCashAnalysisListInst?.TRAVEL}</td>
															<td>${pbCashAnalysisListInst?.DISCOUNT}</td>
															<td>${pbCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${pbCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${pbCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${pbCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${pbCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${pbCashAnalysisList.sum { it.CASH } }</td>
															<td> ${pbCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${pbCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${pbCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${pbCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${pbCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${pbCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${pbCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${pbCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${pbCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${pbCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${pbCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${pbCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${pbCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${pbCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For PB</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${prCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>PR</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${prCashAnalysisList}" status="i" var="prCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${prCashAnalysisListInst?.INWARD_ID}</td>
															<td>${prCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${prCashAnalysisListInst?.CASH}</td>
															<td>${prCashAnalysisListInst?.CASHBACK}</td>
															<td>${prCashAnalysisListInst?.BALANCE}</td>
															<td>${prCashAnalysisListInst?.CHEQUE}</td>
															<td>${prCashAnalysisListInst?.CREDITCARD}</td>
															<td>${prCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${prCashAnalysisListInst?.ADVANCE}</td>
															<td>${prCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${prCashAnalysisListInst?.GIFTV}</td>
															<td>${prCashAnalysisListInst?.LOAN}</td>
															<td>${prCashAnalysisListInst?.TRAVEL}</td>
															<td>${prCashAnalysisListInst?.DISCOUNT}</td>
															<td>${prCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${prCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${prCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${prCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${prCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${prCashAnalysisList.sum { it.CASH } }</td>
															<td> ${prCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${prCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${prCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${prCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${prCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${prCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${prCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${prCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${prCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${prCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${prCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${prCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${prCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${prCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For PR</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${gvCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>GV</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${gvCashAnalysisList}" status="i" var="gvCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${gvCashAnalysisListInst?.INWARD_ID}</td>
															<td>${gvCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${gvCashAnalysisListInst?.CASH}</td>
															<td>${gvCashAnalysisListInst?.CASHBACK}</td>
															<td>${gvCashAnalysisListInst?.BALANCE}</td>
															<td>${gvCashAnalysisListInst?.CHEQUE}</td>
															<td>${gvCashAnalysisListInst?.CREDITCARD}</td>
															<td>${gvCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${gvCashAnalysisListInst?.ADVANCE}</td>
															<td>${gvCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${gvCashAnalysisListInst?.GIFTV}</td>
															<td>${gvCashAnalysisListInst?.LOAN}</td>
															<td>${gvCashAnalysisListInst?.TRAVEL}</td>
															<td>${gvCashAnalysisListInst?.DISCOUNT}</td>
															<td>${gvCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${gvCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${gvCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${gvCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${gvCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${gvCashAnalysisList.sum { it.CASH } }</td>
															<td> ${gvCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${gvCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${gvCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${gvCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${gvCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${gvCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${gvCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${gvCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${gvCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${gvCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${gvCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${gvCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${gvCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${gvCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For GV</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${gcCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>GC</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${gcCashAnalysisList}" status="i" var="gcCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${gcCashAnalysisListInst?.INWARD_ID}</td>
															<td>${gcCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${gcCashAnalysisListInst?.CASH}</td>
															<td>${gcCashAnalysisListInst?.CASHBACK}</td>
															<td>${gcCashAnalysisListInst?.BALANCE}</td>
															<td>${gcCashAnalysisListInst?.CHEQUE}</td>
															<td>${gcCashAnalysisListInst?.CREDITCARD}</td>
															<td>${gcCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${gcCashAnalysisListInst?.ADVANCE}</td>
															<td>${gcCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${gcCashAnalysisListInst?.GIFTV}</td>
															<td>${gcCashAnalysisListInst?.LOAN}</td>
															<td>${gcCashAnalysisListInst?.TRAVEL}</td>
															<td>${gcCashAnalysisListInst?.DISCOUNT}</td>
															<td>${gcCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${gcCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${gcCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${gcCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${gcCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${gcCashAnalysisList.sum { it.CASH } }</td>
															<td> ${gcCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${gcCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${gcCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${gcCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${gcCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${gcCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${gcCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${gcCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${gcCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${gcCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${gcCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${gcCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${gcCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${gcCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For GC</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${drCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>DR</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${drCashAnalysisList}" status="i" var="drCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${drCashAnalysisListInst?.INWARD_ID}</td>
															<td>${drCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${drCashAnalysisListInst?.CASH}</td>
															<td>${drCashAnalysisListInst?.CASHBACK}</td>
															<td>${drCashAnalysisListInst?.BALANCE}</td>
															<td>${drCashAnalysisListInst?.CHEQUE}</td>
															<td>${drCashAnalysisListInst?.CREDITCARD}</td>
															<td>${drCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${drCashAnalysisListInst?.ADVANCE}</td>
															<td>${drCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${drCashAnalysisListInst?.GIFTV}</td>
															<td>${drCashAnalysisListInst?.LOAN}</td>
															<td>${drCashAnalysisListInst?.TRAVEL}</td>
															<td>${drCashAnalysisListInst?.DISCOUNT}</td>
															<td>${drCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${drCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${drCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${drCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${drCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${drCashAnalysisList.sum { it.CASH } }</td>
															<td> ${drCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${drCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${drCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${drCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${drCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${drCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${drCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${drCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${drCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${drCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${drCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${drCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${drCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${drCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For DR</div>
													</g:else>
												</tbody>
											</table>
											<table class="table">
												<thead>
												<g:set value="${atwbCashAnalysisList?.NU_MC_ID}" var="machineId" />
												<tr>ID NO :<td>${machineId}</td></tr>
												<tr><center>ATWB</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>Adv Trf.</strong></td>
														<td><strong>WB Trf.</strong></td>
														<td><strong>G Crd.</strong></td>
													</tr>
												</thead>
												<tbody>					
													<g:each in="${atwbCashAnalysisList}" status="i" var="atwbCashAnalysisListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${atwbCashAnalysisListInst?.INWARD_ID}</td>
															<td>${atwbCashAnalysisListInst?.BILL_AMOUNT}</td>
															<td>${atwbCashAnalysisListInst?.CASH}</td>
															<td>${atwbCashAnalysisListInst?.CASHBACK}</td>
															<td>${atwbCashAnalysisListInst?.BALANCE}</td>
															<td>${atwbCashAnalysisListInst?.CHEQUE}</td>
															<td>${atwbCashAnalysisListInst?.CREDITCARD}</td>
															<td>${atwbCashAnalysisListInst?.FOREIGNCURR}</td>
															<td>${atwbCashAnalysisListInst?.ADVANCE}</td>
															<td>${atwbCashAnalysisListInst?.KP_SETTLE}</td>
															<td>${atwbCashAnalysisListInst?.GIFTV}</td>
															<td>${atwbCashAnalysisListInst?.LOAN}</td>
															<td>${atwbCashAnalysisListInst?.TRAVEL}</td>
															<td>${atwbCashAnalysisListInst?.DISCOUNT}</td>
															<td>${atwbCashAnalysisListInst?.TR_ADVANCE}</td>
															<td>${atwbCashAnalysisListInst?.WB_ADVANCE}</td>
															<td>${atwbCashAnalysisListInst?.GCard}</td>
														 </tr>
													</g:each>
													<g:if test="${atwbCashAnalysisList}">	
														<tr>
															<td colspan="1"><strong>Total :</strong></td> <td>${atwbCashAnalysisList.sum { it.BILL_AMOUNT } }</td> 
															<td> ${atwbCashAnalysisList.sum { it.CASH } }</td>
															<td> ${atwbCashAnalysisList.sum { it.CASHBACK } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.BALANCE } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.CHEQUE } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.CREDITCARD } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.FOREIGNCURR } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.ADVANCE } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.KP_SETTLE } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.GIFTV } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.LOAN } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.TRAVEL } }</td>		
															<td> ${atwbCashAnalysisList.sum { it.DISCOUNT } }</td>
															<td> ${atwbCashAnalysisList.sum { it.TR_ADVANCE } }</td>
															<td> ${atwbCashAnalysisList.sum { it.WB_ADVANCE } }</td>
															<td> ${atwbCashAnalysisList.sum { it.GCard } }</td>																						
														</tr>
													</g:if>
													<g:else>
														<div style="font-size: 16px; color: red">No Data Found For ATWB</div>
													</g:else>
												</tbody>
											</table>	
											
											<table class="table">
												<thead>
												<tr><center>TOTAL</center></tr>
													<tr>
														<td></td>
														<td><strong>Bill Amount</strong></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
														<td><strong>Balance</strong></td>
														<td><strong>Cheque</strong></td>
														<td><strong>Credit Card</strong></td>
														<td><strong>Foreign CU</strong></td>
														<td><strong>Advance</strong></td>
														<td><strong>KP</strong></td>
														<td><strong>Gift Vo.</strong></td>
														<td><strong>Loan</strong></td>
														<td><strong>Travel</strong></td>
														<td><strong>Discount</strong></td>
														<td><strong>GCard</strong></td>
													</tr>
												</thead>
												<tbody>					
														<tr>
															<td>ADVANCE :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>GIFT VOUCHER :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>		
														<tr>
															<td>KALPAVRUKSHA :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>MISC RECEIPT :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>OLD GOLD :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>		
														<tr>
															<td>PURCHASE :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>PUR. RETURN :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>STANDARD PUCHASE :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>SALES :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>STANDARD SALES :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>GIFT CARD :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>	
														<tr>
															<td>DONATION RECEIPT :</td>
															<td>${sumCashAnaList?.BILL_AMOUNT9}</td>
															<td>${sumCashAnaList?.CASH9}</td>
															<td>${sumCashAnaList?.CASHBACK9}</td>
															<td>${sumCashAnaList?.BALANCE9}</td>
															<td>${sumCashAnaList?.CHEQUE9}</td>
															<td>${sumCashAnaList?.CREDITCARD9}</td>
															<td>${sumCashAnaList?.FOREIGNCURR9}</td>
															<td>${sumCashAnaList?.ADVANCE9}</td>
															<td>${sumCashAnaList?.KP_SETTLE9}</td>
															<td>${sumCashAnaList?.GIFTV9}</td>
															<td>${sumCashAnaList?.LOAN9}</td>
															<td>${sumCashAnaList?.TRAVEL9}</td>
															<td>${sumCashAnaList?.DISCOUNT9}</td>
															<td>${sumCashAnaList?.GC}</td>
														</tr>
														<tr>
															<td colspan="1"><strong>Grand Total :</strong></td> 
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