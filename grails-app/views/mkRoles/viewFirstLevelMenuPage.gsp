<%-- 
-- File Name: viewFirstLevelMenuPage
-- Description: This page displays Selected first level view data
-- Author(s): CTE. 
-- Date: 22/04/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 22/04/2016		Izaz				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>view first level</title>
	<script type="text/javascript">
		var funArray = new Array();
		var typeValue = "";
		$(document).ready(function(){
			var modId = $("#viewModId").val();
			var roleId = $("#viewRoleId").val();	
			var levelId = '1';
			var menuCode = $("#viewFirLvlMenuCode").val();
			typeValue = $("#typeValue").val();
			$("#spinner").show();
			$.getJSON("${request.getContextPath()}/MkRoles/getSelDataBasedOnLevel/?modId="+modId+"&roleId="+roleId+"&levelId="+levelId+"&menuCode="+menuCode+"&typeValue="+typeValue, function(data) {
				 console.log("data getSelDataBasedOnLevel data : "+JSON.stringify(data));
				 $("#spinner").hide();
				  for (var i = 0; i < data.VC_MENU_CODE.length; i++) {
		              funArray.push(data.VC_MENU_CODE[i]);
		          }
			}).done(function(){
				getAllDataBasedOnLevel();
			});    
		});	
		function getAllDataBasedOnLevel(){
		        var html = ''
		        var levelId = '1';
		        var modId = $("#viewModId").val();
		        var menuCode = $("#viewFirLvlMenuCode").val();
		        $("#spinner").show();
		        var parameter = {modId:modId,levelId:levelId,menuCode:menuCode}
				$.ajax({
				 	url: "${request.getContextPath()}/MkRoles/getAllDataBasedOnLevel",
				 	data : parameter,
					async : false,
					success : function(json){
						$("#spinner").hide();
						console.log("json getAllDataBasedOnLevel data : "+JSON.stringify(json));			
						if(json.VC_MENU_CODE.length == ''){
							$("#Error").html('No More Records Are Present').show();
							hideSuccessErrorMessage("alert");	
						}else{		
						 for (var j = 0; j < json.VC_MENU_CODE.length; j++) {			  
				              var vl = jQuery.inArray(json.VC_MENU_CODE[j],funArray);	
				               html +='<tr>'+
								 	  '<td><b>'+json.VC_MENU_OBJECT[j]+'</b></td>'+
								 	  '<td width="10%">'
								 	  if(vl !== -1){
								 	  	if(typeValue=='edit'){
								 	  		html+='<input type="checkbox" id="'+json.VC_MENU_CODE[j]+'" checked="checked" name="checkbox" value="'+json.VC_MENU_CODE[j]+'" onclick="insOrDelOnCheckOrUnCheck('+json.VC_MENU_CODE[j]+');">'+
						              	  	'<td width="10%"><button id="oneLvlBtn_'+json.VC_MENU_CODE[j]+'" name="oneLvlBtn_'+json.VC_MENU_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 3ex; width: 3ex;" onclick="getSecondLvlMenu('+json.VC_MENU_CODE[j]+');">...</button>'
								 	  	}else{
						              	  	html+='<input type="checkbox" id="'+json.VC_MENU_CODE[j]+'" checked="checked" disabled="disabled" value="'+json.VC_MENU_CODE[j]+'">'+
						              	  	'<td width="10%"><button id="oneLvlBtn_'+json.VC_MENU_CODE[j]+'" name="oneLvlBtn_'+json.VC_MENU_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 3ex; width: 3ex;" onclick="getSecondLvlMenu('+json.VC_MENU_CODE[j]+');">...</button>'
					              	  	}
					              	  }else{
					              	  	if(typeValue=='edit'){
					              	  		html+='<input type="checkbox" id="'+json.VC_MENU_CODE[j]+'" name="checkbox" value="'+json.VC_MENU_CODE[j]+'" onclick="insOrDelOnCheckOrUnCheck('+json.VC_MENU_CODE[j]+');">'+
						              	  	'<td width="10%"><button id="oneLvlBtn_'+json.VC_MENU_CODE[j]+'" name="oneLvlBtn_'+json.VC_MENU_CODE[j]+'" type="button" disabled="disabled" class="btn btn-info btnTax-clk" style="height: 3ex; width: 3ex;" onclick="getSecondLvlMenu('+json.VC_MENU_CODE[j]+');">...</button>'
					              	  	}else{
						              	  	html+='<input type="checkbox" disabled="disabled">'+
						              	  	'<td width="10%"><button type="button" disabled="disabled" class="btn btn-info btnTax-clk" style="height: 3ex; width: 3ex;">...</button>'
					              	  	}
					              	  }			              	   
					              	  '</td>'+
					              	  '</td>'+
								      '</tr>'+
								      '<tr></tr>'+  
								      '<tr>'+
						         	  '<td colspan="3"></td>'+
						         	  '</tr>'
				              }		         
				              html +='<tr>'+
						 	  '<td colspan="3" align="center">'
						 	  if(typeValue=='edit'){
						 	  	html +='<button id="selectAllBtn" name="selectAllBtn" type="button" class="btn btn-info btnTax-clk" onclick="selectAll();">Select All</button>'+
							 	'<button id="DeselectAllBtn" name="DeselectAllBtn" type="button" class="btn btn-info btnTax-clk" onclick="deSelectAll();">Deselect All</button>'
						 	  }else{
							 	  html +='<button id="selectAllBtn" name="selectAllBtn" type="button" disabled="disabled" class="btn btn-info btnTax-clk">Select All</button>'+
							 	  '<button id="DeselectAllBtn" name="DeselectAllBtn" type="button" disabled="disabled" class="btn btn-info btnTax-clk">Deselect All</button>'
						 	  }
						 	  '</td>'+
						 	  '</tr>'; 
				         $("#viewFirLvlMnTblId tbody").empty();
				         $("#viewFirLvlMnTblId tbody").append(html); 
				     }
				 }
				});
		      }
		function returnToViewMainMenuPage(viewFirLvlMenuCode){
			$("#viewFirLvlMenuCode").val(viewFirLvlMenuCode);
			$("#spinner").show();
			document.getElementById("viewFirLevMenForm").action ="index";
			document.getElementById("viewFirLevMenForm").submit();
		}
		function getSecondLvlMenu(menuCode){
			$("#viewSecLvlMenuCode").val(menuCode);	
			var modId = $("#viewModId").val();		
			if(modId.toString().length==1){
				modId = "0"+modId
				console.log("in modId :::"+modId);
			}	
			var roleId = $("#viewRoleId").val();
		 	if(roleId.toString().length==1){
				roleId = "0"+roleId
				console.log("in roleId :::"+roleId);
			}	
			var menuId=menuCode;
			if(menuId.toString().length==1){
				menuId = "0"+menuId
				console.log("in menuId :::"+menuId);
			}	
		 	var levelId=2; 	 
		 	$("#spinner").show();
			 var parameter = {modId:modId,roleId:roleId,levelId:levelId,menuId:menuId}
			 $.ajax({
				 url : "${request.getContextPath()}/MkRoles/isNextMenuAailOrNotForViewOrEdit",
				 async : false,
				 data : parameter,
				 success : function(data){		
				 $("#spinner").hide();
					if(data.countVal!='0'){
						$("#spinner").show();
						document.getElementById("viewFirLevMenForm").action ="viewSecondLevelMenuPage";
						document.getElementById("viewFirLevMenForm").submit();
					}else{
						$("#Error").html('No More Records Are Present').show();
						hideSuccessErrorMessage("alert");	
						return false;
					}
				}
			});	
		}
		function insOrDelOnCheckOrUnCheck(butMenuId){
			var firstlevelMenuId=$("#viewFirLvlMenuCode").val();//moduleCode
			var len = butMenuId.toString().length;
			console.log("len :::"+len);
			if(butMenuId.toString().length==1){
				butMenuId = "0"+butMenuId
				console.log("in butMenuId :::"+butMenuId);
			}
			console.log("val :"+'#'+butMenuId+'');
			if(firstlevelMenuId.toString().length==1){
				firstlevelMenuId = "0"+firstlevelMenuId
				console.log("in firstlevelMenuId :::"+firstlevelMenuId);
			}
			if($('#'+butMenuId+'').is(":checked")){
				$("#spinner").show();
				var parameter = {firstlevelMenuId:butMenuId,moduleId:firstlevelMenuId,insert:'0'}
				$.ajax({
					url : "${request.getContextPath()}/MkRoles/insertIntoTempTableForfirstLvlMenu",
					async : false,
					data : parameter,
					success : function(data){
						$("#spinner").hide();		
					}
				});		
				$("#oneLvlBtn_"+butMenuId).attr("disabled" , false);
			}else{
				//$("#spinner").show();
				var parameter = {firstlevelMenuId:butMenuId,moduleId:firstlevelMenuId,delete:'0'}
				$.ajax({
					url : "${request.getContextPath()}/MkRoles/deletefirstLvlIds",
					async : false,
					data : parameter,
					success : function(data){
						//$("#spinner").hide();		
					}
				});
				$("#oneLvlBtn_"+butMenuId).attr("disabled", 'disabled');
			}
		}
		 function selectAll(){
		      var allfirstLvlMenuId="";
		      var moduleId=$("#viewFirLvlMenuCode").val();
		      if(moduleId.toString().length==1){
					moduleId = "0"+moduleId
					console.log("in moduleId :::"+moduleId);
				}
			  var checkboxes = document.getElementsByName('checkbox');
			  console.log("len :"+checkboxes.length);
			  for (var i = 0; i < checkboxes.length; i++) {
		             if (checkboxes[i].type == 'checkbox') {
			             $("#oneLvlBtn_"+checkboxes[i].value).attr("disabled" , false);
			             allfirstLvlMenuId+=checkboxes[i].value+"~";
		                 checkboxes[i].checked = true;
		             }
		             console.log("in select all allfirstLvlMenuId :"+allfirstLvlMenuId);
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
		  function deSelectAll(){
		     var allfirstLvlMenuId="";
		     var moduleId=$("#viewFirLvlMenuCode").val();
		     if(moduleId.toString().length==1){
					moduleId = "0"+moduleId
					console.log("in moduleId :::"+moduleId);
				}
			 var checkboxes = document.getElementsByName('checkbox');
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
		<div class="col-md-4 page-header">
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
							<div class="row cust-tab-form" style="align: center; border: 2px; overflow: auto;">
								<div class="col-md-8 col-sm-8 col-xs-8" align="center">
									<table id="viewFirLvlMnTblId" style="align: center; border: 2px">
										<tbody></tbody>
									</table>
								</div>
								<div style="vertical-align: middle; margin-top: 70px;">
									<button id="returnViewFirstPage" name="returnViewFirstPage" type="button" class="btn btn-info btnTax-clk" style="height: 6ex; width: 6ex;" onclick="returnToViewMainMenuPage($('#viewFirLvlMenuCode').val());">...</button>
								</div>
							</div>
							<g:form name="viewFirLevMenForm" id="viewFirLevMenForm">
								<g:hiddenField name="viewModId" id="viewModId" value="${modId}"/>
								<g:hiddenField name="viewRoleId" id="viewRoleId" value="${roleId}"/>
								<g:hiddenField name="viewBrId" id="viewBrId" value="${viewBrId}"/>
								<g:hiddenField name="viewRoleName" id="viewRoleName" value="${viewRoleName}"/>
								<g:hiddenField name="viewFirLvlMenuCode" id="viewFirLvlMenuCode" value="${viewFirLvlMenuCode}"/>	
								<g:hiddenField name="viewSecLvlMenuCode" id="viewSecLvlMenuCode"/>
								<g:hiddenField name="typeValue" id="typeValue" value="${typeValue}"/>			
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>