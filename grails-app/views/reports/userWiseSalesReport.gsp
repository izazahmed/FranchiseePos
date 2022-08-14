<%-- 
     -- File Name: userWiseSalesReport
     -- Description: it is used to get the userWiseSalesReport
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.MstReason" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Userwise Sales Report</title>
<script type="text/Javascript">
  function validateUserWiseSalesReport(format,extension) {
  	document.getElementById('userWiseSalesReportForm').format1.value = format;
	document.getElementById('ecsSuserWiseSalesrtForm').extension.value = extension;
	
	document.getElementById("userWiseSalesReportForm").action ="createUserWiseSalesReport";
    document.getElementById("userWiseSalesReportForm").submit();
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
							<h1>Userwise Sales Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="userWiseSalesReportForm" id="userWiseSalesReportForm">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">
													<thead>				
														<tr>
															<td>Branch</td>
															<td><div class="select-style2"><g:select name="brName" from="${BrMstTab.list()*.brName}" noSelection="${['':'Select One...']}"/></div></td>
														</tr>
														<tr>
															<td>Category</td>
															<g:set value="${MstReason.findAllByVcReasonFlg('C')}" var="reasonFlag"></g:set>
															<td>
																<div class="select-style2"><g:select name="category" from="${reasonFlag?.vcReasonDesc}" noSelection="${['':'Select One...']}"/></div>
																<g:each in="${reasonFlag}" var="reasonFlagInstance" status="rci">
																</g:each>
															</td>
														</tr>						
														<tr>
															<td>From Date</td>
															<td><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/></td>
														</tr>
														<tr>
															<td>To Date</td>
															<td><g:textField class="form-control endDateStr" placeholder="To Date" name="toDate"/></td>
														</tr>
														<tr>
															<td>Voucher Number</td>
															<td><g:textField class="form-control" placeholder="Voucher Number" name="voucherNo"/></td>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateUserWiseSalesReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateUserWiseSalesReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateUserWiseSalesReport('csv','csv')"></g:submitButton>
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