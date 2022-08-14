<%-- 
-- File Name: create
-- Description: Shows create Page of Location Master
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 18/04/2016		Sachin				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Location Master</title>
	<script type="text/javascript">
		var dd = '';
		var editFlag = '';
		$(document).ready(function(){
			applyDataTable('locationViewTable');
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
		});
		function onEditClick(){
			editFlag = "TRUE";
			$("#editVar").val(editFlag);
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
							<a href="#" id="location" data-toggle="modal" data-target="#locationPopup" data-whatever="@fat"><asset:image src="view.png" /></a> 
							<a href="#" id="location" data-toggle="modal" data-target="#locationPopup" data-whatever="@fat" onclick="onEditClick();"><asset:image src="edit.png" /></a>
							<a href="#"  onclick="saveMtlItemLocations();"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="#" onclick="clearMtlItemLocations()"><asset:image src="clear.png" /></a>
							<g:link action="dashboard" controller="company"><asset:image src="exit.png" /></g:link> 
						</div>
						<div id="create-mtlItemLocations" class="content scaffold-create">
							<h1>Location Master</h1>
							<g:if test="${flash.message}"> <div class="alert alert-success" role="alert">${flash.message}</div> </g:if>
							<g:hasErrors bean="${mtlItemLocationsInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${catalogInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="alert alert-danger" role="alert" id="warningMessage" style="display: none"></div>  
							<g:form name="mtlItemLocationsDetailsForm" id="mtlItemLocationsDetailsForm">							
								<g:hiddenField name="editVar" id="editVar"/>
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
	<div class="modal fade" id="locationPopup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Select Location</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="locationViewTable" class="table">
						<thead>
							<tr>
								<th>Subinventory Code</th>
								<th>Segment1</th>
								<th>Description</th>
							</tr>
						</thead>
						<tbody id="prevAdvNoTbdId">
							<g:each in="${locationList}" status="i" var="locationInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${locationInst?.INVENTORY_LOCATION_ID}">
									<td name="subinventoryCode" id="subinventoryCode">${locationInst?.SUBINVENTORY_CODE}</td>
									<td name="segment1" id="segment1">${locationInst?.SEGMENT1}</td>
									<td name="description" id="description">${locationInst?.DESCRIPTION}</td>									
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