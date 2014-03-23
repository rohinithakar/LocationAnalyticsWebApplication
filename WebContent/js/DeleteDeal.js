$(document).on("click", ".deleteDeal", function(e){
	console.log(e);
	var promotionid = $(e.target).attr("promotionid");
	console.log(promotionid);
	var obj = new Object();
	obj.promotionid = promotionid;

	var requestJson = JSON.stringify(obj);
	console.log("Json String: "+requestJson);


	$.ajax({
		url: "DeletePromotion",
		type: "POST",
		context: document.body,
		data: requestJson,
		success: function(data){
			if(data.errorCode == 200 && data.responseText == "Success"){
				console.log("success");
				location.reload();
			}
			else{
				console.log("Failed");
				alert("Promotion could not be deleted.");
			}

		}
	});


});