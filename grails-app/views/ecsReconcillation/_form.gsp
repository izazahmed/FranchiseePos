<%@page import="com.tbz.franchisee.CustMst"%>
<%@ page import="com.tbz.franchisee.DtEcsRef" %>
<div class="row cust-tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label">
			<g:message code="ecsDateEntry.customer.label" default="Customer" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="VC_CUST_ID" class="form-control" tabindex="1" />
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="custName" class="form-control" value="${customerInstance?.CUST_NAME}" tabindex="1" />
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<a href="#" id="scheme" data-toggle="modal" data-target="#customerPopup" data-whatever="@fat"> <asset:image src="view.png" /></a>
	</div>
</div>
<div class="col-md-12">
	<div class="panel panel-default">
		<table class="table" id="ecsTable">
			<thead>
				<tr>
					<td><strong>Inst</strong></td>
					<td><strong>ECS Month</strong></td>
					<td><strong>Payment Received On</strong></td>
					<td><strong>ECS details sent on</strong></td>
					<td><strong>Installment Amount</strong></td>
					<td><strong>Text Gen</strong></td>
					<td><strong>Status</strong></td>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
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
								<td name="approvalId" id="approvalId">${customerInst?.CUST_ID}</td>
								<td name="approvalName" id="approvalName">${customerInst?.CUST_NAME}</td>									
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