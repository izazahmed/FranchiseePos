<%-- 
     -- File Name: edit rate parameter
     -- Description: it is used to do update the rate parameter data
     -- Author(s): CTE 
     -- Date: 04/04/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 08/03/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />		
	<title>Rate Parameter</title>
</head>
<body>
	<div class="container-fluid content-height">
		<g:form name="editRateParForm" id="editRateParForm">
		<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
		<g:hiddenField name="rowCntId" id="rowCntId" value="${rowCount}"/>
		<g:each in="${mstRateParameterResult}" status="i" var="mstRateParameterInstance">
			<g:hiddenField name="oldBrNmId${mstRateParameterInstance?.ROWNUM}" id="oldBrNmId${mstRateParameterInstance?.ROWNUM}" value="${mstRateParameterInstance?.BR_NAME}"/>
			<g:hiddenField name="oldPurId${mstRateParameterInstance?.ROWNUM}" id="oldPurId${mstRateParameterInstance?.ROWNUM}" value="${mstRateParameterInstance?.VC_PURITY}"/>
			<g:hiddenField name="oldDescId${mstRateParameterInstance?.ROWNUM}" id="oldDescId${mstRateParameterInstance?.ROWNUM}" value="${mstRateParameterInstance?.VC_CATEGORY}"/>
		</g:each>
		  <div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">							
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="add.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="view.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="edit.png" /></a>
							<a href="#" onclick="updateRatePar();"><asset:image src="save.png" /></a>
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a>
							<a href="#" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div id="list-mstRateParameter" class="content scaffold-list" role="main">
							<h1>Edit Rate Parameter</h1>
							<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
							<div class="alert alert-sucess" role="alert" id="Sucess" style="display: none; color:blue"></div>							
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
						<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="col-md-8 page-header">
											<table class="table">
												<tr>				 	
												 	<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Branch<span class="redColor">*</span></strong></td>
												 	<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Category<span class="redColor">*</span></strong></td>
												 	<td></td>
												 	<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Purity<span class="redColor">*</span></strong></td>
												 	<td></td>
												 	<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rate Percentage<span class="redColor">*</span></strong></td>
												 	<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add Parameter<span class="redColor">*</span></strong></td>				 	
												 </tr>							
												<tbody>				
													<g:each in="${mstRateParameterResult}" status="i" var="editInst">
														<tr width="100%" > 
															<td><g:select name="brName${editInst?.ROWNUM}" id="brName${editInst?.ROWNUM}" class="form-control enableDisable" from="${mstRateParameterResult?.BR_NAME}" value="${editInst?.BR_NAME}"/></td>
															<td><g:textField name="description${editInst?.ROWNUM}" id="description${editInst?.ROWNUM}" class="form-control enableDisable" value="${editInst?.VC_CATEGORY}"/></td>	
															<td><button type="button" class="active_btn" data-toggle="modal" id="catBtnId${editInst?.ROWNUM}" data-target="#catModal">...</button></td>
															<td><g:textField name="pur${editInst?.ROWNUM}" id="pur${editInst?.ROWNUM}" class="form-control enableDisable" value="${editInst?.VC_PURITY}"/></td>	
															<td><button type="button" class="active_btn" data-toggle="modal" id="purBtnId${editInst?.ROWNUM}" data-target="#purModal">...</button></td>
															<td><g:textField name="nuRatePercentage${editInst?.ROWNUM}" id="nuRatePercentage${editInst?.ROWNUM}" class="form-control" value="${editInst?.NU_RATE_PERCENTAGE}"/></td>
															<td><g:textField name="nuAdditionalParameter${editInst?.ROWNUM}" id="nuAdditionalParameter${editInst?.ROWNUM}" class="form-control" value="${editInst?.NU_ADDITIONAL_PARAMETER}"/></td>		
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
	</g:form>
	</div>
	<script type="text/javascript">
		var rowLen = "";
		var oldBrNm = "";
		var oldPur = "";
		var oldDesc = "";
		$(document).ready(function(){	
			$('.enableDisable').attr('disabled', 'disabled');			
			var rc = $("#rowCntId").val();
			for(var i=1;i<=rc;i++){
				$('#catBtnId'+i).attr('disabled', 'disabled');
				$('#purBtnId'+i).attr('disabled', 'disabled');
			}					
		});
		 function updateRatePar(){
		 	var flag = "";
		 	var r = $("#rowCntId").val();
		 	var val = "";
		 	$("#spinner").show();
		 	for(var k=1;k<=r;k++){
		 		flag = validate(k);
			 	if(!flag){
			  		return false;
		  		}else{
		  			val = updateAll(k);
		  		}	  
		 	}
		 	$("#spinner").hide();
		 	if(val == 'something'){
		 		$("#Error").html("Data Not Updated").show();
		 		hideSuccessErrorMessage("alert");
		 	}else{
		 		$("#Sucess").html("Data Updated Successfully").show();
        		hideSuccessErrorMessage("alert");
		 	}
		 }
		 function validate(k){
		 		var flag = "false"; 		 		
		        var per = $("#nuRatePercentage"+k+"").val();
		        var addPer = $("#nuAdditionalParameter"+k+"").val();        
		              		        
		        if(per !== "" && per != null && per !== undefined){
		        	$("#Error").html('');
		        	flag="true";
		        }else{
		        	flag="false";	
			        $("#Error").html("Please Enter Percentage").show();
         			hideSuccessErrorMessage("alert");
			        return false;
		        }
		        if(addPer !== "" && addPer != null && addPer !== undefined){
		        	$("#Error").html('');
		        	flag="true";
		        }else{
		        	flag="false";	
			        $("#Error").html("Please Enter Add Parameter").show();
        			hideSuccessErrorMessage("alert");
			        return false;
		        }		        		       
		 	return flag;
		 }
		 function checkUnique(brNm,pur,oldBrNm,oldPur){
		 	var result="";
		   	$.getJSON("${request.getContextPath()}/mstRateParameter/checkUnique/?brName="+brNm+"&pur="+pur+"&oldBrNm="+oldBrNm+"&oldPur="+oldPur, function(json) {	
		   		console.log(json.chkRslt);		       
		   		result = json.chkRslt;
			});
		    return result;
		 }
		 function updateAll(k){		    
		    oldBrNm = $("#oldBrNmId"+k+"").val(); 
		    oldDesc = $("#oldDescId"+k+"").val();
		    oldPur = $("#oldPurId"+k+"").val();	
		    var per = $("#nuRatePercentage"+k+"").val();
		    var addPer = $("#nuAdditionalParameter"+k+"").val();
		    console.log("addPer::"+addPer); 		    
		    var value = "";		    		    
		    $.getJSON("${request.getContextPath()}/mstRateParameter/updateRatePar/?brName="+oldBrNm+"&description="+oldDesc+"&pur="+oldPur+"&nuRatePercentage="+per+"&nuAdditionalParameter="+addPer+"&rownum="+k, function(json) {		            
		          value = json.dataUpdate
		    }); 
		    return value;
		 }
		 function clearAllFields(){
		 	if(confirm("Sure You Want To Cansel?")){
		 		$("#spinner").show();
			 	document.getElementById("editRateParForm").action ="index";
		    	document.getElementById("editRateParForm").submit();
		 	}
		 } 
		 function returnToMainPage(){
			 if(confirm("Sure You Want To Exit?")){
			 	$("#spinner").show();
			 	document.getElementById("editRateParForm").action ="${request.getContextPath()}/company/mainModule";
		    	document.getElementById("editRateParForm").submit();
			 }
		 }
	</script>
</body>
</html>