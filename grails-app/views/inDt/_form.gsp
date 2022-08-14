<%@ page import="com.tbz.franchisee.InDt" %>
<div class="row tab-form">
	<div class="col-md-6 col-sm-6 col-xs-6">
		<label for="concept" class="col-sm-4 control-label"><g:message code="manufacturer.form.name.label" default="Name"/><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="name" class="form-control" value="${manufacturerInstance?.name}" tabindex="1"/>                         
  		</div>
	</div>
	<div class="col-md-6 col-sm-6 col-xs-6">
  		<label for="concept" class="col-sm-4 control-label"><g:message code="manufacturer.form.code.label" default="Code"/></label>
 		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="code" class="form-control" value="${manufacturerInstance?.code}" tabindex="2"/>
    	</div>
  	</div>
</div>
<div class="content-bg pull-left">
	<div class="col-md-12">
		<div class="panel panel-default">
			<table class="table">
				<thead>
					<g:if test="${params?.action!= 'index'}">
						<tr width="100%" >
							<td><g:message code="inDt.inwardId.label" default="Inward Id" />
								<span class="required-indicator">*</span></td>
							<td><g:message code="inDt.inwardDate.label" default="Inward Date" /></td>
							<td><g:message code="inDt.amount.label" default="Amount" /></td>
							<td><g:message code="inDt.payMode.label" default="Pay Mode" /></td>
							<td><g:message code="inDt.vcCheque.label" default="Cheque No." /></td>
							<td><g:message code="inDt.dtCreationDate.label" default="Pay Date" /></td>
							<td><g:message code="inDt.vcCardType.label" default="Credit Card" /></td>
							<td><g:message code="inDt.drawnOn.label" default="Drawn On" /></td>
							<td><g:message code="inDt.drawnOnAddress.label" default="Drawn on Address" /></td>
							<td><g:message code="inDt.formonth.label" default="For Month" /></td>
							<td><g:message code="inDt.defaultGoldRate.label" default="Gold Rate" /></td>
						</tr>
					</g:if>
				</thead>
				<tbody>
					<tr width="100%" >
						<td width="12%"><g:textField class="form-control" size="10" name="inwardId" maxlength="15" required="" value="${inDtInstance?.inwardId}"/></td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'inwardDate', 'error')} ">
							<g:textField class="form-control startDateStr" placeholder="Inward Date" name="inwardDate" id="inwardDate" value="${inDtInstance?.inwardDate}" readonly="true" />
						</td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'amount', 'error')} ">	
							<g:textField class="form-control" size="8" name="amount" maxlength="100" value="${inDtInstance?.amount}"/>
						</td>
						<td width="4%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'payMode', 'error')} ">
							<g:textField class="form-control" size="8" name="payMode" maxlength="100" value="${inDtInstance?.payMode}"/>
						</td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'vcCheque', 'error')} ">
							<g:textField class="form-control" size="8" name="vcCheque" maxlength="20" value="${inDtInstance?.vcCheque}"/>
						</td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'dtCreationDate', 'error')} ">
							<g:textField class="form-control startDateStr" placeholder="Pay Date" name="dtCreationDate" id="dtCreationDate" value="${inDtInstance?.dtCreationDate}" readonly="true" />
						</td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'vcCardType', 'error')} ">
							<g:textField class="form-control" size="8" name="vcCardType" maxlength="50" value="${inDtInstance?.vcCardType}"/>
						</td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'drawnOn', 'error')} ">
							<g:textField class="form-control" size="8" name="drawnOn" maxlength="50" value="${inDtInstance?.drawnOn}"/>
						</td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'drawnOnAdd', 'error')} ">
							<g:textField class="form-control" size="8" name="drawnOnAdd" maxlength="50" value="${inDtInstance?.drawnOnAdd}"/>
						</td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'formonth', 'error')} ">
							<g:textField class="form-control" size="8" name="formonth" maxlength="50" value="${inDtInstance?.formonth}"/>
						</td>
						<td width="12%" class="fieldcontain ${hasErrors(bean: inDtInstance, field: 'defaultGoldRate', 'error')} ">
							<g:textField class="form-control" size="8" name="defaultGoldRate" maxlength="50" value="${inDtInstance?.defaultGoldRate}"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>