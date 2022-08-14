<%-- 
-- File Name: index
-- Description: This page displays index Page of Code Master
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>City Mapping</title>
	<script type="text/javascript">
		var cty='';
		var st='';
		var ctr='';
		var city='';
		var state='';
		var country='';
		$(document).ready(function(){
			applyDataTable('mkUsersPopup');
			applyDataTable('cityTable');
			applyDataTable('stateTable');
			applyDataTable('countryTable');
			$("#createImgIdHid").hide();
			$("#createImgId").show(); 
			$("#deleteImg").hide();
			$("#deleteImgHid").show();
			
			$(".editClickCty td:not(.action)").click(function () {	
				cty =$(this).closest('tr').attr('id');
				$(this).closest('tr').addClass('highlight');
			}); 			
			$(".editClickSt td:not(.action)").click(function () {						
				st =$(this).closest('tr').attr('id');
				$(this).closest('tr').addClass('highlight');
			});   
			$(".editClickCtr td:not(.action)").click(function () {						
				ctr =$(this).closest('tr').attr('id');
				$(this).closest('tr').addClass('highlight');
			}); 
		  	$("#cityId").prop('disabled', true);
		    $("#stateId").prop('disabled', true);
		    $("#countryId").prop('disabled', true);
		    
		    $("#cityId1").prop('disabled', true);
		    $("#stateId1").prop('disabled', true);
		    $("#countryId1").prop('disabled', true);
		    
		    $("#cityId2").prop('disabled', true);
			$("#stateId2").prop('disabled', true);
			$("#countryId2").prop('disabled', true);
			
			$("#cityId3").prop('disabled', true);
			$("#stateId3").prop('disabled', true);
			$("#countryId3").prop('disabled', true);
			
			$("#cityId4").prop('disabled', true);
			$("#stateId4").prop('disabled', true);
			$("#countryId4").prop('disabled', true);
			
			$("#cityId5").prop('disabled', true);
			$("#stateId5").prop('disabled', true);
			$("#countryId5").prop('disabled', true);
			
			$("#cityId6").prop('disabled', true);
			$("#stateId6").prop('disabled', true);
			$("#countryId6").prop('disabled', true);			         			
		   			     
		   $(".editClick td:not(.action)").click(function () {	
		  		city =$(this).closest('tr').attr('cityname');
		  		state =$(this).closest('tr').attr('statename'); 
		  		country =$(this).closest('tr').attr('countryname');
				$(this).closest('tr').addClass('highlight');
		    });
		});	
		function onMasterViewClk(){
			$("#deleteImg").hide();
			$("#deleteImgHid").show();
			$("#cityName1").val(city);
			$("#stateName1").val(state);
			$("#countryName1").val(country);
			$("#createImgIdHid").show()
			$("#createImgId").hide()
			$("#addId1").prop('disabled', true);
			$(".editClick").removeClass('highlight');
		}
			function onMasterEditClk(){	
				$("#createImgIdHid").hide();
				$("#createImgId").show();				
				$("#deleteImg").show();
				$("#deleteImgHid").hide();
				$("#cityName2").hide();
				$("#cityName3").hide();
				$("#cityName4").hide();
				$("#cityName5").hide();
				$("#cityName6").hide();
				$("#stateName2").hide();
				$("#stateName3").hide();
				$("#stateName4").hide();
				$("#stateName5").hide();
				$("#stateName6").hide();
				$("#countryName2").hide();
				$("#countryName3").hide();
				$("#countryName4").hide();
				$("#countryName5").hide();
				$("#countryName6").hide();
				$("#cityId2").hide();
				$("#cityId3").hide();
				$("#cityId4").hide();
				$("#cityId5").hide();
				$("#cityId6").hide();
				$("#stateId2").hide();
				$("#stateId3").hide();
				$("#stateId4").hide();
				$("#stateId5").hide();
				$("#stateId6").hide();
				$("#countryId2").hide();
				$("#countryId3").hide();
				$("#countryId4").hide();
				$("#countryId5").hide();
				$("#countryId6").hide();
				$("#cityName1").val(city);
				$("#stateName1").val(state);
				$("#countryName1").val(country);
				$("#flag").val("1");
				$("#addId1").prop('disabled', true);			
				$(".editClick").removeClass('highlight');
				$("#cityId1").prop('disabled', false);
			    $("#stateId1").prop('disabled', false);
			    $("#countryId1").prop('disabled', false);
			    $("#addId1").prop('disabled', false);
			    $("#cityId1").prop('disabled', false);
			    $("#stateId1").prop('disabled', false);
			    $("#countryId1").prop('disabled', false);
			    $("#cityId2").prop('disabled', false);
				$("#stateId2").prop('disabled', false);
				$("#countryId2").prop('disabled', false);
				$("#cityId3").prop('disabled', false);
				$("#stateId3").prop('disabled', false);
				$("#countryId3").prop('disabled', false);
				$("#cityId4").prop('disabled', false);
				$("#stateId4").prop('disabled', false);
				$("#countryId4").prop('disabled', false);
				$("#cityId5").prop('disabled', false);
				$("#stateId5").prop('disabled', false);
				$("#countryId5").prop('disabled', false);
				$("#cityId6").prop('disabled', false);
				$("#stateId6").prop('disabled', false);
				$("#countryId6").prop('disabled', false);		
			}			
			function getCity(){
				var city = cty;
				var flag = $("#flag").val();
				if(flag==""){
					var cName1 = $("#cityName1").val();
					var cName2 = $("#cityName2").val();
					var cName3 = $("#cityName3").val();
					var cName4 = $("#cityName4").val();
					var cName5 = $("#cityName5").val();
					var cName6 = $("#cityName6").val();
					var btn = $("#cityId1").val();
					if(cName1==''){
						$("#cityName1").val(city);
						$("#cityName2").disable();
					}else if(cName2==''){
						$("#cityName2").val(city);
					}else if(cName3==''){
						$("#cityName3").val(city);
					}else if(cName4==''){
						$("#cityName4").val(city);
					}else if(cName5==''){
						$("#cityName5").val(city);
					}else{
						$("#cityName6").val(city);
					}						
					$(".editClickCty").removeClass('highlight');
				}else if(flag==1){
					var cName1 = $("#cityName1").val();					
					var btn = $("#cityId1").val();
					$("#cityName1").val(city);					
					$(".editClickCty").removeClass('highlight');
				}
			}				
			function getState(){
				var state = st;				
				var flag = $("#flag").val();
				if(flag==""){				
					var sName1 = $("#stateName1").val();
					var sName2 = $("#stateName2").val();
					var sName3 = $("#stateName3").val();
					var sName4 = $("#stateName4").val();
					var sName5 = $("#stateName5").val();
					var sName6 = $("#stateName6").val();
					if(sName1==''){
						$("#stateName1").val(state);
					}else if(sName2==''){
						$("#stateName2").val(state);
					}else if(sName3==''){
						$("#stateName3").val(state);
					}else if(sName4==''){
						$("#stateName4").val(state);
					}else if(sName5==''){
						$("#stateName5").val(state);
					}else{
						$("#stateName6").val(state);
					}
					$(".editClickSt").removeClass('highlight');
				}else if(flag==1){
					var sName1 = $("#stateName1").val();
					$("#stateName1").val(state);
					$(".editClickSt").removeClass('highlight');
				}
			}
			function getCountry(){
				var country = ctr;
				var flag = $("#flag").val();
				if(flag==""){				
					var counName1 = $("#countryName1").val();
					var counName2 = $("#countryName2").val();
					var counName3 = $("#countryName3").val();
					var counName4 = $("#countryName4").val();
					var counName5 = $("#countryName5").val();
					var counName6 = $("#countryName6").val();
					if(counName1==''){
						$("#countryName1").val(country);
					}else if(counName2==''){
						$("#countryName2").val(country);
					}else if(counName3==''){
						$("#countryName3").val(country);
					}else if(counName4==''){
						$("#countryName4").val(country);
					}else if(counName5==''){
						$("#countryName5").val(country);
					}else{
						$("#countryName6").val(country);
					}
					$(".editClickCtr").removeClass('highlight');
				}else if(flag==1){
					var counName1 = $("#countryName1").val();
					$("#countryName1").val(country);
					$(".editClickCtr").removeClass('highlight');
				}
			}
			function addFn(){
				alert("add");
			}
		    $(document).ready(function(){	       
		   		$("#cityList").change(function(){		
				var cityList = $("#cityList").val();
				${remoteFunction(
	                controller:'MstRegion',
	                action:'saveCityName',
	                update: 'updateMe',
	                params:'\'cityName=\' + this.value')}
	    	});
	    	$("#stateList").change(function(){		
				var cityList = $("#stateList").val();   
				${remoteFunction(
	                controller:'MstRegion',
	                action:'saveStateName',
	                update: 'updateMe',
	                params:'\'stateName=\' + this.value')}
	    	});
	    	$("#countryList").change(function(){		
				var cityList = $("#countryList").val();   
	
				${remoteFunction(
	                controller:'MstRegion',
	                action:'saveCountryName',
	                update: 'updateMe',
	                params:'\'CountryName=\' + this.value')}
	    	});
	    	$("#saveImg").click(function(){	    
	    		var flag = '';
	    		flag = $("#flag").val();
	    		var brCode = $("#brCodeHid").val();
	    		var cityA = document.getElementsByName('cityName');
	    		var stateA = document.getElementsByName('stateName');
	    		var countryA = document.getElementsByName('countryName');
	    		if(flag==""){
	    			flag="0";
	    		}
	    		for(var i=0;i<cityA.length;i++){
	    			var city = cityA[i].value;
	    			var state = stateA[i].value;
	    			var country = countryA[i].value;
	    			console.log("City: "+city+ " State: "+state+" Country: "+country);
	    			if(city!="" && state!="" && country!=""){		    			
		    			var parameter = {city:city,state:state,country:country,brCode:brCode,flag:flag}
			    			$.ajax({
							url: "${request.getContextPath()}/MstRegion/saveMaster",
							async : false,
							data : parameter,
							success : function(data){	
								window.location.reload();							
							}
						});
					}
	    		}	    		
	    	});
	    	$("#deleteImg").click(function(){
	    		fnDelete();
	    	});
	    });
	    function fnDelete() {
    		var brCode = $("#brCodeHid").val();
    		var cityA = document.getElementsByName('cityName');
    		var stateA = document.getElementsByName('stateName');
    		var countryA = document.getElementsByName('countryName');
    		
    		for(var i=0;i<cityA.length;i++){
    			var city = cityA[i].value;
    			var state = stateA[i].value;
    			var country = countryA[i].value;
    			console.log("City: "+city+ " State: "+state+" Country: "+country);
    			if(city!="" && state!="" && country!=""){		    			
	    			var parameter = {city:city,state:state,country:country,brCode:brCode}
		    			$.ajax({
						url: "${request.getContextPath()}/MstRegion/deleteMaster",
						async : false,
						data : parameter,
						success : function(data){
							window.location.reload();							
						}
					});
				}
    		}
    	}	
	    function createOnClick(){
	    	if($("#countryName1").val() !=null && $("#countryName1").val() !=="" && $("#countryName1").val() !== undefined){
	    		$("#cityId1").prop('disabled', true);
			    $("#stateId1").prop('disabled', true);
			   	$("#countryId1").prop('disabled', true);
	    	}else{
	    		$("#cityId1").prop('disabled', false);
			    	$("#stateId1").prop('disabled', false);
			       	$("#countryId1").prop('disabled', false);
			   	    $("#addId1").click(function() {
				    	$("#cityId2").prop('disabled', false);
				       	$("#stateId2").prop('disabled', false);
					   	$("#countryId2").prop('disabled', false);
						$("#addId1").click(function() {
					    	$("#cityId3").prop('disabled', false);
					       	$("#stateId3").prop('disabled', false);
						   	$("#countryId3").prop('disabled', false);
						   	$("#addId1").click(function() {
						    	$("#cityId4").prop('disabled', false);
						       	$("#stateId4").prop('disabled', false);
							   	$("#countryId4").prop('disabled', false);
							   	$("#addId1").click(function() {
							       	$("#cityId5").prop('disabled', false);
							       	$("#stateId5").prop('disabled', false);
								   	$("#countryId5").prop('disabled', false);
								   	$("#addId1").click(function() {
								    	$("#cityId6").prop('disabled', false);
								       	$("#stateId6").prop('disabled', false);
									   	$("#countryId6").prop('disabled', false);
									});
								});
							});
						});
					});
	    		}
	    	}
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="#" onclick="createOnClick();"><asset:image src="add.png" /></a>
							<a href="javascript:void(0)" id="locatorId" data-toggle="modal" data-target="#viewCityMasterModal" data-whatever="@fat"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)" id="locatorEditId" data-toggle="modal" data-target="#editCityMasterModal" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>City Mapping</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form url="[resource:dtRateDetailInstance, action:'save']">
								<div id="list-mstRegion" class="content scaffold-list" role="main">
									<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
									<g:hiddenField name="brCodeHid" id="brCodeHid" value="${brCode}"/>
									<g:hiddenField name="flag" id="flag" value=""/>
									<div class="content-bg pull-left">
										<div class="row">
											<div class="col-md-12">
											<table class="table" id="tableId">
												<thead>
													<tr>
														<g:sortableColumn property="vcCity" title="${message(code: 'mstRegion.vcCity.label', default: 'City')}" />
														<g:sortableColumn property="vcState" title="${message(code: 'mstRegion.vcState.label', default: 'State')}" />
														<g:sortableColumn property="vcCountry" title="${message(code: 'mstRegion.vcCountry.label', default: 'Country')}" />
													</tr>
												</thead>
												<tbody id="tbodyId">
													<tr>
														<td>
															<div class="textbtn">
																<g:textField name="cityName" class="form-control" readonly="true"></g:textField>
																<button id="cityId1" name="city1" type="button"	class="active_btn" data-toggle="modal" data-target="#cityModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
															<g:textField name="stateName" class="form-control" readonly="true"></g:textField>
																<button id="stateId1" name="state" type="button" class="active_btn" data-toggle="modal" data-target="#stateModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
															<g:textField name="countryName" class="form-control" readonly="true"></g:textField>
																<button id="countryId1" name="country" type="button" class="active_btn" data-toggle="modal" data-target="#countryModal">...</button>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="textbtn">
																<g:textField name="cityName" class="form-control" readonly="true"></g:textField>
																<button id="cityId2" name="city" type="button" class="active_btn" data-toggle="modal"	data-target="#cityModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
																<g:textField name="stateName" class="form-control" readonly="true"></g:textField>
																<button id="stateId2" name="state" type="button" class="active_btn" data-toggle="modal" data-target="#stateModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
																<g:textField name="countryName" class="form-control" readonly="true"></g:textField>
																<button id="countryId2" name="country" type="button" class="active_btn" data-toggle="modal" data-target="#countryModal">...</button>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="textbtn">
																<g:textField name="cityName" class="form-control" readonly="true"></g:textField>
																<button id="cityId3" name="city" type="button" class="active_btn" data-toggle="modal" data-target="#cityModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
																<g:textField name="stateName" class="form-control" readonly="true"></g:textField>
																<button id="stateId3" name="state" type="button" class="active_btn" data-toggle="modal" data-target="#stateModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
																<g:textField name="countryName" class="form-control" readonly="true"></g:textField>
																<button id="countryId3" name="country" type="button" class="active_btn" data-toggle="modal" data-target="#countryModal">...</button>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="textbtn">
																<g:textField name="cityName" class="form-control" readonly="true"></g:textField>
																<button id="cityId4" name="city" type="button" class="active_btn" data-toggle="modal" data-target="#cityModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
																<g:textField name="stateName" class="form-control" readonly="true"></g:textField>
																<button id="stateId4" name="state" type="button" class="active_btn" data-toggle="modal" data-target="#stateModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
																<g:textField name="countryName" class="form-control" readonly="true"></g:textField>
																<button id="countryId4" name="country" type="button" class="active_btn" data-toggle="modal" data-target="#countryModal">...</button>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="textbtn">
																<g:textField name="cityName" class="form-control" readonly="true"></g:textField>
																<button id="cityId5" name="city" type="button" class="active_btn" data-toggle="modal" data-target="#cityModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
																<g:textField name="stateName" class="form-control" readonly="true"></g:textField>
																<button id="stateId5" name="state" type="button" class="active_btn" data-toggle="modal" data-target="#stateModal">...</button>
															</div>
														</td>
														<td>
															<div class="textbtn">
																<g:textField name="countryName" class="form-control" readonly="true"></g:textField>
																<button id="countryId5" name="country" type="button" class="active_btn" data-toggle="modal"data-target="#countryModal">...</button>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="textbtn">
																<g:textField name="cityName" class="form-control" readonly="true"></g:textField>
																			<button id="cityId6" name="city" type="button" class="active_btn" data-toggle="modal" data-target="#cityModal">...</button>
																		</div>
																	</td>
																	<td>
																		<div class="textbtn">
																			<g:textField name="stateName" class="form-control" readonly="true"></g:textField>
																			<button id="stateId6" name="state" type="button" class="active_btn" data-toggle="modal"data-target="#stateModal">...</button>
																		</div>
																	</td>
																	<td>
																		<div class="textbtn">
																			<g:textField name="countryName" class="form-control" readonly="true"></g:textField>
																			<button id="countryId6" name="country" type="button" class="active_btn" data-toggle="modal"data-target="#countryModal">...</button>
																		</div>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
										<div class="row">
											<div class="col-md-12">	
												<div class="pull-right rightsapce">
													<input type="button" name="add" id="addId1" value="Add" class="active_btn" />
													<g:submitButton name="delete" class="active_btn" onclick="fnDelete();" value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
												</div>
											</div>
										</div>
									</div>
								</div>	
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- Popup for City Start -->	
	<div class="modal fade" id="cityModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">City</h4>
				</div>
				<div class="modal-body">
					<table width="100%" class="table" id="cityTable">
						<thead>
							<tr>
								<th>City Name</th>
							</tr>
						</thead>
						<tbody id="cityLstId">
							<g:each in="${ctyLst}" status="i" var="cityInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClickCty" id="${cityInst}" name="${cityInst}">
									<td name="cty" id="cty">${cityInst}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="getCity();" data-dismiss="modal">Add</button>
				</div>
			</div>
		</div>
	</div>
<!-- Popup for City End -->	
<!-- Popup for State Start -->	
	<div class="modal fade" id="stateModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">State</h4>
				</div>
				<div class="modal-body">
					<table width="100%" class="table" id="stateTable">
						<thead>
							<tr>
								<th>State Name</th>
							</tr>
						</thead>
						<tbody id="stateLstId">
							<g:each in="${stateLst}" status="i" var="stateInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClickSt" id="${stateInst}" name="${stateInst}">
									<td name="state" id="state">${stateInst}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="getState();" data-dismiss="modal">Add</button>
				</div>
			</div>
		</div>
	</div>
<!-- Popup for State End -->		
<!-- Popup for Country Start -->	
	<div class="modal fade" id="countryModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Country</h4>
				</div>
				<div class="modal-body">
					<table width="100%" class="table" id="countryTable">
						<thead>
							<tr>
								<th>Country Name</th>
							</tr>
						</thead>
						<tbody id="countryLstId">
							<g:each in="${countryLst}" status="i" var="countryInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClickCtr" id="${countryInst}" name="${countryInst}">
									<td name="country" id="country">${countryInst}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="getCountry();" data-dismiss="modal">Add</button>
				</div>
			</div>
		</div>
	</div>
<!-- Popup for Country End -->
<%-- POPUP FOR VIEW Start--%>
<div class="modal fade" id="viewCityMasterModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Master List</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="mkUsersPopup" class="table">
						<thead>
							<tr>
								<th>City</th>
								<th>State</th>
								<th>Country</th>
							</tr>
						</thead>
						<tbody id="cityMasterId">
							<g:each in="${mstRegionLst}" status="i" var="mstUsersInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick">
									<td>${mstUsersInst?.CITY}</td>
									<td>${mstUsersInst?.STATE}</td>
									<td>${mstUsersInst?.COUNTRY}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="onMasterViewClk();" data-dismiss="modal">Ok</button>
					<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
<%-- POPUP END FOR VIEW --%>
<%-- POPUP FOR VIEW Start--%>
<div class="modal fade" id="editCityMasterModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Master List</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="mkUsersPopup" class="table">
						<thead>
							<tr>
								<th>City</th>
								<th>State</th>
								<th>Country</th>
							</tr>
						</thead>
						<tbody id="cityMasterId">
							<g:each in="${mstRegionLst}" status="i" var="mstUsersInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick">
									<td>${mstUsersInst?.CITY}</td>
									<td>${mstUsersInst?.STATE}</td>
									<td>${mstUsersInst?.COUNTRY}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="onMasterEditClk();" data-dismiss="modal">Ok</button>
					<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
<%-- POPUP END FOR VIEW --%>
</body>
</html>