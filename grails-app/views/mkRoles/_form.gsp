<%@ page import="com.tbz.franchisee.MkModule"%>

<script type="text/javascript">
	var popRoleNamee = "";
	var popRoleId = "";
	var popModCd = "";
	$(document).ready(function(){
	    $.ajaxSetup({
	    	async: false
	    });	 
		var viewModIdFrmIndex = $("#viewModIdFrmIndex").val();
		var viewRoleName = $("#viewRoleName").val();
		var viewBrId = $("#viewBrId").val();
		if(viewModIdFrmIndex !== "" && viewModIdFrmIndex != null && viewModIdFrmIndex !== undefined){				
			if(viewModIdFrmIndex.toString().length==1){
					viewModIdFrmIndex="0"+viewModIdFrmIndex;
				}
				popModCd = viewModIdFrmIndex;
			getRoleData(1);
		}
		if(viewRoleName !== "" && viewRoleName != null && viewRoleName !== undefined){		
	 		$("#rollName").val(viewRoleName);
	 		$("#rollName").attr('disabled', true);
		}
		if(viewBrId !== "" && viewBrId != null && viewBrId !== undefined){		
			$("#compDD").val(viewBrId);
		}
	});
	function viewPopUp(val){
	$("#typeValue").val(val);
		var html = ''
		var brName = ''	
		var brCode = $("#compDD").val();
		$("#spinner").show();
		var parameter = {brCode:brCode}
			$.ajax({
			 	url: "${request.getContextPath()}/MkRoles/getViewDataBasedOnBranch",
			 	data : parameter,
				async : false,
				success : function(data){				
					$("#spinner").hide();
					if(data.BR_CODE.length == ''){
						$("#brRlError").html('');
						$("#brRlError").html('No Data For Selected Branch').show();
						hideSuccessErrorMessage("alert");				
					}else{
						for(var i=0;i<data.BR_CODE.length;i++){	
							brName = data.BR_NAME[i];
						html +='<tr id="popRoleId'+i+'" name="'+data.VC_ROLE_NAME[i]+'" value="'+data.CH_ROLE_CODE[i]+'" onclick="addCssToRow('+i+','+data.CH_ROLE_CODE[i]+','+data.VC_MODULE_CODE[i]+');">'+ 				
				 				'<td>'+data.BR_NAME[i]+'</td>'+
				 				'<td id="popRlNmId'+i+'">'+data.VC_ROLE_NAME[i]+'</td>'+
				 				'<td>'+data.VC_MODULE_OBJECT[i]+'</td>'+
				 				'</tr>';		
						}												
					}
					$("#roleViewModalTableId tbody").empty();
					$("#roleViewModalTableId tbody").append(html);  	
					applyDataTable('roleViewModalTableId');	
			 		var perBrName = brName+"%";
			 		//$("#findBrRl").val(perBrName);				
				}
			 });	
	}	
	function addCssToRow(val,rlid,mdlCode){
	 	popRoleName = $("#popRlNmId" + val+"").html();
	 	popRoleId=rlid; 	
	 	$("#viewRoleId").val(popRoleId);
	 	popModCd=mdlCode;
		$('.highlight').removeClass('highlight');
		$("#popRoleId" + val+"").addClass('highlight');
	 }
	 function getRoleData(val){ 	
	 	var html = '';
	 	var count = '0';
	 	if(val == 0){
	 		$("#rollName").val(popRoleName);
	 		$("#rollName").attr('disabled', true);
	 		$("#roleViewModal .close").click(); 	
	 	}else{}
	 	$("#spinner").show();
	 	$.getJSON("${request.getContextPath()}/MkRoles/getModuleList", function(json) {	 
	 		$("#spinner").hide();
	 			for(var j=0;j<json.VC_MODULE_CODE.length;j++){
	 				count = j;
			 		if(count %2==0){
			 			if(count==2){
			 				if(json.VC_MODULE_CODE[j]==popModCd){
								html +='<td style="width: 10%;"><button id="'+json.VC_MODULE_CODE[j]+'" name="'+json.VC_MODULE_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 4ex;" onclick="getFirstLevelCheckedViewData('+json.VC_MODULE_CODE[j]+','+$('#viewRoleId').val()+')">...</button></td>'+					
								'<td style="width: 38%; background-color: gray;" align="left">'+
									'<input type="checkbox" name="'+json.VC_MODULE_CODE[j]+'" id="'+json.VC_MODULE_CODE[j]+'" checked="checked" disabled="disabled" />'+
										'<label style="color: white">'+json.VC_MODULE_OBJECT[j]+'</label>'+
								'</td>'+
								'<td style="width: 2%;"></td>'
								'<tr>'+
									'<td style="height: 8px;"></td>'+
								'</tr>';
								}else{
									html +='<td style="width: 10%;"><button id="'+json.VC_MODULE_CODE[j]+'" name="'+json.VC_MODULE_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 4ex;" disabled="disabled">...</button></td>'+					
									'<td style="width: 38%; background-color: gray;" align="left">'+
										'<input type="checkbox" name="'+json.VC_MODULE_CODE[j]+'" id="'+json.VC_MODULE_CODE[j]+'" disabled="disabled" />'+
											'<label style="color: white">'+json.VC_MODULE_OBJECT[j]+'</label>'+
									'</td>'+
									'<td style="width: 2%;"></td>'
									'<tr>'+
										'<td style="height: 8px;"></td>'+
									'</tr>';
								}
							}else{
								if(json.VC_MODULE_CODE[j]==popModCd){
									html +='<tr style="height: 35x">'+
									'<td style="width: 10%;"><button id="'+json.VC_MODULE_CODE[j]+'" name="'+json.VC_MODULE_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 4ex;" onclick="getFirstLevelCheckedViewData('+json.VC_MODULE_CODE[j]+','+$('#viewRoleId').val()+')">...</button></td>'+					
									'<td style="width: 38%; background-color: gray;" align="left">'+
										'<input type="checkbox" name="'+json.VC_MODULE_CODE[j]+'" id="'+json.VC_MODULE_CODE[j]+'" checked="checked" disabled="disabled" />'+
											'<label style="color: white">'+json.VC_MODULE_OBJECT[j]+'</label>'+
									'</td>'+
									'<td style="width: 2%;"></td>'
									'</tr>'+
									'<tr>'+
										'<td style="height: 8px;"></td>'+
									'</tr>';
								}else{
									html +='<tr style="height: 35x">'+
									'<td style="width: 10%;"><button id="'+json.VC_MODULE_CODE[j]+'" name="'+json.VC_MODULE_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 4ex;" disabled="disabled">...</button></td>'+					
									'<td style="width: 38%; background-color: gray;" align="left">'+
										'<input type="checkbox" name="'+json.VC_MODULE_CODE[j]+'" id="'+json.VC_MODULE_CODE[j]+'" disabled="disabled" />'+
											'<label style="color: white">'+json.VC_MODULE_OBJECT[j]+'</label>'+
									'</td>'+
									'<td style="width: 2%;"></td>'
									'</tr>'+
									'<tr>'+
										'<td style="height: 8px;"></td>'+
									'</tr>';
								}
							}
						}else{
							if(json.VC_MODULE_CODE[j]==popModCd){
								html +='<td style="width: 10%;"><button id="'+json.VC_MODULE_CODE[j]+'" name="'+json.VC_MODULE_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 4ex;" onclick="getFirstLevelCheckedViewData('+json.VC_MODULE_CODE[j]+','+$('#viewRoleId').val()+')">...</button></td>'+						
								'<td style="width: 38%; background-color: gray;" align="left">'+
									'<input type="checkbox" name="'+json.VC_MODULE_CODE[j]+'" id="'+json.VC_MODULE_CODE[j]+'" checked="checked" disabled="disabled" />'+
									  '<label style="color: white">'+json.VC_MODULE_OBJECT[j]+'</label>'+
								'</td>'+
								'<td style="width: 2%;"></td>'+
								'<tr>'+
									'<td style="height: 8px;"></td>'+
								'</tr>';
							}else{
								html +='<td style="width: 10%;"><button id="'+json.VC_MODULE_CODE[j]+'" name="'+json.VC_MODULE_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 4ex;" disabled="disabled">...</button></td>'+						
								'<td style="width: 38%; background-color: gray;" align="left">'+
									'<input type="checkbox" name="'+json.VC_MODULE_CODE[j]+'" id="'+json.VC_MODULE_CODE[j]+'" disabled="disabled" />'+
									  '<label style="color: white">'+json.VC_MODULE_OBJECT[j]+'</label>'+
								'</td>'+
								'<td style="width: 2%;"></td>'+
								'<tr>'+
									'<td style="height: 8px;"></td>'+
								'</tr>';
							}
						}
	 			} 			 			
	 			$("#mainTblId tbody").empty();
	 			$("#mainTblId tbody").append(html); 			 		
	 		});	 	
	 }
	 function getFirstLevelCheckedViewData(modId,roleId){
	 	if(modId.toString().length==1){
			modId = "0"+modId
		}
	 	if(roleId.toString().length==1){
			roleId = "0"+roleId
		}
	 	 var levelId=1;
	 	 var menuId='';
	 	 $("#spinner").show();
	 	 var parameter = {modId:modId,roleId:roleId,levelId:levelId,menuId:menuId}
	 	 $.ajax({
			 url : "${request.getContextPath()}/MkRoles/isNextMenuAailOrNotForViewOrEdit",
			 async : false,
			 data : parameter,
			 success : function(data){		
			 	$("#spinner").hide();
				if(data.countVal!='0'){
					 $("#viewModId").val(modId);		
					 var rlnm = $("#rollName").val(); 
					 $("#viewRoleName").val(rlnm);
					 var brCode = $("#compDD").val();
					 $("#viewBrId").val(brCode); 
					 $("#viewFirLvlMenuCode").val(modId);
					 $("#spinner").show();
					 document.getElementById("roleIndexForm").action ="viewFirstLevelMenuPage";
					 document.getElementById("roleIndexForm").submit();
				}else{
					$("#Error").html('No More Records Are Present').show();
					hideSuccessErrorMessage("alert");
					return false;
				}
			}
		});			 
	 }
</script>
<div class="row cust-tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.add1.label" default="Role Name" /><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="rollName" class="form-control" disabled="disabled" value="" tabindex="1" />
		</div>
	</div>
	<button id="butId" type="button" class="btn btn-info btnTax-clk" data-toggle="modal" disabled="disabled" data-target="#salesPersonPopup" style="height: 4ex;">...</button>
</div>
<table id="mainTblId" style="width: 100%; align: center;">
	<tbody>
		<g:if test="${moduleId != null && !moduleId.isEmpty()}"></g:if>
		<g:else>
			<g:set var="count" value="0" />
			<g:each in="${MkModule.list()*.vcModuleObject}" status="i" var="moduleVal">
				<g:set var="count" value="${i}" />
				<g:if test="${count %2==0}">
					<tr style="height: 35x">
						<td style="width: 10%;"><button id="${moduleVal+count}" name="${moduleVal+count}" type="button"
								class="btn btn-info btnTax-clk" style="height: 4ex;" disabled="disabled">...</button></td>
						<td></td>
						<td style="width: 38%; background-color: gray;" align="left">
							<g:checkBox name="${moduleVal+count}" id="${moduleVal+count}" onclick="doRoleOperaton('${count+moduleVal}');" disabled="disabled" />
							<label style="color: white">${moduleVal}</label>
						</td>
						<td style="width: 2%;"></td>
					</tr>
				</g:if>
				<g:else>
				<tr>
					<td style="width: 10%;"><button id="${moduleVal+count}" name="${moduleVal+count}" type="button"
							class="btn btn-info btnTax-clk" style="height: 4ex;" disabled="disabled">...</button></td>
					<td></td>
					<td style="width: 38%; background-color: gray;" align="left">
						<g:checkBox name="${count+moduleVal}" id="${count+moduleVal}" onclick="doRoleOperaton('${count+moduleVal}');" disabled="disabled" />
						<label style="color: white">${moduleVal}</label>
					</td>
					<td style="width: 2%;"></td>
					</tr>
					<tr>
						<td style="height: 8px;"></td>
					</tr>
				</g:else>
			</g:each>
		</g:else>	
	</tbody>
</table>
<%-- roleViewModal POPUP START--%>
<div class="modal fade" id="roleViewModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Roles&Modules</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger" role="alert" id="brRlError" style="display: none;"></div>
				<table class="table" id="roleViewModalTableId">							
					<thead>
						<tr>
							<th width="30%">Branch Name</th>
							<th>Role</th>
							<th>Module</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>				
			<div class="modal-footer">
				<button type="button" class="active_btn" onclick="getRoleData(0);" >OK</button>
			</div>
		</div>
	</div>
</div>
<%-- roleViewModal POPUP END--%>