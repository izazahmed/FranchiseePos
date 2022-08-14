<style>
a {
	text-decoration: none;
}
.dropdown {
	position: relative;
	display: inline-block;
}
.dropdown-content {
	z-index:9;
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 100px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	padding: 12px 16px;
}
.dropdown:hover .dropdown-content {
	display: block;
}
</style>
<table style="width: 100%">
	<tr style="background-color: #E1F6FC">
		<td width="5%">
			<div class="dropdown">
				<span>Sales</span>
				<div class="dropdown-content">
					<a href="${request.contextPath}/mstApprovalAuth/index">Scheme Master</a><br />
					<a href="${request.contextPath}/mstRateParameter/index">Rate Parameter</a><br />
					<a href="${request.contextPath}/custMst/index">Customer Master</a><br />
					<a href="${request.contextPath}/dtRateDetail/index">Rate Details</a><br />
					<a href="${request.contextPath}/fndFlexValuesVl">FndFlexValuesVlController</a><br />
				</div>
			</div>
		</td>
		<td width="5%">
			<div class="dropdown">
				<span>Reports</span>
				<div class="dropdown-content">
					<a href="${request.contextPath}/dtEcsRef">DtEcsRefController</a><br />
					<a href="${request.contextPath}/dtRateDetail">DtRateDetailController</a><br/>
					<a href="${request.contextPath}/empMst">EmpMstController</a><br />
					<a href="${request.contextPath}/hdCash">HdCashController</a><br />
					<a href="${request.contextPath}/hdEcsDet">HdEcsDetController</a>
				</div>
			</div>
		</td>
		<td width="5%">
			<div class="dropdown">
				<span>Definition</span>
				<div class="dropdown-content">
					<a href="${request.contextPath}/hdSaleAdvOrd">HdSaleAdvOrdController</a><br />
					<a href="${request.contextPath}/inDt">InDtController</a><br />
					<a href="${request.contextPath}/mkModuleMenu">MkModuleMenuController</a><br />
					<a href="${request.contextPath}/mkRolesComp">MkRolesCompController</a><br />
					<a href=" /fran_pos/mkRoles">MkRolesController</a><br />
					<a href="${request.contextPath}/mkUsers">MkUsersController</a>


				</div>
			</div>
		</td>
		<td width="5%">
			<div class="dropdown">
				<span>Master</span>
				<div class="dropdown-content">
					<a href="${request.contextPath}/mstCode">MstCodeController</a><br />
					<a href="${request.contextPath}/mstCompany">MstCompanyController</a><br />
					<a href="${request.contextPath}/mstRegion">MstRegionController</a><br />
					<a href="${request.contextPath}/mstScheme">MstSchemeController</a>
					<a href="${request.contextPath}/brMstTab">BrMstTabController</a><br /> 
					<a href="${request.contextPath}/custMst">CustMstController</a><br />
					<a href="${request.contextPath}/dtCashAdvanceMr">DtCashAdvanceMrController</a><br />
					<a href="${request.contextPath}/dtChequeClear">DtChequeClearController</a><br />
					<a href="${request.contextPath}/dtCustRef">DtCustRefController</a><br />
					<a href="${request.contextPath}/dtEcsDet">DtEcsDetController</a>
				</div>
			</div>
		</td>
		<td width="5%">
			<div class="dropdown">
				<span>Exit</span>
				<div class="dropdown-content">
					<a href=" ${request.contextPath}/rateDetails">RateDetailsController</a><br />
					<a href="${request.contextPath}/regDt">RegDtController</a><br />
					<a href="${request.contextPath}/reports">ReportsController</a><br />
					<a href="${request.contextPath}/schemeMst">SchemeMstController</a><br />
					<a href="${request.contextPath}/tbzPosItemcodeLabelV">TbzPosItemcodeLabelVController</a><br />
					<a href="${request.contextPath}/tbzPosReciptMethodsV">TbzPosReciptMethodsVController</a><br />
					<a href="${request.contextPath}/tbzPosSalespersonVOrig">TbzPosSalespersonVOrigController</a>

				</div>
			</div>
		</td>
		<td width="40%">
			<div class="dropdown">
				<span>Window</span>
				<div class="dropdown-content">
					<a href="${request.contextPath}/userFinAccountYear">UserFinAccountYearController</a><br />
					<a href=" ${request.contextPath}/xxposCashReceipts">XxposCashReceiptsController</a><br/>
					<a href="${request.contextPath}/xxposMiscellaneousReceiptsV">XxposMiscellaneousReceiptsVController</a><br />
					<a href="${request.contextPath}/xxtbzItemMaster">XxtbzItemMasterController</a><br />
					<a href="${request.contextPath}/xxtbzMiscCashAnalysis">XxtbzMiscCashAnalysisController</a><br/>
					<a href="${request.contextPath}/xxtbzTransferHeaders">XxtbzTransferHeadersController</a><br />
					<a href="${request.contextPath}/dbdoc">DbdocController</a>
				</div>
			</div>
		</td>
	</tr>
</table>