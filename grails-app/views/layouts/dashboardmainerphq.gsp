<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<g:set var="entityName"><g:layoutTitle default="TBZ"/></g:set>
	<title><g:layoutTitle default="TBZ"/></title>
	
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
	<g:layoutHead/>
	<export:resource />
</head>
<body>
	<div id="spinner" class="spinner" style="display:none;">
		<div class="spinner_lean_overlay">
			<g:message code="spinner.alt" default="Loading&hellip;"/>
		</div>
	</div>
	<header class="main-header"></header>
	<div class="sidebar-at-right" id="main-sidemenu">
	 	<g:layoutBody/>   	  
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			hideSuccessErrorMessage("alert");
		});
		$('.datepicker').datepicker({
			changeMonth:true,
			changeYear:true
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