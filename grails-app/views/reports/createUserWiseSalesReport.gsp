<%-- 
     -- File Name: createUserwiseSalesReport
     -- Description: it is used to display Check Clearance Report Data
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Userwise Sales Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>Userwise Sales Report</h1>
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
										<!-- Table -->
										<div class="col-md-8 page-header">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Showroom</strong></td>						
														<td><strong>Username</strong></td>
														<td><strong>Voucher No.</strong></td>
														<td><strong>Voucher Date</strong></td>
														<td><strong>Customer Name</strong></td>													
													</tr>
												</thead>
												<tbody>			
													<g:each in="${userWiseSalesList}" status="i" var="userwiseSalesValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${userwiseSalesValInst?.NAME}</td>	
															<td>${userwiseSalesValInst?.VC_USER_NAME}</td>
															<td>${userwiseSalesValInst?.VC_VOUCHER_NO}</td>
															<td>${userwiseSalesValInst?.DT_VOUCHER_DATE}</td>
															<td>${userwiseSalesValInst?.Customer_Name}</td>
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
		</div>
	</body>
</html>