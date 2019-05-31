angular.module('principal')
.controller('HomeController', ['$scope', '$uibModal', '$log', '$document', '$location', '$window', '$filter', 'HomeService', '$rootScope', '$localStorage','$rootScope', 
	function ($scope, $uibModal, $log, $document, $location, $window, $filter, HomeService, $rootScope, $localStorage, $rootScope) {

    $scope.temErro = false;
    $scope.tela = 1;
    $scope.mensagem = "";
    $scope.form = {
    	"name": "",
    	"email": "contato@williansmartins.com", 
    	"password": "martin"
    };
    $scope.$storage = $localStorage;
    $scope.apontamentosDoDia = {};
    $scope.todosApontamentos = {};
    $scope.todasDescricoes = {};
    $scope.sucesso = false;
    $scope.entidadeSelecionada = null;
    $scope.apontamentosOrganizados = null;
    $scope.titulo = "Manutenção de Veículos";
    $scope.titulo = "---";

    $scope.mudarTela = function(tela){
    	$scope.tela = tela;

        if(tela==1){
            $scope.titulo = "Apontamento de horas";
        }
    	if(tela==2){
    		buscarApontamentosDoDia();
            $scope.titulo = "Apontamento de HOJE";
    	}

        if(tela==3){
            $('.itens').hide();
            buscarTodosApontamentos();
            $scope.titulo = "Todos os apontamentos";
        }

        if(tela==4){
            $scope.titulo = "Editando um apontamento";
        }

        if(tela==5){
            $scope.titulo = "Editando uma descrição";
        }

        if(tela==6){
            $('.itens').hide();
            buscarApontamentosDoMesAtual();
            $scope.titulo = "Mês atual";
        }
    }

	$scope.sair = function(){
    	$window.location.href = "#login";
    }

    $scope.voltar = function(){
        $scope.mudarTela(3);
    }

    init = function() {
    };

	init();
}]);