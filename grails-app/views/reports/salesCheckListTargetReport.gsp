<%-- 
     -- File Name: salesChecklistWithTargetReport
     -- Description: it is used to get the salesChecklistWithTargetReport
     -- Author(s): CTE 
     -- Date: 23/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 23/05/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Sales Checklist with Target Report</title>
<script type="text/Javascript">
var catVal = '';
$(document).ready(function(){	
	$(".clickOnCat td:not(.action)").click(function () {
		catVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).addClass('highlight');
	});
});  
function displayTextOnCat(){
 	$(".clickOnCat").removeClass('highlight');
 	var allVal = [];
	allVal = catVal.split(",");	
	var c1 = allVal[0];
	var c2 = allVal[1];
	$("#hidCat").val(c2);
 	$("#category").val(c1);
 }	
  function validateSalesChecklistWithTargetReport(format,extension) {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{  	
  		document.getElementById('salesChecklistWithTargetForm').format1.value = format;
		document.getElementById('salesChecklistWithTargetForm').extension.value = extension;
		
        document.getElementById("salesChecklistWithTargetForm").action ="createSalesChecklistWithTargetReport";
     	document.getElementById("salesChecklistWithTargetForm").submit();
  	}  	  	     	
  }
  function validate(){
  		var flag = false;  		  		  		
    	var fromDate = $('#fromDate').val();
    	var toDate = $('#toDate').val();
    	var category = $('#category').val();
         
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
         if(category !== "" && category != null && category !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select Category");
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
							<h1>Sales Checklist with Target Report</h1>
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
							<g:form name="salesChecklistWithTargetForm" id="salesChecklistWithTargetForm">
							<g:hiddenField name="hidCat" />
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">
													<tbody>
														<tr>
															<td>From Date</td>
															<td><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate" />	
														</tr>
														<tr>															
															<td>To Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="To Date" name="toDate" />										
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
														<tr>
															<td>Target Audit</td>
															<td><g:textField name="targetAudit" class="form-control" id="targetAudit" />	
														</tr>
														<tr>															
															<td>Mystery Audit</td>
															<td><g:textField name="mysteryAudit" class="form-control" id="mysteryAudit" />										
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-8">
											<g:hiddenField name="format1"/>
											<g:hiddenField name="extension"/>
											<div class="pull-right rightsapce">
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateSalesChecklistWithTargetReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateSalesChecklistWithTargetReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateSalesChecklistWithTargetReport('csv','csv')"></g:submitButton>
											</div>
										</div>
									</div>
								</div>
							</g:form>
							<%-- CATEGORY POPUP START --%>
							<div class="modal fade" id="catModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Category List</h4>
										</div>
										<div class="modal-body">
											<table class="table">
												<thead>
													<tr>
														<th>Description</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${descList}" var="descListInst" status="i">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnCat" id="${descListInst?.vc_reason_desc},${descListInst?.vc_reason_code}">
															<td name="popDesc" id="popDesc">${descListInst?.vc_reason_desc}</td>															
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="displayTextOnCat();" data-dismiss="modal">OK</button>
											<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- CATEGORY POPUP ENDS --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>