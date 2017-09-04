<!DOCTYPE html>
<html ng-app="mainApp">
    <head>
    	<%@ page contentType="text/html; charset=UTF-8" %>
        <title>Courses Distribution</title>
        
        <script src="../js/lib/jquery.min.js"></script>
      	<script src="../js/lib/jquery-ui.min.js"></script>
        <script src="../js/lib/angular.min.js"></script>
        <script src="../js/lib/ui-bootstrap-tpls-0.13.4.min.js"></script>
        <script src="../js/lib/js-custom-select/customSelect.js"></script>
        <script src="../js/lib/sortable.js"></script>
        <script src="../js/lib/angular-route.min.js"></script>
        <script src="../js/controller/main.js"></script>
        <script src="../js/modules/utils.js"></script>
        <script src="../js/controller/turmaController.js"></script>
        <script src="../js/lib/ngStorage.js"></script>

        <!-- Bootstrap core CSS -->
        <link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Custom styles for this template -->
        <link href="../css/main.css" rel="stylesheet">
        
        <link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
    </head>

    <body>
        <jsp:include page="/jsp/menu.jsp" />
        <div class="container" ng-controller="TurmaController">

            <accordion close-others="false">
                <accordion-group ng-show="true" heading="Listagem de Turmas pelo Código da Disciplina" is-open="false">
                    <div class="row">
                        <div class="row" style="padding-left: 15px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <div class="input-group">    
                                        <span class="input-group-addon">
                                            <i class="fa fa-search"></i>
                                        </span>
                                        <input type="text" class="form-control" placeholder="Filtrar pelo código da Disciplina" ng-model="filterSearch.codigoDisc">
                                    </div>
                                </div>  
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <div class="input-group">    
                                        <span class="input-group-addon">
                                            <i class="fa fa-search"></i>
                                        </span>
                                        <input type="text" class="form-control" placeholder="Filtrar pelo Ano" ng-model="filterSearch.ano">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <div class="input-group">    
                                        <span class="input-group-addon">
                                            <i class="fa fa-search"></i>
                                        </span>
                                        <input type="text" class="form-control" placeholder="Filtrar pelo Semestre" ng-model="filterSearch.semestre">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <div class="input-group">
                                        <button type="button" title="Adicionar Nova Turma" ng-click="newRegister()" class="btn btn-success">
                                            <i class="fa fa-plus" style="padding-right: 15px;"></i>Nova turma
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <table class="table table-hover">
                            <thead>
                                <tr class="tableHeader">
                                    <th>Id</th>
                                    <th>Código Disciplina</th>
                                    <th>Turma</th>
                                    <th>Carga Horária</th>
                                    <th>Ano</th>
                                    <th>Semestre</th>
                                    <th class="text-center">Opções</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="turma in turmas | filter:filterSearch">
                                    <td>{{turma.id}}</td>
                                    <td>{{turma.codigoDisc}}</td>
                                    <td>{{turma.turma}}</td>
                                    <td>{{turma.ch}}</td>
                                    <td>{{turma.ano}}</td>
                                    <td>{{turma.semestre}}</td>
                                    <td class="text-center">
                                        <button type="button" title="Remover" ng-click="openRemove(turma)"class="btn btn-danger">
                                            <i class="fa fa-trash"></i>
                                        </button>
                                        <button type="button" title="Editar Turma" ng-click="editRegister(turma)" class="btn btn-info">
                                            <i class="fa fa-pencil"></i>
                                        </button>
                                        <!--
                                        <button type="button" title="Adicionar Turma" ng-click="newTurma(turma)" class="btn btn-success">
                                        <i class="fa fa-plus"></i>
                                        </button> -->
                                    </td>
                                </tr>
                                <tr ng-show="!turmas || (turmas | filter:filterSearch).length<=0">
                                    <td colspan="4" >
                                        <h5>Nenhum registro encontrado</h5>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </accordion-group>
          
                <accordion-group ng-show="true" heading="Listagem de Turmas a serem duplicadas" is-open="false">
                    <div class="row">                 
                        <div class="col-md-2">             
                            <select name="semestre" ng-options="s as (s.ano+' / '+s.semestre+s.estado) for s in semestres" ng-change="alterarSemestre(semestre)" ng-model="semestre" class="form-control">
                            </select>                   
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <div class="input-group">    
                                    <span class="input-group-addon">
                                     <i class="fa fa-search"></i>
                                    </span>
                                    <input type="text" class="form-control" placeholder="Nome Disciplina" ng-model="filterSearchDuplicar.nomeDisciplina">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <div class="input-group">    
                                    <span class="input-group-addon">
                                     <i class="fa fa-search"></i>
                                    </span>
                                    <input type="text" class="form-control" placeholder="Código Disciplina" ng-model="filterSearchDuplicar.turma.codigoDisc">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <div class="input-group">    
                                    <span class="input-group-addon">
                                     <i class="fa fa-search"></i>
                                    </span>
                                    <input type="text" class="form-control" placeholder="Curso" ng-model="filterSearchDuplicar.nomeCurso">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">             
                            <select name="semestreDuplicar" ng-options="s as (s.ano+' / '+s.semestre+s.estado) for s in semestres | filter:{exibeOption:true}" ng-model="semestreDuplicar" class="form-control">
                            </select>                   
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <div class="input-group">
                                  <button type="button" title="Duplicar Turmas" ng-click="duplicarTurmas(duplicar,semestreDuplicar)" class="btn btn-success">
                                    <i class="fa fa-plus" style="padding-right: 15px;"></i>Duplicar Turmas
                                  </button>
                                </div>
                            </div>
                        </div>
                        <table class="table table-hover">
                            <thead>
                                <tr class="tableHeader">
                                    <th width="40%">Nome Disciplina</th>
                                    <th>Código Disciplina</th>
                                    <th>Nome Curso</th>
                                    <th>Turma</th>
                                    <th>Carga Horária</th>
                                    <th class="text-center">Duplicar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="tur in turmasDuplicar | filter:filterSearchDuplicar">
                                    <td>{{tur.nomeDisciplina}}</td>
                                    <td>{{tur.turma.codigoDisc}}</td>
                                     <td>{{tur.nomeCurso}}</td>
                                    <td>{{tur.turma.turma}}</td>
                                    <td>{{tur.turma.ch}}</td>
                                    <td class="text-center">
                                        <input type="checkbox" ng-model="dadsa" ng-click="marca(tur.turma)" ng-checked="seleciona(tur.turma)">
                                    </td>
                                </tr>
                                <tr ng-show="!turmasDuplicar || (turmasDuplicar | filter:filterSearchDuplicar).length<=0">
                                    <td colspan="4" >
                                        <h5>Nenhum registro encontrado</h5>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </accordion-group>
            </accordion>

        </div> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="../css/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    </body>

</html>
