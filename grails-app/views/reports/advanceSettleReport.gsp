<%-- 
     -- File Name: AdvanceSettleReport
     -- Description: it is used to get the AdvanceSettleReport
     -- Author(s): CTE 
     -- Date: 04/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 04/05/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.FndFlexValuesVl"%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Advance Settle Report</title>
<script type="text/javascript">  
var catVal = '';
var payModeVal = '';
$(document).ready(function(){
	applyDataTable('catTblId');
	applyDataTable('payModeTblId');
	$("#paymode").val('ALL');
	$(".clickOnCat td:not(.action)").click(function () {
		catVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
	
	$(".clickOnPayMode td:not(.action)").click(function () {
		payModeVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
}); 

function displayTextOnCat(){
 	$(".clickOnCat").removeClass('highlight');
 	$("#category").val(catVal);
 }	
 
 function displayTextOnPayMode(){
 	$(".clickOnPayMode").removeClass('highlight');
 	$("#paymode").val(payModeVal);
 }	
 
  function validateAdvanceSettleReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
        document.getElementById('advanceSettleReportForm').format1.value = format;
		document.getElementById('advanceSettleReportForm').extension.value = extension;
		
		document.getElementById("advanceSettleReportForm").action ="createAdvanceSettleReport";
     	document.getElementById("advanceSettleReportForm").submit();
  	}  	  	     	
  }
  function validate(){
  		var flag = "false";  	
    	var fromDate = $('#fromDate').val();
    	var toDate = $('#toDate').val();    	    	
    	
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
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Advance Settle Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="advanceSettleReportForm" id="advanceSettleReportForm">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">					
													<thead>					
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
																	<g:textField size="17" name="category" class="form-control"/>
																	<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#catModal">...</button>
																</div>
															</td>
														</tr>
														<tr>
															<td>Pay Mode</td>
															<td>
																<div class="textbtn">
																	<g:textField size="17" name="paymode" class="form-control"/>
																	<button id="butIdPayMode" type="button" class="active_btn" data-toggle="modal" data-target="#payModal">...</button>
																</div>
															</td>
														</tr>
													</thead>				
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-8">
											<g:hiddenField name="format1"/>
											<g:hiddenField name="extension"/>
											<div class="pull-right rightsapce">
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateAdvanceSettleReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateAdvanceSettleReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateAdvanceSettleReport('csv','csv')"></g:submitButton>
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
														<th>Value</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${descList}" var="descListInst" status="i">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnCat" id="${descListInst?.description}">
															<td name="popDesc" id="popDesc">${descListInst?.description}</td>
															<td name="popValue" id="popValue">${descListInst?.flex_value_meaning}</td>
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
						<%-- PAYMODE POPUP START --%>
							<div class="modal fade" id="payModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Category List</h4>
										</div>
										<div class="modal-body">
											<table width="100%" id="payModeTblId" class="table">
												<thead>
													<tr>
														<th>Pay Mode</th>
														<th>Pay Type</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${payModeList}" var="payModeListInst" status="i">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnPayMode" id="${payModeListInst?.vc_pay_type}">
															<td name="popPayModeId" id="popEmpId">${payModeListInst?.vc_pay_mode}</td>
															<td name="popPayModeName" id="popEmpName">${payModeListInst?.vc_pay_type}</td>
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="displayTextOnPayMode();" data-dismiss="modal">OK</button>
											<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- PAYMODE POPUP ENDS --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>