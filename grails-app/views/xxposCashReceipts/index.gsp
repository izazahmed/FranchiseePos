<%-- 
-- File Name: index
-- Description: This page displays index Page of Jan Se Jama
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Jan Se Jama</title>
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
						<div id="create-xxPosChashReceipts" class="content scaffold-create">
							<h1>Jan Se Jama</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${xxPosChashReceiptsInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${xxPosChashReceiptsInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="content-bg pull-left">
								<div class="col-md-12">
									<div class="panel panel-default">
										<table class="table">
											<thead>
												<tr>
													<td><strong>Voucher Number</strong></td>
													<td><strong>Voucher Date</strong></td>
													<td><strong>Receipt Type</strong></td>
													<td><strong>Category</strong></td>
													<td><strong>Sale Vocuher no</strong></td>
													<td><strong>Customer Name</strong></td>
													<td><strong>Sale Voucher Date</strong></td>
													<td><strong>Balance Amount</strong></td>
													<td><strong>Payment mode</strong></td>
													<td><strong>Receipt Amount</strong></td>
													<td><strong>Employee Name</strong></td>
												</tr>
											</thead>
											<tbody>
												<g:each in="${xxposCashReceiptsInstanceList}" status="i"
													var="xxposCashReceiptsInstance">
													<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "voucherNo")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "voucherDate")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "receiptType")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "voucherCategory")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "customerId")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "voucherDate")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "customerName")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "balanceAmount")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "payMode")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "receiptAmount")}</td>
														<td>${fieldValue(bean: xxposCashReceiptsInstance, field: "employeeName")}</td>
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
	</div>
</body>
</html>