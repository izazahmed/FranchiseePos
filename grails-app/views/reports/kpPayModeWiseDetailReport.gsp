<%-- 
     -- File Name: kpPaymodeWiseDetailReport
     -- Description: it is used to get the kpPaymodeWiseDetailReport
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.SchemeMst"%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>KP Paymodewise Details Report</title>
<script type="text/Javascript">
  function validatekpPaymodeWiseDetailReport(format,extension) {    
  	var flag;
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
  		document.getElementById('kpPaymodeWiseDetailReportForm').format1.value = format;
		document.getElementById('kpPaymodeWiseDetailReportForm').extension.value = extension;
		
		document.getElementById("kpPaymodeWiseDetailReportForm").action ="createkpPaymodeWiseDetailReport";
     	document.getElementById("kpPaymodeWiseDetailReportForm").submit();
        //$("#kpPaymodeWiseDetailReportForm").submit();
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
</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-empMst" class="content scaffold-create">
							<h1>KP Paymodewise Details Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="kpPaymodeWiseDetailReportForm" id="kpPaymodeWiseDetailReportForm">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
										<div class="panel panel-default">
												<table class="table">
													<thead>				
														<tr>
															<td>From Date</td>
															<td><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate" /></td>
														</tr>
														<tr>
															<td>To Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="To Date" name="toDate"/></td>
														</tr>
														<tr>
															<td>Scheme</td>
															<td>
																<div class="select-style2">
																	<g:select name="schemeName" from="${SchemeMst.list()*.schemeName}" noSelection="${['':'Select One...']}"/>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validatekpPaymodeWiseDetailReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validatekpPaymodeWiseDetailReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validatekpPaymodeWiseDetailReport('csv','csv')"></g:submitButton>
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