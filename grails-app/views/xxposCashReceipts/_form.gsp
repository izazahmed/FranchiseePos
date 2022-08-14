<%@ page import="com.tbz.franchisee.CustMst" %>
<%@ page import="com.tbz.franchisee.TbzPosSalespersonVOrig" %>
<div class="row cust-tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label">
			<g:message code="xxposCashReceiptsForm.receiptNumber.label" default="Receipt no./Date" />
		</label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="receiptNumber" class="form-control" maxlength="60" value="${xxposCashReceiptsInstance?.receiptNumber}"/>
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message
				code="xxposCashReceiptsForm.voucherCategory.label" default="Category" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="voucherCategory" class="form-control" maxlength="10" value="${xxposCashReceiptsInstance?.voucherCategory}" required="true"/>
		</div>
	</div>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'receiptNumber', 'error')} ">
	<label for="receiptNumber">
		<g:message code="xxposCashReceipts.receiptNumber.label" default="Receipt Number" />
	</label>
	<g:textField class="form-control" name="receiptNumber" maxlength="60" value="${xxposCashReceiptsInstance?.receiptNumber}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'voucherCategory', 'error')} ">
	<label for="voucherCategory">
		<g:message code="xxposCashReceipts.voucherCategory.label" default="Voucher Category" />
	</label>
	<g:textField class="form-control" name="voucherCategory" maxlength="10" value="${xxposCashReceiptsInstance?.voucherCategory}" required="true"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'voucherNo', 'error')} ">
	<label for="voucherNo">
		<g:message code="xxposCashReceipts.voucherNo.label" default="Voucher No"/>
	</label>
	<g:select name="voucherNo" from="${CustMst.list()*.custId}" maxlength="60" value="${xxposCashReceiptsInstance?.voucherNo}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'customerName', 'error')} ">
	<label for="customerName">
		<g:message code="xxposCashReceipts.customerName.label" default="Customer Name" />
	</label>
	<g:textField class="form-control" name="customerName" maxlength="200" value="${xxposCashReceiptsInstance?.customerName}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'voucherDate', 'error')} ">
	<label for="voucherDate">
		<g:message code="xxposCashReceipts.voucherDate.label" default="Voucher Date" />
	</label>
	<g:datePicker name="voucherDate" precision="day"  value="${xxposCashReceiptsInstance?.voucherDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'balanceAmount', 'error')} ">
	<label for="balanceAmount">
		<g:message code="xxposCashReceipts.balanceAmount.label" default="Balance Amount" />
	</label>
	<g:field type="" name="balanceAmount" value="${fieldValue(bean: xxposCashReceiptsInstance, field: 'balanceAmount')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'payMode', 'error')} ">
	<label for="payMode">
		<g:message code="xxposCashReceipts.payMode.label" default="Pay Mode" />
	</label>
	<g:textField class="form-control" name="payMode" maxlength="10" value="${xxposCashReceiptsInstance?.payMode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'receiptAmount', 'error')} ">
	<label for="receiptAmount">
		<g:message code="xxposCashReceipts.receiptAmount.label" default="Receipt Amount" />
	</label>
	<g:field type="" name="receiptAmount" value="${fieldValue(bean: xxposCashReceiptsInstance, field: 'receiptAmount')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'employeeName', 'error')} ">
	<label for="employeeName">
		<g:message code="xxposCashReceipts.employeeName.label" default="Employee Name" />
	</label>
	<g:select name="employeeName" from="${TbzPosSalespersonVOrig.list()*.salespersonName}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'mobileNumber', 'error')} ">
	<label for="mobileNumber">
		<g:message code="xxposCashReceipts.mobileNumber.label" default="Mobile Number" />
	</label>
	<g:field type="" name="mobileNumber" value="${fieldValue(bean: xxposCashReceiptsInstance, field: 'mobileNumber')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposCashReceiptsInstance, field: 'receiptRemark', 'error')} ">
	<label for="receiptRemark">
		<g:message code="xxposCashReceipts.receiptRemark.label" default="Receipt Remark" />
	</label>
	<g:textField class="form-control" name="receiptRemark" maxlength="200" value="${xxposCashReceiptsInstance?.receiptRemark}"/>
</div>
<g:javascript>
    $(document).ready(function(){		    
    	$("#voucherNo").change(function(){
    		var voucherNo = $("#voucherNo").val();
    		var parameter = "voucherNo="+$("#voucherNo").val();
    		$.ajax({
    			url: "${request.getContextPath()}/XxposCashReceipts/getCustInfo",
				async:false,
				data :parameter,
				success : function(data){
					$("#custId").val(data.CUST_ID);
					$("#customerName").val(data.FNAME);
					$("#mobileNumber").val(data.MOBILE);
				}
    		});
    	});
    });
</g:javascript>