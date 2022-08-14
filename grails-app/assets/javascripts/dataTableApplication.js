$(document).ready(function() {
    // bind DataTable on table ID
    $('#stylesTable').dataTable( {
    /* to increase the process and speed for loading data from server
     * (check more properties if needed )
     */
        "bProcessing": true,
        "sPaginationType": "full_numbers",
        "bDeferRender": true
    });
    bindDateTimePicker();
});

function bindDateTimePicker(){
    /* date picker */
    if($('.datepick').length > 0){
        $('.datepick').datepicker();
    }
    /* date range picker */
    if($('.daterangepick').length > 0){
        $('.daterangepick').daterangepicker();
    }
    /* time picker */
    if($('.timepick').length > 0){
        $('.timepick').timepicker({
            defaultTime: 'current',
            minuteStep: 1,
            disableFocus: true,
            template: 'dropdown'
        });
    }
}
