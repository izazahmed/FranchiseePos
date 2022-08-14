<%-- 
     -- File Name: misReport
     -- Description: it is used to get the misReport
     -- Author(s): CTE 
     -- Date: 15/04/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/04/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.CustMst"%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>MIS Report</title>
<script type="text/Javascript">

  function validateMisReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{  	
  		document.getElementById('misReportForm').format1.value = format;
		document.getElementById('misReportForm').extension.value = extension;
		
		document.getElementById("misReportForm").action ="createMisReport";
     	document.getElementById("misReportForm").submit();
        //$("#misReportForm").submit();
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
</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>MIS Report</h1>
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
							<g:form name="misReportForm" id="misReportForm">
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
										<div class="panel panel-default">
												<table class="table">
													<thead>
														<tr>
															<td>From Date</td>
															<td>
																<g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate" />															
														</tr>
														<tr>
															<td>To Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="To Date" name="toDate" />										
														</tr>
														<tr>
															<td>Category</td>
															<td>
																<div class="select-style2">
																	<select name="category" id="category">
																		<option value="A">ALL</option>
																		<option value="G">GOLD</option>
																		<option value="D">DIAMOND</option>
																		<option value="S">SILVER</option>
																	</select>
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
											<div class="rightsapce pull-right">
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateMisReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateMisReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateMisReport('csv','csv')"></g:submitButton>
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


