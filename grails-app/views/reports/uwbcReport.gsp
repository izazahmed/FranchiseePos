<%-- 
     -- File Name: uwbcReport
     -- Description: it is used to get the uwbcReport
     -- Author(s): CTE 
     -- Date: 15/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/05/2016	   Abhijit     		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.HdCash" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Userwise Bill Count Report</title>
<script type="text/javascript"> 
  function validateBillCountReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{ 		
        document.getElementById('uwbcReportForm').format1.value = format;
		document.getElementById('uwbcReportForm').extension.value = extension;
		
		document.getElementById("uwbcReportForm").action ="createUWBillCountReport";
     	document.getElementById("uwbcReportForm").submit();
        //$("#uwbcReportForm").submit();
        //return false;
  	}  	  	     	
  }
  function validate(){
  		var flag = "false";
  		var brName = $('#brName').val();
    	var fromDate = $('#fromDate').val();
    	var toDate = $('#toDate').val(); 	   
    	
    	if(brName !== "" && brName != null && brName !== undefined){
             flag="true";
         }else{
         	 flag="false";	
             alert("Please Select Branch");
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
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Userwise Bill Count Report</h1>
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
							<g:form name="uwbcReportForm" id="uwbcReportForm">		
							<div class="content-bg pull-left">
								<div class="row">
									<div class="col-md-8">
										<div class="panel panel-default">
											<table class="table">			
												<thead>
													<tr>
														<td>Branch Name</td>
														<td><div class="select-style2"><g:select name="brName" from="${BrMstTab.list()*.brName}" noSelection="${['':'Select One...']}"/></div></td>
													</tr>
													<tr>
														<td>From Date</td>
														<td><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/></td>
													</tr>
													<tr>
														<td>End Date</td>
														<td><g:textField class="form-control endDateStr" placeholder="End Date" name="toDate"/></td>
													</tr>					
												</thead>				
											</table>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-8">
										<div class="pull-right rightsapce">
											<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateBillCountReport('view','view')"></g:submitButton>
											<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateBillCountReport('pdf','pdf')"></g:submitButton>
											<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateBillCountReport('csv','csv')"></g:submitButton>
										</div>
										<g:hiddenField name="format1"/>
										<g:hiddenField name="extension"/>
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