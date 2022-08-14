<script type="text/javascript">
	function ValidateEmail(inputText)  {  
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
		if(inputText.match(mailformat))  
			return true;    
		else  
			return false;   
	}
	$(document).ready(function(){
		$('#birthDate').datepicker({
			changeMonth: true,
			changeYear: true
		});
		$("#findCustBtnId").attr('disabled', true);
		$("#addId").prop('disabled', true);
  		$("#addId").click(function() {
  			$("#custLocatId").prop('disabled', false);     			
  		});
	});
	var dd='';
	$(".editClick td:not(.action)").click(function () {						
		dd =$(this).closest('tr').attr('id');
		$('.highlight').removeClass('highlight');
		$(this).closest('tr').addClass('highlight');
	});  
	function getCustLocat(){
		var customerID = dd;
		$("#customerId").val(customerID);
		var parameter = {customerID:customerID}
		$.ajax({
			url: "${request.getContextPath()}/CustMst/getCustData",
			async : false,
			data : parameter,
			success : function(data){
				console.log(JSON.stringify(data));
				var nomineeGen = data.CUST_NOMINEE_GEN
				var photoId = data.PHOTO_ID
				var addressProof = data.ADDRESS_PROOF
				var mb = data.MOBILE
				var date = data.DOB
				console.log("Date : "+date);
				console.log("Mobile : "+mb);
				console.log(data.CUST_ID);
				$("#fname").attr("disabled",'disabled');
				$("#mname").attr("disabled",'disabled');
				$("#lname").attr("disabled",'disabled');
				$("#add1").attr("disabled",'disabled');
				$("#add2").attr("disabled",'disabled');
				$("#city").attr("disabled",'disabled');
				$("#state").attr("disabled",'disabled');
				$("#pin").attr("disabled",'disabled');
				$("#phoneR").attr("disabled",'disabled');
				$("#phoneO").attr("disabled",'disabled');
				$("#mobileNo").attr("disabled",'disabled');
				$("#emailId").attr("disabled",'disabled');
				$("#birthDate").attr("disabled",'disabled');
				$("#panNo").attr("disabled",'disabled');
				$("#custNominee").attr("disabled",'disabled');
				$("#custNominee").attr("disabled",'disabled');
				$("#nomineeGen").attr("disabled",'disabled');
				$("#custNomineeRel").attr("disabled",'disabled');
				$("#emailId").attr("disabled",'disabled');
				$("#photoId").attr("disabled",'disabled');
				$("#addressProof").attr("disabled",'disabled');
				$('#custid').val(data.CUST_ID);
				$('#fname').val(data.FNAME);
				$('#mname').val(data.MNAME);
				$('#lname').val(data.LNAME);
				$('#add1').val(data.ADD1);
				$('#add2').val(data.ADD2);
				$('#city').val(data.CITY);
				$('#state').val(data.STATE);
				$('#pin').val(data.PIN);
				$('#phoneR').val(data.PHONE_R);
				$('#phoneO').val(data.PHONE_O);
				$('#mobileNo').val(data.MOBILE);
				$('#emailId').val(data.EMAIL_ID);
				$('#birthDate').val(date);
				$('#panNo').val(data.PAN_NO);
				$('#custNominee').val(data.CUST_NOMINEE);
				if(nomineeGen=='F'){	
					$(".custNomFCLS").prop("checked", true );
				}else if(nomineeGen=='M'){	
					$(".custNomMCLS").prop("checked", true );
				}
				$('#custNomineeRel').val(data.CUST_NOMINEE_REL);
				$('#emailId').val(data.EMAIL_ID);	
				if(photoId=='PC'){	
					console.log(photoId);					
					$(".panCLS").prop("checked", true );
				}else if(photoId=='PP'){	
					console.log(photoId);					
					$(".passportCLS").prop("checked", true );
				}else if(photoId=='DL'){	
					console.log(photoId);					
					$(".dlCLS").prop("checked", true );
				}else if(photoId=='AC'){	
					console.log(photoId);					
					$(".acCLS").prop("checked", true );
				}	
				if(addressProof=='EB'){	
					console.log(addressProof);					
					$(".eBillCLS").prop("checked", true );
				}else if(addressProof=='PB'){	
					console.log(addressProof);					
					$(".PBillCLS").prop("checked", true );
				}else if(addressProof=='BP'){	
					console.log(addressProof);					
					$(".bPassbookCLS").prop("checked", true );
				}else if(addressProof=='RA'){	
					console.log(addressProof);					
					$(".rentAgrmntCLS").prop("checked", true );
				}else if(addressProof=='VC'){	
					console.log(addressProof);					
					$(".voterCrdCLS").prop("checked", true );
				}
			}
		});
		$(".editClick").removeClass('highlight');
	}
	function saveCustDetails(){
		if($('#fname').val()==""){
			$("#blankerrormsg").html("First Name Field Should Not Blank!"); 
			$('#fname').focus();
			return false;
		}else if($('#lname').val()==""){
			$("#blankerrormsg").html("Last Name Field Should Not Blank!"); 
			$('#lname').focus();
			return false;
		}else if($('#phoneR').val()==""){
			$("#blankerrormsg").html("Residence Field Should Not Blank!"); 
			$('#phoneR').focus();
			return false;
		} else if($('#phoneO').val()==""){
			$("#blankerrormsg").html("Office Field Should Not Blank!"); 
			$('#phoneO').focus();
			return false;
		}else if($('#mobileNo').val()==""){
			$("#blankerrormsg").html("Mobile number Field Should Not Blank!"); 
			$('#mobileNo').focus();
			return false;
		}else if($('#emailId').val()==""){
			$("#blankerrormsg").html("Email Field Should Not Blank!"); 
			$('#emailId').focus();
			return false;
		}
		else if(!ValidateEmail($('#emailId').val())){
			$("#blankerrormsg").html("Please enter a valid Email!"); 
			$('#emailId').focus();
			return false;
		} else if($('#birthDate').val()==""){
			$("#blankerrormsg").html("DOB Field Should Not Blank!"); 
			$('#birthDate').focus();
			return false;
		}else if($('#panNo').val()==""){
			$("#blankerrormsg").html("Pan Card Field Should Not Blank!"); 
			$('#panNo').focus();
			return false;
		} 
		else{
			$("#blankerrormsg").html("");
			document.getElementById("custDetailsForm").action ="saveCustDetails";
	    	document.getElementById("custDetailsForm").submit();
	    }
	}	
	function removeDivValue(){
		$("#blankerrormsg").html("");
	}
	</script>
<%--POPUP ENDS HERE --%>
<div id="blankerrormsg" align="center" style="color: red;height: 30px;">
	<label></label>
</div>
<br/>
<div class="row cust-cust-tab-form">
	<div class="col-md-6 col-sm-6 col-xs-6">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.customerId.label" default="Customer Id" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="custid" class="form-control" value="${customerInstance?.custId}" tabindex="1" readonly="true" />
		</div>
	</div>
	<div class="col-md-6 col-sm-6 col-xs-6">
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="customerId" class="form-control" value="${customerInstance?.customerId}" tabindex="1" readonly="true" />
		</div>
		<button id="custLocatId" name="custLocat" type="button" class="active_btn" data-toggle="modal" data-target="#custLocatModal" style="height: 4ex;">...</button>
	</div>
</div>
<div class="row cust-cust-tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-6 control-label"><g:message code="customerForm.fname.label" default="First Name" /><span class="redColor">*</span></label>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-6 control-label"><g:message code="customerForm.fname.label" default="Middle Name" /></label>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-6 control-label"><g:message code="customerForm.fname.label" default="Last Name" /><span class="redColor">*</span></label>
	</div>
</div>
<div class="row cust-cust-tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="fname" id="fname" class="form-control" value="${customerInstance?.fname}" tabindex="1" onKeyDown="removeDivValue();" />
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="mname" class="form-control" value="${customerInstance?.mname}" tabindex="1"  />
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="lname" id="lname" class="form-control" value="${customerInstance?.lname}" tabindex="1" onKeyDown="removeDivValue();" />
		</div>
	</div>
</div>
<div class="row cust-tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.add1.label" default="Address" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="add1" class="form-control" value="${customerInstance?.add1}" tabindex="1" />
		</div>
	</div>
</div>
<div class="row cust-tab-form">
	<div class="col-md-8 col-sm-8 col-xs-8">
		<label for="concept" class="col-sm-4 control-label"></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="add2" class="form-control" value="${customerInstance?.add2}" tabindex="1" />
		</div>
	</div>
</div>
<div class="row cust-tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.city.label" default="City" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="city" class="form-control" value="${customerInstance?.city}" tabindex="1" />
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.state.label" default="State" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="state" class="form-control" value="${customerInstance?.state}" tabindex="1" />
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.pin.label" default="Pin Code" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="pin" class="form-control" value="${customerInstance?.pin}" maxlength="6"
				onkeypress="validatePhone(event);"
				onblur="validatePhoneReplace(this);"
				onkeyup="validatePhoneReplace(this);"
				onmousedown="validatePhoneReplace(this);"
				onmouseleave="validatePhoneReplace(this);" />
		</div>
	</div>
</div>
<b>Contact Details</b>
<fieldset style="border: 1px solid #1F497D; border-radius: 2px; padding: 15px;">
	<div class="row cust-tab-form">
		<div class="col-md-3 col-sm-3 col-xs-3">
			<label for="concept" class="col-sm-6 control-label"><g:message code="customerForm.fname.label" default="Residence" /><span class="redColor">*</span></label>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<label for="concept" class="col-sm-6 control-label"><g:message code="customerForm.fname.label" default="Office" /><span class="redColor">*</span></label>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<label for="concept" class="col-sm-6 control-label"><g:message code="customerForm.fname.label" default="Mobile" /><span class="redColor">*</span></label>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<label for="concept" class="col-sm-6 control-label"><g:message code="customerForm.fname.label" default="E-Mail" /><span class="redColor">*</span></label>
		</div>
	</div>
	<div class="row cust-tab-form">
		<div class="col-md-3 col-sm-3 col-xs-3">
			<div class="col-sm-12 col-md-12 col-xs-12">
				<g:textField name="phoneR" class="form-control"
					value="${customerInstance?.phoneR}" maxlength="15"
					onkeypress="validatePhone(event);"
					onblur="validatePhoneReplace(this);"
					onkeyup="validatePhoneReplace(this);"
					onmousedown="validatePhoneReplace(this);"
					onmouseleave="validatePhoneReplace(this);" onKeyDown="removeDivValue();"/>
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<div class="col-sm-12 col-md-12 col-xs-12">
				<g:textField name="phoneO" class="form-control"
					value="${customerInstance?.phoneO}" maxlength="15"
					onkeypress="validatePhone(event);"
					onblur="validatePhoneReplace(this);"
					onkeyup="validatePhoneReplace(this);"
					onmousedown="validatePhoneReplace(this);"
					onmouseleave="validatePhoneReplace(this);" onKeyDown="removeDivValue();"/>
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<div class="col-sm-12 col-md-12 col-xs-12">
				<g:textField name="mobileNo" class="form-control"
					value="${customerInstance?.mobile}" maxlength="15"
					onkeypress="validatePhone(event);"
					onblur="validatePhoneReplace(this);"
					onkeyup="validatePhoneReplace(this);"
					onmousedown="validatePhoneReplace(this);"
					onmouseleave="validatePhoneReplace(this);" onKeyDown="removeDivValue();"/>
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<div class="col-sm-12 col-md-12 col-xs-12">
				<g:textField name="emailId" class="form-control" value="${customerInstance?.emailId}" tabindex="1" onKeyDown="removeDivValue();" />
			</div>
		</div>
	</div>
</fieldset>
<div class="row cust-tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.dob.label" default="Birth Date" /><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField class="form-control datepicker" placeholder="Birthdate" name="birthDate" value="${params?.dob}" readonly="true" onKeyDown="removeDivValue();" />			
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.panNo.label" default="PAN no."/><span class="redColor">*</span></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="panNo" class="form-control"
				value="${customerInstance?.panNo}" maxlength="10" 
				onKeyDown="removeDivValue();" 	
				onkeypress="validatePhone(event);"
				onblur="validatePhoneReplace(this);"
				onkeyup="validatePhoneReplace(this);"
				onmousedown="validatePhoneReplace(this);"
				onmouseleave="validatePhoneReplace(this);"/>
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.custNominee.label" default="Nominee" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="custNominee" class="form-control" value="${customerInstance?.custNominee}" tabindex="1" />
		</div>
	</div>
</div>

<div class="row cust-tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.dob.label" default="Gender" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:radio name="custNomineeGen" class="custNomMCLS" id="custNomineeGen" value="M" checked="checked" />Male
			<g:radio name="custNomineeGen" class="custNomFCLS" id="custNomineeGen" value="F" />Female
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<label for="concept" class="col-sm-4 control-label"><g:message code="customerForm.custNomineeRel.label" default="Relation" /></label>
		<div class="col-sm-8 col-md-8 col-xs-8">
			<g:textField name="custNomineeRel" class="form-control" value="${customerInstance?.custNomineeRel}" tabindex="1" />
		</div>
	</div>
</div>
<div class="row cust-tab-form">
	<div class="col-md-4 col-sm-4 col-xs-4">
		<b><g:message code="customerForm.dob.label" default="Photo Id" /></b>
		<fieldset
			style="border: 1px solid #1F497D; border-radius: 2px; padding: 15px;">
			<div>
				<div class="col-sm-12 col-md-12 col-xs-12">
					<g:radio name="pidRadio" class="panCLS" value="PC" checked="checked" /> &nbsp;Pan Card <br>
					<g:radio name="pidRadio" class="passportCLS" value="PP" /> &nbsp;Passport <br>
					<g:radio name="pidRadio" class="dlCLS" value="DL" /> &nbsp;Driving License <br>
					<g:radio name="pidRadio" class="acCLS" value="AC" /> &nbsp;Aadhar Card <br>
				</div>
			</div>
		</fieldset>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-4">
		<b><g:message code="customerForm.dob.label" default="Address Proof" /></b>
		<fieldset
			style="border: 1px solid #1F497D; border-radius: 2px; padding: 15px;">
			<div>
				<div class="col-sm-12 col-md-12 col-xs-12">
					<g:radio name="apRadio" class="eBillCLS" value="EB" checked="checked" /> &nbsp;Electric Bill <br>
					<g:radio name="apRadio" class="PBillCLS" value="PB" /> &nbsp;Phone Bill <br>
					<g:radio name="apRadio" class="bPassbookCLS" value="BP" /> &nbsp;Bank Pass Book <br>
					<g:radio name="apRadio" class="rentAgrmntCLS" value="RA" /> &nbsp;Rent Agreement <br>
					<g:radio name="apRadio" class="voterCrdCLS" value="VC" /> &nbsp;Voter Card <br>
				</div>
			</div>
		</fieldset>
	</div>
</div>