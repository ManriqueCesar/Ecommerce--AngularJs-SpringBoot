angular.module("ClientesApp")
    .directive('tableClient', function() {
        return {
            template: ` 
            <div ng-hide="clientes.length == 0"> 
            <table class="table table-bordered table-striped">
                <thead>
                        <tr>
                            <th>ID</th>
                            <th>NOMBRE</th>
                            <th>APELLIDO</th>
                            <th>EMAIL</th>
                            <th>ACCION</th>
                        </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="cliente in clientes">
                        <td><a type="button" class="btn btn-primary" href="#/formulario/{{cliente.id}}">{{cliente.id}}</a></td>
                        <td>{{ cliente.nombre}}</td>
                        <td>{{ cliente.apellido}}</td>
                        <td>{{ cliente.email}}</td>
                        <td>
                           <a> <img src="img/icons/eliminar.png"   title="ELIMINAR" width=30px;  height=30px; type="button"></a> |
                           <a> <img src="img/icons/editar.png"   title="EDITAR" width=30px;  height=30px; type="button"></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
                    `

        }
    })
    .directive("backImg", function() {
        return function(scope, element, attrs) {
            attrs.$observe('backImg', function(value) {
                element.css({
                    'background': "url(" + value + ")",
                    'background-size': "cover",
                    'background-position': "center",
                });
            })
        }
    });