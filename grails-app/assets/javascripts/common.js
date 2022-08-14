
function hideSuccessErrorMessage(fieldId) {
	$("."+fieldId).delay(5000).fadeOut('slow');
}
function applyDataTable(selector) {
	$('#'+selector).DataTable();
}

