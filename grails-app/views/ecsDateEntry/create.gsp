<%@page import="com.tbz.franchisee.CustMst"%>
<%@ page import="com.tbz.franchisee.DtEcsRef" %>
<div class="fieldcontain ${hasErrors(bean: dtEcsRefInstance, field: 'vcCustId', 'error')} required">
	<table class="table">
		<thead>
			<g:if test="${params?.action!= 'index'}">
				<tr width="100%">
					<td><g:message code="dtEcsRef.vcCustId.label" default="Customer Id" />
						<span class="required-indicator">*</span></td>
					<td><g:message code="dtEcsRef.nuInstallFor.label" default="INST" /></td>
					<td><g:message code="dtEcsRef.      .label" default="ECS MONTH" /></td>
					<td><g:message code="dtEcsRef.dtInstallDate.label" default="Payment Received on" /></td>
					<td><g:message code="dtEcsRef.dtField2.label" default="ECS details sent on" /></td>
					<td><g:message code="dtEcsRef.     .label" default="Install Amount" /></td>
					<td><g:message code="dtEcsRef.chTextGen.label" default="Text gen" /></td>
					<td><g:message code="dtEcsRef.chActive.label" default="Status" /></td>
				</tr>
			</g:if>
		</thead>
		<tbody>
			<tr width="100%" >
				<td><g:select name="customer" from="${CustMst.list()*.custId}"/></td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: dtEcsRefInstance, field: 'nuInstallFor', 'error')} ">	
					<g:textField size="8" name="nuInstallFor" maxlength="20" value="${dtEcsRefInstance?.nuInstallFor}" class="form-control"/>				
				</td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: dtEcsRefInstance, field: 'nuInstallFor', 'error')} ">	
					<g:textField size="8" name="nuInstallFor" maxlength="100" value="${dtEcsRefInstance?.nuInstallFor} class="form-control""/>
				</td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: dtEcsRefInstance, field: 'vcEmpType', 'error')} ">
				<select name="vcEmpType" id="vcEmpType" value="${params?.vcEmpType}">
						<option value="${params?.vcEmpType}">-Choose Category-</option>
						<option value="S">S</option>
						<option value="O">O</option>
						<option value="P">P</option>
						<option value="M">M</option>
					</select></td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: dtEcsRefInstance, field: 'vcField1', 'error')} ">
					<select name="vcField1" id="vcField1" >
						<option value="${params?.vcField1}">-Choose Category-</option>
						<option value="GOLD" >GOLD</option>
						<option value="DIAMOND" >DIAMOND</option>
					</select>
				</td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: dtEcsRefInstance, field: 'vcDeptDesc', 'error')} ">
					<select name="vcDeptDesc" id="vcDeptDesc" >
						<option value="${params?.vcDeptDesc}">-Choose Description-</option>
						<option value="UNLABEL SALE" >UNLABEL SALE</option>
						<option value="DIAMOND" >DIAMOND</option>
						<option value="RING" >RING</option>
						<option value="CHAIN" >CHAIN</option>
						<option value="MANGALSUTRA" >MANGALSUTRA</option>
						<option value="SET" >SET</option>
						<option value="EARRING" >EARRING</option>
						<option value="BANGLE" >BANGLE</option>
						<option value="PENDANT SET" >PENDANT SET</option>
					</select>
				</td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: dtEcsRefInstance, field: 'vcDesigCode', 'error')} ">
					<select name="vcDesigCode" id="vcDesigCode" >
						<option value="${params?.vcDesigCode}">-Choose designation Code-</option>
						<option value="CSE" >CSE</option>
						<option value="CRO" >CRO</option>
						<option value="SO" >SO</option>
						<option value="OTHER" >OTHER</option>
						<option value="SENIOR ASSOCIATE" >SENIOR ASSOCIATE</option>
						<option value="ASSOCIATE" >ASSOCIATE</option>
						<option value="CRE" >CRE</option>
						<option value="TEAM LEADER" >TEAM LEADER</option>
					</select>
				</td>
				<td width="4%" class="fieldcontain ${hasErrors(bean: dtEcsRefInstance, field: 'chActive', 'error')} ">
					<input type="checkbox" name="chActive"/>
				</td>
			</tr>	
		</tbody>
	</table>
</div>