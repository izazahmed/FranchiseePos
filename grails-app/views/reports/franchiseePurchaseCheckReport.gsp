<%-- 
     -- File Name: franchiseePurchaseCheckReport
     -- Description: it is used to get the franchiseePurchaseCheckReport
     -- Author(s): CTE 
     -- Date: 09/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 09/05/2016	   Sachin      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Franchisee Checklist Report</title>
<script type="text/Javascript">
  function validatefranchiseePurchaseCheckReport(format,extension) {    
  	var flag;
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
  		document.getElementById('franchiseePurchaseCheckReportForm').format1.value = format;
		document.getElementById('franchiseePurchaseCheckReportForm').extension.value = extension;
        $("#franchiseePurchaseCheckReportForm").submit();
        return false;
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
						<div id="create-empMst" class="content scaffold-create">
							<h1>Franchisee Checklist Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="franchiseePurchaseCheckReportForm" action="createFranchiseePurchaseCheckReport">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
													<table class="table">
														<thead>				
															<tr>
																<td>From Date</td>
																<td colspan="2"><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/></td>
															</tr>
															<tr>
																<td>To Date</td>
																<td colspan="2"><g:textField class="form-control endDateStr" placeholder="To Date" name="toDate" /></td>
															</tr>																								
															<tr>
																<td>Category</td>
																<td>
																	<div class="textbtn">
																		<g:textField size="37" name="category" class="form-control"/>
																		<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#catModal">...</button>
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
													<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validatefranchiseePurchaseCheckReport('view','view')"></g:submitButton>
													<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validatefranchiseePurchaseCheckReport('pdf','pdf')"></g:submitButton>
													<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validatefranchiseePurchaseCheckReport('csv','csv')"></g:submitButton>
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