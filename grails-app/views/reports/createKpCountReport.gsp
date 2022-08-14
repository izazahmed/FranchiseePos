<%-- 
     -- File Name: createKpCountReport
     -- Description: it is used to display KalpavrukshCount Report Data
     -- Author(s): CTE 
     -- Date: 03/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 03/03/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.SchemeMst" %>
<%@ page import="com.tbz.franchisee.CustMst" %>
<%@ page import="com.tbz.franchisee.RegDt" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>KP count Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>KP count Report</h1>
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
														<g:sortableColumn property="custId" title="${message(code: 'CustMst.custId.label', default: 'Customer Id')}" />
														<g:sortableColumn property="custName" title="${message(code: 'CustMst.custName.label', default: 'Customer Name')}" />
													</tr>
												</thead>
												<tbody>			
												<g:if test="${custMstResult}">
													<g:each in="${custMstResult}" status="i" var="custMstInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${custMstInst?.CUST_ID}</td>
															<td>${custMstInst?.CUST_NAME}</td>				
														</tr>					
													</g:each>				
													<g:each in="${countVal}" status="i" var="countValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td colspan="2">Count ${countValInst?.CNT}</td>
														</tr>
													</g:each>	
												</g:if>
												<g:else>
													<td colspan="2" align="center"><div class="error">No Data Found</div></td>
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
