<%-- 
     -- File Name: franchiseeIdealStockReport
     -- Description: it is used to get the franchiseeIdealStockReport
     -- Author(s): CTE 
     -- Date: 09/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 09/05/2016	   Sachin      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.XxtbzItemMaster" %>

<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Franchisee Ideal Stock Report</title>
<script type="text/Javascript">

  function validatefranchiseeIdealStockReport(format,extension) {    
  	var flag;
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{		
  		document.getElementById('franchiseeIdealStockReportForm').format1.value = format;
		document.getElementById('franchiseeIdealStockReportForm').extension.value = extension;
        $("#franchiseeIdealStockReportForm").submit();
        return false;
  	}  	  	     	
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
							<h1>Franchisee Ideal Stock Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="franchiseeIdealStockReportForm" action="createfranchiseeIdealStockReport">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
										<div class="panel panel-default">
												<table class="table">
													<thead>				
														<tr>
															<td>Branch Name</td>
															<td><div class="select-style2"><g:select name="brName" id="brName" from="${BrMstTab.list()*.brName}" required="true"/></div></td>
														</tr>
														<tr>
															<td>Purity</td>
															<td><div class="select-style2"><g:select name="purity" id="purity" from="${XxtbzItemMaster.list()*.purity}" required="true"/></div></td>
														</tr>																								
														<tr>
															<td>Item</td>
															<td><div class="select-style2"><g:select name="item" id="item" from="${XxtbzItemMaster.list()*.product}" required="true"/></div></td>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validatefranchiseeIdealStockReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validatefranchiseeIdealStockReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validatefranchiseeIdealStockReport('csv','csv')"></g:submitButton>
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