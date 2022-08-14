<div class="fieldcontain ${hasErrors(bean: xxposOtherDiscountInstance, field: 'brCode', 'error')} ">
	<label for="brCode">
		<g:message code="xxposOtherDiscount.brCode.label" default="Br Code" />
	</label>
	<g:textField class="form-control" name="brCode" maxlength="10" value="${xxposOtherDiscountInstance?.brCode}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposOtherDiscountInstance, field: 'nuDiscount', 'error')} ">
	<label for="nuDiscount">
		<g:message code="xxposOtherDiscount.nuDiscount.label" default="Nu Discount" />
	</label>
	<g:field name="nuDiscount" value="${fieldValue(bean: xxposOtherDiscountInstance, field: 'nuDiscount')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: xxposOtherDiscountInstance, field: 'vcActive', 'error')} ">
	<label for="vcActive">
		<g:message code="xxposOtherDiscount.vcActive.label" default="Vc Active" />
	</label>
	<g:textField class="form-control" name="vcActive" maxlength="3" value="${xxposOtherDiscountInstance?.vcActive}"/>
</div>