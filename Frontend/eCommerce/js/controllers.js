angular
  .module("ClientesApp")

  .controller("MainController", function ($scope, ProductoResource, CarritoResource, $location) {
    $scope.loading = true;
    $scope.title = "Catálogo de Productos";
    $scope.carritoCantidad = 0;



    $scope.productos = ProductoResource.query(function (data) {
      $scope.productos = data;
    });


    //Cantidad de productos en carrito
    $scope.carritos = CarritoResource.query(function (data) {
      $scope.carritos = data;
      $scope.carritoCantidad = data.length;
    });

    $scope.verCarrito = function () {
      $location.url('/carrito/1')
    };
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
              "¡Cliente " + cliente.nombre + " eliminado correctamente!",
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
    $scope.clientes = ClienteResource.query(function (data) {
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
        ClienteResource.save($scope.cliente, function (cliente) {
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
        );
      };
    }
  )
  .controller("LoadFacturaController",
    function (
      $scope,
      FacturaResource,
      ClienteResource,
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

      //cargar clienteEmail por id
      $scope.selections = [];
      $scope.selectionChanged = function (idx) {
        $scope.clienteId = $scope.selections[idx].id;
        $scope.email = ClienteResource.query(function (data) {
          $scope.email = data[$scope.clienteId - 1].email;
        });
      };


   
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

      $scope.items = [];

      //preparar items (idProducto y cantidad)
      class item {
        constructor(productoId, cantidad) {
          this.productoId = productoId;
          this.cantidad = cantidad;
        }
      };
      
      $http.get("http://localhost:8080/carrito/detalle/1")
        .success(function (data) {
          $scope.productos = data;
          for (i = 0; i < $scope.productos.length; i++) {
            $scope.items.push(new item($scope.productos[i].idProducto, $scope.productos[i].cantidad));
          } 
        });

      //generar Factura
      $scope.saveFactura = function () {
        $scope.productoId = $scope.productos.id;
        $scope.request = {};
  

        $scope.request.observacion = "Ninguna";
        $scope.request.descripcion = $scope.obseravacion;
        $scope.request.idCliente = $scope.clienteId;
        $scope.request.items = $scope.items;

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
            let mensaje = error.data.arreglo.join();
            console.log(error);
            Swal.fire({
              icon: "error",
              title: error.data.message,
              text: mensaje,
              showConfirmButton: false,
              timer: 2000,
            });
            $scope.productoAgotados = "";
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
        console.log(error);
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
    $scope.stock = false;

    //cargar productos por id
    $scope.productos = ProductoResource.get({
      id: $routeParams.id,
    });


    $scope.stock = $scope.productos.cantidad;

    $scope.loading = false;

    $scope.cantidad = {
      value: 1
    };

    $scope.addQty = function () {
      if ($scope.cantidad.value < $scope.productos.cantidad)
        $scope.cantidad.value = $scope.cantidad.value + 1;
    };
    $scope.reduceQty = function () {
      if ($scope.cantidad.value > 0)
        $scope.cantidad.value = $scope.cantidad.value - 1;
    };

    //agregar al carrito
    $scope.agregarCarrito = function () {
      $http.get("http://localhost:8080/carrito/detalle/" + 1)
        .success(function (data) {
          $scope.carritos = [];
          $scope.carritos = data;
          console.log(data);
          for (i = 0; i < data.length; i++) {
            console.log(data.length);
            if ($routeParams.id == $scope.carritos[i].idProducto) {
              console.log($scope.carritos[i].idProducto);
              $scope.temp = true;
              break;
            }
          }
          if ($scope.productos.cantidad === 0) {

            Swal.fire({
              position: "top-end",
              icon: "error",
              title: "¡Error al agregar al carrito!",
              showConfirmButton: false,
              timer: 2000,
            });

          }
          else {
            console.log(" HAY PRODUCTO")
          }

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
                  icon: 'success',
                  title: 'Agregado al carrito',
                  text: 'Lo podrás ver en MiCarrito',
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
          } else {
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
                $location.url('carrito/' + 1)
              }
            })
          }
        }
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
  .controller("CarritoController", function ($scope, $http, CarritoResource, $location) {
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

    $scope.quitarProducto = function (carritoId) {
      CarritoResource.delete(
        {
          id: carritoId,
        },
        function () {
          Swal.fire({
            position: "top-end",
            icon: "info",
            title: "Producto eliminado del carrito",
            showConfirmButton: false,
            timer: 2000,
          });
        }
      );

      $scope.carritos = $scope.carritos.filter(function (element) {
        console.log("total es:" + $scope.total);
        return element.id !== carritoId;
      });
    };

    $scope.redirect = function () {
      $location.url("/");
    }

    $scope.agregarCantidad = function (id) {
      console.log("agregado");
      $scope.carrito.cantidad = $scope.carrito.cantidad + 1;
    },
      $scope.restarCantidad = function (id) {
        console.log("reducido");
      }
  }
  );
