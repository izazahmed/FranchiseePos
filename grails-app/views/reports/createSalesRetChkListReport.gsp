<%-- 
     -- File Name: createSalesRetChkListReport
     -- Description: it is used to display SalesRetChkListReport Data
     -- Author(s): CTE 
     -- Date: 18/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 18/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Sales Return CheckList Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Sales Return CheckList Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Date</strong></td>
													<td><strong>Voucher No</strong></td>														
													<td><strong>Label</strong></td>													
													<td><strong>Sale Date</strong></td>	
													<td><strong>Item Cd</strong></td>
													<td><strong>Sub Cd</strong></td>														
													<td><strong>Pu</strong></td>													
													<td><strong>Nang</strong></td>
													<td><strong>Pcs</strong></td>
													<td><strong>Gross</strong></td>	
													<td><strong>Nett</strong></td>
													<td><strong>Amount</strong></td>
													<td><strong>Customer Name</strong></td>														
													<td><strong>Emp No</strong></td>													
													<td><strong>Adv No</strong></td>
													<td><strong>Adv Date</strong></td>
													<td><strong>EBSOrderNo</strong></td>	
													<td><strong>Vat</strong></td>
													<td><strong>Excise</strong></td>
													<td><strong>Old Voucher No</strong></td>																									
												</tr>
											</thead>
											<tbody>		
												<g:each in="${salesRetChkList}" status="i" var="salesRetChkListInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${salesRetChkListInst?.dt_voucher_date}</td>
														<td>${salesRetChkListInst?.vc_voucher_no}</td>
														<td>${salesRetChkListInst?.vc_label_no}</td>
														<td>${salesRetChkListInst?.dt_ref_sale_voucher_date}</td>
														<td>${salesRetChkListInst?.vc_item_code}</td>
														<td>${salesRetChkListInst?.vc_sub_code}</td>
														<td>${salesRetChkListInst?.vc_purity}</td>
														<td>${salesRetChkListInst?.nu_nang}</td>
														<td>${salesRetChkListInst?.nu_nang_pcs}</td>
														<td>${salesRetChkListInst?.nu_gross_wtt}</td>
														<td>${salesRetChkListInst?.nu_nett_wtt}</td>
														<td>${salesRetChkListInst?.nu_amount}</td>
														<td>${salesRetChkListInst?.vc_cust_fname}${salesRetChkListInst?.vc_cust_mname}${salesRetChkListInst?.vc_cust_lname}</td>														
														<td>${salesRetChkListInst?.vc_emp_code}</td>
														<td>${salesRetChkListInst?.vc_adv_ord_no}</td>
														<td>${salesRetChkListInst?.dt_adv_ord_date}</td>
														<td>${salesRetChkListInst?.vc_ebs_order_no}</td>
														<td>${salesRetChkListInst?.nu_vat}</td>
														<td>10</td>
														<td>123</td>														
													</tr>	
												</g:each>	
												<g:if test="${salesRetChkList}">													
													<tr>
														<td><strong>Totals..</strong></td>
														<td colspan="8">${salesRetChkList.sum { it.nu_nang } }</td> 
														<td colspan="9">${salesRetChkList.sum { it.nu_nang_pcs } }</td>	
														<td colspan="10">${salesRetChkList.sum { it.nu_gross_wtt } }</td> 
														<td colspan="11">${salesRetChkList.sum { it.nu_nett_wtt } }</td>	
														<td colspan="12">${salesRetChkList.sum { it.nu_amount } }</td> 
														<td colspan="17">${salesRetChkList.sum { it.nu_vat } }</td>	
														<td colspan="18">${salesRetChkList.sum { 10 } }</td>											
													</tr>
												</g:if>
												<g:else>
													<td colspan="22" align="center"><div class="error">No Data Found</div></td>
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
