angular.module('market-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/mymarket';

    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex) {
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

    $scope.delProduct = function (id) {
        $http.delete(contextPath + '/products' + '/' + id)
            .then(function successCallback(response) {
                    $scope.loadProducts(currentPageIndex);
                }, function failCallback(response) {
                    alert(response.data.message);
                }
            );
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
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

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/products', $scope.new_product)
            .then(function successCallback(response) {
                    $scope.loadProducts(currentPageIndex);
                    $scope.new_product = null;
                }, function failCallback(response) {
                    alert(response.data.message);
                }
            );
    }

    $scope.prepareProductForUpdate = function (productId) {
        $http.get(contextPath + '/products/' + productId)
            .then(function successCallback(response) {
                    $scope.update_product = response.data;
                }, function failCallback(response) {
                    alert(response.data.message);
                }
            );
    }

    $scope.updateProduct = function () {
        $http.put(contextPath + '/products', $scope.update_product)
            .then(function successCallback(response) {
                    $scope.update_product = null;
                    $scope.loadProducts(currentPageIndex);
                    alert("Продукт успешно обновлен");
                }, function failCallback(response) {
                    alert(response.data.message);
                }
            );
    }

    $scope.loadProducts(1);

});