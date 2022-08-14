<table width="100%" style="border-collapse: collapse;">
<tr>
	<td bgcolor="#25a2df">
	<g:set var="menuList" value="${session.menuCodeList}"></g:set>
	<g:set var="menuDetailsMap" value="${session.menuDetailsMap}"></g:set>
	<form id="menuForm"  method='POST'>
		<g:hiddenField name="menuCode"/>
		<g:hiddenField name="menuValueObj"/>
		<g:hiddenField name="contextPath" value="${request.contextPath}"/>
		<ul class="top-level-menu">
			<g:if test="${menuList.contains('01')}">
				<li class="liCls">
					<g:if test="${menuList.contains('01')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('01')}"></g:set>
						${subMenuList[0]}<asset:image src="arrow-down.png" />
					</g:if>
					<ul class="second-level-menu">
						<g:if test="${menuList.contains('0101')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0101')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0102')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0102')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0103')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0103')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0104')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0104')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0105')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0105')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0106')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0106')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0107')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0107')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0108')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0108')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0109')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0109')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0110')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0110')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0111')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0111')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0112')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0112')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0113')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0113')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0114')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0114')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0115')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0115')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0116')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0116')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0117')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0117')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0118')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0118')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0119')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0119')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0120')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0120')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
					</ul>
				</li>
			</g:if>	
			<g:render template="/layouts/salesreportsubmenu"></g:render>
			<g:if test="${menuList.contains('03')}">
				<li class="liCls">
					<g:if test="${menuList.contains('03')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('03')}"></g:set>
						<span> ${subMenuList[0]}<asset:image src="arrow-down.png"/></span>
					</g:if>
					<ul class="second-level-menu">
						<g:if test="${menuList.contains('0301')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0301')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0302')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0302')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0303')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0303')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0304')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0304')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0305')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0305')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0306')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0306')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0307')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0307')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0308')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0308')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0309')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0309')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0310')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0310')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0311')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0311')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0312')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0312')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0313')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0313')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0314')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0314')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0315')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0315')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0316')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0316')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0317')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0317')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0318')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0318')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0319')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0319')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0320')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0320')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0321')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0321')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0322')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0322')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0323')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0323')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0324')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0324')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0325')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0325')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
					</ul>
				</li>
			</g:if>
			<g:if test="${menuList.contains('04')}">
				<li class="liCls">
					<g:if test="${menuList.contains('04')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('04')}"></g:set>
						<span> ${subMenuList[0]}<asset:image src="arrow-down.png"/></span>
					</g:if>
					<ul class="second-level-menu">
						<g:if test="${menuList.contains('0401')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0401')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0402')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0402')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0403')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0403')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0404')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0404')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0405')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0405')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0406')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0406')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0407')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0407')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0408')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0408')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0409')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0409')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0410')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0410')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0411')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0411')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0412')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0412')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0413')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0413')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0414')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0414')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('0415')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('0415')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
					</ul>
				</li>
			</g:if>
			<g:render template="/layouts/salesSubMenu"></g:render>
			<li class="liCls"><g:link action="dashboard" controller="company" class="exittext">Exit</g:link></li>
		</ul>
	</form>
</td>
</tr>
</table>
<script type="text/javascript">
	function loadPage(value0,value1,value2){
		var conPath = document.getElementById('menuForm').contextPath.value 
		document.getElementById('menuForm').menuCode.value = value0;
		document.getElementById('menuForm').menuValueObj.value = value2;
		document.getElementById('menuForm').action= conPath+value2;
		document.getElementById('menuForm').submit();
	}
	$(".liCls").click(function(){
		$("#spinner").show();
	});
</script>