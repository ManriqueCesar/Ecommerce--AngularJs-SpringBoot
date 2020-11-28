angular.module("ClientesApp", ["ngRoute", "ngResource",'ngSanitize', 'ui.select'])
    .config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: "template/home.html",
                controller: "MainController"
            })
            .when('/cliente', {
                templateUrl: "template/cliente.html",
                controller: "ClientController"
            })
            .when('/formulario', {
                templateUrl: "template/formulario.html",
                controller: "NewClientController"
            })
            .when('/formulario/:id', {
                templateUrl: "template/formulario.html",
                controller: "LoadClientController"
            })
            .when('/factura', {
                templateUrl: "template/factura.html",
                controller: "FacturaController"
            })
            .when('/producto', {
                templateUrl: "template/newProducto.html",
                controller: "NewProductController"
            })
            .when('/producto/:id', {
                templateUrl: "template/producto.html",
                controller: "ProductController"
            })
            .when('/registrar/:id', {
                templateUrl: "template/registrar.html",
                controller: "LoadFacturaController"
            })

    });