<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>SR Days extend</title>
	<script type="text/javascript">
		var dd='';
		$(document).ready(function(){
			applyDataTable('customerPopup');
			$(".editClick td:not(.action)").click(function () {						
				dd =$(this).closest('tr').attr('id');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');
			});
		});
		function getCustLocat(){
			var customerID = dd;
			$("#customerId").val(customerID);
			var parameter = {customerID:customerID}
			$.ajax({
				url: "${request.getContextPath()}/SaleRetExp/getBranchData",
				async : false,
				data : parameter,
				success : function(data){
					$('#brCode').val(data.brCode);
					$('#brName').val(data.brName);
				}
			})
			$(".editClick").removeClass('highlight');
		}
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div id="create-ecsDateEntry" class="content scaffold-create">
							<h1> SR Days extend </h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:form action="edit" method="PUT">
								<div class="content-bg pull-left">
									<div class="row cust-tab-form">
										<div class="col-md-4 col-sm-4 col-xs-4">
											<label for="concept" class="col-sm-4 control-label">
												<g:message code="saleretExpForm.brCode.label" default="Branch Code" />
											</label>
											<div class="col-sm-8 col-md-8 col-xs-8">
												<g:textField name="brCode" class="form-control" />
											</div>
										</div>
										<div class="col-md-4 col-sm-4 col-xs-4">
											<label for="concept" class="col-sm-4 control-label">
												<g:message code="saleretExpForm.brName.label" default="Branch Name" />
											</label>
											<div class="col-sm-6 col-md-6 col-xs-6">
												<g:textField name="brName" class="form-control" />
											</div>
											<div class="col-sm-2 col-md-2 col-xs-2">
												<button id="custLocatId" name="custLocat" type="button"
													class="active_btn" data-toggle="modal"
													data-target="#branchModal" style="height: 4ex;">...</button>
											</div>
										</div>
										<div class="col-md-4 col-sm-4 col-xs-4">
											<label for="concept" class="col-sm-4 control-label"><g:message
													code="customerForm.state.label" default="Days" /></label>
											<div class="col-sm-8 col-md-8 col-xs-8">
												<g:textField name="nuDays" class="form-control" tabindex="1" />
											</div>
										</div>
									</div>
									<div class="pull-right rightsapce">
										<g:actionSubmit class="active_btn" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
									</div>
								</div>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="branchModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Branch List</h4>
				</div>
				<div class="modal-body">
					<table width="100%" id="customerPopup" class="table">
						<thead>
							<tr width="100%">
								<th><strong>Branch Code</strong></th>								
								<th><strong>Branch Name</strong></th>
							</tr>
						</thead>
						<tbody id="branchId">
							<g:each in="${branchList}" status="i" var="branchInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${branchInst?.brCode}" width="100%">
									<td name="brCode" id="brCode" nowrap="nowrap">${branchInst?.brCode}</td>
									<td name="brName" id="brName" nowrap="nowrap">${branchInst?.brName}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="getCustLocat();" data-dismiss="modal">Add</button>
					<button type="button" class="active_btn" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>