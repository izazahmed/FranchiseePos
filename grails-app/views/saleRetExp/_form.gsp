<div class="fieldcontain ${hasErrors(bean: saleRetExpInstance, field: 'nuDays', 'error')} ">
	<label for="nuDays">
		<g:message code="saleRetExp.nuDays.label" default="Nu Days" />
	</label>
	<g:field name="nuDays" value="${fieldValue(bean: saleRetExpInstance, field: 'nuDays')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: saleRetExpInstance, field: 'brCode', 'error')} ">
	<label for="brCode">
		<g:message code="saleRetExp.brCode.label" default="Br Code" />
	</label>
	<g:textField class="form-control" name="brCode" maxlength="10" value="${saleRetExpInstance?.brCode}"/>
</div>
