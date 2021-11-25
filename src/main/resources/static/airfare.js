
  $(document).ready( function() {
    
$.ajax({
	  type: "GET",
	  url: "/airports",
	  cache: false,
	  success: function(data){
		var parsedData = JSON.parse(data);
		 addOptions('#source',parsedData._embedded.locations);
		addOptions('#destination',parsedData._embedded.locations);
	  }
	});

	//$( "#source" ).select2({matcher: matchCustom});
	//$( "#destination" ).select2({matcher: matchCustom});
	
	// Matcher function to fetch options which are maching text or value of options
	function matchCustom(params, data) {
    // If there are no search terms, return all of the data
	  if ($.trim(params.term) === '') {
		return data;
	  }

	  // Skip if there is no 'children' property
	  if (typeof data.children === 'undefined') {
		return null;
	  }
	  
	  // `data.children` contains the actual options that we are matching against
	  var filteredChildren = [];
	  $.each(data.children, function (idx, child) {
		if ($(child.element).attr("code") && (child.text.toUpperCase().indexOf(params.term.toUpperCase()) != -1 || 
			$(child.element).attr("code").toUpperCase().indexOf(params.term.toUpperCase()) != -1 ||
			$(child.element).attr("description").toUpperCase().indexOf(params.term.toUpperCase()) != -1) ) {
		  filteredChildren.push(child);
		}
	  });

	  // If we matched any of the timezone group's children, then set the matched children on the group
	  // and return the group object
	  if (filteredChildren.length) {
		var modifiedData = $.extend({}, data, true);
		modifiedData.children = filteredChildren;

		// You can return modified objects from here
		// This includes matching the `children` how you want in nested data sets
		return modifiedData;
	  }

    // Return `null` if the term should not be displayed
    return null;
	}
	
	function addOptions(optionId, locations) {
		let optiongroup = $( optionId ).find('optgroup');
		locations.forEach(function(ele,id){
			let option = $('<option>');
			option.text(ele.name);
			option.attr("code", ele.code);
			option.attr("description",ele.description);
			optiongroup.append(option);
		});
		$( optionId ).select2({
		placeholder: {
    id: '-1', // the value of the option
    text: 'Select an option'
  },
			matcher: matchCustom
			
		});
	}
	
	$( "#source, #destination" ).on('change',function(){
	
		if($("#source").val() && $("#destination").val()) {
			$("#getFare").prop('disabled', false);
		}
		else
		{
			$("#getFare").prop('disabled', true);
		}
		
	});
	
	
    
	$( "#getFare" ).on('click',function(){
	
		if(!$("#source").val() && !$("#destination").val()) {
			alert("source or destination cannot be empty");
			return;
		}
		if($("#source").val() == $("#destination").val()) {
			alert("source and destination cannot be same");
			return;
		}
		
		
		
		$.ajax({
	  type: "GET",
	  url: "/fares?origin="+$("#source").find("option:contains('"+$("#source").val()+"')").attr('code')+"&destination="+$("#destination").find("option:contains('"+$("#destination").val()+"')").attr('code'),
	  cache: false,
	  success: function(data){
			var parsedData = JSON.parse(data);
			var fareData = " Amount : "+ parsedData.amount + " Currency : "+parsedData.currency + " Origin : "+parsedData.origin+" Destination : "+parsedData.destination;
		$("#fareDetail").show();
		$("#fareDetail").text(fareData);
		// fare fetched do something
	  }
	});
	});
  } );