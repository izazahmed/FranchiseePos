
<div class="container-fluid content-height">
		<div class="col-md-8 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="list-hdCash" class="content scaffold-list" role="main">
							<h1 style="color: blue;">User Wise Bill Count</h1>
						<div class="content-bg pull-left">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="col-md-8 page-header">
										<table class="table" border="1">
											<thead>
												<tr>
													<td><strong>Branch Name</strong></td>
													<td><strong>User Name</strong></td>
													<td><strong>Category</strong></td>
												</tr>
											</thead>
											<tbody>
												<g:if test="${getUWBCdata}">
													<g:each in="${getUWBCdata}" status="i" var="uwbcDataInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${uwbcDataInst?.BR_NAME}</td>
															<td>${uwbcDataInst?.VC_USER_NAME}</td>																								
															<td>${uwbcDataInst?.VC_CATEGORY}</td>
														</tr>
													</g:each>
													<tr>
														<td colspan="3"><strong>Count : </strong>${getUWBCdata?.get(0)?.CNT}</td>	
													</tr>
												</g:if>
												<g:else>
													<div style="font-size: 16px; color: red">No Data Found</div>
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
</div>