<%-- 
     -- File Name: createUWBillCountReport
     -- Description: it is used to display UWBillCountReport Data
     -- Author(s): CTE 
     -- Date: 25/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 25/03/2016	   Abhijit      	Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.HdCash" %>
<%@ page import="com.tbz.franchisee.MkUsers" %>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
<title>User Wise Bill Count Report</title>
</head>
<body>
<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-hdCash" class="content scaffold-list" role="main">
							<h1>User Wise Bill Count</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Branch Name</strong></td>
													<td><strong>User Name</strong></td>
													<td><strong>Category</strong></td>
												</tr>
											</thead>
											<tbody>
												<g:if test="${getUWBCdata}">
													<g:each in="${getUWBCdata}" status="i" var="uwbcDataInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${uwbcDataInst?.BR_NAME}</td>
															<td>${uwbcDataInst?.VC_USER_NAME}</td>																								
															<td>${uwbcDataInst?.VC_CATEGORY}</td>
														</tr>
													</g:each>
													<tr>
														<td colspan="3"><strong>Count : </strong>${getUWBCdata?.get(0)?.CNT}</td>	
													</tr>	
												</g:if>
												<g:else>
													 <td colspan="3" align="center"><div class="error">No Data Found</div></td>
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