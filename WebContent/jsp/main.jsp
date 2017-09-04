<!DOCTYPE html>
<html ng-app="mainApp">
	<head>
		<%@ page contentType="text/html; charset=UTF-8" %>
		<title>Courses Distribution</title>

		<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script> -->
		<script src="../js/lib/jquery.min.js"></script>
			<!-- <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script> -->
			<script src="../js/lib/jquery-ui.min.js"></script>
		<script src="../js/lib/angular.min.js"></script>
		<script src="../js/lib/ui-bootstrap-tpls-0.13.4.min.js"></script>
		<script src="../js/lib/js-custom-select/customSelect.js"></script>
		<!-- <script src="https://rawgithub.com/angular-ui/ui-sortable/master/src/sortable.js"></script> -->
		<script src="../js/lib/sortable.js"></script>
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js"></script> -->
		<script src="../js/lib/angular-route.min.js"></script>
		<script src="../js/lib/ngStorage.js"></script>
		<script src="../js/controller/main.js"></script>

		<!-- Bootstrap core CSS -->
		<link href="../css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		<!-- <script src="../css/lib/font-awesome.min.css"></script> -->
		<!-- Custom styles for this template -->
		<link href="../css/main.css" rel="stylesheet">

		<link rel="stylesheet" href="../js/lib/js-custom-select/style.css" />
		<link rel="stylesheet" href="../js/lib/tags-input/ng-tags-input.bootstrap.min.css">
		<script type="text/javascript" src="../js/lib/tags-input/ng-tags-input.min.js"></script>
	</head>

	<body ng-controller="MainController">
	  	<jsp:include page="/jsp/menuUser.jsp" />

	    <div class="container" >
	    
	    	<div class="row">
	    		<div class="col-md-8">
					<b><p>{{professorInfo.siape}} - {{professorInfo.nome}}</p></b>
				</div>
				<div class="col-md-4">
					<select name="semestre" ng-options="s as (s.ano+' / '+s.semestre+s.estado) for s in semestres" ng-change="alterarSemestre(semestre)" ng-model="semestre" class="form-control">
					</select>
				</div>
	    	</div>
	    	
			<hr>

			<accordion close-others="false">
				<accordion-group ng-show="etapaAtiva('entrarFila')" heading="Entrar / Sair em filas de disciplinas" is-open="false">
					<div class="col-md-12">
					
						<div class="row" ng-show="semestreSelecionado.status">
				    		<div class="col-md-12">
								<p>Adicionar nova disciplina:</p>
							</div>
						</div>
						
						<div class="row" ng-show="semestreSelecionado.status">
							<div class="col-md-8">
								<input 
	    						type="text"
	    						ng-model="novaDisciplina"
	    						ng-required="true"
	    						name="disciplina"
	    						typeahead="disciplina as (disciplina.codigoDisciplina+' - '+disciplina.nomeDisciplina+' - '+disciplina.codigoCurso) for disciplina in disciplinaTurmaHorario | filter:$viewValue"
	    						typeahead-editable="false"
	    						typeahead-select-on-exact="true"
	    						autocomplete="off"
	    						typeahead-on-select="exibeInfoDisciplina($item)"
	    						class="form-control">
		   					</div>
		   					<div class="col-md-2">
		   						<button type="button" class="btn btn-primary" ng-click="entrarFilaDisciplina(novaDisciplina); novaDisciplina = '';">ADICIONAR</button>
		   					</div>
						</div>
						
						<div class="row">
							<div class="col-md-12" style="margin-top: 15px;" ng-show="showMsgSucesso">
								<div class="alert alert-success" role="alert">
									<span>{{msgAddDisciplina}}</span>
									<span ng-click="fecharMsgAdicionar()" class="glyphicon glyphicon-remove-sign" style="float:right; color: #000000; cursor: pointer;" aria-hidden="true"></span>
								</div>
							</div>
							<div class="col-md-12"style="margin-top: 15px;" ng-show="showMsgErro">
								<div class="alert alert-danger" role="alert">
									<span>{{msgAddDisciplina}}</span>
									<span ng-click="fecharMsgAdicionar()" class="glyphicon glyphicon-remove-sign" style="float:right; color: #000000; cursor: pointer;" aria-hidden="true"></span>
								</div>
							</div>
						</div>
						
						<br/>
						
						<div class="row">
							<table class="table table-striped" ng-show="disciplinaInfo != null">
						    	<thead>
						        	<tr>
						          		<th>Disciplina</th>
						          		<th>Curso</th>
						          		<th>Carga horária total</th>
						        	</tr>
						        	<tr>
						          		<th>{{disciplinaInfo.nome}}</th>
						          		<th>{{disciplinaInfo.curso}}</th>
						          		<th>{{disciplinaInfo.cargaHoraria}} hora(s)</th>
						        	</tr>
						        	<tr ng-show="turmasInfo.length > 0 && turmasInfo[0].turma != null">
						          		<th>Turma</th>
						          		<th>Dia semana</th>
						          		<th>Horário</th>
						        	</tr>
						      	</thead>
						      	<tbody ng-show="turmasInfo.length > 0 && turmasInfo[0].turma != null">
						        	<tr ng-repeat="ti in turmasInfo">
						          		<td>{{ti.turma}}</td>
						          		<td>{{ti.diaSemana}}</td>
						          		<td>{{ti.horario}}</td>
						        	</tr>
						      	</tbody>
						    </table>
						</div>
						
						<div id="removeMsg"></div>
						
						<hr>
						
						<div class="row">
							<div class="col-md-12" ng-show="showRemoveSucesso">
								<div class="alert alert-success" role="alert">
									<span>{{msgRemoveDisciplina}}</span>
									<span ng-click="fecharMsgRemover()" class="glyphicon glyphicon-remove-sign" style="float:right; color: #000000; cursor: pointer;" aria-hidden="true"></span>
								</div>
							</div>
							<div class="col-md-12" ng-show="showRemoveErro">
								<div class="alert alert-danger" role="alert">
									<span>{{msgRemoveDisciplina}}</span>
									<span ng-click="fecharMsgRemover()" class="glyphicon glyphicon-remove-sign" style="float:right; color: #000000; cursor: pointer;" aria-hidden="true"></span>
								</div>
							</div>
						</div>
						
						<div class="row" ng-show="filaDisciplinasProfessor.length > 0">
							<table class="table table-striped">
						    	<thead>
						        	<tr>
						          		<th>Disciplina</th>
						          		<th>Curso</th>
						          		<th ng-show="semestreSelecionado.status">Opções</th>
						        	</tr>
						      	</thead>
						      	<tbody>
						        	<tr ng-repeat="d in filaDisciplinasProfessor">
						          		<td>{{d.codigoDisciplina}} - {{d.nomeDisciplina}} - {{d.codigoCurso}}</td>
						          		<td>{{d.nomeCurso}}</td>
						          		<td style="text-align: center;" ng-show="semestreSelecionado.status">
						          			<button type="button" title="Remover" ng-click="atualizaStatusParaRemossao(d)" class="btn btn-danger">
												<i class="fa fa-trash"></i>
											</button>
						          		</td>
						        	</tr>
						      	</tbody>
						    </table>
						</div>
					</div>
				</accordion-group>
				
				<accordion-group ng-show="etapaAtiva('escolherPrioridade')" heading="Escolher prioridade de disciplinas" is-open="false">
					<div class="row">
	   					<div class="col-md-12" ng-show="semestreSelecionado.status">
	   						<button type="button" class="btn btn-primary" style="float:right;" ng-click="submeterFilaPrioridades()">SALVAR</button>
	   					</div>
					</div>
					
					<br/>
					
					<div class="row">
						<div class="col-md-12" ng-show="showPrioridadeSucesso">
							<div class="alert alert-success" role="alert">
								<span>{{msgAlteraPrioridade}}</span>
								<span ng-click="fecharMsgAlterarPrioridade()" class="glyphicon glyphicon-remove-sign" style="float:right; color: #000000; cursor: pointer;" aria-hidden="true"></span>
							</div>
						</div>
						<div class="col-md-12" ng-show="showPrioridadeErro">
							<div class="alert alert-danger" role="alert">
								<span>{{msgAlteraPrioridade}}</span>
								<span ng-click="fecharMsgAlterarPrioridade()" class="glyphicon glyphicon-remove-sign" style="float:right; color: #000000; cursor: pointer;" aria-hidden="true"></span>
							</div>
						</div>
					</div>
					
					<hr>
		    		
		      		<div class="floatleft" ng-show="semestreSelecionado.status">
		    			<ul ui-sortable="sortableOptions" ng-model="listPrioridade" class="list">
			      			<li ng-repeat="item in listPrioridade" class="item">
			      				<!--<span style="float:left; margin-left: 5px;">{{$index+1}}</span>-->
			      				<span>{{$index+1}} - {{item.codigoDisciplina}} - {{item.nomeDisciplina}} ({{item.turma}}) - {{item.codigoCurso}}</span>
			        			<!--<span ng-click="removeDisciplina(item)" class="glyphicon glyphicon-remove-sign" style="float:right; margin-right: 5px; color: #9C2525;" aria-hidden="true"></span>-->
			      			</li>
		    			</ul>
		  			</div>
		  			
		  			<div class="row col-md-12" ng-show="!semestreSelecionado.status">
		  				<table class="table table-striped">
					    	<thead>
					        	<tr>
					        		<th>Prioridade</th>
					          		<th>Código</th>
					          		<th>Disciplina</th>
					          		<th>Turma</th>
					          		<th>Curso</th>
					        	</tr>
					      	</thead>
					      	<tbody>
					        	<tr ng-repeat="l in list">
					          		<td>{{l.prioridade}}</td>
					          		<td>{{l.codigoDisciplina}}</td>
					          		<td>{{l.nomeDisciplina}}</td>
					          		<td>{{l.turma}}</td>
					          		<td>{{l.codigoCurso}}</td>
					        	</tr>
					      	</tbody>
					    </table>
		  			</div>
					<!--
		  			<div class="floatleft" style="margin-left: 20px;">
		    			<ul class="list logList">
		      				<li ng-repeat="entry in sortingLog track by $index" class="logItem">
		        				{{entry}}
		      				</li>
		    			</ul>
		  			</div>
		  			-->
		    	</accordion-group>
		    	
		    	<accordion-group ng-show="etapaAtiva('verificarHorarios')" heading="Verificar horários das disciplinas" is-open="false">
		    		<div class="col-md-12">
		    			<div class="row">
		    				<span>Disciplinas que estão da minha fila:</span>
		    				<tags-input ng-model="tagInput.tags" 
		    					on-tag-added="marcaHorario2($tag,true,false)" 
		    					replace-spaces-with-dashes="false" 
		    					add-from-autocomplete-only="true" 
		    					add-on-blur="false"
		    					placeholder="Busque uma disciplina"
		    					on-tag-removed="marcaHorario2($tag,false,false)"> 
		    					<auto-complete 
		    						source="loadItems($query)" 
		    						min-length="0" 
	                     			debounce-delay="0"
	                     			max-results-to-show="999"></auto-complete>
		      			</div>
		      			<div class="row" style="padding-top:10px">
		      				<span>Disciplinas que <b>não estão</b> na minha fila:</span>
		    				<tags-input ng-model="tagInput.tagsOutras" 
		    					on-tag-added="marcaHorario2($tag,true,false)" 
		    					replace-spaces-with-dashes="false" 
		    					add-from-autocomplete-only="true" 
		    					add-on-blur="false"
		    					placeholder="Busque uma disciplina"
		    					on-tag-removed="marcaHorario2($tag,false,false)"> 
		    					<auto-complete 
		    						source="loadItems2($query)" 
		    						min-length="0" 
	                     			debounce-delay="0"
	                     			max-results-to-show="999"></auto-complete>
		      			</div>
		      			<div class="row" style="padding-top:10px">
		      				<span>Horário por professor:</span>
		    				<tags-input ng-model="tagInput.tagsProfessores" 
		    					on-tag-added="marcaHorario2($tag,true,true)" 
		    					replace-spaces-with-dashes="false" 
		    					add-from-autocomplete-only="true" 
		    					add-on-blur="false"
		    					placeholder="Busque um professor"
		    					on-tag-removed="marcaHorario2($tag,false,true)"> 
		    					<auto-complete 
		    						source="loadItems3($query)" 
		    						min-length="0" 
	                     			debounce-delay="0"
	                     			max-results-to-show="999"></auto-complete>
		      			</div>
		      			<div class="col-md-10" style="padding-top: 15px;">
			      			<table class="table table-bordered" id="tabelaHorarioConflito">
								<tr>
									<th></th>
									<th ng-show="false">Domingo</th>
								    <th>Segunda-feira</th>
								    <th>Terça-feira</th>
								    <th>Quarta-feira</th>
								    <th>Quinta-feira</th>
								    <th>Sexta-feira</th>
								    <th>Sabado</th>
								</tr>
								<tr ng-repeat="c in calendario">
									<td style="padding: 3px;">{{c.horario}}</td>
								    <td ng-show="false" class={{c.domingo}}></td>
								    <td class={{c.segunda}}></td>
								    <td class={{c.terca}}></td>
								    <td class={{c.quarta}}></td>
								    <td class={{c.quinta}}></td>
								    <td class={{c.sexta}}></td>
								    <td class={{c.sabado}}></td>
								</tr>
							</table>
						</div>
	      			</div>
		    	</accordion-group>
		    	
		    	<!-- Exibir fila por disciplina -->
		    	<accordion-group ng-show="etapaAtiva('filaDisciplina')" heading="Exibir fila por disciplina" is-open="false">
		      		<div class="col-md-12">
		      			<div class="row">
							<p>Digite uma disciplina:</p>
						</div>
						<div class="row">
							<div class="col-md-8">
							<input 
	    						type="text"
	    						ng-model="disciplina"
	    						ng-required="true"
	    						name="repeatSelect"
	    						typeahead="disciplina as (disciplina.codigoDisciplina+' - '+disciplina.nomeDisciplina+' - '+disciplina.codigoCurso) for disciplina in disciplinaTurmaHorario | filter:$viewValue"
	    						typeahead-editable="false"
	    						typeahead-select-on-exact="true"
	    						autocomplete="off"
	    						typeahead-on-select="buscaFilaPorCodDisciplina(disciplina); disciplina = '';"
	    						class="form-control">
	    					</div>
						</div>
						
						<hr>
						
						<div class="row" ng-show="filaProfessores">
							<table class="table table-striped">
						    	<thead>
						      		<tr>
						          		<th colspan="4">{{disciplinaFilaInfo.codigoDisciplina}} - {{disciplinaFilaInfo.nomeDisciplina}} - {{disciplinaFilaInfo.codigoCurso}}</th>
						        	</tr>
						        	<tr>
						          		<th>Professor</th>
						          		<th>Posição na fila</th>
						          		<th>Prioridade</th>
						          		<th>Qte. Min/Qte. Max</th>
						        	</tr>
						      	</thead>
						      	<tbody>
						        	<tr ng-repeat="professor in filaProfessores">
						          		<td>{{professor.nomeProfessor}}</td>
						          		<td>{{professor.posicao}}</td>
						          		<td>{{professor.prioridade}}</td>
						          		<td>{{professor.quantidadeMinistrada}}</td>
						        	</tr>
						      	</tbody>
						    </table>
						</div>
					</div>
		    	</accordion-group>
		    	
		    	<!-- Exibir fila por professor (sem as turmas) -->
		  		<accordion-group ng-show="etapaAtiva('filaProfessorSemTurma')" heading="Exibir fila por professor (sem as turmas)" is-open="false">
		  			<div class="col-md-12">
		      			<div class="row">
							<p>Digite um professor:</p>
						</div>
						<div class="row">
							<div class="col-md-8">
							<input 
	    						type="text"
	    						ng-model="professorSelecionado"
	    						ng-required="true"
	    						name="repeatSelect"
	    						typeahead="p as p.nome for p in professores | filter:$viewValue"
	    						typeahead-editable="false"
	    						typeahead-select-on-exact="true"
	    						typeahead-on-select="listaFilaPorSiape(professorSelecionado.siape, professorSelecionado.nome); professorSelecionado = '';"
	    						autocomplete="off"
	    						class="form-control">    					
	    					</div>
						</div>
						
						<hr>
						
						<div class="row" ng-show="filaProfessor.length > 0">
							<table class="table table-striped">
						    	<thead>
						    		<tr>
						          		<th colspan="4">{{professorFilaInfo}}</th>
						        	</tr>
						        	<tr>
						          		<th>Disciplina</th>
						          		<th>Curso</th>
						          		<th>Prioridade</th>
						          		<th>Posição</th>
						          		<th>Qte. Min/Qte. Max</th>
						        	</tr>
						      	</thead>
						      	<tbody>
						        	<tr ng-repeat="fp in filaProfessor">
						          		<td>{{fp.codigoDisciplina}} - {{fp.nomeDisciplina}}</td>
						          		<td>{{fp.nomeCurso}}</td>
						          		<td>{{fp.prioridade}}</td>
						          		<td>{{fp.posicao}}</td>
						          		<td>{{fp.quantidadeMinistrada}}</td>
						        	</tr>
						      	</tbody>
						    </table>
						</div>
					</div>
		  		</accordion-group>
		  		
		  		<!-- Exibir fila por turma -->
		    	<accordion-group ng-show="etapaAtiva('filaTurma')" heading="Exibir fila por turma" is-open="false">
		      		<div class="col-md-12">
		      			<div class="row">
							<p>Digite uma disciplina:</p>
						</div>
						<div class="row">
							<div class="col-md-8">
							
							<input 
	    						type="text"
	    						ng-model="disciplinaSelecionada"
	    						ng-required="true"
	    						name="repeatSelect"
	    						typeahead="d as (d.codigoDisciplina+' - ('+d.nomeDisciplina+' '+d.turma+') - '+d.codigoCurso) for d in disciplinas | filter:$viewValue"
	    						typeahead-editable="false"
	    						typeahead-select-on-exact="true"
	    						typeahead-on-select="buscaProfessoresPorCodDisciplina(disciplinaSelecionada); disciplinaSelecionada = '';"
	    						autocomplete="off"
	    						class="form-control">
	    					</div>
						</div>
						
						<hr>
						
						<div class="row" ng-show="professoresDisciplina">
							<table class="table table-striped">
						    	<thead>
						      		<tr>
						          		<th colspan="3">{{professoresDisciplina.disciplina.nome}}</th>
						        	</tr>
						        	<tr>
						          		<th>Professor</th>
						          		<th>Posição na fila</th>
						          		<th>Prioridade</th>
						          		<th>Qte. Min/Qte. Max</th>
						        	</tr>
						      	</thead>
						      	<tbody>
						        	<tr ng-repeat="professor in professoresDisciplina.listaProfessores">
						          		<td>{{professor.nome}}</td>
						          		<td>{{professor.pos}}</td>
						          		<td>{{professor.prioridade}}</td>
						          		<td>{{professor.quantidadeMinistrada}} / {{professor.quantidadeMaxima}}</td>
						        	</tr>
						      	</tbody>
						    </table>
						</div>
					</div>
		    	</accordion-group>
		    	
		    	<!-- Exibir fila por professor (com as turmas) -->
		  		<accordion-group ng-show="etapaAtiva('filaProfessorComTurma')" heading="Exibir fila por professor (com as turmas)" is-open="false">
		  			<div class="col-md-12">
		      			<div class="row">
							<p>Digite um professor:</p>
						</div>
						<div class="row">
							<div class="col-md-8">
							<input 
	    						type="text"
	    						ng-model="professorSelecionado"
	    						ng-required="true"
	    						id="repeatSelect"
	    						name="repeatSelect"
	    						typeahead="p as p.nome for p in professores | filter:$viewValue"
	    						typeahead-editable="false"
	    						typeahead-select-on-exact="true"
	    						typeahead-on-select="buscaDisciplinasPorSiape(professorSelecionado.siape, professorSelecionado.nome); professorSelecionado = '';"
	    						autocomplete="off"
	    						class="form-control">
	    					</div>
						</div>
						
						<hr>
						
						<div class="row" ng-show="disciplinasProfessor.length > 0">
							<table class="table table-striped">
						    	<thead>
						    		<tr>
						          		<th colspan="3">{{professorFilaInfoNome}}</th>
						        	</tr>
						        	<tr>
						          		<th>Disciplina</th>
						          		<th>Turma</th>
						          		<th>Curso</th>
						          		<th>Posição</th>
						          		<th>Prioridade</th>
						          		<th>Qte. Min/Qte. Max</th>
						        	</tr>
						      	</thead>
						      	<tbody>
						        	<tr ng-repeat="d in disciplinasProfessor">
						          		<td>{{d.codigoDisciplina}} - {{d.nomeDisciplina}}</td>
						          		<td>{{d.turma}}</td>
						          		<td>{{d.nomeCurso}}</td>
						          		<td>{{d.pos}}</td>
						          		<td>{{d.prioridade}}</td>
						          		<td>{{d.qteMinistrada}} / {{d.qteMaxima}}</td>
						        	</tr>
						      	</tbody>
						    </table>
						</div>
					</div>
		  		</accordion-group>
		  		
		  		<accordion-group ng-show="etapaAtiva('restricoes')" class="panel-no-padding" heading="Exibir restrições" is-open="false">
					<div class="col-md-12" style="padding: 0;">
						<table class="table table-hover" style="padding: 0;">
							<thead>
								<tr class="tableHeader">
									<th class="col-md-5">Siape - Professor</th>
									<th class="col-md-3">Dia</th>
									<th class="col-md-3">Horário(letra)</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="r in restricoes">
									<td class="col-md-3">{{r.restricao.siape}} - {{r.professorNome}}</td>
									<td class="col-md-3">{{r.diaNome}} - ({{r.restricao.dia}})</td>
									<td class="col-md-3">{{r.horarioNome}} - ({{r.restricao.letra}})</td>
								</tr>
								<tr ng-show="!restricoes || restricoes.length <= 0">
									<td colspan="4" >
										<h5>Nenhum registro encontrado</h5>
									</td>
								</tr>
							</tbody>
					  	</table>
					</div>
				</accordion-group>
		  	</accordion>
		
			<hr>
		
			<footer>
		    	<p>&copy;UFU 2016</p>
			</footer>

	    </div><!--/.container-->

	    <!-- Bootstrap core JavaScript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <script src="../css/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

  	</body>
</html>
