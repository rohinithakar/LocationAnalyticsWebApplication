$(document).ready(function(){
var mygc = new google.maps.Geocoder();
var globalDivCount=0;
document.getElementById("addnew").addEventListener("click",manipulate);
document.getElementById("evaluate").addEventListener("click",evaluate);



//when user clicks on edit button it adds new class abc to show the image
$('#delete').on('click',function(event){
	$('span.deletehide').each(function( index ){
		$('.deletehide').addClass('deleteshow');
	});

});

//when user clicks on done button it removes the image button.
$('#done').on('click',function(event){

	$('span.deletehide').each(function( index ){
		$('.deletehide').removeClass('deleteshow');
	});
	
});

function deleteDeal(){
	alert("hi");
	//this.parent().remove();	
}

$(document.body).on('click','.deletehide',function(event){
	$(this).parent().remove();
});

function manipulate(){
	globalDivCount = globalDivCount+1;
	var tags=['Student','Event','Education','Shopping'];
	var parent = document.getElementById("promotiondealdetails");
	var element = document.createElement("div");
	element.setAttribute("id",globalDivCount);
	element.setAttribute("class","addable");
	
	//<span class="delete" id="cancel0"><img src="img/cancel_icon.gif"></span>
	//var newImageSpan = $(document.createElement('span')).attr({"id":"image-" + currentCity,"class":'image1'});
	//newImageSpan.after().html('<img src="img/cancel_icon.gif">');
	
	//create span tag
	var cancelSpan = document.createElement("span");
	cancelSpan.setAttribute("id", "image"+globalDivCount);
	cancelSpan.setAttribute("class", "deletehide");
	//cancelSpan.addEventListener("click",deleteDeal);
	
	//create image tag
	var cancelImage = document.createElement("img");
	cancelImage.setAttribute("src", "img/cancel_icon.gif");
	cancelSpan.appendChild(cancelImage);
	
	//name section
	var labelDeal = document.createElement("label");
	labelDeal.setAttribute("for", "dealName"+globalDivCount);
	labelDeal.innerHTML = "Event Name:";
	var textDeal = document.createElement("input");
	textDeal.setAttribute("type", "text");
	textDeal.setAttribute("id", "dealName"+globalDivCount);
	
	//desc section
	var labelDesc = document.createElement("label");
	labelDesc.setAttribute("for", "dealDescription"+globalDivCount);
	labelDesc.innerHTML = "Event Description:";
	var textDesc = document.createElement("textarea");
	textDesc.setAttribute("rows", "0");
	textDesc.setAttribute("cols", "20");
	textDesc.setAttribute("id", "dealDescription"+globalDivCount);
	
	//Deal Detail
	var labelDealDetail = document.createElement("label");
	labelDealDetail.setAttribute("for", "dealDetail"+globalDivCount);
	labelDealDetail.innerHTML = "Event Detail:";
	var textDealDetail = document.createElement("input");
	textDealDetail.setAttribute("type", "text");
	textDealDetail.setAttribute("id", "dealDetail"+globalDivCount);
	
	//Number of Deals 
	var labelNumberofDeals  = document.createElement("label");
	labelNumberofDeals .setAttribute("for", "noofDeals"+globalDivCount);
	labelNumberofDeals .innerHTML = "Event Tickets:";
	var textNumberofDeals = document.createElement("input");
	textNumberofDeals.setAttribute("type", "text");
	textNumberofDeals.setAttribute("id", "noofDeals"+globalDivCount);
	
	//Number of Deals 
	var labelNumberofDeals  = document.createElement("label");
	labelNumberofDeals .setAttribute("for", "noofAttendees"+globalDivCount);
	labelNumberofDeals .innerHTML = "Event Tickets:";
	var textNumberofDeals = document.createElement("input");
	textNumberofDeals.setAttribute("type", "text");
	textNumberofDeals.setAttribute("id", "noofAttendees"+globalDivCount);
	
	//Tags
	var labelTags = document.createElement("label");
	labelTags.setAttribute("for", "Tags"+globalDivCount);
	labelTags.innerHTML = "Tags";
	var selectTag = document.createElement("select");
	selectTag.setAttribute("multiple", "multiple");
	selectTag.setAttribute("id", "Tags"+globalDivCount);
	//loop for adding options
	for(var i=0;i<4;i++){
		var optionTags = document.createElement("option");
		optionTags.setAttribute("value", tags[i]);
		optionTags.innerHTML = tags[i];
		selectTag.appendChild(optionTags);
	}
	
	
	element.appendChild(cancelSpan);
	element.appendChild(labelDeal);
	element.appendChild(textDeal);
	element.appendChild(document.createElement("br"));
	element.appendChild(labelDesc);
	element.appendChild(textDesc);
	element.appendChild(document.createElement("br"));
	element.appendChild(labelDealDetail);
	element.appendChild(textDealDetail);
	element.appendChild(document.createElement("br"));
	element.appendChild(labelNumberofDeals);
	element.appendChild(textNumberofDeals);
	element.appendChild(document.createElement("br"));
	element.appendChild(labelTags);
	element.appendChild(selectTag);
	console.log(parent);
	parent.appendChild(element);
}


function evaluate(){
	
	var promotion={};
	
	//get all properties/values of promotion
	// 2 for Events.
	promotion.type=2;
	promotion.name=document.getElementById("promoName").value;
	promotion.startdate=document.getElementById("startDate").value;
	promotion.enddate=document.getElementById("endsDate").value;
	promotion.description=document.getElementById("desc").value;
	alert(document.getElementById("address").value);
	promotion.address=document.getElementById("address").value;
	promotion.city=document.getElementById("city").value;
	promotion.pincode=document.getElementById("pincode").value;
	
	var combineAddress = promotion.address + "," + promotion.city + " "+ promotion.pincode;
	
	//converting address to lat/long
	mygc.geocode({'address' : '\''+ combineAddress + '\''}, function(results, status){
	    console.log( "latitude : " + results[0].geometry.location.lat() );
	    console.log( "longitude : " + results[0].geometry.location.lng() );
	    
	    promotion.lat=document.getElementById(results[0].geometry.location.lat() );
		promotion.long=document.getElementById(results[0].geometry.location.lng());
	});
	
	
	
	
	//getting list of deals and creating a array name : deal 
	//var elements = document.getElementsByClassName("addable");
	var deals=[];
	var selectedtags=[];
	//alert(elements.length);
	var kids = $(".promotiondealdetails").children("div.addable");
	//alert(kids);
	
	
	
	for(var i=0;i<kids.length;i++){
		var deal={
				
				toString:function(){
					return this.name;
				}
		};
		var divid=kids[i].id;
			Ad.name = document.getElementById("dealName"+divid).value;
			Ad.description = document.getElementById("dealDescription"+divid).value;
			Ad.dealDetail = document.getElementById("dealDetail"+divid).value;
			Ad.noofDeals = document.getElementById("noofDeals"+divid).value;
			Ad.noofAttendees = document.getElementById("noofAttendees"+divid).value;
			Ad.scheduletime=document.getElementById("start_time"+divid) + "-" + document.getElementById("end_time"+divid);
			
			var tag = document.getElementById("Tags"+divid);
			
			for(var j=0;j<tag.length;j++){
				if(tag[j].selected){
					console.log("fff" + tag[j].value);
					selectedtags.push(tag[j].value);}
			}
			Ad.tags = selectedtags;
			selectedtags=[];
			
			deals.push(Ad);
	}
	
	promotion.deal=deals;
	console.log(promotion);
	callAjax(JSON.stringify(promotion));
	//alert(deals[1].name);
	/*for(var e in elements){
		alert(document.getElementById("dealName1").value);
	}*/
}

function callAjax(jsStr)
{
	
	//var formdata = "name=mahavir&age=31"
	//var country1 = 'mahavirswami';
	 $.ajax( {
         url:'LoginForm',
         type:'POST',
         dataType: 'json',
         //data:'country=1',
         data:jsStr,
         contentType: 'application/json; charset=utf-8',
         success:function(data) {
           alert(data);
         }
      });
}






});