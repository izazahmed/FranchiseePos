<%-- 
     -- File Name: LabelWiseStockReport
     -- Description: it is used to get the LabelWiseStockReport
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Labelwise Stock Report</title>
<script type="text/Javascript">
var catVal = '';
  function validateLabelWiseStockReport(format,extension) {    
  			
  		document.getElementById('labelWiseStockReportForm').format1.value = format;
		document.getElementById('labelWiseStockReportForm').extension.value = extension;
        $("#labelWiseStockReportForm").submit();
    
   }
   
   function displayTextOnCat(){
 	$(".clickOnCat").removeClass('highlight');
 	var allVal = [];
	allVal = catVal.split(",");	
	var c1 = allVal[0];
	var c2 = allVal[1];
	$("#hidCat").val(c2);
 	$("#category").val(c1);
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
							<h1>Labelwise Stock Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="labelWiseStockReportForm" action="createLabelWiseStockReport">		
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">
													<thead>		
														<tr>		
															<td>Category</td>
															<td>
																<div class="textbtn">
																	<g:textField size="37" name="category" class="form-control"/>
																	<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#catModal">...</button>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateLabelWiseStockReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateLabelWiseStockReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateLabelWiseStockReport('csv','csv')"></g:submitButton>
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