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
    <script src="../js/controller/rltMinistra4Controller.js"></script>
	  <script src="../js/lib/ngStorage.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="../css/main.css" rel="stylesheet">
    
    <link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
  </head>

  <body ng-controller="RltMinistra4Controller">
    <jsp:include page="/jsp/menuUser.jsp" />
    <div >
    	<div class="panel panel-default">
			<table class="table" style="margin-top: 20px;">
				<thead>
					<tr class="tableHeader">
						<th>Nome</th>
                        <th>Cod. Disciplina</th>
                        <th>Nome Disciplina</th>
                        <th>Curso</th>
                        <th>Quantidade</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="r in relat | filter:filterSearch" ng-class="r.listClass">
						<td>{{r.nomeProfessor}}</td>
                        <td>{{r.disciplinaCodigo}}</td>
                        <td>{{r.disciplinaNome}}</td>
                        <td>{{r.curso}}</td>
                        <td>{{r.quantidade}}</td>
					</tr>
					<tr ng-show="!relat || (relat | filter:filterSearch).length<=0">
						<td colspan="6" >
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
