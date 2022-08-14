<%-- 
     -- File Name: createPurchageChkListReport
     -- Description: it is used to display PurchageChkListReport Data
     -- Author(s): CTE 
     -- Date: 15/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/03/2016	   Abhijit      	Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtBill" %>
<%@ page import="com.tbz.franchisee.HdBill" %>

<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Purchage Check List Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Purchage Check List Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${custMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${custMstInstance}" var="error">
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
														<td><strong>Date</strong></td>
														<td><strong>BILL No</strong></td>
														<td><strong>Stage</strong></td>
														<td><strong>Adv No</strong></td>
														<td><strong>Adv Date</strong></td>
														<td><strong>Supplier Name</strong></td>
														<td><strong>Amount</strong></td>
														<td><strong>Other Chrg</strong></td>
														<td><strong>Sub</strong></td>
														<td><strong>Gold Rate</strong></td>
														<td><strong>Pu</strong></td>
														<td><strong>Nang</strong></td>
														<td><strong>Gross Wt.</strong></td>
														<td><strong>Net Wt.</strong></td>
														<td><strong>Item</strong></td>
													</tr>
												</thead>
												<tbody>
												<g:each in="${purchageChkList}" status="i" var="purCheckListInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td nowrap="nowrap">${purCheckListInst?.DT_BILL_DATE}</td>
														<td nowrap="nowrap">${purCheckListInst?.SUBSTR_A_VC_BILL_NO_3_8}</td>													
														<td nowrap="nowrap">${purCheckListInst?.CH_STAGE}</td>	
														<td nowrap="nowrap">${purCheckListInst?.VC_ADV_ORD_NO}</td>
														<td nowrap="nowrap">${purCheckListInst?.DT_ADV_ORD_DATE}</td>							
														<td nowrap="nowrap">${purCheckListInst?.VC_SUPPLIER_FNAME}</td>	
														<td nowrap="nowrap">${purCheckListInst?.NU_AMOUNT}</td>
														<td nowrap="nowrap">${purCheckListInst?.NU_OTHER_CHARGES}</td>													
														<td nowrap="nowrap">${purCheckListInst?.VC_SUB_CODE}</td>	
														<td nowrap="nowrap">${purCheckListInst?.NU_RATE}</td>
														<td nowrap="nowrap">${purCheckListInst?.VC_PURITY}</td>							
														<td nowrap="nowrap">${purCheckListInst?.NU_NANG}</td>	
														<td nowrap="nowrap">${purCheckListInst?.PCS}</td>
														<td nowrap="nowrap">${purCheckListInst?.NU_GROSS_WTT}</td>													
														<td nowrap="nowrap">${purCheckListInst?.NU_NETT_WTT}</td>	
														<td nowrap="nowrap">${purCheckListInst?.VC_ITEM_CODE}</td>
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