<%-- 
     -- File Name: createKpSaleCountReport
     -- Description: it is used to display KpSaleCountReport Data
     -- Author(s): CTE 
     -- Date: 07/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 07/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>KP Sale Count Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>KP Sale Count Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Month</strong></td>
													<td><strong>Scheme No</strong></td>					
													<td><strong>Branch</strong></td>
													<td><strong>New Enroll No</strong></td>
													<td><strong>New Enroll Amount</strong></td>													
													<td><strong>Total Rec.Amount</strong></td>					
												</tr>
											</thead>
											<tbody>			
												<g:each in="${kpSaleCountList}" status="i" var="kpSaleCountListInst">
													<tr>
														<td>${kpSaleCountListInst?.month}</td>
														<td>${kpSaleCountListInst?.schemeNo}</td>	
														<td>${kpSaleCountListInst?.BrName}</td>
														<td>${kpSaleCountListInst?.enrollNo}</td>						
														<td>${kpSaleCountListInst?.enrollAmt}</td>
														<td>${kpSaleCountListInst?.TotRecAmt}</td>																																	
													</tr>					
												</g:each>				
												<tr>
													<td colspan="3"><strong>Total :</strong></td> 
													<td>${kpSaleCountList.sum { it.enrollNo } }</td>
													<td> ${kpSaleCountList.sum { it.enrollAmt } }</td>	
													<td> ${kpSaleCountList.sum { it.TotRecAmt } }</td>																			
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
