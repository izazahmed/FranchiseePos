<%-- 
     -- File Name: salesPanDtlsReport
     -- Description: it is used to get the salesPanDtlsReport
     -- Author(s): CTE 
     -- Date: 10/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 10/05/2016    Izaz       Created File
     --            
--%>

<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Traveller Foreign Currency Report</title>
<script type="text/javascript">  

function validateTravellerForeignCurrencyReport(format,extension) {    
  var flag = "";
  flag = validate();
  if(!flag){
  	return false;
  }else{
    document.getElementById('travellerForeignCurrencyForm').format1.value = format;
    document.getElementById('travellerForeignCurrencyForm').extension.value = extension;
    
     document.getElementById("travellerForeignCurrencyForm").action ="createTravFrgnCrncyReport";
     document.getElementById("travellerForeignCurrencyForm").submit();
    //$("#travellerForeignCurrencyForm").submit();
    //return false;
  }        
}
function validate(){
	  var flag = "false";    
	  var fromDate = $('#fromDate').val();
	  if(fromDate !== "" && fromDate != null && fromDate !== undefined){
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
							<h1>Traveller Foreign Currency Report</h1>
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
							<g:form name="travellerForeignCurrencyForm"
								id="travellerForeignCurrencyForm">
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
													</thead>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-8">
											<g:hiddenField name="format1" />
											<g:hiddenField name="extension" />
											<div class="pull-right rightsapce">
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateTravellerForeignCurrencyReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateTravellerForeignCurrencyReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateTravellerForeignCurrencyReport('csv','csv')"></g:submitButton>
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