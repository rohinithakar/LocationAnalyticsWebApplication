<!-- HomePage -->

<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@page import="utils.LocalizationHelper"%>
<html lang="en" class="no-js demo-1">
<head>
<meta charset="UTF-8" />
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>LocationAnalytics Manager</title>
<meta name="description"
	content="LocationAnalyticsManager - Manage Your Business" />
<meta name="keywords"
	content="Deals, Ads, Promotion, Discount, Coupon, Location Analytics, Location" />
<meta name="author" content="Codrops" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
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


<script>	
 
	   function changeToSpanish()
       {
           document.form1.hiddenLanguage.value = "spanish";
            form1.submit();

       }    
       function changeToEnglish()
       {
           document.form1.hiddenLanguage.value = "english";

           form1.submit();

       }    
       
       
  function redirect() {
		<%if (session.getAttribute("userId") == null) {%>
	alert("Please login");
<% } %>
}
	
</script>

<style>
.actionlist {
	display: block;
	max-width: 100%;
	text-align: center;
	font-size: 2em;
	font-weight: bold;
	padding: 0px;
}

.actionlist li {
	list-style: none;
	list-style-type: none;
	margin: 1em 5em;
}

.actionlist li a {
	background-color: #49bfc3;
	padding: 1em;
	border-radius: 10px;
	color: white;
	display: inline-block;
	width: 100%;
	min-height: 100%;
}
</style>

</head>

<%
if(session.getAttribute("userId") == null){
	System.out.println("Invalid session");
	response.sendRedirect("index.jsp"); 
}
%>
<body>

	<div id="wsb-canvas-template-page" class="wsb-canvas-page page"
		style="margin: auto; width: 1019px; background-color: #ffffff; position: relative; margin-top: 0px">

		<div id="d_header">
			<div id="heading">

				<div class="left">
					<% 		
			 String lang = request.getParameter("hiddenLanguage");
			
			if (lang == null)
			{
				lang = "english";
				
			} 
			
			session.removeAttribute("lang");
			session.setAttribute("lang", lang);
			System.out.println("session is " + session.getAttribute("lang"));
			LocalizationHelper helper = LocalizationHelper.getInstance(lang, getServletContext());
			
			
			%>
					<span class="s_heading"><%=helper.getText("heading")%></span><br>
					<span class="s_heading" style="font-size: 14px; float: left">
						<%=helper.getText("heading2part1")%>&#9679;<%=helper.getText("heading2part2")%>&#9679;<%=helper.getText("heading2part3")%>
					</span>
				</div>

				<div class="right">
					<div
						style="display: inline-block; vertical-align: top; margin-left: 200pxx;">
						<a href="home.jsp" id="home"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
							style="font-size: 14px; color: #561243;" data-inline="true">
							<span class="ui-button-text"><%=helper.getText("home")%></span>
						</a>
						<% Integer userId = (Integer)session.getAttribute("userId"); %>
						<a id="login-user"
							style="font-size: 14px; color: #561243; <%if( userId != null){ %>display:none; <% } %>"
							data-inline="true";><%=helper.getText("login")%></a> <a
							id="logout-user" href="logOut.jsp"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
							style="font-size:14px;color: #561243;<%if(userId == null){ %>display:none; <% } %>"
							data-inline="true"; ><span class="ui-button-text"><%=helper.getText("logout")%></span></a>
						<a id="create-user"
							style="font-size: 14px; color: #561243; <%if(userId != null){ %>display:none; <% } %>"
							data-inline="true"><%=helper.getText("register")%></a> <span
							id="fblogin"
							style="data-inline:true; <%if(userId != null){ %>display:none; <% } %>">
							<a href="javascript:login();"> <img src="img/fb-login.png"></a>
						</span> <span id="language"
							style="data-inline:true; <%if (userId != null) {%>display:block; <%}%>">

						</span>
					</div>


					<div id="fblogout"
						style="display: inline-block; visibility: hidden;">
						<img style="data-inline: true;" id="profile_pic" />
						<div style="display: inline-block;">
							<span id="profile_name"
								style="data-inline: true; font-size: 12px; visibility: hidden;"></span><br />
							<a href="javascript:logout();"><img
								src="img/facebook_logout_button.png"></a>
						</div>
					</div>

				</div>


			</div>
		</div>
		<div class="container">
			<!-- /container -->

			<div id="category">
				<ul class="actionlist">
					<li><a href="addEvent.jsp">Add Event</a></li>
					<li><a href="addPromotion.jsp">Add Promotion</a></li>
					<li><a href="myEvents.jsp">View My Deals</a></li>
					<li><a href="viewReports.jsp">Generate Report</a></li>
				</ul>
			</div>

			<!-- End of Container -->
		</div>
		<br>
		<!-- start of button code -->


		<!-- end of button code -->

	</div>
	<div class="d_footer">
		<span class="copyright">© 2013 LocationAnalyticsManager Inc.
			All Rights Reserved. | </span> <a href="AboutUS.jsp?cateogry=common"
			style="color: #87968E">About Us </a>
	</div>
	<br>
	<br>
</body>



</html>