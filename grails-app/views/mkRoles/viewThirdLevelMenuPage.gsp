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
<%@ page import="com.tbz.franchisee.MkRoles"%>
<%@ page import="com.tbz.franchisee.MkModule"%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Role Management</title>
	<script type="text/javascript">
		var funArray = new Array();
		var typeValue = "";
		$(document).ready(function(){
			typeValue = $("#typeValue").val();
			var modId = $("#viewModId").val();
			var roleId = $("#viewRoleId").val();	
			var levelId = '3';
			var menuCode = $("#viewThiLvlMenuCode").val();	
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
		        var levelId = '3';
		        var menuCode = $("#viewThiLvlMenuCode").val();
		        var modId = $("#viewModId").val();
		        $("#spinner").show();
		        var parameter = {modId:modId,levelId:levelId,menuCode:menuCode}
				$.ajax({
				 	url: "${request.getContextPath()}/MkRoles/getAllDataBasedOnLevel",
				 	data : parameter,
					async : false,
					success : function(json){
						$("#spinner").hide();
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
						              	  	html+='<input type="checkbox" id="'+json.VC_MENU_CODE[j]+'" checked="checked" name="checkboxthi" value="'+json.VC_MENU_CODE[j]+'" onclick="insOrDelOnCheckOrUnCheckThird('+json.VC_MENU_CODE[j]+');">'+
						              	  	'<td width="10%"><button id="threeLvlBtn_'+json.VC_MENU_CODE[j]+'" name="threeLvlBtn_'+json.VC_MENU_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 3ex; width: 3ex;" onclick="getFourthLvlMenu('+json.VC_MENU_CODE[j]+');">...</button>'
					              	  	}else{
					              	  		html+='<input type="checkbox" id="'+json.VC_MENU_CODE[j]+'" disabled="disabled" value="'+json.VC_MENU_CODE[j]+'">'+
						              	  	'<td width="10%"><button id="threeLvlBtn_'+json.VC_MENU_CODE[j]+'" name="threeLvlBtn_'+json.VC_MENU_CODE[j]+'" type="button" class="btn btn-info btnTax-clk" style="height: 3ex; width: 3ex;" onclick="getFourthLvlMenu('+json.VC_MENU_CODE[j]+');">...</button>'
					              	  	}
					              	  }else{
					              	  	if(typeValue=='edit'){
						              	  	html+='<input type="checkbox" id="'+json.VC_MENU_CODE[j]+'" name="checkboxthi" value="'+json.VC_MENU_CODE[j]+'" onclick="insOrDelOnCheckOrUnCheckThird('+json.VC_MENU_CODE[j]+');">'+
						              	  	'<td width="10%"><button id="threeLvlBtn_'+json.VC_MENU_CODE[j]+'" name="threeLvlBtn_'+json.VC_MENU_CODE[j]+'" type="button" disabled="disabled" class="btn btn-info btnTax-clk" style="height: 3ex; width: 3ex;" onclick="getFourthLvlMenu('+json.VC_MENU_CODE[j]+');">...</button>'
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
				         $("#viewThiLvlMnTblId tbody").empty();
				         $("#viewThiLvlMnTblId tbody").append(html); 
				    }
				 }
				});
		      }
		function returnToViewSecondLevelMenuPage(menuId,viewSecLvlMenuCode){	
			$("#viewSecLvlMenuCode").val(viewSecLvlMenuCode);
			$("#viewFirLvlMenuCode").val(menuId);
			$("#spinner").show();
			document.getElementById("viewThiLevMenForm").action ="viewSecondLevelMenuPage";
			document.getElementById("viewThiLevMenForm").submit();
		}
		function getFourthLvlMenu(menuCode){
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
			if(menuId.toString().length==5){
				menuId = "0"+menuId
				console.log("in menuId :::"+menuId);
			}	
		 	var levelId=3; 	 
		 	$("#spinner").show();
		 	var parameter = {modId:modId,roleId:roleId,levelId:levelId,menuId:menuId}
		 	$.ajax({
				 url : "${request.getContextPath()}/MkRoles/isNextMenuAailOrNotForViewOrEdit",
				 async : false,
				 data : parameter,
				 success : function(data){	
				 $("#spinner").hide();	
				 console.log("cnt :"+data.countVal);
					if(data.countVal!='0'){
						$("#spinner").show();
						document.getElementById("viewThiLevMenForm").action ="viewFourthLevelMenuPage";
						document.getElementById("viewThiLevMenForm").submit();
					}else{
						$("#Error").html('No More Records Are Present').show();
						hideSuccessErrorMessage("alert");
						return false;
					}
				}
			});		
		}
		function insOrDelOnCheckOrUnCheckThird(butMenuIdThi){
			console.log("butMenuIdThi :::"+butMenuIdThi);	
			if(butMenuIdThi.toString().length==5){
				butMenuIdThi = "0"+butMenuIdThi
				console.log("in butMenuIdThi :::"+butMenuIdThi);
			}
			var modId = $("#viewModId").val();
			console.log("modId :::"+modId);	
			console.log("val :"+'#'+butMenuIdThi+'');
			
			var firstLvlId = $("#viewSecLvlMenuCode").val();
		      if(firstLvlId.toString().length==1){
				firstLvlId = "0"+firstLvlId
				console.log("in firstLvlId :::"+firstLvlId);
			  }
		      
		      var secondLvlId = $("#viewThiLvlMenuCode").val();
		      if(secondLvlId.toString().length==3){
				secondLvlId = "0"+secondLvlId
				console.log("in secondLvlId :::"+secondLvlId);
			  }
			
			if($('#'+butMenuIdThi+'').is(":checked")){
				console.log("in checked :");
				$("#spinner").show();
				var parameter = {modId:modId,firstLvlId:firstLvlId,secondLvlId:secondLvlId,butMenuIdThi:butMenuIdThi,insert:'0'}
				$.ajax({
						url : "${request.getContextPath()}/MkRoles/insertIntoTempTableForThirdLvlMenu",
						async : false,
						data : parameter,
						success : function(data){
							$("#spinner").hide();		
					}
				});
				$("#threeLvlBtn_"+butMenuIdThi).attr("disabled" , false);		
			}else{			
				console.log("in un checked :");
				$("#spinner").show();
				var parameter = {modId:modId,firstLvlId:firstLvlId,secondLvlId:secondLvlId,butMenuIdThi:butMenuIdThi,delete:'0'}
				$.ajax({
						url : "${request.getContextPath()}/MkRoles/deleteThirdLvlIdsFromTemp",
						async : false,
						data : parameter,
						success : function(data){		
							$("#spinner").hide();
					}
				});
				$("#threeLvlBtn_"+butMenuIdThi).attr("disabled", 'disabled');
			}
		}
		function selectAll(){
		      var allThirdLvlMenuId="";
		      var modId=$("#viewModId").val();      
		      var firstLvlId = $("#viewSecLvlMenuCode").val();
		      if(firstLvlId.toString().length==1){
				firstLvlId = "0"+firstLvlId
				console.log("in firstLvlId :::"+firstLvlId);
			  }      
		      var secondLvlId = $("#viewThiLvlMenuCode").val();
		      if(secondLvlId.toString().length==3){
				secondLvlId = "0"+secondLvlId
				console.log("in secondLvlId :::"+secondLvlId);
			  }      
			  var checkboxes = document.getElementsByName('checkboxthi');
			  console.log("len :"+checkboxes.length);
			  for (var i = 0; i < checkboxes.length; i++) {
		             if (checkboxes[i].type == 'checkbox') {
			             $("#threeLvlBtn_"+checkboxes[i].value).attr("disabled" , false);
			             allThirdLvlMenuId+=checkboxes[i].value+"~";
		                 checkboxes[i].checked = true;
		             }
		             console.log("in select all allThirdLvlMenuId :"+allThirdLvlMenuId);
			      }   			 
			 $("#spinner").show();     
			 var parameter = {modId:modId,firstLvlId:firstLvlId,secondLvlId:secondLvlId,butMenuIdThi:allThirdLvlMenuId,insert:'1'}
			 $.ajax({
					url : "${request.getContextPath()}/MkRoles/insertIntoTempTableForThirdLvlMenu",
					async : false,
					data : parameter,
					success : function(data){
						$("#spinner").hide();		
					}
				});
		  }
		  function deSelectAll(){
		      var allThirdLvlMenuId="";
		      var modId=$("#viewModId").val();      
		      var firstLvlId = $("#viewSecLvlMenuCode").val();
		      if(firstLvlId.toString().length==1){
				firstLvlId = "0"+firstLvlId
				console.log("in firstLvlId :::"+firstLvlId);
			  }      
		      var secondLvlId = $("#viewThiLvlMenuCode").val();
		      if(secondLvlId.toString().length==3){
				secondLvlId = "0"+secondLvlId
				console.log("in secondLvlId :::"+secondLvlId);
			  }
			 var checkboxes = document.getElementsByName('checkboxthi');
		     for (var i = 0; i < checkboxes.length; i++) {
		         if (checkboxes[i].type == 'checkbox') {
		         $("#threeLvlBtn_"+checkboxes[i].value).attr("disabled" , 'disabled');
		             checkboxes[i].checked = false;
		              allThirdLvlMenuId+=checkboxes[i].value+"~";
		         }
		         console.log("in select all allThirdLvlMenuId :"+allThirdLvlMenuId);
		      }
		      $("#spinner").show();
		      var parameter = {modId:modId,firstLvlId:firstLvlId,secondLvlId:secondLvlId,butMenuIdThi:allThirdLvlMenuId,delete:'1'}  			 
			 $.ajax({
					url : "${request.getContextPath()}/MkRoles/deleteThirdLvlIdsFromTemp",
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
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="row cust-tab-form" style="align: center; border: 2px; overflow: auto;">
								<div class="col-md-8 col-sm-8 col-xs-8" align="center">
									<table id="viewThiLvlMnTblId" style="align: center; border: 2px">
										<tbody></tbody>
									</table>
								</div>
								<div style="vertical-align: middle; margin-top: 70px;">
									<button id="returnViewFirstPage" name="returnViewFirstPage" type="button" class="btn btn-info btnTax-clk" style="height: 6ex; width: 6ex;" onclick="returnToViewSecondLevelMenuPage($('#viewFirLvlMenuCode').val(),$('#viewSecLvlMenuCode').val());">...</button>
								</div>
							</div>
							<g:form name="viewThiLevMenForm" id="viewThiLevMenForm">
								<g:hiddenField name="viewModId" id="viewModId" value="${modId}"/>
								<g:hiddenField name="viewRoleId" id="viewRoleId" value="${roleId}"/>
								<g:hiddenField name="viewBrId" id="viewBrId" value="${viewBrId}"/>
								<g:hiddenField name="viewRoleName" id="viewRoleName" value="${viewRoleName}"/>
								<g:hiddenField name="viewFirLvlMenuCode" id="viewFirLvlMenuCode" value="${viewFirLvlMenuCode}"/>
								<g:hiddenField name="viewSecLvlMenuCode" id="viewSecLvlMenuCode" value="${viewSecLvlMenuCode}"/>
								<g:hiddenField name="viewThiLvlMenuCode" id="viewThiLvlMenuCode" value="${viewThiLvlMenuCode}"/>
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