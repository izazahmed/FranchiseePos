
<%-- 
     -- File Name: view brmst
     -- Description: it is used to do view the branch data
     -- Author(s): CTE 
     -- Date: 09/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 09/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Branch Master</title>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.brCodeCls').attr('disabled', 'disabled');
			$('.brNameCls').attr('disabled', 'disabled');
		});
		function clearAllFields(){
		 	if(confirm("Sure You Want To Cancel?")){
		 		$("#spinner").show();
			 	document.getElementById("viewBranchForm").action ="index";
		    	document.getElementById("viewBranchForm").submit();
		 	}
		 } 
		 function returnToMainPage(){
			if(confirm("Sure You Want To Exit?")){
			 	$("#spinner").show();
			 	document.getElementById("viewBranchForm").action ="${request.getContextPath()}/company/mainModule";
		    	document.getElementById("viewBranchForm").submit();
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
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="save.png" /></a>
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a>
							<a href="#" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>View Company</h1>
							<form name="viewBranchForm" id="viewBranchForm">
								<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
								<div class="content-bg pull-left">
									<div class="col-md-12">
										<div class="panel panel-default">
												<table class="table">
													<tr>
														<td><strong>Branch Code</strong></td>
														<td><strong>Branch Name</strong></td>
													</tr>
													<tbody>													
														<g:each in="${baranchResult}" status="i" var="baranchInstance">
															<tr>
																<td><g:textField name="brCode" value="${baranchInstance?.BR_CODE}" class="form-control brCodeCls" /></td>
																<td><g:textField name="brName" value="${baranchInstance?.BR_NAME}" class="form-control brNameCls" /></td>
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
</body>
</html>