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
<title>Advance Active Report</title>
<script type="text/Javascript">
  function validateAdvanceActiveReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{  	
  		document.getElementById('advanceActiveReportForm').format1.value = format;
		document.getElementById('advanceActiveReportForm').extension.value = extension;
        $("#advanceActiveReportForm").submit();
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
							<h1>Advance Active Report </h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="advanceActiveReportForm" action="createAdvanceActiveReport">
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">
													<thead>
														<tr>
															<td>From Date</td>
															<td>
																<g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate" value="${params?.fromDate}"/>															
															</tr>
														<tr>
															<td>To Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="To Date" name="toDate" value="${params?.toDate}"/>										
														</tr>
														<tr>
															<td>Category</td>
															<td>
																<div class="select-style2">
																	<g:select name="category" from="${categoryListResult}" noSelection="${['':'Select One...']}" />
																</div>
															</td>
														</tr>
														<tr>
															<td>Gold Type</td>
															<td>
																<div class="select-style2">
																	<g:select name="goldType" from="${goldTypeListResult}" noSelection="${['':'Select One...']}" />
																</div>
															</td>
														</tr>
														<tr>
															<td>Pay Mode</td>
															<td>
																<div class="select-style2">
																	<g:select name="payMode" from="${paymodeListResult}" noSelection="${['':'Select One...']}" />
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateAdvanceActiveReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateAdvanceActiveReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateAdvanceActiveReport('csv','csv')"></g:submitButton>
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