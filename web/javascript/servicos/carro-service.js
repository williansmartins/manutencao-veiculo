angular
.module('principal')
.factory('CarroService', CarroService);

function CarroService ($q, $window, $http) {
    return {

        buscarTudo: function(){
            return $http({
                method : "GET",
                url : barramento + "/carros"
            })
        },

        buscarPorID : function (id) {
            return $http({
                method : "GET",
                url : barramento + "/carros/"+id
            })
        },

        criar : function (objeto) {
            return $http({
                method : "POST",
                url : barramento + "/carros",
                data: {
                    "fabricante": objeto.fabricante,
                    "modelo": objeto.modelo,
                    "ano": objeto.ano
                }
            })
        },

        atualizar : function (objeto) {
            return $http({
                method : "PUT",
                url : barramento + "/carros/" + objeto.id,
                data: {
                    "fabricante": objeto.fabricante,
                    "modelo": objeto.modelo,
                    "ano": objeto.ano
                }
            })
        },

        excluir : function (id) {
            return $http({
                method : "DELETE",
                url : barramento + "/carros/"+id
            })
        },
        
    };
}