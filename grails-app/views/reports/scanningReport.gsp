<%-- 
     -- File Name: scanningReport
     -- Description: it is used to get the data of scanningReport
     -- Author(s): CTE 
     -- Date: 10/05/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 10/05/2016	   Izaz      	Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Scanning Report</title>
<script type="text/javascript"> 
var catVal = '';
var purVal = '';
var itemCodeVal = '';
$(document).ready(function(){	

	applyDataTable('catTblId');
	applyDataTable('purTblId');
	applyDataTable('itemCodeTblId');
	
	$("#purModal").hide();
	$("#itemCodeModal").hide();	
	
	$(".clickOnCat td:not(.action)").click(function () {
		catVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
	
	$(".clickOnPur td:not(.action)").click(function () {
		purVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});
	
	$(".clickOnItemCode td:not(.action)").click(function () {
		itemCodeVal =$(this).closest('tr').attr('id');
		$(".highlight").removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
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
function getPurity(){
	var cat = $("#category").val();
	if(cat !== "" && cat != null && cat !== undefined){
	}else{
		$("#purModal").hide();
	}
}
function displayTextOnPur(){
 	$(".clickOnPur").removeClass('highlight');
 	$("#purity").val(purVal);
 }	
 
 function displayTextOnItemCode(){
 	$(".clickOnItemCode").removeClass('highlight');
 	$("#itemCode").val(itemCodeVal);
 }	
function validateScanningReport(format,extension){
	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{			   	
	 document.getElementById('scanningReportForm').format1.value = format;
	 document.getElementById('scanningReportForm').extension.value = extension;
	 
	 document.getElementById("scanningReportForm").action ="createScanningReport";
     document.getElementById("scanningReportForm").submit();
  }
 }
 function validate(){
  		var flag = "false";  	
    	var category = $('#category').val();
    	var purity = $('#purity').val();
    	var itemCode = $('#itemCode').val();    	    	
    	
         if(category !== "" && category != null && category !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select category");
             return false;
         }
         if(purity !== "" && purity != null && purity !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select purity");
             return false;
         }    
         if(itemCode !== "" && itemCode != null && itemCode !== undefined){
             flag = true;
         }else{
         	 flag = false;	
             alert("Please Select itemCode");
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
							<h1>Scanning Report</h1>
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
							<g:form name="scanningReportForm" id="scanningReportForm">
								<g:hiddenField name="hidCat" />
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
																	<g:textField class="form-control" size="37" name="category"/>
																	<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#catModal">...</button>
																</div>
															</td>
														</tr>
														<tr>
															<td>Purity</td>
															<td>
																<div class="textbtn">
																	<g:textField class="form-control" size="37" name="purity"/>
																	<button id="butIdPur" type="button" class="active_btn" data-toggle="modal" onclick="getPurity();" data-target="#purModal">...</button>
																</div>
															</td>
														</tr>	
														<tr>
															<td>ItemCode</td>
															<td>
																<div class="textbtn">
																	<g:textField size="37" class="form-control" name="itemCode" id="itemCode" />
																	<button id="butIdCat" type="button" class="active_btn" data-toggle="modal" data-target="#itemCodeModal">...</button>
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
										<div class="pull-right topsapce">
											<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateScanningReport('view','view')"></g:submitButton>
											<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateScanningReport('pdf','pdf')"></g:submitButton>
											<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateScanningReport('csv','csv')"></g:submitButton>
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
											<table class="table" id="catTblId" width="100%">
												<thead>
													<tr>
														<th>Category</th>
														<th>Description</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${catList}" status="i" var="catPopUpDataInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnCat" id="${catPopUpDataInst.VC_REASON_DESC},${catPopUpDataInst.VC_CATEGORY}">
															<td name="catPopDesc" id="catPopDesc">${catPopUpDataInst.VC_CATEGORY}</td>
															<td name="catPopDesc" id="catPopDesc">${catPopUpDataInst.VC_REASON_DESC}</td>
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
						<%-- PURITY POPUP START --%>
						<div class="modal fade" id="purModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Purity List</h4>
										</div>
										<div class="modal-body">
											<div class="alert alert-danger" role="alert" id="purError" style="display: none;"></div>
											<table width="100%" id="purTblId" class="table">
												<thead>
													<tr>
														<th>Purity</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${purList}" status="i" var="purInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnPur" id="${purInst.VC_PURITY}">
															<td name="purPop" id="purPop">${purInst.VC_PURITY}</td>
														</tr>
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
												<button type="button" class="active_btn" onclick="displayTextOnPur();" data-dismiss="modal">OK</button>
												<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
					<%-- PURITY POPUP ENDS --%>
					<%-- ITEMCODE POPUP START --%>
							<div class="modal fade" id="itemCodeModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Item Code List</h4>
										</div>
										<div class="modal-body">
											<div class="alert alert-danger" role="alert" id="itemCodeError" style="display: none;"></div>
											<table width="100%" id="itemCodeTblId" class="table">
												<thead>
													<tr>
														<th>Item Code</th>
													</tr>
												</thead>
												<tbody id="catTblBdId">
													<g:each in="${itemCodeList}" status="i" var="itemCodeInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'} clickOnItemCode" id="${itemCodeInst?.VC_ITEM_CODE}">
															<td name="popItemCode" id="popItemCode">${itemCodeInst?.VC_ITEM_CODE}</td>
														</tr>
														
													</g:each>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="active_btn" onclick="displayTextOnItemCode();" data-dismiss="modal">OK</button>
											<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						<%-- CUSTOMER POPUP ENDS --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>