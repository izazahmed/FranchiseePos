<%-- 
     -- File Name: index
     -- Description: it is used to do view the form
     -- Author(s): CTE 
     -- Date: 04/04/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 04/04/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Rate master</title>
	<script type="text/javascript">
		var viewVal = "";
		$(document).ready(function(){		
			$("#rateType").attr('disabled', true);
			$("#brListId").attr('disabled', true);    
	    	$("#rateDate").attr("disabled" , "disabled");
	    	$("#rate").attr("disabled" , "disabled");    	
			$("#clearImg").attr("disabled" , 'disabled');
			$("#deleteImg").attr("disabled" , 'disabled');
			$("#exitImg").attr("disabled" , 'disabled');
			$("#saveImg").attr("disabled" , 'disabled');
			$(".clickOnView td:not(.action)").click(function () {
				viewVal =$(this).closest('tr').attr('id');		
				$(".highlight").removeClass('highlight');
				$(this).closest('tr').addClass('highlight');
			}); 		
	    });
	    function returnToMainPage(){
			 if(confirm("Sure You Want To Exit?")){
			 	$("#spinner").show();
			 	document.getElementById("rateMasterIndex").action ="${request.getContextPath()}/company/mainModule";
		    	document.getElementById("rateMasterIndex").submit();
			 }
	 	}
	    function getPerticularViewData(){
		    $("#rateDtlsViewModal .close").click();
			var allVal = [];
			allVal = viewVal.split(",");	
			var date = allVal[0];
			var cat = allVal[1];
			var brId = allVal[2];
			var parameter = {cat:cat,brId:brId,date:date}
			$("#spinner").show();
			$.ajax({
				url: "${request.getContextPath()}/DtRateDetail/getParticularBranchData/",
				async:false,
				data :parameter,
				success : function(response){
				$("#spinner").hide();
					console.log(response.rateData);			
					$("#uploadDateN").html(response);
					$(".enableDisable").attr('disabled', true);			
				}		    					    			
		 	});
		 	$(".clickOnView").removeClass('highlight');
		}
		function goToCreatePage(){
			$("#spinner").show();
		 	document.getElementById("rateMasterIndex").action ="${request.getContextPath()}/DtRateDetail/create";
		   	document.getElementById("rateMasterIndex").submit();
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
						<a href="#" onclick="goToCreatePage();"><asset:image src="add.png" /></a>										
						<a href="#" data-toggle="modal" data-target="#rateDtlsViewModal" data-whatever="@fat"><asset:image src="view.png" /></a>		
						<a href="javascript:void(0)" class="cursordefault"><asset:image src="save.png" /></a> 
						<a href="javascript:void(0)" class="cursordefault"><asset:image src="delete.png" /></a>
						<a href="#" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
					</div>
					<div id="create-dtRateDetail" class="content scaffold-create">
						<h1>Rate master</h1>
						<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<g:hasErrors bean="${mstApprovalAuthInstance}">
							<ul class="errors" role="alert">
								<g:eachError bean="${mstApprovalAuthInstance}" var="error">
									<li
										<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" />
									</li>
								</g:eachError>
							</ul>
						</g:hasErrors>
						<g:form name="rateMasterIndex" id="rateMasterIndex">
							<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
							<div class="content-bg pull-left">
								<div class="row">
									<div class="col-md-8">
										<div class="panel panel-default">
												<table class="table">					
													<tbody>					
														<tr>
															<td colspan="2"><b>Branch Selection</b></td>															
														</tr>	
														<tr>
															<td><g:radio name="branch" checked="checked" value="allBranches" />All Branches</td>
															<td><g:radio name="branch" value="allBranches" />Selected Branches</td>
														</tr>
														<tr>
															<td><b>Rate type</b></td>
															<td>	
																	<div class="select-style2"><g:select name="rateType" from="${rateTypeList}"  tabindex="8"/></div>
																	<button id="brListId" name="brListId" type="button" onclick="rateTypePopup();" class="active_btn">...</button>
															</td>
														</tr>
														<tr>
															<td><b>Rate Date</b></td>
															<td>
																<g:textField name="rateDate" class="form-control" />
															</td>
														</tr>
														<tr>
															<td><b>Rate</b></td>
															<td>
																<g:textField name="rate" class="form-control" value="${dtRateDetailInstance?.rate}" />
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							<g:form>
								<div id="uploadDateN">
									<g:render template="dataDescription" />
								</div>
							</g:form>
							<%-- VIEW POPUP START--%>
							<div class="modal fade" id="rateDtlsViewModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Existing Rate Details List</h4>
										</div>
										<div class="modal-body">
											<table width="100%" id="rateDtlsViewTblId" class="table">
												<thead>
													<tr>
														<th>Date</th>
														<th>Category</th>
														<th>Branch</th>
														<th>Rate</th>
													</tr>
												</thead>
												<tbody>
													<g:each in="${listViewDetails}" status="i" var="listViewInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnView" id="${listViewInst?.DT_MOD_DATE},${listViewInst?.VC_CATEGORY},${listViewInst?.BR_CODE}">
															<td>${listViewInst?.DT_MOD_DATE}</td>
															<td>${listViewInst?.VC_CATEGORY}</td>
															<td>${listViewInst?.BR_NAME}</td>
															<td>
															</td>
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="getPerticularViewData();">OK</button>
											<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- VIEW POPUP END--%>
						</g:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>