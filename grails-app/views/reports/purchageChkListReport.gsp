<%-- 
     -- File Name: purchageChkListReport
     -- Description: it is used to get the purchageChkListReport
     -- Author(s): CTE 
     -- Date: 20/04/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 20/04/2016	   Abhijit     		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>Purchase Check List Report</title>
<script type="text/javascript"> 
	$("#toDte_year").change(function(){						
	var fromDay = $("#FromDte_day").val();    
	var fromMonth = $("#FromDte_month").val();
	var fromYear = $("#FromDte_year").val();		
	var toDay = $("#toDte_day").val();
	var toMonth = $("#toDte_month").val();
	var toYear = $("#toDte_year").val();		
	var fromDate = (fromDay+"/"+fromMonth+"/"+fromYear);
	var toDate =(toDay+"/"+toMonth+"/"+toYear);
	var html = '';
	var parameter = {fromDate:fromDate,toDate:toDate}
	$.ajax({
		 	url: "${request.getContextPath()}/reports/getPurity",
		 	async : false,
			data : parameter,
			success : function(data){
							
					for(var i=0;i<data.PURITY.length;i++){
						html+='<select name="purityName" id="purity">'+
							'<option value="Select One...">Select One...</option>'+
							'<option value="'+data.PURITY+'">'+data.PURITY+'</option>'+
							'</select>';
					}
					$(".purityCls").html(html);
					}
		 	})
	});				
	$(document).ready(function(){
	    $(".allOPCls").click(function(){
	       	$(".OPCls").attr("disabled","");
	    });			    
	    $(".specificOPCls").click(function(){
	   		$(".OPCls").removeAttr("disabled");
	    });
	    $(".allPurCls").click(function(){
	      $(".purityCls").attr("disabled","");
	    });			    
	    $(".specPurCls").click(function(){
	   		$(".purityCls").removeAttr("disabled");
	    });
	    $(".allCls").click(function(){
	       $(".itemCls").attr("disabled","");
	    });			    
	    $(".specificCls").click(function(){
	   		$(".itemCls").removeAttr("disabled");
	    });
    });
  function validatePurchageReport() { 		 
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{ 		
  		document.getElementById("PurchageChkListReportForm").action ="createPurchageChkListReport";
        document.getElementById("PurchageChkListReportForm").submit();
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
							<h1>Purchase Check List Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
											<g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="PurchageChkListReportForm" id="PurchageChkListReportForm">
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<div class="panel panel-default">
												<table class="table">
													<tbody>
															<tr><td colspan="2"><b>Specification</b></td></tr>	
															<tr>
																<td nowrap="nowrap">From Date</td>
																<td nowrap="nowrap">
																	<g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/>
																</td>
															</tr>
															<tr>
																<td nowrap="nowrap">End Date</td>
																<td nowrap="nowrap">
																	<g:textField class="form-control endDateStr" placeholder="End Date" name="toDate"/>
																	<g:hiddenField name="vcCompCode" value="02" />
																	<g:hiddenField name="brCode" value="88" />
																</td>
															</tr>
															<tr>
																<td class="action">Category</td>
																<td>
																	<div class="select-style2">
																		<g:select name="category" from="${categoryList?.VC_REASON_DESC}" class="form-control" noSelection="${['':'Select One...']}" />
																	</div>
																</td>
															</tr>
															<tr>
																<td>Gold Type</td>
																<td>
																	<div class="select-style2">
																		<select name="goldType" id="goldType" class="form-control">
																			<option value="">Select One...</option>
																			<option value="SB">Standard</option>
																			<option value="N">Non-Standard</option>
																			<option value="OG">Old Gold</option>
																			<option value="OS">Old Silver</option>
																		</select>
																	</div>
																</td>
															</tr>
															<tr>
																<td>Type</td>
																<td>
																	<div class="select-style2">
																		<select name="type" class="form-control" id="type">
																			<option value="">Select One...</option>
																			<option value="T">Tbz</option>
																			<option value="C">Non-TBz</option>
																			<option value="ALL">ALL</option>
																		</select>
																	</div>
																</td>
															</tr>
															<tr align="center"><td colspan="2"><g:submitButton name="Submit" value="VIEW" class="active_btn" onclick="return validatePurchageReport()"></g:submitButton></td></tr>
															<tr>
																<td>
																	<table class="table">
																		<tr><td colspan="2"><b>Purity</b></td></tr>
																		<tr>
																			<td colspan="2"><g:radio name="purity" class="allPurCls" value="" checked="ckecked" />ALL</td>
																		</tr>
																		<tr>
																			<td><g:radio name="purity" class="specPurCls" value="" />Specific</td>
																			<td>
																				<g:select name="purityName" disabled="" class="purityCls" from="" noSelection="${['':'Select One...']}" />
																			</td>
																		</tr>
																		</table>
																	</td>
																	<td>
																		<table class="table">
																			<tr><td colspan="2"><b>Item</b></td></tr>
																			<tr>
																				<td colspan="2"><g:radio name="item" class="allCls" value="all" checked="ckecked" />ALL</td>
																			</tr>
																			<tr>
																				<td><g:radio name="item" class="specificCls" value="specific" />Specific</td>
																				<td>
																					<g:select name="itemName" class="itemCls" from="" disabled="" noSelection="${['':'Select One...']}" />
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table class="table">
																			<tr><td colspan="2"><b>Item</b></td></tr>
																			<tr>
																				<td colspan="2"><g:radio name="selBtn" class="scrCls" value="" checked="ckecked" />Screen</td>
																			</tr>
																			<tr>
																				<td><g:radio name="selBtn" class="priCls" value="" />Printer</td>
																				<td>
																					No. of Coppies <g:textField name="copy" class="form-control" value="" />
																				</td>
																			</tr>
																			<tr>
																				<td><g:radio name="selBtn" class="fileCls" value="" />File</td>
																				<td>
																					File Name <g:textField class="form-control" name="fname" value="" />
																				</td>
																			</tr>
																		</table>
																	</td>
																	<td>
																		<table class="table">
																			<tr>
																				<td colspan="2"><g:radio name="op" class="allOPCls" value="" checked="ckecked" />ALL</td>
																			</tr>
																			<tr>
																				<td><g:radio name="op" class="specificOPCls" value="" />Specific</td>
																				<td>
																					Supplier <g:select class="OPCls" name="opName" disabled="" from="${supplierList?.VC_SUPPLIER_NAME}" noSelection="${['':'Select One...']}" />
																				</td>
																			</tr>
																			
																		</table>
																	</td>
																</tr>
														</tbody>
													</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</g:form>
						</div>
					</div>
				</div>
			</div>
</body>
</html>