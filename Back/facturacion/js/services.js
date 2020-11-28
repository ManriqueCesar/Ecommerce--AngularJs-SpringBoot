angular.module("ClientesApp")
    .factory("ClienteResource", function($resource) {
        return $resource("http://localhost:8080/clientes/:id", { id: "@id" }, { update: { method: "PUT" } });
    })
    .factory("FacturaResource", function($resource) {
        return $resource("http://localhost:8080/facturas/:id", { id: "@id" }, { update: { method: "PUT" } });
    })
    .factory("ProductoResource", function($resource) {
        return $resource("http://localhost:8080/productos/:id", { id: "@id" }, { update: { method: "PUT" } });
    })