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
    <script src="../js/controller/rltMinistraController.js"></script>
	  <script src="../js/lib/ngStorage.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="../css/main.css" rel="stylesheet">
    
    <link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
  </head>

  <body ng-controller="RltMinistraController">
    <jsp:include page="/jsp/menuUser.jsp" />
    <div >
    	<div class="panel panel-default">
			<div class="row" style="padding-top: 15px;padding-left: 15px;">
				<div class="col-md-6 col-md-offset-1">
					<p style="margin-left: 17px;margin-bottom: 5px;"><b>Filtros:</b></p>
					
	                <div class="col-md-4 ">
	                    <div class="form-group">
	                        <div class="input-group">    
	                        	<span class="input-group-addon">
	                        		<i class="fa fa-search"></i>
	                        	</span>
	                        	<input type="text" class="form-control" placeholder="Filtre pelo professor" ng-model="filterSearch.nomeProfessor">
	                        </div>
	                    </div>  
	                </div>
	                <div class="col-md-4">
	                    <div class="form-group">
	                        <div class="input-group">    
	                        	<span class="input-group-addon">
	                        		<i class="fa fa-search"></i>
	                        	</span>
	                        	<input type="text" class="form-control" placeholder="Filtre pelo código da disciplina" ng-model="filterSearch.disciplinaCodigo">
	                        </div>
	                    </div>  
	                </div>
	                <div class="col-md-4">
	                    <div class="form-group">
	                        <div class="input-group">    
	                        	<span class="input-group-addon">
	                        		<i class="fa fa-search"></i>
	                        	</span>
	                        	<input type="text" class="form-control" placeholder="Filtre pela disciplina" ng-model="filterSearch.disciplinaNome">
	                        </div>
	                    </div>  
	                </div>
	            </div>
                <div class="col-md-2">
                	<label>Semestre:</label>
	                <select name="semestre" ng-options="s as (s.ano+' / '+s.semestre+s.estado) for s in semestres" ng-change="alterarSemestre(semestre)" ng-model="semestre" class="form-control">
					</select>
                </div>
                <div class="col-md-2">
	                <label>Ordenação:</label>
	                <select name="sort" ng-change="alterarOrdenacao(order)" ng-model="order" class="form-control">
	                	<option value="prioridade">Prioridade</option>
	                	<option value="disciplina">Disciplina</option>
	                	<option value="professor">Professor</option>
					</select>
				</div>
			</div>
			<table class="table" style="margin-top: 20px;">
				<thead>
					<tr class="tableHeader">
						<th>Nome</th>
                        <th>Cod. Disciplina</th>
                        <th>Nome Disciplina</th>
                        <th>Prioridade</th>
                        <th>Posição</th>
                        <th>Quantidade</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="r in relat | filter:filterSearch" ng-class="r.listClass">
						<td>{{r.nomeProfessor}}</td>
                        <td>{{r.disciplinaCodigo}}</td>
                        <td>{{r.disciplinaNome}}</td>
                        <td>{{r.prioridade}}</td>
                        <td>{{r.posicaoFila}}</td>
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
