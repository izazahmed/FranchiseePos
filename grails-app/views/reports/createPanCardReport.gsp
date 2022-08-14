<%-- 
     -- File Name: createPanCardReport
     -- Description: it is used to display PanCardReport Data
     -- Author(s): CTE 
     -- Date: 12/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 12/03/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta name='layout' content='mainerphq' />
<title>Pan Card Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Pan Card Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Branch Name</strong></td>
													<td><strong>Name</strong></td>					
													<td><strong>Voucher No</strong></td>
													<td><strong>Voucher Date</strong></td>
													<td><strong>Category</strong></td>
													<td><strong>Pan No</strong></td>
													<td><strong>ID Type</strong></td>
													<td><strong>Amount</strong></td>					
												</tr>
											</thead>
											<tbody>			
												<g:if test="${panCardReport}">
												<g:each in="${panCardReport}" status="i" var="panCardReportInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${panCardReportInst?.BR_NAME}</td>
														<td>${panCardReportInst?.NAME}</td>	
														<td>${panCardReportInst?.VC_VOUCHER_NO}</td>
														<td>${panCardReportInst?.DT_VOUCHER_DATE}</td>
														<td>${panCardReportInst?.VC_CATEGORY}</td>
														<td>${panCardReportInst?.PAN_NO}</td>
														<td>${panCardReportInst?.ID_TYPE}</td>
														<td>${panCardReportInst?.AMOUNT}</td>														
													</tr>					
												</g:each>	
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
