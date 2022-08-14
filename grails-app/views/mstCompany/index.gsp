<%-- 
     -- File Name: index mstCompany
     -- Description: it is used to do display the form
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
	<title>Company Master</title>	
</head>
<body>
<form name="indexMstCompanyForm" id="indexMstCompanyForm">
	<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav" id="nav1">
							<a href="#" id="addcompany"><asset:image src="add.png" /></a>
							<a href="${request.getContextPath()}/mstCompany/view"><asset:image src="view.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="delete.png" /></a>
							<a href="#" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div class="nav" id="nav2" style="display: none;">
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="add.png" /></a> 
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)" onclick="saveCompany();"><asset:image src="save.png" /></a>
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Mst Company</h1>
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
								<div class="col-md-8">
									<div class="panel panel-default">
										<table class="table">
											<thead>	
												<tr>
													<td>Company Name<span class="redColor">*</span></td>
													<td><g:textField name="companyName" disabled="disabled" class="form-control"/></td>
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
	</form>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#addcompany").click(function(){
			$('#companyName').removeAttr('disabled');
			$('#nav2').show();
			$('#nav1').hide();
		});
	});
	function clearAllFields(){
	 	if(confirm("Sure You Want To Cancel?")){
		 	$("#companyName").val('');
	 	}
	 } 
	function returnToMainPage(){
		 if(confirm("Sure You Want To Exit?")){
		 	$("#spinner").show();
		 	document.getElementById("indexMstCompanyForm").action ="${request.getContextPath()}/company/mainModule";
	    	document.getElementById("indexMstCompanyForm").submit();
		 }
	 }
	 function saveCompany(){
		var flag = "";
	  	flag = validate();
		if(!flag){
	  		return false;
	  	}else{		
	  		$("#spinner").show();
	  		document.getElementById("indexMstCompanyForm").action ="saveCompany";
	    	document.getElementById("indexMstCompanyForm").submit();
	  	}  	  
	}
	function validate(){
		var flag = "false"; 
		var companyName = $('#companyName').val();
	  	if(companyName !== "" && companyName != null && companyName !== undefined){
	  		 $("#Error").html('');
	         flag="true";
	     }else{
	     	 flag="false";	
	         $("#Error").html('Please Enter Company Name').show();
	         hideSuccessErrorMessage("alert");
	         return false;
	     }   	 
	     return flag;
	}
</script>
</body>
</html>
