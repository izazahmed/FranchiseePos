<%-- 
     -- File Name: cashCounterReport
     -- Description: it is used to get the cashCounterReport
     -- Author(s): CTE 
     -- Date: 05/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 05/05/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.FndFlexValuesVl"%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Cash Counter Report</title>
<script type="text/javascript">  
$(document).ready(function(){	
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
	
}); 
  function validateCashCounterReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
        document.getElementById('cashCounterReportForm').format1.value = format;
		document.getElementById('cashCounterReportForm').extension.value = extension;
		
      	document.getElementById("cashCounterReportForm").action ="createCashCounterReport";
     	document.getElementById("cashCounterReportForm").submit();
  	}  	  	     	
  }
  function validate(){
  		var flag = "false";  	
    	var fromDate = $('#fromDate').val();
    	var machineId = $('#machineId').val();  
    	var machineNo = $('#machineNo').val();    	    	
    	
         if(fromDate !== "" && fromDate != null && fromDate !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select From Date");
             return false;
         }
         if(machineId !== "" && machineId != null && machineId !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Enter MachineId");
             return false;
         }     
         if(machineNo !== "" && machineNo != null && machineNo !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Enter MachineNo");
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
							<h1>Cash Counter Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="cashCounterReportForm" id="cashCounterReportForm">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">					
													<thead>					
														<tr>
															<td>Date</td>
															<td><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/></td>
														</tr>
														<tr>
															<td>Machine Id</td>
															<td><g:textField name="machineId" value="ALL" class="form-control" /></td>
														</tr>
														<tr>
															<td>Machine No</td>
															<td><g:textField name="machineNo" value="ALL" class="form-control"/></td>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateCashCounterReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateCashCounterReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateCashCounterReport('csv','csv')"></g:submitButton>
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
</body>
</html>