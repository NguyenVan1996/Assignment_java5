$(document).ready(function() {
	$(".remove-btn").click(function(e) {
		var action = confirm("Bạn có muốn xóa thương hiệu này không?");
		if (action) {
			var id = $(this).data("id");
			var req = "/admin/product/brand/".concat(id);
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