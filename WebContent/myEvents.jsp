<!-- HomePage -->

<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@page import="utils.LocalizationHelper"%>
<%@page import="servlets.*" %>
<%@page import="java.util.*" %>
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

<link rel="stylesheet" type="text/css" href="css/demo1.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script src="js/modernizr.custom.js"></script>
<script src="js/DeleteDeal.js"></script>

<style type="text/css">
.TFtable {
	width: 80%;
	border-collapse: collapse;
}

.TFtable td {
	padding: 7px;
	/* border: #4e95f4 1px solid; */
	color: black;
}
/* provide some minimal visual accomodation for IE8 and below */
.TFtable tr {
	background: #b8d1f3;
}
.TFtable th {
	font-weight: bold;
	padding: 7px;
	border: #4e95f4 1px solid;
	background: #368E91;
	color: white;
}
/*  Define the background color for all the ODD background rows  */
.TFtable tr:nth-child(odd) {
	/* background: #b8d1f3; */
	/*background:#49bfc3; */
	background: #B8DBFF;
}
/*  Define the background color for all the EVEN background rows  */
.TFtable tr:nth-child(even) {
	/*background: #dae5f4; */
	/* background:#D2FAFF; */
	background: #DBFAFF;
}

.TFtable tr:hover {
  cursor: pointer;
  font-weight:bold;
  background-color: lightyellow;
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
		style="height: 1050px; margin: auto; width: 1019px; background-color: #ffffff; position: relative; margin-top: 0px">

		<div id="d_header">
			<div id="heading">

				<div class="left">
					<span class="s_heading">LocationAnalytics Manager</span><br> <span
						class="s_heading" style="font-size: 14px; float: left">
						Promote &#9679;Manage &#9679;Analyze </span>
				</div>

				<div class="right">
					<div
						style="display: inline-block; vertical-align: top; margin-left: 200pxx;">
						<a href="home.jsp" id="home"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
							style="font-size: 14px; color: #561243;" data-inline="true">
							<span class="ui-button-text">Home</span>
						</a>
						<%
							Integer userId = (Integer) session.getAttribute("userId");
						%>


						<a id="login-user"
							style="font-size: 14px; color: #561243; <%if (userId != null) {%>display:none; <%}%>"
							data-inline="true";>Login</a> <a id="logout-user"
							href="logOut.jsp"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
							style="font-size:14px;color: #561243;<%if (userId == null) {%>display:none; <%}%>"
							data-inline="true"; ><span class="ui-button-text">LogOut</span></a>
						
							<!-- <a href="javascript:login();"> <img src="img/fb-login.png"></a> -->
						</span>


						
						</FORM>
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
		<br>
		<div class="container">
			<div class="main clearfix">
				<table class="TFtable" align="center" id="viewDealsTable">
					<tr>
					<th>Promotion Name</th>
					<th>Description</th>
					<th>Promotion Type</th>
					<th>Status</th>
					<th>Delete</th>
					</tr>
					<%
					ServletHelper helper = new ServletHelper();
					List<DealDetailsPOJO> deals = helper.getPromotions(userId);
					%>
					<% for(DealDetailsPOJO deal : deals){ %>

					<tr>
						<td><%=deal.getDealname() %></td>
						<td><%=deal.getDealDesc() %></td>
						<% if((deal.getType()).equals("1")){%>
						<td>Promotional Deal</td>
						<% } else { %>
						<td>Promotional Event</td>
						<% } %>
						<% if(deal.getStatus()) {%>
						<td>Active</td>
						<% }else {%>
						<td>Expired</td>
						<% }  %>
						
						<td><button class="deleteDeal" promotionid="<%=deal.getDealid()%>" type="button" >Delete</button></td>
					</tr>
					<% }  %>
				</table>

			</div>
		</div>


	</div>

	<div class="d_footer">
		<span class="copyright">© 2013 LocationAnalyticsManager Inc.
			All Rights Reserved. | </span> <a href="AboutUS.jsp?cateogry=common"
			style="color: #87968E">About Us </a>
	</div>

	</div>

</body>



</html>