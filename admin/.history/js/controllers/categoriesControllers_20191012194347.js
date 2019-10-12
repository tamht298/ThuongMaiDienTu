(function(module){
    module.controller('categoriesControllers',function($scope,$http){
        //$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
        var baseUrl='http://localhost:8080/api/categories';
        
        $http({
            method: "GET",
            url: "http://localhost:8080/api/categories"
        }).then(function mySuccess(response){
            $scope.categories=response.data;
        });

        $http.post()
    });
}(angular.module("myApp")));