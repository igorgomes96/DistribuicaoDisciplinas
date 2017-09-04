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
    <script src="../js/controller/filaProfessorController.js"></script>
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
    <div class="container" ng-controller="FilaProfessorController">
    	<div class="panel panel-default">
			<div class="panel-heading">Listagem de Fila dos professores</div>
			<!--<div class="row" style="padding-top: 15px;padding-left: 15px;">
                <div class="col-md-10">
                    <div class="form-group">
                    <label>Professor</label>
                        <div class="input-group">   
                            <input
                                type="text"
                                ng-model="professor"
                                ng-required="true"
                                name="siape"
                                typeahead="professor as (professor.siape+' - '+professor.nome) for professor in professores | filter:{nome:$viewValue} | limitTo:8"
                                typeahead-editable="false"
                                autocomplete="off"
                                typeahead-select-on-exact="true"
                                typeahead-on-select="onSelectProfessor($item)"
                                class="form-control">
                                <span class="input-group-addon">
                                    <span class="fa" ng-class="(form.professor.$invalid ? 'fa-search text-danger' : 'fa-search text-success')"></span>
                                </span>
                        </div>
                </div>
                </div>
                <div class="col-md-2" style="padding-top: 25px">
                    <div class="form-group">
                        <div class="input-group">
                        	<button type="button" title="Buscar" ng-disabled="!professor" ng-click="buscaDisciplinas(professor.siape)" class="btn btn-primary">
								<i class="fa fa-search" style="padding-right: 15px;"></i>Buscar
							</button>
                        </div>
                    </div>
                </div>
            </div>-->

            <div class="row" style="padding-top: 15px;padding-left: 15px;">
                <div class="col-md-10">
                    <label>Professor</label><label style="font-weight: bold;" ng-bind="nomeProfessor"></label>
                    <tags-input ng-model="tagInput.tags" 
                        on-tag-added="buscaDisciplinas($tag)" 
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

                <!--<div class="col-md-2" style="padding-top: 25px">
                    <div class="form-group">
                        <div class="input-group">
                            <button type="button" id="busca-disciplina" title="Buscar" ng-disabled="botaoDesativado"  ng-click="buscaDisciplinas(tagInput.tags)" class="btn btn-primary">
                                <i class="fa fa-search" style="padding-right: 15px;"></i>Buscar
                            </button>
                        </div>
                    </div>
                </div>-->
            
            </div>

			<table class="table table-hover">
				<thead>
					<tr class="tableHeader">
						<th>Codigo Disciplina</th>
						<th>Nome Disciplina</th>
                        <th>Curso</th>
						<th class="text-center">Opções</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="disciplina in disciplinas | filter:filterSearch">
						<td>{{disciplina.codigoDisc}}</td>
						<td>{{disciplina.nomeDisc}}</td>
                        <td>{{disciplina.nomCurso}}</td>
						<td class="text-center">
							<button type="button" title="Remover" ng-click="openRemove(disciplina)"class="btn btn-danger">
								<i class="fa fa-trash"></i>
							</button>
							<button type="button" title="Editar" ng-click="editRegister(disciplina)" class="btn btn-info">
								<i class="fa fa-pencil"></i>
							</button>
						</td>
					</tr>
					<tr ng-show="!disciplinas || (disciplinas | filter:filterSearch).length<=0">
						<td colspan="4" >
							<h5>Nenhum registro encontrado</h5>
						</td>
					</tr>
				</tbody>
		  	</table>
		</div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../css/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

  </body>
</html>
