<div class="row tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.no.label" default="Registration No."/><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="regNo" class="form-control" value="${regDtInstance?.regNo}" tabindex="1"/>                         
  		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
  		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.regfistrationDate.label" default="Code"/></label>
  		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField class="form-control datepicker" placeholder="regDate" name="regDate" value="${regDtInstance?.regDate}" readonly="true"/>
		</div>
  	</div>
</div>
<div class="row tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.schemeNo.label" default="Scheme Name"/><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="schemeNo" class="form-control" value="${params?.schemeName}" tabindex="1"/>                         
  		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
  		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.period.label" default="Period"/></label>
 		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="nuPeriod" class="form-control" value="${regDtInstance?.nuPeriod}" tabindex="2"/>
    	</div>
  	</div>
</div>
<div class="row tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.custId.label" default="Customer Id"/><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="custId" class="form-control" value="${params?.customerId}" tabindex="1"/>                         
  		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
  		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.period.label" default="Name"/></label>
 		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="custName" class="form-control" value="${regDtInstance?.custId}" tabindex="2"/>
    	</div>
  	</div>
</div>
<div class="row tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
  		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.custRefno.label" default="custRefno"/></label>
 		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="custRefno" class="form-control" value="${regDtInstance?.custRefno}" tabindex="2"/>
    	</div>
  	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.kpEmpmst.label" default="Employee Name"/><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="kp_empMst" class="form-control" value="${regDtInstance?.vcField1}" tabindex="1"/>
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
        <label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.department.label" default="Department"/></label>
        <div class="col-sm-8 col-md-8 col-xs-8 select-style2">
			<g:select id="vcField1" name="vcField1" from="${['Gold', 'Diamond']}" value="${params?.vcField1?:''}" />
		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-6 col-sm-6 col-xs-6">
		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.chCancelFlag.label" default="Road Show"/></label>
       <div class="col-sm-8 col-md-8 col-xs-8">
		<g:checkBox name="active" value="${regDtInstance?.chCancelFlag}" class="checkbox" tabindex="2" />		                     
       </div>
    </div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.kpEmpmst.label" default="Employee Name"/><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="kp_empMst" class="form-control" value="ABC" tabindex="1"/>
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
        <label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.label" default="(If PRO/CRO then penalty should be)"/></label>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-6 col-sm-6 col-xs-6">
		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form..enrollmentStartDate.label" default="Enrollment Start Date"/></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField class="form-control datepicker" placeholder="enrollmentStartDate" name="enrollmentStartDate" value="${regDtInstance?.enrollmentStartDate}" readonly="true"/>
		</div>
	</div>
	<div class="col-md-6 col-sm-6 col-xs-6">
		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form..enrollmentEndDate.label" default="Enrollment End Date"/></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField class="form-control datepicker" placeholder="enrollmentEndDate" name="enrollmentEndDate" value="${regDtInstance?.enrollmentEndDate}" readonly="true"/>
		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
  		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.totalMonth.label" default="Total Month"/></label>
 		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="totalMonth" class="form-control" value="${regDtInstance?.totalMonth}" tabindex="2"/>
    	</div>
  	</div>
  	<div class="col-md-4 col-sm-4 col-xs-4">
  		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.dueAmount.label" default="Enrollemnt Amount"/></label>
 		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="dueAmount" class="form-control" value="${regDtInstance?.dueAmount}" tabindex="2"/>
    	</div>
  	</div>
</div>
<div class="row tab-form">
	<div class="col-md-6 col-sm-6 col-xs-6">
    	<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.planDuration.label" default="Jewellery buying preference at the time of redemeption"/></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:radio name="vcWantToBuy" value="true" checked="${regDtInstance.vcWantToBuy}" tabindex="11" /> <span><g:message code="couponForm.yearly.label" default="Gold "/></span>
			<g:radio name="vcWantToBuy" value="false" checked="${!regDtInstance.vcWantToBuy}" tabindex="11" /> <span><g:message code="couponForm.monthly.label" default="Diamond"/></span>                               
			<g:radio name="vcWantToBuy" value="false" checked="${!regDtInstance.vcWantToBuy}" tabindex="11" /> <span><g:message code="couponForm.monthly.label" default="Platinum"/></span>
			<g:radio name="vcWantToBuy" value="false" checked="${!regDtInstance.vcWantToBuy}" tabindex="11" /> <span><g:message code="couponForm.monthly.label" default="Gold Coin"/></span>
	    </div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-3 col-sm-3 col-xs-3">
  		<label for="concept" class="col-sm-4 control-label"><g:message code="regDt.form.remark.label" default="Remark"/></label>
 		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="vcRemark" class="form-control" value="${regDtInstance?.vcRemark}" tabindex="2"/>
    	</div>
  	</div>
</div>