<g:each in="${nameList}" status="i" var="bankInstance">
	<g:textField name="name" value="${bankInstance?.VC_CODE_DESC}" readonly="true"/>
	<g:hiddenField name="hiddenSize" value="${params?.size}"/>
	<g:hiddenField name="codeHid" value="${bankInstance?.VC_CODE}"/>
</g:each>