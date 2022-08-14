<%-- 
     -- File Name: katanWadharaReport
     -- Description: it is used to get the katanWadharaReport
     -- Author(s): CTE 
     -- Date: 17/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 17/05/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.FndFlexValuesVl"%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Katan Wadhara Report Report</title>
<script type="text/javascript">  
var catVal = '';
var itemVal = '';
var subVal = '';
var popPurVal = '';
$(document).ready(function(){	
	applyDataTable('catTblId');
	applyDataTable('itemTblId');
	applyDataTable('subTblId');
	$("#purModal").hide();
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
	//today = dd+'-'+mm+'-'+yyyy+' '+hh+':'+minutes+':'+seconds;
	today = mm+'/'+dd+'/'+yyyy;
	$("#fromDate").val(today);
	$("#toDate").val(today);
	
	$("#paymode").val('ALL');
	
	$(".clickOnCat td:not(.action)").click(function () {
		catVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
	
	$(".clickOnItem td:not(.action)").click(function () {
		itemVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
	
	$(".clickOnSub td:not(.action)").click(function () {
		subVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
}); 

function displayTextOnCat(){
 	$(".clickOnCat").removeClass('highlight'); 	
 	var allVal = [];
	allVal = catVal.split(",");	
	var c1 = allVal[0];
	var c2 = allVal[1];
	$("#hidCat").val(c2);
 	$("#category").val(c1);
 }	
 
 function displayTextOnItem(){
 	$(".clickOnItem").removeClass('highlight');
 	$("#item").val(itemVal);
 }	
 
 function displayTextOnSub(){
 	$(".clickOnSub").removeClass('highlight');
 	$("#sub").val(subVal);
 }	
 
 function getPurDataBasedOnCat(){
 	var purityTemp="";
 	$("#findPur").val('');
 	var desVal = $("#category").val();
 	if(desVal==''){
 		alert("please select category");
 	}else{
	 	var html = '';	         
	        $.getJSON("${request.getContextPath()}/reports/getKatanPurityList/?desVal=" + desVal, function(data) {	
	        	console.log(data.PURITY.length);
	        	if(data.PURITY.length == 0){
					$("#purError").html("No Data For Selcted Category").show();
				}else{
					$("#purError").html("");
		 			for(var i=0;i<data.PURITY.length;i++){
		 				html +='<tr>'+ 				
		 				'<td id="popPurId'+i+'" name="'+data.PURITY[i]+'" value="'+data.PURITY[i]+'" onclick="getPurValue('+i+');">'+data.PURITY[i]+'</td>'+
		 				'</tr>';
		 			}
	 			}
	 			$("#purTblId tbody").empty();
	 			$("#purTblId tbody").append(html);
	 			
	 			$("#purModal").dialog({
	               width:600,
	               hight:100
	         	});
	 		});	
 		}	
 }
 function getPurValue(val){
 	popPurVal = $("#popPurId" + val+"").html();
	$('.highlight').removeClass('highlight');
	$("#popPurId" + val+"").addClass('highlight');
 }
 function displayPurity(){
 	$(".clickOnPur").removeClass('highlight');
 	//var purPopDesc1 = $("#purPopDesc").val();
 	//$("#pur1").val(purPopDesc1);
 	
 	$("#purModal").dialog("destroy");
 	$("#purity").val(popPurVal);
 }	
 function cancelPurity(){
 	$("#purModal").dialog("destroy");
 }
 function searchPurity(){
 	var findPur = $("#findPur").val();
 	var desVal = $("#category").val();
 	var html = '';
 	$.getJSON("${request.getContextPath()}/mstRateParameter/serchPurity/?desVal="+desVal+"&findPur="+findPur, function(data) {	
        	console.log(data.PURITY.length);
        	if(data.PURITY.length == 0){
				$("#purError").html("No Purity").show();
			}else{
				$("#purError").html("");
	 			for(var i=0;i<data.PURITY.length;i++){
	 				html +='<tr>'+ 				
	 				'<td id="popPurId'+i+'" name="'+data.PURITY[i]+'" value="'+data.PURITY[i]+'" onclick="getPurValue('+i+');">'+data.PURITY[i]+'</td>'+
	 				'</tr>';
	 			}
 			}
 			$("#purTblId tbody").empty();
 			$("#purTblId tbody").append(html); 			 		
 		});		
 }
 
  function validateKatanWadharaReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
        document.getElementById('katanWadharaReportForm').format1.value = format;
		document.getElementById('katanWadharaReportForm').extension.value = extension;
		
		document.getElementById("katanWadharaReportForm").action ="createKatanWadharaReport";
     	document.getElementById("katanWadharaReportForm").submit();
        //$("#advanceSettleReportForm").submit();
        //return false;
  	}  	  	     	
  }
  function validate(){
  		var flag = "false";  	
    	var fromDate = $('#fromDate').val();
    	var toDate = $('#toDate').val();
    	var category = $('#category').val();    	    	
    	
         if(fromDate !== "" && fromDate != null && fromDate !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select From Date");
             return false;
         }
         if(toDate !== "" && toDate != null && toDate !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select To Date");
             return false;
         }    
         if(category !== "" && category != null && category !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select Category");
             return false;
         }            
         return flag;
   }
</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Katan Wadhara Report Report</h1>
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
							<g:form name="katanWadharaReportForm" id="katanWadharaReportForm">		
							<g:hiddenField name="hidCat" />
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
										<div class="panel panel-default">
												<table class="table">					
													<thead>		
														<tr>
															<td>From Date</td>
															<td><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/></td>
															<td>End Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="End Date" name="toDate"/></td>
														</tr>
														<tr>
															<td>Category</td>
															<td>
																<div class="textbtn">
																	<g:textField size="17" name="category" class="form-control"/>
																	<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#catModal">...</button>
																</div>
															</td>														
															<td>Item</td>
															<td>
																<div class="textbtn">
																	<g:textField size="17" name="item" class="form-control"/>
																	<button id="butIdItem" type="button" class="active_btn" data-toggle="modal" data-target="#itemModal">...</button>
																</div>
															</td>
														</tr>		
														<tr>
															<td>Sub</td>
																<td>
																	<div class="textbtn">
																		<g:textField size="17" name="sub" class="form-control"/>
																		<button id="butIdSub" type="button" class="active_btn" data-toggle="modal" data-target="#subModal">...</button>
																	</div>
																</td>
															<td>Purity</td>
															<td>
																<div class="textbtn">
																	<g:textField size="17" name="purity" class="form-control"/>
																	<button id="butIdPurity" type="button" class="active_btn" onclick="getPurDataBasedOnCat();">...</button>
																</div>
															</td>
														</tr>																																																												
													</thead>	
												</table>
												<fieldset style="border: 1px solid #1F497D; border-radius: 2px; padding: 15px;">
													<div>
														<div class="col-md-1 col-sm-1 col-xs-1"><g:radio name="kwSel" checked="checked" value="K"/>Katan</div>
														<div class="col-md-2 col-sm-2 col-xs-2"><g:radio name="kwSel" value="W"/>Wadhara</div>
													</div>
												</fieldset>	
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-8">
											<g:hiddenField name="format1"/>
											<g:hiddenField name="extension"/>
											<div class="pull-right rightsapce topsapce">
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateKatanWadharaReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateKatanWadharaReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateKatanWadharaReport('csv','csv')"></g:submitButton>
											</div>
										</div>
									</div>
								</div>
							</g:form>
							<%-- CATEGORY POPUP START --%>
							<div class="modal fade" id="catModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Category List</h4>
										</div>
										<div class="modal-body">
											<table width="100%" id="catTblId" class="table">
												<thead>
													<tr>
														<th>Description</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${descList}" var="descListInst" status="i">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnCat" id="${descListInst?.vc_reason_desc},${descListInst?.vc_reason_code}">
															<td name="popDesc" id="popDesc">${descListInst?.vc_reason_desc}</td>															
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="displayTextOnCat();" data-dismiss="modal">OK</button>
											<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- CATEGORY POPUP ENDS --%>
						<%-- ITEM POPUP START --%>
							<div class="modal fade" id="itemModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Item List</h4>
										</div>
										<div class="modal-body">
											<table width="100%" id="itemTblId" class="table">
												<thead>
													<tr>
														<th>Item Code</th>
														<th>Item Desc</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${itemList}" var="itemListInst" status="i">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnItem" id="${itemListInst?.vc_group_desc}">
															<td name="popItemId" id="popItemId">${itemListInst?.vc_group_code}</td>
															<td name="popItemName" id="popItemName">${itemListInst?.vc_group_desc}</td>
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="displayTextOnItem();" data-dismiss="modal">OK</button>
											<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- ITEM POPUP ENDS --%>
						<%-- SUB POPUP START --%>
							<div class="modal fade" id="subModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Sub List</h4>
										</div>
										<div class="modal-body">
											<table width="100%" id="subTblId" class="table">
												<thead>
													<tr>
														<th>SubCode</th>
														<th>Description</th>
													</tr>
												</thead>
												<tbody>
													<g:each in="${subList}" var="subListInst" status="i">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnSub" id="${subListInst?.vc_reason_desc}">
															<td name="popSubId" id="popSubId">${subListInst?.vc_reason_code}</td>
															<td name="popSubName" id="popSubName">${subListInst?.vc_reason_desc}</td>
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="displayTextOnSub();" data-dismiss="modal">OK</button>
											<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- SUB POPUP ENDS --%>
						<%-- PURITY POPUP START --%>
						<div class="modal fade" id="purModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Purity List</h4>
										</div>
										<div class="modal-body">
											<div class="alert alert-danger" role="alert" id="purError" style="display: none;"></div>
											<table width="100%" id="purTblId" class="table">
												<thead>
													<tr>
														<th>Purity</th>
													</tr>
												</thead>
												<tbody id="purTblBdId">
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="displayPurity();">OK</button>
											<button type="button" class="active_btn" onclick="cancelPurity();">Cancel</button>
										</div>
									</div>
								</div>
							</div>
					<%-- PURITY POPUP ENDS --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>