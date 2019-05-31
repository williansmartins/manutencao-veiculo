angular.module('principal')
.controller('LoginController', ['$scope', '$uibModal', '$log', '$document', '$location', '$window', '$filter', 'LoginService', '$rootScope', '$localStorage','$rootScope', 'CarroService', 
	function ($scope, $uibModal, $log, $document, $location, $window, $filter, LoginService, $rootScope, $localStorage, $rootScope, CarroService) {

    $scope.temErro = false;
    $scope.mensagem = "";
    $scope.form = {
    	"name": "",
    	"email": "",
    	"password": ""
    };
    $scope.$storage = $localStorage;
    $scope.flagMostrarLogin = true;
    $scope.popularEmail = function(){
    	$scope.form.email = "contato@williansmartins.com";
    }

    $scope.solicitar = function(){
    	console.info("solicitando para:");
    	console.info($scope.form);

    	LoginService.validarDisponibilidade($scope.form)
        .success(function(response, status){
            if(status == 406){
        		apresentarMensagem("Usuário não disponível");
        	}else{
        		cadastrar();
        	}
        })
        .error(function(response){
        	
        	console.info(response);
            apresentarMensagem("Usuário indisponível:");
        });
    }

    var cadastrar = function(){

    	//validar email

    	//validar usuario
    	LoginService.signup($scope.form)
        .success(function(response, status){
    		apresentarMensagem("Cadastro efetuado com sucesso, a partir de agora você pode entrar com seu usuário e senha.");
    		$scope.flagMostrarLogin = true;
        })
        .error(function(response){
            apresentarMensagem("Erro ao solicitar cadastro");
        });
    }

    $scope.login = function(){
        LoginService.login($scope.form)
        .success(function(response){

            //TODO: nao era pra ser assim, erro de login tinha que cair em error...
            if(response.status=="error"){
                apresentarMensagem("Houve um problema ao tentar fazer o login.");
            }else{
                $scope.nome = response.user.name;
                $scope.tipo = response.user.tipo;
                $localStorage.nome = response.user.name;
                $localStorage.token = response.token;
                $localStorage.tipo = response.user.tipo;
                $scope.temErro = false;
                $scope.$storage.usuarioLogado = true;
                
                var objetoGlogal = {
                	"localstorage" : $localStorage,
                	"flagMostrarMenu" : true
                }

                $rootScope.$broadcast('topic', objetoGlogal);

                $location.path("/home");
            }

        })
        .error(function(response){
            $scope.$storage.usuarioLogado = false;
            $scope.temErro = true;
            apresentarMensagem("Houve um problema ao tentar fazer o login.");
        });
    }

    var limparDadosDeLogin = function(){

     	var objetoGlogal = {
        	"localstorage" : null,
        	"flagMostrarMenu" : null
        }

        $rootScope.$broadcast('topic', objetoGlogal);
    }

    var buscarTudo = function(){
        CarroService.buscarTudo()
        .success(function(response, status, a){ 
            console.info(response); 
        })
        .error(function(response, status, a){ 
            console.info(response); 
        })
    }

    var buscarPorID = function(){
        CarroService.buscarPorID(3)
        .success(function(response){ 
            console.info(response); 
        })
        .error(function(response){ 
            console.info(response); 
        })
    }

    var criar = function(){
        var objeto = {
            "fabricante": "Honda-WEB",
            "modelo": "FIT",
            "ano": "2010"
        }

        CarroService.criar(objeto)
        .success(function(response){ 
            console.info(response); 
        })
        .error(function(response){ 
            console.info(response); 
        })
    }

    var atualizar = function(){
        var objeto = {
            "id" : "3",
            "fabricante": "Honda-Novo",
            "modelo": "FIT",
            "ano": "2010"
        }

        CarroService.atualizar(objeto)
        .success(function(response){ 
            console.info(response); 
        })
        .error(function(response){ 
            console.info(response); 
        })
    }

    var excluir = function(){
        CarroService.excluir(2)
        .success(function(response){ 
            console.info(response); 
        })
        .error(function(response){ 
            console.info(response); 
        })
    }

    init = function() {
        limparDadosDeLogin();
        // buscarTudo();
        buscarPorID();
        // excluir();
        //atualizar();
    };

	init();
}]);