<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.vcApprovalId.label" default="Approval Id"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:textField name="vcApprovalId" class="form-control enableDisable" disabled="disabled" value="${mstApprovalAuthInstance?.vcApprovalId}" tabindex="1"/>
  		</div>
		<button id="taxId" type="button" disabled="disabled" class="btn btn-info btnTax-clk enableDisable" data-toggle="modal" data-target="#editPopup" style="height: 4ex;">...</button>                         
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.vcEmpCode.label" default="Emp Code"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:textField name="vcEmpCode" class="form-control enableDisable" disabled="disabled" value="${mstApprovalAuthInstance?.vcEmpCode}" tabindex="2"/>                         
  		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.vcApprovalName.label" default="Approval Name"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:textField name="vcApprovalName" class="form-control enableDisable" disabled="disabled" value="${mstApprovalAuthInstance?.vcApprovalName}" tabindex="3"/>                         
  		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.dtStartDate.label" default="Start Date"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:textField name="dtStartDate" class="form-control enableDisable startDateStr" disabled="disabled" value="${mstApprovalAuthInstance?.dtStartDate?.format('MM/DD/YYYY')}" readonly="true"  tabindex="4"/>
  		</div>		
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.dtEndDate.label" default="End Date"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:textField name="dtEndDate" class="form-control enableDisable endDateStr" disabled="disabled" value="${mstApprovalAuthInstance?.dtEndDate?.format('MM/DD/YYYY')}" readonly="true"  tabindex="5"/>
  		</div>		
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.vcApprovalName.label" default="Designation"  tabindex="6"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:select name="vcEmpType" from="${designationList}" class="form-control enableDisable" disabled="disabled"/>
  		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.vcGender.label" default="Gender"  tabindex="7"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:select name="vcGender" from="${genderList}" class="form-control enableDisable" disabled="disabled" />
  		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.vcDeptt.label" default="Deptt"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:select name="vcDeptt" from="${departmentList}" class="form-control enableDisable" disabled="disabled"  tabindex="8"/>
  		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.nuApprovalAuth.label" default="Discount(%)"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:textField name="nuApprovalAuth" class="form-control enableDisable" disabled="disabled" value="${mstApprovalAuthInstance?.nuApprovalAuth}" tabindex="9"/>
  		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.discountId.label" default="Discount ID"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:textField name="discountId" class="form-control enableDisable" disabled="disabled" value="${mstApprovalAuthInstance?.nuApprovalAuth}" tabindex="10"/>
  		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.vcDeptt.label" default="Category"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6 ">
			<g:select name="vcField3" from="${categoryList}" value="${mstApprovalAuthInstance?.vcField3?:''}" class="form-control enableDisable" disabled="disabled" tabindex="11"/>
  		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mstApprovalAuth.form.chActive.label" default="Active"/></label>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<g:select name="chActive" from="${activeList}" value="${mstApprovalAuthInstance?.chActive?:''}" class="form-control enableDisable" disabled="disabled" tabindex="12"/>
  		</div>
	</div>
</div>
<g:hiddenField name="dtStartDate" id="dtStartDate"/>
<g:hiddenField name="dtEndDate" id="dtEndDate"/>