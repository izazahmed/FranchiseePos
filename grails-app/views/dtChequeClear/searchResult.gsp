<%@ page import="com.tbz.franchisee.DtChequeClear" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Cheque Clearance</title>
</head>
<body>
	<a href="#list-dtChequeClear" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
	</div>
	<div id="list-dtChequeClear" class="content scaffold-list" role="main">
		<h1>Cheque Clearance</h1>
		<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
		<g:form action="search">
			<table style="width: 50%; border-style:inset; border-color: gray;">
				<thead>
					<tr style="width: 30%;font-size: 14px; font-family: serif;">					
						<td width="50%">From Date</td><td width="50%" colspan="2">To Date</td>
					</tr>						
					<tr style="width: 30%;font-size: 14px; font-family: serif;">
						<td  width="50%">
							<g:textField name="fromDate" class="form-control" /></td><td width="50%" colspan="2">
							<g:textField name="toDate" onblur="serchByDte()" class="form-control" />
						</td>
					</tr>
					<tr style="width: 30%;font-size: 14px; font-family: serif;">
						<td colspan="3" width="50%">Search Cheque</td>
					</tr>
					<tr style="width: 30%;font-size: 14px; font-family: serif;">
						<td width="50%" colspan="3" class="search"><g:textField name="searchChqNo" value="${params.searchChqNo}" class="form-control" /></td>
					</tr>
					<tr style="width: 30%;font-size: 14px; font-family: serif;">					
						<td width="50%">From Amount</td><td width="50%" colspan="2">To Amount</td>
					</tr>						
					<tr style="width: 30%;font-size: 14px; font-family: serif;">
						<td  width="50%"><g:textField name="fromAmt" class="form-control" /></td>
						<td  width="50%"><g:textField name="toAmt" class="form-control" /></td>
						<td><input type="submit" class="active_btn" name="find" value="Find"></td>
					</tr>
				</thead>
			</table>
			</g:form>
			<g:each in="${dtChqInstance}" status="i" var="dtChequeClearInst">
				<table class="table">
				<thead>
					<tr>	
						<g:sortableColumn property="vcCustId" title="${message(code: 'dtChequeClear.vcCustId.label', default: 'Customer Id')}" />
						<g:sortableColumn property="vcCustFname" title="${message(code: 'dtChequeClear.vcCustFname.label', default: 'First Name')}" />
						<g:sortableColumn property="vcCustMname" title="${message(code: 'dtChequeClear.vcCustMname.label', default: 'Middle Name')}" />
						<g:sortableColumn property="vcCustLname" title="${message(code: 'dtChequeClear.vcCustLname.label', default: 'Last Name')}" />
						<g:sortableColumn property="vcVoucherNo" title="${message(code: 'dtChequeClear.vcVoucherNo.label', default: 'Voucher No')}" />
						<g:sortableColumn property="dtVoucherDate" title="${message(code: 'dtChequeClear.dtVoucherDate.label', default: 'Voucher Date')}" />
						<g:sortableColumn property="vcOldPaymentType" title="${message(code: 'dtChequeClear.vcOldPaymentType.label', default: 'Payment Type')}" />
						<g:sortableColumn property="dtClearDate" title="${message(code: 'dtChequeClear.dtClearDate.label', default: 'Clear Date')}" />
						<g:sortableColumn property="vcChqNo" title="${message(code: 'dtChequeClear.vcChqNo.label', default: 'Chq No')}" />
						<g:sortableColumn property="dtChqDate" title="${message(code: 'dtChequeClear.dtChqDate.label', default: 'Chq Date')}" />
						<g:sortableColumn property="nuAmount" title="${message(code: 'dtChequeClear.nuAmount.label', default: 'Amount')}" />
						<g:sortableColumn property="vcBankName" title="${message(code: 'dtChequeClear.vcBankName.label', default: 'Bank Name')}" />
						<g:sortableColumn property="chAuthFlag" title="${message(code: 'dtChequeClear.chAuthFlag.label', default: 'Flag')}" />
					</tr>
				</thead>
				<tbody>
					<tr>						
						<td><g:link action="show" id="${dtChequeClearInst.vcCustId}">${fieldValue(bean: dtChequeClearInst, field: "vcCustId")}</g:link></td>						
						<td>${fieldValue(bean: dtChequeClearInst, field: "vcCustFname")}</td>							
						<td>${fieldValue(bean: dtChequeClearInst, field: "vcCustMname")}</td>							
						<td>${fieldValue(bean: dtChequeClearInst, field: "vcCustLname")}</td>						
						<td>${fieldValue(bean: dtChequeClearInst, field: "vcVoucherNo")}</td>						
						<td>${fieldValue(bean: dtChequeClearInst, field: "dtVoucherDate")}</td>						
						<td>${fieldValue(bean: dtChequeClearInst, field: "vcOldPaymentType")}</td>						
						<td>${fieldValue(bean: dtChequeClearInst, field: "dtClearDate")}</td>							
						<td>${fieldValue(bean: dtChequeClearInst, field: "vcChqNo")}</td>						
						<td>${fieldValue(bean: dtChequeClearInst, field: "dtChqDate")}</td>						
						<td>${fieldValue(bean: dtChequeClearInst, field: "nuAmount")}</td>						
						<td>${fieldValue(bean: dtChequeClearInst, field: "vcBankName")}</td>						
						<td><input type="checkbox" name="flag" id="flg"/> </td>
					</tr>
				</tbody>
			</table>
		</g:each>
	</div>
</body>
</html>
