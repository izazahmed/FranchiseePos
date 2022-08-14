<%-- 
     -- File Name: createChkListReport
     -- Description: it is used to display CheckList Report Data
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Abhijit      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.HdCash" %>
<%@ page import="com.tbz.franchisee.DtCashPayment" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Sales Check List (Item Wise/Customer Wise) Report</title>
	</head>
	<body>
		<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-hdCash" class="content scaffold-list" role="main">
						<h1>Sales Check List (Item Wise/Customer Wise) Report</h1>
						<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table" width="100%">
											<thead>
												<tr>
													<td><strong>Vou Date</strong></td>
													<td><strong>Voucher No</strong></td>
													<td><strong>Stage</strong></td>
													<td><strong>Sales Type</strong></td>
													<td><strong>Sales</strong></td>
													<td><strong>Bill Amount</strong></td>
													<td><strong>Label No</strong></td>
													<td><strong>Item Code</strong></td>
													<td><strong>Sub Code</strong></td>
													<td><strong>Pu</strong></td>
													<td><strong>Nang</strong></td>
													<td><strong>Pcs</strong></td>
													<td><strong>Gross Wt.</strong></td>
													<td><strong>Net Wt.</strong></td>
													<td><strong>Rate</strong></td>
													<td><strong>Customer Name</strong></td>
													<td><strong>EBS Order No</strong></td>
												</tr>
											</thead>
											<tbody>
											<g:each in="${salesChkList}" status="i" var="salesCheckListInst">
												<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
													<td>${salesCheckListInst?.dt_voucher_date}</td>
													<td>${salesCheckListInst?.vc_voucher_no}</td>													
													<td>${salesCheckListInst?.ch_stage}</td>	
													<td>${salesCheckListInst?.vc_sale_type}</td>
													<td>${salesCheckListInst?.s}</td>							
													<td>${salesCheckListInst?.nu_bill_amount}</td>	
													<td>${salesCheckListInst?.vc_label_no}</td>
													<td>${salesCheckListInst?.vc_item_code}</td>													
													<td>${salesCheckListInst?.vc_sub_code}</td>	
													<td>${salesCheckListInst?.vc_purity1}</td>
													<td>${salesCheckListInst?.nu_nang}</td>							
													<td>${salesCheckListInst?.nu_nang_pcs}</td>	
													<td>${salesCheckListInst?.nu_gross_wtt}</td>
													<td>${salesCheckListInst?.nu_nett_wtt}</td>													
													<td>${salesCheckListInst?.nu_rate}</td>	
													<td>${salesCheckListInst?.vc_design_no}</td>
													<td>${salesCheckListInst?.vc_ebs_order_no}</td>			
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