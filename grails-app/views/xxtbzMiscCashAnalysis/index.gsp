<%-- 
-- File Name: index
-- Description: This page displays index Page of Registration Details
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
	<title>Cash Analysis DataEntry</title>
	<script type="text/javascript">
		var tempApprovalId = '';
		var dd = '';
		$(document).ready(function(){
			$(".editClick td:not(.action)").click(function () {
				dd =$(this).closest('tr').attr('id');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');
		    });
		});
		function getTaxVal(){
			var html = ''; 
			if(dd == ""){
		         alert("Please Select Row");
		    }else{
			    var parameter = {dd:dd}
				$.ajax({
				 	url: "${request.getContextPath()}/xxtbzMiscCashAnalysis/getExactBankData",
					async : false,
					data : parameter,
					success : function(data){	
						$("#bankName").val(data.BANK_NAME);
					}
				 });
				 $(".editClick").removeClass('highlight');
			 }
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
							<g:link action="create"><asset:image src="add.png" /></g:link> 
							<a href="javascript:void(0)" id="locatorId" data-toggle="modal" data-target="#viewCityMasterModal" data-whatever="@fat"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)" id="locatorEditId" data-toggle="modal" data-target="#editCityMasterModal" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Cash Analysis Data Entry</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${xxtbzMiscCashAnalysisInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${xxtbzMiscCashAnalysisInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form url="[resource:dtRateDetailInstance, action:'save']">
								<div id="list-mstRegion" class="content scaffold-list" role="main">
									<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
									<div class="content-bg pull-left">
										<div class="row">
											<div class="col-md-12">
												<div class="panel panel-default">
													<table class="table" id="tableId">
														<thead>
														<tr>
															<g:sortableColumn property="Account Description" title="Account Description" />
															<g:sortableColumn property="Run Date" title="Run Date" />
															<g:sortableColumn property="Amount" title="Amount" />
															<g:sortableColumn property="Bank Name" title="Bank Name" />
															<g:sortableColumn property="Amount" title="Amount" />
														</tr>
														</thead>
														<tbody id="tbodyId">
															<tr>
																<td>
																	<g:textField id="cityName2" name="cityName" class="form-control" readonly="true"></g:textField>
																</td>
																<td>
																	<g:textField id="stateName2" name="stateName" readonly="true" class="form-control"></g:textField>
																</td>
																<td>
																	<g:textField  id="bankName2" name="bankName" readonly="true" class="form-control"></g:textField>
																</td>
																<td>
																	<div class="textbtn">	
																		<g:textField id="cityName1" name="cityName" class="form-control" readonly="true"></g:textField>
																		<button id="bankId6" name="bank" type="button" class="active_btn" data-toggle="modal"data-target="#bankModal">...</button>
																	</div>
																</td>
																<td><g:textField id="cityName1" name="cityName"	class="form-control" readonly="true"></g:textField></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="pull-right rightsapce">
													<input type="button" name="add" id="addId1" value="Add" class="active_btn" />
													<g:submitButton name="delete" class="active_btn" onclick="fnDelete();" value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
<!-- Popup for Bank Start -->	
	<div class="modal fade" id="bankModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Bank</h4>
				</div>
				<div class="modal-body">
					<table width="100%" class="table">
						<thead>
							<tr>
								<th>Bank Name</th>
								<th>Bank Account Id</th>
							</tr>
						</thead>
						<tbody id="bankLstId">
							<g:each in="${bankList}" status="i" var="bankInst">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${bankInst}">
									<td name="bankName" id="bankName">${bankInst?.BANK_NAME}</td>
									<td name="bankId" id="bankId">${bankInst?.BANK_ACCOUNT_ID}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="active_btn" onclick="getTaxVal();" data-dismiss="modal">Add</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>