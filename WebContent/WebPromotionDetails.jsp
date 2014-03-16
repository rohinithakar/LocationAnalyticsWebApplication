<!doctype html>
<html>
<head>
	<title> Add and Delete Button on Fly</title>
	<style>
		.butt{
			width:300px;
			height:100px;
		}
		
		.addable{
		border:1px solid;
		 background:yellow;
		}
		
		#promotiondetails{
			border: 1px solid black;
		}
		
		div{
		margin: 20px;}
		
		.deletehide{
			border:1px solid black;
			float:right;
			opacity:0;
		}
		
		.deleteshow{
			opacity:1;		
		}
	</style>
	</head>
	<body>
	<form>
		<div id="promotiondetails">
		Promotion Details:<br>
				<label for="promoname">Name:</label>
				<input type="text" id="promoName">
				<label for="startDate">Start Date:</label>
				<input type="date" id="startDate">
				<label for="endDate">End Date:</label>
				<input type="date" id="endsDate">
				<label for="desc">Description:</label>
				<textarea id="desc"></textarea>
			<div id="address">
				<label for="address">Address:</label>
				<textarea id="address"></textarea>
				<label for="city">City:</label>
				<textarea id="city"></textarea>
				<label for="pincode">Pincode:</label>
				<textarea id="pincode"></textarea>
			</div>
		</div>
		
		<div class="promotiondealdetails" id="promotiondealdetails">
		Promotion Deal Details
		<div class="addable">
				<span class="deletehide" id="cancel0"><img src="img/cancel_icon.gif"></span>
				<label for="dealName0">Name:</label><input type="text" name="name" id="dealName0"><br>
				<label for="dealDescription0">Description</label><textarea name="desc" rows="0" cols="20" id="dealDescription0"></textarea><br>
				<label for="dealDetail0">Deal Detail</label><input type="text" name="dealDetail" id="dealDetail0"><br>
				<label for="noofDeals0">Number of Deals</label><input type="text" name="noofDeals" id="noofDeals0"><br>
				<label for="Tags0">Tags</label>
					<select multiple="multiple" id="Tags0">
						<option value="Student">Student</option>
						<option value="Event">Event</option>
						<option value="Education">Education</option>
						<option value="Shopping">Shopping</option>
					</select>	
				
		</div>
		
		</div>
		<button type="button" id="addnew">Add New</button>
		<button type="button" id="delete">Delete</button>
		<button type="button" id="done">Done</button>
		<button type="button" id="evaluate">Evaluate</button>
	</form>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="js/script.js"></script>
	</body>
</html>