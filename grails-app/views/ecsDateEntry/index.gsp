<%-- 
-- File Name: index
-- Description: This page displays index Page of Registration Details
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Sachin				Created File
--            
--%>
<%@ page import="com.tbz.franchisee.DtEcsRef"%>
<!DOCTYPE html>
<html>
<head>
	<meta name='layout' content='mainerphq' />
	<title>ECS date Entry</title>
	<script type="text/javascript">
		var tempApprovalId = '';
		var dd = '';
		$(document).ready(function(){
			$(".editClick td:not(.action)").click(function () {
				dd =$(this).closest('tr').attr('id');
				$('.highlight').removeClass('highlight');
				$(this).closest('tr').addClass('highlight');
		    });
			$(function(){
				$("#dtStartDate").datepicker({
					changeMonth:true,
					changeYear:true
				});
				$("#dtEndDate").datepicker({
					changeMonth:true,
					changeYear:true
				});
			});
		});
		function getTaxVal(){
			var html = ''; 
			if(dd == ""){
		         alert("Please Select Row");
		    }else{
			    var parameter = {dd:dd}
				$.ajax({
				 	url: "${request.getContextPath()}/ecsDateEntry/getExactCustomerData",
					async : false,
					data : parameter,
					success : function(data){	
						$("#VC_CUST_ID").val(data.VC_CUST_ID);
						for(var i=0;i<data.CNT;i++){
							html +='<tr>'+
			 				'<td>'+data.NU_INSTALL_FOR[i]+'</td>'+
			 				'<td></td>'+
			 				'<td>'+data.VC_FIELD2[i]+'</td>'+
			 				'<td>'+data.DT_FIELD2[i]+'</td>'+
			 				'<td>'+data.NU_AMOUNT[i]+'</td>'+
			 				'<td>'+data.CH_STAGE[i]+'</td>'+
			 				'<td></td>'+
			 				'</tr>';
			 			}
						 $("#ecsTable tbody").empty();
			 			$("#ecsTable tbody").append(html);
					}
				 });
				 $(".editClick").removeClass('highlight');
			 }
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
							<g:link action="create"><asset:image src="add.png" /></g:link>
							<a><asset:image src="view.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a> 
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<a href="javascript:void(0)"><asset:image src="exit.png" /></a>
						</div>
						<div id="create-ecsDateEntry" class="content scaffold-create">
							<h1>ECS date Entry</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:form name="ecsDateEntryForm" id="ecsDateEntryForm">
								<div class="content-bg pull-left">
									<div class="row">
										<div class="col-md-12">
											<fieldset class="form">
												<g:render template="form" />
											</fieldset>
										</div>
									</div>
								</div>
							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>