
<%-- 
     -- File Name: view rate parameter
     -- Description: it is used to do update the rate parameter data
     -- Author(s): CTE 
     -- Date: 04/04/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 17/03/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Rate Parameter</title>
	<script type="text/javascript">
		1$(document).ready(function(){
			$('.enableDisable').attr('disabled', 'disabled');						
			$('#catButId').attr('disabled', 'disabled');
			$('#purButId').attr('disabled', 'disabled');
		});
		function clearAllFields(){
		 	if(confirm("Sure You Want To Cancel?")){
		 		$("#spinner").show();
			 	document.getElementById("viewRateParameter").action ="index";
		    	document.getElementById("viewRateParameter").submit();
		 	}
		 } 
		 function returnToMainPage(){
			 if(confirm("Sure You Want To Exit?")){
			 	$("#spinner").show();
			 	document.getElementById("viewRateParameter").action ="${request.getContextPath()}/company/mainModule";
		    	document.getElementById("viewRateParameter").submit();
			 }
		 }		
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="add.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="view.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="save.png" /></a>
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a>
							<a href="#" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div id="list-mstRateParameter" class="content scaffold-list" role="main">
							<h1>Rate Parameter</h1>
							<g:if test="${flash.message}">
								<div class="message" role="status">${flash.message}</div>
							</g:if>
							<form name="viewRateParameter" id="viewRateParameter">
							<g:hiddenField name="moduleCode" id="moduleCode"  value="${session.moduleValue}"/>
								<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="col-md-8 page-header">
											<table class="table">
												<tr>
													<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Branch</strong></td>
													<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Category</strong></td>
													<td></td>
													<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Purity</strong></td>
													<td></td>
													<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rate Percentage</strong></td>
													<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add Parameter</strong></td>
												</tr>
												<tbody id="indTbdId">
													<g:each in="${mstRateParameterResult}" status="i" var="mstRateParameterInstance">
														<tr width="100%">
															<td><g:select name="brName" id="${mstRateParameterInstance?.BR_CODE}" class="form-control enableDisable" from="${mstRateParameterResult?.BR_NAME}" value="${mstRateParameterInstance?.BR_NAME}"/></td>
															<td><g:textField name="description" id="description" class="form-control enableDisable" value="${mstRateParameterInstance?.VC_CATEGORY}"/></td>
															<td><button type="button" class="active_btn" data-toggle="modal" data-target="#catButId">...</button></td>
															<td><g:textField name="pur" id="pur" class="form-control enableDisable" value="${mstRateParameterInstance?.VC_PURITY}"/></td>
															<td><button type="button" class="active_btn" data-toggle="modal" data-target="#purButId">...</button></td>
															<td><g:textField name="nuRatePercentage" id="nuRatePercentage" class="form-control enableDisable" value="${mstRateParameterInstance?.NU_RATE_PERCENTAGE}"/></td>
															<td><g:textField name="nuAdditionalParameter" id="nuAdditionalParameter" class="form-control enableDisable" value="${mstRateParameterInstance?.NU_ADDITIONAL_PARAMETER}"/></td>
														</tr>
													</g:each>
												</tbody>												
											</table>
										</div>
									</div>	
								</div>
								</div>	
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>