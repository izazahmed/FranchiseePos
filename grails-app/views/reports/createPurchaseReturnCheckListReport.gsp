<%-- 
     -- File Name: createPurchaseReturnCheckListReport
     -- Description: it is used to display Check Clearance Report Data
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Purchase Return Checklist Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>Purchase Return Checklist Report</h1>
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
														<td><strong>Bill Date</strong></td>						
														<td><strong>Bill No.</strong></td>
														<td><strong>Stage</strong></td>
														<td><strong>Sales Type</strong></td>
														<td><strong>Item</strong></td>
														<td><strong>Purity</strong></td>
														<td><strong>Nang</strong></td>
														<td><strong>Pcs</strong></td>
														<td><strong>Gross Wt.</strong></td>
														<td><strong>Net Wt.</strong></td>
														<td><strong>Carat Wt.</strong></td>
														<td><strong>Selling Price</strong></td>
														<td><strong>Stone</strong></td>
														<td><strong>Pcs.</strong></td>
														<td><strong>Carat</strong></td>
														<td><strong>Cust Name</strong></td>
													</tr>
												</thead>
												<tbody>
													<g:each in="${purchaseReturnCheckList}" status="i" var="purchaseReturnCheckListValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${purchaseReturnCheckListValInst?.DT_VOUCHER_DATE}</td>
															<td>${purchaseReturnCheckListValInst?.HEADER_ID}</td>	
															<td>${purchaseReturnCheckListValInst?.CH_STAGE}</td>
															<td>${purchaseReturnCheckListValInst?.SALE_TYPE}</td>
															<td>${purchaseReturnCheckListValInst?.CAT}</td>
															<td>${purchaseReturnCheckListValInst?.PURITY}</td>
															<td>${purchaseReturnCheckListValInst?.NANG}</td>
															<td>${purchaseReturnCheckListValInst?.PCS}</td>
															<td>${purchaseReturnCheckListValInst?.GRT}</td>
															<td>${purchaseReturnCheckListValInst?.NET}</td>
															<td>${purchaseReturnCheckListValInst?.CRT}</td>
															<td>${purchaseReturnCheckListValInst?.SELLING}</td>
															<td>${purchaseReturnCheckListValInst?.PURITY}</td>
															<td>${purchaseReturnCheckListValInst?.SELLING}</td>
															<td>${purchaseReturnCheckListValInst?.CRT}</td>
															<td>${purchaseReturnCheckListValInst?.OPERATING_UNIT_NAME}</td>
														</tr>
													</g:each>
													<tr>
														<td colspan="6"><strong>Total :</strong></td>
														<td>${purchaseReturnCheckList?.sum { it.NANG}}</td>
														<td>${purchaseReturnCheckList?.sum { it.PCS}}</td>
														<td>${purchaseReturnCheckList?.sum { it.GRT}}</td>
														<td>${purchaseReturnCheckList?.sum { it.NET}}</td>														
														<td></td>
														<td>${purchaseReturnCheckList?.sum { it.SELLING}}</td>
														<td colspan="4"></td>
													</tr>
												</tbody>
												</table>
											</div>
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
													<g:each in="${stonePurchaseReturnCheckList}" status="i"
														var="stonePurchaseReturnCheckListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${stonePurchaseReturnCheckListInst?.STONE_TYPE}
															</td>
															<td>
																${stonePurchaseReturnCheckListInst?.STONECRT}
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
	</body>
</html>