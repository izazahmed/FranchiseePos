<%-- 
-- File Name: auth
-- Description: This page displays login Page of Application
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Login</title>
	
	<asset:javascript src="jquery.min.js"/>
	<asset:javascript src="bootstrap.min.js"/>
	<asset:javascript src="common.js"/>
	
	<asset:stylesheet href="bootstrap.min.css"/>
	<asset:stylesheet href="mainerphq.css"/>
	<asset:stylesheet href="media.css"/>
	
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
	
	<style type="text/css">
	#counterPopup .modal-dialog {
		margin: 160px auto;
		width: 34%;
	}
	</style>
</head>
<body>
<div id="spinner" class="spinner" style="display: none;">
		<div class="spinner_lean_overlay">
			<g:message code="spinner.alt" default="Loading&hellip;" />
		</div>
	</div>
	<div class="panel-body form-signin">
		<form action='auth' method='POST' id='loginForm' autocomplete='on'>
			<fieldset>
				<div class="logo1">
					<asset:image src="logo_login.png" />
				</div>
				<div class="Login-content-bg pull-left">
					<div class="col-md-12">
						<g:if test='${flash.error}'>
							<div class="alert alert-danger" role="alert">
								<i class="fa fa-exclamation-circle" aria-hidden="true"></i> <span
									class="sr-only">Error:</span><a class="close"
									data-dismiss="alert" href="#">×</a>
								${flash.error}
							</div>
						</g:if>
						<div class="alert alert-danger" role="alert" id="warningMsg" style="display: none;"></div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row tab-form">
								<label for="concept" class="col-sm-4 control-label">User Name</label>
								<div class="col-sm-8 col-md-8 col-xs-8">
									<g:textField name="username" class="form-control" placeholder="Username" tabindex="1" onblur="validateUser();" />
								</div>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row tab-form">
								<label for="concept" class="col-sm-4 control-label">Password</label>
								<div class="col-sm-8 col-md-8 col-xs-8">
									<g:passwordField placeholder="Password" name="password" class="form-control" value="" tabindex="2" onblur="validatePassword();" />
								</div>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row tab-form">
								<label for="concept" class="col-sm-4 control-label">Company</label>
								<div class="col-sm-8 col-md-8 col-xs-8">
									<div class="select-style2">
										<g:select name="companyCode" class="cmpnyCodeCls form-control" from="" tabindex="3"/>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row tab-form">
								<label for="concept" class="col-sm-4 control-label">Branch</label>
								<div class="col-sm-8 col-md-8 col-xs-8">
									<div class="select-style2">
										<g:select name="branchCode" class="brCodeCls form-control" from="" tabindex="4" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row tab-form">
								<label for="concept" class="col-sm-4 control-label">Year</label>
								<div class="col-sm-8 col-md-8 col-xs-8">
									<div class="select-style2">
										<g:select name="year" class="yrCodeCls form-control" from="" tabindex="5" />
									</div>
								</div>
							</div>
						</div>
						<div class="pull-right">
							<input class="btn btn-info" type="submit" value="Login" onclick="return validateData();" />
						</div>
					</div>
				</div>
			</fieldset>
			<div class="modal fade" id="counterPopup" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
				<div class="modal-dialog" role="document">
			    	<div class="modal-content">
			      		<div class="modal-header">
			        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        		<h4 class="modal-title" id="exampleModalLabel">Counter ID/Machine No.</h4>
			      		</div>
					    <div class="modal-body">
							<div class="inner pull-left"> 
								<div class="alert alert-danger" role="alert" id="sendQueryBlock" style="display: none"></div>
							</div>
							<div class="row cust-tab-form">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<label for="concept" class="col-sm-4 control-label">Counter Id</label>
									<div class="col-sm-8 col-md-8 col-xs-8">
										<g:textField name="counterId" class="form-control" />
									</div>
								</div>
							</div>
							<div class="row cust-tab-form">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<label for="concept" class="col-sm-4 control-label">Machine No.</label>
									<div class="col-sm-8 col-md-8 col-xs-8">
										<g:textField name="machineNo" class="form-control" />
									</div>
								</div>
							</div>
						</div>  
				      <div class="modal-footer">
			      		<a href="#" class="btn btn-info" onClick="onCounterSubmit();">OK</a>
			        	<button class="btn btn-info" data-dismiss="modal">Close</button>
				      </div>
			    	</div>
			  	</div>
			</div>
			
		</form>
	</div>
<script type="text/javascript">
	function validateData(){
		var username = $("#username").val();
		var password = $("#password").val();
		if(!username || username==""){
			$("#warningMsg").html("Username cannot be blank").show();
			hideSuccessErrorMessage("alert");
			$("#username").focus();		
		    return false;	    
		}
		if(!password || password==""){
			$("#warningMsg").html("Password cannot be blank").show();
			hideSuccessErrorMessage("alert");
			$("#password").focus();
		    return false;	    
		}
		if(!branchCode || branchCode==""){
			$("#warningMsg").html("Branch cannot be blank").show();
			hideSuccessErrorMessage("alert");
		    return false;	
		}
		if(!companyCode || companyCode==""){
			$("#warningMsg").html("Company cannot be blank").show();
			hideSuccessErrorMessage("alert");
		    return false;	
		}
		if(!year || year==""){
			$("#warningMsg").html("Year cannot be blank").show();
			hideSuccessErrorMessage("alert");
		    return false;	
		}
	    $('#counterPopup').modal('toggle');
		$('#counterPopup').modal('show');
		return false;
	}
	function onCounterSubmit(){
		var counterId=$("#counterId").val();
		var machineNo=$("#machineNo").val();
		if(!counterId || counterId==""){
			$("#warningMsg").html("Counter Id cannot be blank").show();
			hideSuccessErrorMessage("alert");
		    return false;	    
		}
		if(!machineNo || machineNo==""){
			$("#warningMsg").html("Machine Number cannot be blank").show();
			hideSuccessErrorMessage("alert");
			$("#machineNo").focus();
		    return false;	    
		}
		$('#counterPopup .close').click();
		$("#spinner").show();
		document.getElementById("loginForm").action ="auth";
		document.getElementById("loginForm").submit();
	}
	function validateUser() {
		var parameters = "usernameVal=" + $("#username").val(); 
		$.ajax({
			type:'POST',
			url: "${request.getContextPath()}/login/getDetailUserId",
			async:false,
			data :parameters,
			success : function(response){
				if(JSON.stringify(response.NOT_VALID_USER) == 'null') {
					$("#warningMsg").html("User Name is invalid/inactive").show();
					hideSuccessErrorMessage("alert");
				}
			},
		  	error:function(){
				return false;
			}
		});
	}
	function validatePassword(){
		var brhtml = "";
		var cmpnyhtml = "";
		var yrhtml = "";
		var parameters = "usernameVal=" + $("#username").val() + "&passwordVal="+$("#password").val();
		$("#spinner").show();
		$.ajax({
			type:'POST',
			url: "${request.getContextPath()}/login/getDetailPassword",
			async:false,
			data :parameters,
			success : function(response){
				$("#spinner").hide();	
				if (JSON.stringify(response.NOT_VALID_PASSWORD) == 'null') {
					$("#warningMsg").html("Password is Invalid").show();
					hideSuccessErrorMessage("alert");
				}
				if (JSON.stringify(response.VC_COMPANY_NAME) == 'null') {
					$("#password").focus();	
				} else {
					if(response.VC_COMPANY_NAME.length == 0){
						$("#warningMsg").html("Issue in Company").show();
						hideSuccessErrorMessage("alert");
					}else{
						for(var i=0;i<response.VC_COMPANY_NAME.length;i++){
							cmpnyhtml  +='<option value='+response.VC_COMP_CODE+'>'+response.VC_COMPANY_NAME+'</option>';					
						}												
						$(".cmpnyCodeCls").html('');
						$(".cmpnyCodeCls").html(cmpnyhtml);
					}
					if(response.BR_NAME.length == 0){
						$("#warningMsg").html("Issue in Branch").show();
						hideSuccessErrorMessage("alert");
					}else{
					    for(var i=0;i<response.BR_NAME.length;i++){
							brhtml +='<option value='+response.BR_CODE+'>'+response.BR_NAME+'</option>';					
						}												
						$(".brCodeCls").html('');
						$(".brCodeCls").html(brhtml);
					}
					if(response.DT_FIN_START_DATE.length == 0){
						$("#warningMsg").html("Issue in Financial Year").show();
						hideSuccessErrorMessage("alert");
					}else{
						for(var i=0;i<response.DT_FIN_START_DATE.length;i++){
							yrhtml +='<option value='+response.DT_FIN_START_DATE+'>'+response.DT_FIN_START_DATE+'</option>';					
						}
						$(".yrCodeCls").html('');
						$(".yrCodeCls").html(yrhtml);
					}	
				}			
			},
			error:function(){
				alert("Invalid Credentials");
				return false;
			}
		});
	}
</script>
</body>
</html>

