<%-- 
     -- File Name: createScanningReport
     -- Description: it is used to display ScanningReport Data
     -- Author(s): CTE 
     -- Date: 10/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 10/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE  html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
	<meta name='layout' content='mainerphq' />
<title>Scanning Report</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-dtCrossAdvSettle" class="content scaffold-list" role="main">
							<h1>Scanning Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Label No</strong></td>
													<td><strong>Type of Label</strong></td>																
												</tr>
											</thead>
											<tbody>		
											<B>Labels Not Found in Item Master</B>
												<g:each in="${mainScaList}" status="i" var="mainScaInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${mainScaInst?.VC_LABEL_NO}</td>
														<td>${mainScaInst?.LABEL_TYPE}</td>
													</tr>	
												</g:each>																											
											</tbody>
										</table>										
										<table class="table">
											<thead>
												<tr>
													<td><strong>Label No</strong></td>
													<td><strong>Pu</strong></td>	
													<td><strong>Nang Pieces</strong></td>
													<td><strong>Gross</strong></td>			
													<td><strong>Net</strong></td>
													<td><strong>Crt</strong></td>	
													<td><strong>MakingCharges S</strong></td>
													<td><strong>Karigar Cod</strong></td>															
												</tr>
											</thead>
											<tbody>		
											<B>Labels Not Found in Physical Stock</B>
												<g:each in="${phyScaList}" status="i" var="phyScaInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${phyScaInst?.LABEL_NUMBER}</td>
														<td>${phyScaInst?.PURITY}</td>
														<td>${phyScaInst?.PIECES}</td>
														<td>${phyScaInst?.GROSS_WEIGHT}</td>
														<td>${phyScaInst?.NET_WEIGHT}</td>
														<td>${phyScaInst?.CARAT_WEIGHT}</td>
														<td>${phyScaInst?.TOTAL_AMOUNT_SP}</td>
														<td>${phyScaInst?.KARIGAR_CODE}</td>
													</tr>	
												</g:each>																											
											</tbody>
										</table>							
										<table class="table">
											<thead>
												<tr>
													<td><strong>Label No</strong></td>
													<td><strong>Pu</strong></td>	
													<td><strong>Nang Pieces</strong></td>
													<td><strong>Gross</strong></td>			
													<td><strong>Net</strong></td>
													<td><strong>Crt</strong></td>	
													<td><strong>MakingCharges S</strong></td>
													<td><strong>Karigar Cod</strong></td>															
												</tr>
											</thead>
											<tbody>		
											<B>Labels Having Different Department</B>
												<g:each in="${deptScaList}" status="i" var="deptScaInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${deptScaInst?.VC_LABEL_NO}</td>
														<td>${deptScaInst?.VC_PURITY}</td>
														<td>${deptScaInst?.NU_NANG_PCS}</td>
														<td>${deptScaInst?.NU_GROSS_WTT}</td>
														<td>${deptScaInst?.NU_NETT_WTT}</td>
														<td>${deptScaInst?.NU_CARRAT_WTT}</td>
														<td>${deptScaInst?.NU_MAKING_CHARGES}</td>
														<td>${deptScaInst?.VC_KAR_CODE}</td>
													</tr>	
												</g:each>																											
											</tbody>
										</table>	
										<table class="table">
											<thead>
												<tr>
													<td><strong>Label No</strong></td>
													<td><strong>Pu</strong></td>	
													<td><strong>Nang Pieces</strong></td>
													<td><strong>Gross</strong></td>			
													<td><strong>Net</strong></td>
													<td><strong>Crt</strong></td>	
													<td><strong>MakingCharges S</strong></td>
													<td><strong>Karigar Cod</strong></td>															
												</tr>
											</thead>
											<tbody>		
											<B>Labels Having Different Purity</B>
												<g:each in="${purScaList}" status="i" var="purScaInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${purScaInst?.VC_LABEL_NO}</td>
														<td>${purScaInst?.VC_PURITY}</td>
														<td>${purScaInst?.NU_NANG_PCS}</td>
														<td>${purScaInst?.NU_GROSS_WTT}</td>
														<td>${purScaInst?.NU_NETT_WTT}</td>
														<td>${purScaInst?.NU_CARRAT_WTT}</td>
														<td>${purScaInst?.NU_MAKING_CHARGES}</td>
														<td>${purScaInst?.VC_KAR_CODE}</td>
													</tr>	
												</g:each>																											
											</tbody>
										</table>		
										<table class="table">
											<thead>
												<tr>
													<td><strong>Label No</strong></td>
													<td><strong>Pu</strong></td>	
													<td><strong>Nang Pieces</strong></td>
													<td><strong>Gross</strong></td>			
													<td><strong>Net</strong></td>
													<td><strong>Crt</strong></td>	
													<td><strong>MakingCharges S</strong></td>
													<td><strong>Karigar Cod</strong></td>															
												</tr>
											</thead>
											<tbody>		
											<B>Labels Having Different Item</B>
												<g:each in="${itemScaList}" status="i" var="itemScaInst">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${itemScaInst?.VC_LABEL_NO}</td>
														<td>${itemScaInst?.VC_PURITY}</td>
														<td>${itemScaInst?.NU_NANG_PCS}</td>
														<td>${itemScaInst?.NU_GROSS_WTT}</td>
														<td>${itemScaInst?.NU_NETT_WTT}</td>
														<td>${itemScaInst?.NU_CARRAT_WTT}</td>
														<td>${itemScaInst?.NU_MAKING_CHARGES}</td>
														<td>${itemScaInst?.VC_KAR_CODE}</td>
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
