<%-- 
     -- File Name: monthwisePendingInstallmentReport
     -- Description: it is used to get the monthwisePendingInstallmentReport
     -- Author(s): CTE 
     -- Date: 10/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 10/05/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.SchemeMst" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Monthwise Pending Installment Report</title>
<script type="text/javascript">  
var catVal = '';
$(document).ready(function(){	

	applyDataTable('catTblId');
	$(".clickOnCat td:not(.action)").click(function () {
		catVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).addClass('highlight');
	});
	
	$("#category").attr('disabled', true);	
	$("#butIdCat").attr('disabled', true);
	
	$("#mid").attr('disabled', true);	
}); 

function displayTextOnCat(){
 	$(".clickOnCat").removeClass('highlight');
 	$("#category").val(catVal);
 }	
 
 function modifyCategoryFields(val){
	if(val=='specific'){
		$("#category").attr('disabled', false);	
		$("#butIdCat").attr('disabled', false);
	}else{
		$("#category").attr('disabled', true);	
		$("#butIdCat").attr('disabled', true);
	}
}
function modifyMidFields(val){
	if(val=='specific'){
		$("#mid").attr('disabled', false);	
	}else{
		$("#mid").attr('disabled', true);	
	}
}

  function validateMonthwisePendingInstallmentReport(format,extension) {    
  		document.getElementById('monthwisePendingInstallmentReportForm').format1.value = format;
		document.getElementById('monthwisePendingInstallmentReportForm').extension.value = extension;
		
		document.getElementById("monthwisePendingInstallmentReportForm").action ="createMonthwisePendingInstallmentReport";
     	document.getElementById("monthwisePendingInstallmentReportForm").submit();S
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
							<h1>Monthwise Pending Installment Report</h1>
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
							<g:form name="monthwisePendingInstallmentReportForm" id="monthwisePendingInstallmentReportForm">
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">
													<thead>				
														<tr>
															<td>Branch</td>
															<td colspan="4"><div class="select-style2"><g:select name="brName" from="${BrMstTab.list()*.brName}" noSelection="${['':'Select One...']}"/></div></td>
														</tr>
														<tr>
															<td>Scheme</td>
															<td colspan="4"><div class="select-style2"><g:select name="brName" from="${SchemeMst.list()*.schemeName}" noSelection="${['':'Select One...']}"/></div></td>
														</tr>							
														<tr>
															<td>Year</td>
															<td><div class="select-style2"><g:select name="brName" from="${SchemeMst.list()*.schemeName}" noSelection="${['':'Select One...']}"/></div></td>
															<td>Month</td>
															<td><div class="select-style2"><g:select name="brName" from="${SchemeMst.list()*.schemeName}" noSelection="${['':'Select One...']}"/></div></td>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateMonthwisePendingInstallmentReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateMonthwisePendingInstallmentReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateMonthwisePendingInstallmentReport('csv','csv')"></g:submitButton>
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