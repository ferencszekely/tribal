/**
 * Any js that is required for the projects / managers / tech leads
 * should go here
 */
$(document).ready(function() {
	// Toggle for new project button
	$('#add').on("click", function() {
		
	});
	
	// Init. datepicker
	$( "#date" ).datepicker({
		minDate: 0
	});
	
	$('#priority').keyup(function() {
	    $.ajax({
	        url: '${createLink(controller: 'projects', action: 'verifyPriority')}',
	        dataType: "json",
	        data: {
	            pr: $('#priority').val()
	        },
	        success: function(response) {
	           if (response.success) {
	               $('#goProject').prop('disabled', false);
	               $("#inUse").hide();
	           } else if (response.error) {
	        	   $('#goProject').prop('disabled', true);
	        	   $("#inUse").show();
	           }
	        },
	        error: function(error) {
	        }
	    });
	   
     });
	
	
});