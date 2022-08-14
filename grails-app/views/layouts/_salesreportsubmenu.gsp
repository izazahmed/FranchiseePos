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
				<li><a href="#"> ${subMenuList[0]}<asset:image src="arrow.png"/></a>
					<g:if test="${menuList.contains('020901')}">
						<ul class="third-level-menu">
							<g:set var="subMenuList" value="${menuDetailsMap.get('020901')}"></g:set>
							<li><a href="#"> ${subMenuList[0]}<asset:image src="arrow.png"/></a>
								<ul class="fourth-level-menu">
									<g:if test="${menuList.contains('02090101')}">
										<g:set var="subMenuList" value="${menuDetailsMap.get('02090101')}"></g:set>
										<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
									</g:if>
									<g:if test="${menuList.contains('02090102')}">
										<g:set var="subMenuList" value="${menuDetailsMap.get('02090102')}"></g:set>
										<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
									</g:if>
									<g:if test="${menuList.contains('02090103')}">
										<g:set var="subMenuList" value="${menuDetailsMap.get('02090103')}"></g:set>
										<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
									</g:if>
								</ul>
							</li>
						</ul>
					</g:if>
				</li>
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
				<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}<asset:image src="arrow.png"/></a>
					<ul class="third-level-menu">
						<g:if test="${menuList.contains('021401')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021401')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021402')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021402')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021403')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021403')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021404')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021404')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021405')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021405')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021406')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021406')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021407')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021407')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021408')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021408')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021409')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021409')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021410')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021410')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021411')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021411')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021412')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021412')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021413')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021413')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021414')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021414')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021415')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021415')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021416')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021416')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021417')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021417')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021418')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021418')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021419')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021419')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a></li>
						</g:if>
						<g:if test="${menuList.contains('021420')}">
							<g:set var="subMenuList" value="${menuDetailsMap.get('021420')}"></g:set>
							<li><a href="${request.contextPath}${subMenuList[1]}"> ${subMenuList[0]}</a>
						</g:if>
					</ul>
				</li>
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
		</ul>
	</li>
</g:if>
<script type="text/javascript">
	$(".liCls").click(function(){
		$("#spinner").show();
	});
</script>