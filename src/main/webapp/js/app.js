$(document).ready(function(event) {
    $('#countryId').change(function() {
	
		$("#stateId").find('option').remove();
		$('<option>').val('').text('-select-').appendTo("#stateId");
		
			$("#cityId").find('option').remove();
		$('<option>').val('').text('-select-').appendTo("#cityId");
		
    	var countryId = $("#countryId").val();
    $.ajax({
        type:"GET",
        url : "/getStates?cid="+countryId,   
        
        success : function(res) {
			alert(res);
			$.each(res,function(statId,statName) {
				$('<option>').val(statId).text(statName).appendTo("#stateId");
			});
           }
     });
    });
    
    $('#stateId').change(function() {
	
		$("#cityId").find('option').remove();
		$('<option>').val('').text('-select-').appendTo("#cityId");
		
    	var stateId = $("#stateId").val();
    	
    $.ajax({
        type:"GET",
        url : "/getCities?sid="+stateId,   
        
        success : function(res) {
			alert(res);
			$.each(res,function(cityId,cityName) {
				$('<option>').val(statId).text(cityName).appendTo("#cityId");
			});
           }
     });
    });    
    
    
    
    
    
    
 });