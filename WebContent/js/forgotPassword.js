

function forgotPassword(){
	console.log("Inside forgotPassword function..");
	var newHTML1 =  "<fieldset style=\"display:inline;\"><p id=\"errorMessage\" style=\"font-size:12px;\"></p><label for=\"email\">Email</label>" +
	" <input type=\"text\" name=\"email\" id=\"email\" class=\"text ui-widget-content ui-corner-all\" />";

	document.getElementById("login_form").innerHTML = newHTML1;
	// $($("button", $("#dialog-form1").parent())[1])[0].style.display = "none";

	// getter
	var buttons = $( "#dialog-form1" ).dialog( "option", "buttons" );

	// setter

	$( "#dialog-form1" ).dialog({ buttons: [ { text: "Question", click: function() {

		var user = {};
		var userEmail = email.value;
		user.email = email.value;

		var requestJson = JSON.stringify(user);
		console.log("Json String: "+requestJson);

		$.ajax({
			url: "ForgotPasswordQuestion",
			type: "POST",
			data: requestJson,
			context: document.body,
			success: function(data){
				if(data.errorCode == 200){
					console.log("Sucess");
					console.log("Question: "+data.responseText);
					var newHTML2 = "<fieldset style=\"display:inline;\"><p id=\"errorMessage\" style=\"font-size:12px;\"></p><label for=\"question\">Question</label>" +
					" <input type=\"text\" name=\"question\" value=\""+data.responseText+"\" id=\"question\" class=\"text ui-widget-content ui-corner-all\" />" +
					"<br><label for=\"answer\">Answer</label> <input type=\"password\" name=\"answer\" id=\"answer\"" +
					" style=\"font-size:12px;\" class=\"text ui-widget-content ui-corner-all\"/></fieldset>";



					document.getElementById("login_form").innerHTML = newHTML2;

					// button setter
					$( "#dialog-form1" ).dialog( "option", "buttons", [ { text: "OK", click: function() {
						//code for submitting answer to question

						var usr = {};
						usr.value = $('#dialog-form1').find('input[name="answer"]').val();
						usr.email = userEmail;

						var requestJson2 = JSON.stringify(usr);
						console.log("Json String for answer: "+requestJson2);

						$.ajax({

							url: "ForgotPasswordQuestionValidation",
							type: "POST",
							data: requestJson2,
							context: document.body,
							success: function(data){
								if(data.errorCode == 200){
									console.log("in 200");
									//show new password setup

									var newHTML3 = "<fieldset style=\"display:inline;\"><p id=\"errorMessage\" style=\"font-size:12px;\"></p><label for=\"newPwd\">New Password</label>" +
									" <input type=\"password\" name=\"newPwd\" id=\"newPwd\" class=\"text ui-widget-content ui-corner-all\" />" +
									"<label for=\"confirmPwd\">Confirm Password</label><input type=\"password\" name=\"confirmPwd\" id=\"confirmPwd\"" +
									" style=\"font-size:12px;\" class=\"text ui-widget-content ui-corner-all\"/></fieldset>";

									document.getElementById("login_form").innerHTML = newHTML3;

									// button setter
									$( "#dialog-form1" ).dialog( "option", "buttons", [ { text: "SetPassword", click: function() {
										//code for setting new password
										var password = {};
										password.value = $('#dialog-form1').find('input[name="newPwd"]').val();
										password.email = userEmail;
										var requestJson3 = JSON.stringify(password);
										console.log("Json String for answer: "+requestJson3);

										$.ajax({ 
											//make ajax call
											
											url: "SetNewPassword",
											type: "POST",
											data: requestJson3,
											context: document.body,
											success: function(data){
												//write success response code for new passsword setup
												if(data.errorCode == 200){
													var newHTML4 = "Password reset successful";
													console.log("Password reset successful");
													document.getElementById("login_form").innerHTML = newHTML4;
													$( "#dialog-form1" ).dialog( "option", "buttons", [ { text: "Ok", click: function() { $( this ).dialog( "close" ); } } ] );
												}
												else{
													//Password reset failed.
													$("#errorMessage").innerHTML = "Password Reset Failed. Try again.";
												}
											}

										});

									} } ] );


								}else{
									console.log("in 202");
									$("#errorMessage").html("Erorr. Try Again.");
								}
							}
						}


						);


					} } ] );



				}
				else{
					//error message code when question cant be fetched from database
					$("#errorMessage").innerHTML = "Internal Erorr. Try Again.";
				}
			}
		});


	} } ] });

}

