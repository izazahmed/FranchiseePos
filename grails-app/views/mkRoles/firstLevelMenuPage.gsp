<%-- 
-- File Name: firstLevelMenuPage
-- Description: This page displays firstLevelMenuPage Page of role assignment
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
		var firstLvlSelection="";
		function populateSecondLvlMenu(secondlevelMenuId){
			var moduleId=$("#firstlevelMenuId").val();
			var parameter = {moduleId:moduleId,secondlevelMenuId:secondlevelMenuId}
			var menuArray;
			$("#spinner").show();
			$.ajax({
				url : "${request.getContextPath()}/MkRoles/getFirstLvlmodulesAvail",
				async : false,
				data : parameter,
				success : function(data){	
					$("#spinner").hide();	
					menuArray =data.substring(1,(data.length-1)).split(',');
					jQuery.each(menuArray, function(index, item) {			
						if(item.split(":")[1]!='0'){
							$("#secondlevelMenuId").val(secondlevelMenuId);
							$("#spinner").show();
							document.getElementById("roleSecMenuForm").action ="secondLvlMenuPage";
						    document.getElementById("roleSecMenuForm").submit();
						}else{
							$("#Error").html('No menu available for this selection!!').show();
							hideSuccessErrorMessage("alert");
							return false;
						}
					});
				}
			});
		}
		function returntoMainMenuPage(menu_ids){
			$("#firstlevelMenuId").val(menu_ids);
			$("#spinner").show();
			document.getElementById("roleSecMenuForm").action ="create";
		    document.getElementById("roleSecMenuForm").submit();
		}
		function enabledRespectiveButton(buttonid){	
			var firstlevelMenuId=$("#firstlevelMenuId").val();
			if($("#"+buttonid).is(":checked")) {
				$("#spinner").show();
				var parameter = {firstlevelMenuId:buttonid,moduleId:firstlevelMenuId,insert:'0'}
				$.ajax({
						url : "${request.getContextPath()}/MkRoles/insertIntoTempTableForfirstLvlMenu",
						async : false,
						data : parameter,
						success : function(data){		
							$("#spinner").hide();
						}
					});			
				$("#oneLvlBtn_"+buttonid).attr("disabled" , false);
				}else{
					$("#spinner").show();
					var parameter = {firstlevelMenuId:buttonid,moduleId:firstlevelMenuId,delete:'0'}
					$.ajax({
							url : "${request.getContextPath()}/MkRoles/deletefirstLvlIds",
							async : false,
							data : parameter,
							success : function(data){
								$("#spinner").hide();		
							}
						});	
					$("#oneLvlBtn_"+buttonid).attr("disabled", 'disabled');
				}
		}
		function checkAll() {
		    var allfirstLvlMenuId="";
		    var moduleId=$("#firstlevelMenuId").val();
			var checkboxes = document.getElementsByTagName('input');
		    for (var i = 0; i < checkboxes.length; i++) {
	         	if (checkboxes[i].type == 'checkbox') {
			         $("#oneLvlBtn_"+checkboxes[i].value).attr("disabled" , false);
			         allfirstLvlMenuId+=checkboxes[i].value+"~";
			         checkboxes[i].checked = true;
		        }
	      	}
		    $("#spinner").show();  
		 	var parameter = {firstlevelMenuId:allfirstLvlMenuId,moduleId:moduleId,insert:'1'}     			 
			$.ajax({
				url : "${request.getContextPath()}/MkRoles/insertIntoTempTableForfirstLvlMenu",
				async : false,
				data : parameter,
				success : function(data){	
					$("#spinner").hide();	
				}
			});
		}
		function deCheckAll() {
			var allfirstLvlMenuId="";
		    var moduleId=$("#firstlevelMenuId").val();
			var checkboxes = document.getElementsByTagName('input');
		  	for (var i = 0; i < checkboxes.length; i++) {
		         if (checkboxes[i].type == 'checkbox') {
		         $("#oneLvlBtn_"+checkboxes[i].value).attr("disabled" , 'disabled');
		             checkboxes[i].checked = false;
		             allfirstLvlMenuId+=checkboxes[i].value+"~";
		        }
		    }
		    $("#spinner").show(); 
		    var parameter = {firstlevelMenuId:allfirstLvlMenuId,moduleId:moduleId,delete:'1'}     			 
			$.ajax({
				url : "${request.getContextPath()}/MkRoles/deletefirstLvlIds",
				async : false,
				data : parameter,
				success : function(data){		
					$("#spinner").hide();
				}
			});
		 }
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-15">
						<div id="create-roles" class="content scaffold-create">
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" />
										</li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="roleSecMenuForm" id="roleSecMenuForm">
								<g:hiddenField name="secondlevelMenuId" id="secondlevelMenuId" />
								<g:hiddenField name="firstlevelMenuId" id="firstlevelMenuId" value="${moduleCode}" />
								<fieldset class="form">
									<div class="row cust-tab-form" style="align: center; border: 2px; overflow: auto;">
										<div class="col-md-8 col-sm-8 col-xs-8" align="center">
											<table style="align: center; border: 2px">
												<g:each in="${firstLevelMenu}" status="i" var="moduleVal">
													<tr>
														<td width=""><b> ${moduleVal.value}</b></td>
														<td width="10%">
														 <g:if test="${selectedsDataMap.toString().contains(moduleVal.key)}">
															<input type='checkbox' id='${moduleVal.key}' checked="checked" name='check' value='${moduleVal.key}' onclick="enabledRespectiveButton('${moduleVal.key}');">
															<td width="10%">
																<button id="oneLvlBtn_${moduleVal.key}" name="oneLvlBtn_${moduleVal.key}" type="button" class="btn btn-info btnTax-clk"  style="height: 3ex; width: 3ex;" onclick="populateSecondLvlMenu('${moduleVal.key}');">...</button>
															</td>
														</g:if>
														<g:else>
															<input type='checkbox' id='${moduleVal.key}' name='check' value='${moduleVal.key}' onclick="enabledRespectiveButton('${moduleVal.key}');">
															<td width="10%">
																<button id="oneLvlBtn_${moduleVal.key}" name="oneLvlBtn_${moduleVal.key}" type="button" class="btn btn-info btnTax-clk" disabled="disabled" style="height: 3ex; width: 3ex;" onclick="populateSecondLvlMenu('${moduleVal.key}');">...</button>
															</td>
														</g:else>
														</td>
													</tr>
													<tr></tr>
												</g:each>
												<tr><td colspan="3"></td></tr>
												<tr>
													<td colspan="3" align="center">
														<button id="selectAllBtn" name="selectAllBtn" type="button" class="btn btn-info btnTax-clk" onclick="checkAll();">Select All</button>
														<button id="DeselectAllBtn" name="DeselectAllBtn" type="button" class="btn btn-info btnTax-clk" onclick="deCheckAll();">Deselect All</button>
													</td>
												</tr>
											</table>
										</div>
										<div style="vertical-align: middle; margin-top: 70px;">
											<button id="returnFirstPage" name="returnFirstPage" type="button" class="btn btn-info btnTax-clk" style="height: 6ex; width: 6ex;" onclick="returntoMainMenuPage($('#firstlevelMenuId').val());">...</button>
										</div>
									</div>
								</fieldset>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>