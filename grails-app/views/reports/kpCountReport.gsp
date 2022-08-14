<%-- 
     -- File Name: kpCountReport
     -- Description: it is used to get the kpCountReport
     -- Author(s): CTE 
     -- Date: 10/04/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 10/04/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.SchemeMst" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>KP Count Report</title>
<script type="text/Javascript">
 $(document).ready(function(){
        $("#schemeNo").change(function() {
            ${remoteFunction(
                controller:'reports',
                action:'getSchemeName',
				update :'updateMe',
                params:'\'schemeNo=\' + this.value')}
        });   
    });
    
  function validateKpCountReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{
  		document.getElementById('kpCountReportForm').format1.value = format;
		document.getElementById('kpCountReportForm').extension.value = extension;
		
		document.getElementById("kpCountReportForm").action ="createKpCountReport";
     	document.getElementById("kpCountReportForm").submit();
        //$("#kpCountReportForm").submit();
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
							<h1>KP Count Report</h1>
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
							<g:form name="kpCountReportForm" id="kpCountReportForm">		
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
															<td>To Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="To Date" name="toDate"/></td>
														</tr>
														<tr>
														<td>Scheme No</td>
														<td>
															<div class="select-style2">
																<g:select name="schemeNo" from="${SchemeMst.list()*.schemeNo}" noSelection="${['':'Select One...']}"/>
															</div>
														</td>
													</tr>
													<tr>
														<td>Scheme Name</td>
														<td><form action="getSchemeName"><div id="updateMe"><g:render template="description"/></div></form>
														</td>
													</tr>				
													<tr>
														<td>Branch</td>
														<td>
															<div class="select-style2">
																<g:select name="brName" from="${BrMstTab.list()*.brName}" noSelection="${['':'Select One...']}"/>
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
											<div class="pull-right rightsapce">
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateKpCountReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateKpCountReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateKpCountReport('csv','csv')"></g:submitButton>
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