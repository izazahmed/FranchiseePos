<%-- 
     -- File Name: createFranchiseeWeightWiseStockReport
     -- Description: it is used to display Mis Report Data
     -- Author(s): CTE 
     -- Date: 07/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 07/03/2016	   Sachin      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Franchisee Ideal Stock Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Franchisee Ideal Stock Report</h1>
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
										<!-- Table -->
										<div class="col-md-8 page-header">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Bill Date</strong></td>
														<td><strong>BIll No.</strong></td>
														<td><strong>Stsge</strong></td>
														<td><strong>Sale Type</strong></td>
														<td><strong>Item</strong></td>
														<td><strong>Purity</strong></td>
														<td><strong>Nang</strong></td>
														<td><strong>Pieces</strong></td>
														<td><strong>Gross Wt.</strong></td>
														<td><strong>Net Wt.</strong></td>
														<td><strong>Carat wt.</strong></td>
														<td><strong>Selling price</strong></td>
														<td><strong>Stone</strong></td>
														<td><strong>Pcs</strong></td>
														<td><strong>Carat</strong></td>
														<td><strong>Cust Name</strong></td>
													</tr>
												</thead>
												<tbody>
													<g:each in="${FranchiseePurchaseCheckReportList}" status="i"
														var="franchiseePurchaseCheckInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${franchiseePurchaseCheckInst?.DT_VOUCHER_DATE}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.HEADER_ID}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.CH_STAGE}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.SALE_TYPE}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.VC_CATEGORY}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.PURITY}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.NANG}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.PCS}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.GRT}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.NET}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.CRT}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.SELLING}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.NANG}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.NANG}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.CRT}
															</td>
															<td>
																${franchiseePurchaseCheckInst?.FR_NAME}
															</td>
														</tr>
													</g:each>
												</tbody>
											</table>											
										</div>
									</div>
								</div>
							</div>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<!-- Table -->
										<div class="col-md-8 page-header">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Stone Type</strong></td>
														<td><strong>Stone Carat</strong></td>
													</tr>
												</thead>
												<tbody>
													<g:each in="${FranchiseeStonePurchaseCheckReportList}" status="i" var="franchiseePurchaseStoneCheckInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${franchiseePurchaseStoneCheckInst?.STONE_TYPE}
															</td>
															<td>
																${franchiseePurchaseStoneCheckInst?.STONECRT}
															</td>
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