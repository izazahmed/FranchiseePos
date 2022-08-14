<%-- 
     -- File Name: cashAnalysisReport
     -- Description: it is used to get the cashAnalysisReport
     -- Author(s): CTE 
     -- Date: 10/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 10/05/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.FndFlexValuesVl"%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Cash Analysis Report</title>
<script type="text/javascript">  
var catVal = '';
$(document).ready(function(){	

	applyDataTable('catTblId');
	
	$(".clickOnCat td:not(.action)").click(function () {
		catVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).addClass('highlight');
	});
	
	$("#category").attr('disabled', true);	
	$("#butIdCat").attr('disabled', true);
	
	$("#mid").attr('disabled', true);	
}); 

function displayTextOnCat(){
 	$(".clickOnCat").removeClass('highlight');
 	$("#category").val(catVal);
 }	
 
 function modifyCategoryFields(val){
	if(val=='specific'){
		$("#category").attr('disabled', false);	
		$("#butIdCat").attr('disabled', false);
	}else{
		$("#category").attr('disabled', true);	
		$("#butIdCat").attr('disabled', true);
	}
}
function modifyMidFields(val){
	if(val=='specific'){
		$("#mid").attr('disabled', false);	
	}else{
		$("#mid").attr('disabled', true);	
	}
}

  function validateCashAnalysisReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
        document.getElementById('cashAnalysisReportForm').format1.value = format;
		document.getElementById('cashAnalysisReportForm').extension.value = extension;
		
		document.getElementById("cashAnalysisReportForm").action ="createCashAnalysis";
     	document.getElementById("cashAnalysisReportForm").submit();
  	}  	  	     	
  }
  function validate(){
  		var flag = "false";  	
    	var fromDate = $('#fromDate').val();
    	//var machineId = $('#machineId').val();  
    	//var machineNo = $('#machineNo').val();    	    	
    	
         if(fromDate !== "" && fromDate != null && fromDate !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select Date");
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
							<h1>Cash Analysis Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="cashAnalysisReportForm" id="cashAnalysisReportForm">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
													<table class="table">					
														<tbody>					
																<tr>
																	<td>Date</td>
																	<td><g:textField class="form-control startDateStr" placeholder="Date" name="fromDate"/></td>
																</tr>	
																<tr>
																	<td>Other Expenses</td>
																	<td><g:textField size="37" class="form-control" name="othExp" /></td>
																</tr>
																<tr>
																	<td>Cash Deposited</td>
																	<td><g:textField size="37" class="form-control" name="cashDepositedId" /></td>
																</tr>
																<tr>
																	<td>
																		<table class="table">
																			<tr><td colspan="2"><b>Category</b></td></tr>
																			<tr>
																				<td colspan="2"><g:radio name="catSel" value="ALL" checked="ckecked" onchange="modifyCategoryFields('all');"/>ALL</td>																	
																			</tr>
																			<tr>
																				<td><g:radio name="catSel" value="SPECIFIC" onchange="modifyCategoryFields('specific');"/>Specific</td>
																				<td>
																					<div class="textbtn">
																						<g:textField class="form-control" size="17" name="category" />
																						<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#catModal">...</button>
																					</div>
																				</td>
																			</tr>
																		</table>
																	</td>
																	<td>
																		<table class="table">
																			<tr><td colspan="2"><b>Machine Id</b></td></tr>
																			<tr>
																				<td colspan="2"><g:radio name="midSel" value="ALL" checked="ckecked" onchange="modifyMidFields('all');"/>ALL</td>																	
																			</tr>
																			<tr>
																				<td><g:radio name="midSel" value="SPECIFIC" onchange="modifyMidFields('specific');"/>Specific</td>
																				<td>
																					<div class="textbtn">
																						<g:textField class="form-control" size="17" name="mid" id="mid"/>
																					</div>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td><g:radio name="reportType" value="Summary" checked="ckecked" />Summary</td>		
																	<td><g:radio name="reportType" value="Detail" />Detail</td>																												
																</tr>
															</tbody>
														</table>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-8">
											<g:hiddenField name="format1"/>
											<g:hiddenField name="extension"/>
											<div class="pull-right rightsapce">
												<g:submitButton class="active_btn" name="Submit" value="View" onclick="return validateCashAnalysisReport('view','view')"></g:submitButton>
												<g:submitButton class="active_btn" name="Submit" value="PDF" onclick="return validateCashAnalysisReport('pdf','pdf')"></g:submitButton>
												<g:submitButton class="active_btn" name="Submit" value="CSV" onclick="return validateCashAnalysisReport('csv','csv')"></g:submitButton>
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>