<%-- 
     -- File Name: AdvanceBalanceReport
     -- Description: it is used to get the AdvanceBalanceReport
     -- Author(s): CTE 
     -- Date: 13/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 13/05/2016	   Rohil      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Advance Balance Report</title>
<script type="text/Javascript">
  function validateAdvanceBalanceReport(format,extension) {    
  		document.getElementById('advanceBalanceReportForm').format1.value = format;
		document.getElementById('advanceBalanceReportForm').extension.value = extension;
        $("#advanceBalanceReportForm").submit();
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
							<h1>Advance Balance Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${advanceBalanceInst}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${advanceBalanceInst}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="advanceBalanceReportForm" action="createAdvanceBalanceReport">
								<g:hiddenField name="format1"/>
								<g:hiddenField name="extension"/>
								<div class="pull-right topsapce">
									<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateAdvanceBalanceReport('view','view')"></g:submitButton>
									<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateAdvanceBalanceReport('pdf','pdf')"></g:submitButton>
									<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateAdvanceBalanceReport('csv','csv')"></g:submitButton>
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