<!DOCTYPE html>
<html ng-app="indexApp">
  <head>
	<%@ page contentType="text/html; charset=UTF-8" %>
    <title>Courses Distribution</title>
    
    <!--  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
  	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script> -->
  	<script src="js/lib/jquery.min.js"></script>
  	<script src="js/lib/jquery-ui.min.js"></script>
    <script src="js/lib/angular.min.js"></script>
    <script src="js/lib/ui-bootstrap-tpls-0.13.4.min.js"></script>
    <script src="js/lib/js-custom-select/customSelect.js"></script>
    <!--  <script src="https://rawgithub.com/angular-ui/ui-sortable/master/src/sortable.js"></script> -->
    <script src="js/controller/index.js"></script>
    <script src="js/lib/ngStorage.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="css/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" />
	<!-- <script src="css/lib/font-awesome.min.css"></script> -->
    <!-- Custom styles for this template -->
    <link href="css/main.css" rel="stylesheet">
    <link href="js/lib/tags-input/ng-tags-input.bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/lib/tags-input/ng-tags-input.min.js"></script>
    
    <!-- <link rel="stylesheet" href="../js/lib/js-custom-select/style.css" /> -->
  </head>

  <body>
    <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Distribuição de Disciplinas</a>
        </div>
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container" ng-controller="IndexController">
    	<!--
   		<span>Siape:</span>
    	<input type="text" ng-model="siape" ng-keypress="clicarEnter($event)" />
    	<button type="button" class="btn" ng-click="entrar()">ENTRAR</button>
    	
    	<hr>
		-->
		<div ng-view class="row">
		
			<div class="col-md-4"></div>
			
			<div class="col-md-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Siape (sem zeros à esquerda):</h3>
					</div>
					<div class="panel-body">
						<form role="form">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Siape" name="siape" type="text" autofocus="" ng-model="siape" ng-keypress="clicarEnter($event)" />
								</div>
								<button type="button" class="btn  btn-sm btn-success" ng-click="entrar()">ENTRAR</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			
			<div class="col-md-4"></div>
			
		</div>
	
		<hr>
	
		<footer>
	    	<p>&copy;UFU 2015</p>
		</footer>

    </div><!--/.container-->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="css/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

  </body>
</html>
