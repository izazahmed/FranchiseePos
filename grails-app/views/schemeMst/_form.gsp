<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'schemeNo', 'error')} required">
	<label for="schemeNo">
		<g:message code="schemeMst.schemeNo.label" default="Scheme No" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="schemeNo" type="number" value="${schemeMstInstance.schemeNo}" required=""/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'schemeName', 'error')} ">
	<label for="schemeName">
		<g:message code="schemeMst.schemeName.label" default="Scheme Name" />
	</label>
	<g:textField class="form-control" name="schemeName" maxlength="75" value="${schemeMstInstance?.schemeName}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'schemeStartDate', 'error')} ">
	<label for="schemeStartDate">
		<g:message code="schemeMst.schemeStartDate.label" default="Scheme Start Date" />
	</label>
	<g:datePicker name="schemeStartDate" precision="day"  value="${schemeMstInstance?.schemeStartDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'schemeEndDate', 'error')} ">
	<label for="schemeEndDate">
		<g:message code="schemeMst.schemeEndDate.label" default="Scheme End Date" />
	</label>
	<g:datePicker name="schemeEndDate" precision="day"  value="${schemeMstInstance?.schemeEndDate}" default="none" noSelection="['': '']" />
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'enrollamount', 'error')} ">
	<label for="enrollamount">
		<g:message code="schemeMst.enrollamount.label" default="Enrollamount" />
	</label>
	<g:field name="enrollamount" value="${fieldValue(bean: schemeMstInstance, field: 'enrollamount')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'makDisc', 'error')} ">
	<label for="makDisc">
		<g:message code="schemeMst.makDisc.label" default="Mak Disc" />
	</label>
	<g:field name="makDisc" type="number" value="${schemeMstInstance.makDisc}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'amt', 'error')} ">
	<label for="amt">
		<g:message code="schemeMst.amt.label" default="Amt" />
	</label>
	<g:field name="amt" value="${fieldValue(bean: schemeMstInstance, field: 'amt')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'wegiht', 'error')} ">
	<label for="wegiht">
		<g:message code="schemeMst.wegiht.label" default="Wegiht" />
	</label>
	<g:field name="wegiht" value="${fieldValue(bean: schemeMstInstance, field: 'wegiht')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'referalCount', 'error')} ">
	<label for="referalCount">
		<g:message code="schemeMst.referalCount.label" default="Referal Count" />
	</label>
	<g:field name="referalCount" type="number" value="${schemeMstInstance.referalCount}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'exMakDisc', 'error')} ">
	<label for="exMakDisc">
		<g:message code="schemeMst.exMakDisc.label" default="Ex Mak Disc" />
	</label>
	<g:field name="exMakDisc" type="number" value="${schemeMstInstance.exMakDisc}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'vcShortName', 'error')} ">
	<label for="vcShortName">
		<g:message code="schemeMst.vcShortName.label" default="Vc Short Name" />
	</label>
	<g:textField class="form-control" name="vcShortName" maxlength="50" value="${schemeMstInstance?.vcShortName}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'chActive', 'error')} ">
	<label for="chActive">
		<g:message code="schemeMst.chActive.label" default="Ch Active" />
	</label>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'nuTotalMonth', 'error')} ">
	<label for="nuTotalMonth">
		<g:message code="schemeMst.nuTotalMonth.label" default="Nu Total Month" />
	</label>
	<g:field name="nuTotalMonth" type="number" value="${schemeMstInstance.nuTotalMonth}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'nuDays', 'error')} ">
	<label for="nuDays">
		<g:message code="schemeMst.nuDays.label" default="Nu Days" />
	</label>
	<g:field name="nuDays" value="${fieldValue(bean: schemeMstInstance, field: 'nuDays')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'nuCustRefAmt', 'error')} ">
	<label for="nuCustRefAmt">
		<g:message code="schemeMst.nuCustRefAmt.label" default="Nu Cust Ref Amt" />
	</label>
	<g:field name="nuCustRefAmt" value="${fieldValue(bean: schemeMstInstance, field: 'nuCustRefAmt')}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: schemeMstInstance, field: 'closeFlag', 'error')} ">
	<label for="closeFlag">
		<g:message code="schemeMst.closeFlag.label" default="Close Flag" />
	</label>
	<g:textField class="form-control" name="closeFlag" maxlength="1" value="${schemeMstInstance?.closeFlag}"/>
</div>