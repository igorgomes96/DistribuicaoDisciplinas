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
    <script src="../js/controller/prioridadeController.js"></script>
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
    <div class="container" ng-controller="PrioridadeController">
        <div class="panel panel-default">
            <!-- <div class="panel-heading">Listagem de Prioridades</div>
            <div class="row" style="padding: 15px;padding-bottom:0px;">
                <div class="col-md-12">
                    <div class="form-group">
                      <label>Selecione um Professor:</label>
                        <div class="input-group">    
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input 
                                type="text" 
                                ng-model="professor" 
                                ng-required="true" 
                                name="professor" 
                                typeahead="professor as professor.nome for professor in professores | filter:{nome:$viewValue} | limitTo:8" 
                                typeahead-editable="false" 
                                autocomplete="off" 
                                typeahead-select-on-exact="true" 
                                typeahead-on-select="buscar($item)" 
                                class="form-control" 
                                required="required">
                        </div>
                    </div>  
                </div>
            </div> -->

            <div class="panel-heading">Listagem de Prioridades</div>
            <div class="row" style="padding: 15px;padding-bottom:0px;">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>Selecione um Professor:</label>
                        <tags-input ng-model="tagInput.tags" 
                            on-tag-added="buscar($tag)" 
                            replace-spaces-with-dashes="false" 
                            add-from-autocomplete-only="true" 
                            add-on-blur="false"
                            placeholder="Busque um professor"                 
                            on-tag-removed="exibeInputText()"> 
                        <auto-complete 
                            source="loadProfessores($query)" 
                            min-length="0" 
                            debounce-delay="0"
                            max-results-to-show="999">
                        </auto-complete>
                    </div>  
                </div>
            </div>

            <label style="font-weight: bold; padding-left: 15px; padding-bottom: 15px;" ng-bind="nomeProfessor"></label>
            
            <accordion close-others="false">
                <accordion-group ng-show="true" heading="Escolher prioridade de disciplinas: " is-open="true">
                    <div class="row">
                        <div class="col-md-12">
                            <button type="button" class="btn btn-primary" style="float:right;" ng-click="submeterFilaPrioridades()" ng-disabled="botaoDesativado">SALVAR</button>
                        </div>
                    </div>
                    
                    <br/>
                    
                    <div class="row">
                            <div class="col-md-12" ng-show="showPrioridadeSucesso">
                                <div class="alert alert-success" role="alert">
                                    <span>{{msgAlteraPrioridade}}</span>
                                    <span ng-click="fecharMsgAlterarPrioridade()" class="glyphicon glyphicon-remove-sign" style="float:right; color: #000000; cursor: pointer;" aria-hidden="true"></span>
                                </div>
                            </div>
                            <div class="col-md-12" ng-show="showPrioridadeErro">
                                <div class="alert alert-danger" role="alert">
                                    <span>{{msgAlteraPrioridade}}</span>
                                    <span ng-click="fecharMsgAlterarPrioridade()" class="glyphicon glyphicon-remove-sign" style="float:right; color: #000000; cursor: pointer;" aria-hidden="true"></span>
                                </div>
                            </div>
                    </div>
                    <hr>
                    
                    <div class="floatleft">
                        <ul ui-sortable="sortableOptions" ng-model="list" class="list">
                            <li ng-repeat="item in list" class="item">
                                <span>{{$index+1}} - {{item.codigoDisciplina}} - {{item.nomeDisciplina}} ({{item.turma}}) - {{item.codigoCurso}}</span>
                            </li>
                        </ul>
                    </div>
                </accordion-group>
            </accordion>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../css/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

  </body>
</html>
