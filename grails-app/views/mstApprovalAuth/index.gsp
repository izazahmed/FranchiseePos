<%-- 
-- File Name: index
-- Description: This page displays index Page of Scheme Master
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@page import="com.tbz.franchisee.MstApprovalAuth"%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Scheme Master</title>
	<script type="text/javascript">
		var tempApprovalId = '';
		var dd = '';
		$(document).ready(function(){
			applyDataTable('salesPerson');
			applyDataTable('editScheme');
			applyDataTable('viewScheme');
			$("#createImgId").click(function(){
				$('.enableDisable').removeAttr('disabled');				
				$('#nav2').show();
				$('#nav1').hide();
			});
			$(".editClick td:not(.action)").click(function () {
				dd = $(this).closest('tr').attr('id');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');
		    });
		});
		function editSubmit(){
			if(dd == ""){
		         alert("Please Select Row");
		    }else{	
		    	$("#editPopup .close").click(); 	
			    var parameter = {dd:dd};
			    $("#spinner").show();
				$.ajax({
				 	url: "${request.getContextPath()}/mstApprovalAuth/getActualSchemData/",
					async : false,
					data : parameter,
					success : function(data){	
						console.log(JSON.stringify(data));
						$("#spinner").hide();
						$("#vcApprovalId").val(data.VC_APPROVAL_ID);
		
						$("#vcApprovalId").val(data.VC_APPROVAL_ID);
						console.log(tempApprovalId);
						
						$("#vcEmpCode").val(data.VC_EMP_CODE);
						$("#vcApprovalName").val(data.VC_APPROVAL_NAME);
						$("#dtStartDate").val(data.DT_START_DATE);
						$("#dtEndDate").val(data.DT_END_DATE);
						$("#nuApprovalAuth").val(data.NU_APPROVAL_AUTH);
						
						if(data.VC_EMP_TYPE == 'M')
							$("#vcEmpType").val("MANAGER");
						else if(data.VC_EMP_TYPE == 'P')
							$("#vcEmpType").val("PRO");
						else if(data.VC_EMP_TYPE == 'S')
							$("#vcEmpType").val("SALES OFFICER");
						else if(data.VC_EMP_TYPE == 'CS')
							$("#vcEmpType").val("COUNTER SALES");
						else
							$("#vcEmpType").val();
						if(data.VC_GENDER == 'M')
							$("#vcGender").val("MALE");
						else
							$("#vcGender").val("FEMALE");
						if(data.VC_DEPTT == 0)
							$("#vcDeptt").val("ORDER DEPTT");
						else if(data.VC_DEPTT == 1)
							$("#vcDeptt").val("EARING");
						else if(data.VC_DEPTT == 2)
							$("#vcDeptt").val("BRACELET");
						else if(data.VC_DEPTT == 3)
							$("#vcDeptt").val("NECKLACE");
						else if(data.VC_DEPTT == 4)
							$("#vcDeptt").val("CHAIN");
						else if(data.VC_DEPTT == 5)
							$("#vcDeptt").val("PENDANT");
						else if(data.VC_DEPTT == 6)
							$("#vcDeptt").val("KANGAN");
						else if(data.VC_DEPTT == 7)
							$("#vcDeptt").val("SET");
						else if(data.VC_DEPTT == 8)
							$("#vcDeptt").val("DIAMOND");
						else if(data.VC_DEPTT == 9)
							$("#vcDeptt").val("CUPID DIAMOND");
						else if(data.VC_DEPTT == 10)
							$("#vcDeptt").val("ZAVERAT");
						else if(data.VC_DEPTT == 11)
							$("#vcDeptt").val("PEARLS");
						else if(data.VC_DEPTT == 12)
							$("#vcDeptt").val("BRANCH");
						else
							$("#vcDeptt").val("");
						if(data.VC_FIELD3 == 'G')
							$("#vcField3").val("GOLD");
						else
							$("#vcField3").val("DIAMOND");
						if(data.CH_ACTIVE == 'Y')
							$("#chActive").val("ACTIVE");
						else
							$("#chActive").val("INACTIVE");
					}
			});
			$(".editClick").removeClass('highlight');
	 	}
	}
	function viewSubmit(){
		if(dd == ""){
	         alert("Please Select Row");
	    }else{	
		    var parameter = {dd:dd};
		    $("#spinner").show();
			$.ajax({
			 	url: "${request.getContextPath()}/mstApprovalAuth/getActualSchemData/",
				async : false,
				data : parameter,
				success : function(data){
					$("#spinner").hide();	
					console.log(JSON.stringify(data));
					$("#vcApprovalId").val(data.VC_APPROVAL_ID);
					$("#vcEmpCode").val(data.VC_EMP_CODE);
					$("#vcApprovalName").val(data.VC_APPROVAL_NAME);
					$("#dtStartDate").val(data.DT_START_DATE);
					$("#dtEndDate").val(data.DT_END_DATE);
					$("#nuApprovalAuth").val(data.NU_APPROVAL_AUTH);
					if(data.VC_EMP_TYPE == 'M')
						$("#vcEmpType").val("MANAGER");
					else if(data.VC_EMP_TYPE == 'P')
						$("#vcEmpType").val("PRO");
					else if(data.VC_EMP_TYPE == 'S')
						$("#vcEmpType").val("SALES OFFICER");
					else if(data.VC_EMP_TYPE == 'CS')
						$("#vcEmpType").val("COUNTER SALES");
					else
						$("#vcEmpType").val();
					if(data.VC_GENDER == 'M')
						$("#vcGender").val("MALE");
					else
						$("#vcGender").val("FEMALE");
					if(data.VC_DEPTT == '0')
						$("#vcDeptt").val("ORDER DEPTT");
					else if(data.VC_DEPTT == '1')
						$("#vcDeptt").val("EARING");
					else if(data.VC_DEPTT == '2')
						$("#vcDeptt").val("BRACELET");
					else if(data.VC_DEPTT == '3')
						$("#vcDeptt").val("NECKLACE");
					else if(data.VC_DEPTT == '4')
						$("#vcDeptt").val("CHAIN");
					else if(data.VC_DEPTT == '5')
						$("#vcDeptt").val("PENDANT");
					else if(data.VC_DEPTT == '6')
						$("#vcDeptt").val("KANGAN");
					else if(data.VC_DEPTT == '7')
						$("#vcDeptt").val("SET");
					else if(data.VC_DEPTT == '8')
						$("#vcDeptt").val("DIAMOND");
					else if(data.VC_DEPTT == '9')
						$("#vcDeptt").val("CUPID DIAMOND");
					else if(data.VC_DEPTT == '10')
						$("#vcDeptt").val("ZAVERAT");
					else if(data.VC_DEPTT == '11')
						$("#vcDeptt").val("PEARLS");
					else if(data.VC_DEPTT == '12')
						$("#vcDeptt").val("BRANCH");
					else
						$("#vcDeptt").val("");
					if(data.VC_FIELD3 == 'G')
						$("#vcField3").val("GOLD");
					else
						$("#vcField3").val("DIAMOND");
					if(data.CH_ACTIVE == 'Y')
						$("#chActive").val("ACTIVE");
					else
						$("#chActive").val("INACTIVE");
				}
			 });
			 $(".editClick").removeClass('highlight');
		 }
	}
	function validate(){
		var flag = false; 
		var vcApprovalId = $('#vcApprovalId').val();
	  	
	  	if(vcApprovalId !== "" && vcApprovalId != null && vcApprovalId !== undefined){
	  		 $("#Error").html('');
	         flag=true;
	     }else{
	     	 flag=false;	
	         $("#Error").html('Approval Id cannot be blank').show();
	         hideSuccessErrorMessage("alert");
	         return false;
	     }   
	     return flag;
	}
	$(document).ready(function(){
		$(".editClick td:not(.action)").click(function () {
			dd =$(this).closest('tr').attr('id');
			$('.highlight').removeClass('highlight');
			$(this).closest('tr').addClass('highlight');
	    });
	});
	function saveDiscountForm() {
		document.getElementById('schemForm').action= "saveDiscount";
		document.getElementById('schemForm').submit();
	}
	function clearDiscountForm() {
		if(confirm("Sure You Want To Cancel?")){
			document.getElementById("schemForm").reset();		
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
						<div class="nav">
							<div class="nav" id="nav1">						
								<a href="#" id="createImgId"><asset:image src="add.png" /></a>
								<a href="#" data-toggle="modal" data-target="#viewPopup" data-whatever="@fat"><asset:image src="view.png" /></a>
								<a href="#" data-toggle="modal" data-target="#editPopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
								<a  href="javascript:void(0)" class="cursordefault"><asset:image src="save.png" /></a>
								<a href="javascript:void(0)" class="cursordefault"><asset:image src="delete.png" /></a> 
								<a href="javascript:void(0)" class="cursordefault"><asset:image src="clear.png" /></a>
								<g:link action="dashboard" controller="company"><asset:image src="exit.png" /></g:link>
							</div>
							<div class="nav" id="nav2" style="display: none;">
								<a href="javascript:void(0)" class="cursordefault"><asset:image src="add.png" /></a>
								<a href="#" data-toggle="modal" class="cursordefault" data-target="#schemePopup" data-whatever="@fat"><asset:image src="view.png" /></a> 
								<a href="#" data-toggle="modal" class="cursordefault" data-target="#schemePopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
								<a href="#" onclick="saveDiscountForm()"><asset:image src="save.png" /></a>
								<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
								<a href="#" onclick="clearDiscountForm()"><asset:image src="clear.png" /></a>
								<a href="${request.getContextPath()}/"><asset:image src="exit.png" /></a>
							</div>  
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Scheme Master</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:if test="${flash.warning}"><div class="alert alert-danger" role="status">${flash.warning}</div></g:if>
							<div class="alert alert-danger" role="alert" id="Error" style="display: none;"></div>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="schemForm" method="POST">
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-8">
											<fieldset class="form">
												<g:render template="form" />
											</fieldset>
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
	<div class="modal fade" id="editPopup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Scheme List</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="editScheme" class="table">
						<thead>
							<tr>
								<th>App Id</th>
								<th>Approval Name</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Category</th>
								<th>App</th>
								<th>Active</th>
							</tr>
						</thead>
						<tbody id="prevAdvNoTbdId">
							<g:each in="${schemeList}" status="i" var="salesPersonInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${salesPersonInst?.VC_APPROVAL_ID}">
									<td name="approvalId" id="approvalId">${salesPersonInst?.VC_APPROVAL_ID}</td>
									<td name="approvalName" id="approvalName">${salesPersonInst?.VC_APPROVAL_NAME}</td>
									<td name="startDate" id="startDate">${salesPersonInst?.DT_START_DATE?.format('MM/dd/YYYY')}</td>
									<td name="endDate" id="endDate">${salesPersonInst?.DT_END_DATE?.format('MM/dd/YYYY')}</td>
									<td name="designation" id="designation">${salesPersonInst?.VC_FIELD3}</td>
									<td name="app" id="app">${salesPersonInst?.NU_APPROVAL_AUTH}</td>
									<td name="active" id="active">${salesPersonInst?.CH_ACTIVE}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="editSubmit();">Add</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="viewPopup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">View Scheme</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="viewScheme" class="table">
						<thead>
							<tr>
								<th>App Id</th>
								<th>Approval Name</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Category</th>
								<th>App</th>
								<th>Active</th>
							</tr>
						</thead>
						<tbody id="prevAdvNoTbdId">
							<g:each in="${schemeList}" status="i" var="salesPersonInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${salesPersonInst?.VC_APPROVAL_ID}">
									<td name="approvalId" id="approvalId">${salesPersonInst?.VC_APPROVAL_ID}</td>
									<td name="approvalName" id="approvalName">${salesPersonInst?.VC_APPROVAL_NAME}</td>
									<td name="startDate" id="startDate">${salesPersonInst?.DT_START_DATE}</td>
									<td name="endDate" id="endDate">${salesPersonInst?.DT_END_DATE}</td>
									<td name="designation" id="designation">${salesPersonInst?.VC_FIELD3}</td>
									<td name="app" id="app">${salesPersonInst?.NU_APPROVAL_AUTH}</td>
									<td name="active" id="active">${salesPersonInst?.CH_ACTIVE}</td>
									
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="viewSubmit();">Add</button>							
				</div>
			</div>
		</div>
	</div>
</body>
</html>