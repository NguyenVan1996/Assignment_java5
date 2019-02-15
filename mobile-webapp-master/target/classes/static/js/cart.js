$(document).ready(function() {
	var carQty = $("#cart-qty");
	$("#add-to-cart-btn").click(function() {
		var id = $(this).data("product-id");
		var req = "/cart/add/".concat(id);
		$.ajax({
			url : req,
			success : function(data) {
				carQty.text(data);
			}
		});
	});
});