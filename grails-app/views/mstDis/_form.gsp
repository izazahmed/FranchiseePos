<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'vcEmpCode', 'error')} ">
	<label for="vcEmpCode">
		<g:message code="mstDis.vcEmpCode.label" default="Vc Emp Code" />
	</label>
	<g:textField class="form-control" name="vcEmpCode" maxlength="10" value="${mstDisInstance?.vcEmpCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'vcDisCode', 'error')} ">
	<label for="vcDisCode">
		<g:message code="mstDis.vcDisCode.label" default="Vc Dis Code" />
	</label>
	<g:textField class="form-control" name="vcDisCode" maxlength="5" value="${mstDisInstance?.vcDisCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'vcEmpName', 'error')} ">
	<label for="vcEmpName">
		<g:message code="mstDis.vcEmpName.label" default="Vc Emp Name" />
	</label>
	<g:textField class="form-control" name="vcEmpName" maxlength="30" value="${mstDisInstance?.vcEmpName}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'vcField1', 'error')} ">
	<label for="vcField1">
		<g:message code="mstDis.vcField1.label" default="Vc Field1" />
	</label>
	<g:textField class="form-control" name="vcField1" maxlength="30" value="${mstDisInstance?.vcField1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'vcField2', 'error')} ">
	<label for="vcField2">
		<g:message code="mstDis.vcField2.label" default="Vc Field2" />
	</label>
	<g:textField class="form-control" name="vcField2" maxlength="10" value="${mstDisInstance?.vcField2}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'dtField1', 'error')} ">
	<label for="dtField1">
		<g:message code="mstDis.dtField1.label" default="Dt Field1" />
	</label>
	<g:datePicker name="dtField1" precision="day"  value="${mstDisInstance?.dtField1}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'dtField2', 'error')} ">
	<label for="dtField2">
		<g:message code="mstDis.dtField2.label" default="Dt Field2" />
	</label>
	<g:datePicker name="dtField2" precision="day"  value="${mstDisInstance?.dtField2}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'nuField1', 'error')} ">
	<label for="nuField1">
		<g:message code="mstDis.nuField1.label" default="Nu Field1" />
	</label>
	<g:field name="nuField1" value="${fieldValue(bean: mstDisInstance, field: 'nuField1')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstDisInstance, field: 'nuField2', 'error')} ">
	<label for="nuField2">
		<g:message code="mstDis.nuField2.label" default="Nu Field2" />
	</label>
	<g:field name="nuField2" value="${fieldValue(bean: mstDisInstance, field: 'nuField2')}"/>
</div>