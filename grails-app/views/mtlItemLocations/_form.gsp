<script type="text/Javascript">
	var dd = '';
	$(document).ready(function(){
		applyDataTable('salesPerson');
		$(".editClick td:not(.action)").click(function () {
			dd =$(this).closest('tr').attr('id');
			$('.highlight').removeClass('highlight');
			$(this).closest('tr').addClass('highlight');
	    });
	});
	function getDesignVal() {
		if(dd == ""){
	         alert("Please Select Row");
	    }else{	
		    var parameter = {dd:dd}
			$.ajax({
			 	url: "${request.getContextPath()}/mstApprovalAuth/getActualSalesPersonData",
				async : false,
				data : parameter,
				success : function(data){				
					console.log(JSON.stringify(data));
					$("#vcApprovalId").val(data.VC_SALES_ID);
					$("#vcEmpCode").val(data.VC_SALES_ID);
					$("#vcApprovalName").val(data.EMP_NAME);
				}
			});
		}
	}
	function getTaxVal() {
		if(dd == ""){
	         alert("Please Select Row");
	    }else{	
		    var parameter = {dd:dd}
			$.ajax({
			 	url: "${request.getContextPath()}/mtlItemLocations/getActualLocationsData",
				async : false,
				data : parameter,
				success : function(data){	
					console.log(JSON.stringify(data));
					$("#subinventoryCode").val(data.SUBINVENTORY_CODE);
					$("#segment1").val(data.SEGMENT1);
					$("#description").val(data.DESCRIPTION);
				}
			 });
			 $(".editClick").removeClass('highlight');
		 }
	}
	function saveMtlItemLocations() {
		document.getElementById('mtlItemLocationsDetailsForm').action= "save";
		document.getElementById('mtlItemLocationsDetailsForm').submit();
	}
	function clearMtlItemLocations() {
		$("#subinventoryCode").val('');
		$("#segment1").val('');
		$("#description").val('');
	}
</script>
<div class="row tab-form">
	<div class="col-md-6 col-sm-6 col-xs-6">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mtlItemLocationsForm.subinventoryCode.label" default="Subinventory" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="subinventoryCode" class="form-control" value="${mtlItemLocationsInstance?.subinventoryCode}" tabindex="1"/>
		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-6 col-sm-6 col-xs-6">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mtlItemLocationsForm.segment1.label" default="Segment1" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="segment1" class="form-control" value="${mtlItemLocationsInstance?.segment1}" tabindex="2"/>
		</div>
	</div>
</div>
<div class="row tab-form">
	<div class="col-md-6 col-sm-6 col-xs-6">
		<label for="concept" class="col-sm-4 control-label"><g:message code="mtlItemLocationsForm.description.label" default="Description" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="description" class="form-control" value="${mtlItemLocationsInstance?.description}" tabindex="3"/>
		</div>
	</div>
</div>