<%-- 
     -- File Name: ecsStatusReport
     -- Description: it is used to get the EcsStatusReport
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<%@ page import="com.tbz.franchisee.SchemeMst" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>ECS status Report</title>
<script type="text/Javascript">

var dd=""
$(document).ready(function(){
		$(".editClick td:not(.action)").click(function () {
			dd =$(this).closest('tr').attr('id');
			$('.highlight').removeClass('highlight');
			$(this).closest('tr').addClass('highlight');
	    });
	});
  function validateEcsStatusReport(format,extension) {
  	document.getElementById('ecsStatusReportForm').format1.value = format;
	document.getElementById('ecsStatusReportForm').extension.value = extension;
	document.getElementById("ecsStatusReportForm").action ="createEcsStatusReport";
    document.getElementById("ecsStatusReportForm").submit();
  }
   
   function displayTextOnPayMode(){
 	$(".editClick").removeClass('highlight');
 	$("#paymode").val(catVal);
 }	
 
 function getTaxVal(){
	 var html = ''; 
	if(dd == ""){
         alert("Please Select Row");
    }else{
		    var parameter = {dd:dd}
			$.ajax({
			 	url: "${request.getContextPath()}/ecsDateEntry/getExactCustomerData",
				async : false,
				data : parameter,
				success : function(data){
					$("#custId").val(data.VC_CUST_ID);
					for(var i=0;i<data.CNT;i++){
						html +='<tr>'+
		 				'<td>'+data.NU_INSTALL_FOR[i]+'</td>'+
		 				'<td></td>'+
		 				'<td>'+data.VC_FIELD2[i]+'</td>'+
		 				'<td>'+data.DT_FIELD2[i]+'</td>'+
		 				'<td>'+data.NU_AMOUNT[i]+'</td>'+
		 				'<td>'+data.CH_STAGE[i]+'</td>'+
		 				'<td></td>'+
		 				'</tr>';
		 			}
					 $("#ecsTable tbody").empty();
		 			$("#ecsTable tbody").append(html);
				}
			 });
			 $(".editClick").removeClass('highlight');
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
							<h1>ECS status Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="ecsStatusReportForm" id="ecsStatusReportForm">		
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
															<td>Scheme</td>
															<td><div class="select-style2"><g:select name="schemeName" from="${SchemeMst.list()*.schemeName}" noSelection="${['':'Select One...']}"/></div></td>
														</tr>	
														<tr>
															<td>Customer Id</td>
															<td>
																<div class="textbtn">
																	<g:textField name="custId" class="form-control" value="${customerInstance?.CUST_NAME}" tabindex="1" />
																	<button id="custMst" type="button" class="active_btn" data-toggle="modal" data-target="#customerPopup">...</button>
																</div>
															</td>														
														</tr>						
														<tr>
															<td>From Date</td>
															<td><g:textField class="form-control startDateStr" placeholder="From Date" name="fromDate"/></td>
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
												<g:submitButton name="Submit" value="View" class="active_btn" onclick="return validateEcsStatusReport('view','view')"></g:submitButton>
												<g:submitButton name="Submit" value="PDF" class="active_btn" onclick="return validateEcsStatusReport('pdf','pdf')"></g:submitButton>
												<g:submitButton name="Submit" value="CSV" class="active_btn" onclick="return validateEcsStatusReport('csv','csv')"></g:submitButton>
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
	<div class="modal fade" id="customerPopup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Select Customer</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="customer" class="table">
						<thead>
							<tr>
								<th>Customer ID</th>
								<th>Customer Name</th>
							</tr>
						</thead>
						<tbody id="prevAdvNoTbdId">
							<g:each in="${customerList}" status="i" var="customerInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${customerInst?.CUST_ID}">
									<td name="approvalId" id="approvalId">
										${customerInst?.CUST_ID}
									</td>
									<td name="approvalName" id="approvalName">
										${customerInst?.CUST_NAME}
									</td>									
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="getTaxVal();" data-dismiss="modal">OK</button>
					<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>