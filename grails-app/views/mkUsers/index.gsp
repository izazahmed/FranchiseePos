<%--
-- File Name: create
-- Description: This page displays create Page of Registration Details
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Izaz				Created File
--            
--%>
<%@ page import="com.tbz.franchisee.MstCompany"%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>User Management</title>
	<script type="text/javascript">
	var brname='';
	var popCompName='';
	var popRoleName='';
	var selEdDt ="";
	var selStDt ="";
	var popUserCode = "";
	var userCode = '';
	var brCode = $("#brName").val();
	var compButIdVal='';
	var roleButIdVal='';
	var compIdVal = '';
	var cArray = new Array();
	var compCodeStr = "";
	var roleIdVal = '';
	var rArray = new Array();
	var roleCodeStr = "";
	$(document).ready(function(){		
		$("#addMkUser").click(function(){
			$('#name').removeAttr('disabled', 'disabled');
			$('#password').removeAttr('disabled', 'disabled');
			$('.actRadioCLSY').removeAttr('disabled', 'disabled');
			$('.authRadioCLSY').removeAttr('disabled', 'disabled');
			$('#brName').removeAttr('disabled', 'disabled');
			for(var i=0;i<5;i++){
				if(i==0){
					$('#compId'+i).removeAttr('disabled', 'disabled');
					$('#btnCompPP'+i).removeAttr('disabled', 'disabled');
					$('#roleId'+i).removeAttr('disabled', 'disabled');
					$('#btnRolePP'+i).removeAttr('disabled', 'disabled');
				}else{
					$('#compId'+i).attr('disabled', 'disabled');
					$('#btnCompPP'+i).attr('disabled', 'disabled');
					$('#roleId'+i).attr('disabled', 'disabled');
					$('#btnRolePP'+i).attr('disabled', 'disabled');
				}
			}
			$('#nav2').show();
			$('#nav1').hide();	
		});	
		$("#curDte").hide();
		$("#prvDte").hide();	
		$("#rolesModal").hide();
		$("#mkUsersModal").hide();		
		$(".clickOnUser td:not(.action)").click(function () {	
			userCode =$(this).closest('tr').attr('id');
			$('.highlight').removeClass('highlight');			
			$(this).closest('tr').addClass('highlight');
		});
	});	
	function clearCompRole(){
	 	$(".clearVal").val('');	
	 }
	function getCompanies(selVal){		
		compButIdVal = selVal;
		$("#companiesModal").modal("hide");
		var html = '';
		$("#spinner").show();
		$.getJSON("${request.getContextPath()}/MkUsers/getCompanies/", function(data) { 		 	
			$("#spinner").hide();
			if(data.VC_COMP_CODE.length == 0){
				$("#compPopErr").html("No Companies Data").show();
				hideSuccessErrorMessage("alert");				
			}else{
				$("#compPopup tbody").empty();
	 			for(var i=0;i<data.VC_COMP_CODE.length;i++){
	 				html +='<tr name="'+data.VC_COMPANY_NAME[i]+'">'+
	 						'<td id="popCompId'+i+'" name="'+data.VC_COMPANY_NAME[i]+'" value="'+data.VC_COMPANY_NAME[i]+'" onclick="getCompanyValue('+i+',\'' + data.VC_COMP_CODE[i] + '\');">'+data.VC_COMPANY_NAME[i]+'</td>'+
	 					'</tr>';
	 				/*if(cArray.length==0){
	 					html +='<tr name="'+data.VC_COMPANY_NAME[i]+'">'+
	 						'<td id="popCompId'+i+'" name="'+data.VC_COMPANY_NAME[i]+'" value="'+data.VC_COMPANY_NAME[i]+'" onclick="getCompanyValue('+i+',\'' + data.VC_COMP_CODE[i] + '\');">'+data.VC_COMPANY_NAME[i]+'</td>'+
	 					'</tr>';
	 				}else{
	 					var vl = jQuery.inArray(data.VC_COMP_CODE[i],cArray);
	 					if(vl !== -1){
	 					}else{
	 						html +='<tr name="'+data.VC_COMPANY_NAME[i]+'">'+
	 						'<td id="popCompId'+i+'" name="'+data.VC_COMPANY_NAME[i]+'" value="'+data.VC_COMPANY_NAME[i]+'" onclick="getCompanyValue('+i+',\'' + data.VC_COMP_CODE[i] + '\');">'+data.VC_COMPANY_NAME[i]+'</td>'+
	 						'</tr>';
	 					}
	 				}*/	 
	 			}
			}
				$("#companiesModal").modal("show");
				$("#compPopup tbody").append(html);
				applyDataTable('compPopup');	
			});		
	}
	function getCompanyValue(val,compIdVal){
		popCompName = $("#popCompId" + val+"").html();
		//cArray.push(compIdVal);
		$('.highlight').removeClass('highlight');
		$("#popCompId" + val+"").addClass('highlight');
	}
	function getCompanyName(){
		$("#companiesModal .close").click(); 
		$("#compId"+compButIdVal+"").val(popCompName);
		$("#compId"+compButIdVal+"").attr('disabled',true);
	}
	 $(document).ready(function () {
	    $('#rolesModal').on('shown.bs.modal', function () {
	   	 var brCode= $("#brName").val();
	      if(brCode=="" || brCode==null){
	      		$("#rolesModal").modal("hide");
				$("#Error").html("Please Select Branch").show();
				hideSuccessErrorMessage("alert");
			}
	    });
    });
	function getRolesBasedOnBranch(selVal){
		roleButIdVal = selVal;
		$("#curDte").show();
		$("#prvDte").show();
		var html = '';
		var brCode= $("#brName").val();
		if(brCode) {
			$("#spinner").show();
			$.getJSON("${request.getContextPath()}/MkUsers/getRolesBasedOnBranch/?brCode="+brCode, function(data) { 
				$("#spinner").hide();		 	
				if(data.CH_ROLE_CODE.length == 0){
					$("#rolePopErr").html("No Data For Selected Branch").show();
					hideSuccessErrorMessage("alert");
				}else{
					$("#rolePopup tbody").empty();
		 			for(var i=0;i<data.CH_ROLE_CODE.length;i++){
		 				html +='<tr name="'+data.VC_ROLE_NAME[i]+'">'+
		 						'<td id="popRoleId'+i+'" name="'+data.VC_ROLE_NAME[i]+'" value="'+data.VC_ROLE_NAME[i]+'" onclick="getRoleValue('+i+',\'' + data.CH_ROLE_CODE[i] + '\');">'+data.VC_ROLE_NAME[i]+'</td>'+
		 					 '</tr>';
		 				/*if(rArray.length==0){
		 					html +='<tr name="'+data.VC_ROLE_NAME[i]+'">'+
		 						'<td id="popRoleId'+i+'" name="'+data.VC_ROLE_NAME[i]+'" value="'+data.VC_ROLE_NAME[i]+'" onclick="getRoleValue('+i+',\'' + data.CH_ROLE_CODE[i] + '\');">'+data.VC_ROLE_NAME[i]+'</td>'+
		 					 '</tr>';
		 				}else{
		 					var vl = jQuery.inArray(data.CH_ROLE_CODE[i],rArray);
		 					if(vl !== -1){
		 					}else{
		 						html +='<tr name="'+data.VC_ROLE_NAME[i]+'">'+
		 						'<td id="popRoleId'+i+'" name="'+data.VC_ROLE_NAME[i]+'" value="'+data.VC_ROLE_NAME[i]+'" onclick="getRoleValue('+i+',\'' + data.CH_ROLE_CODE[i] + '\');">'+data.VC_ROLE_NAME[i]+'</td>'+
		 					 '</tr>';
		 					}
		 				}*/
		 			}
				}
				$("#rolePopup tbody").append(html);
				applyDataTable('rolePopup');
			});	
		}	
	}	
	function getRoleValue(val,roleIdVal){
		popRoleName = $("#popRoleId" + val+"").html();
		//rArray.push(roleIdVal);
		$('.highlight').removeClass('highlight');
		$("#popRoleId" + val+"").addClass('highlight');
	}	
	function getRoleName(){
		$("#rolesModal .close").click();
		$("#roleId"+roleButIdVal+"").val(popRoleName);
		$("#roleId"+roleButIdVal+"").attr('disabled',true);
	}		
	function isViewOrEdit(val){
		$("#isViewOrEdit").val(val);
	}	
	function getUserValue(val){
		popUserCode = $("#popUserId" + val+"").val();
		$('.highlight').removeClass('highlight');
		$("#popUserId" + val+"").addClass('highlight');
	}
	function getUserData(){
		var flag = $("#isViewOrEdit").val();
		$("#viewOrEditUsersModal .close").click();
		var date = [];
		var m = 1;
		var dateVal = '';
		var branchhtml = "";		
		var parameter = {userCode:userCode,brCode:brCode,flag:flag}
		$("#spinner").show();
		$.ajax({
			url: "${request.getContextPath()}/MkUsers/getUserDataEdit",
			async : false,
			data : parameter,
			success : function(data){	
				$("#spinner").hide();				
				console.log(JSON.stringify(data));
				if(data.vc_company_name.length==0) {
					$("#Error").html("No Data For Selected User").show();
					hideSuccessErrorMessage("alert");
				}else {
					$("#curDte").show();
					$("#prvDte").show();
					if(flag==0){
						$(".enableDisable").prop("disabled",true);
						$('.selectPrvDteCls').prop("disabled",true);
						$('.selectCurDteCls').prop("disabled", true );
					}else if(flag==1){
						$('#compId0').removeAttr('disabled', 'disabled');
						$('#roleId0').removeAttr('disabled', 'disabled');
						$('#btnCompPP0').removeAttr('disabled', 'disabled');
						$('#btnRolePP0').removeAttr('disabled', 'disabled');
						$('.selectPrvDteCls').removeAttr('disabled', 'disabled');
						$('.selectCurDteCls').removeAttr('disabled', 'disabled');
					}
					$("#spinner").show();
					$.getJSON("${request.getContextPath()}/MkUsers/getBranchList", function(json) { 
						$("#spinner").hide();
						branchhtml +='<select name="brName1" id="brName1">';
			 			for(var c=0;c<json.BR_CODE.length;c++){
			 				if(data.BR_CODE==json.BR_CODE[c]){
			 					branchhtml +='<option value="'+json.BR_CODE[c]+'" selected="selected">'+json.BR_NAME[c]+'</option>';
			 				}else {
			 					branchhtml +='<option value="'+json.BR_CODE[c]+'">'+json.BR_NAME[c]+'</option>';
			 				}
			 			}		
			 			branchhtml +='</select>';				
					});	
					$("#brName").html('');
					$("#brName").html(branchhtml);
					for(var a=0;a<data.VC_USER_NAME.length;a++){
						$('#name').val(data.VC_USER_NAME[a]);
					}
					for(var b=0;b<data.VC_PASSWORD.length;b++){
						$('#password').val(data.VC_PASSWORD[b]);		
					}	
					if(data.CH_USER_ACTIVE=='Y'){					
						$('.actRadioCLSY').prop("checked", true );
					}else if(data.CH_USER_ACTIVE=='N'){					
						$('.actRadioCLSY').prop("checked", true );
					}
					if(data.VC_AUTH_CODE=='Y'){					
						$('.authRadioCLSY').prop("checked", true );
					}else if(data.VC_AUTH_CODE=='N'){					
						$('.authRadioCLSY').prop("checked", true );
					}			
					
					for(var m=0;m<data.vc_company_name.length;m++){	
						$("#compId"+m+"").val(data.vc_company_name[m]);
					}
					for(var n=0;n<data.VC_ROLE_NAME.length;n++){
						$("#roleId"+n+"").val(data.VC_ROLE_NAME[n]);
					}
					selStDt=data.DT_FIN_START_DATE;
					selEdDt=data.DT_FIN_END_DATE;
					date = data.dtValS.split(",");
					for(var k=0;k<=(date.length-1); k++){
						stDt = date[k]
						edDt = date[m]
						k=k+1;
						m=m+2;
						if(selStDt==stDt && selEdDt==edDt){
							$('.selectPrvDteCls').prop("checked", true );							
						}
					}	
					$('#userCdHid').val(data.userCD);
					$('#flagHid').val(data.FLAG);			    
				}
			}
		}); 
		$(".clickOnUser").removeClass('highlight');
	}
	function saveOnClick(){
		var hidFlag = $("#flagHid").val();
		var hiduserCD = $("#userCdHid").val();
		if(hidFlag==''){
			hidFlag = 0
		}
		if($('#name').val()==""){
			$("#Error").html("Name Field Should Not Be Blank!").show(); 
			hideSuccessErrorMessage("alert");
			$('#name').focus();
			return false;
		}else if($('#password').val()==""){
			$("#Error").html("Password Field Should Not Be Blank!").show(); 
			hideSuccessErrorMessage("alert");
			$('#password').focus();
			return false;
		}else if($('#compId0').val()==""){
			$("#Error").html("Company Field Should Not Be Blank!").show(); 
			hideSuccessErrorMessage("alert");
			$('#compId0').focus();
			return false;
		}else if($('#roleId0').val()==""){
			$("#Error").html("Role Field Should Not Be Blank!").show(); 
			hideSuccessErrorMessage("alert");
			$('#roleId0').focus();
			return false;
		}else{
			var name = $('#name').val();
			var password = $('#password').val();
			var actRadio = $("input[name=actRadio]:checked").val();
			VAR authRadio = $("input[name=authRadio]:checked").val();
			var selectDate =  $('#selectDate').val();		
			var ids="";
			var s = document.getElementsByName('selectDate');
			for(var i=0, n=s.length;i<n;i++) {
			    if(s[i].checked){
			       ids=ids+s[i].value+"_";
			    }
		    }
		    var aa=ids.substring(0,(ids.length-1));
		    var bb=aa.split("_");
		    var cur="";
	        jQuery.each(bb, function(index, item) {  
		       cur+=item.substring(1,(item.length-1))+",";
	        });
	        var brCode = $("#brName").val();
	        var cIds = "";
	        cIds = $("#compId").val();
	        var rIds = "";
	        rIds = $("#roleId").val();
	        /*if(cArray){
		        for(var m=0;m<cArray.length;m++) {
				    cIds+=cArray[m].trim()+",";
			    }
		    }
	        if(rArray){
		        for(var p=0;p<rArray.length;p++) {
				    rIds+=rArray[p].trim()+",";
			    }
		    }*/
	        console.log("rIds out :"+rIds);
		    var parameter = {brCode:brCode,name:name,password:password,actRadio:actRadio,authRadio:authRadio,cIds:cIds,rIds:rIds,cur:cur,hidFlag:hidFlag,hiduserCD:hiduserCD,selectDate:selectDate}
			$.ajax({
				url: "${request.getContextPath()}/MkUsers/saveUser",
				async : false,
				data : parameter,
				success : function(data){					
					$("#name").val('');
					$("#password").val('');
				}
			});
	    }
	}
	 function clearAllFields(){
	 	if(confirm("Sure You Want To Cancel?")){
		 	$("#name").html('');
	 	}
	 } 
	function returnToMainPage(){
	 if(confirm("Sure You Want To Exit?")){
	 	$("#spinner").show();
	 	document.getElementById("mkUsersForm").action ="${request.getContextPath()}/company/mainModule";
    	document.getElementById("mkUsersForm").submit();
	 }
 	}
	</script>
</head>
<body>
<g:form name="mkUsersForm" id="mkUsersForm">
	<g:hiddenField name="flagHid" id="flagHid" value="" />
	<g:hiddenField name="userCdHid" id="userCdHid" value="" />
	<g:hiddenField name="isViewOrEdit"/>
	<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
	<div class="container-fluid content-height">
		<div class="col-md-8 page-header">
		<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav" id="nav1">
							<a href="#" id="addMkUser"><asset:image src="add.png" /></a>
							<a href="#" data-toggle="modal" data-target="#viewOrEditUsersModal" onclick="isViewOrEdit(0);"><asset:image src="view.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="save.png" /></a>
							<a href="#" data-toggle="modal" data-target="#viewOrEditUsersModal" onclick="isViewOrEdit(1);"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="delete.png" /></a>
							<a href="#" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div class="nav" id="nav2" style="display: none;">
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="add.png" /></a>
							<a href="javascript:void(0)" class="cursordefault" data-toggle="modal" data-target="#viewOrEditUsersModal" onclick="isViewOrEdit(0);"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)" class="cursordefault" data-toggle="modal" data-target="#viewOrEditUsersModal" onclick="isViewOrEdit(1);"><asset:image src="edit.png" /></a>
							<a href="#" onclick="saveOnClick();"><asset:image src="save.png" /></a> 
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a> 
							<a href="javascript:void(0)" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>						
						<g:select class="enableDisable" disabled="disabled" name="brName" from="${brMstTabList?.BR_NAME}" keys="${brMstTabList?.BR_CODE}" noSelection="${['':'Select Branch...']}" onchange="clearCompRole();" />							
						<div id="create-mkUsers" class="content scaffold-create">
							<h1>User Management</h1>
							<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mkUsersInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mkUsersInstance}" var="error">
										<li
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" />
										</li>
									</g:eachError>
								</ul>
							</g:hasErrors>
								<div class="modal-body">
								<div class="content-bg pull-left">
									<div class="col-md-12">
										<div class="panel panel-default">
											<div class="table_bg">
												<table class="table">
													<tr>
														<td>Name</td>
														<td colspan="2"><g:textField name="name" class="form-control enableDisable" disabled="disabled" /></td>
													</tr>
													<tr>
														<td>Password</td>
														<td colspan="2"><g:passwordField name="password" class="form-control enableDisable" disabled="disabled" /></td>
													</tr>
													<tr>
														<td>Active User</td>
														<td><g:radio class="actRadioCLSY enableDisable" name="actRadio" id="actRadio" checked="checked" value="Y" />Yes</td>
														<td><g:radio class="actRadioCLSN enableDisable" name="actRadio" id="actRadio" value="N" />No</td>
													</tr>
													<tr>
														<td>Authorised User</td>
														<td><g:radio class="authRadioCLSY enableDisable" name="authRadio" id="authRadio" checked="checked" value="Y" />Yes</td>
														<td><g:radio class="authRadioCLSY enableDisable" name="authRadio" id="authRadio" value="N" />No</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-body">
								<div class="content-bg pull-left">
									<div class="col-md-12">
										<div class="panel panel-default">
											<div class="table_bg">
												<table class="table">
													<tr>
														<td align="center">Company</td>
														<td align="center">Roles</td>
													</tr>
														<g:each in="${sizeList}" status="i" var="compcnt">
														<tr>
															<td>
																<div class="textbtn">
																	<g:textField name="compId${i}" id="compId${i}" class="form-control enableDisable" disabled="disabled"/>
																	<button type="button" id="btnCompPP${i}" class="btn btn-info btn-clk enableDisable" data-toggle="modal" data-target="#companiesModal" disabled="disabled" onclick="getCompanies(${i});">...</button>
																</div>
															</td>
															<td>
																<div class="textbtn">
																	<g:textField name="roleId${i}" id="roleId${i}" class="form-control enableDisable clearVal" disabled="disabled"/>
																	<button type="button" id="btnRolePP${i}" class="btn btn-info btn-clk enableDisable" data-toggle="modal" data-target="#rolesModal" disabled="disabled" onclick="getRolesBasedOnBranch(${i});" style="height: 4ex;">...</button>
																</div>
															</td>
														</tr>
													</g:each>
												</table>
												<table class="table">
													<tr>
														<td align="center" width="49%">Financial Year Start Date</td>
														<td align="center" colspan="2" width="49%">Financial Year End Date</td>
													</tr>
													<g:each in="${fnCDtTranspose}" status="i" var="cDtInstance">
														<tr id="curDte">
															<g:each in="${cDtInstance}" var="stInstance">
																<td align="center" width="49%"> ${stInstance}</td>
															</g:each>
															<td><g:checkBox name="selectDate" id="selectDate" class="selectCurDteCls" value="${cDtInstance}" checked="" /></td>
														</tr>
													</g:each>
													<g:each in="${fnPDtTranspose}" status="J" var="pDtInstance">
														<tr id="prvDte">
															<g:each in="${pDtInstance}" var="pvInstance">
																<td align="center" width="49%"> ${pvInstance} </td>
															</g:each>
															<td><g:checkBox name="selectDate" id="selectDate" class="selectPrvDteCls" value="${pDtInstance}" checked="" /></td>
														</tr>
													</g:each>
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
	</div>
<%-- POPUP START FOR COMPANY --%>
<div class="modal fade" id="companiesModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Companies</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger" role="alert" id="compPopErr" style="display: none;"></div>
						<table class="table table-hover table-nomargin table-bordered" id="compPopup">
							<thead>
								<tr>
									<th><strong>Company Name</strong></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info btn-clk" onclick="getCompanyName();">Ok</button>
				<button type="button" class="btn btn-info btn-clk" data-dismiss="modal">Cancel</button>
	   		</div>
		</div>
	</div>
</div>
<%-- POPUP END FOR COMPANY --%>
<%-- POPUP START FOR ROLES --%>
<div class="modal fade" id="rolesModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Role Name</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger" role="alert" id="rolePopErr" style="display: none;"></div>
				<div class="table_bg">
				<table id="rolePopup" class="table">
					<thead>
						<tr>
							<th><strong>Roles</strong></th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>	
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-info btn-clk" onclick="getRoleName();">Ok</button>
			<button type="button" class="btn btn-info btn-clk" data-dismiss="modal">Cancel</button>
		</div>
	</div>
</div>	
</div>
<%-- POPUP END FOR ROLES --%>
<%-- POPUP START FOR VIEW or EDIT--%>
<div class="modal fade" id="viewOrEditUsersModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">User's List</h4>
			</div>
			<div class="modal-body">
				<table id="mkUsersPopup" class="table table-hover table-nomargin table-bordered">
					<thead>
						<tr>
							<th><strong>Name</strong></th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${mkUsersLstEdit}" status="i" var="mstUsersInst">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnUser" name="${mstUsersInst?.CH_USER_CODE}" id="${mstUsersInst?.CH_USER_CODE}">
								<td> ${mstUsersInst.VC_USER_NAME}</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info btn-clk" onclick="getUserData();">Add</button>
				<button type="button" class="btn btn-info btn-clk" data-dismiss="modal">Cancel</button>
			</div>
		</div>
	</div>
</div>
<%-- POPUP END FOR VIEW or EDIT--%>
</g:form>

</body>
</html>