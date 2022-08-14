
<%@ page import="com.tbz.franchisee.XxtbzMiscCashAnalysis" %>
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
		var bmNm = '';
		var mainButId = '';
		$(document).ready(function(){
			$(".editClick td:not(.action)").click(function () {
				bmNm =$(this).closest('tr').attr('id');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');			
		    });
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			var yyyy = today.getFullYear();
			if(dd<10) {
			    dd='0'+dd
			} 		
			if(mm<10) {
			    mm='0'+mm
			} 		
			today = dd+'/'+mm+'/'+yyyy;		
			$("#todaysDate").val(today);
			$("#accountDesc1").val('Cash Deposited');
			$("#accountDesc2").val('Petty Cash transfer');
			$("#runDate1").val(today);
			$("#runDate2").val(today);
		});
		function getButId(val){	
			mainButId = val;
		}	
		function getTaxVal(){
			if(bmNm == ""){
			    alert("Please Select Row");
			   }else{
				$("#bankName"+mainButId+"").val(bmNm);
			 }
		}
		function saveCashAnalysis(){
			document.getElementById('cashAnalysisForm').submit();
			document.getElementById('cashAnalysisForm').action= "save";
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
							<a href="javascript:void(0)" id="locatorId" data-toggle="modal" data-target="#viewCityMasterModal" data-whatever="@fat"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)" id="locatorEditId" data-toggle="modal" data-target="#editCityMasterModal" data-whatever="@fat"><asset:image src="edit.png" /></a>
							<a href="#" onclick="saveCashAnalysis();"><asset:image src="save.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-mstApprovalAuth" class="content scaffold-create">
							<h1>Cash Analysis Data Entry</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstApprovalAuthInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstApprovalAuthInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="cashAnalysisForm">
								<div id="list-mstRegion" class="content scaffold-list" role="main">
									<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
									<div class="content-bg pull-left">
										<div class="col-md-12">
											<div class="panel panel-default">
												<!-- Table -->
												<div class="col-md-12 page-header">
													<table class="table" id="tableId">
														<thead>
														<tr>
															<g:sortableColumn property="Account Description" title="Account Description" />
															<g:sortableColumn property="Run Date" title="Run Date" />
															<g:sortableColumn property="Amount" title="Amount" />
															<g:sortableColumn property="Bank Name" title="Bank Name" />
															<g:sortableColumn property="Narration" title="Narration" />
														</tr>
														</thead>
														<tbody id="tbodyId">
															<tr>
																<td nowrap="nowrap">
																	<g:textField class="form-control" name="accountDesc1"></g:textField>
																</td>
																<td nowrap="nowrap">
																	<g:textField class="form-control" name="runDate1"></g:textField>
																</td>
																<td nowrap="nowrap">
																	<g:textField class="form-control" name="amount1"></g:textField>
																</td>
																<td nowrap="nowrap"><g:textField class="form-control" name="bankName1"></g:textField>
																<button id="bankId" name="bank" type="button" class="active_btn" data-toggle="modal"data-target="#bankModal" onclick="getButId(1);" style="height: 4ex;">...</button>
																</td>
																<td nowrap="nowrap"><g:textField class="form-control" name="narration1"></g:textField></td>
															</tr>
															<tr>
																<td nowrap="nowrap">
																	<g:textField class="form-control" name="accountDesc2"></g:textField>
																</td>
																<td nowrap="nowrap">
																	<g:textField class="form-control" name="runDate2"></g:textField>
																</td>
																<td nowrap="nowrap">
																	<g:textField class="form-control" name="amount2"></g:textField>
																</td>
																<td nowrap="nowrap"><g:textField class="form-control" name="bankName2"></g:textField>
																
																</td>
																<td nowrap="nowrap"><g:textField class="form-control" name="narration2"></g:textField></td>
															</tr>
														</tbody>
													</table>
													<table class="table"> 
														<tr>
															<td>
																<input type="button" name="add" id="addId1" value="Add" class="active_btn" />
																<g:submitButton name="delete" class="active_btn" onclick="fnDelete();" value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
															</td>
														</tr>
													</table>
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
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'} editClick" id="${bankInst?.BANK_NAME}">
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