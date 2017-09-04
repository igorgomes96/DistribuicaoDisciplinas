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
    <script src="../js/controller/rltTurmasController.js"></script>
      <script src="../js/lib/ngStorage.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="../css/main.css" rel="stylesheet">
    
    <link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
  </head>

  <body ng-controller="RltTurmasController">
    <jsp:include page="/jsp/menuUser.jsp" />
    <div class="" >
    	<div class="panel panel-default">
			<div class="panel-heading" ng-bind="nomeRelatorio"></div>
			<div class="row" style="padding: 15px;">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">    
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input type="text" class="form-control" placeholder="Filtre pelo código" ng-model="filterSearch.codigo">
                        </div>
                    </div>  
                </div>
                 <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">    
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input type="text" class="form-control" placeholder="Filtre pela turma" ng-model="filterSearch.turma">
                        </div>
                    </div>  
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">    
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input type="text" class="form-control" placeholder="Filtre pelo nome" ng-model="filterSearch.nome">
                        </div>
                    </div>  
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">    
                            <span class="input-group-addon">
                                <i class="fa fa-search"></i>
                            </span>
                            <input type="text" class="form-control" placeholder="Filtre pelo curso" ng-model="filterSearch.curso">
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
                        <th>Código</th>
                        <th>Turma</th>
                        <th>Nome</th>
                        <th>Curso</th>
                        <th>Carga horária</th>
                        <th>Horário</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="turma in turmas | filter:filterSearch">
                        <td>{{turma.codigo}}</td>
                        <td>{{turma.turma}}</td>
                        <td>{{turma.nome}}</td>
                        <td>{{turma.curso}}</td>
                        <td>{{turma.cargahoraria}}</td>
                        <td>{{turma.horarioExtenso}}</td>
                        <td class="text-center">
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
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../css/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

  </body>
</html>
