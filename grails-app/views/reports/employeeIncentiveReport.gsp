<%-- 
     -- File Name: employeeIncentiveReport
     -- Description: it is used to get the employee Incentive Report
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Sachin      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab"%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Employee Incentive Report</title>
<script type="text/Javascript">

var catVal=""
$(document).ready(function(){
	$(".editClick td:not(.action)").click(function () {
		catVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
});
  function validateEmployeeIncentive(format,extension) {
  	var flag;
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
  		document.getElementById('employeeIncentiveReportForm').format1.value = format;
		document.getElementById('employeeIncentiveReportForm').extension.value = extension;
		
		document.getElementById("employeeIncentiveReportForm").action ="createEmployeeIncentiveReport";
     	document.getElementById("employeeIncentiveReportForm").submit();
        //$("#employeeIncentiveReportForm").submit();
        //return false;
  	}  	  	     	
  }
  function validate(){
  		var flag = false;	  		  		
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
             alert("Please Select From Date");
             return false;
         }         
         return flag;
   }
   
   function displayTextOnPayMode(){
 	$(".editClick").removeClass('highlight');
 	$("#paymode").val(catVal);
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
							<h1>Employee Incentive Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="employeeIncentiveReportForm" id="employeeIncentiveReportForm">		
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
															<td>To Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="To Date" name="toDate"/></td>
														</tr>							
														<tr>
															<td>Branch</td>
															<td>
																<div class="select-style2">
																	<g:select name="brName" from="${BrMstTab.list()*.brName}" noSelection="${['':'Select One...']}"/>
																</div>
														</td>
														</tr>
														<tr>
															<td>Type</td>
															<td>
																<div class="select-style2">
																	<g:select name="brName" from="${BrMstTab.list()*.brName}" noSelection="${['':'Select One...']}"/>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateEmployeeIncentive('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateEmployeeIncentive('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateEmployeeIncentive('csv','csv')"></g:submitButton>
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
	<div class="modal fade" id="payModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">PayMode List</h4>
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
							<g:each in="${payModeListforKP}" var="payModeListInst" status="i">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${payModeListInst?.vc_pay_type}">
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
</body>
</html>