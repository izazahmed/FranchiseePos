<%-- 
     -- File Name: createKatanWadharaReport
     -- Description: it is used to display KatanWadharaReport Data
     -- Author(s): CTE 
     -- Date: 17/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 17/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Katan Wadhara Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Katan Wadhara Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<th>Voucher No</th>
													<th>Voucher Date</th>														
													<th>Label No</th>													
													<th>Deptt</th>	
													<th>Item Code</th>
													<th>Purity</th>														
													<th>Nang</th>													
													<th>Pcs</th>
													<th>Gross Wtt</th>
													<th>Nett Wtt</th>	
													<th>Making Chrg</th>
													<th>Kar</th>
													<th>Ref Label</th>																									
												</tr>
											</thead>
											<tbody>		
												<g:each in="${katanWadharaList}" status="i" var="katanWadharaListInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${katanWadharaListInst?.vc_voucher_no}</td>
														<td>${katanWadharaListInst?.dt_voucher_date}</td>
														<td>${katanWadharaListInst?.vc_label_no}</td>
														<td>${katanWadharaListInst?.VC_DEPTT_DESC}</td>
														<td>${katanWadharaListInst?.VC_ITEM_CODE}</td>
														<td>${katanWadharaListInst?.vc_purity}</td>
														<td>${katanWadharaListInst?.nu_nang}</td>
														<td>${katanWadharaListInst?.nu_nang_pcs}</td>
														<td>${katanWadharaListInst?.nu_gross_wtt}</td>
														<td>${katanWadharaListInst?.nu_nett_wtt}</td>
														<td>${katanWadharaListInst?.NU_MAKING_CHARGES}</td>
														<td>${katanWadharaListInst?.VC_KAR_CODE}</td>
														<td>${katanWadharaListInst?.VC_KW_LABEL}</td>														
													</tr>	
												</g:each>	
												<g:if test="${katanWadharaList}">													
													<tr>
														<td colspan="5"><strong>Total :</strong></td>
														<td colspan="8">${katanWadharaList.sum { it.nu_gross_wtt } }</td> 
														<td colspan="9">${katanWadharaList.sum { it.nu_nett_wtt } }</td>											
													</tr>
												</g:if>
												<g:else>
													<tr>
														<td align="center" colspan="13"><div class="error">No Data Found</div></td>
													</tr>
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
