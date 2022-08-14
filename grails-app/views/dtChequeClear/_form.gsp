<%@ page import="com.tbz.franchisee.DtChequeClear" %>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcCompCode', 'error')} ">
	<label for="vcCompCode">
		<g:message code="dtChequeClear.vcCompCode.label" default="Vc Comp Code" />
	</label>
	<g:textField name="vcCompCode" class="form-control" maxlength="3" value="${dtChequeClearInstance?.vcCompCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'brCode', 'error')} ">
	<label for="brCode">
		<g:message code="dtChequeClear.brCode.label" default="Br Code" />
	</label>
	<g:textField name="brCode" class="form-control" maxlength="10" value="${dtChequeClearInstance?.brCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcCustFname', 'error')} ">
	<label for="vcCustFname">
		<g:message code="dtChequeClear.vcCustFname.label" default="Vc Cust Fname" />
	</label>
	<g:textField class="form-control" name="vcCustFname" maxlength="70" value="${dtChequeClearInstance?.vcCustFname}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcCustMname', 'error')} ">
	<label for="vcCustMname">
		<g:message code="dtChequeClear.vcCustMname.label" default="Vc Cust Mname" />
	</label>
	<g:textField class="form-control" name="vcCustMname" maxlength="70" value="${dtChequeClearInstance?.vcCustMname}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcCustLname', 'error')} ">
	<label for="vcCustLname">
		<g:message code="dtChequeClear.vcCustLname.label" default="Vc Cust Lname" />
		
	</label>
	<g:textField class="form-control" name="vcCustLname" maxlength="70" value="${dtChequeClearInstance?.vcCustLname}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcVoucherNo', 'error')} ">
	<label for="vcVoucherNo">
		<g:message code="dtChequeClear.vcVoucherNo.label" default="Vc Voucher No" />
	</label>
	<g:textField class="form-control" name="vcVoucherNo" maxlength="30" value="${dtChequeClearInstance?.vcVoucherNo}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'dtVoucherDate', 'error')} ">
	<label for="dtVoucherDate">
		<g:message code="dtChequeClear.dtVoucherDate.label" default="Dt Voucher Date" />
	</label>
	<g:datePicker name="dtVoucherDate" precision="day"  value="${dtChequeClearInstance?.dtVoucherDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcChqNo', 'error')} ">
	<label for="vcChqNo">
		<g:message code="dtChequeClear.vcChqNo.label" default="Vc Chq No" />
	</label>
	<g:textField class="form-control" name="vcChqNo" maxlength="30" value="${dtChequeClearInstance?.vcChqNo}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'dtChqDate', 'error')} ">
	<label for="dtChqDate">
		<g:message code="dtChequeClear.dtChqDate.label" default="Dt Chq Date" />
	</label>
	<g:datePicker name="dtChqDate" precision="day"  value="${dtChequeClearInstance?.dtChqDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'dtClearDate', 'error')} ">
	<label for="dtClearDate">
		<g:message code="dtChequeClear.dtClearDate.label" default="Dt Clear Date" />
	</label>
	<g:datePicker name="dtClearDate" precision="day"  value="${dtChequeClearInstance?.dtClearDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'chAuthFlag', 'error')} ">
	<label for="chAuthFlag">
		<g:message code="dtChequeClear.chAuthFlag.label" default="Ch Auth Flag" />
	</label>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'dtCreationDate', 'error')} ">
	<label for="dtCreationDate">
		<g:message code="dtChequeClear.dtCreationDate.label" default="Dt Creation Date" />
	</label>
	<g:datePicker name="dtCreationDate" precision="day"  value="${dtChequeClearInstance?.dtCreationDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcCreationBy', 'error')} ">
	<label for="vcCreationBy">
		<g:message code="dtChequeClear.vcCreationBy.label" default="Vc Creation By" />
	</label>
	<g:textField class="form-control" name="vcCreationBy" maxlength="5" value="${dtChequeClearInstance?.vcCreationBy}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'dtUpdateDate', 'error')} ">
	<label for="dtUpdateDate">
		<g:message code="dtChequeClear.dtUpdateDate.label" default="Dt Update Date" />
	</label>
	<g:datePicker name="dtUpdateDate" precision="day"  value="${dtChequeClearInstance?.dtUpdateDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcUpdateBy', 'error')} ">
	<label for="vcUpdateBy">
		<g:message code="dtChequeClear.vcUpdateBy.label" default="Vc Update By" />
	</label>
	<g:textField class="form-control" name="vcUpdateBy" maxlength="5" value="${dtChequeClearInstance?.vcUpdateBy}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcCustId', 'error')} ">
	<label for="vcCustId">
		<g:message code="dtChequeClear.vcCustId.label" default="Vc Cust Id" />
	</label>
	<g:textField class="form-control" name="vcCustId" maxlength="30" value="${dtChequeClearInstance?.vcCustId}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'nuAmount', 'error')} ">
	<label for="nuAmount">
		<g:message code="dtChequeClear.nuAmount.label" default="Nu Amount" />
	</label>
	<g:field name="nuAmount" value="${fieldValue(bean: dtChequeClearInstance, field: 'nuAmount')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcTranType', 'error')} ">
	<label for="vcTranType">
		<g:message code="dtChequeClear.vcTranType.label" default="Vc Tran Type" />
	</label>
	<g:textField class="form-control" name="vcTranType" maxlength="50" value="${dtChequeClearInstance?.vcTranType}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'nuReceiptId', 'error')} ">
	<label for="nuReceiptId">
		<g:message code="dtChequeClear.nuReceiptId.label" default="Nu Receipt Id" />
	</label>
	<g:field name="nuReceiptId" value="${fieldValue(bean: dtChequeClearInstance, field: 'nuReceiptId')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'nuBankCode', 'error')} ">
	<label for="nuBankCode">
		<g:message code="dtChequeClear.nuBankCode.label" default="Nu Bank Code" />
	</label>
	<g:field name="nuBankCode" value="${fieldValue(bean: dtChequeClearInstance, field: 'nuBankCode')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcBankName', 'error')} ">
	<label for="vcBankName">
		<g:message code="dtChequeClear.vcBankName.label" default="Vc Bank Name" />
	</label>
	<g:textField class="form-control" name="vcBankName" maxlength="100" value="${dtChequeClearInstance?.vcBankName}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: dtChequeClearInstance, field: 'vcOldPaymentType', 'error')} ">
	<label for="vcOldPaymentType">
		<g:message code="dtChequeClear.vcOldPaymentType.label" default="Vc Old Payment Type" />
	</label>
	<g:textField class="form-control" name="vcOldPaymentType" maxlength="50" value="${dtChequeClearInstance?.vcOldPaymentType}"/>
</div>