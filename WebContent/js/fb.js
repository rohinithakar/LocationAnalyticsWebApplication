  
(function(d, s, id) {
var js, fjs = d.getElementsByTagName(s)[0];
if (d.getElementById(id)) return;
js = d.createElement(s); js.id = id;
js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=682447581775162";
fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


window.fbAsyncInit = function()
        {
            FB.init
            (
                {
                    appId   : "682447581775162",
                    status  : true,
                    cookie  : true,
                    oauth   : true
                }
            );
            
            FB.getLoginStatus(function(response) {
            	  if (response.status === 'connected') {
            	    // the user is logged in and has authenticated your
            	    // app, and response.authResponse supplies
            	    // the user's ID, a valid access token, a signed
            	    // request, and the time the access token 
            	    // and signed request each expire
            	    var uid = response.authResponse.userID;
            	    var accessToken = response.authResponse.accessToken;
            	    console.log(response);
            	    getLoginInformation(false);
            	  } else if (response.status === 'not_authorized') {
            	    // the user is logged in to Facebook, 
            	    // but has not authenticated your app
            		  console.log("not authorized");
            	  } else {
            	    // the user isn't logged in to Facebook.
            	  }
            	 });
        };

        function login()
        {
        	console.log("Inside Login..");
            FB.login
            (
                function( response )
                {
                    if ( response.authResponse )
                    {
                    	console.log(response);
                    	getLoginInformation(true);
                    	
                        
                    } else {
                    	console.log("in else");
                    	console.log(response);
                    }
                },
                { scope: 'email'}
            );
        }
        
        function logout()
        {
          document.getElementById("login-user").style.display = "inline-block";  
          document.getElementById("login-user").style.visibility = "visible";   
          document.getElementById("create-user").style.display = "inline-block"; 
      	  document.getElementById("create-user").style.visibility = "visible";  
      	  
      	  document.getElementById("fblogin").style.visibility = "visible";
      	  $("#fblogin")[0].style.display = "inline-block";
    	  //document.getElementById("fblogout").style.visibility = "hidden";   	  
      	  document.getElementById("fblogout").style.display = "none"; 
      	document.getElementById("viewSummary").style.display = "none"; 
      	document.getElementById("logout-user").style.disolay = "none";
        	
          document.getElementById( "profile_name" ).style.visibility = "hidden";
          document.getElementById( "profile_pic" ).style.visibility = "hidden";

            
            FB.logout(function(response) {
            	  // user is now logged out
            	document.location.href = "logOut.jsp";
            	});
        }
        
        function getLoginInformation(flag) {
        	FB.api
            (
                "/me",
                function( response )
                {   
                	console.log("Response:"+response);
                	
                	var query = FB.Data.query('select name,email,hometown_location, sex, pic_square from user where uid={0}', response.id);
                    query.wait(function(rows) {
                      var email = rows[0].email;
                      console.log("Email: "+email);
                      
                      var user = {};
                      user.email = email;
                      var requestJson = JSON.stringify(user);
      				  console.log("Json String: "+requestJson);
      				  
                      $.ajax({
      					url: "FBRegisterLoginUser",
      					type: "POST",
      					context: document.body,
      					data: requestJson,
      					success: function(data){
      						if(data.errorCode == 200 && data.responseText == "Success"){
      							console.log("Login Successful..!!");
      							if(flag == true){
      							location.reload();
      							}
      						
      							document.getElementById("fblogin").style.visibility = "hidden";
      		                	document.getElementById("fblogin").style.display = "none";
      		                	document.getElementById("login-user").style.display = "none";  
      		                	document.getElementById("create-user").style.display = "none";  
      		                	document.getElementById("viewSummary").style.display = "inline-block"; 
      		                	document.getElementById("fblogout").style.visibility = "visible"; 
      		                	
      		                	$("#profile_name")[0].style.visibility = "visible";
      		                	$("#profile_pic")[0].style.visibility = "visible";
      		                	$("#profile_name")[0].style.display = "inline-block";
      		                	$("#profile_pic")[0].style.display = "inline-block";
      		                	$("#profile_pic")[0].src = "http://graph.facebook.com/" + response.id + "/picture";
      		                	
      		                	$("#profile_name")[0].innerHTML = response.name;

      						}
      						else{
      							alert("Login Failed. Try Again.");
      						}
      					}
      				});   
                	
                }
            );
        });
        }
        