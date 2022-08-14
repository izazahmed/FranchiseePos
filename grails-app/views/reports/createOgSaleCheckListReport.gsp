<%-- 
     -- File Name: createOgSaleCheckListReport
     -- Description: it is used to display OgSaleCheckListReport Data
     -- Author(s): CTE 
     -- Date: 19/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 19/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta name='layout' content='mainerphq' />
<title>OG Sales CheckList Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="col-md-8 page-header">
										<strong>${mainHead.get("coName")}</strong><br>	
										<strong>${ogSaleAddress[0]}</strong>
									<h1><center>Franchisee OG Sales Checklist</center></h1>
										<div class="content-bg pull-left">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="col-md-8 page-header">
														<table class="table">		
															<tr>
																<td><strong>Organization :</strong>${mainHead.get("coName")}</td>&nbsp;&nbsp;<td><strong>Print Time :</strong>${mainHead.get("today")}</td>
															</tr>
															<tr>
																<td><strong>From Date :</strong>${mainHead.get("fromDate")}</td>
																<td><strong>To Date :</strong>${mainHead.get("toDate")}</td>
																<td><strong>For Category :</strong>${mainHead.get("cat")}</td>
															</tr>
														</table>
													</div>
												</div>
											</div>
										</div>										
										<div class="content-bg pull-left">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="col-md-8 page-header">
														<table class="table">
															<thead>
																<tr>
																	<td><strong>Bill Date</strong></td>
																	<td><strong>Bill Number</strong></td>					
																	<td><strong>Item Name</strong></td>
																	<td><strong>Purity</strong></td>
																	<td><strong>Nang</strong></td>														
																	<td><strong>Pices</strong></td>
																	<td><strong>Gross wt.</strong></td>					
																	<td><strong>Net Wt.</strong></td>
																	<td><strong>Carret Wt.</strong></td>
																	<td><strong>Amount</strong></td>													
																	<td><strong>Stone Cd</strong></td>
																	<td><strong>Stone Pcs</strong></td>
																	<td><strong>Stone Crt</strong></td>										
																</tr>
															</thead>
															<tbody>			
																<g:each in="${ogSaleCheckMap?.resultListOne}" status="i" var="ogSaleCheckListInst">
																	<tr>
																		<td>${ogSaleCheckListInst?.billDt}</td>
																		<td>${ogSaleCheckListInst?.billNo}</td>	
																		<td>${ogSaleCheckListInst?.itemName}</td>
																		<td>${ogSaleCheckListInst?.pur}</td>
																		<td>${ogSaleCheckListInst?.nang}</td>						
																		<td>${ogSaleCheckListInst?.pcs}</td>	
																		<td>${ogSaleCheckListInst?.gwt}</td>
																		<td>${ogSaleCheckListInst?.nwt}</td>	
																		<td>${ogSaleCheckListInst?.cwt}</td>
																		<td>${ogSaleCheckListInst?.amt}</td>																								
																		<td>${ogSaleCheckListInst?.stone_type}</td>
																		<td>${ogSaleCheckListInst?.stonepcs}</td>	
																		<td>${ogSaleCheckListInst?.stonecrt}</td>																													
																	</tr>															
																</g:each>	
																	<tr>
																		<td colspan="4"><strong>Total :</strong></td>
																		<td>${ogSaleCheckMap?.resultListOne.sum { it.nang } }</td>
																		<td>${ogSaleCheckMap?.resultListOne.sum { it.pcs } }</td>	
																		<td>${ogSaleCheckMap?.resultListOne.sum { it.gwt } }</td>
																		<td>${ogSaleCheckMap?.resultListOne.sum { it.nwt } }</td>
																		<td>${ogSaleCheckMap?.resultListOne.sum { it.cwt } }</td>	
																		<td>${ogSaleCheckMap?.resultListOne.sum { it.amt } }</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>																					
																	</tr>					
														</tbody>				
													</table>	
												</div>	
											</div>
										</div>
									</div>
									<div class="content-bg pull-left">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="col-md-8 page-header">									
														<table class="table">
															<thead>
																<tr>
																	<td><strong>Colour Stone Summary</strong></td>
																</tr>
																<tr>													
																	<td><strong>Stone Cd</strong></td>
																	<td><strong>Stone Pcs</strong></td>
																	<td><strong>Stone Crt</strong></td>										
																</tr>
															</thead>
															<tbody>									
																<g:each in="${ogSaleCheckMap?.resultListTwo}" status="i" var="colourStoneSummaryInst">																	
																	<tr>
																		<td>${colourStoneSummaryInst?.sumsttype}</td>
																		<td>${colourStoneSummaryInst?.sumstpcs}</td>	
																		<td>${colourStoneSummaryInst?.sumstcrt}</td>																			
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
		</div>
	</div>
</div>
</div>
</body>
</html>
