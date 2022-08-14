<%@ page import="com.tbz.franchisee.EmpMst" %>
<div class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'empId', 'error')} required">
	<table style="width: 10px; font-weight: bold;" >
		<thead>
			<g:if test="${params?.action!= 'index'}">
				<tr width="100%" >
					<td><g:message code="empMst.empId.label" default="Emp Id" />
						<span class="required-indicator">*</span></td>
					<td><g:message code="empMst.vcSalesId.label" default="Sales Id" /></td>
					<td><g:message code="empMst.empName.label" default="Emp Name" /></td>
					<td><g:message code="empMst.chActive.label" default="Active" /></td>
					<td><g:message code="empMst.vcEmpType.label" default="Emp Type" /></td>
					<td><g:message code="empMst.vcField1.label" default="Category" /></td>
					<td><g:message code="empMst.vcDeptDesc.label" default="Department Description" /></td>
					<td><g:message code="empMst.vcDesigCode.label" default="Designation Code" /></td>
					<td><g:message code="empMst.vcDesignation.label" default="Designation" /></td>
				</tr>
			</g:if>
		</thead>
		<tbody>
			<tr width="100%" >
				<td width="12%"><g:textField size="10" name="empId" maxlength="15" required="" value="${empMstInstance?.empId}" class="form-control"/></td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcSalesId', 'error')} ">	
					<g:textField size="8" class="form-control" name="vcSalesId" maxlength="20" value="${empMstInstance?.vcSalesId}"/>				
				</td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'empName', 'error')} ">	
					<g:textField size="8" class="form-control" name="empName" maxlength="100" value="${empMstInstance?.empName}"/>
				</td>
				<td width="4%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'chActive', 'error')} ">
					<input type="checkbox" name="chActive"/>
				</td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcEmpType', 'error')} ">
				<select name="vcEmpType" id="vcEmpType" value="${params?.vcEmpType}">
						<option value="${params?.vcEmpType}">-Choose Category-</option>
						<option value="S">S</option>
						<option value="O">O</option>
						<option value="P">P</option>
						<option value="M">M</option>
					</select></td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcField1', 'error')} ">
					<select name="vcField1" id="vcField1" >
						<option value="${params?.vcField1}">-Choose Category-</option>
						<option value="GOLD" >GOLD</option>
						<option value="DIAMOND" >DIAMOND</option>
					</select>
				</td>
				<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcDeptDesc', 'error')} ">
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
				<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcDesigCode', 'error')} ">
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
				<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcDesignation', 'error')} ">
					<g:textField size="8" name="vcDesignation" maxlength="50" value="${empMstInstance?.vcDesignation}" class="form-control"/>
				</td>
			</tr>	
		</tbody>
	</table>
</div>