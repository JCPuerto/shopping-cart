
$(function () {

	var addToCart = function () {

    	var $form = $(this).parents('form:first');
    	
    	var p = { id : $form.attr("data-sc-productId") };
    	
        var options = {
    		contentType: 'application/json; charset=utf-8',
        	url: $form.attr("action"),
            type: $form.attr("method"),
            data: JSON.stringify(p)
        };
    	
        $.ajax(options).done(function (data) {
        	
        	var $target = $("#cart");
        	var $newHtml = $(data.cartTextDisplay);
        	$target.replaceWith($newHtml);
        	//$newHtml.effect("highlight");
        	console.log("item added");
    	});
        
        return false;
    };
    
	var removeFromCart = function () {

    	var $form = $(this).parents('form:first');
    	
    	var p = { id : $form.attr("data-sc-productId") };
    	
        var options = {
    		contentType: 'application/json; charset=utf-8',
        	url: $form.attr("action"),
            type: $form.attr("method"),
            data: JSON.stringify(p)
        };
        
        console.log(p);
    	
        $.ajax(options).done(function (data) {
        	
        	var $target = $("#cart");
        	var $newHtml = $(data.cartTextDisplay);
        	$target.replaceWith($newHtml);
        	//$newHtml.effect("highlight");
        	
        	console.log(data.orderItems);
        	
        	$.each(data.orderItems, function(key, orderItem) {
        		  var $tr = $("#tr" + orderItem.id);
        		  $tr.find("#qty" + orderItem.id).html(orderItem.qty)
        		  console.log("orderItem.id: " + orderItem.id + " orderItem.qty: " + orderItem.qty)
        		  if (orderItem.qty == 0) {
        			  console.log("Deleteing " + orderItem.id)
        			  //$form.parents('tr:first').remove();
        		  }
        			  	  
        		});

        	$("#totalQty").html('<h3>' + data.totalQty + '</h3>');
        	$("#totalPrice").html('<h3>$' + data.total.toFixed(2) + '</h3>');
        	
        	console.log("item deleted");
    	});
        
        return false;
    };
    
    $(".container").on("click", "#tblProducts a", addToCart);
    $(".container").on("click", "#tblCart a", removeFromCart);
});