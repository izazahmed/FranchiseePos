<%-- 
     -- File Name: createAdvTransferReport
     -- Description: it is used to display Advance Transfer Report Data
     -- Author(s): CTE 
     -- Date: 07/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 07/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html> 
	<head>
		<meta name='layout' content='mainerphq' />
		<g:set var="entityName" value="${message(code: 'dtCrossAdvSettle.label', default: 'AdvanceTransferReport')}" />
<%--		<title><g:message code="default.list.label" args="[entityName]" /></title>--%>
<title>Advance Transfer Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1><g:message code="default.list.label" args="[entityName]" /></h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Adv No</strong></td>
													<td><strong>Adv Date</strong></td>					
													<td><strong>Customer Name</strong></td>
													<td><strong>Tot Amt</strong></td>
													<td><strong>Bal Amt</strong></td>													
													<td><strong>Status</strong></td>
													<td><strong>TransferredFromBranch</strong></td>
													<td><strong>TransferredToBranch</strong></td>						
												</tr>
											</thead>
											<tbody>			
											<g:if test="${dtCrossAdvSettleResult}">	
												<g:each in="${dtCrossAdvSettleResult}" status="i" var="dtCrossAdvSettleResultInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${dtCrossAdvSettleResultInst?.VC_ADV_ORD_NO}</td>
														<td>${dtCrossAdvSettleResultInst?.DT_ADV_ORD_DATE}</td>	
														<td>${dtCrossAdvSettleResultInst?.CUSTOMER_NAME}</td>
														<td>${dtCrossAdvSettleResultInst?.NU_TOT_AMT}</td>						
														<td>${dtCrossAdvSettleResultInst?.NU_BAL_AMT}</td>
														<td>${dtCrossAdvSettleResultInst?.STATUS}</td>
														<td>${dtCrossAdvSettleResultInst?.TRANSFERRED_FROM_BRANCH}</td>
														<td>${dtCrossAdvSettleResultInst?.TRANSFERRED_TO_BRANCH}</td>																				
													</tr>					
												</g:each>															
													<tr>
														<td colspan="3"><strong>Total :</strong></td> <td>${dtCrossAdvSettleResult.sum { it.NU_TOT_AMT } }</td> <td> ${dtCrossAdvSettleResult.sum { it.NU_BAL_AMT } }</td>																				
													</tr>		
												</g:if>
												<g:else>
													 <td colspan="8" align="center"><div class="error">No Data Found</div></td>
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
