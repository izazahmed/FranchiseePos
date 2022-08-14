
<g:each in="${mstRegionLst}" status="i" var="masterInstance">
<tr>	
	<td nowrap="nowrap">
		<g:textField name="cityName"	style="font-size: 8;" value="${masterInstance?.CITY}"  readonly="true"></g:textField>
		<button id="cityId" name="city1" type="button"	class="active_btn" data-toggle="modal" data-target="#cityModal" style="height: 4ex;">...</button>
	</td>
	<td nowrap="nowrap">
		<g:textField name="stateName" style="font-size: 8;" value="${masterInstance?.STATE}"  readonly="true"></g:textField>
		<button id="stateId" name="state" type="button" class="active_btn" data-toggle="modal" data-target="#stateModal" style="height: 4ex;">...</button>
	</td>
	<td nowrap="nowrap">
		<g:textField name="countryName" style="font-size: 8;" value="${masterInstance?.COUNTRY}"  readonly="true"></g:textField>
		<button id="countryId" name="country" type="button" class="active_btn" data-toggle="modal" data-target="#countryModal" style="height: 4ex;">...</button>
	</td>
</tr>
</g:each>
