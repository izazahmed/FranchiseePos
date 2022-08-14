
<%@ page import="com.tbz.franchisee.CustMst" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Customer Master</title>
</head>
<body>
	<a href="#list-custMst" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create">New Customer</g:link></li>
		</ul>
	</div>
	<div id="list-custMst" class="content scaffold-list" role="main">
		<h1>Customer Master</h1>
		<h1 style="text-align: right;top: 2px;">
			<g:form action="search">
				<div class="search">
					<g:textField name="q" value="${params.q}" size="8" class="form-control"/>
					<input type="submit" value="Search"/>
				</div>
			</g:form>
		</h1>
		<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
		<g:each in="${custInstance}" status="i" var="custMstInst">
			<table style="width: 100%; ">
				<tr style="font-size:12px; font: bold;">
					<td style="border: 1px; border-color: gray; border-bottom-style: solid; border-left-style: solid;border-right-style: solid; border-top-style: solid">
					<table style="width: 80%; border-style: solid; margin-left: 15em;">
						<thead style="font-weight: bold; background-color: gray; color: white;">
							<tr>						
								<td width="50%"><g:message code="custMst.custId.label" default="Customer ID" /></td>
								<td width="50%" class="fieldcontain ${hasErrors(bean: custMstInstance, field: 'fname', 'error')}"><g:message code="custMst.fname.label" default="Name" /></td>
							</tr>
						</thead>
					</table>
					<table style="width: 80%; margin-left: 15em;">
						<tbody>
							<tr>						
								<td width="50%" style="font-size:12px; font: bold;">
									<g:link action="show" controller="CustMst" id="${custMstInst.custId}">${custMstInst.custId}</g:link>
								</td>
								<td width="50%" >${fieldValue(bean: custMstInst, field: "fname")} ${fieldValue(bean: custMstInst, field: "mname")} ${fieldValue(bean: custMstInst, field: "lname")}</td>
							</tr>
						</tbody>
					</table>	
					</td>	
				</tr>
			</table>			
		</g:each>
	</div>
</body>
</html>