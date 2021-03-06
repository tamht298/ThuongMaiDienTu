(function(module){
    module.controller('ListProductController', function($scope,$http, $stateParams){
        var baseUrl = 'http://localhost:8080/api/';
     
        
        this.$onInit=function(){
            getdataSingleProducts();
            console.log('init');
            
           
            
        }
        
        getdataProducts = function () {
			$http({
				method: "GET",
				url: baseUrl + 'products/' + $stateParams.id
			}).then(function mySuccess(response) {
				$scope.singleProducts = response.data;
				
				$scope.images = $scope.singleProducts.images;
				$scope.defaultImage = $scope.images[0];
			});
		}

    });
}(angular.module('myApp')));