(function (module) {
	module.controller('single_product_controllers', function ($stateParams, $http, $scope, $rootScope, $localStorage) {
		var baseUrl = 'http://localhost:8080/api/';
		
		this.$onInit = function () {
			getdataSingleProducts();
			$scope.getProduct();
			getAccount();
			
			
		}
		console.log($rootScope.carts);
		
		$scope.images=[];
		$scope.defaultImage={};
		$scope.user={};
		getdataSingleProducts = function () {
			$http({
				method: "GET",
				url: baseUrl + 'products/' + $stateParams.id
			}).then(function mySuccess(response) {
				$scope.singleProducts = response.data;
				console.log($scope.singleProducts);
				$scope.images = $scope.singleProducts.images;
				$scope.defaultImage = $scope.images[0];
			});
		}
		$scope.change_image_slider = function (id) {
			console.log(id);
			$scope.images.forEach(img => {
				if(img.id===id){
					$scope.defaultImage = img;
				}
			});
		}
		$scope.getProduct=function(){
			$http({
				method:'GET',
				url: baseUrl+'products'	
			}).then(function mySucces(response){
				$scope.products = response.data;
			});
		}

		getAccount=function(){
			$http({
				method:'GET',
				url: baseUrl+'users?email='+ $localStorage.currentUser.email
			  }).then(function mySucces(response){
				$scope.user = response.data;
				console.log(response.data);
			
				
			  })
		}
		$scope.AddToCart=function(){
			console.log('add cart');
			
			
			if($localStorage.currentUser==null){
			  $location.path('/login');
			}else{
				let data ={
					product:$scope.singleProducts
				}
			  $http.post(baseUrl+'accounts/'+$scope.user.id+'/carts', data)
			  .then(function mySucces(res){
				console.log('thêm ok');
				
				//$rootScope.amountOfProducts=$rootScope.carts.items.length+1
				$http({
					method: "GET",
					url: baseUrl+'accounts/'+$rootScope.user.id+'/carts'
				  }).then(function mySuccess(response) {
					
					$rootScope.carts = response.data;
					$rootScope.amountOfProducts=$rootScope.carts.items.length;
				  });
				
			  },(err)=>{
				console.log('that bai');
			  });
	  
			}
		  }
	});
}(angular.module('myApp')));