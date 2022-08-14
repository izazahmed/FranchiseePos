<%-- 
-- File Name: secondLvlMenuPage
-- Description: This page displays secondLvlMenuPage Page of role assignment
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
		function returntoFirstPage(menu_ids){
			$("#secondLvlSelectedVal").val('${secmenuId}');
			$("#firstlevelMenuId").val(menu_ids);
			$("#spinner").show();
			document.getElementById("roleSecMenuForm").action ="firstLevelMenuPage";
		    document.getElementById("roleSecMenuForm").submit();
		}
		function populateThirdLvlMenu(thirdLvlId) {	
			var moduleId=$("#firstlevelMenuId").val();
			var secondlevelMenuId=$("#secondlevelMenuId").val();		
			var parameter = {moduleId:moduleId,secondlevelMenuId:secondlevelMenuId,thirdLvlId:thirdLvlId}
			var menuArray;
			$("#spinner").show();
			$.ajax({
				url : "${request.getContextPath()}/MkRoles/getThirdLvlmodulesAvail",
				async : false,
				data : parameter,
				success : function(data){		
					$("#spinner").hide();
					menuArray =data.substring(1,(data.length-1)).split(',');
					jQuery.each(menuArray, function(index, item) {			
						if(item.split(":")[1]!='0'){
							$("#thirdlevelMenuId").val(thirdLvlId);
							$("#spinner").show();
							document.getElementById("roleSecMenuForm").action ="thirdLvlMenuList";
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
		function enabledRespectiveButton(buttonid){
			if($("#"+buttonid).is(":checked")) {
				$("#twoLvlBtn_"+buttonid).attr("disabled" , false);			
				var moduleId=$("#firstlevelMenuId").val();
				var firstLvlId=$("#secondlevelMenuId").val();				
				var parameter = {moduleId:moduleId,firstLvlId:firstLvlId,thirdLvlId:buttonid,insert:'0'}
				$("#spinner").show();
				$.ajax({
					url : "${request.getContextPath()}/MkRoles/insertIntoTempTableForsecondLvlMenu",
					async : false,
					data : parameter,
					success : function(data){	
						$("#spinner").hide();	
					}
				});				
			}else{			
				var moduleId=$("#firstlevelMenuId").val();
				var firstLvlId=$("#secondlevelMenuId").val();				
				var parameter = {moduleId:moduleId,firstLvlId:firstLvlId,thirdLvlId:buttonid,insert:'0'}
				$("#spinner").show();
				$.ajax({
					url : "${request.getContextPath()}/MkRoles/deleteIntoTempTableForsecondLvlMenu",
					async : false,
					data : parameter,
					success : function(data){		
						$("#spinner").hide();
					}
				});	
				$("#twoLvlBtn_"+buttonid).attr("disabled", 'disabled');
			}
		}
		function checkAll() {
			var allsecondLvlMenuId="";
		    var moduleId=$("#firstlevelMenuId").val();
			var firstLvlId=$("#secondlevelMenuId").val();	
			var checkboxes = document.getElementsByTagName('input');
		  	for (var i = 0; i < checkboxes.length; i++) {
		         if (checkboxes[i].type == 'checkbox') {
		         	$("#twoLvlBtn_"+checkboxes[i].value).attr("disabled" , false);
		          	allsecondLvlMenuId+=checkboxes[i].value+"~";
		            checkboxes[i].checked = true;
		         }
		      }
		    var parameter = {thirdLvlId:allsecondLvlMenuId,moduleId:moduleId,firstLvlId:firstLvlId,insert:'1'}    
		    $("#spinner").show(); 			 
			$.ajax({
				url : "${request.getContextPath()}/MkRoles/insertIntoTempTableForsecondLvlMenu",
				async : false,
				data : parameter,
				success : function(data){		
					$("#spinner").hide();
				}
			});
		}
		function deCheckAll() {
			var allsecondLvlMenuId="";
		    var moduleId=$("#firstlevelMenuId").val();
			var firstLvlId=$("#secondlevelMenuId").val();
			var checkboxes = document.getElementsByTagName('input');
		  	for (var i = 0; i < checkboxes.length; i++) {
		    	if (checkboxes[i].type == 'checkbox') {
			    	$("#twoLvlBtn_"+checkboxes[i].value).attr("disabled" , 'disabled');
			     	allsecondLvlMenuId+=checkboxes[i].value+"~";
			        checkboxes[i].checked = false;
			    }
		   }			
			var parameter = {moduleId:moduleId,firstLvlId:firstLvlId,thirdLvlId:allsecondLvlMenuId,insert:'1'}
			$("#spinner").show();
			$.ajax({
				url : "${request.getContextPath()}/MkRoles/deleteIntoTempTableForsecondLvlMenu",
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
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
												error="${error}" />
										</li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="roleSecMenuForm" id="roleSecMenuForm">
								<g:hiddenField name="firstlevelMenuId" id="firstlevelMenuId" value='${firstlevelMenuId}' />
								<g:hiddenField name="secondlevelMenuId" id="secondlevelMenuId" value='${secmenuId}' />
								<g:hiddenField name="thirdlevelMenuId" id="thirdlevelMenuId" value='' />
								<g:hiddenField name="secondLvlSelectedVal" id="secondLvlSelectedVal" value='' />
								<fieldset class="form">
									<div class="row cust-tab-form" style="align: center; border: 2px; overflow: auto; height: 400px;">
										<div class="col-md-8 col-sm-8 col-xs-8" align="center">
											<table style="align: center; border: 2px;">
												<g:each in="${secondLevelMenu}" status="i" var="moduleVal">
													<tr>
														<td width="60%"><b> ${moduleVal.value}</b></td>
	 													<g:if test="${secondSelectionData.toString().contains(moduleVal.key)}">
															<td width="20%"><input type='checkbox' checked="checked" 	id='${moduleVal.key}' name='${moduleVal.key}' value='${moduleVal.key}' onclick="enabledRespectiveButton('${moduleVal.key}');">	</td>
														</g:if>
														<g:else>
															<td width="20%"><input type='checkbox' id='${moduleVal.key}' name='${moduleVal.key}' value='${moduleVal.key}' onclick="enabledRespectiveButton('${moduleVal.key}');">	</td>
														</g:else>													
														<td width="10%">
															<g:if test="${secondSelectionData.toString().contains(moduleVal.key)}">
																<button id="twoLvlBtn_${moduleVal.key}" name="twoLvlBtn_${moduleVal.key}" type="button" class="btn btn-info btnTax-clk" 	style="height: 3ex; width: 3ex;" onclick="populateThirdLvlMenu('${moduleVal.key}');">...</button>
															</g:if>
															<g:else>
																<button id="twoLvlBtn_${moduleVal.key}" name="twoLvlBtn_${moduleVal.key}" type="button" class="btn btn-info btnTax-clk" disabled="disabled" 	style="height: 3ex; width: 3ex;" onclick="populateThirdLvlMenu('${moduleVal.key}');">...</button>
															</g:else>													
														</td>
													</tr>
													<tr></tr>
												</g:each>
											</table>
										</div>
									</div>
								</fieldset>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="vertical-align: middle; margin-top: 280px;">
			<button id="returnFirstPage" name="returnFirstPage" type="button" class="btn btn-info btnTax-clk" style="height: 6ex; width: 6ex;" onclick="returntoFirstPage($('#firstlevelMenuId').val());">...</button>
		</div>
	</div>
	<div style="width: 80%; margin: 0 auto;">
		<button id="selectAllBtn" name="selectAllBtn" type="button" class="btn btn-info btnTax-clk" onclick="checkAll();">Select All</button>
		<button id="DeselectAllBtn" name="DeselectAllBtn" type="button" class="btn btn-info btnTax-clk" onclick="deCheckAll();">Deselect All</button>
	</div>
</body>
</html>