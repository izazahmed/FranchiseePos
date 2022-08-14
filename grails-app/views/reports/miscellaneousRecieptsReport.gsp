<%-- 
     -- File Name: miscellaneousRecieptsReport
     -- Description: it is used to get the miscellaneousRecieptsReport 
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
<title>Miscalleneous Receipts Report</title>
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
	$("#toDate").val(today);
	
}); 
  function validateMiscellaneousRecieptsReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
        document.getElementById('miscellaneousRecieptsReportForm').format1.value = format;
		document.getElementById('miscellaneousRecieptsReportForm').extension.value = extension;
		
		document.getElementById("miscellaneousRecieptsReportForm").action ="createMiscellaneousRecieptsReport";
     	document.getElementById("miscellaneousRecieptsReportForm").submit();
        //$("#miscellaneousRecieptsReportForm").submit();
        //return false;
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
							<h1>Miscalleneous Receipts Report</h1>
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
							<g:form name="miscellaneousRecieptsReportForm" id="miscellaneousRecieptsReportForm">		
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
															<td><div class="select-style2"><g:select name="category" id="category" from="${FndFlexValuesVl.list()*.description}"/></div></td>
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
												<g:submitButton class="active_btn" name="Submit" value="View" onclick="return validateMiscellaneousRecieptsReport('view','view')"></g:submitButton>
												<g:submitButton class="active_btn" name="Submit" value="PDF" onclick="return validateMiscellaneousRecieptsReport('pdf','pdf')"></g:submitButton>
												<g:submitButton class="active_btn" name="Submit" value="CSV" onclick="return validateMiscellaneousRecieptsReport('csv','csv')"></g:submitButton>
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