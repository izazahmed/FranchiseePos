<%-- 
-- File Name: create
-- Description: This page displays create Page of Mk Roles
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
	<meta name='layout' content='mainerphq' />
	<title>Role Management</title>
	<script type="text/javascript">
		var popRoleName = "";
		$(document).ready(function(){
			$.ajaxSetup({
		           async: false
		        });
			$("#viewImg").click(function(){
				$("#roleName").attr("disabled" , false);
			});
			applyDataTable('rolePopTblId');
			$(".clickOnRolePop td:not(.action)").click(function () {
				popRoleName =$(this).closest('tr').attr('name');
				$(".highlight").removeClass('highlight');
				$("#popRolAndBrId").addClass('highlight');
			});
		});
		var firstlevelMenuId="";
		function doSelectDeselectMenu(chkedKey,chkedVal,menu){
			var menuArray =menu.substring(1,(menu.length-1)).split(',');
			var menuArrayKey=[];
			jQuery.each(menuArray, function(index, item) {
				menuArrayKey.push(item.split(':')[0]);
			});
			if($("#"+chkedKey+"_cbk").is(":checked")) {
				//insert data in temp table block starts
				var parameter = {moduleId:chkedKey}
				var menuArray;
				$("#spinner").show();
				$.ajax({
					url : "${request.getContextPath()}/MkRoles/insertIntoTempTable",
					async : false,
					data : parameter,
					success : function(data){	
						$("#spinner").hide();	
					}
				});			
				//insert temp tables block data ends
				jQuery.each(menuArrayKey, function(index, item) {
						$("#"+item.trim()+"_cbk").attr("disabled" , 'disabled');
						if(item.trim()==chkedKey.trim()){
							$("#"+item.trim()).attr("disabled" , false);
						}else{
							$("#"+item.trim()).attr("disabled" , 'disabled');
							$("#"+item.trim()).attr("disabled" , 'disabled');
						}
				});								
				}else{
					var branchId=$("#compDD").val();
					$("#spinner").show();
					var parameter = {chkedKey:chkedKey,branchId:branchId}					
					$.ajax({
						url : "${request.getContextPath()}/MkRoles/deleteModuleIds",
						async : false,
						data : parameter,
						success : function(data){	
							$("#spinner").hide();	
						}
					});	
				}
		 }			
		function menuButtonClick(menu_ids,menunm){		
			var parameter = {moduleId:menu_ids}
			var menuArray;
			$("#spinner").show();
			$.ajax({
				url : "${request.getContextPath()}/MkRoles/getmodulesAvail",
				async : false,
				data : parameter,
				success : function(data){	
					$("#spinner").hide();	
					menuArray =data.substring(1,(data.length-1)).split(',');
					jQuery.each(menuArray, function(index, item) {
						if(item.split(":")[1]!='0'){
								$("#firstlevelMenuId").val(menu_ids);
								$("#moduleSelectionId").val(menu_ids);		
								$("#chkModuleLevelSelection").val($("#chkModuleLevelSelection").val());		
								$("#spinner").show();				
								document.getElementById("roleForm").action ="firstLevelMenuPage";
						    	document.getElementById("roleForm").submit();
						}else{
								$("#Error").html('No menu available for this selection!!').show();
								hideSuccessErrorMessage("alert");
								return false;
							}
						});
					}
				});		
		}
		function saveMenuDataIn(){
			var branchId=$("#compDD").val();
			var rollName=$("#rollName").val();
			if(rollName==""){
				$("#Error").html('Please enter the roll name').show();
				hideSuccessErrorMessage("alert");
				return false;
			}else{
				if($("#updateVal").val() != 'update'){
					$("#spinner").show();
					var parameter = {branchId:branchId,rollName:rollName}
					$.ajax({
						url : "${request.getContextPath()}/MkRoles/insertIntoMainTable",
						async : false,
						data : parameter,
						success : function(data){		
							$("#spinner").hide();
						}
					});	
				}else{		
					var role_idss=$("#role_ids").val();
					var rollName=$("#rollName").val();
					var branchCode=$("#branchCode").val();
					$("#spinner").show();
					var parameter = {role_ids:role_idss,rollName:rollName,branchCode:branchCode}
					$.ajax({
						url : "${request.getContextPath()}/MkRoles/updateIntoMainTable",
								async : false,
								data : parameter,
								success : function(data){	
									$("#spinner").hide();	
								}
						});	
					}
				$("#spinner").show();	
				document.getElementById("roleForm").action ="create";
			    document.getElementById("roleForm").submit();
			}
		}
		function displayPopRoleOnRole(){
			$(".clickOnRolePop").removeClass('highlight');
			var roleName=popRoleName.split("~")[0];
			var role_ids=popRoleName.split("~")[1];
			var br_code=popRoleName.split("~")[2];
			$("#role_ids").val(role_ids);
			$("#rollName").val(roleName);
			$("#rollname_hid").val(roleName);		
			$("#updateVal").val('update');
			$("#branchCode").val(br_code);
			$("#spinner").show();
			var parameter = {roleName:roleName,role_id:role_ids,br_code:br_code}
			$.ajax({
				url : "${request.getContextPath()}/MkRoles/insertIntoTempTableBasedOnSelectedRole",
				async : false,
				data : parameter,
				success : function(data){	
					$("#spinner").hide();	
					$('#'+data+'_cbk').attr('checked',true)
					$('#'+data).attr('disabled',false)
				}
			});	
		}
		function returnToMainPage(){
			 if(confirm("Sure You Want To Exit?")){
			 	$("#spinner").show();
			 	document.getElementById("roleForm").action ="${request.getContextPath()}/company/mainModule";
		    	document.getElementById("roleForm").submit();
			 }
		 }
		 function clearAllFields(){
		 	if(confirm("Sure You Want To Cancel?")){
			 	$("#roleForm").reset();	 	
		 	}
		 } 
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
		<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<g:link class="cursordefault"><asset:image src="add.png" /></g:link>
							<a href="#" class="cursordefault" data-toggle="modal" data-target="#viewPopup" data-whatever="@fat"><asset:image src="view.png" /></a> 
							<a href="#" class="cursordefault" data-toggle="modal" data-target="#editPopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="#" onclick="saveMenuDataIn();"><asset:image src="save.png" /></a> 
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a> 
							<a href="javascript:void(0)" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>								
							<select id="compDD" name="compDD">
							<g:each in="${complist}" status="i" var="complistData">
							<option value="${complistData.key}">${complistData.value}</option>							
							</g:each>
							</select>
						</div>
						<div id="create-roles" class="content scaffold-create">
							<h1>
								<g:message code="Roles" args="Roles" />
							</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
												error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="roleForm" id="roleForm">
								<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
								<g:hiddenField name="firstlevelMenuId" id="firstlevelMenuId" />
								<g:hiddenField name="moduleSelectionId" id="moduleSelectionId" />
								<g:hiddenField name="role_ids" id="role_ids" />
								<g:hiddenField name="updateVal" id="updateVal" />
								<g:hiddenField name="branchCode" id="branchCode" />
								<g:hiddenField name="rollname_hid" id="rollname_hid"/>
								
								<fieldset class="form">
									<div class="row cust-tab-form">
										<div class="col-md-8 col-sm-8 col-xs-8">
											<label for="concept" class="col-sm-4 control-label"><g:message
													code="customerForm.add1.label" default="Role Name" /><span class="redColor">*</span></label>
											<div class="col-sm-8 col-md-8 col-xs-8">
												<g:textField name="rollName" class="form-control" value="" tabindex="1" />
											</div>
										</div>
										<button id="roleName" name="roleName" type="button" disabled="disabled" class="active_btn" style="height: 4ex;" data-toggle="modal" data-target="#roleModalPop">...</button>																			
									</div>
									<table style="width: 100%; align: center;">
										<g:set var="count" value="0" />
										<g:each in="${menuList}" status="i" var="moduleVal">
											<g:set var="count" value="${i}" />
											<g:if test="${count %2==0}">
											
														 <g:if test="${moduleVal.key.toString().trim()==selectedModuleId.toString().trim()}">		
														 	<tr style="height: 35x">
																<td style="width: 10%;"><button id="${moduleVal.key}" name="${moduleVal.key}" type="button"  class="btn btn-info btnTax-clk" style="height: 4ex;"
																		onclick="menuButtonClick('${moduleVal.key}','${moduleVal.value}')">...</button></td>
																<td></td>
																<td style="width: 38%; background-color: gray;" align="left">									 
																<input type="checkBox" name="${moduleVal.key+'_cbk'}" id="${moduleVal.key+'_cbk'}" checked="checked" onclick="doSelectDeselectMenu('${moduleVal.key}','${moduleVal.value}','${menuList}');" />
														</g:if>
														<g:else>
														  <tr style="height: 35x">
															<td style="width: 10%;"><button id="${moduleVal.key}" name="${moduleVal.key}" type="button" disabled="disabled" class="btn btn-info btnTax-clk" style="height: 4ex;"
																onclick="menuButtonClick('${moduleVal.key}','${moduleVal.value}')">...</button></td>
															<td></td>
															<td style="width: 38%; background-color: gray;" align="left">
															<g:checkBox name="${moduleVal.key+'_cbk'}" id="${moduleVal.key+'_cbk'}" onclick="doSelectDeselectMenu('${moduleVal.key}','${moduleVal.value}','${menuList}');" />
														</g:else>
															
														<label style="color: white">
															${moduleVal.value}
													</label></td>
													<td style="width: 2%;"></td>
											</g:if>
											<g:else>
										
										   <g:if test="${moduleVal.key.toString().trim()==selectedModuleId.toString().trim()}">
												<td style="width: 10%;"><button id="${moduleVal.key}" name="${moduleVal.key}" type="button" class="btn btn-info btnTax-clk" style="height: 4ex;"
														onclick="menuButtonClick('${moduleVal.key}','${moduleVal.value}')">...</button>
												</td>
												<td></td>
												<td style="width: 38%; background-color: gray;" align="left">
													<input type="checkBox" name="${moduleVal.key+'_cbk'}" checked="checked" id="${moduleVal.key+'_cbk'}"
														onclick="doSelectDeselectMenu('${moduleVal.key}','${moduleVal.value}','${menuList}');" />
											</g:if>
											<g:else>
												<td style="width: 10%;"><button id="${moduleVal.key}" name="${moduleVal.key}" type="button" disabled="disabled"
														class="btn btn-info btnTax-clk" style="height: 4ex;"
														onclick="menuButtonClick('${moduleVal.key}','${moduleVal.value}')">...</button>
												</td>
												<td></td>
												<td style="width: 38%; background-color: gray;" align="left">
												<g:checkBox name="${moduleVal.key+'_cbk'}" id="${moduleVal.key+'_cbk'}"
														onclick="doSelectDeselectMenu('${moduleVal.key}','${moduleVal.value}','${menuList}');" />
											</g:else>
												<label style="color: white"> ${moduleVal.value}
												</label>
												</td>
												<td style="width: 2%;"></td>
												</tr>
												<tr>
													<td style="height: 8px;"></td>
												</tr>
											</g:else>

										</g:each>
									</table>
								</fieldset>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="roleModalPop" role="dialog">
		    <div class="modal-dialog">
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Roles & Modules</h4>
		        </div>
		        <div class="modal-body">
		        	<table width="100%" id="rolePopTblId" class="table">          
					<thead>
						<tr>
							<th>Branch Name</th>
							<th>Role</th>
						</tr>
					</thead>			
						<tbody>
							<g:each in="${brAndRoleData}" status="i" var="brAndRoleDataInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnRolePop" name="${brAndRoleDataInst?.VC_ROLE_NAME}~${brAndRoleDataInst?.CH_ROLE_CODE}~${brAndRoleDataInst?.BR_CODE}" id="popRolAndBrId">
									<td>${brAndRoleDataInst?.BR_NAME}</td>		
									<td>${brAndRoleDataInst?.VC_ROLE_NAME}</td>
								</tr>					
							</g:each>
							<div id="catDivId"></div>
						</tbody>
					</table>                 
		        </div>
		        <div class="modal-footer">
		          	<button type="button" class="active_btn" onclick="displayPopRoleOnRole();" data-dismiss="modal">OK</button>
		          	<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
		        </div>
		      </div>
		    </div>
		  </div>
</body>
</html>
