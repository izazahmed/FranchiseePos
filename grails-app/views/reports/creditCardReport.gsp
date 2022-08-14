<%-- 
     -- File Name: creditCardReport
     -- Description: it is used to get the creditCardReport
     -- Author(s): CTE 
     -- Date: 30/03/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 30/03/2016	   Abhijit     		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab"%>
<%@ page import="com.tbz.franchisee.HdCash"%>
<%@ page import="com.tbz.franchisee.MstCode"%>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Credit Card Report</title>
<script type="text/Javascript">

function validateCreditCardReport(format,extension) { 
	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{
  		document.getElementById('creditCardReportForm').format1.value = format;
		document.getElementById('creditCardReportForm').extension.value = extension;
		
		document.getElementById("creditCardReportForm").action ="createCreditCardReport";
     	document.getElementById("creditCardReportForm").submit();
        //$("#creditCardReportForm").submit();
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
							<h1>Credit Card Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li
											<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
												error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="creditCardReportForm" id="creditCardReportForm">
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">
													<thead>
														<tr>
															<td>From Date</td>
															<td>
																<g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/>
																<g:hiddenField name="brCode" value="88" />
															</td>
														</tr>
														<tr>
															<td>End Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="End Date" name="toDate"/>
																<g:hiddenField name="vcCompCode" value="02" />
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateCreditCardReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateCreditCardReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateCreditCardReport('csv','csv')"></g:submitButton>
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