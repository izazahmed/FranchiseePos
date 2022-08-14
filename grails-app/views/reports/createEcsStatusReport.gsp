<%-- 
     -- File Name: createEcsStatusReport
     -- Description: it is used to display Check Clearance Report Data
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
<title>ECS status Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>ECS status Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
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
														<td><strong>Cust Id</strong></td>						
														<td><strong>Installment Amount</strong></td>
														<td><strong>Install No.</strong></td>
														<td><strong>ECS sent on</strong></td>
														<td><strong>Payment Received On</strong></td>
														<td><strong>Receipt No.</strong></td>														
													</tr>
												</thead>
												<tbody>			
													<g:each in="${ecsStatusList}" status="i" var="ecsStatusValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${ecsStatusValInst?.VC_CUST_ID}</td>
															<td>${ecsStatusValInst?.NU_AMOUNT}</td>	
															<td>${ecsStatusValInst?.NU_INSTALL_FOR}</td>
															<td>${ecsStatusValInst?.DT_FIELD1}</td>
															<td>${ecsStatusValInst?.DT_FIELD2}</td>
															<td>${ecsStatusValInst?.VC_FIELD2}</td>
														</tr>														
													</g:each>													
													<tr>
														<td><strong>Total Amount</strong></td>
														<td colspan="6">${ecsStatusList.sum { it.NU_AMOUNT } }</td>
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