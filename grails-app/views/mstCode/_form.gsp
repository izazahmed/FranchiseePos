<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcCompCode', 'error')} ">
	<label for="vcCompCode">
		<g:message code="mstCode.vcCompCode.label" default="Vc Comp Code" />
	</label>
	<g:textField class="form-control" name="vcCompCode" maxlength="2" value="${mstCodeInstance?.vcCompCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcCode', 'error')} ">
	<label for="vcCode">
		<g:message code="mstCode.vcCode.label" default="Vc Code" />
	</label>
	<g:textField class="form-control" name="vcCode" maxlength="12" value="${mstCodeInstance?.vcCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcCodeDesc', 'error')} ">
	<label for="vcCodeDesc">
		<g:message code="mstCode.vcCodeDesc.label" default="Vc Code Desc" />
	</label>
	<g:textField class="form-control" name="vcCodeDesc" maxlength="35" value="${mstCodeInstance?.vcCodeDesc}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'chStatFlag', 'error')} ">
	<label for="chStatFlag">
		<g:message code="mstCode.chStatFlag.label" default="Ch Stat Flag" />
	</label>
	<g:textField class="form-control" name="chStatFlag" maxlength="2" value="${mstCodeInstance?.chStatFlag}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'chStatUpFlag', 'error')} ">
	<label for="chStatUpFlag">
		<g:message code="mstCode.chStatUpFlag.label" default="Ch Stat Up Flag" />
	</label>
	<g:textField class="form-control" name="chStatUpFlag" maxlength="2" value="${mstCodeInstance?.chStatUpFlag}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'dtModDate', 'error')} ">
	<label for="dtModDate">
		<g:message code="mstCode.dtModDate.label" default="Dt Mod Date" />
	</label>
	<g:datePicker name="dtModDate" precision="day"  value="${mstCodeInstance?.dtModDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcDefaultComp', 'error')} ">
	<label for="vcDefaultComp">
		<g:message code="mstCode.vcDefaultComp.label" default="Vc Default Comp" />
	</label>
	<g:textField class="form-control" name="vcDefaultComp" maxlength="2" value="${mstCodeInstance?.vcDefaultComp}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcAuthCode', 'error')} ">
	<label for="vcAuthCode">
		<g:message code="mstCode.vcAuthCode.label" default="Vc Auth Code" />
	</label>
	<g:textField class="form-control" name="vcAuthCode" maxlength="2" value="${mstCodeInstance?.vcAuthCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcField1', 'error')} ">
	<label for="vcField1">
		<g:message code="mstCode.vcField1.label" default="Vc Field1" />
	</label>
	<g:textField class="form-control" name="vcField1" maxlength="30" value="${mstCodeInstance?.vcField1}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcField2', 'error')} ">
	<label for="vcField2">
		<g:message code="mstCode.vcField2.label" default="Vc Field2" />
	</label>
	<g:textField class="form-control" name="vcField2" maxlength="30" value="${mstCodeInstance?.vcField2}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcField3', 'error')} ">
	<label for="vcField3">
		<g:message code="mstCode.vcField3.label" default="Vc Field3" />
	</label>
	<g:textField class="form-control" name="vcField3" maxlength="30" value="${mstCodeInstance?.vcField3}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'vcField4', 'error')} ">
	<label for="vcField4">
		<g:message code="mstCode.vcField4.label" default="Vc Field4" />
	</label>
	<g:textField class="form-control" name="vcField4" maxlength="30" value="${mstCodeInstance?.vcField4}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'nuField1', 'error')} ">
	<label for="nuField1">
		<g:message code="mstCode.nuField1.label" default="Nu Field1" />
	</label>
	<g:field name="nuField1" value="${fieldValue(bean: mstCodeInstance, field: 'nuField1')}" type=""/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'nuField2', 'error')} ">
	<label for="nuField2">
		<g:message code="mstCode.nuField2.label" default="Nu Field2" />
	</label>
	<g:field name="nuField2" value="${fieldValue(bean: mstCodeInstance, field: 'nuField2')}" type=""/>
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'dtField1', 'error')} ">
	<label for="dtField1">
		<g:message code="mstCode.dtField1.label" default="Dt Field1" />
	</label>
	<g:datePicker name="dtField1" precision="day"  value="${mstCodeInstance?.dtField1}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'dtField2', 'error')} ">
	<label for="dtField2">
		<g:message code="mstCode.dtField2.label" default="Dt Field2" />
	</label>
	<g:datePicker name="dtField2" precision="day"  value="${mstCodeInstance?.dtField2}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: mstCodeInstance, field: 'brCode', 'error')} ">
	<label for="brCode">
		<g:message code="mstCode.brCode.label" default="Br Code" />
	</label>
	<g:textField class="form-control" name="brCode" maxlength="10" value="${mstCodeInstance?.brCode}"/>
</div>