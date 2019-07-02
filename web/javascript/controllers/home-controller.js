angular.module('principal')
.controller('HomeController', ['$scope', '$uibModal', '$log', '$document', '$location', '$window', '$filter', '$rootScope', '$localStorage','$rootScope', 'ManutencaoService', 
	function ($scope, $uibModal, $log, $document, $location, $window, $filter, $rootScope, $localStorage, $rootScope, ManutencaoService) {

 //    $scope.temErro = false;
 //    $scope.tela = 3;
 //    $scope.mensagem = "";
 //    $scope.form = {
 //    	"name": "",
 //    	"email": "contato@williansmartins.com", 
 //    	"password": "martin"
 //    };
 //    $scope.$storage = $localStorage;
 //    $scope.lista = {};
 //    $scope.sucesso = false;
 //    $scope.itemSelecionado = null;
 //    $scope.tituloHome = "Manutenção de Veículos";
 //    $scope.titulo = $scope.tituloHome;

 //    $scope.mudarTela = function(tela){
 //    	$scope.tela = tela;

 //        if(tela==1){
 //            $scope.titulo = $scope.tituloHome;
 //        }
 //    	if(tela==2){
 //            buscarTudo();
 //            $scope.titulo = "Manutenções realizadas";
 //    	}

 //        if(tela==3){
 //            $scope.titulo = "Edição de Manutenção";
 //        }

 //    }

	// $scope.sair = function(){
 //    	$window.location.href = "#login";
 //    }

 //    $scope.voltar = function(){
 //        $scope.mudarTela(3);
 //    }

 //    var buscarTudo = function(){
 //        ManutencaoService.buscarTudo()
 //        .success(function(response, status, a){ 
 //            $scope.lista = response;
 //            console.info(response); 
 //        })
 //        .error(function(response, status, a){ 
 //            console.info(response); 
 //        })
 //    }

 //    var buscarPorID = function(){
 //        ManutencaoService.buscarPorID(4051)
 //        .success(function(response){ 
 //            console.info(response); 
 //        })
 //        .error(function(response){ 
 //            console.info(response); 
 //        })
 //    }

 //    var criar = function(){
 //        var objeto = {
 //            "fabricante": "Honda-WEB",
 //            "modelo": "FIT",
 //            "ano": "2010"
 //        }

 //        ManutencaoService.criar(objeto)
 //        .success(function(response){ 
 //            console.info(response); 
 //        })
 //        .error(function(response){ 
 //            console.info(response); 
 //        })
 //    }

 //    var atualizar = function(){
 //        var objeto = {
 //            "id" : "3",
 //            "fabricante": "Honda-Novo",
 //            "modelo": "FIT",
 //            "ano": "2010"
 //        }

 //        ManutencaoService.atualizar(objeto)
 //        .success(function(response){ 
 //            console.info(response); 
 //        })
 //        .error(function(response){ 
 //            console.info(response); 
 //        })
 //    }

 //    $scope.excluir = function(item){
 //        ManutencaoService.excluir(item.id)
 //        .success(function(response){ 
 //            console.info(response); 
 //            buscarTudo();
 //        })
 //        .error(function(response){ 
 //            console.info(response); 
 //        })
 //    }

 //    $scope.salvar(){
 //    	if(isEmpytNullOrUndefined($scope.itemSelecionado.id)){
 //            ContasService.inserir($scope.itemSelecionado)
 //            .success(function(response){
 //                $scope.buscarContas();
 //                $scope.itemSelecionado = new Object();
 //            })
 //            .error(function(response){
 //                console.error(response);
 //                $scope.mensagem("Ocorreu um erro.");
 //            });
 //        }else{
 //            ContasService.atualizar($scope.itemSelecionado)
 //            .success(function(response){
 //                $scope.buscarContas();
 //                $scope.itemSelecionado = new Object();
 //            })
 //            .error(function(response){
 //                console.error(response);
 //                $scope.mensagem("Ocorreu um erro.");
 //            });
 //        }
 //    }

 //    init = function() {
 //        // buscarTudo();
 //        // buscarPorID();
 //        // excluir();
 //        // atualizar();
 //    };

	init();
}]);