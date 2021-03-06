(function(module){
    module.controller('SearchController',function($http,$stateParams,$scope,$rootScope,$localStorage, $location){
        var baseUrl = 'http://localhost:8080/api/';
        this.$onInit=function(){
            getResult();
        }
        
        getResult =function(){
            if($scope.key!=null){
                $scope.eror=true;
                $http({
                    method:'GET',
                    url:baseUrl+'products/search-by-name?categoryId='+$stateParams.id+'&name='+$stateParams.key
                }).then(function mySusses(response){
                    $scope.result=response.data;
                    console.log($stateParams.id);
                    console.log($stateParams.key);
                    console.log($scope.result);
                    if($scope.result.length==0){
                        $scope.eror=false;
                    }
                })
            }else{
                alert('Chưa nhập từ khóa tìm kiếm');
                
            }
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
       
		$scope.AddToCart=function(id){
		
			
			
			if($localStorage.currentUser==null){
			  $location.path('/login');
			}else{
				$http.get(baseUrl+'products/'+id).then(function mySucces(res){
					let data ={
						product:res.data
					}
					console.log(res.data);
				})
				
				
				
			  /
		  }
    });
}(angular.module('myApp')));