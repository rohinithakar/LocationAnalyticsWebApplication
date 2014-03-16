$(document).ready(function(){
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

$(document.body).on('click','.deleteshow',function(event){
	$(this).parent().remove();
});

function manipulate(){
	globalDivCount = globalDivCount+1;
	var tags=['Student','Event','Education','Shopping'];
	var parent = document.getElementById("promotiondealdetails");
	var element = document.createElement("div");
	element.setAttribute("class","addable");
	
	//<span class="delete" id="cancel0"><img src="img/cancel_icon.gif"></span>
	//var newImageSpan = $(document.createElement('span')).attr({"id":"image-" + currentCity,"class":'image1'});
	//newImageSpan.after().html('<img src="img/cancel_icon.gif">');
	
	//create span tag
	var cancelSpan = document.createElement("span");
	cancelSpan.setAttribute("id", "image"+globalDivCount);
	cancelSpan.setAttribute("class", "deleteshow");
	//cancelSpan.addEventListener("click",deleteDeal);
	
	//create image tag
	var cancelImage = document.createElement("img");
	cancelImage.setAttribute("src", "img/cancel_icon.gif");
	cancelSpan.appendChild(cancelImage);
	
	//name section
	var labelDeal = document.createElement("label");
	labelDeal.setAttribute("for", "dealName"+globalDivCount);
	labelDeal.innerHTML = "Name:";
	var textDeal = document.createElement("input");
	textDeal.setAttribute("type", "text");
	textDeal.setAttribute("id", "dealName"+globalDivCount);
	
	//desc section
	var labelDesc = document.createElement("label");
	labelDesc.setAttribute("for", "dealDescription"+globalDivCount);
	labelDesc.innerHTML = "Description:";
	var textDesc = document.createElement("textarea");
	textDesc.setAttribute("rows", "0");
	textDesc.setAttribute("cols", "20");
	textDesc.setAttribute("id", "dealDescription"+globalDivCount);
	
	//Deal Detail
	var labelDealDetail = document.createElement("label");
	labelDealDetail.setAttribute("for", "dealDetail"+globalDivCount);
	labelDealDetail.innerHTML = "Deal Detail:";
	var textDealDetail = document.createElement("input");
	textDealDetail.setAttribute("type", "text");
	textDealDetail.setAttribute("id", "dealDetail"+globalDivCount);
	
	//Number of Deals 
	var labelNumberofDeals  = document.createElement("label");
	labelNumberofDeals .setAttribute("for", "noofDeals"+globalDivCount);
	labelNumberofDeals .innerHTML = "Number of Deals:";
	var textNumberofDeals = document.createElement("input");
	textNumberofDeals.setAttribute("type", "text");
	textNumberofDeals.setAttribute("id", "noofDeals"+globalDivCount);
	
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
	element.appendChild(document.createElement("br"));
	element.appendChild(labelDesc);
	element.appendChild(textDesc);
	element.appendChild(document.createElement("br"));
	element.appendChild(document.createElement("br"));
	element.appendChild(labelDealDetail);
	element.appendChild(textDealDetail);
	element.appendChild(document.createElement("br"));
	element.appendChild(document.createElement("br"));
	element.appendChild(labelNumberofDeals);
	element.appendChild(textNumberofDeals);
	element.appendChild(document.createElement("br"));
	element.appendChild(document.createElement("br"));
	element.appendChild(labelTags);
	element.appendChild(selectTag);
	console.log(parent);
	parent.appendChild(element);
}


function evaluate(){
	var elements = document.getElementsByClassName("addable");
	var deals=[];
	var selectedtags=[];
	alert(elements.length);
	for(var i=0;i<elements.length;i++){
		var deal={
				
				toString:function(){
					return this.name;
				}
		};
			deal.name = document.getElementById("dealName"+i).value;
			deal.description = document.getElementById("dealDescription"+i).value;
			deal.dealDetail = document.getElementById("dealDetail"+i).value;
			deal.noofDeals = document.getElementById("noofDeals"+i).value;
			var tag = document.getElementById("Tags"+i);
			
			for(var j=0;j<tag.length;j++){
				if(tag[j].selected){
					console.log("fff" + tag[j].value);
					selectedtags.push(tag[j].value);}
			}
			deal.tags = selectedtags;
			
			deals.push(deal);
	}
	alert(deals[0].name);
	/*for(var e in elements){
		alert(document.getElementById("dealName1").value);
	}*/
}



});