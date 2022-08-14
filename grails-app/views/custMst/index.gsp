<%-- 
-- File Name: index
-- Description: Shows index Page of Customer
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@ page import="com.tbz.franchisee.CustMst"%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>Customer Master</title>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#custLocatId").prop('disabled', true);
			$("#adViId").prop('disabled', true);
				    
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
			var parameter = {customerID:customerID};
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
					console.log("Mobile : "+mb);
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
					$('#mob').val(data.MOBILE);
					$('#emailId').val(data.EMAIL_ID);
					$('#db').val(data.DOB);
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
		function enableCreate() {
			$("#saveImg").click(function(){
		 		saveCustDetails();
		 	});
		 	document.getElementById('saveImg').disabled = false;
		 	document.getElementById("custDetailsForm").action ="create";
		    document.getElementById("custDetailsForm").submit();
		}
	</script>
</head>
<body>
	<div class="container-fluid content-height">
		<div class="col-md-12 page-header">
			<div class="row">
				<div class="dashboard_detail_bg">
					<div class="col-md-12">
						<div class="nav">
							<a href="#" onclick="enableCreate();"><asset:image src="add.png" /></a>
							<a href="javascript:void(0)"><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<a href="javascript:void(0)"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-custMst" class="content scaffold-create">
							<h1>Customer Master</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${custMstInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${custMstInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<g:form name="custDetailsForm" id="custDetailsForm">
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