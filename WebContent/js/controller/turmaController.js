app.controller('TurmaController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
    'use strict';
    var url = '/CDD/rest/';
  
    $scope.newRegister = function (){
        $scope.openCreate($scope.init);
    };
  
    $scope.editRegister = function(model){
        $scope.openEdit(model,$scope.init);
    };
  
    $scope.newTurma = function(model){
        $scope.openNewTurma(model, $scope.init);
    }
  
    $scope.openNewTurma = function(model, callback){
        $modal.open({
            size : 'lg',
            templateUrl: '../modal/turmaModal.html',
            backdrop: 'static',
            keyboard: true,
            controller: function ($scope, $modalInstance) {
                $scope.title = "Criar turma";
                $scope.turma = {};
                $scope.disableCodigo = true;
                $scope.turma.codigoDisc = model.codigoDisc;
                $scope.sim = function () {
                    delete $scope.turma.nomeDisciplina;
                    $scope.turma.turma = $scope.turma.turma.trim();
                    $http.post(url + 'turma/save', $scope.turma).success(function(response) {
                        if(response && response.data){
                            if(response.data.status == 300){
                                utils.error(response.data.exception);
                                callback();
                            } 
                        } 
                        $modalInstance.close();
                    });
                };
                $scope.nao = function () {
                    $modalInstance.close();
                };
            }
        });  
    };

    $.horariosDisciplina = [];
    $scope.selecionaHorario = function(horario) {
        /*
         * Sem horarios: #FFFFFF
         * Apenas uma disciplina: #90CAF9
         */
        var horarioTabela = document.getElementsByClassName(horario);
        if (horarioTabela[0].bgColor == "#FFFFFF" || horarioTabela[0].bgColor == "") {
            
            horarioTabela[0].bgColor = "#90CAF9";
            
            // Adiciona horario ao array de horarios
            $.horariosDisciplina.push(horario);
            
        } else {
            
            horarioTabela[0].bgColor = "#FFFFFF";
            
            // Remove horario do array de horarios
            $.horariosDisciplina.splice($.horariosDisciplina.indexOf(horario), 1);
            
        }
        
        //console.log($scope.horariosDisciplina);
    };
    
    $scope.openCreate = function(callback){
	    $modal.open({
      	    size : 'lg',
            templateUrl: '../modal/turmaDiscModal.html',
            backdrop: 'static',
            keyboard: true,
            controller: function ($scope, $modalInstance) {
                $scope.title = "Criar turma";
                $scope.turma = {};
                $http.get(url + "disciplina/findAll").success(function(response) {
         		    $scope.disciplinas = response; 
  			    });
                $scope.sim = function () {
            	    var params = {
        			    'codigoDisc' : $scope.turma.disciplina.codigo,
        			    'turma' : $scope.turma.turma,
        			    'ch' : $scope.turma.ch,
        			    'ano' : $scope.turma.ano,
        			    'semestre' : $scope.turma.semestre
            	    }
            	    $http.post(url + 'turma/save', params).success(function(response) {
            		    if(response && response.data){
        				    if(response.data.status == 300){
        					    utils.error(response.data.exception);
        				    } else {
                                $.id = "";
                                $http.post(url + 'turma/findTurmaCadastrada', params).success(function(response) {
                                    $.id = response;
                                });
                                $modal.open({
                                    size : 'lg',
                                    templateUrl: '../modal/horaTurmaModal.html',
                                    backdrop: 'static',
                                    keyboard: true,
                                    controller: function ($scope, $modalInstance) {
                                        $scope.titleModal = "Configurar Disciplina";
                                        $scope.sim = function () {
                                            $scope.oferta = {
                                                'turmaId': $.id,
                                                'horarios': $.horariosDisciplina
                                            }
                                            var res = $http.post(url + 'oferta/alterarHorarioTurma', $scope.oferta).success(function(response) {
                                                if (response != 'savo') {
                                                    alert(response);
                                                }
                                                $modalInstance.close();
                                            });
                                                                            
                                        };
                                        $scope.nao = function () {
                                            $modalInstance.close();
                                        };
                                    }
                                });
                                
                            }
        			    } 
            		    callback();
            		    $modalInstance.close();
            	    });
                };
                $scope.nao = function () {
                    $modalInstance.close();
                };
            }
        });  
    };
  
    $scope.openEdit = function(model, callback){
	    $modal.open({
      	    size : 'lg',
            templateUrl: '../modal/turmaModal.html',
            backdrop: 'static',
            keyboard: true,
            controller: function ($scope, $modalInstance) {
                $scope.title = "Editar Turma";
                $scope.turma = angular.copy(model);
                $scope.disableCodigo = true;
                $scope.sim = function () {
            	    $scope.turma.turma = $scope.turma.turma.trim();
            	    $http.post(url + 'turma/update', $scope.turma).success(function(response) {
            		    if(response && response.data){
        				    if(response.data.status == 300){
        					    utils.error(response.data.exception);
        					    callback();
        				    }
        			    } 
            		    $modalInstance.close();
            		    callback();
            	    });
                };
              $scope.nao = function () {
                    $modalInstance.close();
                };
            }
        });  
    };
  

    $scope.openRemove = function(model){
	    utils.confirm("Deseja realmente remover este registro ?",function(){
		    $http.post(url + 'turma/delete', model).success(function(response) {
			    if(response && response.data){
				    if(response.data.status == 300){
					    utils.error(response.data.exception);
				    } else {
					    $scope.init();
				    }
			    } 
    	    });
	    },null,"Excluir registro");  
    };

    $scope.semestres = [];
    $scope.turmasDuplicar = [];
    
    var selecionadosAux = [];
    var arraySelecionados = [];
    var duplicarAux = [];
    var arrayDuplicar = [];

    $scope.alterarSemestre = function(semestre) {

        var s = semestre;
        var selecionadosAux = [];
        var arraySelecionados = [];
        var duplicarAux = [];
        var arrayDuplicar = [];

        $scope.semestreSelecionado = s;
        $scope.selecionados = {};

        //console.log($scope.semestreSelecionado.ano);
        //console.log($scope.semestreSelecionado.semestre);

        //Seta variável que oculta option semestreDuplicar 
        for(var i = 0; i < $scope.semestres.length; i++) {
            $scope.semestres[i].exibeOption = true;
            if($scope.semestreSelecionado.ano == $scope.semestres[i].ano && $scope.semestreSelecionado.semestre == $scope.semestres[i].semestre) {
                $scope.semestres[i].exibeOption = false;
            }
        }

        //Seta o filtro de semestres a serem duplicados com o último semestre
        for(var i = 0; i < $scope.semestres.length; i++) { 
            if($scope.semestres[0].exibeOption != false) {
                $scope.semestreDuplicar = $scope.semestres[0];
            } else {
                $scope.semestreDuplicar = $scope.semestres[1];
            }
        }

        //Lista as turmas a serem duplicadas através do semestre selecionado
        $http.get(url + 'turma/findTurmaDuplicar/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
            if(response && response.length > 0){
                $scope.turmasDuplicar = response;
                for(var i = 0; i < $scope.turmasDuplicar.length; i++) {
                    //console.log($scope.turmasDuplicar[i]['turma']["id"]);
                    selecionadosAux.push($scope.turmasDuplicar[i]['turma']["id"]);
                    duplicarAux.push($scope.turmasDuplicar[i]['turma']);
                }
                //console.log(selecionadosAux);
            } else {
                $scope.turmasDuplicar = [];
            }
        }); 
      
        //console.log("Semestre selecionado = ", $scope.semestreSelecionado);
        //console.log("Semestre", $scope.semestre);
        //console.log("$scope.semestres", $scope.semestres); 

        $scope.selecionados = { "turmas": selecionadosAux };
        $scope.duplicar = { "turmas": duplicarAux };

        $scope.seleciona = function(turma) {
            var arraySelecionados = $scope.selecionados.turmas;
            return (arraySelecionados.indexOf(turma.id) > -1);

            var arrayDuplicar = $scope.duplicar.turmas;
            return (arrayDuplicar.indexOf(turma.id) > -1);
        }

        $scope.marca = function(turma) { 
            var arraySelecionados = $scope.selecionados.turmas;
            var index = arraySelecionados.indexOf(turma.id);
            if (index > -1) {
                arraySelecionados.splice(index, 1);
            } else {
                $scope.selecionados.turmas.push(turma.id);
            }

            var arrayDuplicar = $scope.duplicar.turmas;
            var index = arrayDuplicar.indexOf(turma);
            if (index > -1) {
                arrayDuplicar.splice(index, 1);
            } else {
                $scope.duplicar.turmas.push(turma);
            }
        }  

    };


    $http.get(url + 'semestre/listar').success(function(response) {

        //Carrega o filtro de semestres
        $scope.semestres = response; 
        for(var i = 0; i < response.length; i++) {
            if ($scope.semestres[i].status == true) {
                $scope.semestres[i].estado = '(Ativo)';
                //Seta o filtro de semestres com o semestre ativo           
                $scope.semestre = $scope.semestres[i];
                //Seta o filtro de semestres a serem duplicados com o último semestre 
                $scope.semestreDuplicar = $scope.semestres[0];
                $scope.semestreSelecionado = $scope.semestres[i];

                //Seta variável que oculta option semestreDuplicar 
                $scope.semestres[i].exibeOption = false;

            } else {
                $scope.semestres[i].estado = '';
                //Seta variável que oculta option semestreDuplicar 
                $scope.semestres[i].exibeOption = true;
            }
        }

        //Inicia as turmas a serem duplicadas
        $http.get(url + 'turma/findTurmaDuplicar/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
            if(response && response.length > 0){
                $scope.turmasDuplicar = response;

                for(var i = 0; i < $scope.turmasDuplicar.length; i++) {
                    //console.log($scope.turmasDuplicar[i]['turma']["id"]);
                    selecionadosAux.push($scope.turmasDuplicar[i]['turma']["id"]);
                    duplicarAux.push($scope.turmasDuplicar[i]['turma']);
                }
                //console.log(selecionadosAux);
                //console.log(duplicarAux);

            } else {
                $scope.turmasDuplicar = [];
            }
        }); 
      
        //console.log("Semestre selecionado = ", $scope.semestreSelecionado);
        //console.log("Semestre", $scope.semestre);
        //console.log("$scope.semestres", $scope.semestres);
    });

    $scope.selecionados = { "turmas": selecionadosAux };
    $scope.duplicar = { "turmas": duplicarAux };

    $scope.seleciona = function(turma) {
        var arraySelecionados = $scope.selecionados.turmas;
        return (arraySelecionados.indexOf(turma.id) > -1);

        var arrayDuplicar = $scope.duplicar.turmas;
        return (arrayDuplicar.indexOf(turma.id) > -1);
    }

    $scope.marca = function(turma) { 
        var arraySelecionados = $scope.selecionados.turmas;
        var index = arraySelecionados.indexOf(turma.id);
        if (index > -1) {
            arraySelecionados.splice(index, 1);
        } else {
            $scope.selecionados.turmas.push(turma.id);
        }

        var arrayDuplicar = $scope.duplicar.turmas;
        var index = arrayDuplicar.indexOf(turma);
        if (index > -1) {
            arrayDuplicar.splice(index, 1);
        } else {
            $scope.duplicar.turmas.push(turma);
        }
    } 

    $scope.duplicarTurmas = function(selecionadas, semestre){
        utils.confirm("Deseja realmente duplicar as turmas selecionadas ?",function(){
            //console.log("Ano: " + semestre.ano + ",Semestre: " + semestre.semestre);
            /*for(var i = 0; i < selecionadas['turmas'].length; i++) {
                console.log(selecionadas['turmas'][i]);
            }*/
            $http.post(url + 'turma/duplica/'+semestre.ano+'/'+semestre.semestre, selecionadas['turmas']).success(function(response) {
                if(response && response.data){
                    if(response.data.status == 300){
                        utils.error(response.data.exception);
                    } else {
                        $scope.init();
                    }
                } 
            });
        },null,"Duplicar turmas");  
    };

  
    $scope.init = function(){
        $http.get(url + 'turma/findAll').success(function(response) {
            if(response && response.length > 0){
                $scope.turmas = response;
            }
        }); 
    };
  
    $scope.init();

   $scope.calendario = [
        {
            horario: '07:10 (a)',
            domingo: '1-a',
            segunda: '2-a',
            terca: '3-a',
            quarta: '4-a',
            quinta: '5-a',
            sexta: '6-a',
            sabado: '7-a'
        },
        {
            horario: '08:00 (b)',
            domingo: '1-b',
            segunda: '2-b',
            terca: '3-b',
            quarta: '4-b',
            quinta: '5-b',
            sexta: '6-b',
            sabado: '7-b'
        },
        {
            horario: '08:50 (c)',
            domingo: '1-c',
            segunda: '2-c',
            terca: '3-c',
            quarta: '4-c',
            quinta: '5-c',
            sexta: '6-c',
            sabado: '7-c'
        },
        {
            horario: '09:50 (d)',
            domingo: '1-d',
            segunda: '2-d',
            terca: '3-d',
            quarta: '4-d',
            quinta: '5-d',
            sexta: '6-d',
            sabado: '7-d'
        },
        {
            horario: '10:40 (e)',
            domingo: '1-e',
            segunda: '2-e',
            terca: '3-e',
            quarta: '4-e',
            quinta: '5-e',
            sexta: '6-e',
            sabado: '7-e'
        },
        {
            horario: '11:30 (q)',
            domingo: '1-q',
            segunda: '2-q',
            terca: '3-q',
            quarta: '4-q',
            quinta: '5-q',
            sexta: '6-q',
            sabado: '7-q'
        },
        {
            horario: '13:10 (f)',
            domingo: '1-f',
            segunda: '2-f',
            terca: '3-f',
            quarta: '4-f',
            quinta: '5-f',
            sexta: '6-f',
            sabado: '7-f'
        },
        {
            horario: '14:00 (g)',
            domingo: '1-g',
            segunda: '2-g',
            terca: '3-g',
            quarta: '4-g',
            quinta: '5-g',
            sexta: '6-g',
            sabado: '7-g'
        },
        {
            horario: '14:50 (h)',
            domingo: '1-h',
            segunda: '2-h',
            terca: '3-h',
            quarta: '4-h',
            quinta: '5-h',
            sexta: '6-h',
            sabado: '7-h'
        },
        {
            horario: '16:00 (i)',
            domingo: '1-i',
            segunda: '2-i',
            terca: '3-i',
            quarta: '4-i',
            quinta: '5-i',
            sexta: '6-i',
            sabado: '7-i'
        },
        {
            horario: '16:50 (j)',
            domingo: '1-j',
            segunda: '2-j',
            terca: '3-j',
            quarta: '4-j',
            quinta: '5-j',
            sexta: '6-j',
            sabado: '7-j'
        },
        {
            horario: '17:40 (k)',
            domingo: '1-k',
            segunda: '2-k',
            terca: '3-k',
            quarta: '4-k',
            quinta: '5-k',
            sexta: '6-k',
            sabado: '7-k'
        },
        {
            horario: '18:10 (l)',
            domingo: '1-l',
            segunda: '2-l',
            terca: '3-l',
            quarta: '4-l',
            quinta: '5-l',
            sexta: '6-l',
            sabado: '7-l'
        },
        {
            horario: '19:00 (m)',
            domingo: '1-m',
            segunda: '2-m',
            terca: '3-m',
            quarta: '4-m',
            quinta: '5-m',
            sexta: '6-m',
            sabado: '7-m'
        },
        {
            horario: '19:50 (n)',
            domingo: '1-n',
            segunda: '2-n',
            terca: '3-n',
            quarta: '4-n',
            quinta: '5-n',
            sexta: '6-n',
            sabado: '7-n'
        },
        {
            horario: '20:50 (o)',
            domingo: '1-o',
            segunda: '2-o',
            terca: '3-o',
            quarta: '4-o',
            quinta: '5-o',
            sexta: '6-o',
            sabado: '7-o'
        },
        {
            horario: '21:40 (p)',
            domingo: '1-p',
            segunda: '2-p',
            terca: '3-p',
            quarta: '4-p',
            quinta: '5-p',
            sexta: '6-p',
            sabado: '7-p'
        }
    ];
  
}]);