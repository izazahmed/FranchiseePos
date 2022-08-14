<%-- 
     -- File Name: create rate master
     -- Description: it is used to do save&display the rate master data
     -- Author(s): CTE 
     -- Date: 04/04/2016 
     -- MOD HISTORY
     -- DATE           USER             COMMENTS
     -- 04/04/2016	   Izaz      		Created File
     --            
--%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Rate Master</title>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="add.png" /></a>
							<a href="javascript:void(0)" class="cursordefault"><asset:image src="view.png" /></a>
							<a href="javascript:void(0)" onclick="saveRateMaster();"><asset:image src="save.png" /></a>
							<a href="#" onclick="clearAllFields();"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)" onclick="returnToMainPage();"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-dtRateDetail" class="content scaffold-create">
							<h1>Rate master</h1>													
							<g:form name="rateDetailForm" id="rateDetailForm">
								<g:hiddenField name="moduleCode" id="moduleCode" value="${session.moduleValue}"/>
								<div>
									<g:if test="${flash.warning}"><div class="message_error" style="font-size: medium;color: red;">${flash.warning}</div></g:if>
									<g:hasErrors beans="[dtRateDetailInstance : dtRateDetailInstance]">
										<ul>
											<g:eachError bean="${dtRateDetailInstance}" var="error">
												<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
											</g:eachError>
										</ul>
									</g:hasErrors>
								</div>
								<fieldset class="form">
									<g:render template="form" />
								</fieldset>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>