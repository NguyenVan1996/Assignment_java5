var app = angular.module("profileApp", []);

app.filter('formatPrice', function() {
	return function(x) {
		return x.toLocaleString('vi-VN');
	};
});

app.controller("profileController", function($scope, $http) {

	$('#myList [href="#profile"]').tab("show");

	$('#myList [href="#profile"]').on('click', function(e) {
		e.preventDefault()
		$(this).tab('show');
	});

	$('#myList [href="#change-password"]').on('click', function(e) {
		e.preventDefault()
		$(this).tab('show');
	});

	$http({
		method : "GET",
		url : "/profile/user-info"
	}).then(function onSuccess(response) {
		$scope.user = response.data;

		$scope.genders = [ {
			name : "Nam",
			value : true
		}, {
			name : "Nữ",
			value : false
		} ];

		if ($scope.user.gender != null) {
			if ($scope.user.gender)
				$scope.selectedGender = $scope.genders[0];
			else
				$scope.selectedGender = $scope.genders[1];
		}
	}, function onError(response) {
		alert(response.statusText);
	});

	$scope.changeFullname = function(user) {
		$http({
			method : "PUT",
			url : "/profile/change-fullname",
			data : user.fullname
		}).then(function onSuccess(response) {
			if (response.data)
				location.reload();
		}, function onError(response) {
			alert("Cập nhật thất bại");
		});
	}

	$scope.changeAddress = function(user) {
		$http({
			method : "PUT",
			url : "/profile/change-address",
			data : user.address
		}).then(function onSuccess(response) {
			if (response.data)
				location.reload();
		}, function onError(response) {
			alert("Cập nhật thất bại");
		});
	}

	$scope.changeGender = function(selectedGender) {
		$http({
			method : "PUT",
			url : "/profile/change-gender",
			data : selectedGender.value
		}).then(function onSuccess(response) {
			if (response.data)
				location.reload();
		}, function onError(response) {
			alert("Cập nhật thất bại");
		});
	}

	$http({
		method : "GET",
		url : "/profile/order/total-order"
	}).then(function onSuccess(response) {
		$scope.totalOrder = response.data;
	}, function onError(response) {
		alert(response.statusText);
	});

	$('#myList [href="#order-history"]').on('click', function(e) {
		e.preventDefault()
		$(this).tab('show');
		$http({
			method : "GET",
			url : "/profile/order/history"
		}).then(function onSuccess(response) {
			$scope.orders = response.data;
		}, function onError(response) {
			alert(response.statusText);
		});
	});

	$scope.viewOrder = function(id) {
		$http({
			method : "GET",
			url : "/profile/order/" + id
		}).then(function onSuccess(response) {
			$scope.orderLines = response.data;
			$("#orderDetailModal").modal("show");
		}, function onError(response) {
			alert("Lỗi server!");
		});
	}
	
	$scope.changePassword = function(){
	}

});