angular.module('market-front').controller('regController', function ($scope, $location, $http) {
    const contextPath = 'http://localhost:8189/mymarket/api/v1';

    $scope.tryRegistrUser = function () {
        if ($scope.user1 == null) {
            alert("Форма не заполнена");
            return;
        }
        $http.post(contextPath + '/reg', $scope.user1)
            .then(function successCallback(response) {
                    alert(response.data.message);
                    $scope.user1 = null;
                    $location.path('/welcome');
                }, function failCallback(response) {
                    alert(response.data.message);
                    $scope.user1 = null;
                }
            );
    }


});