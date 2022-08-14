<%-- 
-- File Name: index
-- Description: This page displays index Page of Code Master
-- Author(s): CTE. 
-- Date: 01/02/2016 
-- MOD HISTORY
-- DATE           	USER				COMMENTS
-- 01/02/2016		Abhijit				Created File
--            
--%>
<html>
<head>
<meta name='layout' content='mainerphq'/>
<title>Other Parameter</title>
	<script type="text/javascript">
		$(document).ready(function(){
		    $("#newDesc").hide();
		    $("input[name*='size']").click(function() {
		        ${remoteFunction(
		            controller:'mstCode',
		            action:'getBankName',
		            update: "updateMe",
		            params:'\'size=\' + this.value')}
		        $("#createImgId").click(function(){
			      	$("#newDesc").show();
		    		$("#newDesc").focus();    				
				});	
			    $("#saveImg").click(function(){
			      	fnSave();
			  	});
			   	$("#editImg").click(function(){
			      	fnEdit();
			   	});  
			   	$("#clearImg").click(function(){
			       	fnClear();
			   	}); 
					   	
			   	$("#deleteImg").click(function(){
			       	fnDelete();
			   	});   			   	     
		    });
		});
		function fnSave() {
		    var selectSize = $("#hiddenSize").val();   
		    $("#blankerrormsg").html("");
		    var hdFlg = $("#hiddenFlag").val();
		    var name = $("#name").val();
		   	var vcCode = $("#codeHid").val();
		   	var select = $("#size").val();
	    	if(hdFlg==1){
		    	var parameter = {name:name,vcCode:vcCode}
				$.ajax({
				 	url: "${request.getContextPath()}/mstCode/updateName",
					async : false,
					data : parameter,
					success : function(data){	
						$("#blanksucessmsg").html("Updated successfully.........").show();
						window.location.reload();
					}
				});	    		
	    	}else{
	    		var newDesc = $("#newDesc").val();
	    		var brcode = $("#brCodeHid").val();
	    		var selectSize = $("#hiddenSize").val();
	    		var parameter = {newDesc:newDesc,brcode:brcode,selectSize:selectSize}
				$.ajax({
				 	url: "${request.getContextPath()}/mstCode/addParams",
					async : false,
					data : parameter,
					success : function(data){	
						$("#blanksucessmsg").html("Added successfully.........").show();
						window.location.reload();
					}
				});
	    	}
	    }
	    function fnEdit() {   
		   $("#hiddenFlag").val(1); 
		    $("#name").prop('readonly', false);
		   $("#name").focus();
		   var name = $("#name").val();
	    }
	    function fnClear() {
	    	window.location.reload();
	    }
	    function fnDelete() {
	       	$("#name").focus();
	    	var name = $("#name").val();
	    	var brcode = $("#brCodeHid").val();
	    	var vcCode = $("#codeHid").val(); 
	    	var parameter = {name:name,brcode:brcode,vcCode:vcCode}
			$.ajax({
			 	url: "${request.getContextPath()}/mstCode/deleteParam",
				async : false,
				data : parameter,
				success : function(data){		
					$("#blanksucessmsg").html("Successfully delete.........").show();			
					window.location.reload();
				}
			});
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
							<a href="#"><asset:image src="add.png" /></a>
							<a href="#"><asset:image src="view.png" /></a>
							<a href="#"><asset:image src="edit.png" /></a>
							<a href="javascript:void(0)"><asset:image src="save.png" /></a>
							<a href="javascript:void(0)"><asset:image src="delete.png" /></a>
							<a href="javascript:void(0)"><asset:image src="clear.png" /></a>
							<g:link action="dashboard" controller="company"><asset:image src="exit.png" /></g:link>
						</div>
						<div id="create-mstCode" class="content scaffold-create">
							<h1>Other Parameter</h1>
							<g:if test="${flash.message}"><div class="alert alert-success" role="status">${flash.message}</div></g:if>
							<g:hasErrors bean="${mstCodeInstance}">
								<ul class="alert alert-danger" role="alert">
									<g:eachError bean="${mstCodeInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="alert alert-success" role="status" id="blanksucessmsg" style="display: none;"></div>
							<div class="content-bg pull-left">
								<div class="col-md-8">
									<div class="panel panel-default">
										<table class="table">
											<tr>
												<td>
													<table class="table">
														<thead> </thead>
															<tbody>
															<tr>
																<td class="action" >
																	<input type="radio" name="size" id="size" value="bank">Bank<br>
																</td>
															</tr>																						
															<tr>
																<td class="action">
																	<input type="radio" name="size" id="size" class="s" value="city">City<br><br>
																</td>
															</tr>
															<tr>
																<td class="action">
																	<input type="radio" name="size" id="size" class="s" value="state">State<br><br>
																</td>
															</tr>
															<tr>
																<td class="action">
																	<input type="radio" name="size" id="size" class="s" value="country">Country<br><br>
																</td>
															</tr>
															<tr>
																<td class="action">
																	<input type="radio" name="size" id="size" class="s" value="region">Region<br><br>
																</td>
															</tr>
															<tr>
																<td class="action" nowrap="nowrap">
																	<input type="radio" name="size" id="size" class="s" value="creditCard">Credit Card Bank<br><br>
																</td>
															</tr>
														</tbody>								
													</table>
												</td>
												<td>
													<g:form name="otherParamsForm" id="otherParamsForm">
														<table class="table">
															<tr><td style="text-align: center; font-weight: bold;">Description</td></tr>
															<tr>
																<td><g:hiddenField name="brCodeHid" id="brCodeHid" value="${brCode}"/>
																	<div id="updateMe"style="overflow: auto; width: 30ex; height: 200px;">
															            <g:render template="description"></g:render>
															        </div>
															        <g:hiddenField name="hiddenFlag" id="hiddenFlag" value=""/>
															       	<g:hiddenField name="hiddenSize" id="hiddenSize" value="${params?.size}"/>
															        <g:textField name="newDesc" class="form-control"/>${params?.size}
														        </td>
														    </tr> 
														 </table>
													</g:form>	
												</td>
											</tr>			
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>