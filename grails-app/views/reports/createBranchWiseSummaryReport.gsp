<%-- 
     -- File Name: branchwiseSummaryReport
     -- Description: it is used to display branchwiseSummary Report Data
     -- Author(s): CTE 
     -- Date: 07/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 07/03/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Branchwise Summary Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Branchwise Summary Report</h1>
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
														<td><strong>Branch Code</strong></td>
														<td><strong>Branch Name</strong></td>
														<td><strong>Amount</strong></td>														
													</tr>
												</thead>
												<tbody>
													<g:each in="${branchwiseSummaryList}" status="i"
														var="branchwiseSummaryReportValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${branchwiseSummaryReportValInst?.BR_CODE}
															</td>
															<td>
																${branchwiseSummaryReportValInst?.BR_NAME}
															</td>
															<td>
																${branchwiseSummaryReportValInst?.AMT}
															</td>
														</tr>
														<g:set var="nuAmount" value="${branchwiseSummaryReportVal.sum { it.AMT} }"></g:set>
													</g:each>
													<tr>
														<td colspan="2"><strong>Total :</strong></td>
														<td>${nuAmount}</td>
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