

function generateCharts(){
				$.ajax({
					url: "GenerateChart",
					type: "POST",
					context: document.body,
					success: function(data){
						if(data.errorCode == 200){
							console.log("success");
							console.log(JSON.stringify(data)); 
							
						}
						else{
							console.log("Failure");
						}
					}
				});
	}
	
	
	

