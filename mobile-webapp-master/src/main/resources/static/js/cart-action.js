$(document).ready(function() {
	$(".cart-update-btn").on("click", function() {
		var key = $(this).data("cart-id");
		var qtyId = "#qty-cart-item-".concat(key);
		var quantity = $(qtyId).val();
		var url = "/cart/update";
		$.ajax({
			type : "GET",
			url : url,
			data : {
				key : key,
				quantity : quantity
			},
			success : function() {
				location.reload();
			}
		});
	});

	$(".cart-remove-btn").on("click", function() {
		var action = confirm("Bạn có muốn xóa sản phẩm này ra khỏi giỏ hàng không?");
		if(action){
			var key = $(this).data("cart-id");
			var url = "/cart/remove";
			$.ajax({
				type : "GET",
				url : url,
				data : {
					key : key
				},
				success : function() {
					location.reload();
				}
			});
		}
	});
});