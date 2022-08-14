
function validateFriendlyUrl(event){
	
	var regex = new RegExp("^[a-zA-Z0-9]+$");
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	if( !(event.keyCode == 8                                // backspace
		|| event.keyCode == 9                                // Tab
		|| event.keyCode == 13                              // Enter
		|| event.keyCode == 27                              // Escape
		|| event.keyCode == 46                              // delete
		|| (event.keyCode >= 35 && event.keyCode <= 40)     // arrow keys/home/end
		|| (event.keyCode >= 48 && event.keyCode <= 57)     // numbers on keyboard
		|| (event.keyCode >= 96 && event.keyCode <= 105)   // number on keypad
		|| regex.test(key))
		){
			event.preventDefault();     // Prevent character input
			return false;
	}
	return true;
}

function validateFriendlyUrlReplace(field){
	if ($(field).val().match(/[^a-zA-Z0-9]/g)) {
		$(field).val($(field).val().replace(/[^a-zA-Z0-9]/g, ''));
	}
}

function validateonlyNumeric(event){
	var regex = new RegExp("^[0-9]+$");
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	if( !(event.keyCode == 8                                // backspace
		|| event.keyCode == 9                                // Tab
		|| event.keyCode == 13                              // Enter
		|| event.keyCode == 27                              // Escape
		|| event.keyCode == 46                              // delete
		|| (event.keyCode >= 35 && event.keyCode <= 40)     // arrow keys/home/end
		|| (event.keyCode >= 48 && event.keyCode <= 57)     // numbers on keyboard
		|| (event.keyCode >= 96 && event.keyCode <= 105)   // number on keypad
		|| ((event.charCode == 65 || event.charCode == 97) && event.ctrlKey === true) // Allow: Ctrl+A OR Ctrl+a
		|| ((event.charCode == 67 || event.charCode == 99) && event.ctrlKey === true) // Allow: Ctrl+C OR Ctrl+c
		|| ((event.charCode == 88 || event.charCode == 120) && event.ctrlKey === true) // Allow: Ctrl+X OR Ctrl+x
		|| ((event.charCode == 86 || event.charCode == 118) && event.ctrlKey === true) // Allow: Ctrl+V OR Ctrl+v
		|| regex.test(key))
		){
			event.preventDefault();     // Prevent character input
			return false;
	}
	return true;
}

function validateonlyNumericReplace(field){
	if ($(field).val().match(/[^0-9]+/g)) {
		$(field).val($(field).val().replace(/[^0-9]+/g, ''));
	}
}

function validateonlyNumericWithDot(event){
	var regex = new RegExp("^[0-9]+\\.?[0-9]*$");
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	var elem = event.srcElement || event.target;
	if( !(event.keyCode == 8                                // backspace
		|| event.keyCode == 9                                // Tab
		|| event.keyCode == 13                              // Enter
		|| event.keyCode == 27                              // Escape
		|| event.keyCode == 46                              // delete
		|| (event.keyCode >= 35 && event.keyCode <= 40)     // arrow keys/home/end
		|| (event.keyCode >= 48 && event.keyCode <= 57)     // numbers on keyboard
		|| (event.keyCode >= 96 && event.keyCode <= 105)   // number on keypad
		|| ((event.charCode == 65 || event.charCode == 97) && event.ctrlKey === true) // Allow: Ctrl+A OR Ctrl+a
		|| ((event.charCode == 67 || event.charCode == 99) && event.ctrlKey === true) // Allow: Ctrl+C OR Ctrl+c
		|| ((event.charCode == 88 || event.charCode == 120) && event.ctrlKey === true) // Allow: Ctrl+X OR Ctrl+x
		|| ((event.charCode == 86 || event.charCode == 118) && event.ctrlKey === true) // Allow: Ctrl+V OR Ctrl+v
		|| regex.test(elem.value+key))
		){
			event.preventDefault();     // Prevent character input
			return false;
	}
	return true;
}
 function validateonlyNumericWithDotReplace(field){
	$(field).val(process($(field).val()));
}

function process( str ) {
	str=str.replace(/[^0-9.]/g,'');
    return str.replace( /^([^.]*\.)(.*)$/, function ( a, b, c ) { 
        return b + c.replace( /\./g, '' );
    });
}

function validateonlyString(event){
	var regex = new RegExp("^[a-z A-Z]+$");
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	if( !(event.keyCode == 8                                // backspace
		|| event.keyCode == 9                                // Tab
		|| event.keyCode == 13                              // Enter
		|| event.keyCode == 32                              // Space bar
		|| event.keyCode == 27                              // Escape
		|| event.keyCode == 46                              // delete
		|| (event.keyCode >= 35 && event.keyCode <= 40)     // arrow keys/home/end
		|| (event.keyCode >= 48 && event.keyCode <= 57)     // numbers on keyboard
		|| (event.keyCode >= 96 && event.keyCode <= 105)   // number on keypad
		|| ((event.charCode == 65 || event.charCode == 97) && event.ctrlKey === true) // Allow: Ctrl+A OR Ctrl+a
		|| ((event.charCode == 67 || event.charCode == 99) && event.ctrlKey === true) // Allow: Ctrl+C OR Ctrl+c
		|| ((event.charCode == 88 || event.charCode == 120) && event.ctrlKey === true) // Allow: Ctrl+X OR Ctrl+x
		|| ((event.charCode == 86 || event.charCode == 118) && event.ctrlKey === true) // Allow: Ctrl+V OR Ctrl+v
		|| regex.test(key))
		){
			event.preventDefault();     // Prevent character input
			return false;
	}
	return true;
}

function validateonlyStringReplace(field){
	if ($(field).val().match(/[^a-z A-Z]+/g)) {
		$(field).val($(field).val().replace(/[^a-z A-Z]+/g, ''));
	}
}
	

function validatePhone(event){
	var regex = new RegExp("^[- + ()0-9]+$");
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	if( !(event.keyCode == 8                                // backspace
		|| event.keyCode == 9                                // Tab
		|| event.keyCode == 13                              // Enter
		|| event.keyCode == 27                              // Escape
		|| event.keyCode == 46                              // delete
		|| (event.keyCode >= 35 && event.keyCode <= 40)     // arrow keys/home/end
		|| (event.keyCode >= 48 && event.keyCode <= 57)     // numbers on keyboard
		|| (event.keyCode >= 96 && event.keyCode <= 105)   // number on keypad
		|| ((event.charCode == 65 || event.charCode == 97) && event.ctrlKey === true) // Allow: Ctrl+A OR Ctrl+a
		|| ((event.charCode == 67 || event.charCode == 99) && event.ctrlKey === true) // Allow: Ctrl+C OR Ctrl+c
		|| ((event.charCode == 88 || event.charCode == 120) && event.ctrlKey === true) // Allow: Ctrl+X OR Ctrl+x
		|| ((event.charCode == 86 || event.charCode == 118) && event.ctrlKey === true) // Allow: Ctrl+V OR Ctrl+v
		|| regex.test(key))
		){
			event.preventDefault();     // Prevent character input
			return false;
	}
	return true;
}

function validatePhoneReplace(field){
	if ($(field).val().match(/[^- + ()0-9]+/g)) {
		$(field).val($(field).val().replace(/[^- + ()0-9]+/g, ''));
	}
}