angular.module('market-front').controller('cartProductController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/mymarket';


    $scope.loadCart = function () {
        $http.get(contextPath + '/products/cart-content')
            .then(function successCallback(response) {
                    $scope.contentCart = response.data;
                }, function failCallback(response) {
                    alert(response.data.messages);
                }
            );
    }

    $scope.loadCart();
});