<%@page import="com.tbz.franchisee.BrMstTab"%>
<%@ page import="com.tbz.franchisee.MkUsers" %>
<%@ page import="com.tbz.franchisee.MstCompany" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<g:set var="entityName"><g:layoutTitle default="TBZ" /></g:set>
	<title><g:layoutTitle default="TBZ" /></title>
	
	<asset:javascript src="jquery.min.js"/>
	<asset:javascript src="dataTableApplication.js"/>
	<asset:javascript src="bootstrap.min.js"/>
	<asset:javascript src="validateFriendlyNameUrl.js"/>
	<asset:javascript src="jquery-ui.js"/>
	<asset:javascript src="jquery.dataTables.min.js"/>
	<asset:javascript src="common.js"/>
	<asset:stylesheet href="mainerphq.css"/>
	<asset:stylesheet href="media.css"/>
	<asset:stylesheet href="menu_css.css"/>
	<asset:stylesheet href="bootstrap.min.css"/>
	<asset:stylesheet href="jquery-ui.css"/>
	<asset:stylesheet href="jquery.dataTables.min.css"/>
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
	<g:set var="userId" value="${session.userId}"></g:set>
	<g:layoutHead />
	<export:resource />
</head>
<body>
	<div id="spinner" class="spinner" style="display: none;">
		<div class="spinner_lean_overlay">
			<g:message code="spinner.alt" default="Loading&hellip;" />
		</div>
	</div>
	<header class="main-header"> 
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="col-sm-3 col-md-3 col-xs-3">
				<asset:image src="tbz_logo.jpg"/>
			</div>
			<div class="col-sm-6 col-md-6 col-xs-6">
			<table class="headerTableCls">
                <tr>
	                 <td align="center">
	                      <span style="font-size:25px;" class="headerTdCls">T</span><span style="font-size:20px" class="headerTdCls">RIBHOVANDAS</span>&nbsp;
	                      <span style="font-size:25px;" class="headerTdCls">B</span><span style="font-size:20px" class="headerTdCls">HIMJI</span>&nbsp;
	                      <span style="font-size:25px;" class="headerTdCls">Z</span><span style="font-size:20px" class="headerTdCls">AVERI</span>&nbsp;
	                 </td>
                </tr>
                <tr>
	                <td align="center"><span style="font-size:13px;" class="headerTdCls">SHRIKANT ZAVERI GROUP</span></td>
                </tr>
                <tr>
		        	<td align="center"><span style="font-size:23px;" class="headerTdCls">POINT OF SALE</span></td>
                </tr>
               </table>
			</div>
			<div class="col-sm-3 col-md-3 col-xs-3 userprofile">
				<ul>
					<li><label>UserName :</label> ${MkUsers.findByChUserCode(userId)?.vcUserName}</li>
					<li><label>Login Date :</label> <%=new Date() %></li>
					<li><label>Company Name :</label> ${MstCompany.findByVcCompCode(session.companyCode)?.vcCompanyName}</li>
					<li><label>Branch Name :</label> ${BrMstTab.findByBrCode(session.brCode)?.brName}</li>
				</ul>
			</div>
		</div>
	</header>
	<div style="clear: both;"></div>
	<div id="main-sidemenu">
		<g:set var="moduleValue" value="${session.moduleValue}"></g:set>
		<g:if test="${moduleValue?.equals('04')}">
			<g:render template="../layouts/salesMenu" />
		</g:if>
		<g:if test="${moduleValue?.equals('10')}">
			<g:render template="../layouts/inventoryMenu" />
		</g:if>
		<g:if test="${moduleValue?.equals('03')}">
			<g:render template="../layouts/adminMenu" />
		</g:if>
		<div class="container-fluid">
			<div class="page-wrap-bg">
				<g:layoutBody />
			</div>
		</div>
	</div>
	<footer class="footer">
	  	<div class="container-fluid">
	    <p class="text-muted">
			<g:message code="layoutFooter.poweredby.label" default="Powered by TBZ" />
		</p>
		</div>
	</footer>
	<script type="text/javascript">
		$(document).ready(function(){
			hideSuccessErrorMessage("alert");
		});
		$('.startDateStr').datepicker({
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true,
			showButtonPanel: true,
			closeText: 'Clear',
			onSelect: function(dateText, inst) {
				$(".endDateStr").datepicker("option", "minDate", $(".startDateStr").datepicker("getDate"));					
			}
		});
		$('.endDateStr').datepicker({
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true,
			showButtonPanel: true,
			closeText: 'Clear',
			minDate: $(".startDateStr").datepicker("getDate")
		});
		/* Hack for UI of Page  */
		$('#ui-datepicker-div').css('display', 'none');
		/* Hack for Clear Button */
		$.datepicker._generateHTML_Old = $.datepicker._generateHTML;
		$.datepicker._generateHTML = function(inst) {
			res = this._generateHTML_Old(inst);
			res = res.replace("_hideDatepicker()","_clearDate('#"+inst.id+"')");
			return res;
		}	
	</script>
</body>
</html>