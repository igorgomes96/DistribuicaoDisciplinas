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
    <script src="../js/controller/filaController.js"></script>
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
    <div class="container" ng-controller="FilaController">
    	<div class="panel panel-default">
			<div class="panel-heading">Listagem de filas2</div>
			<div class="row" style="padding: 15px;padding-bottom:0px;">
                <div class="col-md-12">
                    <div class="form-group">
                        <div class="input-group">    
                        	<span class="input-group-addon">
                        		<i class="fa fa-search"></i>
                        	</span>
                        	<input type="text" class="form-control" placeholder="Busque por uma fila" ng-model="filterSearch">
                        </div>
                    </div>  
                </div>
            </div>
			<table class="table table-hover">
				<thead>
					<tr class="tableHeader">
						<th style="width: 40%;">Ano</th>
						<th style="width: 40%;">Semestre</th>
						<th class="text-center">Opções</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="fila in filas | filter:filterSearch">
						<td>{{fila.ano}}</td>
						<td>{{fila.semestre}}</td>
						<td class="text-center">
							<button type="button" title="Copiar fila" ng-click="copiarFila(fila)"class="btn btn-success">
								<i class="fa fa-copy"></i>
							</button>
							<button type="button" title="Adicionar professor" ng-click="editFila(fila)" class="btn btn-info">
								<i class="fa fa-user-plus"></i>
							</button>
						</td>
					</tr>
					<tr ng-show="!filas || (filas | filter:filterSearch).length<=0">
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
