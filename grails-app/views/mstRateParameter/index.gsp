<%-- 
     -- File Name: index rate parameter
     -- Description: it is used to do update the rate parameter data
     -- Author(s): CTE 
     -- Date: 04/04/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 11/03/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab"%>
<%@ page import="com.tbz.franchisee.FndFlexValuesVl"%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Rate Parameter</title>
</head>
<body>
<form name="indexRateParameter" id="indexRateParameter">
	<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav" id="nav1">
							<a href="#" id="addRatePara"><asset:image src="add.png" /></a>
							<a href="/FranchiseePos/mstRateParameter/view"><asset:image src="view.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="save.png" /></a>
							<a href="${request.getContextPath()}/mstRateParameter/edit"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="delete.png" /></a>
							<a href="#" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div class="nav" id="nav2" style="display: none;">
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="add.png" /></a> 
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)" onclick="saveRatePar();"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="edit.png" /></a>
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Rate Parameter</h1>
							<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
							<g:if test="${flash.message}"><div class="alert alert-success" role="alert">${flash.message}</div></g:if>
							<g:if test="${flash.warning}"><div class="alert alert-danger" role="alert">${flash.warning}</div></g:if>							
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
											<table class="table">
												<thead>
													<tr>
														<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Branch<span class="redColor">*</span></strong></td>
														<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Category<span class="redColor">*</span></strong></td>
														<td></td>
														<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Purity<span class="redColor">*</span></strong></td>
														<td></td>
														<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rate Percentage<span class="redColor">*</span></strong></td>
														<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add Parameter<span class="redColor">*</span></strong></td>
													</tr>
												</thead>
												<tbody id="indTbdId">
													<g:each in="${sizeResult}" status="i" var="ten">
														<tr id="${ten}" width="100%">
															<td><g:select name="brName" id="brName${ten}" class="form-control enableDisable" disabled="disabled" from="${BrMstTab.list()*.brName}" noSelection="${['':'Select One...']}"/></td>
															<td><g:textField name="description${ten}" id="description${ten}" class="form-control enableDisable" disabled="disabled" /></td>
															<td><button id="butIdCat${ten}" type="button" class="btn btn-info btn-clk enableDisable" data-toggle="modal" data-target="#catModal">...</button></td>
															<td><g:textField name="pur${ten}" id="pur${ten}" class="form-control enableDisable" disabled="disabled"/></td>
															<td><button id="butIdPur${ten}" type="button" class="btn btn-info btn-clk enableDisable" data-toggle="modal" data-target="#purModal">...</button></td>
															<td><g:textField name="nuRatePercentage${ten}" id="nuRatePercentage${ten}" class="form-control enableDisable" disabled="disabled"/></td>
															<td><g:textField name="nuAdditionalParameter${ten}" id="nuAdditionalParameter${ten}" class="form-control enableDisable" disabled="disabled"/></td>
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
	<%-- CATEGORY POPUP START --%>
		<div class="modal fade" id="catModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Category List</h4>
					</div>
					<div class="modal-body">
						<table width="100%" id="catTblId" class="table">
							<thead>
								<tr>
									<th>Description</th>
								</tr>
							</thead>
							<tbody id="catTblBdId">
								<g:each in="${FndFlexValuesVl.list()*.description}" status="i" var="catPopUpDataInst">
									<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnCat" id="${catPopUpDataInst}">
										<td name="catPopDesc" id="catPopDesc">${catPopUpDataInst}</td>
									</tr>
								</g:each>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="active_btn" onclick="displayTextOnCat();">OK</button>
						<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	<%-- CATEGORY POPUP ENDS --%>
	<%-- PURITY POPUP START --%>
		<div class="modal fade" id="purModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Purity List</h4>
					</div>
					<div class="modal-body">
						<table id="purTblId" class="table">							
							<thead>
								<tr><th>Purity</th></tr>
							</thead>		
							<tbody></tbody>		
						</table> 
					</div>
					<div class="modal-footer">
						<button type="button" class="active_btn" onclick="displayPurity();">OK</button>
						<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
		
		<%-- PURITY POPUP ENDS --%>
	</form>	
	<script type="text/javascript">
		var catVal = '';
		var purVal = '';
		var popPurVal = '';
		$(document).ready(function(){
			applyDataTable('catTblId');
			$("#addRatePara").click(function(){
				for(var i=1;i<=10;i++){
					if(i==1){
						$('#brName'+i).removeAttr('disabled', 'disabled');
						$('#description'+i).removeAttr('disabled', 'disabled');
						$('#butIdCat'+i).removeAttr('disabled', 'disabled');
						$('#pur'+i).removeAttr('disabled', 'disabled');
						$('#butIdPur'+i).removeAttr('disabled', 'disabled');
						$('#nuRatePercentage'+i).removeAttr('disabled', 'disabled');
						$('#nuAdditionalParameter'+i).removeAttr('disabled', 'disabled');
					}else{
						$('#brName'+i).attr('disabled', 'disabled');
						$('#description'+i).attr('disabled', 'disabled');
						$('#butIdCat'+i).attr('disabled', 'disabled');
						$('#pur'+i).attr('disabled', 'disabled');
						$('#butIdPur'+i).attr('disabled', 'disabled');
						$('#nuRatePercentage'+i).attr('disabled', 'disabled');
						$('#nuAdditionalParameter'+i).attr('disabled', 'disabled');
					}
				}
				$('#nav2').show();
				$('#nav1').hide();	
			});	
			for(var i=1;i<=10;i++){
				$('#butIdCat'+i).attr('disabled', 'disabled');
				$('#butIdPur'+i).attr('disabled', 'disabled');
			}
			$("#purModal").hide();
			$(".clickOnCat td:not(.action)").click(function () {
				catVal =$(this).closest('tr').attr('id');
				$(".highlight").removeClass('highlight');
				$(this).addClass('highlight');
			}); 
			$(".clickOnPur td:not(.action)").click(function () {
				purVal =$(this).closest('tr').attr('id');
				$(".highlight").removeClass('highlight');
				$(this).addClass('highlight');
			});
		});
		function returnToMainPage(){
			 if(confirm("Sure You Want To Exit?")){
			 	$("#spinner").show();
			 	document.getElementById("indexRateParameter").action ="${request.getContextPath()}/company/mainModule";
		    	document.getElementById("indexRateParameter").submit();
			 }
		 }
		 function clearAllFields(){
		 	if(confirm("Sure You Want To Cancel?")){
			 	$("#brName1").val('');
			 	$("#description1").val('');
			 	$("#pur1").val('');
			 	$("#nuRatePercentage1").val('');
			 	$("#nuAdditionalParameter1").val('');
		 	}
		} 
	 	function displayTextOnCat(){
		 	$(".clickOnCat").removeClass('highlight');
		 	$("#description1").val(catVal);
		 	
		 	getPurDataBasedOnCat();
		 	$("#catModal .close").click(); 	
		 }	
		 function saveRatePar(){
			var flag = "";
		  	flag = validate();
			if(!flag){
		  		return false;
		  	}else{		
		  		$("#spinner").show();
		  		document.getElementById("indexRateParameter").action ="saveRatePar";
		    	document.getElementById("indexRateParameter").submit();
		  	}  	  
		}
		function validate(){
			var flag = "false"; 
			var brName = $('#brName1').val();
		  	var cat = $('#description1').val();
		  	var pur = $('#pur1').val();
		  	var ratePer = $('#nuRatePercentage1').val();
		  	var addPar = $('#nuAdditionalParameter1').val();
		  	if(brName !== "" && brName != null && brName !== undefined){
		  		 $("#Error").html('');
		         flag="true";
		     }else{
		     	 flag="false";	
		         $("#Error").html('Please Select Branch').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }
		   	 if(cat !== "" && cat != null && cat !== undefined){
		   	 	 $("#Error").html('');
		         flag="true";
		     }else{
		     	 flag="false";	
		         $("#Error").html("Please Select Category").show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }
		     if(pur !== "" && pur != null && pur !== undefined){
		     	 $("#Error").html('');
		         flag="true";
		     }else{
		     	 flag="false";	
		         $("#Error").html("Please Select Purity").show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }
		   	 if(ratePer !== "" && ratePer != null && ratePer !== undefined){
		   	 	 $("#Error").html('');
		         flag="true";
		     }else{
		     	 flag="false";	
		         $("#Error").html("Please Enter Rate Percentage").show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }
		     if(addPar !== "" && addPar != null && addPar !== undefined){
		     	 $("#Error").html('');
		         flag="true";
		     }else{
		     	 flag="false";	
		         $("#Error").html("Please Enter Additional Parameter").show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }
		     return flag;
		}
		function getPurDataBasedOnCat(){
		 	var desVal = $("#description1").val();
		 	$("#spinner").show();
		 	var html = '';            
		        $.getJSON("${request.getContextPath()}/mstRateParameter/getPurityList/?desVal=" + desVal, function(data) {	
		        	$("#spinner").hide();        	
		 			for(var i=0;i<data.PURITY.length;i++){
		 				html +='<tr>'+ 				
		 				'<td id="popPurId'+i+'" name="'+data.PURITY[i]+'" value="'+data.PURITY[i]+'" onclick="getPurValue('+i+');">'+data.PURITY[i]+'</td>'+
		 				'</tr>';
		 			}
		 			$("#purTblId tbody").empty().append(html);	
		 			applyDataTable('purTblId');
		 		});		
		 }
		 function getPurValue(val){
		 	popPurVal = $("#popPurId" + val+"").html();
			$('.highlight').removeClass('highlight');
			$("#popPurId" + val+"").addClass('highlight');
		 }
		 function displayPurity(){
		 	$(".clickOnPur").removeClass('highlight');
		 	$("#purModal .close").click();
		 	$("#pur1").val(popPurVal);
		 }	
	</script>
</body>
</html>