<!-- HomePage -->

<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<%@page import="utils.LocalizationHelper"%>
<html lang="en" class="no-js demo-1">
<head>
	<meta charset="UTF-8" />
<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>LocationAnalytics Manager</title>
	<meta name="description" content="LocationAnalyticsManager - Manage Your Business" />
	<meta name="keywords" content="Deals, Ads, Promotion, Discount, Coupon, Location Analytics, Location" />
	<meta name="author" content="Codrops" />
	
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> 
	
	<link rel="shortcut icon" href="../favicon.ico">
	<link rel="stylesheet" type="text/css" href="css/default.css" />
	<link rel="stylesheet" type="text/css" href="css/bookblock.css" />

	<link rel="stylesheet" type="text/css" href="css/demo1.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script src="js/modernizr.custom.js"></script> 

	<script src="js/jquerypp.custom.js"></script>
	<script src="js/jquery.bookblock.js"></script>
	<script src="js/slidercode.js"></script>

	<script src="js/registration.js"></script>
	<script src="js/login.js"></script>
	<script src="//connect.facebook.net/en_US/all.js"></script>
	<script src="js/fb.js"></script>
	<script src="js/forgotPassword.js"></script>
	<script src="js/addpromotion.js"></script>
	
	<style>
.butt {
	width: 300px;
	height: 100px;
}

.addable {
	margin: 0 auto;
	width: 600px;
	padding: 14px;
	background: #ebf4fb;
}

#promotiondetails {
	margin: 0 auto;
	width: 700px;
	padding: 14px; //
	border: solid 2px #b7cdf2;
	background: #B8DBFF;
}

textarea {
	width: 200px;
	padding-bottom: 5px;
	border-radius: 5px;
}

#Tags0 {
	width: 200px;
	padding-bottom: 5px;
	border-radius: 5px;
}

.deletehide {
	border: 1px solid black;
	float: right;
	opacity: 0;
}

.deleteshow {
	opacity: 1;
	float: right;
}

input[type="text"],input[type="date"] {
	width: 200px;
	padding-bottom: 5px;
	border-radius: 5px;
}

form label {
	display: inline-block;
	float: left;
	clear: left;
	font-size: 14px;
}

#addpromotionform {
	margin-bottom: 20px;
}
</style>
	
</head>
<body>

	<div id="wsb-canvas-template-page" class="wsb-canvas-page page"style="margin: auto; width: 1019px; background-color: #ffffff; position: relative; margin-top: 0px">

		<div id="d_header">
			<div id="heading">
			
			<div class="left">
				<span class="s_heading">LocationAnalytics Manager</span><br>
				<span class="s_heading" style="font-size: 14px; float:left">
				Manage&#9679;Promote&#9679;Analyze
				</span>
			</div>
			
		<div class="right">
				<div style="display:inline-block; vertical-align:top; margin-left:200pxx;">
				<a href="home.jsp" id="home" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 14px; color: #561243;" data-inline="true">
				<span class="ui-button-text">Home</span>
				</a>
				<% Integer userId = (Integer)session.getAttribute("userId"); %>
				
				<a id="login-user" style="font-size: 14px; color: #561243; <%if( userId != null){ %>display:none; <% } %>" data-inline="true";>Login</a>
				<a id="logout-user" href="logOut.jsp" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" 
				style="font-size:14px;color: #561243;<%if(userId == null){ %>display:none; <% } %>" data-inline="true"; ><span class="ui-button-text">SignOut</span></a>
				<a id="create-user" style="font-size: 14px; color: #561243; <%if(userId != null){ %>display:none; <% } %>" data-inline="true">Register</a>
				<span id = "fblogin" style="data-inline:true; <%if(userId != null){ %>display:none; <% } %>">
  				<a href="javascript:login();"> <img src="img/fb-login.png"></a>
   				</span>
   				
 				</div>
				
   				
  			    <div id = "fblogout" style="display:inline-block;visibility:hidden;">
  			    <img style="data-inline:true;" id="profile_pic"/>
  			    <div style="display:inline-block;">
  			    <span id="profile_name" style="data-inline:true;font-size:12px;visibility:hidden;"></span><br/>
  			    <a href="javascript:logout();"><img src="img/facebook_logout_button.png"></a> 
 				</div> 			    	    
  			    </div>
  			   
  			</div>
  			      
				
			</div>
		</div>
		<br>
		<div class="container">
		
		<form id="addpromotionform">
		<div id="promotiondetails">
		<h1>Add Promotion: Event</h1><br>
				<label for="promoname">Name:</label>
				<input type="text" id="promoName"><br>
				<label for="startDate">Start Date:</label>
				<input type="date" id="startDate"><br>
				<label for="endDate">End Date:</label>
				<input type="date" id="endsDate"><br>
				<label for="desc">Description:</label>
				<input type="text" id="desc"><br><br><br>
			<div id="address"><br>
				<label for="address">Address:</label>
				<textarea id="address"></textarea><br>
				<label for="city">City:</label>
				<textarea id="city"></textarea><br>
				<label for="pincode">Pincode:</label>
				<textarea id="pincode"></textarea><br>
			</div><br>
		
		<div class="promotiondealdetails" id="promotiondealdetails">
		<h2>Event Details: </h2>
		<div class="addable">
				<div>
				<label for="dealName0">Name:</label><input type="text" name="name" id="dealName0"><br><br>
				<label for="dealDescription0">Description</label><textarea name="desc" rows="0" cols="20" id="dealDescription0"></textarea><br>
				<label for="dealDetail0">Deal Detail</label><input type="text" name="dealDetail" id="dealDetail0"><br>
				<label for="noofDeals0">Deal Count</label><input type="text" name="noofDeals" id="noofDeals0"><br><br><br>
				<label for="Tags0">Tags</label>
					<select multiple="multiple" id="Tags0">
						<option value="Student">Student</option>
						<option value="Event">Event</option>
						<option value="Education">Education</option>
						<option value="Shopping">Shopping</option>
					</select><br>
				</div>
		</div>
		
		</div><br>
		<button type="button" id="addnew">Add New</button>
		<button type="button" id="evaluate">Evaluate</button>
		<!-- End of promotionaldetails container  --> 
		</div>
	</form>
		
		<!-- End of class container -->
		</div>
		</div>
		<br>
		
		<div class="d_footer">
			<span class="copyright">© 2013 LocationAnalyticsManager Inc. All Rights Reserved. | </span>
			<a href="AboutUS.jsp?cateogry=common" style="color:#87968E" >About Us </a> 
		</div>
		<br><br>
		
		
		
		<!-- start of button code -->
		  
			<div id="dialog-form" title="Register">
		<p class="validateTips">All form fields are required.</p>

		<form>
			<fieldset  style="display:inline;">
				<p id="error1" style="font-size:12px;color:red;"></p>
				<label for="fname">First Name</label>
				<input type="text"  name="name"  id="fname" placeholder='Jane' class="text ui-widget-content ui-corner-all" style="font-size: 12px;"/><br>
				<label for="lname">Last Name</label>
				<input type="text"  name="lname"  id="lname" placeholder='Doe' class="text ui-widget-content ui-corner-all" style="font-size: 12px;" /><br>
				<label for="email_reg">Email</label>
				<input type="email"  name="email_reg"  id="email_reg"  placeholder='jane@yahoo.com' class="text ui-widget-content ui-corner-all" style="font-size: 12px;" /><br>
				<label for="password">Password</label>
				<input type="password" name="password" id="password_reg" class="text ui-widget-content ui-corner-all" style="font-size: 12px;"/>
				<label for="question">Question</label>
				<input type="text" name="question" id="question" placeholder='Make of your first car?' class="text ui-widget-content ui-corner-all" style="font-size: 12px;"/>
				<label for="answer">Answer</label>
				<input type="text" name="answer" id="answer" class="text ui-widget-content ui-corner-all" style="font-size: 12px;"/>
			</fieldset>
		</form>
	</div> 
	
	  <div id="dialog-form1" title="Login">
		<p class="validateTips">All form fields are required.</p>

		<form id="login_form">
			<fieldset style="display:inline;">
				<p id="errorMessage" style="font-size:12px;color:red;"></p>
				<label for="email_login">Email</label> <input type="email" name="email_login" id="email_login" style="font-size:12px;" class="text ui-widget-content ui-corner-all" placeholder='jane@yahoo.com'/><br>
				<label for="password_login">Password</label> <input type="password" name="password_login" id="password_login" style="font-size:12px;" class="text ui-widget-content ui-corner-all"/> <br>
				<a href='javascript:forgotPassword();' id="forgotPwd" style="font-size: 12px;"data-inline="true">
				<span>Forgot Password?</span>
				</a> 
			</fieldset>
		</form>
	</div> 
	
	<!-- end of button code -->

	</div>

</body>



</html>