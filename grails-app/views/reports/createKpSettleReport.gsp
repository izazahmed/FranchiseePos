<%-- 
     -- File Name: createKpSettleReport
     -- Description: it is used to display Check Clearance Report Data
     -- Author(s): CTE 
     -- Date: 15/02/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 15/02/2016	   Izaz      		Created File
     --            
--%>
<%@ page import="com.tbz.franchisee.DtCrossAdvSettle" %>
<%@ page import="com.tbz.franchisee.BrMstTab" %>
<!DOCTYPE html>
<html>
<head>
<meta name='layout' content='mainerphq' />
<title>KP Settle Report</title>
<script type="text/Javascript">
 $(document).ready(function(){ 	
        //$("input[name*='schemeNo']").change(function() {
        $("#schemeNo").change(function() {
            ${remoteFunction(
                controller:'reports',
                action:'getSchemeName',
				update :'updateMe',
                params:'\'schemeNo=\' + this.value')}
        });       
    });
    
  function validateKpSettleReport() {    
  	var flag = "";
  	flag = validate();
  	if(!flag){
  		return false;
  	}else{ 		
  		document.getElementById("kpSettleReportForm").action ="createKpSettleReport";
        document.getElementById("kpSettleReportForm").submit();
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
							<h1>KP Settle Report</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
											<table class="table">
												<thead>
													<tr>
														<td><strong>Cust Id</strong></td>						
														<td><strong>Br Code</strong></td>
														<td><strong>Reg Date</strong></td>
														<td><strong>Settle Date</strong></td>
														<td><strong>Name</strong></td>
														<td><strong>Settle Amount</strong></td>
														<td><strong>Type Of Payment</strong></td>
														<td><strong>Invoice no.</strong></td>
														<td><strong>Invoice Amount</strong></td>
														<td><strong>Bonus Amount</strong></td>
														<td><strong>Cust A/C</strong></td>
													</tr>
												</thead>
												<tbody>			
													<g:each in="${kpSettleVal}" status="i" var="kpSettleValInst">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td>${kpSettleValInst?.CUST_ID}</td>
															<td>${kpSettleValInst?.BR_CODE}</td>	
															<td>${kpSettleValInst?.REG_DATE}</td>
															<td>${kpSettleValInst?.DT_VOUCHER_DATE}</td>
															<td>${kpSettleValInst?.NAME}</td>
															<td>${kpSettleValInst?.NU_SETT_AMT}</td>
															<td>${kpSettleValInst?.TERMS_OF_PAYMENT}</td>
															<td>${kpSettleValInst?.NU_MC_NO}</td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
													</g:each>
												</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</body>
</html>