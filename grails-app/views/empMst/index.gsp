<%-- 
-- File Name: index
-- Description: This page displays index Page of Employee
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@ page import="com.tbz.franchisee.EmpMst" %>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Employee Master</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<g:link action="create"><asset:image src="add.png" /></g:link>
							<a href="#" id="scheme" data-toggle="modal" data-target="#viewPopup" data-whatever="@fat"><asset:image src="view.png" /></a>
							<a href="#" id="scheme" data-toggle="modal" data-target="#editPopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<a href="${request.getContextPath()}/"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-empMst" class="content scaffold-create">
							<h1>Employee Master </h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${empMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${empMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="col-md-8 page-header">
											<table class="table">
												<thead>
													<tr>
														<g:sortableColumn property="empId" title="${message(code: 'empMst.empId.label', default: 'Emp Id')}" />
														<g:sortableColumn property="vcSalesId" title="${message(code: 'empMst.vcSalesId.label', default: 'Sales Id')}" />
														<g:sortableColumn property="empName" title="${message(code: 'empMst.empName.label', default: 'Emp Name')}" />
														<g:sortableColumn property="chActive" title="${message(code: 'empMst.chActive.label', default: 'Active')}" />
														<g:sortableColumn property="vcEmpType" title="${message(code: 'empMst.vcEmpType.label', default: 'Emp Type')}" />
														<g:sortableColumn property="vcField1" title="${message(code: 'empMst.vcField1.label', default: 'Category')}" />
														<g:sortableColumn property="empName" title="${message(code: 'empMst.vcDeptDesc.label', default: 'Department Description')}" />
														<g:sortableColumn property="vcDesigCode" title="${message(code: 'empMst.vcDesigCode.label', default: 'Designation Code')}" />
														<g:sortableColumn property="vcDesignation" title="${message(code: 'empMst.vcDesignation.label', default: 'Designation')}" />
													</tr>
												</thead>
												<tbody>
													<g:each in="${empMstInstanceList}" status="i" var="empMstInstance">
														<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
															<td width="12%"><g:link action="show" id="${empMstInstance.empId}">${fieldValue(bean: empMstInstance, field: "empId")}</g:link></td>
															<td width="12%">${fieldValue(bean: empMstInstance, field: "vcSalesId")}</td>
															<td width="12%">${fieldValue(bean: empMstInstance, field: "empName")}</td>
															<td width="4%">${fieldValue(bean: empMstInstance, field: "chActive")}</td>
															<td width="12%">${fieldValue(bean: empMstInstance, field: "vcEmpType")}</td>
															<td width="12%">${fieldValue(bean: empMstInstance, field: "vcField1")}</td>
															<td width="12%">${fieldValue(bean: empMstInstance, field: "vcDeptDesc")}</td>
															<td width="12%">${fieldValue(bean: empMstInstance, field: "vcDesigCode")}</td>
															<td width="12%">${fieldValue(bean: empMstInstance, field: "vcDesignation")}</td>
														</tr>
													</g:each>
													<tr width="100%" >
														<td width="12%"><g:textField name="" size="10" maxlength="15" value="${empMstInstance?.empId}" class="form-control" /></td>
														<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcSalesId', 'error')} ">	
															<g:textField size="6" class="form-control" name="vcSalesId" maxlength="8" value="${empMstInstance?.vcSalesId}""/>				
														</td>
														<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'empName', 'error')} ">	
															<g:textField class="form-control" size="6" name="empName" maxlength="100" value="${empMstInstance?.empName}"/>
														</td>											
														<td width="4%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'chActive', 'error')} ">
															<input type="checkbox" name="chActive"/>
														</td>
														<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcEmpType', 'error')} ">
															<g:textField size="6" class="form-control" name="vcEmpType" maxlength="4" value="${empMstInstance?.vcEmpType}"/>
														</td>
														<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcField1', 'error')} ">
															<g:textField size="6" class="form-control" name="vcField1" maxlength="4" value="${empMstInstance?.vcField1}"/>
														</td>
														<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcDeptDesc', 'error')} ">
															<g:textField size="5" class="form-control" name="vcDeptDesc" maxlength="50" value="${empMstInstance?.vcDeptDesc}"/>
														</td>
														<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcDesigCode', 'error')} ">
															<g:textField size="5" class="form-control" name="vcDesigCode" maxlength="50" value="${empMstInstance?.vcDesigCode}"/>
														</td>
														<td width="12%" class="fieldcontain ${hasErrors(bean: empMstInstance, field: 'vcDesignation', 'error')} ">
															<g:textField size="6" class="form-control" name="vcDesignation" maxlength="50" value="${empMstInstance?.vcDesignation}"/>
														</td>
													</tr>
												</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>