<%-- 
     -- File Name: create User Role ListReport
     -- Description: it is used to display User Role List Data
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>User Role list Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>User Role list Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
												error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>User</strong></td>						
													<td><strong>Company</strong></td>
													<td><strong>Role</strong></td>
												</tr>
											</thead>
												<tbody>			
												<g:each in="${moduleList}" status="i" var="moduleInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${moduleInst?.vc_user_name}</td>
														<td>${moduleInst?.vc_company_name}</td>																
														<td>${moduleInst?.COMP_CODE}</td>
													</tr>					
												</g:each>																									
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