(function(module){
    module.controller('ListProductController', function($scope,$http, $stateParams){
        var baseUrl = 'http://localhost:8080/api/';
     
        
        this.$onInit=function(){
            getResult();
            console.log('init');
            
           
            
        }
        
        getResult =function(){
            if($scope.key!=null){
                $scope.eror=true;
                $http({
                    method:'GET',
                    url:baseUrl+'products/'+ $stateParams.id
                }).then(function mySusses(response){
                    $scope.result=response.data;
                    console.log('list ok');
                    
                    if($scope.result.length==0){
                        $scope.eror=false;
                    }
                })
    }
}

    });
}(angular.module('myApp')));