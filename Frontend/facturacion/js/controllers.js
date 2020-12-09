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
    $scope.clientes = ClienteResource.query( function (data) {
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
  .controller(
    "NewClientController", function ($scope, ClienteResource, $location) {
      $scope.title = "Crear Cliente";
      $scope.cliente = {};

      //crear
      $scope.saveClient = function () {
        ClienteResource.save( $scope.cliente, function (cliente) {
            Swal.fire({
              position: "top-end",
              icon: "success",
              title: "¡Cliente " + cliente.nombre + " creado correctamente!",
              showConfirmButton: false,
              timer: 2000,
            });
            $location.url("/");
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
  .controller(
    "LoadClientController", function ($scope, ClienteResource, $routeParams) {
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
  .controller( "LoadFacturaController",
   function (
      $scope,
      FacturaResource,
      $routeParams,
      ClienteResource,
      ProductoResource,
      $http
    ) {

      $scope.title = "Boleta de Compra";
      $scope.clienteId = 0;
      $scope.productos = [];
      $scope.descripcion = [
        {
          id: 1,
          titulo: "Equipos electrónicos",
        },
        {
          id: 2,
          titulo: "Productos de Belleza",
        },
        {
          id: 3,
          titulo: "Casa y Hogar",
        },
      ];

      //cargar clientes
      $scope.clientes = ClienteResource.query(function (data) {
        $scope.clientes = data;
      });

    //cargar lista de productos del carrito por Clienteid 
      

         $http.get("http://localhost:8080/carrito/detalle/1")
         .success(function (data) {
          $scope.monto = 0;
           $scope.loading = false;
           $scope.productos = data;
           for (i = 0; i < data.length; i++) {
             $scope.monto = $scope.monto + ($scope.productos[i].precio * $scope.productos[i].cantidad);
 
           }
           
         });

     

      //cargar clienteEmail por id
      $scope.selections = [];
      $scope.selectionChanged = function (idx) {
        $scope.clienteId = $scope.selections[idx].id;
        $scope.email = ClienteResource.query(function (data) {
          $scope.email = data[$scope.clienteId - 1].email;
        });
      };

      //generar Factura
      $scope.saveFactura = function () {
        $scope.productoId = $scope.productos.id;
        $scope.request = {};

        items = [{ cantidad: 2, productoId: $scope.productoId }];

        // $scope.items.cantidad = 2;
        // $scope.items.productoId = $scope.productoId;
        $scope.request.observacion = "Ninguna";
        $scope.request.descripcion = $scope.obseravacion;
        $scope.request.idCliente = $scope.clienteId;
        $scope.request.items = items;

        FacturaResource.save(
          $scope.request,
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
      };
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
  .controller("ProductController", function ($scope, $http, $location, ProductoResource, $routeParams, CarritoResource) {
    $scope.loading = true;
    $scope.temp = false;

    //cargar productos por id
    $scope.productos = ProductoResource.get({
      id: $routeParams.id,
    });

    $scope.loading = false;

    $scope.cantidad = {
      value: 1
    };

    $scope.addQty = function () {
      if($scope.cantidad.value<99)
      $scope.cantidad.value=$scope.cantidad.value +1;
    };
    $scope.reduceQty = function () {
      if($scope.cantidad.value>0)
      $scope.cantidad.value=$scope.cantidad.value -1;
      };

         //agregar al carrito
    $scope.agregarCarrito = function () {
      $http.get("http://localhost:8080/carrito/detalle/" + 1)
        .success(function (data) {
          $scope.carritos = [];
          $scope.carritos = data;

          for (i = 0; i < data.length; i++) {
            if ($routeParams.id == $scope.carritos[i].idProducto) {
              $scope.temp = true;
              break;
            }}
            if ($scope.temp == false) {
              $scope.request = {};
              $scope.request.idCliente = 1;
              $scope.request.idProducto = $routeParams.id;
              $scope.request.cantidad = $scope.cantidad.value;

              //agregar al carrito
              $scope.carritos = CarritoResource.save(
                $scope.request,
                function () {
                  Swal.fire({
                    title: 'Producto Agregado al Carrito',
                    text: "Lo podrás ver en Mi Carrito",
                    icon: 'success',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Seguir Comprando',
                    cancelButtonText: 'Ir al Carrito'
                  }).then((result) => {
                    if (result.isConfirmed) {
                      $location.url('/home');
                    }
                    else if (result.isCanceled) {
                      console.log("cancelado")
                    }
                  })
                    , function () {
                      Swal.fire({
                        position: "top-end",
                        icon: "error",
                        title: "¡Error al agregar al carrito!",
                        showConfirmButton: false,
                        timer: 2000,
                      });
                    }
                })
              } else{
                Swal.fire({
                  title: 'El producto ya se encuentra en tu Carrito',
                  icon: 'warning',
                  showCancelButton: true,
                  confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                  confirmButtonText: 'Ver mi carrito',
                  cancelButtonText: 'Entendido'
                }).then((result) => {
                  if (result.isConfirmed) {
                    $location.url('carrito/'+1)
                  }
                })
              }}
        );
        

        
    }
  })

  .controller("ProductoController", function ($scope, ProductoResource) {
      $scope.loading = true;

      // Cargar
      $scope.clientes = ProductoResource.query(
        function (data) {
          $scope.productos = data;
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
    .controller("CarritoController", function (
      $scope,
      $http
    ) {
      $scope.title = "Carrito de Compra";
      $scope.carritos = [];
      $scope.total = 0;
      //cargar carrito por Clienteid 
      $http.get("http://localhost:8080/carrito/detalle/1")
        .success(function (data) {

          $scope.loading = false;
          $scope.carritos = data;
          for (i = 0; i < data.length; i++) {
            $scope.total = $scope.total + ($scope.carritos[i].precio * $scope.carritos[i].cantidad);

          }
        });

      $scope.agregarCantidad = function (id) {
        console.log("agregado");
        $scope.carrito.cantidad = $scope.carrito.cantidad + 1;
      },
        $scope.restarCantidad = function (id) {
          console.log("reducido");
        }
    }
    );
