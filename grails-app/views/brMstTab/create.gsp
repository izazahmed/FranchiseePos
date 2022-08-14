<%-- 
     -- File Name: create brMstTab
     -- Description: it is used to do save the branch
     -- Author(s): CTE 
     -- Date: 09/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 09/05/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.MstCompany" %>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Branch Master</title>
	<script type="text/javascript">
		function clearAllFields(){
		 	if(confirm("Sure You Want To Cancel?")){
			 	$("#companyName").val('');
		 	}
		} 
		function returnToMainPage(){
			 if(confirm("Sure You Want To Exit?")){
			 	document.getElementById("createBranchForm").action ="${request.getContextPath()}/company/mainModule";
		    	document.getElementById("createBranchForm").submit();
			 }
		 }
		 function saveBranch(){
			var flag = validate();
			if(!flag){
		  		return false;
		  	}else{		
		  		document.getElementById("createBranchForm").action ="saveBranch";
		    	document.getElementById("createBranchForm").submit();
		  	}  	  
		}
		function validate(){
			var flag = false; 
			var branchName = $('#branchName').val();
			var compName = $('#compName').val();
			var city = $('#city').val();
			var state = $('#state').val();
			var country = $('#country').val();
			var telephone = $('#telephone').val();
			var chActive = $('#chActive').val();
			var iecNo = $('#iecNo').val();
			var ebsOrgMap = $('#ebsOrgMap').val();
			var statFlag = $('#statFlag').val();
			var add = $('#add').val();
			var fullAdd = $('#fullAdd').val();
		  	if(branchName !== "" && branchName != null && branchName !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter Branch Name').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		  	if(compName !== "" && compName != null && compName !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Select Company Name').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   	 
		     if(city !== "" && city != null && city !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter City').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		     if(state !== "" && state != null && state !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter State').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		     if(country !== "" && country != null && country !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter Country').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		     if(telephone !== "" && telephone != null && telephone !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter Telephone No').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		     if(iecNo !== "" && iecNo != null && iecNo !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter IecNo').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		     if(ebsOrgMap !== "" && ebsOrgMap != null && ebsOrgMap !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter EbsOrgMap').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		     if(add !== "" && add != null && add !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter Address').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		     if(fullAdd !== "" && fullAdd != null && fullAdd !== undefined){
		  		 $("#Error").html('');
		         flag=true;
		     }else{
		     	 flag=false;	
		         $("#Error").html('Please Enter Full Address').show();
		         hideSuccessErrorMessage("alert");
		         return false;
		     }   
		     return flag;
		}
	</script>
</head>
<body>
<form name="createBranchForm" id="createBranchForm">
	<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="add.png" /></a> 
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)" onclick="saveBranch();"><asset:image src="save.png" /></a>
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a>
							<a href="#" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Branch Master</h1>
							<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
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
														<td>Branch Name<span class="error">*</span></td>
														<td><g:textField name="branchName" class="form-control" /></td>
														<td>Company Name<span class="error">*</span></td>
														<td>
															<div class="select-style2">
																<g:select class="form-control" name="compName" from="${MstCompany.list()*.vcCompanyName}" noSelection="${['':'Please Select Company Name']}"/>
															</div>
														</td>
													</tr>
													<tr>
														<td>City<span class="error">*</span></td>
														<td><g:textField class="form-control" name="city" /></td>
														<td>State<span class="error">*</span></td>
														<td><g:textField class="form-control" name="state" /></td>
													</tr>	
													<tr>
														<td>Country<span class="error">*</span></td>
														<td><g:textField class="form-control" name="country"/></td>
														<td>Telephone<span class="error">*</span></td>
														<td><g:textField name="telephone" class="form-control" /></td>
													</tr>	
													<tr>
														<td>Ch Active</td>
														<td>
															<div class="col-md-6 col-sm-6 col-xs-6"><g:radio name="chActive" id="chActive" checked="checked" value="Y" />Active</div>
															<div class="col-md-6 col-sm-6 col-xs-6"><g:radio name="chActive" id="chActive" value="N" />InActive</div>
														</td>
														<td>IEC No<span class="error">*</span></td>
														<td><g:textField name="iecNo" class="form-control" /></td>
													</tr>	
													<tr>
														<td>EBS Org Map<span class="error">*</span></td>
														<td><g:textField name="ebsOrgMap" class="form-control" /></td>														
														<td>Status Flag</td>
														<td>
															<div class="col-md-6 col-sm-6 col-xs-6"><g:radio name="statFlag" id="statFlag" checked="checked" value="Y" />Active</div>
															<div class="col-md-6 col-sm-6 col-xs-6"><g:radio name="statFlag" id="statFlag" value="N" />InActive</div>
														</td>
													</tr>	
													<tr>
														<td>Address<span class="error">*</span></td>
														<td><g:textField name="add" class="form-control" /></td>
														<td>Full Address<span class="error">*</span></td>
														<td><g:textArea name="fullAdd" class="form-control"/></td>
													</tr>																																	
												</thead>		
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
	</form>
</body>
</html>