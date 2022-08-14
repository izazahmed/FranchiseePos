<%@ page import="com.tbz.franchisee.XxtbzMiscCashAnalysis" %>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'headerId', 'error')} ">
	<label for="headerId">
		<g:message code="xxtbzMiscCashAnalysis.headerId.label" default="Header Id" />
	</label>
	<g:field name="headerId" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'headerId')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'brCode', 'error')} ">
	<label for="brCode">
		<g:message code="xxtbzMiscCashAnalysis.brCode.label" default="Br Code" />
	</label>
	<g:textField class="form-control" name="brCode" maxlength="10" value="${xxtbzMiscCashAnalysisInstance?.brCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'runDate', 'error')} ">
	<label for="runDate">
		<g:message code="xxtbzMiscCashAnalysis.runDate.label" default="Run Date" />
	</label>
	<g:datePicker name="runDate" precision="day"  value="${xxtbzMiscCashAnalysisInstance?.runDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'accountDescription', 'error')} ">
	<label for="accountDescription">
		<g:message code="xxtbzMiscCashAnalysis.accountDescription.label" default="Account Description" />
	</label>
	<g:textField class="form-control" name="accountDescription" maxlength="50" value="${xxtbzMiscCashAnalysisInstance?.accountDescription}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'amount', 'error')} ">
	<label for="amount">
		<g:message code="xxtbzMiscCashAnalysis.amount.label" default="Amount" />
	</label>
	<g:field name="amount" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'amount')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'vcField1', 'error')} ">
	<label for="vcField1">
		<g:message code="xxtbzMiscCashAnalysis.vcField1.label" default="Vc Field1" />
	</label>
	<g:textField class="form-control" name="vcField1" value="${xxtbzMiscCashAnalysisInstance?.vcField1}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'vcField2', 'error')} ">
	<label for="vcField2">
		<g:message code="xxtbzMiscCashAnalysis.vcField2.label" default="Vc Field2" />
	</label>
	<g:textField class="form-control" name="vcField2" value="${xxtbzMiscCashAnalysisInstance?.vcField2}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'vcField3', 'error')} ">
	<label for="vcField3">
		<g:message code="xxtbzMiscCashAnalysis.vcField3.label" default="Vc Field3" />
	</label>
	<g:textField class="form-control" name="vcField3" maxlength="50" value="${xxtbzMiscCashAnalysisInstance?.vcField3}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'vcField4', 'error')} ">
	<label for="vcField4">
		<g:message code="xxtbzMiscCashAnalysis.vcField4.label" default="Vc Field4" />
	</label>
	<g:textField class="form-control" name="vcField4" maxlength="50" value="${xxtbzMiscCashAnalysisInstance?.vcField4}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'vcField5', 'error')} ">
	<label for="vcField5">
		<g:message code="xxtbzMiscCashAnalysis.vcField5.label" default="Vc Field5" />
	</label>
	<g:textField class="form-control" name="vcField5" maxlength="50" value="${xxtbzMiscCashAnalysisInstance?.vcField5}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField1', 'error')} ">
	<label for="nuField1">
		<g:message code="xxtbzMiscCashAnalysis.nuField1.label" default="Nu Field1" />
	</label>
	<g:field name="nuField1" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField1')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField2', 'error')} ">
	<label for="nuField2">
		<g:message code="xxtbzMiscCashAnalysis.nuField2.label" default="Nu Field2" />
	</label>
	<g:field name="nuField2" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField2')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField3', 'error')} ">
	<label for="nuField3">
		<g:message code="xxtbzMiscCashAnalysis.nuField3.label" default="Nu Field3" />
	</label>
	<g:field name="nuField3" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField3')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField4', 'error')} ">
	<label for="nuField4">
		<g:message code="xxtbzMiscCashAnalysis.nuField4.label" default="Nu Field4" />
	</label>
	<g:field name="nuField4" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField4')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField5', 'error')} ">
	<label for="nuField5">
		<g:message code="xxtbzMiscCashAnalysis.nuField5.label" default="Nu Field5" />
	</label>
	<g:field name="nuField5" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'nuField5')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'dtField1', 'error')} ">
	<label for="dtField1">
		<g:message code="xxtbzMiscCashAnalysis.dtField1.label" default="Dt Field1" />
	</label>
	<g:datePicker name="dtField1" precision="day"  value="${xxtbzMiscCashAnalysisInstance?.dtField1}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'dtField2', 'error')} ">
	<label for="dtField2">
		<g:message code="xxtbzMiscCashAnalysis.dtField2.label" default="Dt Field2" />
	</label>
	<g:datePicker name="dtField2" precision="day"  value="${xxtbzMiscCashAnalysisInstance?.dtField2}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'dtField3', 'error')} ">
	<label for="dtField3">
		<g:message code="xxtbzMiscCashAnalysis.dtField3.label" default="Dt Field3" />
	</label>
	<g:datePicker name="dtField3" precision="day"  value="${xxtbzMiscCashAnalysisInstance?.dtField3}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="xxtbzMiscCashAnalysis.createdBy.label" default="Created By" />
	</label>
	<g:field name="createdBy" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'createdBy')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'creationDate', 'error')} ">
	<label for="creationDate">
		<g:message code="xxtbzMiscCashAnalysis.creationDate.label" default="Creation Date" />
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${xxtbzMiscCashAnalysisInstance?.creationDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'lastUpdatedBy', 'error')} ">
	<label for="lastUpdatedBy">
		<g:message code="xxtbzMiscCashAnalysis.lastUpdatedBy.label" default="Last Updated By" />
	</label>
	<g:field name="lastUpdatedBy" value="${fieldValue(bean: xxtbzMiscCashAnalysisInstance, field: 'lastUpdatedBy')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxtbzMiscCashAnalysisInstance, field: 'lastUpdatedDate', 'error')} ">
	<label for="lastUpdatedDate">
		<g:message code="xxtbzMiscCashAnalysis.lastUpdatedDate.label" default="Last Updated Date" />
	</label>
	<g:datePicker name="lastUpdatedDate" precision="day"  value="${xxtbzMiscCashAnalysisInstance?.lastUpdatedDate}" default="none" noSelection="['': '']" />
</div>