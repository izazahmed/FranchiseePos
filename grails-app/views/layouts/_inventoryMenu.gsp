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
					</ul>
				</li>
			</g:if>
		
			<g:if test="${menuList.contains('02')}">
				<li class="liCls"><g:if test="${menuList.contains('02')}">
						<g:set var="subMenuList" value="${menuDetailsMap.get('02')}"></g:set>
						<span> ${subMenuList[0]}<asset:image src="arrow-down.png" /></span>
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
					</ul>
				</li>
			</g:if>
			<li class="liCls"><g:link action="dashboard" controller="company"  class="exittext">Exit</g:link></li>
		</ul>
	</td>
</tr>
</table>
<script type="text/javascript">
	$(".liCls").click(function(){
		$("#spinner").show();
	});
</script>