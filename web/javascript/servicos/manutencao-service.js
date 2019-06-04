angular
.module('principal')
.factory('ManutencaoService', ManutencaoService);

function ManutencaoService ($q, $window, $http) {
    return {

        buscarTudo: function(){
            return $http({
                method : "GET",
                url : barramento + "/manutencoes"
            })
        },

        buscarPorID : function (id) {
            return $http({
                method : "GET",
                url : barramento + "/manutencoes/"+id
            })
        },

        criar : function (objeto) {
            return $http({
                method : "POST",
                url : barramento + "/manutencoes",
                data:  {
                    "id_veiculo": objeto.id_veiculo,
                    "id_usuario": objeto.id_usuario,
                    "data": objeto.data,
                    "categoria": objeto.categoria,
                    "kilometragem": objeto.kilometragem,
                    "observacoes": objeto.observacoes
                }
            })
        },

        atualizar : function (objeto) {
            return $http({
                method : "PUT",
                url : barramento + "/manutencoes/" + objeto.id,
                data:  {
                    "id_veiculo": objeto.id_veiculo,
                    "id_usuario": objeto.id_usuario,
                    "data": objeto.data,
                    "categoria": objeto.categoria,
                    "kilometragem": objeto.kilometragem,
                    "observacoes": objeto.observacoes
                }
            })
        },

        excluir : function (id) {
            return $http({
                method : "DELETE",
                url : barramento + "/manutencoes/"+id
            })
        },
        
    };
}