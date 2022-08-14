<%-- 
     -- File Name: advTransferReport
     -- Description: it is used to get the advTransferReport
     -- Author(s): CTE 
     -- Date: 04/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 04/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.SchemeMst" %>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Advance Transfer Report</title>
<script type="text/javascript"> 
  function validateAdvTransferReport(format,extension) {      
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{	
        document.getElementById('advTransferReportForm').format1.value = format;
		document.getElementById('advTransferReportForm').extension.value = extension;
		
        document.getElementById("advTransferReportForm").action ="createAdvTransferReport";
     	document.getElementById("advTransferReportForm").submit();
        
        //$("#advTransferReportForm").submit();
        //return false;
  	}  	  	     	
  }
  function validate(){
  		var flag = "false";  		    				  		
    	var fromDate = $('#fromDate').val();
    	var toDate = $('#toDate').val();	    	      	
    	var brName = $('#brName').val();  		
  		var trType = $('#trType').val();	    
  		
  		 if(brName !== "" && brName != null && brName !== undefined){
             flag="true";
         }else{
         	 flag="false";	
             alert("Please Select Branch");
             return false;
         }    	 
    	 if(trType !== "" && trType != null && trType !== undefined){
             flag="true";
         }else{
         	 flag="false";	
             alert("Please Select Transaction Type");
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
						<div id="create-empMst" class="content scaffold-create">
							<h1>Advance Transfer Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="advTransferReportForm" id="advTransferReportForm">		
							<div class="content-bg pull-left">
								<div class="row">
									<div class="col-md-8">
										<div class="panel panel-default">
											<table class="table">			
												<thead>
													<tr>
														<td>Branch</td>
														<td>
															<div class="select-style2">
																<select name="brName" id="brName">
																	<g:each in="${branchResult}" status="i" var="branchData">
																		<option value="${branchData.key}">${branchData.value}</option>							
																	</g:each>
																</select>
															</div>
														</td>
													</tr>
													<tr>
														<td>Transaction Type</td>
														<td><div class="select-style2"><g:select name="trType" from="${transactionTypeResult}" noSelection="${['':'Select One...']}"/></div></td>
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
										<div class="rightsapce pull-right">
											<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateAdvTransferReport('view','view')"></g:submitButton>
											<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateAdvTransferReport('pdf','pdf')"></g:submitButton>
											<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateAdvTransferReport('csv','csv')"></g:submitButton>
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