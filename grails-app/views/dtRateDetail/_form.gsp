<%@ page import="com.tbz.franchisee.BrMstTab"%>
<script type="text/javascript">
	var brSelChkVal =  new Array();
	var rgSelChkVal =  new Array();
	var chBrData = "";
	var chRgData = "";
	var brSel = "";
	var rateErrorType = "";
	var clickedOnBranch="";
	$(document).ready(function(){	
		$( "#rateType" ).change(function() {		  	
		  	if ($(this).val() == "BRANCH") {
			  	var html = ''; 
			  	$("#butId").attr("data-target","#branchModal");		
			  	$("#spinner").show();  	
			  	$.getJSON("${request.getContextPath()}/DtRateDetail/getBranchData/", function(data) { 	
			  	$("#spinner").hide();	
		 			for(var i=0;i<data.BR_CODE.length;i++){
		 				html +='<tr>'+
		 				'<td><input type="checkbox" id="brName" name="brName" value="'+data.BR_CODE[i]+'"></td>'+
		 				'<td>'+data.BR_NAME[i]+'</td>'+
		 				'</tr>';
		 			}
		 			$("#branchTable tbody").empty().append(html);
		 			applyDataTable('branchTable');
		 		});		
		  	} else if ($(this).val() == "REGION") {
			  	var html = ''; 
			  	$("#butId").attr("data-target","#regionModal");
			  	$("#spinner").show();
			  	$.getJSON("${request.getContextPath()}/DtRateDetail/getRegionData/", function(data) { 	
			  		$("#spinner").hide();
		 			for(var i=0;i<data.REGION_CODE.length;i++){
		 				html +='<tr>'+
		 				'<td><input type="checkbox" id="regName" name="regName" value="'+data.REGION_CODE[i]+'"></td>'+
		 				'<td>'+data.REGION_NAME[i]+'</td>'+
		 				'</tr>';
		 			}
		 			$("#regionTable tbody").empty().append(html); 			
			 		applyDataTable('regionTable');
	 			});	
		  }	else if ($(this).val() == "REGION BRANCH WISE") {
		  	var html = ''; 
		  	$("#butId").attr("data-target","#regionBranchModal");	
		  	$("#spinner").show();
		  	$.ajax({
			url: "${request.getContextPath()}/DtRateDetail/getRegionData/",
			"dataSrc": "Data",
	    	"type": "POST",
			async:false,
				success : function(data){
					$("#spinner").hide();
					for(var i=0;i<data.REGION_CODE.length;i++){
	 				html +='<tr>'+
	 				'<td><input type="checkbox" id="regName" name="regName" value="'+data.REGION_CODE[i]+'"></td>'+
	 				'<td>'+data.REGION_NAME[i]+'</td>'+
	 				'<td><button type="button" class="active_btn" onclick="regionTypeBranch('+data.REGION_CODE[i]+');">...</button></td>'+
	 				'</tr>';
	 			}
	 			$("#rgBrTblId tbody").empty().append(html);
	 			applyDataTable('rgBrTblId');
				}
		  	});			 		
		  }	      
		});
			
		brSel = 'A';
		$("#branchModal").hide();
		$("#regionModal").hide();	
		$("#regionBranchModal").hide();
		
		$("#rateType").attr('disabled', true);
		$("#butId").attr('disabled', true);
		
		var today = new Date();
			      
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();
		
		var hh = today.getHours();
		var minutes = today.getMinutes(); //January is 0!
		var seconds = today.getSeconds();
		
		if(dd<10) {
		    dd='0'+dd
		} 	
		if(mm<10) {
		    mm='0'+mm
		} 	
		today = dd+'-'+mm+'-'+yyyy+' '+hh+':'+minutes+':'+seconds;
		$("#rateDate").val(today);
	});       
		 
	function enableFields(){
		brSel = 'S';
		$("#rateType").attr('disabled', false);
		$("#butId").attr('disabled', false);
	}
	function disableFields(){
		brSel = 'A';
		$("#rateType").attr('disabled', true);
		$("#butId").attr('disabled', true);
	}
	function rateTypePopup(){
		var html = '';
		var type = $("#rateType").val();
	 	if (type == "REGION"){
	     	$("#spinner").show();
	     	$.getJSON("${request.getContextPath()}/DtRateDetail/getRegionData/", function(data) { 
	     		$("#spinner").hide();	
	 			for(var i=0;i<data.REGION_CODE.length;i++){
	 				html +='<tr>'+
	 				'<td><input type="checkbox" id="regName" name="regName" value="'+data.REGION_CODE[i]+'"></td>'+
	 				'<td>'+data.REGION_NAME[i]+'</td>'+
	 				'</tr>';
	 			}
	 			$("#regionTable tbody").empty().append(html); 			
		 		applyDataTable('regionTable');
	 		});	
	     }          
	}
	function mainBrCheck(source) {
	  checkboxes = document.getElementsByName('brName');
	  for(var i=0, n=checkboxes.length;i<n;i++) {
	    checkboxes[i].checked = source.checked;
	  }
	}
	function mainRgBrCheck(source) {
	  rgcheckboxes = document.getElementsByName('regName');
	  for(var j=0, m=rgcheckboxes.length;j<m;j++) {
	    rgcheckboxes[j].checked = source.checked;
	    
	  }
	}
	function getBranchData(){
		rateErrorType = "Branch";
		var brSelNextId = $("#brSelNextId").val();
		chb = document.getElementsByName('brName');
		brSelChkVal = [];
		chBrData = "";
		  for(var i=0;i<chb.length;i++) {
		    if(chb[i].checked){
		      chBrData =chb[i].value+","+chBrData;
		    }	    
	 	}
	 	console.log("chBrData len "+chBrData);
	 	if(chBrData.length==''){
	    	console.log("else");
	    	$("#brError").html("Please Select AtLeast One Branch").show();
	        hideSuccessErrorMessage("alert");
	    	return false;
		}else{	
		 	console.log("chBrData "+chBrData);	 	
		 	$("#branchModal").modal("hide");
		 	$("#spinner").show();
		 	$.getJSON("${request.getContextPath()}/DtRateDetail/saveBrSelection/?chBrData="+chBrData+"&brSelNextId="+brSelNextId, function(data) { 	
		 		$("#spinner").hide();	
	 			console.log(data.resultData);
	 			$("#regionBranchModal").modal("show");
	 		});		 	
		 	$("#mainChBrId").attr("checked", false);
		 }	 	
		 clickedOnBranch="yes";
		 $("#branchModal .close").click();
		 $("#rateType").attr('disabled', true);
		 $("#butId").attr('disabled', true);
		 
	}
	function getRegionData(type){
		if(clickedOnBranch=='yes'){
			rateErrorType = "RegionBranch";
			$("#mainChBrId").attr("checked", false);
		}else{
			rateErrorType = "Region";
			var brSelNextId = $("#brSelNextId").val();
			chr = document.getElementsByName('regName');
			rgSelChkVal = [];
			chRgData = "";
			  for(var i=0;i<chr.length;i++) {
			    if(chr[i].checked){	
			      chRgData =chr[i].value+","+chRgData;
			    }	    
		 	}
		 	console.log("chRgData len "+chRgData.length);
		 	if(chRgData.length==''){
		    	console.log("else");
		    	$("#rgError").html("Please Select AtLeast One Region").show();
	        	hideSuccessErrorMessage("alert");
		    	return false;
			}else{	
			 	console.log("chRgData "+chRgData);		 	
			 	$("#spinner").show();
			 	$.getJSON("${request.getContextPath()}/DtRateDetail/saveRgSelection/?chRgData="+chRgData+"&brSelNextId="+brSelNextId, function(data) { 	
			 		$("#spinner").hide();	
		 			console.log(data.resultData);
		 		});		 		
			 	$("#mainChRgId").attr("checked", false);
			 }
		 }	
		 if(type=="regBr"){
		 	$("#regionBranchModal .close").click();
		 }else{
		 	$("#regionModal .close").click(); 	
		 }
		 $("#rateType").attr('disabled', true);
		 $("#butId").attr('disabled', true);
	}
	function regionTypeBranch(rgcd){
		$("#regionBranchModal").modal("hide");
		var html = '';	
		$("#spinner").show();
		$.getJSON("${request.getContextPath()}/DtRateDetail/getRegionBranchData/?rgcd="+rgcd, function(data) { 
		$("#spinner").hide();		
 			for(var i=0;i<data.BR_CODE.length;i++){
 				html +='<tr>'+
 				'<td><input type="checkbox" id="brName" name="brName" value="'+data.BR_CODE[i]+'"></td>'+
 				'<td>'+data.BR_NAME[i]+'</td>'+
 				'</tr>';
 			}
 			$("#branchTable tbody").empty().append(html);
 			applyDataTable('branchTable');
 			$("#branchModal").modal("show");
 		});		
	}
	function getRateDetails(){
		var rate = $("#rateVal").val();
		var brSelNextId = $("#brSelNextId").val();
		var parameter = {rate:rate,brSel:brSel,brSelNextId:brSelNextId}
		$("#spinner").show();
		$.ajax({
			url: "${request.getContextPath()}/DtRateDetail/getrate/",
			async:false,
			data :parameter,
			success : function(response){
				$("#spinner").hide();
				console.log(response.rateData);
				if(response.rateData == 'No Data'){
					if(rateErrorType == 'Branch'){
						$("#rateDataError").html('No Data For Selected Branch').show();
						hideSuccessErrorMessage("alert");
					}else if(rateErrorType == 'Region'){
						$("#rateDataError").html('No Data For Selected Region').show();
	        			hideSuccessErrorMessage("alert");
					}else if(rateErrorType == 'RegionBranch'){
						$("#rateDataError").html('No Data For Selected Region Wise Branch').show();
	        			hideSuccessErrorMessage("alert");
					}
				}else{
					$("#rateDataError").html('');
				}
				$("#uploadDateN").html(response);
			}		    					    			
	 	});	
	}
	function saveRateMaster(){
		$("#spinner").show();
		document.getElementById("rateDetailForm").action ="save";
	    document.getElementById("rateDetailForm").submit();
	}
	function returnToMainPage(){
		 if(confirm("Sure You Want To Exit?")){
		 	$("#spinner").show();
		 	document.getElementById("rateDetailForm").action ="${request.getContextPath()}/company/mainModule";
	    	document.getElementById("rateDetailForm").submit();
		 }
	 }
	 function clearAllFields(){
	 	if(confirm("Sure You Want To Cancel?")){
		 	$("#rateDetailForm").reset();	 	
	 	}
	 } 
</script>
<form>
	<g:hiddenField name="brNameSize" id="brNameSize" value="${BrMstTab.list()*.brName.size()}"/>
	<g:hiddenField name="brSelNextId" id="brSelNextId" value="${brSelNextVl}"/>
	<div id="list-dtRateDetail" class="content scaffold-list" role="main">	
		<div class="content-bg pull-left">
			<div class="row">
				<div class="col-md-8">
					<div class="panel panel-default">
							<table class="table">					
								<tbody>					
									<tr>
										<td colspan="2"><b>Branch Selection</b></td>															
									</tr>	
									<tr>
										<td><g:radio name="brSel" id="brSel" checked="checked" value="" onchange="disableFields();" />All Branches</td>
										<td><g:radio name="brSel" id="brSel" value="" onchange="enableFields();" />Selected Branches</td>
									</tr>
									<tr>
										<td><b>Rate type</b></td>
										<td>	
											<div class="select-style2"><g:select name="rateType" from="${rateTypeList}" tabindex="8"/></div>
											<button id="butId" type="button" class="active_btn" onclick="rateTypePopup();" data-toggle="modal" data-target="#regionModal">...</button>
										</td>
									</tr>
									<tr>
										<td><b>Rate Date</b></td>
										<td>
											<g:textField name="rateDate" class="form-control" />
										</td>
									</tr>
									<tr>
										<td><b>Rate</b></td>
										<td>
											<g:textField name="rateVal" class="form-control" onblur="getRateDetails();" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		<div class="alert alert-danger" role="alert" id="rateDataError" style="display: none;"></div>
		<div id="uploadDateN" style="overflow: auto; height: 200px;">
			<g:render template="dataDescription" />
		</div>
	<%-- REGION POPUP START--%>
		<div class="modal fade" id="regionModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Region List</h4>
					</div>
					<div class="modal-body">
						<div class="alert alert-danger" role="alert" id="rgError" style="display: none;"></div>
							<table id="regionTable" class="table">							
								<thead>
									<tr>
										<th width="10%"><input type="checkbox" id="mainChRgId" onclick="mainRgCheck(this);"/></th>
										<th align="center"><strong>Region Name</strong></th>									
									</tr>								
								</thead>
								<tbody></tbody>
							</table>
						</div>	
					<div class="modal-footer">
						<button type="button" class="active_btn" onclick="getRegionData();" >OK</button>
					</div>
				</div>
			</div>
		</div>
	<%-- REGION POPUP END--%>	
	<%-- BRANCH POPUP START--%> 
	<div class="modal fade" id="branchModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Branch List</h4>
					</div>
					<div class="modal-body">
						<div class="alert alert-danger" role="alert" id="brError" style="display: none;"></div>
							<table id="branchTable" class="table">	
									<thead>
										<tr>
											<th width="10%"><input type="checkbox" id="mainChBrId" onclick="mainBrCheck(this);"/></th>
											<th align="center"><strong>Branch Name</strong></th>
									    </tr>				                       	
									</thead>
									<tbody></tbody>	
								</table>
						</div>
					<div class="modal-footer">
						<button type="button" class="active_btn" onclick="getBranchData();" >OK</button>
					</div>
				</div>
			</div>
		</div>		
	<%-- BRANCH POPUP END--%>
	<%-- REGION BRANCH POPUP START --%>
			<div class="modal fade" id="regionBranchModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Region List</h4>
						</div>
						<div class="modal-body">
							<table id="rgBrTblId" class="table">							
								<thead>
									<tr>
										<th width="10%"><input type="checkbox" id="mainChRgId" onclick="mainRgCheck(this);"/></th>
										<th align="center"><strong>Region Name</strong></th>
										<th></th>
									</tr>								
								</thead>		
								<tbody></tbody>		
							</table> 
						</div>
						<div class="modal-footer">
							<button type="button" class="active_btn" onclick="getRegionData('regBr');">OK</button>
						</div>
					</div>
				</div>
			</div>
	<%-- REGION BRANCH POPUP ENDS --%>
	</div>
</form>