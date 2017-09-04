<!DOCTYPE html>
<html ng-app="mainApp">
<head>
<%@ page contentType="text/html; charset=UTF-8"%>
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
<script src="../js/controller/ministraDisciplinaController.js"></script>
<script src="../js/lib/ngStorage.js"></script>

<!-- Bootstrap core CSS -->
<link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Custom styles for this template -->
<link href="../css/main.css" rel="stylesheet">

<link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
</head>

<body>
	<jsp:include page="/jsp/menu.jsp" />
	<div class="container" ng-controller="MinistraDisciplinaController">
		<accordion close-others="false"> 
			<accordion-group ng-show="true" heading="Atribuir turma ao professor" is-open="false">
				<div class="panel panel-default">
					<div class="panel-heading">Listagem de professores</div>
					<div class="row" style="padding-top: 15px; padding-left: 15px;">
						<div class="col-md-10">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-search"></i>
									</span> <input type="text" class="form-control"
										placeholder="Busque por um professor" ng-model="filterSearch">
								</div>
							</div>
						</div>
					</div>
					<table class="table table-hover">
						<thead>
							<tr class="tableHeader">
								<th>Siape</th>
								<th>Nome</th>
								<th class="text-center">Opções</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="professor in professores | filter:filterSearch">
								<td>{{professor.siape}}</td>
								<td>{{professor.nome}}</td>
								<td class="text-center">
									<button type="button" title="Atribuir Turma"
										ng-click="editRegister(professor)" class="btn btn-info">
										<i class="fa fa-pencil"></i>
									</button>
								</td>
							</tr>
							<tr
								ng-show="!professores || (professores | filter:filterSearch).length<=0">
								<td colspan="4">
									<h5>Nenhum registro encontrado</h5>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</accordion-group> <!-- Exibir fila por professor (com as turmas) --> 
			<accordion-group ng-show="true" heading="Exibir turmas por professor" is-open="false">
				<div class="col-md-12">
					<div class="col-md-12">
		                <label>Professor</label><label style="font-weight: bold;" ng-bind="nomeProfessor"></label>
		                <tags-input ng-model="tagInput.tags" 
		                    on-tag-added="buscaDisciplinasPorSiape($tag)" 
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

				<hr>

				<div class="row" ng-show="disciplinasProfessor.length > 0">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Disciplina</th>
								<th>Turma</th>
								<th>Semestre</th>
								<th>Curso</th>
								<th class="text-center">Opções</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="d in disciplinasProfessor">
								<td>{{d.codigoDisc}} - {{d.nomeDisc}}</td>
								<td>{{d.turma}}</td>
								<td>{{d.turmaAno}}/{{d.turmaSemestre}}</td>
								<td>{{d.nomeCurso}}</td>
								<td class="text-center">
									<button type="button" title="Remover Turma"
										ng-click="openRemove(d)" class="btn btn-danger">
										<i class="fa fa-trash"></i>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
			</accordion-group> 
		</accordion>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="../css/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

</body>
</html>
