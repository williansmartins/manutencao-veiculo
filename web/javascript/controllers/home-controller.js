angular.module('principal')
.controller('HomeController', ['$scope', '$uibModal', '$log', '$document', '$location', '$window', '$filter', '$rootScope', '$localStorage','$rootScope', 'ManutencaoService', 
	function ($scope, $uibModal, $log, $document, $location, $window, $filter, $rootScope, $localStorage, $rootScope, ManutencaoService) {

    $scope.temErro = false;
    $scope.tela = 2;
    $scope.mensagem = "";
    $scope.form = {
    	"name": "",
    	"email": "contato@williansmartins.com", 
    	"password": "martin"
    };
    $scope.$storage = $localStorage;
    $scope.lista = {};
    $scope.sucesso = false;
    $scope.itemSelecionado = new Object();
    $scope.tituloHome = "Manutenção de Veículos";
    $scope.titulo = $scope.tituloHome;

    $scope.mudarTela = function(tela){
    	$scope.tela = tela;

        if(tela==1){
            $scope.titulo = $scope.tituloHome;
        }
    	if(tela==2){
            buscarTudo();
            $scope.titulo = "Manutenções realizadas";
    	}

        if(tela==3){
            $scope.titulo = "Edição de Manutenção";
        }

    }

	$scope.sair = function(){
    	$window.location.href = "#login";
    }

    var buscarTudo = function(){
        ManutencaoService.buscarTudo()
        .success(function(response, status, a){ 
            $scope.lista = response;
            console.info(response); 
        })
        .error(function(response, status, a){ 
            console.info(response); 
        })
    }

    var buscarPorID = function(){
        ManutencaoService.buscarPorID(4051)
        .success(function(response){ 
            console.info(response); 
        })
        .error(function(response){ 
            console.info(response); 
        })
    }

    var criar = function(){
        ManutencaoService.criar($scope.itemSelecionado)
        .success(function(response){ 
            console.info(response); 
        })
        .error(function(response){ 
            console.info(response); 
        })
    }

    var atualizar = function(){
        ManutencaoService.atualizar($scope.itemSelecionado)
        .success(function(response){ 
            console.info(response); 
        })
        .error(function(response){ 
            console.info(response); 
        })
    }

    $scope.excluir = function(item){
        ManutencaoService.excluir(item.id)
        .success(function(response){ 
            console.info(response); 
            buscarTudo();
        })
        .error(function(response){ 
            console.info(response); 
        })
    }

    $scope.salvar = function(){
        if($scope.itemSelecionado.id){
            atualizar();
        }else{
            criar();
        }

        
        resetItemSelecionado();
    }

    $scope.prepararParaEditar = function(item){
        $scope.itemSelecionado = item;
        $scope.mudarTela(3);
    }

    $scope.novaManutencao = function(item){
        $scope.itemSelecionado = {
            categoria: item, 
            data: "", 
            kilometragem: "", 
            modelo: "", 
            observacoes: ""
        };
        $scope.mudarTela(3);
    }

    var resetItemSelecionado = function(){
        $scope.itemSelecionado = {
            categoria: "", 
            data: "", 
            kilometragem: "", 
            modelo: "", 
            observacoes: ""
        };
    }

    init = function() {
        buscarTudo();
        //buscarPorID();
        // excluir();
        // atualizar();
    };

	init();
}]);