<%-- 
-- File Name: index
-- Description: This page displays index Page of Cheque Clearance
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@ page import="com.tbz.franchisee.DtChequeClear" %>
<html>
<head>
	<meta name='layout' content='mainerphq'/>
	<title>Cheque Clearance</title>
	<script type="text/javascript">
		function serchByDte(){
			var params = {}
			 var frmDt = $("#fromDate").val();
			 params.frmDt = frmDt;	
			 var toDate = $("#toDate").val();		 
			 params.toDate = toDate;
			 console.log(params)
			 $.ajax({
				type:'POST',
				url:'${request.getContextPath()}/DtChequeClear/getData',
				data:params,
				success:function(data){
				}				
			});	
		}
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="#" id="scheme" data-toggle="modal" data-target="#editPopup" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="${request.getContextPath()}/"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-dtChequeClear" class="content scaffold-create">
							<h1>Cheque Clearance</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${dtChequeClearInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${dtChequeClearInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form action="search">
								<div class="content-bg pull-left">
									<div class="col-md-8">
										<div class="panel panel-default">
											<table class="table">
												<thead>
													<tr>					
														<td>From Date</td><td>To Date</td>
													</tr>						
													<tr>
														<td>
															<g:datePicker name="fromDate" precision="day" default="none" noSelection="['': '']" value="${params.fromDate}"/>
														</td>
														<td>
															<g:datePicker name="toDate" precision="day" default="none" noSelection="['': '']" value="${params.todate}"/>
														</td>
													</tr>
													<tr>
														<td colspan="2">Search Cheque</td>
													<tr>
														<td colspan="2" class="search"><g:textField class="form-control" name="searchChqNo" value="${params.searchChqNo}" /></td>
													</tr>
													<tr>					
														<td>From Amount</td><td>To Amount</td>
													</tr>						
													<tr>
														<td><g:textField class="form-control" name="fromAmt" value="${params.fromAmt}" /></td>
														<td>
															<div class="textbtn">
																<g:textField class="form-control" name="toAmt" value="${params.toAmt}" />
																<input type="submit" name="find" value="Find" class="active_btn">
															</div>
														</td>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</g:form>
							<div class="content-bg pull-left">
								<div class="col-md-12">
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
											<g:each in="${dtChequeClearInstanceList}" status="i" var="dtChequeClearInstance">
												<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
													<td><g:link action="show" id="${dtChequeClearInstance.vcCustId}">${fieldValue(bean: dtChequeClearInstance, field: "vcCustId")}</g:link></td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "vcCustFname")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "vcCustMname")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "vcCustLname")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "vcVoucherNo")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "dtVoucherDate")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "vcOldPaymentType")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "dtClearDate")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "vcChqNo")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "dtChqDate")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "nuAmount")}</td>
													<td>${fieldValue(bean: dtChequeClearInstance, field: "vcBankName")}</td>
													<td><input type="checkbox" name="flag" id="flg"/> </td>
												</tr>
											</g:each>
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
</body>
</html>