<%-- 
-- File Name: changePasswordAdmin
-- Description: This page displays Change Password Page of User Management
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Abhijit				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Change Password</title>
	<script type="text/javascript">
		var userName='';
		var userId='';
		$(document).ready(function(){
			$("#newPwdConf").hide();
			$(".editClick td:not(.action)").click(function () {
				userId =$(this).closest('tr').attr('id');
				userName =$(this).closest('tr').attr('name');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');			
		  	});
		});
		function editSubmit() {		
			$("#name").val(userName);
			$("#userIdHid").val(userId);
			var parameter = {userId:userId}
			$.ajax({
				url: "${request.getContextPath()}/mkUsers/getUserDetailsAdmin",
				async : false,
				data : parameter,
				success : function(data){	
					console.log(JSON.stringify(data));
					$("#oldPwd").val(data.VC_PASSWORD);					
				}
			 });			 
		 $(".editClick").removeClass('highlight');	 
		}
		function fnPassword() {
			$("#newPwd").hide();
			$("#newPwdConf").show();
			$("#newPwdConf").focus()	
		}
		function savePassword() {
			if($('#name').val()==""){
				$("#blankerrormsg").html("Name Field Should Not Be Blank!").show(); 
				$('#name').focus();
				return false;
			}else if($('#oldPwd').val()==""){
				$("#blankerrormsg").html("Old Password Field Should Not Be Blank!").show(); 
				$('#oldPwd').focus();
				return false;
			}else if($('#newPwd').val()==""){
				$("#blankerrormsg").html("New Password Field Should Not Be Blank!").show(); 
				$('#newPwd').focus();
				return false;
			}else if($('#newPwdConf').val()==""){
				$("#blankerrormsg").html("Confirm Password Field Should Not Be Blank!").show(); 
				$('#newPwdConf').focus();
				return false;
			}else{
				$("#blankerrormsg").html("");
				var name = $('#name').val();
				var userId = $('#userIdHid').val();
				var oldPassword = $('#oldPwd').val();
				var newPassword = $('#newPwd').val();
				var newPasswordConf = $('#newPwdConf').val();
				
				if(newPassword==newPasswordConf){				
					var parameter = {userId:userId,newPasswordConf:newPasswordConf}
					$.ajax({
						url : "../MkUsers/updatePassword/",
						async : false,
						data : parameter,
						success : function(data){					
							$("#name").val('');
		 					$("#oldPwd").val('');
		 					$("#newPwdConf").val('');
						}
					});
				}else{
					$("#blankerrormsg").html("Password does not match! Please try again...").show();
					$('#newPwdConf').focus();
					return false;
				}
			}
		}
	</script>
</head>
<body>
	<div class="modal fade" id="editPopup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">User List</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="editPassword" class="table">
						<thead>
							<tr>
								<th>Name</th>
							</tr>
						</thead>
						<tbody id="prevAdvNoTbdId">
							<g:each in="${userListForAdmin}" status="i" var="userInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${userInst?.CH_USER_CODE}" name="${userInst?.VC_USER_NAME}" style="font-size: 12px;">
									<td name="userId" id="userId">${userInst?.VC_USER_NAME}</td>									
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="editSubmit();" data-dismiss="modal">Add</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="#" id="scheme" data-toggle="modal" data-target="#editPopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="#" onclick="savePassword();"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<g:link action="dashboard" controller="company"><asset:image src="exit.png" /></g:link>
						</div>
						<div id="create-mkUsers" class="content scaffold-create">
							<h3>Change Password</h3>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mkUsersInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mkUsersInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="alert alert-danger" role="alert" id="blankerrormsg" style="display: none;"></div>
							<div class="col-md-12">
								<div class="row tab-form">
									<div class="col-md-6 col-sm-6 col-xs-6">
										<label for="concept" class="col-sm-4 control-label"><g:message code="mtlItemLocationsForm.subinventoryCode.label" default="Name" /></label>
										<div class="col-sm-8 col-md-8 col-xs-8">
											<g:textField name="name" id="name" class="form-control" value="" tabindex="1" readonly="true"/>
											<g:hiddenField name="userIdHid" id="userIdHid" class="form-control" value="" tabindex="1"/>
										</div>
									</div>
								</div>
								<div class="row tab-form">
									<div class="col-md-6 col-sm-6 col-xs-6">
										<label for="concept" class="col-sm-4 control-label"><g:message code="mtlItemLocationsForm.segment1.label" default="Old Password" /></label>
										<div class="col-sm-8 col-md-8 col-xs-8">
											<g:passwordField name="oldPwd" id="oldPwd" class="form-control" value="" tabindex="2" readonly="true"/>
										</div>
									</div>
								</div>
								<div class="row tab-form">
									<div class="col-md-6 col-sm-6 col-xs-6">
										<label for="concept" class="col-sm-4 control-label"><g:message code="mtlItemLocationsForm.description.label" default="New Password"  nowrap="nowrap"/></label>
										<div class="col-sm-8 col-md-8 col-xs-8">
											<g:passwordField name="newPwd" id="newPwd" class="form-control" value="" tabindex="3" onblur="fnPassword();"/>
											<g:passwordField name="newPwdConf" id="newPwdConf" class="form-control" value="" tabindex="3" />
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