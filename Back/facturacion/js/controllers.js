angular
  .module("ClientesApp")

  .controller("MainController", function ($scope, ProductoResource) {
    $scope.loading = true;
    $scope.title = "Catálogo de Productos";
    //cargar productos
    $scope.productos = ProductoResource.query(function (data) {
      $scope.productos = data;
    });
  })

  .controller("ClientController", function ($scope, ClienteResource) {
    $scope.loading = true;
    $scope.title = "Lista de Clientes";
    
    // Eliminar cliente
    $scope.clientes = ClienteResource.query();
    $scope.removeCliente = function (cliente) {
      ClienteResource.delete(
        {
          id: cliente.id,
        },
        function () {
          Swal.fire({
            position: "top-end",
            icon: "info",
            title:
              "¡Cliente " +
              '"' +
              cliente.nombre +
              '"' +
              " eliminado correctamente!",
            showConfirmButton: false,
            timer: 2000,
          });
        }
      );
      $scope.clientes = $scope.clientes.filter(function (element) {
        return element.id !== cliente.id;
      });
    };

    // Cargar cliente
    $scope.clientes = ClienteResource.query(
      function (data) {
        $scope.clientes = data;
        $scope.loading = false;
      },
      function (error) {
        $scope.loading = false;
        Swal.fire({
          position: "top-end",
          icon: "error",
          title: "¡No se encontraron resultados! " + error,
          showConfirmButton: false,
          timer: 2000,
        });
      }
    );
  })
  .controller("NewClientController", function ($scope, ClienteResource, $location) {
    $scope.title = "Crear Cliente";
    $scope.cliente = {};

    //crear
    $scope.saveClient = function () {
      ClienteResource.save(
        $scope.cliente,
        function (cliente) {
          console.log(cliente);
          Swal.fire({
            position: "top-end",
            icon: "success",
            title: "¡Cliente " + cliente.nombre + " creado correctamente!",
            showConfirmButton: false,
            timer: 2000,
          });
          $location.path("/");
        },
        function (error) {
          $scope.loading = false;
          Swal.fire({
            position: "top-end",
            icon: "error",
            title: "¡Error al crear!" + error,
            showConfirmButton: false,
            timer: 2000,
          });
        }
      );
    };
  }
  )
  .controller("LoadClientController",
    function ($scope, ClienteResource, $routeParams) {
      $scope.title = "Editar Cliente";

      //cargar cliente por id
      $scope.cliente = ClienteResource.get({
        id: $routeParams.id,
      });

      //editar cliente
      $scope.editClient = function () {
        ClienteResource.update(
          {
            id: $scope.cliente.id,
          },
          $scope.cliente,
          function (cliente) {
            console.log(cliente);
          }
        );
      };
    }
  )
  .filter('propsFilter', function () {
    return function (items, props) {
      var out = [];

      if (angular.isArray(items)) {
        var keys = Object.keys(props);

        items.forEach(function (item) {
          var itemMatches = false;

          for (var i = 0; i < keys.length; i++) {
            var prop = keys[i];
            var text = props[prop].toLowerCase();
            if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
              itemMatches = true;
              break;
            }
          }

          if (itemMatches) {
            out.push(item);
          }
        });
      } else {
        // Let the output be the input untouched
        out = items;
      }

      return out;
    };
  })
  .controller("LoadFacturaController",
    function (
      $scope,
      FacturaResource,
      $routeParams,
      ClienteResource,
      ProductoResource
    ) {
      $scope.title = "Boleta de Compra";
      $scope.clienteId = 0;
      // $scope.fecha = new Date();
      // $scope.hora = new Date();

      $scope.descripcion = [{
        id: 1,
        titulo: 'Equipos electrónicos'
      }, {
        id: 2,
        titulo: 'Productos de Belleza'
      }, {
        id: 3,
        titulo: 'Casa y Hogar'
      }];

      //cargar clientes
      $scope.clientes = ClienteResource.query(function (data) {
        $scope.clientes = data;
      });

      //cargar producto por id [pendiente]
      $scope.productos = ProductoResource.get({
        id: $routeParams.id,
      }, function(){
        $scope.productos.cantidad = 1;
      $scope.monto= "S/ " + $scope.productos.precio * $scope.productos.cantidad;
      });

      //cargar clienteEmail por id
      $scope.selections = [];
      $scope.selectionChanged = function (idx) {
        $scope.clienteId = $scope.selections[idx].id
        $scope.email = ClienteResource.query(function (data) {
          $scope.email = data[$scope.clienteId - 1].email;
        });
      };


      //generar Factura
      $scope.saveFactura = function () {
        $scope.productoId = $scope.productos.id;
        $scope.request = {};

        items = [
          { cantidad:2,
            productoId: $scope.productoId
          }
        ];

        // $scope.items.cantidad = 2;
        // $scope.items.productoId = $scope.productoId;
        $scope.request.observacion = "Ninguna";
        $scope.request.descripcion = $scope.obseravacion;
        $scope.request.idCliente = $scope.clienteId;
        $scope.request.items = items;

        console.log($scope.request);

        FacturaResource.save($scope.request,
          function () {
            Swal.fire({
              icon: "success",
              title: "¡Factura creada correctamente!",
              showConfirmButton: false,
              timer: 2000,
            });
          },
          function (error) {
            $scope.loading = false;
            Swal.fire({
              position: "top-end",
              icon: "error",
              title: "¡Error al crear!" + error,
              showConfirmButton: false,
              timer: 2000,
            });
          }
        );
      }
    }
  )
  .controller("FacturaController", function ($scope, FacturaResource) {
    // Cargar
    $scope.facturas = FacturaResource.query(
      function (data) {
        $scope.facturas = data;
      },
      function (error) {
        $scope.loading = true;
        Swal.fire({
          position: "top-end",
          icon: "error",
          title: "¡No se encontraron resultados! " + error,
          showConfirmButton: false,
          timer: 2000,
        });
      }
    );
  })
  .controller("ProductController",
    function ($scope, ProductoResource, $routeParams) {
      //cargar productos por id
      $scope.productos = ProductoResource.get({
        id: $routeParams.id,
      });
    }
  )

  .controller("ProductoController", function ($scope, ProductoResource) {
    $scope.loading = true;

    // Cargar
    $scope.clientes = ProductoResource.query(
      function (data) {
        $scope.productos = data;
        $scope.loading = false;
        console.log(data);
      },
      function (error) {
        $scope.loading = false;
        Swal.fire({
          position: "top-end",
          icon: "error",
          title: "¡No se encontraron resultados! " + error,
          showConfirmButton: false,
          timer: 2000,
        });
      }
    );
  });
