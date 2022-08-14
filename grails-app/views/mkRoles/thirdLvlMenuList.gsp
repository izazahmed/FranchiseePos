<%-- 
-- File Name: thirdLvlMenuList
-- Description: This page displays thirdLvlMenuList Page of role assignment
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
		function returntoSecondPage(menu_ids,secondLvlId){
			$("#secondlevelMenuId").val(secondLvlId);
			$("#firstlevelMenuId").val(menu_ids);
			document.getElementById("rolethirdMenuForm").action ="secondLvlMenuPage";
		    document.getElementById("rolethirdMenuForm").submit();
		}
			/*function populateThirdLvlMenu(thirdLvlId)
			{
				$("#thirdlevelMenuId").val(thirdLvlId);
				document.getElementById("roleSecMenuForm").action ="thirdLvlMenuList";
			    document.getElementById("roleSecMenuForm").submit();
			}*/
		function enabledRespectiveButton(buttonid){
			if($("#"+buttonid).is(":checked")) {
				$("#twoLvlBtn_"+buttonid).attr("disabled" , false);
			}else{
				$("#twoLvlBtn_"+buttonid).attr("disabled", 'disabled');
			}
		}
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
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
							<g:form name="rolethirdMenuForm" id="rolethirdMenuForm">
								<g:hiddenField name="firstlevelMenuId" id="firstlevelMenuId" value='${firstlevelMenuId}' />
								<g:hiddenField name="secondlevelMenuId" id="secondlevelMenuId" value='${secmenuId}' />
								<g:hiddenField name="thirdlevelMenuId" id="thirdlevelMenuId" value='' />
								<fieldset class="form">
									<div class="row cust-tab-form" style="align: center; border: 2px; overflow: auto; height: 400px;">
										<div class="col-md-8 col-sm-8 col-xs-8" align="center">
											<table style="align: center; border: 2px;">
												<g:each in="${secondLevelMenu}" status="i" var="moduleVal">
													<tr>
														<td width="60%"><b> ${moduleVal.value}</b></td>
														<td width="20%"><input type='checkbox' id='${moduleVal.key}' name='${moduleVal.key}' value='${moduleVal.key}' onclick="enabledRespectiveButton('${moduleVal.key}');"></td>
														<td width="10%">
															<button id="twoLvlBtn_${moduleVal.key}" name="twoLvlBtn_${moduleVal.key}" type="button" class="btn btn-info btnTax-clk" disabled="disabled" style="height: 3ex; width: 3ex;" onclick="populateThirdLvlMenu('${moduleVal.key}');">...</button>
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
			<button id="returnSecondPage" name="returnSecondPage" type="button" class="btn btn-info btnTax-clk" style="height: 6ex; width: 6ex;" onclick="returntoSecondPage($('#firstlevelMenuId').val(),$('#secondlevelMenuId').val());">...</button>
		</div>
	</div>
</body>
</html>