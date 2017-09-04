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
    <script src="../js/controller/etapaController.js"></script>
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
    <div class="container" ng-controller="EtapaController">
    	<div class="panel panel-default">
			<table class="table table-hover">
				<thead>
					<tr class="tableHeader">
						<th class="col-md-4">Etapa</th>
                        <th class="col-md-6">Descrição</th>
						<th class="text-center">Opções</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="etapa in etapas | filter:filterSearch" ng-class="!etapa.ativo ? 'tr-inativo' : ''">
						<td class="col-md-4">{{etapa.codigo}}</td>
                        <td class="col-md-6">{{etapa.descricao}}</td>
						<td class="text-center">
							<button type="button" title="Ativar etapa" ng-click="ativarEtapa(etapa)" class="btn" ng-class="etapa.ativo ? 'btn-success' : 'btn-primary'">
								<i class="fa" ng-class="etapa.ativo ? 'fa-unlock' : 'fa-lock'"></i>
							</button>
						</td>
					</tr>
					<tr ng-show="!etapas || (etapas | filter:filterSearch).length<=0">
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
