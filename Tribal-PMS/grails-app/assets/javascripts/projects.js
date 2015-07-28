/**
 * Any js that is required for the projects / managers / tech leads
 * should go here
 */
$(document).ready(function() {
	// Toggle for new project button
	$("#add").on("click", function() {
		$(".addNewProject").toggle();
		$(".alert").hide();
	});
	// Init. datepicker
	$( "#date" ).datepicker({
		minDate: 0
	});
	
	// Monitoring changes in priority field
	$('#priority').on("keyup change", function() {
	    $.ajax({
	        url: '/projects/verifyPriority',
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