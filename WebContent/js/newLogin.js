

$(function() {
	console.log("Inside New Login File");
	var name = $( "#name" ),
	email = $( "#email" ),
	password = $( "#password" ),
	allFields = $( [] ).add( name ).add( email ).add( password ),
	tips = $( ".validateTips" );

	//var divObj = $( "#dialog-form3" );
	$( "#dialog-form3" ).dialog({
		
		autoOpen: true,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
			"TheButton": function() {
				console.log("Inside dialog function...");
			
				var user = {};
				user.email = email_login.value;
				user.password = password_login.value;
				
				if((email_login.value == null || email_login.value=="")||(password_login.value == null || password_login.value=="")){
					document.getElementById("errorMessage").innerHTML = "Invalid Input. Please fill all details.";
					return;
				}

				var requestJson = JSON.stringify(user);
				console.log("Json String: "+requestJson);
				

				$.ajax({
					url: "GetLoginDetails",
					type: "POST",
					context: document.body,
					data: requestJson,
					success: function(data){
						if(data.errorCode == 200 && data.responseText == "Success"){
							console.log("success");
							console.log("Login Successful..!!");
							document.getElementById("login-user").style.display = "none";  
							document.getElementById("create-user").style.display = "none";
							document.getElementById("fblogin").style.display = "none";
							document.getElementById("logout-user").style.display = "inline-block";
							$( "#dialog-form1").dialog( "close" );
						}
						else{
							document.getElementById("errorMessage").innerHTML = "Invalid Input. Try again.";
							console.log("Uh-oh - Something went wrong, try again!");
						}
					}
				});

			},
			Cancel: function() {
				$( this ).dialog( "close" );
				parent.reloadContent(); // added by Rohini
			}
		},
		close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
			parent.reloadContent(); // added by Rohini
		}
	});
	
	function buttonClicked() {
		$( "#dialog-form3" ).dialog('open');
	}
	
	
/*
	$( "#logout-user" )
	.button()
	.click(function() {
		
		console.log("Logging Out..");
		document.getElementById("login-user").style.display = "inline-block";  
		document.getElementById("create-user").style.display = "inline-block";
		document.getElementById("fblogin").style.display = "inline-block";
		document.getElementById("logout-user").style.display = "none";  
		console.log("Logged out");
	});

*/
});


//added by Rohini
function reloadContent() {
    location.reload();
}
