<%-- 
-- File Name: dashboard
-- Description: Shows Dashboard of Franchisee Application
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
	<meta name='layout' content='dashboardmainerphq' />
	<title>Dashboard</title>
	<script type="text/javascript">
		function onModuleCodeClick(item){
			$("#spinner").show();
			document.getElementById('moduleForm').moduleCode.value = item;
			document.getElementById('moduleForm').action= "mainModule";
			document.getElementById('moduleForm').submit();
		}
	</script>
</head>
<body>
<div id="spinner" class="spinner" style="display: none;">
	<div class="spinner_lean_overlay">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
</div>
<div class="container-fluid content-height">
	<div class="col-md-12">
		<div class="row">
			<form id="moduleForm"  method='POST'>
				<div class="form-signin">
					<fieldset>
						<ul>
							<g:hiddenField name="moduleCode"/>
							<g:each in="${moduleMenu}" status="i" var="menuInst">
								<li>
									<div class="dashboard_detail_bg">
										<button onclick="onModuleCodeClick('${menuInst?.VC_MODULE_CODE}');" class="dashboard_active_btn" action="mainModule">${menuInst?.VC_MODULE_OBJECT}</button>
									</div>						
								</li>
							</g:each>
							<li>
								<div class="dashboard_detail_bg">
									<g:actionSubmit class="dashboard_active_btn" action="logout" value="Logout" />
								</div>			
							</li>
						</ul>		
					</fieldset>
				</div>
			</form>
		</div>
	</div>
</div>	
</body>
</html>