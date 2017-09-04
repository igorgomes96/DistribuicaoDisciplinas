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
    <script src="../js/controller/rltProfessorController.js"></script>
      <script src="../js/lib/ngStorage.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="../css/main.css" rel="stylesheet">
    
    <link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
  </head>

  <body ng-controller="RltProfessorController">
    <jsp:include page="/jsp/menuUser.jsp" />
    <div class="" >
    	<div class="panel panel-default">
			<div class="panel-heading">Relatório ordenado por professor</div>
			<div class="row" style="padding-top: 15px;padding-left: 15px;">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">    
                        	<span class="input-group-addon">
                        		<i class="fa fa-search"></i>
                        	</span>
                        	<input type="text" class="form-control" placeholder="Filtre pelo professor" ng-model="filterSearch.nomeProfessor">
                        </div>
                    </div>  
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">    
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input type="text" class="form-control" placeholder="Filtre pelo código da disciplina" ng-model="filterSearch.codigo">
                        </div>
                    </div>  
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">    
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input type="text" class="form-control" placeholder="Filtre pela disciplina" ng-model="filterSearch.nomeDisc">
                        </div>
                    </div>  
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">    
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input type="text" class="form-control" placeholder="Filtre pelo curso" ng-model="filterSearch.nomeCurso">
                        </div>
                    </div>  
                </div>
                <div class="col-md-2">
                    <select name="semestre" ng-options="s as (s.ano+' / '+s.semestre+s.estado) for s in semestres" ng-change="alterarSemestre(semestre)" ng-model="semestre" class="form-control">
                    </select>
                </div>
            </div>
			<table class="table table-hover">
				<thead>
					<tr class="tableHeader">
						<th>Nome</th>
						<th>Cod. Disciplina</th>
                        <th>Turma</th>
                        <th>Nome Disciplina</th>
                        <th>Curso</th>
                        <th>CH</th>
                        <th>Qte. Ministrada</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="professor in professores | filter:filterSearch">
						<td>{{professor.nomeProfessor}}</td>
						<td>{{professor.codigo}}</td>
                        <td>{{professor.turma}}</td>
                        <td>{{professor.nomeDisc}}</td>
                        <td>{{professor.nomeCurso}}</td>
                        <td>{{professor.cargaHoraria}}</td>
                        <td>{{professor.qte == null ? "-" : professor.qte}}</td>
						<td class="text-center">
						</td>
					</tr>
					<tr ng-show="!professores || (professores | filter:filterSearch).length<=0">
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
