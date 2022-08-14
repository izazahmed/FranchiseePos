<div class="content-bg pull-left">
	<div class="col-md-12">
		<div class="panel panel-default">
			<table class="table">
				<thead>
					<tr>
						<td>Branch</td>
						<td>Category</td>
						<td>Purity</td>
						<td>Rate%</td>
						<td>Rate</td>
					</tr>
				</thead>
				<tbody>							
					<g:each in="${rateData}" status="i" var="dataInstance">						
						<tr>
							<td><g:textField name="branchName" class="enableDisable form-control" value="${dataInstance?.BR_NAME}" /></br></td>
							<td><g:textField name="category" class="enableDisable form-control" value="${dataInstance?.VC_CATEGORY}" /></br></td>
							<td><g:textField name="purity" class="enableDisable form-control" value="${dataInstance?.VC_PURITY}" /></br></td>
							<td><g:textField name="ratePer" class="enableDisable form-control" value="${dataInstance?.NU_RATE_PERCENTAGE}" /></br></td>
							<td><g:textField name="rate" class="enableDisable form-control" value="${dataInstance?.RATE}" /></br></td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</div>
</div>