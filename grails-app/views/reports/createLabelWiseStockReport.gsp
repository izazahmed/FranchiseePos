<%-- 
     -- File Name: createMisReport
     -- Description: it is used to display Mis Report Data
     -- Author(s): CTE 
     -- Date: 07/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 07/03/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.TbzPosItemcodeLabelV" %>
<%@ page import="com.tbz.franchisee.XxtbzComponentDetailVNew" %>

<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Label Wise Stock Report</title>
</head>
<body>
	<div class="container-fluid content-height">		
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-custMst" class="content scaffold-create">
							<h1>Label Wise Stock Report</h1>
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
														<td><strong>Product Category</strong></td>
														<td><strong>Label Number</strong></td>
														<td><strong>Style</strong></td>
														<td><strong>Sub Style</strong></td>
														<td><strong>Metal Colour</strong></td>
														<td><strong>purity</strong></td>
														<td><strong>Locator</strong></td>
														<td><strong>Nang</strong></td>
														<td><strong>Pcs</strong></td>
														<td><strong>Grswt</strong></td>
														<td><strong>Netwt</strong></td>
														<td><strong>Crtwt</strong></td>
														<td><strong>Stone Type</strong></td>
														<td><strong>Pcs_St</strong></td>
														<td><strong>Crt_St</strong></td>
													</tr>
												</thead>
												<tbody>
													<g:each in="${labelWiseStockList}" status="i" var="labelWiseStockListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${labelWiseStockListInst?.PRODUCT_CATEGORY}
															</td>
															<td>
																${labelWiseStockListInst?.LABEL_NUMBER}
															</td>
															<td>
																${labelWiseStockListInst?.STYLE}
															</td>
															<td>
																${labelWiseStockListInst?.SUB_STYLE}
															</td>
															<td>
																${labelWiseStockListInst?.METAL_COLOUR}
															</td>
															<td>
																${labelWiseStockListInst?.PURITY}
															</td>
															<td>
																${labelWiseStockListInst?.LOCATOR}
															</td>
															<td>
																${labelWiseStockListInst?.NANG1}
															</td>
															<td>
																${labelWiseStockListInst?.PCS1}
															</td>
															<td>
																${labelWiseStockListInst?.GRSWT1}
															</td>
															<td>
																${labelWiseStockListInst?.NETWT1}
															</td>
															<td>
																${labelWiseStockListInst?.crtwt1}
															</td>
															<td>
																${labelWiseStockListInst?.STONE_TYPE}
															</td>
															<td>
																${labelWiseStockListInst?.PCS_ST}
															</td>
															<td>
																${labelWiseStockListInst?.CRTWT_ST}
															</td>
														 </tr>
														
													</g:each>													
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<h1>Stone Summary</h1>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<!-- Table -->
										<div class="col-md-8 page-header">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Stone Type</strong></td>
														<td><strong>Pieces</strong></td>
														<td><strong>Carat</strong></td>
											
													</tr>
												</thead>
												<tbody>
													<g:each in="${labelWiseStockSumList}" status="i" var="labelWiseStockSumListInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>
																${labelWiseStockSumListInst?.STONE_TYPE}
															</td>
															<td>
																${labelWiseStockSumListInst?.PCS_ST}
															</td>
															<td>
																${labelWiseStockSumListInst?.CRTWT_ST}
															</td>								
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