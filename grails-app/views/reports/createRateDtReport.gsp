<%-- 
     -- File Name: createRateDtReport
     -- Description: it is used to display RateDtReport Data
     -- Author(s): CTE 
     -- Date: 20/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 20/03/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.DtRateDetail" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Rate Details Report</title>
</head>
	<body>
		<div class="container-fluid content-height">
			<div class="col-md-12 page-header">
				<div class="row">
					<div class="dashboard_detail_bg">
						<div class="col-md-12">
							<div id="list-dtRateDetail" class="content scaffold-list" role="main">
								<h1>Rate Details Report</h1>
								<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
											<table class="table">
												<thead>
													<tr>
														<g:sortableColumn property="brName" title="${message(code: 'brMstTab.brName.label', default: 'Branch Name')}" />
														<g:sortableColumn property="dtModDate" title="${message(code: 'DtRateDetail.dtModDate.label', default: 'Rate Date')}" />
														<g:sortableColumn property="vcPurity" title="${message(code: 'DtRateDetail.vcPurity.label', default: 'Purity')}" />
														<g:sortableColumn property="nuRate" title="${message(code: 'DtRateDetail.nuRate.label', default: 'Rate')}" />
													</tr>
												</thead>
												<tbody>
												<g:if test="${dtRateDetailResult}">
												<g:each in="${dtRateDetailResult}" status="i" var="rtDtInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${rtDtInst?.BR_NAME}</td>
														<td>${rtDtInst?.DT_MOD_DATE}</td>
														<td>${rtDtInst?.VC_PURITY}</td>
														<td>${rtDtInst?.NU_RATE}</td>					
													</tr>
												</g:each>				
												</g:if>
												<g:else>
													<td colspan="4" align="center"><div class="error">No Data Found</div></td>
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
