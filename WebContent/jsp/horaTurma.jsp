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
	    <script src="../js/controller/horaTurmaController.js"></script>
	  	<script src="../js/lib/ngStorage.js"></script>

	    <!-- Bootstrap core CSS -->
	    <link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" />
	    <!-- Custom styles for this template -->
	    <link href="../css/main.css" rel="stylesheet">
	    
	    <link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
	</head>

	<body>
	    <jsp:include page="/jsp/menu.jsp" />
	    <div class="container" ng-controller="HoraTurmaController">
	    
	    	<div class="row">
	    		<div class="col-md-8">
					<b><p>Configurar Disciplina</p></b>
				</div>
	    	</div>
	    	
			<hr>

			<accordion close-others="false">
		    	<accordion-group ng-show="true" heading="Alterar horário da disciplina" is-open="true">
		    		
		    		<!--<div class="row col-md-12">
						<p>Selecione uma disciplina:</p>
					</div>
					<div class="row">-->
						<!--
						<div class="col-md-10">
							<select name="repeatSelect" ng-change="exibirHorarios(disciplina)" ng-model="disciplina" class="form-control col-md-8">
		     						<option ng-repeat="d in disciplinas" value="{{d}}">{{d.codigoDisciplina}} - {{d.nomeDisciplina}} ({{d.turma}}) - {{d.codigoCurso}}</option>
		   					</select>
	   					</div>
	   					-->
						<!--<div class="col-md-10">
	    					<input type="text"
	    						ng-model="disciplina"
	    						ng-required="true"
	    						name="disciplina"
	    						typeahead="disciplina as (disciplina.codigoDisciplina+ ' - ' +disciplina.nomeDisciplina+' - '+disciplina.turma) for disciplina in disciplinas | filter:$viewValue"
	    						typeahead-editable="false"
	    						typeahead-select-on-exact="true"
	    						autocomplete="off"
	    						typeahead-on-select="exibirHorarios(disciplina)"
	    						class="form-control"
	    						typeahead-on>
				        </div>
	   					<div class="col-md-2">
	   						<button type="button" class="btn btn-primary" ng-click="savarHorarios(disciplina)">Salvar</button>
	   					</div>
					</div>-->

					<div class="row">
						<div class="col-md-10">
		    				<tags-input ng-model="tagInput.tags" 
		    					on-tag-added="exibirHorarios2($tag)" 
		    					replace-spaces-with-dashes="false" 
		    					add-from-autocomplete-only="true" 
		    					add-on-blur="false"
		    					placeholder="Busque uma disciplina"  				
		    					on-tag-removed="limparHorarios2()"> 
	    					<auto-complete 
	    						source="loadDisciplinas($query)" 
	    						min-length="0" 
	                 			debounce-delay="0"
	                 			max-results-to-show="999">
	                 		</auto-complete>
	         			</div>

	         			<div class="col-md-2">
	   						<button type="button" class="btn btn-primary" ng-click="salvaHorario(tagInput.tags)">Salvar</button>
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
