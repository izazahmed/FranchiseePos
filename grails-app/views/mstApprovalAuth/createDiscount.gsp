<%-- 
-- File Name: createDiscount
-- Description: This page displays create Page of Scheme Master
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Scheme Master</title>
	<script type="text/javascript">
		var dd = '';
		$(document).ready(function(){
			applyDataTable('salesScheme');
			$("#viewImg").attr("disabled" , 'disabled');
			$("#clearImg").attr("disabled" , 'disabled');
			$("#deleteImg").attr("disabled" , 'disabled');
			$("#exitImg").attr("disabled" , 'disabled');
			$("#saveImg").attr("disabled" , 'disabled');
			$(".editClick td:not(.action)").click(function () {
				dd =$(this).closest('tr').attr('id');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');
		    });
		    var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			var yyyy = today.getFullYear();
			if(dd<10) {
			    dd='0'+dd
			} 
			if(mm<10) {
			    mm='0'+mm
			} 
			today = mm+'/'+dd+'/'+yyyy;
			$("#dtStartDate").val(today);
			$("#dtEndDate").val(today);
		});
		function createScheme()	{
			document.getElementById('schemForm').action= "createDiscount";
			document.getElementById('schemForm').submit();
			document.getElementById('clearImg').disabled = false;
			document.getElementById('exitImg').disabled = false;
			document.getElementById('delImg').disabled = false;
			document.getElementById('saveImg').disabled = false;
			document.getElementById('createImg').disabled = true;
			document.getElementById('editImg').disabled = true;
			document.getElementById('viewImg').disabled = true;
	 	}   
		function getTaxVal(){
			if(dd == ""){
		         alert("Please Select Row");
		    }else{	
			    var parameter = {dd:dd}
				$.ajax({
				 	url: "${request.getContextPath()}/mstApprovalAuth/getActualSchemData",
					async : false,
					data : parameter,
					success : function(data){	
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
						else if(data.CH_ACTIVE == 'N')
							$("#chActive").val("INACTIVE");
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
						<div class="nav">
							<a href="javascript:void(0)"><asset:image src="add.png" /></a>
							<a href="#" id="scheme" data-toggle="modal" data-target="#schemePopup" data-whatever="@fat"><asset:image src="view.png" /></a> 
							<a href="#" id="scheme" data-toggle="modal" data-target="#schemePopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="#" onclick="saveDiscountForm()"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete" /></a>
							<a href="#" onclick="clearDiscountForm()"><asset:image src="clear.png" /></a>
							<a href="${request.getContextPath()}/"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Scheme MasterS</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="schemForm" id="schemForm">
								<fieldset class="form">
									<g:render template="form" />
								</fieldset>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="schemePopup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Select Scheme</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="salesScheme" class="table">
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
					<button type="button" class="active_btn" onclick="getTaxVal();" data-dismiss="modal">OK</button>
					<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>