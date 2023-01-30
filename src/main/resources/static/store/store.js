angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/mymarket/api/v1';

    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    }

    $scope.selectGroup = function () {
        console.log("Зашел в метод выбора группы продуктов");
        console.log(id);
    }

    $scope.showInfo = function (product) {
        alert(product.title);
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.nextPage = function () {
        currentPageIndex++;
        if (currentPageIndex > $scope.productsPage.totalPages) {
            currentPageIndex = $scope.productsPage.totalPages;
        }
        $scope.loadProducts(currentPageIndex);
    }

    $scope.prevPage = function () {
        currentPageIndex--;
        if (currentPageIndex < 1) {
            currentPageIndex = 1;
        }
        $scope.loadProducts(currentPageIndex);
    }

    $scope.navToEditProductPage = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.navToEditProductPage = function (productId) {
        $location.path('/update_product/' + productId);
    }

    $scope.addProductToCart = function (productId) {

        $http.get(contextPath + '/cart/' + $localStorage.springWebGuestCartId + '/add/' + productId)
            .then(function successCallback(response) {
                alert("Продукт успешно добавлен в корзину");
                }, function failCallback(response) {
                    alert(response.data.messages);
                }
            );
    }

    $scope.loadCategories = function () {
        $http.get(contextPath + '/products/categories')
            .then(function successCallback(response) {
                $scope.listCategories = response.data;
                console.log($scope.listCategories);
                }, function failCallback(response) {
                    alert(response.data.messages);
                }
            );
    }

    $scope.loadCategories();
    $scope.loadProducts(1);
});