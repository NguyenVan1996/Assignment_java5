$(document).ready(function() {
	$(".remove-btn").click(function(e) {
		var action = confirm("Bạn có muốn xóa tài khoản này không?");
		if (action) {
			var id = $(this).data("id");
			var req = "/admin/user/".concat(id);
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