<table width="100%" style="border-collapse: collapse;">
<tr>
	<td bgcolor="#25a2df">
		<g:set var="menuList" value="${session.menuCodeList}"></g:set>
		<g:set var="menuDetailsMap" value="${session.menuDetailsMap}"></g:set>
	<ul class="top-level-menu">
		<g:if test="${menuList.contains('01')}">
			<li class="liCls">
				<g:if test="${menuList.contains('01')}">
					<g:set var="subMenuList" value="${menuDetailsMap.get('01')}"></g:set>
					<span> ${subMenuList[0]}<asset:image src="arrow-down.png"/></span>
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
					<g:if test="${menuList.contains('0121')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0121')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0122')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0122')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0123')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0123')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0124')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0124')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0125')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0125')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0126')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0126')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0127')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0127')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0128')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0128')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0129')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0129')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0130')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0130')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0131')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0131')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0132')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0132')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0133')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0133')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0134')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0134')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0135')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0135')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0136')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0136')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0137')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0137')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0138')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0138')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0139')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0139')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0140')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0140')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
				</ul>
			</li>
		</g:if>
		<g:if test="${menuList.contains('02')}">
			<li class="liCls">
				<g:if test="${menuList.contains('02')}">
					<g:set var="subMenuList" value="${menuDetailsMap.get('02')}"></g:set>
					<span> ${subMenuList[0]}<asset:image src="arrow-down.png"/></span>
				</g:if>
				<ul class="second-level-menu">
					<g:if test="${menuList.contains('0201')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0201')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0202')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0202')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0203')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0203')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0204')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0204')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0205')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0205')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0206')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0206')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0207')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0207')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0208')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0208')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0209')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0209')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0210')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0210')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0211')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0211')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0212')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0212')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0213')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0213')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0214')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0214')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0215')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0215')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0216')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0216')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0217')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0217')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0218')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0218')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0219')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0219')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0220')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0220')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0221')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0221')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0222')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0222')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0223')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0223')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0224')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0224')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0225')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0225')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0226')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0226')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0227')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0227')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0228')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0228')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0229')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0229')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0230')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0230')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0231')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0231')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0232')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0232')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0233')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0233')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0234')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0234')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0235')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0235')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0236')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0236')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0237')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0237')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0238')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0238')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0239')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0239')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0240')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0240')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
				</ul>
			</li>
		</g:if>
		<g:if test="${menuList.contains('03')}">
			<li class="liCls"><g:if test="${menuList.contains('03')}">
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
					<g:if test="${menuList.contains('0326')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0326')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0327')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0327')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0328')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0328')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0329')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0329')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0330')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0330')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0331')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0331')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0332')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0332')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0333')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0333')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0334')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0334')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0335')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0335')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0336')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0336')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0337')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0337')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0338')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0338')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0339')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0339')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
					<g:if test="${menuList.contains('0340')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('0340')}"></g:set>
						<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
					</g:if>
				</ul>
			</li>
		</g:if>
			<li class="liCls"><g:link action="dashboard" controller="company" class="exittext">Exit</g:link></li>
		</ul>
	</td>
</tr>
</table>
<script type="text/javascript">
$(".liCls").click(function(){
	$("#spinner").show();
});
</script>