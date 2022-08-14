<%-- 
     -- File Name: moduleListReport
     -- Description: it is used to get the Module List Report for System Admin
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 27/04/2016	   Sachin      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Module List Report</title>
<script type="text/Javascript">

  function validateModuleListReport(format,extension) {    
  		document.getElementById('moduleListReportForm').format1.value = format;
		document.getElementById('moduleListReportForm').extension.value = extension;
		document.getElementById("moduleListReportForm").action ="createModuleListReport";
     	document.getElementById("moduleListReportForm").submit();
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
							<h1>Module List Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:form name="moduleListReportForm" id="moduleListReportForm">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">
													<thead>				
														<tr>
															<td colspan="1"><g:radio name="file" value="screen" checked="ckecked"/>Screen</td>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateModuleListReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateModuleListReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateModuleListReport('csv','csv')"></g:submitButton>
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