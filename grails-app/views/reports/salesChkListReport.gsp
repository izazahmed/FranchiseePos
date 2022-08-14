<%-- 
     -- File Name: SalesChkListReport
     -- Description: it is used to get the SalesChkListReport
     -- Author(s): CTE 
     -- Date: 04/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 04/05/2016	   Abhijit      	Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.EmpMst"%>
<%@ page import="com.tbz.franchisee.HdCash"%>
<%@ page import="com.tbz.franchisee.FndFlexValuesVl"%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Sales Check List (Item Wise/Customer Wise) Report</title>
<script type="text/javascript"> 
var catVal = '';
var empIdVal = '';
var custNameVal = '';
$(document).ready(function(){	
	applyDataTable('catTblId');
	applyDataTable('empTblId');	
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
	today = dd+'-'+mm+'-'+yyyy;
	$("#fromDate").val(today);
	$("#toDate").val(today);
	
	$("#purModal").hide();
	
	$(".clickOnCat td:not(.action)").click(function () {
		catVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).addClass('highlight');
	});
	
	$(".editClickOnEmp td:not(.action)").click(function () {
		empIdVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
	
	$(".clickOnCust td:not(.action)").click(function () {
		custNameVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
	
	$("#purity").attr('disabled', true);	
	$("#butIdPur").attr('disabled', true);
	
	$("#item").attr('disabled', true);	
	$("#butIdItem").attr('disabled', true);
	
	$("#subCode").attr('disabled', true);	
	$("#butIdSubCode").attr('disabled', true);
	
	$("#deptt").attr('disabled', true);	
	$("#butIdDeptt").attr('disabled', true);
	
	$("#custName").attr('disabled', true);	
	$("#butIdCust").attr('disabled', true);
}); 

function displayTextOnCat(){
 	$(".clickOnCat").removeClass('highlight');
 	$("#category").val(catVal);
 }	

function displayTextOnEmpId(){
 	$(".editClickOnEmp").removeClass('highlight');
 	$("#empId").val(empIdVal);
 }	
 
 function displayTextOnCustomer(){
 	$(".editClickOnEmp").removeClass('highlight');
 	$("#custName").val(custNameVal);
 }	

function modifyPurityFields(val){
	if(val=='specific'){
		$("#purity").attr('disabled', false);	
		$("#butIdPur").attr('disabled', false);
	}else{
		$("#purity").attr('disabled', true);	
		$("#butIdPur").attr('disabled', true);
	}
}

function getPurDataBasedOnCat(){
 	var purityTemp="";
 	$("#findPur").val('');
 	var desVal = $("#category").val();
 	var html = '';
     if(desVal==''){
     	alert("Please Select Category");
     }else{       
     $.getJSON("${request.getContextPath()}/mstRateParameter/getPurityList/?desVal=" + desVal, function(data) {	
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
 	$("#purModal").dialog("destroy");
 	$("#purity").val(popPurVal);
 }	
 function cancelPurity(){
 	$("#purModal").dialog("destroy");
 }
 function searchPurity(){
 	var findPur = $("#findPur").val();
 	var desVal = $("#description1").val();
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
 
 function modifyItemFields(val){
	if(val=='specific'){
		$("#item").attr('disabled', false);	
		$("#butIdItem").attr('disabled', false);
	}else{
		$("#item").attr('disabled', true);	
		$("#butIdItem").attr('disabled', true);
	}
}
 
 function modifySubCodeFields(val){
	if(val=='specific'){
		$("#subCode").attr('disabled', false);	
		$("#butIdSubCode").attr('disabled', false);
	}else{
		$("#subCode").attr('disabled', true);	
		$("#butIdSubCode").attr('disabled', true);
	}
}

function modifyDeptFields(val){
	if(val=='specific'){
		$("#deptt").attr('disabled', false);	
		$("#butIdDeptt").attr('disabled', false);
	}else{
		$("#deptt").attr('disabled', true);	
		$("#butIdDeptt").attr('disabled', true);
	}
}

function modifyCustomerFields(val){
	if(val=='specific'){
		$("#custName").attr('disabled', false);	
		$("#butIdCust").attr('disabled', false);
	}else{
		$("#custName").attr('disabled', true);	
		$("#butIdCust").attr('disabled', true);
	}
}
 
  function validateSalesReport(format,extension) { 		 
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
  		document.getElementById('SalesChkListReportForm').format1.value = format;
		document.getElementById('SalesChkListReportForm').extension.value = extension;
  		
  		document.getElementById("SalesChkListReportForm").action ="createChkListReport";
        document.getElementById("SalesChkListReportForm").submit();
  	}  	  	     	
  }
 function validate(){
 	var flag = "false";
 	var category = $('#category').val();
   	var fromDate = $('#fromDate').val();
   	var toDate = $('#toDate').val();    
   	
   	if(category !== "" && category != null && category !== undefined){
        flag = true;
    }else{
    	 flag = false;	
         alert("Please Select Category");
         return false;
    }
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
						<div id="create-empMst" class="content scaffold-create">
							<h1>Sales Check List (Item Wise/Customer Wise) Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
												error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="SalesChkListReportForm" id="SalesChkListReportForm">
								<g:hiddenField name="format1"/>
								<g:hiddenField name="extension"/>
								<div class="content-bg pull-left">
									<div class="col-md-8">
										<div class="panel panel-default">
											<table class="table">
												<tbody>
													<tr><td colspan="2"><b>Specification</b></td></tr>
														<tr>
															<td>From Date</td>
															<td><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/></td>
														</tr>
														<tr>
															<td>End Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="End Date" name="toDate"/></td>
														</tr>	
														<tr>
															<td>Category</td>
															<td>
																<div class="textbtn">
																	<g:textField class="form-control" size="37" name="category" />
																	<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#catModal">...</button>
																</div>
															</td>
														</tr>
														<tr>
															<td>Emp ID</td>
															<td>
																<div class="textbtn">
																	<g:textField size="37" name="empId" class="form-control" />
																	<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#empIdModal">...</button>
																</div>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<div class="pull-right topsapce">
																	<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateSalesReport('view','view')"></g:submitButton>
																	<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateSalesReport('pdf','pdf')"></g:submitButton>
																	<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateSalesReport('csv','csv')"></g:submitButton>
																</div>
															</td>
														</tr>
														<tr>
															<td>
																<table class="table">
																	<tr><td colspan="2"><b>Purity</b></td></tr>
																	<tr>
																		<td colspan="2"><g:radio name="purSel" value="" checked="ckecked" onchange="modifyPurityFields('all');"/>ALL</td>
																	</tr>
																	<tr>
																		<td><g:radio name="purSel" value="" onchange="modifyPurityFields('specific');"/>Specific</td>
																		<td>
																			<div class="textbtn">
																				<g:textField size="17" name="purity" class="form-control"/>
																				<button id="butIdPur" type="button" class="active_btn" onclick="getPurDataBasedOnCat();">...</button>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
															<td>
																<table class="table">
																	<tr><td colspan="2"><b>Item</b></td></tr>
																	<tr>
																		<td colspan="2"><g:radio name="itemSel" value="" checked="ckecked" onchange="modifyItemFields('all');"/>ALL</td>
																	</tr>
																	<tr>
																		<td><g:radio name="itemSel" value="" onchange="modifyItemFields('specific');"/>Specific</td>
																		<td>
																			<div class="textbtn">
																				<g:textField size="17" name="item" class="form-control"/>
																				<button id="butIdItem" type="button" class="active_btn" onclick="getPurDataBasedOnCat();">...</button>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr><td colspan="2"><b>Sub Code</b></td></tr>
														<tr>
															<td colspan="2"><g:radio name="subCodeSel" id="subCodeSel" value="" checked="ckecked" onchange="modifySubCodeFields('all');"/>ALL</td>
														</tr>
														<tr>
															<td><g:radio name="subCodeSel" value="" onchange="modifySubCodeFields('specific');" />Specific</td>
															<td>
																<div class="textbtn">
																	<g:textField size="17" name="subCode" class="form-control"/>
																	<button id="butIdSubCode" type="button" class="active_btn" onclick="getSubCodeDataBasedOnCat();">...</button>
																</div>
															</td>
														</tr>
														<tr><td colspan="2"><b>Deptt</b></td></tr>
														<tr>
															<td colspan="2"><g:radio name="deptSel" value="" checked="ckecked" onchange="modifyDeptFields('all');"/>ALL</td>
														</tr>
														<tr>
															<td><g:radio name="deptSel" value="" onchange="modifyDeptFields('specific');"/>Specific</td>
															<td>
																<div class="textbtn">
																	<g:textField size="17" name="deptt" class="form-control"/>
																	<button id="butIdDeptt" type="button" class="active_btn" onclick="getDepttDataBasedOnCat();">...</button>
																</div>
															</td>
														</tr>
														<tr><td colspan="2"><b>Customer</b></td></tr>
														<tr>
															<td colspan="2"><g:radio name="custSel" id="custSel" value="" checked="ckecked" onchange="modifyCustomerFields('all');"/>ALL</td>
														</tr>
														<tr>
															<td><g:radio name="custSel" id="custSel" value="" onchange="modifyCustomerFields('specific');"/>Specific</td>
															<td>
																<div class="textbtn">
																	<g:textField size="17" name="custName" class="form-control"/>
																	<button id="butIdCust" type="button" class="active_btn" data-toggle="modal" data-target="#custModal">...</button>
																</div>
															</td>
														</tr>
												</tbody>
											</table>
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
											<table class="table">
												<thead>
													<tr>
														<th>Description</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${FndFlexValuesVl.list()*.description}" status="i" var="catPopUpDataInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnCat" id="${catPopUpDataInst}">
															<td name="catPopDesc" id="catPopDesc">${catPopUpDataInst}</td>
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
						<%-- EMPID POPUP START --%>
							<div class="modal fade" id="empIdModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>
										<div class="modal-body">
											<table class="table">
												<thead>
													<tr>
														<th>Emp Id</th>
														<th>SalesPerson Name</th>
													</tr>
												</thead>
												<tbody>
													<g:each in="${empList}" var="empListInst" status="i">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClickOnEmp" id="${empListInst?.VC_SALES_ID}">
															<td name="popEmpId" id="popEmpId">${empListInst?.VC_SALES_ID}</td>
															<td name="popEmpName" id="popEmpName">${empListInst?.EMP_NAME}</td>
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="displayTextOnEmpId();" data-dismiss="modal">OK</button>
											<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- EMPID POPUP ENDS --%>
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
					<%-- CUSTOMER POPUP START --%>
							<div class="modal fade" id="catModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">CUSTOMER List</h4>
										</div>
										<div class="modal-body">
											<table width="100%" id="catTblId" class="table">
												<thead>
													<tr>
														<th>Customer Name</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${customer}" status="i" var="custInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnCust" id="${empListInst?.VC_CUST_FNAME}">
															<td name="popCustName" id="popCustName">${empListInst?.VC_CUST_FNAME}</td>
														</tr>
														
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
												<button type="button" class="active_btn" onclick="displayTextOnCustomer();" data-dismiss="modal">OK</button>
												<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- CUSTOMER POPUP ENDS --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>