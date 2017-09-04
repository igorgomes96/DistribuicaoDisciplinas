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
    <script src="../js/controller/semestreController.js"></script>
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
    <div class="container" ng-controller="SemestreController">
    	<div class="panel panel-default">
			<div class="panel-heading">Listagem de semestres</div>
			<div class="row" style="padding-top: 15px;padding-left: 15px;">
                <div class="col-md-10">
                    <div class="form-group">
                        <div class="input-group">    
                        	<span class="input-group-addon">
                        		<i class="fa fa-search"></i>
                        	</span>
                        	<input type="text" class="form-control" placeholder="Busque por um semestre" ng-model="filterSearch">
                        </div>
                    </div>  
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                        	<button type="button" title="Editar" ng-click="newRegister()" class="btn btn-success">
								<i class="fa fa-plus" style="padding-right: 15px;"></i>Novo semestre
							</button>
                        </div>
                    </div>
                </div>
            </div>
			<table class="table table-hover">
				<thead>
					<tr class="tableHeader">
						<th class="col-md-4">Ano</th>
						<th class="col-md-6">Semestre</th>
						<th class="text-center">Opções</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="semestre in semestres | filter:filterSearch" ng-class="!semestre.status ? 'tr-inativo' : ''">
						<td class="col-md-4">{{semestre.ano}}</td>
						<td class="col-md-6">{{semestre.semestre}}</td>
						<td class="text-center">
							<button type="button" title="Ativar semestre" ng-click="ativarSemestre(semestre)" class="btn" ng-class="semestre.status ? 'btn-success' : 'btn-primary'">
								<i class="fa" ng-class="semestre.status ? 'fa-unlock' : 'fa-lock'"></i>
							</button>
							
							<button type="button" title="Remover" ng-click="openRemove(semestre)"class="btn btn-danger">
								<i class="fa fa-trash"></i>
							</button>
							<button type="button" title="Editar" ng-click="editRegister(semestre)" class="btn btn-info">
								<i class="fa fa-pencil"></i>
							</button>
						</td>
					</tr>
					<tr ng-show="!semestres || (semestres | filter:filterSearch).length<=0">
						<td colspan="3" >
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
