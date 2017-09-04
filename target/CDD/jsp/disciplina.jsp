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
    <script src="../js/controller/disciplinaController.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="../css/main.css" rel="stylesheet">
    
    <link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
  </head>

  <body>
    <jsp:include page="/jsp/menu.jsp" />
    <div class="container" ng-controller="DisciplinaController">
      <div class="panel panel-default">
        <div class="panel-heading">Listagem de disciplinas</div>
      <div class="row" style="padding-top: 15px;padding-left: 15px;">
                <div class="col-md-10">
                    <div class="form-group">
                        <div class="input-group">    
                          <span class="input-group-addon">
                            <i class="fa fa-search"></i>
                          </span>
                          <input type="text" class="form-control" placeholder="Busque por uma disciplina" ng-model="filterSearch">
                        </div>
                    </div>  
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                          <button type="button" title="Editar" ng-click="newRegister()" class="btn btn-success">
                <i class="fa fa-plus" style="padding-right: 15px;"></i>Nova disciplina
              </button>
                        </div>
                    </div>
                </div>
            </div>
      <table class="table table-hover">
        <thead>
          <tr class="tableHeader">
            <th>Código</th>
            <th>Nome</th>
            <th>Curso</th>
            <th>CH. Teorica</th>
            <th>CH. Pratica</th>
            <th>CH. Total</th>
            <th>Periodo</th>
            <th class="text-center">Opções</th>
          </tr>
        </thead>
        <tbody>
          <tr ng-repeat="disciplina in disciplinas | filter:filterSearch">
            <td>{{disciplina.codigo}}</td>
            <td>{{disciplina.nome}}</td>
            <td>{{disciplina.curso}}</td>
            <td>{{disciplina.chTeorica}}</td>
            <td>{{disciplina.chPratica}}</td>
            <td>{{disciplina.chtotal}}</td>
            <td>{{disciplina.periodo}}</td>
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
    <!--
    	<div class="row">
    		<div class="col-md-8">
				<b><p>Configurar Disciplina</p></b>
			</div>
    	</div>
    	
		<hr>

		<accordion close-others="false">
	    	<accordion-group ng-show="true" heading="Alterar horário da disciplina" is-open="true">
	    		
	    		<div class="row col-md-12">
					<p>Selecione uma disciplina:</p>
				</div>
				<div class="row">
					<div class="col-md-10">
						<select name="repeatSelect" ng-change="exibirHorarios(disciplina)" ng-model="disciplina" class="form-control col-md-8">
	     						<option ng-repeat="d in disciplinas" value="{{d}}">{{d.codigoDisciplina}} - {{d.nomeDisciplina}} ({{d.turma}}) - {{d.codigoCurso}}</option>
	   					</select>
   					</div>
   					<div class="col-md-2">
   						<button type="button" class="btn btn-primary" ng-click="savarHorarios(disciplina)">Salvar</button>
   					</div>
				</div>
					
				<hr>
	    		
	    		<div class="row">
		    		<div class="col-md-12">
		    			<div class="col-md-1"></div>
		      			<div class="col-md-10">
			      			<table class="table table-bordered">
								<tr>
									<th></th>
									<th>Domingo</th>
								    <th>Segunda-feira</th>
								    <th>Terça-feira</th>
								    <th>Quarta-feira</th>
								    <th>Quinta-feira</th>
								    <th>Sexta-feira</th>
								    <th>Sabado</th>
								</tr>
								<tr ng-repeat="c in calendario">
									<td style="padding: 3px;">{{c.horario}}</td>
								    <td ng-click="selecionaHorario(c.domingo)" class={{c.domingo}}></td>
								    <td ng-click="selecionaHorario(c.segunda)" class={{c.segunda}}></td>
								    <td ng-click="selecionaHorario(c.terca)" class={{c.terca}}></td>
								    <td ng-click="selecionaHorario(c.quarta)" class={{c.quarta}}></td>
								    <td ng-click="selecionaHorario(c.quinta)" class={{c.quinta}}></td>
								    <td ng-click="selecionaHorario(c.sexta)" class={{c.sexta}}></td>
								    <td ng-click="selecionaHorario(c.sabado)" class={{c.sabado}}></td>
								</tr>
							</table>
						</div>
	      			</div>
      			</div>
      			
	    	</accordion-group>
	  	</accordion>
	
		<hr>
	
		<footer>
	    	<p>&copy;UFU 2015</p>
		</footer>

    </div><!--/.container-->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../css/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

  </body>
</html>
