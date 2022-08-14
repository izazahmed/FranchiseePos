<%-- 
-- File Name: index
-- Description: This page displays index Page of Employee
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@page import="com.tbz.franchisee.BrMstTab"%>
<%@ page import="com.tbz.franchisee.SchemeMst" %>
<!DOCTYPE html>
<html>
	<head>
	<meta name='layout' content='mainerphq' />
	<title>Scheme Master</title>
	<script type="text/Javascript">
		var dd = '';
		$(document).ready(function(){
			applyDataTable('customerTable');
			$("#schemeName").change(function() {
		    	${remoteFunction(
		        	controller:'schemeMst',
		            action:'getSchemeName',
					update :'updateMe',
		            params:'\'schemeNo=\' + this.value')}
	       	});
			$(".editClick td:not(.action)").click(function () {
				dd =$(this).closest('tr').attr('id');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');
		    });
		});
		function getTaxVal(){
			if(dd == ""){
		         alert("Please Select Row");
		    }else{	
			    var parameter = {dd:dd}
				$.ajax({
				 	url: "${request.getContextPath()}/schemeMst/getActualCustomer",
					async : false,
					data : parameter,
					success : function(data){
						console.log(JSON.stringify(data));
						var custname = data.FNAME +" "+ data.MNAME +" "+ data.LNAME;
						
						$("#customerId").val(custname);
					}
				 });
				 $(".editClick").removeClass('highlight');
			 }
		}
		function onSchemeChange() {}
		function onCustomerSubmit(){
			$("#schemeName").val($("#schemeName").val());
			$("#brName").val($("#brName").val());
			document.getElementById("schemeForm").action ="customerShow";
			document.getElementById("schemeForm").submit();
		}
		function onSchemeSubmit(){
			$("#schemeName").val($("#schemeName").val());
			$("#brName").val($("#brName").val());
			$("#customerId").val($("#customerId").val());
			document.getElementById("schemeForm").action ="schemeShow";
			document.getElementById("schemeForm").submit();
		}
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">						
						<div id="create-schemeMst" class="content scaffold-create">
							<h1>Scheme Master</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${schemeMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${schemeMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<form action='auth' method='POST' id='schemeForm' class='cssform' autocomplete='on'>
								<div class="content-bg pull-left">
									<div class="row tab-form">
										<div class="col-md-6 col-sm-6 col-xs-6">
											<label for="concept" class="col-sm-4 control-label"><g:message code="schemeMstForm.schemeName.label" default="Scheme" /></label>
											<div class="col-sm-8 col-md-8 col-xs-8">
												<div class="select-style2"><g:select name="schemeName" id="schemeName" from="${SchemeMst.list()*.schemeName}" noSelection="${['':'Select One...']}" required="true" onchange="onSchemeChange();"/></div>
											</div>
										</div>
									</div>
									<div class="row tab-form">
										<div class="col-md-6 col-sm-6 col-xs-6">
											<label for="concept" class="col-sm-4 control-label"><g:message code="schemeMstForm.segment1.label" default="Branch Name" /></label>
											<div class="col-sm-8 col-md-8 col-xs-8">${brNameInst}
												<div class="select-style2"><g:select name="brName" id="brName" from="${BrMstTab.list()*.brName}" noSelection="${['':'Select One...']}" required="true"/></div>
											</div>
										</div>
									</div>
									<div class="row tab-form">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<label for="concept" class="col-sm-2 control-label"><g:message code="schemeMstForm.description.label" default="Customer" /></label>
											<div class="col-sm-3 col-md-3 col-xs-3">									
												<g:textField class="form-control" name="customerId" id="customerId" class="form-control"  tabindex="3"/>
											</div>
											<div class="col-sm-3 col-md-3 col-xs-3">
												<button id="customer" type="button" class="active_btn" data-toggle="modal" data-target="#customerPopup">Old Customer</button>
												<button type="button" class="active_btn" onclick="onCustomerSubmit();">New Customer</button>
											</div>
										</div>
									</div>
									<div class="row tab-form">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<div class="col-sm-3 col-md-3 col-xs-3">
												<button type="button" class="active_btn" onclick="onSchemeSubmit();">Ok</button>
												<button id="customer" type="button" class="active_btn" data-toggle="modal" data-target="#customerPopup">Cancel</button>
											</div>
										</div>
									</div>
									<g:hiddenField name="schemeName" id="schemeName"/>							
									<g:hiddenField name="brName" id="brName"/>
									<g:hiddenField name="customerId" id="customerId"/>
								</div>
							</form>
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
					<h4 class="modal-title">Customer Details</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="customerTable" class="table">
						<thead>
							<tr>
								<th>Cust Id</th>
								<th>Cust Name</th>
								<th>Phone No.</th>
								<th>City</th>
								<th>Cust Nominee</th>
								<th>Email Id</th>
								<th>DOB</th>
								<th>PAN NO</th>
							</tr>
						</thead>
						<tbody id="prevAdvNoTbdId">
							<g:each in="${customerList}" var="salesPersonInst" status="i">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${salesPersonInst?.CUST_ID}">
									<td name="custId" id="custId">${salesPersonInst?.CUST_ID}</td>
									<td name="fname" id="fname">${salesPersonInst?.FNAME}</td>
									<td name="mobile" id="mobile">${salesPersonInst?.MOBILE}</td>
									<td name="city" id="city">${salesPersonInst?.CITY}</td>									
									<td name="nominee" id="nominee">${salesPersonInst?.CUST_NOMINEE_REL}</td>
									<td name="emailid" id="emailId">${salesPersonInst?.EMAIL_ID}</td>
									<td name="dob" id="dob">${salesPersonInst?.DOB}</td>
									<td name="panNo" id="panNo">${salesPersonInst?.PAN_NO}</td>
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