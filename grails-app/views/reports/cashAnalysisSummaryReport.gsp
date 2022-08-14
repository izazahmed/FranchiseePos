<%-- 
     -- File Name: cashAnalysisSummaryReport
     -- Description: it is used to display CashAnalysisSummary Report Data
     -- Author(s): CTE 
     -- Date: 12/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 12/05/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.TbzPosItemcodeLabelV" %>
<%@ page import="com.tbz.franchisee.XxtbzComponentDetailVNew" %>

<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Cash Analysis Summary Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Cash Analysis Summary Report</h1>
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
														<td colspan="3"><strong>M/C Id.:</strong>${summaryCashBackList?.mid}</td>
													</tr>
													<tr>
														<td></td>
														<td><strong>Cash</strong></td>
														<td><strong>Cashback</strong></td>
													</tr>
												</thead>
												<tbody>					
													<tr>
														<td>ADVANCE :</td><td>${summaryCashList?.ADVANCE}</td><td>${summaryCashBackList?.ADVANCE}</td>
													</tr>	
													<tr>
														<td>GIFT VOUCHER :</td><td>${summaryCashList?.GV}</td><td></td>
													</tr>		
													<tr>
														<td>KALPAVRUKSHA :</td><td>${summaryCashList?.KP}</td><td></td>
													</tr>	
													<tr>
														<td>MISC RECEIPT :</td><td>${summaryCashList?.MR}</td><td></td>
													</tr>	
													<tr>
														<td>OLD GOLD :</td><td>${summaryCashList?.OG}</td><td></td>
													</tr>		
													<tr>
														<td>PURCHASE :</td><td>${summaryCashList?.PB}</td><td></td>
													</tr>	
													<tr>
														<td>PUR. RETURN :</td><td>${summaryCashList?.PR}</td><td></td>
													</tr>	
													<tr>
														<td>STANDARD PUCHASE :</td><td>${summaryCashList?.PS}</td><td></td>
													</tr>	
													<tr>
														<td>SALES :</td><td>${summaryCashList?.SA}</td><td>${summaryCashBackList?.SA}</td>
													</tr>	
													<tr>
														<td>STANDARD SALES :</td><td>${summaryCashList?.SS}</td><td></td>
													</tr>	
													<tr>
														<td>GIFT CARD :</td><td>${summaryCashList?.GC}</td><td></td>
													</tr>	
													<tr>
														<td>DONATION RECEIPT :</td><td>${summaryCashList?.DR}</td><td></td>
													</tr>
													<tr>
														<td colspan="1"><strong>Grand Total :</strong></td> 
														<td>${summaryCashList?.totCash}</td> 	
														<td>${summaryCashBackList?.totCashBck}</td> 																					
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