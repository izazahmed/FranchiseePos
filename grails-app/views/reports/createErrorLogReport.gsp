<%-- 
     -- File Name: createErrorLogReport
     -- Description: it is used to display ErrorLogReport Data
     -- Author(s): CTE 
     -- Date: 05/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 05/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Error Log Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Error Log Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Voucher Date</strong></td>
													<td><strong>Sales Stg</strong></td>														
													<td><strong>Sales Unstg</strong></td>													
													<td><strong>Adv stg</strong></td>	
													<td><strong>Adv Unstg</strong></td>
													<td><strong>Pur Stg</strong></td>														
													<td><strong>Pur Unstg</strong></td>													
													<td><strong>Kp Stg</strong></td>
													<td><strong>Kp Unstg</strong></td>
													<td><strong>KP/AdvPend Chq Clr</strong></td>													
												</tr>
											</thead>
											<tbody>		
												<g:each in="${ErrorLogReportList}" status="i" var="ErrorLogReportListInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${ErrorLogReportListInst?.dt}</td>
														<td>${ErrorLogReportListInst?.SALESSTG}</td>
														<td>${ErrorLogReportListInst?.SALESUNSTG}</td>
														<td>${ErrorLogReportListInst?.ADVSTG}</td>
														<td>${ErrorLogReportListInst?.ADVUNSTG}</td>
														<td>${ErrorLogReportListInst?.PURSTG}</td>
														<td>${ErrorLogReportListInst?.PURUNSTG}</td>
														<td>${ErrorLogReportListInst?.KPSTG}</td>
														<td>${ErrorLogReportListInst?.KPUNSTG}</td>
														<td>${ErrorLogReportListInst?.cpchqclr}</td>
													</tr>	
												</g:each>	
												<g:if test="${ErrorLogReportList}">													
													<tr>
														<td colspan="1"><strong>Total :</strong></td>
														<td>${ErrorLogReportList.sum { it.SALESSTG } }</td> 
														<td>${ErrorLogReportList.sum { it.SALESUNSTG } }</td>	
														<td>${ErrorLogReportList.sum { it.ADVSTG } }</td> 
														<td>${ErrorLogReportList.sum { it.ADVUNSTG } }</td>
														<td>${ErrorLogReportList.sum { it.PURSTG } }</td> 
														<td>${ErrorLogReportList.sum { it.PURUNSTG } }</td>
														<td>${ErrorLogReportList.sum { it.KPSTG } }</td> 
														<td>${ErrorLogReportList.sum { it.KPUNSTG } }</td>	
														<td>${ErrorLogReportList.sum { it.cpchqclr } }</td>																			
													</tr>
												</g:if>
												<g:else>
													<td colspan="10" align="center"><div class="error">No Data Found</div></td>
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
