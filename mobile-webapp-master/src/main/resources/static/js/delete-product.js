$(document).ready(function() {
	$(".remove-btn").click(function(e) {
		e.preventDefault();
		var verify = confirm("Bạn có muốn xóa sản phẩm này không?");
		if (verify) {
			var uri = "/admin/product/";
			var id = $(this).data("id");
			var req = uri.concat(id);
			$.ajax({
				url : req,
				type : 'DELETE',
				success : function(result) {
					if (result) {
						alert("Xóa thành công!");
						location.reload();
					} else
						alert("Xóa thất bại!");
				}
			});
		}
	});
});