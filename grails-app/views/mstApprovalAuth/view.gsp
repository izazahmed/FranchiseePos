<%-- 
-- File Name: view
-- Description: This page displays view Page of Scheme Master
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
		$(document).ready(function(){
			$("#createImgId").click(function(){
				document.getElementById('delImgId').disabled = true;
		    });
		    var dd = '';
		    $(".editClick td:not(.action)").click(function () {    
				dd =$(this).closest('tr').attr('id');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');
		    });
		});
		function getPrevAdvNoVal(){
			if(dd == ""){
		         alert("Please Select Row");
		    }else{	
			    var parameter = {dd:dd}
				$.ajax({
				 	url: "${request.getContextPath()}/mstApprovalAuth/getSchemeList",
					async : false,
					data : parameter,
					success : function(data){				
						console.log(JSON.stringify(data));
							$('#city').val(data.VC_APPROVAL_ID).attr('disabled', 'disabled');
							$('#firstName').val(data.vc_emp_code).attr('disabled', 'disabled');
							$('#middleName').val(data.VC_APPROVAL_NAME).attr('disabled', 'disabled');
							$('#lastName').val(data.DT_START_DATE).attr('disabled', 'disabled');
							$('#address1').val(data.DT_END_DATE).attr('disabled', 'disabled');
							$('#address2').val(data.VC_DESIGNATION).attr('disabled', 'disabled');
							$('#address3').val(data.VC_GENDER).attr('disabled', 'disabled');
							$('#pin').val(data.vc_deptt).attr('disabled', 'disabled');
							$('#pin').val(data.NU_APPROVAL_AUTH).attr('disabled', 'disabled');
							$('#pin').val(data.NU_APPROVAL_AUTH).attr('disabled', 'disabled');
							$('#pin').val(data.CH_ACTIVE).attr('disabled', 'disabled');
							
							if(!data.VC_CITY == ''){									
						         ${remoteFunction(
						             controller:'hdSaleAdvOrd',
						             action:'getStateName',
									 update :'stAndCnt',
						             params:'\'city=\' + $("#city").val()')}
						     	
						         ${remoteFunction(
						             controller:'hdSaleAdvOrd',
						             action:'getCountryName',
									 update :'stAndCnt1',
						             params:'\'city=\' + $("#city").val()')}					     
							}
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
							<g:link action="createDiscount"><asset:image src="add.png" /></g:link>
							<a href="#" id="scheme" data-toggle="modal" data-target="#schemePopup" data-whatever="@fat">" data-whatever="@fat"><img alt="" src="../images/view.png"></a>
							<g:link action="edit"><asset:image src="edit.png" /></g:link>
							<g:link action="save"><asset:image src="save.png" /></g:link>
							<g:link action="delete"><asset:image src="delete.png" /></g:link>
							<g:link action="clear"><asset:image src="clear.png" /></g:link>
							<g:link action="exit"><asset:image src="exit.png" /></g:link>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Scheme Master</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form url="[resource:mstApprovalAuthInstance, action:'save']">
								<fieldset class="form">
									<g:render template="form" />
								</fieldset>
								<fieldset class="buttons">
									<g:submitButton name="create" class="active_btn" value="${message(code: 'default.button.create.label', default: 'Create')}" />
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
					<h4 class="modal-title">Select Person</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="salesPerson">
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
							<tr class="editClick" id="${salesInst?.VC_ADV_ORD_NO}">
								<td name="approvalId" id="approvalId">123</td>								
								<td name="approvalName" id="approvalName">Sachin</td>
								<td name="startDate" id="startDate">09-May-2015</td>
								<td name="endDate" id="endDate">09-May-2015</td>
								<td name="designation" id="designation">D</td>
								<td name="app" id="app">2</td>
								<td name="active" id="active">Y</td>
							</tr>							
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